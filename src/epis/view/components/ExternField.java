package epis.view.components;

import epis.controller.ControllerMaintenance;
import javax.swing.JComboBox;

/**
 * Class base for Extern Fields
 * @author Jean Poffo
 * @since 02/09/2020
 */
abstract public class ExternField extends JComboBox<Object> {
    
    private ControllerMaintenance controller;

    public ExternField(ControllerMaintenance controller) {
        this.controller = controller;
        this.initItems();
    }

    public ExternField() {}

    protected void setController(ControllerMaintenance controller) {
        this.controller = controller;
    }
    
    public ControllerMaintenance getController() {
        return controller;
    }

    abstract void initItems();
    
}
