package es.santander.ucr.tribus.manager.core.constants;

import lombok.experimental.UtilityClass;

/**
 * Clase con las constantes genéricas no definidas
 */
@UtilityClass
public class GenericConstants {

    public static final String CHARS = "value";

    /**
     * Controla que no haya valores raros y limita el tamaño del regex
     *
     * @param chars valor para transformar
     */
    public static void controlRegexValue(String chars) {
        if (chars.length() > 100) {
            throw new IllegalArgumentException("Input too long");
        }

        if (!chars.matches("[a-zA-Z0-9]*")) {
            throw new IllegalArgumentException("Invalid input");
        }
    }

}
