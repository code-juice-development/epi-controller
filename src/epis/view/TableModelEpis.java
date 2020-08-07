package epis.view;

import epis.controller.ControllerEpis;
import epis.models.Epi;
import javax.swing.table.AbstractTableModel;

/**
 * Table Model of Epis
 * @author Jean Poffo
 * @since  30/08/2020
 */
public class TableModelEpis extends AbstractTableModel {

    private final ControllerEpis controller;
            
    private final String[] columns = {"Nome", "Código CA", "Periodicidade"};

    public TableModelEpis(ControllerEpis controller) {
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
        Epi epi = (Epi) this.controller.getData().get(rowIndex);
        
        if(columnIndex == 0) return epi.getName();
        if(columnIndex == 1) return epi.getCodeCa();
        if(columnIndex == 2) return epi.getFrequency();
        
        return null;
    }
    
}
