package es.santander.ucr.tribus.controlbusiness.ports.outside;

import es.santander.ucr.tribus.controlbusiness.adapters.driven.entity.ControlBusiness;
import es.santander.ucr.tribus.controlbusiness.adapters.driven.model.Participants;
import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.input.ModalInputDTO;

import java.util.List;

/**
 * Puerto que agrupa las llamadas y el proceso de la base de datos
 * para generar el modal de la segunda pantalla
 *
 * @author x627544
 */
public interface ModalRepositoryPort {


    /**
     * Método para encontrar el elemento de control de negocio asociado al valor
     * pasado por parámetro
     *
     * @param numberId número de id
     * @return elemento encontrado
     */
    ControlBusiness findByIdRepo(Integer numberId);


    /**
     * Método para actualizar los datos de la colección obtenida por su numberId
     *
     * @param input            valores de entrada inputs
     * @param participantsList lista de participantes mapeada
     * @param dateIni          fecha de inicio mapeada
     * @param dateRemember     fecha de recordatorio mapeado
     * @return colección tras guardado
     */
    ControlBusiness updateBusinessControl(ModalInputDTO input, List<Participants> participantsList,
                                          String dateIni, String dateRemember);


}
