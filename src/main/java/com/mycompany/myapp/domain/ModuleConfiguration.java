package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ModuleConfiguration.
 */
@Entity
@Table(name = "module_configuration")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ModuleConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "moc_name")
    private String mocName;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMocName() {
        return mocName;
    }

    public ModuleConfiguration mocName(String mocName) {
        this.mocName = mocName;
        return this;
    }

    public void setMocName(String mocName) {
        this.mocName = mocName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ModuleConfiguration)) {
            return false;
        }
        return id != null && id.equals(((ModuleConfiguration) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ModuleConfiguration{" +
            "id=" + getId() +
            ", mocName='" + getMocName() + "'" +
            "}";
    }
}
