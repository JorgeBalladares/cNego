package es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.output;

import es.santander.ucr.tribus.controlbusiness.core.constants.ParticipantsConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Class ParticipantsDTO que sirve de output de la lista asistentes
 *
 * @author x627544
 */

@Setter
//Anotación lombok
@Getter
//Anotación lombok
@NoArgsConstructor
public class ParticipantsDTO {


    /**
     * coe
     */
    private String coe;

    /**
     * jobTitle
     */
    private String jobTitle;

    /**
     * isComitee
     */
    private Boolean isComitee;

    /**
     * isAttend
     */
    private Boolean isAttend;


}
