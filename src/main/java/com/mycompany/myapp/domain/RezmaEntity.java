package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A RezmaEntity.
 */
@Entity
@Table(name = "rezma_entity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RezmaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ent_name")
    private String entName;

    @Column(name = "ent_address")
    private String entAddress;

    @Column(name = "ent_system_date")
    private Instant entSystemDate;

    @Column(name = "ent_created_by")
    private String entCreatedBy;

    @Column(name = "ent_identifier")
    private String entIdentifier;

    @Column(name = "ent_type")
    private String entType;

    @Column(name = "ent_country")
    private String entCountry;

    @Column(name = "ent_identifier_type")
    private String entIdentifierType;

    @Column(name = "ent_tin")
    private String entTin;

    @Column(name = "ent_dob")
    private String entDOB;

    @ManyToMany(mappedBy = "rezmaEntities")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Obligation> obligations = new HashSet<>();

    @ManyToMany(mappedBy = "rezmaEntities")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Inspection> inspections = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntName() {
        return entName;
    }

    public RezmaEntity entName(String entName) {
        this.entName = entName;
        return this;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getEntAddress() {
        return entAddress;
    }

    public RezmaEntity entAddress(String entAddress) {
        this.entAddress = entAddress;
        return this;
    }

    public void setEntAddress(String entAddress) {
        this.entAddress = entAddress;
    }

    public Instant getEntSystemDate() {
        return entSystemDate;
    }

    public RezmaEntity entSystemDate(Instant entSystemDate) {
        this.entSystemDate = entSystemDate;
        return this;
    }

    public void setEntSystemDate(Instant entSystemDate) {
        this.entSystemDate = entSystemDate;
    }

    public String getEntCreatedBy() {
        return entCreatedBy;
    }

    public RezmaEntity entCreatedBy(String entCreatedBy) {
        this.entCreatedBy = entCreatedBy;
        return this;
    }

    public void setEntCreatedBy(String entCreatedBy) {
        this.entCreatedBy = entCreatedBy;
    }

    public String getEntIdentifier() {
        return entIdentifier;
    }

    public RezmaEntity entIdentifier(String entIdentifier) {
        this.entIdentifier = entIdentifier;
        return this;
    }

    public void setEntIdentifier(String entIdentifier) {
        this.entIdentifier = entIdentifier;
    }

    public String getEntType() {
        return entType;
    }

    public RezmaEntity entType(String entType) {
        this.entType = entType;
        return this;
    }

    public void setEntType(String entType) {
        this.entType = entType;
    }

    public String getEntCountry() {
        return entCountry;
    }

    public RezmaEntity entCountry(String entCountry) {
        this.entCountry = entCountry;
        return this;
    }

    public void setEntCountry(String entCountry) {
        this.entCountry = entCountry;
    }

    public String getEntIdentifierType() {
        return entIdentifierType;
    }

    public RezmaEntity entIdentifierType(String entIdentifierType) {
        this.entIdentifierType = entIdentifierType;
        return this;
    }

    public void setEntIdentifierType(String entIdentifierType) {
        this.entIdentifierType = entIdentifierType;
    }

    public String getEntTin() {
        return entTin;
    }

    public RezmaEntity entTin(String entTin) {
        this.entTin = entTin;
        return this;
    }

    public void setEntTin(String entTin) {
        this.entTin = entTin;
    }

    public String getEntDOB() {
        return entDOB;
    }

    public RezmaEntity entDOB(String entDOB) {
        this.entDOB = entDOB;
        return this;
    }

    public void setEntDOB(String entDOB) {
        this.entDOB = entDOB;
    }

    public Set<Obligation> getObligations() {
        return obligations;
    }

    public RezmaEntity obligations(Set<Obligation> obligations) {
        this.obligations = obligations;
        return this;
    }

    public RezmaEntity addObligation(Obligation obligation) {
        this.obligations.add(obligation);
        obligation.getRezmaEntities().add(this);
        return this;
    }

    public RezmaEntity removeObligation(Obligation obligation) {
        this.obligations.remove(obligation);
        obligation.getRezmaEntities().remove(this);
        return this;
    }

    public void setObligations(Set<Obligation> obligations) {
        this.obligations = obligations;
    }

    public Set<Inspection> getInspections() {
        return inspections;
    }

    public RezmaEntity inspections(Set<Inspection> inspections) {
        this.inspections = inspections;
        return this;
    }

    public RezmaEntity addInspection(Inspection inspection) {
        this.inspections.add(inspection);
        inspection.getRezmaEntities().add(this);
        return this;
    }

    public RezmaEntity removeInspection(Inspection inspection) {
        this.inspections.remove(inspection);
        inspection.getRezmaEntities().remove(this);
        return this;
    }

    public void setInspections(Set<Inspection> inspections) {
        this.inspections = inspections;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RezmaEntity)) {
            return false;
        }
        return id != null && id.equals(((RezmaEntity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RezmaEntity{" +
            "id=" + getId() +
            ", entName='" + getEntName() + "'" +
            ", entAddress='" + getEntAddress() + "'" +
            ", entSystemDate='" + getEntSystemDate() + "'" +
            ", entCreatedBy='" + getEntCreatedBy() + "'" +
            ", entIdentifier='" + getEntIdentifier() + "'" +
            ", entType='" + getEntType() + "'" +
            ", entCountry='" + getEntCountry() + "'" +
            ", entIdentifierType='" + getEntIdentifierType() + "'" +
            ", entTin='" + getEntTin() + "'" +
            ", entDOB='" + getEntDOB() + "'" +
            "}";
    }
}
