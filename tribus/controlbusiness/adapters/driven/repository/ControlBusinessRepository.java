package es.santander.ucr.tribus.controlbusiness.adapters.driven.repository;

import es.santander.ucr.tribus.controlbusiness.adapters.driven.entity.ControlBusiness;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Control de negocios
 * para obtener llamadas a la base de datos de controlbusiness
 */
@Repository
public interface ControlBusinessRepository extends MongoRepository<ControlBusiness, Integer> {


}
