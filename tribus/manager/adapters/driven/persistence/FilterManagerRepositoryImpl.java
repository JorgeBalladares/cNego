package es.santander.ucr.tribus.manager.adapters.driven.persistence;


import es.santander.ucr.tribus.manager.adapters.driven.entity.Manager;
import es.santander.ucr.tribus.manager.core.constants.ManagerConstants;
import es.santander.ucr.tribus.manager.port.outside.FilterManagerRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Class FilterManagerRepositoryImpl servicio que gestiona las llamadas a la base de datos
 *
 * @author x627544
 */

//Annotation lombok
@AllArgsConstructor
@Service
//Anotación de logs
@Slf4j
public class FilterManagerRepositoryImpl implements FilterManagerRepositoryPort {

    /**
     * Inyección dependencia mongoTemplate
     */
    private final MongoTemplate mongoTemplate;
    public static final String LOG_COMBO = "Filter: Obtaining -- filter {}";

    /**
     * Obtiene lista predictiva de COE para control de negocios
     *
     * @param chars caracteres dados desde el frontal
     * @return lista de string filtrada
     * @throws IOException excepción
     */
    public List<Manager> getListPredictive(String chars) throws IOException {

        try {

            Query query = new Query();
            query.addCriteria(new Criteria().orOperator(
                    Criteria.where(ManagerConstants.MANAGER)
                            .regex(Pattern.quote(chars), "i")));
            log.info(LOG_COMBO, ManagerConstants.MANAGER);
            return mongoTemplate.find
                    (query, Manager.class);


        } catch (Exception e) {
            log.error("Error in getListPredictive with filter");
            throw new IOException("Error: error getting list predictive from repositories", e);
        }

    }


}
