package epis.controller;

import epis.models.DataObject;
import epis.models.Employee;
import epis.view.ViewEdit;
import epis.view.ViewEditEmployees;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Controller Maintenance of Employess
 * @author Jean Poffo
 * @since  30/08/2020
 */
public class ControllerEmployess extends ControllerMaintenance {
    
    @Override
    public void processUpdateData() {
        EntityManager entityMananger = Persistence.createEntityManagerFactory("EpisPU").createEntityManager();
        
        entityMananger.getTransaction().begin();
        
        List<Employee> result = entityMananger.createQuery(" from employees e ", Employee.class).getResultList();
        
        this.getData().clear();
        
        result.forEach(employee -> {
            this.getData().add(employee);
        });
        
        entityMananger.close();
    }

    @Override
    public void processActionEdit(DataObject entity) {
        EntityManager entityMananger = Persistence.createEntityManagerFactory("EpisPU").createEntityManager();
        
        Employee employeeToFind = (Employee) entity;
        
        entityMananger.getTransaction().begin();
        Employee employee = entityMananger.find(Employee.class, employeeToFind.getId());
        
        //Remove all Epis
        employee.getEpis().clear();
        
        entityMananger.getTransaction().commit();
        entityMananger.close();
        
        //Merge and insert new EPIS
        super.processActionEdit(entity);
    }
    
    @Override
    public ViewEdit getViewEdit(DataObject entity, ProcessAction process) {
        return new ViewEditEmployees(entity, process);
    }

    @Override
    public DataObject getDataObjectModel() {
        return new Employee();
    }
    
}
