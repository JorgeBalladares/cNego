package es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.output;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Class ModalOutputDTO que sirve de output el modal de la segunda pantalla
 *
 * @author x627544
 */

@Setter
//Anotación lombok
@Getter
//Anotación lombok
@NoArgsConstructor
public class ModalOutputDTO {


    /**
     * area
     */
    private String area;

    /**
     * coe
     */
    private String coe;

    /**
     * typeForo
     */
    private String typeForo;

    /**
     * status
     */
    private String status;

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
     * dateFinal
     */
    private String dateFin;

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
     * number
     */
    private Integer numberId;

    /**
     * Constructor para inicializar la lista de participantes
     *
     * @param participants participantes
     */
    public ModalOutputDTO(List<ParticipantsDTO> participants) {
        this.participants = participants;
    }
}
