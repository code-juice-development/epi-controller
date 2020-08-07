package epis.view.components;

import epis.controller.ControllerEmployess;
import epis.models.Employee;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Extern Field to Employees
 * @author Jean Poffo
 * @since  03/09/2020
 */
public class ExternFieldEmployees extends ExternField {
    
    public ExternFieldEmployees() {
        super(new ControllerEmployess());
        
        this.setRenderer(this.getListCellRenderer());
        this.addActionListener(this.getActionListener());
    }

    @Override
    public void initItems() {
        this.getController().getData().forEach((dataObject) -> {
            this.addItem(dataObject);
        });
    }

    private ListCellRenderer getListCellRenderer() {
        return (ListCellRenderer<Employee>) (JList<? extends Employee> list, Employee employee, int index, boolean isSelected, boolean cellHasFocus) -> {
            JCheckBox checkBox = new JCheckBox(employee.getName());
                
            checkBox.setEnabled(isEnabled());
            checkBox.setFont(getFont());
            checkBox.setFocusPainted(false);
            checkBox.setBorderPainted(true);
            checkBox.setSelected(employee.isSelected());
            
            return checkBox;
        };
    }
    
    private ActionListener getActionListener() {
        return (ActionEvent actionEvent) -> {
            JComboBox comboBox = (JComboBox) actionEvent.getSource();
            Employee employee = (Employee) comboBox.getSelectedItem();
            
            employee.setSelected(!employee.isSelected());
        };
    }
    
}
