package epis.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Model Class of Report Employee x Epi
 * @author Jean Poffo
 * @since  03/09/2020
 */
public class ReportEmployeeEpi extends DataObject {
    
    private final List<Employee> employees = new ArrayList();
    
    private String initialDate;
    
    private String finalDate;

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }    

    public List<Employee> getEmployees() {
        return employees;
    }
    
}
