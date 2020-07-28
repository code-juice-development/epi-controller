package epis.controller;

import epis.utils.Message;

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
        
    }
    
    public void callActionOpenEpisDisplay() {
        
    }
    
    public void callActionAboutSystemDisplay() {
        Message.information("Desenvolvido por Jean Poffo", "Sobre");
    }
    
    public void callActionOpenReportDisplay() {
        
    }
    
}
