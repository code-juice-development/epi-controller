package epis.view;

import epis.controller.ControllerEmployess;
import javax.swing.table.AbstractTableModel;

/**
 * View Maintenance of Employess
 * @author Jean Poffo
 * @since  29/08/2020
 */
public final class ViewEmployees extends ViewMaintenance {

    public ViewEmployees() {
        super(new ControllerEmployess(), "Funcionários");
    }
    
    @Override
    public AbstractTableModel getTableModel() {
        return new TableModelEmployees((ControllerEmployess) this.getController());
    }
    
}
