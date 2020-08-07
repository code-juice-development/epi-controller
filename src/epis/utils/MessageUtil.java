package epis.utils;

import epis.models.DataObject;
import epis.view.components.ExternField;
import javax.swing.JOptionPane;

/**
 *
 * @author Jean Poffo
 * @since  28/08/2020
 */
public class MessageUtil {
    
    public static void information(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void warning(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
    
    public static void error(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
    
    public static boolean question(String message, String title) {
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION;
    }
    
    public static DataObject questExternField(ExternField externField, String title) {
        if(externField.getItemCount() == 0) {
            MessageUtil.warning("Já foram selecionados todos itens disponíves", "Aviso");
            
            return null;
        }
        
        if(JOptionPane.showConfirmDialog(null, externField, title, JOptionPane.DEFAULT_OPTION) == JOptionPane.OK_OPTION) {
           return externField.getController().getData().get(externField.getSelectedIndex());
        }
        
        return null;
    }
}
