package es.santander.ucr.tribus.controlbusiness.core.constants;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class DateFormatConstants con constantes para parsear fechas
 *
 * @author x627544
 */
@UtilityClass
public class DateFormatConstants {

    /**
     * FORMAT_DATE
     */
    public static final String FORMAT_DATE = "dd-MM-yyyy";

    /**
     * INVERT_FORMAT_DATE
     */
    public static final String INVERT_FORMAT_DATE = "yyyy-MM-dd HH:mm:ss";

    /**
     * WRONG_DATE
     */
    public static final String WRONG_DATE = "Fecha inválida";

    /**
     * Formatea fecha desde a primera hora
     *
     * @param fechaRecibida valor de fecha
     * @return valor parseado
     * @throws IllegalArgumentException excepción
     */

    public static String formatDateFirstHour(String fechaRecibida) throws IllegalArgumentException {
        // Define los formatos de fecha
        SimpleDateFormat inputFormat = new SimpleDateFormat(FORMAT_DATE);
        inputFormat.setLenient(false);
        SimpleDateFormat outputFormat = new SimpleDateFormat(INVERT_FORMAT_DATE);

        try {
            // Parsear la fecha recibida
            Date fecha = inputFormat.parse(fechaRecibida);

            // Convertir la fecha al nuevo formato
            return outputFormat.format(fecha);
        } catch (ParseException e) {
            throw new IllegalArgumentException(WRONG_DATE);
        }
    }


    /**
     * Formatea fechas hasta con valores de última hora
     *
     * @param fechaRecibida valor fecha
     * @return fecha parseada
     * @throws IllegalArgumentException excepción
     */
    public static String formatDateLastHour(String fechaRecibida) throws IllegalArgumentException {
        // Define los formatos de fecha
        SimpleDateFormat inputFormat = new SimpleDateFormat(FORMAT_DATE);
        inputFormat.setLenient(false);
        SimpleDateFormat outputFormat = new SimpleDateFormat(INVERT_FORMAT_DATE);

        try {
            // Parsear la fecha recibida
            Date fecha = inputFormat.parse(fechaRecibida);

            // Usar Calendar para establecer la hora al último momento del día
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);

            // Convertir la fecha al nuevo formato
            return outputFormat.format(calendar.getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(WRONG_DATE);
        }
    }

    /**
     * Método para verificar que la fecha hasta no sea menor a la fecha desde
     *
     * @param dateIni fecha desde
     * @param dateFin fecha hasta
     * @throws IllegalArgumentException excepción
     */
    public static void verifyLastDateNotInferior(String dateIni, String dateFin) throws IllegalArgumentException {
        // Define el formato de fecha
        SimpleDateFormat formato = new SimpleDateFormat(FORMAT_DATE);
        formato.setLenient(false);

        try {
            // Parsear las fechas recibidas
            Date fechaInicial = formato.parse(dateIni);
            Date fechaFinal = formato.parse(dateFin);

            // Comparar las fechas
            if (fechaInicial.after(fechaFinal)) {
                throw new IllegalArgumentException("La primera fecha no puede ser mayor que la segunda.");
            }
            if (fechaFinal.before(fechaInicial)) {
                throw new IllegalArgumentException("La segunda fecha no puede ser menor que la primera.");
            }

        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido");
        }
    }


    /**
     * Formatea fecha desde a formato "dd-MM-yyyy"
     *
     * @param fechaRecibida valor de fecha
     * @return valor parseado en formato "dd-MM-yyyy"
     * @throws IllegalArgumentException excepción
     */
    public static String formatDateToDDMMYYYY(String fechaRecibida) throws IllegalArgumentException {
        // Define los formatos de fecha
        SimpleDateFormat inputFormat = new SimpleDateFormat(INVERT_FORMAT_DATE);
        inputFormat.setLenient(false);
        SimpleDateFormat outputFormat = new SimpleDateFormat(FORMAT_DATE);

        try {
            // Parsear la fecha recibida
            Date fecha = inputFormat.parse(fechaRecibida);

            // Convertir la fecha al formato "dd-MM-yyyy"
            return outputFormat.format(fecha);
        } catch (ParseException e) {
            throw new IllegalArgumentException(WRONG_DATE);
        }
    }



}
