package es.santander.ucr.tribus.controlbusiness.core.usecase;


import es.santander.ucr.tribus.controlbusiness.adapters.driven.entity.ControlBusiness;
import es.santander.ucr.tribus.controlbusiness.adapters.driven.model.Participants;
import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.input.ModalInputDTO;
import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.output.ModalOutputDTO;
import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.output.ParticipantsDTO;
import es.santander.ucr.tribus.controlbusiness.core.constants.DateFormatConstants;
import es.santander.ucr.tribus.controlbusiness.ports.inside.ModalPort;
import es.santander.ucr.tribus.controlbusiness.ports.outside.ModalRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Caso de uso para obtener el moodal de la segunda pantalla
 * y actualizad o finalizar el registro completo
 *
 * @author x627544
 */
@Service
@AllArgsConstructor
@Slf4j
public class ModalUseCase implements ModalPort {

    /**
     * Inyección de depencias
     */
    private final ModalRepositoryPort port;


    /**
     * Método para obtener el modal con la información completa de la tabla
     *
     * @param numberId número de registro
     * @return response entity con los datos del modal
     */
    public ResponseEntity<ModalOutputDTO> executeGetModalInfo(Integer numberId) {

        log.info("Getting register ControlBusiness from data base");
        ControlBusiness valuesModal = port.findByIdRepo(numberId);

        return ResponseEntity.ok(mappingToModalDto(valuesModal));

    }


    /**
     * Mapeo de ControlBusiness a ModalOutputDTO
     *
     * @param controlBusiness colección con datos para mapear
     * @return objeto mapeado ModelOuputDTO
     */
    private ModalOutputDTO mappingToModalDto(ControlBusiness controlBusiness) {

        log.info("Mapping Modal ControlBusiness");

        ModalOutputDTO modalOutputDTO = new ModalOutputDTO();
        modalOutputDTO.setArea(controlBusiness.getArea());
        modalOutputDTO.setCoe(controlBusiness.getCoe());
        modalOutputDTO.setTypeForo(controlBusiness.getTypeForo());
        modalOutputDTO.setStatus(controlBusiness.getStatus());
        modalOutputDTO.setMedio(controlBusiness.getMedio());
        modalOutputDTO.setHourTime(controlBusiness.getHourTime());
        modalOutputDTO.setMinuteTime(controlBusiness.getMinuteTime());
        if (Objects.nonNull(controlBusiness.getDateIni()) && !controlBusiness.getDateIni().isBlank()) {
            modalOutputDTO.setDateFin(DateFormatConstants.formatDateToDDMMYYYY(controlBusiness.getDateIni()));
        } else {
            modalOutputDTO.setDateIni(null);
        }

        if (Objects.nonNull(controlBusiness.getDateRemember()) && !controlBusiness.getDateRemember().isBlank()) {
            modalOutputDTO.setDateRemember(DateFormatConstants.formatDateToDDMMYYYY(controlBusiness.getDateRemember()));
        } else {
            modalOutputDTO.setDateRemember(null);
        }
        modalOutputDTO.setParticipants(mappingParticipantsToDto(controlBusiness.getParticipants()));
        modalOutputDTO.setIssue(controlBusiness.getIssue());
        modalOutputDTO.setProgress(controlBusiness.getProgress());
        modalOutputDTO.setResult(controlBusiness.getResult());
        modalOutputDTO.setNumberId(controlBusiness.getNumberId());

        return modalOutputDTO;


    }


    /**
     * Mapeo de Participants a ParticipantsDTO
     *
     * @param participants lista de participantes
     * @return lista de objetos mapeado ParticipantsDTO
     */
    private List<ParticipantsDTO> mappingParticipantsToDto(List<Participants> participants) {

        log.info("Mapping participants to participantsDto");
        if (Objects.nonNull(participants) && !participants.isEmpty()) {
            List<ParticipantsDTO> listDto = new ArrayList<>();
            for (Participants participant : participants) {
                ParticipantsDTO participantsDTO = new ParticipantsDTO();
                participantsDTO.setCoe(participant.getCoe());
                participantsDTO.setJobTitle(participant.getJobTitle());
                participantsDTO.setIsComitee(participant.getIsComitee());
                participantsDTO.setIsAttend(participant.getIsAttend());
                listDto.add(participantsDTO);
            }
            return listDto;
        } else {
            return null;
        }

    }


    /**
     * Método para actualizar los datos de la colleción obtenida mediante
     * el numberId donde se verifica y mapea registros para adaptarlos a ControlBusiness
     * y retorna una respueta mapeada de ModalOutputDTO
     *
     * @param inputDTO valores de entrada input
     * @return respuesta de ModalOutputDTO
     */
    public ResponseEntity<ModalOutputDTO> executeUpdateControlBusiness(ModalInputDTO inputDTO) {

        log.info("Verifying if variables are corrects");
        if (Objects.isNull(inputDTO)) {
            throw new RuntimeException("Empty json");
        }

        //Verificamos que las fecha recordatorio no es inferio a la fecha de inicio
        DateFormatConstants.verifyLastDateNotInferior(inputDTO.getDateIni(), inputDTO.getDateRemember());


        String dateIni = null;
        if (Objects.nonNull(inputDTO.getDateIni()) && !inputDTO.getDateIni().isBlank()) {
            dateIni = DateFormatConstants.formatDateFirstHour(inputDTO.getDateIni());
        }

        String dateRemember = null;
        if (Objects.nonNull(inputDTO.getDateRemember()) && !inputDTO.getDateRemember().isBlank()) {
            dateRemember = DateFormatConstants.formatDateFirstHour(inputDTO.getDateRemember());
        }

        log.info("Updating values in collection");
        ControlBusiness controlBusiness = port.updateBusinessControl(inputDTO,
                mappingDtoToParticipants(inputDTO.getParticipants()), dateIni, dateRemember);

        return ResponseEntity.ok(mappingToModalDto(controlBusiness));


    }


    /**
     * Método para mapear participantes DTO a participantes para que pueda
     * ser agregado a ControlBusiness
     *
     * @param participants lista de participantes inputs
     * @return lista de participantes mapeada
     */
    private List<Participants> mappingDtoToParticipants(List<ParticipantsDTO> participants) {

        log.info("Mapping participantsDto to participants");
        if (Objects.nonNull(participants) && !participants.isEmpty()) {
            List<Participants> listParticipants = new ArrayList<>();
            var pos = 0;
            for (ParticipantsDTO participantsDTO : participants) {
                Participants participant = new Participants();
                participant.setNumber(pos);
                participant.setCoe(participantsDTO.getCoe());
                participant.setJobTitle(participantsDTO.getJobTitle());
                participant.setIsComitee(participantsDTO.getIsComitee());
                participant.setIsAttend(participantsDTO.getIsAttend());
                listParticipants.add(participant);
                pos++;
            }
            return listParticipants;
        } else {
            return null;
        }

    }


}
