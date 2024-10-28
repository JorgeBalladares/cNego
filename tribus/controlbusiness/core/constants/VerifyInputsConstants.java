package es.santander.ucr.tribus.controlbusiness.core.constants;

import lombok.experimental.UtilityClass;

/**
 * Clase para verificar si el input cumple con criterios
 */
@UtilityClass
public class VerifyInputsConstants {

    /**
     * Método para verificar si el input es menos de 100 caracteres
     *
     * @param input valor de entrada
     */
    public static void testString(String input) {

        if (input.length() > 100) {
            throw new IllegalArgumentException("Input too long");
        }


    }


}
