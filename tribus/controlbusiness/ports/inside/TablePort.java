package es.santander.ucr.tribus.controlbusiness.ports.inside;


import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.input.TableInputDTO;
import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.output.TableOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

/**
 * Puerto que agrupa los métodos de TableUseCase
 * @author x627544
 */
public interface TablePort {

    /**
     * Método que llama al repositorio para generar el listado paginado
     *
     * @param input filtros del frontal
     * @param page  número de página
     * @param size  número de elementos de la página
     * @return ResponseEntity con el paginado
     * @throws ParseException excepción
     */
    ResponseEntity<Page<TableOutputDTO>> executeGetTableControlBusiness(
            TableInputDTO input, Integer page, Integer size) throws ParseException;


}
