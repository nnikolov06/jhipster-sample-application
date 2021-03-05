package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Inspection.
 */
@Entity
@Table(name = "inspection")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Inspection implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ins_number")
    private String insNumber;

    @Column(name = "ins_date")
    private Instant insDate;

    @Column(name = "ins_customs_office")
    private String insCustomsOffice;

    @Column(name = "ins_user")
    private String insUser;

    @Column(name = "ins_sys_date")
    private Instant insSysDate;

    @Column(name = "ins_type")
    private String insType;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "inspection_rezma_entity",
               joinColumns = @JoinColumn(name = "inspection_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "rezma_entity_id", referencedColumnName = "id"))
    private Set<RezmaEntity> rezmaEntities = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsNumber() {
        return insNumber;
    }

    public Inspection insNumber(String insNumber) {
        this.insNumber = insNumber;
        return this;
    }

    public void setInsNumber(String insNumber) {
        this.insNumber = insNumber;
    }

    public Instant getInsDate() {
        return insDate;
    }

    public Inspection insDate(Instant insDate) {
        this.insDate = insDate;
        return this;
    }

    public void setInsDate(Instant insDate) {
        this.insDate = insDate;
    }

    public String getInsCustomsOffice() {
        return insCustomsOffice;
    }

    public Inspection insCustomsOffice(String insCustomsOffice) {
        this.insCustomsOffice = insCustomsOffice;
        return this;
    }

    public void setInsCustomsOffice(String insCustomsOffice) {
        this.insCustomsOffice = insCustomsOffice;
    }

    public String getInsUser() {
        return insUser;
    }

    public Inspection insUser(String insUser) {
        this.insUser = insUser;
        return this;
    }

    public void setInsUser(String insUser) {
        this.insUser = insUser;
    }

    public Instant getInsSysDate() {
        return insSysDate;
    }

    public Inspection insSysDate(Instant insSysDate) {
        this.insSysDate = insSysDate;
        return this;
    }

    public void setInsSysDate(Instant insSysDate) {
        this.insSysDate = insSysDate;
    }

    public String getInsType() {
        return insType;
    }

    public Inspection insType(String insType) {
        this.insType = insType;
        return this;
    }

    public void setInsType(String insType) {
        this.insType = insType;
    }

    public Set<RezmaEntity> getRezmaEntities() {
        return rezmaEntities;
    }

    public Inspection rezmaEntities(Set<RezmaEntity> rezmaEntities) {
        this.rezmaEntities = rezmaEntities;
        return this;
    }

    public Inspection addRezmaEntity(RezmaEntity rezmaEntity) {
        this.rezmaEntities.add(rezmaEntity);
        rezmaEntity.getInspections().add(this);
        return this;
    }

    public Inspection removeRezmaEntity(RezmaEntity rezmaEntity) {
        this.rezmaEntities.remove(rezmaEntity);
        rezmaEntity.getInspections().remove(this);
        return this;
    }

    public void setRezmaEntities(Set<RezmaEntity> rezmaEntities) {
        this.rezmaEntities = rezmaEntities;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inspection)) {
            return false;
        }
        return id != null && id.equals(((Inspection) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Inspection{" +
            "id=" + getId() +
            ", insNumber='" + getInsNumber() + "'" +
            ", insDate='" + getInsDate() + "'" +
            ", insCustomsOffice='" + getInsCustomsOffice() + "'" +
            ", insUser='" + getInsUser() + "'" +
            ", insSysDate='" + getInsSysDate() + "'" +
            ", insType='" + getInsType() + "'" +
            "}";
    }
}
