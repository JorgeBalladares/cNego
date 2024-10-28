package es.santander.ucr.tribus.controlbusiness.adapters.driven.entity;

import es.santander.ucr.tribus.controlbusiness.adapters.driven.model.Participants;
import es.santander.ucr.tribus.controlbusiness.core.constants.ControlBusinessConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import java.util.List;


/**
 * Class controlbusiness referente a la colección ucr_control_negocio
 *
 * @author x627544
 */

@Setter
//Anotación lombok
@Getter
//Anotación lombok
@NoArgsConstructor
@Document(collection = ControlBusinessConstants.NAME_DB)
public class ControlBusiness {


    /**
     * number
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "increase")
    @SequenceGenerator(name = "increase", sequenceName = "increase", allocationSize = 1)
    private Integer numberId;

    /**
     * area
     */
    @Field(ControlBusinessConstants.AREA_BUSINESS)
    private String area;

    /**
     * coe
     */
    @Field(ControlBusinessConstants.COE)
    private String coe;

    /**
     * typeForo
     */
    @Field(ControlBusinessConstants.TYPE_FORO)
    private String typeForo;

    /**
     * status
     */
    @Field(ControlBusinessConstants.STATUS)
    private String status;

    /**
     * medio
     */
    @Field(ControlBusinessConstants.MEDIO)
    private String medio;

    /**
     * hourTime
     */
    @Field(ControlBusinessConstants.HOUR_TIME)
    private Integer hourTime;

    /**
     * minuteTime
     */
    @Field(ControlBusinessConstants.MINUTE_TIME)
    private Integer minuteTime;

    /**
     * dateInit
     */
    @Field(ControlBusinessConstants.DATE_INI)
    private String dateIni;

    /**
     * dateFinal
     */
    @Field(ControlBusinessConstants.DATE_FIN)
    private String dateFin;

    /**
     * dateRemeber
     */
    @Field(ControlBusinessConstants.DATE_REMENBER)
    private String dateRemember;

    /**
     * dateMod
     */
    @Field(ControlBusinessConstants.DATE_MOD)
    private String dateMod;

    /**
     * participants
     */
    private List<Participants> participants;

    /**
     * issue
     */
    @Field(ControlBusinessConstants.ISSUE)
    private String issue;

    /**
     * progress
     */
    @Field(ControlBusinessConstants.PROGRESS)
    private String progress;


    /**
     * progress
     */
    @Field(ControlBusinessConstants.RESULT)
    private String result;


    /**
     * Constructor para inicializar la lista de participantes
     *
     * @param participants participantes
     */

    public ControlBusiness(List<Participants> participants) {
        this.participants = participants;
    }
}
