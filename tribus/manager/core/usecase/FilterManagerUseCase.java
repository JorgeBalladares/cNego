package es.santander.ucr.tribus.manager.core.usecase;


import es.santander.ucr.tribus.manager.adapters.driven.entity.Manager;
import es.santander.ucr.tribus.manager.core.constants.GenericConstants;
import es.santander.ucr.tribus.manager.port.inside.FilterManagerPort;
import es.santander.ucr.tribus.manager.port.outside.FilterManagerRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Caso de uso para obtener la lista filtrada de los coe
 * en función de los caracteres de entrada desde el frontal
 * y que llama al puerto que implementa la lógica que conecta a la base de datos
 */
@Service
@AllArgsConstructor
@Slf4j
public class FilterManagerUseCase implements FilterManagerPort {

    /**
     * port
     */
    private final FilterManagerRepositoryPort port;

    /**
     * Método que llama al repositorio para generar el listado de valores
     * filtrados por los caracteres pasados desde el frontal
     *
     * @param chars caracteres de entrada
     * @return ResponseEntity con la lista
     * @throws IOException excepción
     */
    public ResponseEntity<List<String>> executeFilterManager(String chars) throws IOException {

        log.info("Validating chars input");
        GenericConstants.controlRegexValue(chars);

        List<Manager> managers = port.getListPredictive(chars);

        if (Objects.isNull(managers) || managers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            log.info("Ok, return list");
            return ResponseEntity.ok(getTypeCombo(managers.stream(), Manager::getManager));
        }


    }

    /**
     * Método getTypeCombo que mapea y filtra la lista de las tablas
     *
     * @param stream stream lambda
     * @param mapper function mapper
     * @return TYpeCombo
     */
    <T> List<String> getTypeCombo(Stream<T> stream, Function<T, String> mapper) {
        return stream
                .map(mapper)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }


}
