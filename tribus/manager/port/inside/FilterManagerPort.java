package es.santander.ucr.tribus.manager.port.inside;

import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface FilterManagerPort {

    /**
     * Método que llama al repositorio para generar el listado de valores
     * filtrados por los caracteres pasados desde el frontal
     *
     * @param chars caracteres de entrada
     * @return ResponseEntity con la lista
     * @throws IOException excepción
     */
    ResponseEntity<List<String>> executeFilterManager(String chars) throws IOException;

}
