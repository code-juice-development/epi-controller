package epis;

import com.formdev.flatlaf.FlatLightLaf;
import epis.controller.ControllerRoot;
import epis.view.ViewRoot;
import java.util.Arrays;
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
        
        //Fast the login
        Persistence.createEntityManagerFactory("EpisPU").createEntityManager().close();
        
        new ViewRoot(ControllerRoot.getInstance()).setVisible(true);
    }
    
}
