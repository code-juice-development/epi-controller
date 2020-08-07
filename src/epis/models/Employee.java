package epis.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

/**
 * Model class of Employee
 * @author Jean Poffo
 * @since  28/07/2020
 */
@Entity(name = "employees")
public class Employee extends DataObject {
 
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "cpf", nullable = false)
    private String cpf;
    
    @Column(name = "occupation", nullable = false)
    private String occupation;
    
    @Column(name = "active", nullable = false)
    private boolean active;
    
    @ManyToMany(cascade={ CascadeType.REFRESH })
    @JoinTable(
        name = "Employee_Epi", 
        joinColumns = { @JoinColumn(name = "employee_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "epi_id") }
    )
    private List<Epi> epis = new ArrayList<>();
    
    @Transient
    private boolean selected;
    
    public Employee() {}

    public Employee(String name, String cpf, String occupation, boolean active) {
        this.name = name;
        this.cpf = cpf;
        this.occupation = occupation;
        this.active = active;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Epi> getEpis() {
        return epis;
    }

    public void setEpis(List<Epi> epis) {
        this.epis = epis;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
}
