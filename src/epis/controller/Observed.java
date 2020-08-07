package epis.controller;

import epis.view.Observer;

/**
 *
 * @author Jean Poffo
 * @since  31/08/2020
 */
public interface Observed {

    public void addObserver(Observer observer);

    public void removeObserver(Observer observer);   
    
    public void notifyUpdate();
    
}
