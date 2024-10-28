package es.santander.ucr.tribus.manager.port.outside;


import es.santander.ucr.tribus.manager.adapters.driven.entity.Manager;

import java.io.IOException;
import java.util.List;

/**
 * Puerto que agrupa las llamadas y el proceso de la base de datos
 * para generar la lista filtrada de COE mediante un input string
 */
public interface FilterManagerRepositoryPort {

    /**
     * Obtiene lista predictiva de COE para control de negocios
     *
     * @param chars caracteres dados desde el frontal
     * @return lista de managers filtrados
     * @throws IOException excepción
     */
    List<Manager> getListPredictive(String chars) throws IOException;

}
