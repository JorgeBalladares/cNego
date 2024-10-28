package es.santander.ucr.tribus.controlbusiness.adapters.driving.controller.patch;

import es.santander.darwin.common.exceptions.dto.ErrorModelGateway;
import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.input.ModalInputDTO;
import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.output.ModalOutputDTO;
import es.santander.ucr.tribus.controlbusiness.core.constants.UrlConstants;
import es.santander.ucr.tribus.controlbusiness.ports.inside.ModalPort;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase rest controller encargada de actualizar
 * la colección con los datos del input
 *
 * @author x627544
 */


//Anotación para indicar que es un controlador y agregar su configuración
@RestController
//Anotación de logs
@Slf4j
@RequestMapping("${app.rutas.controladores.tribus.nombre}")
@AllArgsConstructor
public class ControllerModalUpdate {


    private final ModalPort port;


    /**
     * Método updateModal encargado de actualizar los datos del modal de control de negocios
     *
     * @param inputDTO valores de entrada para actualizar
     * @return ResponseEntity de ModalOutputDTO
     */
    // ResponseCode 200
    @ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = ResponseEntity.class,
                    example = "200", description = "successful operation")))
    // ResponseCode 204
    @ApiResponse(responseCode = "204", description = "non content", content = @Content)
    // ResponseCode 401
    @ApiResponse(responseCode = "401", description = "UNAUTHORIZED",
            content = @Content(schema = @Schema(implementation = ErrorModelGateway.class,
                    example = "401", description = "UNAUTHORIZED")))
    // ResponseCode 403
    @ApiResponse(responseCode = "403", description = "FORBIDDEN",
            content = @Content(schema = @Schema(implementation = ErrorModelGateway.class,
                    example = "403", description = "FORBIDDEN")))
    // ResponseCode 500
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(schema = @Schema(implementation = ErrorModelGateway.class,
                    example = "500", description = "Internal server error")))
    @PatchMapping(value = UrlConstants.URL_MODAL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModalOutputDTO> updateModal(
            @RequestBody ModalInputDTO inputDTO) {
        log.info("Method: updateModal");
        return this.port.executeUpdateControlBusiness(inputDTO);

    }


}
