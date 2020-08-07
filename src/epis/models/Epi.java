package epis.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Model class of EPI
 * @author Jean Poffo
 * @since  28/08/2020
 */
@Entity(name = "epis")
public class Epi extends DataObject {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "codeCa", nullable = false)
    private String codeCa;

    @Column(name = "frequency", nullable = false)
    private Integer frequency;

    @ManyToMany(mappedBy = "epis")
    private List<Employee> employees = new ArrayList<>();
    
    public Epi() {}

    public Epi(long id) {
        this.id = id;
    }
    
    public Epi(String name, String codeCa, Integer frequency) {
        this.name = name;
        this.codeCa = codeCa;
        this.frequency = frequency;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeCa() {
        return codeCa;
    }

    public void setCodeCa(String codeCa) {
        this.codeCa = codeCa;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
    public int getMonthInterval() {
        return this.getFrequency() / 30;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
}
