package es.santander.ucr.tribus.controlbusiness.adapters.driven.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.santander.ucr.tribus.controlbusiness.core.constants.ParticipantsConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Clase auxiliar para añadir los asistentes a las reuniones de control de negocios
 *
 * @author x627544
 */
@Setter
//Anotación lombok
@Getter
//Anotación lombok
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Participants {

    /**
     * number
     */
    @Field(ParticipantsConstants.NUMBER)
    private Integer number;

    /**
     * coe
     */
    @Field(ParticipantsConstants.PARTICIPANT)
    private String coe;

    /**
     * jobTitle
     */
    @Field(ParticipantsConstants.JOB_TITLE)
    private String jobTitle;

    /**
     * isComitee
     */
    @Field(ParticipantsConstants.IS_COMITEE)
    private Boolean isComitee;

    /**
     * isAttend
     */
    @Field(ParticipantsConstants.IS_ATTEND)
    private Boolean isAttend;


}
