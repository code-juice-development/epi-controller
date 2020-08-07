package epis.controller;

import epis.utils.MessageUtil;
import epis.view.ViewEmployees;
import epis.view.ViewEpis;

/**
 * Controller Root of Application
 * @author Jean Poffo
 * @since  28/07/2020
 */
public final class ControllerRoot {
 
    private static ControllerRoot instance;

    private ControllerRoot() {}
    
    public static ControllerRoot getInstance() {
        if(instance == null) {
            instance = new ControllerRoot();
        }
        return instance;
    }
    
    public void callActionCloseSystem() {
        System.exit(0);
    }
    
    public void callActionOpenEmployeesDisplay() {
        new ViewEmployees().setVisible(true);
    }
    
    public void callActionOpenEpisDisplay() {
        new ViewEpis().setVisible(true);
    }
    
    public void callActionAboutSystemDisplay() {
        MessageUtil.information("Desenvolvido por Jean Poffo", "Sobre");
    }
    
    /**
     * @todo it is not the best thing to cople the Controller
     */
    public void callActionOpenReportDisplay() {
        new ControllerReportEmployeeEpi().callActionAdd();
    }
    
}
