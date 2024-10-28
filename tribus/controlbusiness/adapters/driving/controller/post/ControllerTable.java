package es.santander.ucr.tribus.controlbusiness.adapters.driving.controller.post;


import es.santander.darwin.common.exceptions.dto.ErrorModelGateway;
import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.input.TableInputDTO;
import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.output.TableOutputDTO;
import es.santander.ucr.tribus.controlbusiness.core.constants.Constants;
import es.santander.ucr.tribus.controlbusiness.core.constants.UrlConstants;
import es.santander.ucr.tribus.controlbusiness.ports.inside.TablePort;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * Clase rest controller encargada de retornar
 * la lista de control de negocio
 *
 * @author x627544
 */


//Anotación para indicar que es un controlador y agregar su configuración
@RestController
//Anotación de logs
@Slf4j
@RequestMapping("${app.rutas.controladores.tribus.nombre}")
@AllArgsConstructor
public class ControllerTable {

    private final TablePort port;


    /**
     * Método getListTable encargado de obtener la lista de la tabla de
     * control de negocios mediante un input con los filtros
     *
     * @param input filtros datos por el frontal
     * @param page  número de página
     * @param size  número de elementos de la página
     * @return ResponseEntity de TableOutputDTO
     * @throws ParseException excepción
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
    @PostMapping(value = UrlConstants.URL_TABLE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<TableOutputDTO>> getListTable(
            @RequestBody TableInputDTO input,
            @RequestParam(value = Constants.PAGE,
                    defaultValue = Constants.DEFAULT_PAGE) int page,
            @RequestParam(value = Constants.SIZE,
                    defaultValue = Constants.DEFAULT_SIZE) int size) throws ParseException {
        log.info("Method: getListTable");
        return this.port.executeGetTableControlBusiness(input, page, size);

    }


}
