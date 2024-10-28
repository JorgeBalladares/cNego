package es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.output;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class TableOutputDTO que sirve de output de la lista de control de negocio
 *
 * @author x627544
 */

@Setter
//Anotación lombok
@Getter
//Anotación lombok
@NoArgsConstructor
public class TableOutputDTO {

    /**
     * the coe
     */
    private String coe;

    /**
     * the area
     */
    private String area;

    /**
     * the typeForo
     */
    private String typeForo;

    /**
     * the status
     */
    private String status;

    /**
     * the dateIni
     */
    private String dateIni;

    /**
     * the dateFin
     */
    private String dateFin;

    /**
     * the number
     */
    private Integer number;

}
