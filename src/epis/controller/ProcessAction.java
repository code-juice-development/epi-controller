package epis.controller;

import epis.models.DataObject;

/**
 * Closure Interface for Process Actions
 * @author Jean Poffo
 * @since  30/09/2020
 */
public interface ProcessAction {

    public void execute(DataObject entity);
    
}
