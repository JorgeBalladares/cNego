package es.santander.ucr.tribus.controlbusiness.adapters.driven.persistence;


import es.santander.ucr.tribus.controlbusiness.adapters.driven.entity.ControlBusiness;
import es.santander.ucr.tribus.controlbusiness.adapters.driven.model.Participants;
import es.santander.ucr.tribus.controlbusiness.adapters.driven.repository.ControlBusinessRepository;
import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.input.ModalInputDTO;
import es.santander.ucr.tribus.controlbusiness.core.constants.DateFormatConstants;
import es.santander.ucr.tribus.controlbusiness.ports.outside.ModalRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Class ModalRepositoryImpl servicio que gestiona las llamadas a la base de datos
 * para generar los datos de la segunda pantalla
 *
 * @author x627544
 */

//Logs
@Slf4j
@Service
@AllArgsConstructor
public class ModalRepositoryImpl implements ModalRepositoryPort {

    /**
     * Inyección de dependencia
     */
    private final ControlBusinessRepository repository;

    /**
     * Método para encontrar el elemento de control de negocio asociado al valor
     * pasado por parámetro
     *
     * @param numberId número de id
     * @return elemento encontrado
     */
    public ControlBusiness findByIdRepo(Integer numberId) {

        ControlBusiness controlBusiness = repository.findById(numberId).orElse(null);

        if (Objects.isNull(controlBusiness)) {
            throw new NoSuchElementException("Not found");
        }
        return controlBusiness;

    }

    /**
     * Método para actualizar los datos de la colección obtenida por su numberId
     *
     * @param input            valores de entrada inputs
     * @param participantsList lista de participantes mapeada
     * @param dateIni          fecha de inicio mapeada
     * @param dateRemember     fecha de recordatorio mapeado
     * @return colección tras guardado
     */
    public ControlBusiness updateBusinessControl(ModalInputDTO input, List<Participants> participantsList,
                                                 String dateIni, String dateRemember) {

        ControlBusiness controlBusiness = findByIdRepo(input.getNumberId());

        controlBusiness.setTypeForo(input.getTypeForo());
        controlBusiness.setMedio(input.getMedio());
        controlBusiness.setHourTime(input.getHourTime());
        controlBusiness.setMinuteTime(input.getMinuteTime());
        controlBusiness.setDateIni(dateIni);
        controlBusiness.setDateRemember(dateRemember);
        controlBusiness.setDateMod(new SimpleDateFormat(DateFormatConstants.INVERT_FORMAT_DATE).format(new Date()));
        controlBusiness.setParticipants(participantsList);
        controlBusiness.setIssue(input.getIssue());
        controlBusiness.setProgress(input.getProgress());
        controlBusiness.setResult(input.getResult());

        repository.save(controlBusiness);

        log.info("Collection updated");

        return controlBusiness;

    }


}
