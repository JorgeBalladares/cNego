package es.santander.ucr.tribus.controlbusiness.core.usecase;


import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.input.TableInputDTO;
import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.output.TableOutputDTO;
import es.santander.ucr.tribus.controlbusiness.core.constants.DateFormatConstants;
import es.santander.ucr.tribus.controlbusiness.core.constants.VerifyInputsConstants;
import es.santander.ucr.tribus.controlbusiness.ports.inside.TablePort;
import es.santander.ucr.tribus.controlbusiness.ports.outside.TableRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Objects;

/**
 * Caso de uso para obtener la lista paginada en función
 * de los filtros dados por el frontal y que llama al puerto
 * que implementa la lógica que conecta a la base de datos
 *
 * @author x627544
 */
@Service
@AllArgsConstructor
public class TableUseCase implements TablePort {

    /**
     * TableRepositoryPort
     */
    private final TableRepositoryPort repositoryPort;


    /**
     * Método que llama al repositorio para generar el listado paginado
     *
     * @param input filtros del frontal
     * @param page  número de página
     * @param size  número de elementos de la página
     * @return ResponseEntity con el paginado
     * @throws ParseException excepción
     */
    public ResponseEntity<Page<TableOutputDTO>> executeGetTableControlBusiness(
            TableInputDTO input, Integer page, Integer size) throws ParseException {

        //Verificamos si cumple criterios los strings
        testingOkString(input);

        //Verificamos y obtenemos los valores de las fechas
        String filterDateIni = null;
        String filterDateFin = null;

        if (Objects.nonNull(input.getFilterDateStart()) && !input.getFilterDateStart().isBlank()) {
            filterDateIni = DateFormatConstants.formatDateFirstHour(input.getFilterDateStart());

            if (Objects.nonNull(input.getFilterDateLast()) && !input.getFilterDateLast().isBlank()) {
                //Verificamos si la fecha final no es inferior a la fecha inicial
                DateFormatConstants.verifyLastDateNotInferior(input.getFilterDateStart(), input.getFilterDateLast());
            }

        }

        if (Objects.nonNull(input.getFilterDateLast()) && !input.getFilterDateLast().isBlank()) {
            filterDateFin = DateFormatConstants.formatDateLastHour(input.getFilterDateLast());
        }

        Page<TableOutputDTO> pageList = repositoryPort.
                generateListFiltered(input, filterDateIni, filterDateFin, page, size);

        if (pageList.isEmpty() || pageList.getContent().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(pageList);
        }


    }

    /**
     * Método que agrupa test de los inputs
     *
     * @param input filtros
     */
    private void testingOkString(TableInputDTO input) {
        if (Objects.nonNull(input)) {
            testInput(input.getArea());
            testInput(input.getTypeForo());
            testInput(input.getStatus());
            testInput(input.getCoe());
            testInput(input.getFilterDateStart());
            testInput(input.getFilterDateLast());

        }
    }

    /**
     * test de los inputs
     *
     * @param value valor a testear
     */
    void testInput(String value) {
        if (Objects.nonNull(value) && !value.isBlank()) {
            VerifyInputsConstants.testString(value);
        }
    }


}
