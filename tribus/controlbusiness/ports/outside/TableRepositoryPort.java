package es.santander.ucr.tribus.controlbusiness.ports.outside;


import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.input.TableInputDTO;
import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.output.TableOutputDTO;
import org.springframework.data.domain.Page;

import java.text.ParseException;

/**
 * Puerto que agrupa las llamadas y el proceso de la base de datos
 * para generar la lista paginada
 *
 * @author x627544
 */
public interface TableRepositoryPort {

    /**
     * Método para generar la lista filtrada pasando criterios del input
     *
     * @param input         filtros ofrecidos por el frontal
     * @param filterDateIni fecha de generación desde
     * @param filterDateFin fecha de generación hasta
     * @param page          página del paginado
     * @param size          número de elementos de la página
     * @return paginado con la lista de control de negocio
     * @throws ParseException excepción
     */
    Page<TableOutputDTO> generateListFiltered(TableInputDTO input, String filterDateIni, String filterDateFin,
                                              Integer page, Integer size) throws ParseException;


}
