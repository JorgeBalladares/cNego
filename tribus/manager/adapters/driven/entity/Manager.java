package es.santander.ucr.tribus.manager.adapters.driven.entity;

import es.santander.ucr.tribus.manager.core.constants.ManagerConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class Manager referente a la colección ucr_manager
 *
 * @author x627544
 */

@Document(collection = ManagerConstants.MANAGER_COLLECTION)
//Anotación lombok
@Setter
//Anotación lombok
@Getter
//Anotación lombok
@NoArgsConstructor
public class Manager {

    /**
     * the id
     */
    @Id
    private String id;

    /**
     * the manager
     */
    private String manager;

    /**
     * the ucir
     */
    private String ucir;

}
