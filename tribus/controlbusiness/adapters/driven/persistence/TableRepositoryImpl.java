package es.santander.ucr.tribus.controlbusiness.adapters.driven.persistence;

import es.santander.ucr.tribus.controlbusiness.adapters.driven.model.CountAggregation;
import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.input.TableInputDTO;
import es.santander.ucr.tribus.controlbusiness.adapters.driving.dto.output.TableOutputDTO;
import es.santander.ucr.tribus.controlbusiness.core.constants.ControlBusinessConstants;
import es.santander.ucr.tribus.controlbusiness.ports.outside.TableRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class TableRepositoryImpl servicio que gestiona las llamadas a la base de datos
 *
 * @author x627544
 */

//Logs
@Slf4j
@Service
@AllArgsConstructor
public class TableRepositoryImpl implements TableRepositoryPort {

    //Inyección del mongoTemplate
    private final MongoTemplate mongoTemplate;
    private static final String LOG_CRITERIA = "Checking if the filter is present {}";


    /**
     * Método para generar la lista filtrada pasando criterios del input
     *
     * @param input         filtros ofrecidos por el frontal
     * @param filterDateIni fecha de generación desde
     * @param filterDateFin fecha de generación hasta
     * @param page          página del paginado
     * @param size          número de elementos de la página
     * @return paginado con la lista de control de negocio
     */
    public Page<TableOutputDTO> generateListFiltered(TableInputDTO input, String filterDateIni,
                                                     String filterDateFin, Integer page, Integer size) {

        log.info("Adding criteria to filter");
        Criteria criteria = new Criteria();
        this.fillCriteria(criteria, input, filterDateIni, filterDateFin);

        Pageable pageable = PageRequest.of(page, size);

        // Paso 1: Consulta para obtener el total de documentos
        Aggregation countAggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group()
                        .count().as("total")
        );

        AggregationResults<CountAggregation> countResults = mongoTemplate.aggregate(
                countAggregation, ControlBusinessConstants.NAME_DB, CountAggregation.class);

        long totalElements = countResults.getMappedResults().isEmpty() ? 0 :
                countResults.getMappedResults().get(0).getTotal();

        if (totalElements == 0) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }

        // Paso 2: Consulta para obtener los resultados paginados
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.sort(Sort.Direction.DESC, "fecha_modificacion"),
                Aggregation.project()
                        .and(ControlBusinessConstants.COE).as("coe")
                        .and(ControlBusinessConstants.AREA_BUSINESS).as("area")
                        .and(ControlBusinessConstants.TYPE_FORO).as("typeForo")
                        .and(ControlBusinessConstants.STATUS).as("status")
                        .and(ControlBusinessConstants.DATE_INI).as("dateToString('%Y-%m-%d', " +
                                                                   "dateFromString(dateIni))")
                        .and(ControlBusinessConstants.DATE_FIN).as("dateToString('%Y-%m-%d', " +
                                                                   "dateFromString(dateFin))")
                        .and("_id").as("number"),
                Aggregation.skip(pageable.getOffset()),
                Aggregation.limit(pageable.getPageSize())
        );

        // Obtener los resultados paginados
        AggregationResults<TableOutputDTO> results = mongoTemplate.aggregate(
                aggregation, ControlBusinessConstants.NAME_DB, TableOutputDTO.class);

        // Obtener la lista de resultados
        List<TableOutputDTO> mappedResults = results.getMappedResults();


        return new PageImpl<>(mappedResults, pageable, totalElements);
    }


    /**
     * Método que agrupa las agregaciones del criterio para filtrar
     *
     * @param criteria      criterio vacío
     * @param input         filtros dados por el frontal
     * @param filterDateIni fecha desde
     * @param filterDateFin fecha hasta
     */
    void fillCriteria(Criteria criteria, TableInputDTO input, String filterDateIni, String filterDateFin) {
        this.addCriteria("area", criteria, ControlBusinessConstants.AREA_BUSINESS, input.getArea());
        this.addCriteria("typeForo", criteria, ControlBusinessConstants.TYPE_FORO, input.getTypeForo());
        this.addCriteria("status", criteria, ControlBusinessConstants.STATUS, input.getStatus());
        this.addCriteria("coe", criteria, ControlBusinessConstants.COE, input.getCoe());
        this.addCriteriaDate(filterDateIni, filterDateFin, criteria);
    }

    /**
     * Método para agregar criterio que no son filtros de fechas
     *
     * @param constantLog frase log
     * @param criteria    criterio a modificar
     * @param constant    valor de la variable de la tabla
     * @param valueInput  valor para filtrar dentro de la variable
     */
    void addCriteria(String constantLog, Criteria criteria, String constant, String valueInput) {
        log.info(LOG_CRITERIA, constantLog);
        if (Objects.nonNull(valueInput) && !valueInput.isBlank()) {
            criteria.and(constant).is(valueInput);
        }

    }

    /**
     * Método para agregar criterios de las fechas
     *
     * @param filterDateIni fecha desde
     * @param filterDateFin fecha hasta
     * @param criteria      criterio modificable
     */
    void addCriteriaDate(String filterDateIni, String filterDateFin, Criteria criteria) {
        if (Objects.nonNull(filterDateIni)) {
            if (Objects.nonNull(filterDateFin)) {
                criteria.and(ControlBusinessConstants.DATE_INI).gte(filterDateIni).lte(filterDateFin);
            } else {
                criteria.and(ControlBusinessConstants.DATE_INI).gte(filterDateIni);
            }
        } else {
            if (Objects.nonNull(filterDateFin)) {
                criteria.and(ControlBusinessConstants.DATE_INI).lte(filterDateFin);
            }
        }
    }

}
