package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CertificateEmergencyText.
 */
@Entity
@Table(name = "certificate_emergency_text")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CertificateEmergencyText implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cet_text")
    private String cetText;

    @Column(name = "cet_is_active")
    private Boolean cetIsActive;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCetText() {
        return cetText;
    }

    public CertificateEmergencyText cetText(String cetText) {
        this.cetText = cetText;
        return this;
    }

    public void setCetText(String cetText) {
        this.cetText = cetText;
    }

    public Boolean isCetIsActive() {
        return cetIsActive;
    }

    public CertificateEmergencyText cetIsActive(Boolean cetIsActive) {
        this.cetIsActive = cetIsActive;
        return this;
    }

    public void setCetIsActive(Boolean cetIsActive) {
        this.cetIsActive = cetIsActive;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CertificateEmergencyText)) {
            return false;
        }
        return id != null && id.equals(((CertificateEmergencyText) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CertificateEmergencyText{" +
            "id=" + getId() +
            ", cetText='" + getCetText() + "'" +
            ", cetIsActive='" + isCetIsActive() + "'" +
            "}";
    }
}
