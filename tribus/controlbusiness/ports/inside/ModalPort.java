package es.santander.ucr.tribus.controlbusiness.ports.inside;

import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.input.ModalInputDTO;
import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.output.ModalOutputDTO;
import org.springframework.http.ResponseEntity;

/**
 * Puerto que agrupa los métodos de ModalUseCase
 *
 * @author x627544
 */

public interface ModalPort {

    /**
     * Método para obtener el modal con la información completa de la tabla
     *
     * @param number número de registro
     * @return response entity con los datos del modal
     */
    ResponseEntity<ModalOutputDTO> executeGetModalInfo(Integer number);


    /**
     * Método para actualizar los datos de la colleción obtenida mediante
     * el numberId donde se verifica y mapea registros para adaptarlos a ControlBusiness
     * y retorna una respueta mapeada de ModalOutputDTO
     *
     * @param inputDTO valores de entrada input
     * @return respuesta de ModalOutputDTO
     */
    ResponseEntity<ModalOutputDTO> executeUpdateControlBusiness(ModalInputDTO inputDTO);


}
