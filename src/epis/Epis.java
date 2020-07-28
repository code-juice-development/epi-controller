package epis;

import com.formdev.flatlaf.FlatLightLaf;
import epis.controller.ControllerRoot;
import epis.models.Employee;
import epis.models.Epi;
import epis.view.ViewRoot;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main Class
 * @author Jan Poffo
 * @since  28/07/2020
 */
public class Epis {
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } 
        catch (UnsupportedLookAndFeelException ex) {
            System.out.println("Error on initialize LAF (" + Arrays.toString(ex.getStackTrace()) + ")");
        }
        
        new ViewRoot(ControllerRoot.getInstance()).setVisible(true);
    }
        
    private static void testeHibernate() {
        EntityManager entityMananger = Persistence.createEntityManagerFactory("EpisPU").createEntityManager();
        entityMananger.getTransaction().begin();
        
        Epi epiCapacete = new Epi("Capacete", "564545", 30);
        
        Employee funcionarioJoao = new Employee("João", "08576540239", "Soldador", true);
        funcionarioJoao.getEpis().add(epiCapacete);
        
        entityMananger.persist(epiCapacete);
        
        entityMananger.persist(funcionarioJoao);
        
        entityMananger.getTransaction().commit();
        entityMananger.close();
        
        entityMananger = Persistence.createEntityManagerFactory("EpisPU").createEntityManager();
        entityMananger.getTransaction().begin();
        
        List<Employee> result = entityMananger.createQuery(" from employees e ", Employee.class).getResultList();
        
        result.forEach(employee -> {
            System.out.println("Funcionario (" + employee.toString()+ ")");
        });
        
        entityMananger.getTransaction().commit();
        entityMananger.close();
    }
    
}
