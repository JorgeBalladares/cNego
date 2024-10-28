package es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.output.ParticipantsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Class ModalInputDTO que sirve de input para actualizar el registro del elemento
 * de control de negocios
 *
 * @author x627544
 */

@Setter
//Anotación lombok
@Getter
//Anotación lombok
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModalInputDTO {

    /**
     * number
     */
    private Integer numberId;

    /**
     * typeForo
     */
    private String typeForo;

    /**
     * medio
     */
    private String medio;

    /**
     * hourTime
     */
    private Integer hourTime;

    /**
     * minuteTime
     */
    private Integer minuteTime;

    /**
     * dateInit
     */
    private String dateIni;

    /**
     * dateRemeber
     */
    private String dateRemember;


    /**
     * participants
     */
    private List<ParticipantsDTO> participants;

    /**
     * issue
     */
    private String issue;

    /**
     * progress
     */
    private String progress;


    /**
     * result
     */
    private String result;


    /**
     * Constructor para inicializar la lista de participantes del output
     *
     * @param participants participantes
     */
    public ModalInputDTO(List<ParticipantsDTO> participants) {
        this.participants = participants;
    }
}
