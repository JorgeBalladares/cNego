package es.santander.ucr.tribus.manager.adapters.driving.controller;


import es.santander.darwin.common.exceptions.dto.ErrorModelGateway;
import es.santander.ucr.tribus.manager.core.constants.GenericConstants;
import es.santander.ucr.tribus.manager.core.constants.ManagerConstants;
import es.santander.ucr.tribus.manager.port.inside.FilterManagerPort;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Clase rest controller encargada de retornar
 * la lista de COE filtrada
 *
 * @author x627544
 */

//Anotación para indicar que es un controlador y agregar su configuración
@RestController
//Anotación de logs
@Slf4j
@RequestMapping("${app.rutas.controladores.tribus.nombre}")
@AllArgsConstructor
public class FilterCOEController {

    /**
     * port
     */
    private final FilterManagerPort port;

    /**
     * Método listFilterPredictive encargado de obtener los COE en función
     * de los caracteres dados desde el frontal
     *
     * @param chars Valores a filtrar
     * @return ResponseEntity de una lista de string
     * @throws IOException exception
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
    @GetMapping(value = ManagerConstants.URL_FILTER, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> listPredictiveManager(
            @RequestParam(name = GenericConstants.CHARS, required = false) String chars) throws IOException {
        log.info("Method: listPredictiveManager");
        return port.executeFilterManager(chars);
    }


}
