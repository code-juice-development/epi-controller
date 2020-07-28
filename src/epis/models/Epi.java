package epis.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Model class of EPI
 * @author Jean Poffo
 * @since  28/08/2020
 */
@Entity(name = "epis")
public class Epi implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "codigoCa", nullable = false)
    private String codigoCa;

    @Column(name = "periodicidade", nullable = false)
    private Integer periodicidade;

    public Epi() {}

    public Epi(String name, String codigoCa, Integer periodicidade) {
        this.name = name;
        this.codigoCa = codigoCa;
        this.periodicidade = periodicidade;
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

    public String getCodigoCa() {
        return codigoCa;
    }

    public void setCodigoCa(String codigoCa) {
        this.codigoCa = codigoCa;
    }

    public Integer getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(Integer periodicidade) {
        this.periodicidade = periodicidade;
    }
    
}
