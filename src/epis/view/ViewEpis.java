package epis.view;

import epis.controller.ControllerEpis;
import javax.swing.table.AbstractTableModel;

/**
 * View Maintenance of Epis
 * @author Jean Poffo
 * @since  30/08/2020
 */
public class ViewEpis extends ViewMaintenance {

    public ViewEpis() {
        super(new ControllerEpis(), "Epi's");
    }
    
    @Override
    public AbstractTableModel getTableModel() {
        return new TableModelEpis((ControllerEpis) this.getController());
    }
    
}
