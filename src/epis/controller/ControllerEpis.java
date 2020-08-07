package epis.controller;

import epis.models.DataObject;
import epis.models.Epi;
import epis.view.ViewEdit;
import epis.view.ViewEditEpis;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Controller Maintenance of Epis
 * @author Jean Poffo
 * @since  30/08/2020
 */
public class ControllerEpis extends ControllerMaintenance {
    
    @Override
    public void processUpdateData() {
        EntityManager entityMananger = Persistence.createEntityManagerFactory("EpisPU").createEntityManager();
        
        entityMananger.getTransaction().begin();
        
        List<Epi> result = entityMananger.createQuery(" from epis e ", Epi.class).getResultList();
        
        this.getData().clear();
        
        result.forEach(epi -> {
            this.getData().add(epi);
        });
        
        entityMananger.close();
    }

    @Override
    public ViewEdit getViewEdit(DataObject entity, ProcessAction action) {
        return new ViewEditEpis(entity, action);
    }

    @Override
    public DataObject getDataObjectModel() {
        return new Epi();
    }
    
}
