package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Obligation.
 */
@Entity
@Table(name = "obligation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Obligation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "obl_sys_date")
    private Instant oblSysDate;

    @Column(name = "obl_created_by")
    private String oblCreatedBy;

    @Column(name = "obl_customs_office")
    private String oblCustomsOffice;

    @Column(name = "obl_type")
    private String oblType;

    @Column(name = "obl_amount", precision = 21, scale = 2)
    private BigDecimal oblAmount;

    @Column(name = "obl_lapsed")
    private Boolean oblLapsed;

    @Column(name = "obl_vpo_number")
    private String oblVpoNumber;

    @Column(name = "obl_transaction_number")
    private String oblTransactionNumber;

    @Column(name = "obl_mra_date")
    private Instant oblMraDate;

    @Column(name = "obl_state")
    private String oblState;

    @Column(name = "obl_identifier")
    private String oblIdentifier;

    @Column(name = "obl_amount_difference", precision = 21, scale = 2)
    private BigDecimal oblAmountDifference;

    @Column(name = "obl_guaranty_difference", precision = 21, scale = 2)
    private BigDecimal oblGuarantyDifference;

    @Column(name = "obl_customs_office_name")
    private String oblCustomsOfficeName;

    @ManyToOne
    @JsonIgnoreProperties(value = "obligations", allowSetters = true)
    private Document document;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "obligation_rezma_entity",
               joinColumns = @JoinColumn(name = "obligation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "rezma_entity_id", referencedColumnName = "id"))
    private Set<RezmaEntity> rezmaEntities = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getOblSysDate() {
        return oblSysDate;
    }

    public Obligation oblSysDate(Instant oblSysDate) {
        this.oblSysDate = oblSysDate;
        return this;
    }

    public void setOblSysDate(Instant oblSysDate) {
        this.oblSysDate = oblSysDate;
    }

    public String getOblCreatedBy() {
        return oblCreatedBy;
    }

    public Obligation oblCreatedBy(String oblCreatedBy) {
        this.oblCreatedBy = oblCreatedBy;
        return this;
    }

    public void setOblCreatedBy(String oblCreatedBy) {
        this.oblCreatedBy = oblCreatedBy;
    }

    public String getOblCustomsOffice() {
        return oblCustomsOffice;
    }

    public Obligation oblCustomsOffice(String oblCustomsOffice) {
        this.oblCustomsOffice = oblCustomsOffice;
        return this;
    }

    public void setOblCustomsOffice(String oblCustomsOffice) {
        this.oblCustomsOffice = oblCustomsOffice;
    }

    public String getOblType() {
        return oblType;
    }

    public Obligation oblType(String oblType) {
        this.oblType = oblType;
        return this;
    }

    public void setOblType(String oblType) {
        this.oblType = oblType;
    }

    public BigDecimal getOblAmount() {
        return oblAmount;
    }

    public Obligation oblAmount(BigDecimal oblAmount) {
        this.oblAmount = oblAmount;
        return this;
    }

    public void setOblAmount(BigDecimal oblAmount) {
        this.oblAmount = oblAmount;
    }

    public Boolean isOblLapsed() {
        return oblLapsed;
    }

    public Obligation oblLapsed(Boolean oblLapsed) {
        this.oblLapsed = oblLapsed;
        return this;
    }

    public void setOblLapsed(Boolean oblLapsed) {
        this.oblLapsed = oblLapsed;
    }

    public String getOblVpoNumber() {
        return oblVpoNumber;
    }

    public Obligation oblVpoNumber(String oblVpoNumber) {
        this.oblVpoNumber = oblVpoNumber;
        return this;
    }

    public void setOblVpoNumber(String oblVpoNumber) {
        this.oblVpoNumber = oblVpoNumber;
    }

    public String getOblTransactionNumber() {
        return oblTransactionNumber;
    }

    public Obligation oblTransactionNumber(String oblTransactionNumber) {
        this.oblTransactionNumber = oblTransactionNumber;
        return this;
    }

    public void setOblTransactionNumber(String oblTransactionNumber) {
        this.oblTransactionNumber = oblTransactionNumber;
    }

    public Instant getOblMraDate() {
        return oblMraDate;
    }

    public Obligation oblMraDate(Instant oblMraDate) {
        this.oblMraDate = oblMraDate;
        return this;
    }

    public void setOblMraDate(Instant oblMraDate) {
        this.oblMraDate = oblMraDate;
    }

    public String getOblState() {
        return oblState;
    }

    public Obligation oblState(String oblState) {
        this.oblState = oblState;
        return this;
    }

    public void setOblState(String oblState) {
        this.oblState = oblState;
    }

    public String getOblIdentifier() {
        return oblIdentifier;
    }

    public Obligation oblIdentifier(String oblIdentifier) {
        this.oblIdentifier = oblIdentifier;
        return this;
    }

    public void setOblIdentifier(String oblIdentifier) {
        this.oblIdentifier = oblIdentifier;
    }

    public BigDecimal getOblAmountDifference() {
        return oblAmountDifference;
    }

    public Obligation oblAmountDifference(BigDecimal oblAmountDifference) {
        this.oblAmountDifference = oblAmountDifference;
        return this;
    }

    public void setOblAmountDifference(BigDecimal oblAmountDifference) {
        this.oblAmountDifference = oblAmountDifference;
    }

    public BigDecimal getOblGuarantyDifference() {
        return oblGuarantyDifference;
    }

    public Obligation oblGuarantyDifference(BigDecimal oblGuarantyDifference) {
        this.oblGuarantyDifference = oblGuarantyDifference;
        return this;
    }

    public void setOblGuarantyDifference(BigDecimal oblGuarantyDifference) {
        this.oblGuarantyDifference = oblGuarantyDifference;
    }

    public String getOblCustomsOfficeName() {
        return oblCustomsOfficeName;
    }

    public Obligation oblCustomsOfficeName(String oblCustomsOfficeName) {
        this.oblCustomsOfficeName = oblCustomsOfficeName;
        return this;
    }

    public void setOblCustomsOfficeName(String oblCustomsOfficeName) {
        this.oblCustomsOfficeName = oblCustomsOfficeName;
    }

    public Document getDocument() {
        return document;
    }

    public Obligation document(Document document) {
        this.document = document;
        return this;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Set<RezmaEntity> getRezmaEntities() {
        return rezmaEntities;
    }

    public Obligation rezmaEntities(Set<RezmaEntity> rezmaEntities) {
        this.rezmaEntities = rezmaEntities;
        return this;
    }

    public Obligation addRezmaEntity(RezmaEntity rezmaEntity) {
        this.rezmaEntities.add(rezmaEntity);
        rezmaEntity.getObligations().add(this);
        return this;
    }

    public Obligation removeRezmaEntity(RezmaEntity rezmaEntity) {
        this.rezmaEntities.remove(rezmaEntity);
        rezmaEntity.getObligations().remove(this);
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
        if (!(o instanceof Obligation)) {
            return false;
        }
        return id != null && id.equals(((Obligation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Obligation{" +
            "id=" + getId() +
            ", oblSysDate='" + getOblSysDate() + "'" +
            ", oblCreatedBy='" + getOblCreatedBy() + "'" +
            ", oblCustomsOffice='" + getOblCustomsOffice() + "'" +
            ", oblType='" + getOblType() + "'" +
            ", oblAmount=" + getOblAmount() +
            ", oblLapsed='" + isOblLapsed() + "'" +
            ", oblVpoNumber='" + getOblVpoNumber() + "'" +
            ", oblTransactionNumber='" + getOblTransactionNumber() + "'" +
            ", oblMraDate='" + getOblMraDate() + "'" +
            ", oblState='" + getOblState() + "'" +
            ", oblIdentifier='" + getOblIdentifier() + "'" +
            ", oblAmountDifference=" + getOblAmountDifference() +
            ", oblGuarantyDifference=" + getOblGuarantyDifference() +
            ", oblCustomsOfficeName='" + getOblCustomsOfficeName() + "'" +
            "}";
    }
}
