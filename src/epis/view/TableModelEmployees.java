package epis.view;

import epis.controller.ControllerEmployess;
import epis.models.Employee;
import javax.swing.table.AbstractTableModel;

/**
 * Table Model of Employees
 * @author Jean Poffo
 * @since  30/08/2020
 */
public class TableModelEmployees extends AbstractTableModel {

    private final ControllerEmployess controller;
            
    private final String[] columns = {"Nome", "Função"};

    public TableModelEmployees(ControllerEmployess controller) {
        this.controller = controller;
    }
    
    @Override
    public int getRowCount() {
        return this.controller.getData().size();
    }

    @Override
    public int getColumnCount() {
        return this.columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return this.columns[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = (Employee) this.controller.getData().get(rowIndex);
        
        if(columnIndex == 0) return employee.getName();
        if(columnIndex == 1) return employee.getOccupation();
        
        return null;
    }
    
}
