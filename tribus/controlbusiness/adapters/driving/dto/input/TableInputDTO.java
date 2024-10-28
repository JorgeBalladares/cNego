package es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class TableInputDTO que sirve de input para obtener la lista de control de negocio
 *
 * @author x627544
 */

@Setter
//Anotación lombok
@Getter
//Anotación lombok
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableInputDTO {

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
     * the coe
     */
    private String coe;

    /**
     * the filterDateStart
     */
    private String filterDateStart;

    /**
     * the filterDateLast
     */
    private String filterDateLast;

}
