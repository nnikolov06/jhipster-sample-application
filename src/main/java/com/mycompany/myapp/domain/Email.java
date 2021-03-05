package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Email.
 */
@Entity
@Table(name = "email")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "eml_value")
    private String emlValue;

    @Column(name = "eml_is_active")
    private Boolean emlIsActive;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmlValue() {
        return emlValue;
    }

    public Email emlValue(String emlValue) {
        this.emlValue = emlValue;
        return this;
    }

    public void setEmlValue(String emlValue) {
        this.emlValue = emlValue;
    }

    public Boolean isEmlIsActive() {
        return emlIsActive;
    }

    public Email emlIsActive(Boolean emlIsActive) {
        this.emlIsActive = emlIsActive;
        return this;
    }

    public void setEmlIsActive(Boolean emlIsActive) {
        this.emlIsActive = emlIsActive;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Email)) {
            return false;
        }
        return id != null && id.equals(((Email) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Email{" +
            "id=" + getId() +
            ", emlValue='" + getEmlValue() + "'" +
            ", emlIsActive='" + isEmlIsActive() + "'" +
            "}";
    }
}
