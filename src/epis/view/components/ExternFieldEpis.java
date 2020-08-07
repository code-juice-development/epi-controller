package epis.view.components;

import epis.controller.ControllerEpis;
import epis.models.DataObject;
import epis.models.Epi;
import java.util.ArrayList;
import java.util.List;

/**
 * Extern Field to Epis
 * @author Jean Poffo
 * @since  02/09/2020
 */
public class ExternFieldEpis extends ExternField {

    private List<Long> listEpiIdFilter;
    
    public ExternFieldEpis(List<Long> listEpiIdFilter) {
        this.setListEpiIdFilter(listEpiIdFilter);
        this.setController(new ControllerEpis());
        this.initItems();
    }

    protected List<Long> getListEpiIdFilter() {
        return listEpiIdFilter;
    }

    protected void setListEpiIdFilter(List<Long> listEpiIdFilter) {
        this.listEpiIdFilter = listEpiIdFilter;
    }
    
    @Override
    public void initItems() {
        List<Epi> epiToRemove = new ArrayList();
        
        for (DataObject dataObject : this.getController().getData()) {
            Epi epi = (Epi) dataObject;
            
            if(listEpiIdFilter.contains(epi.getId())) {
                epiToRemove.add(epi);
            }
        }
        
        this.getController().getData().removeAll(epiToRemove);
        this.getController().getData().forEach((dataObject) -> this.addItem(dataObject));
    }
    
}
