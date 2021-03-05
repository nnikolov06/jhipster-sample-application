package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "trn_number")
    private String trnNumber;

    @Column(name = "trn_date")
    private Instant trnDate;

    @Column(name = "trn_system")
    private String trnSystem;

    @Column(name = "trn_amount", precision = 21, scale = 2)
    private BigDecimal trnAmount;

    @Column(name = "trn_creation_date")
    private Instant trnCreationDate;

    @Column(name = "trn_sys_date")
    private Instant trnSysDate;

    @Column(name = "trn_created_by")
    private String trnCreatedBy;

    @Column(name = "trn_type")
    private String trnType;

    @Column(name = "trn_code")
    private String trnCode;

    @Column(name = "trn_status")
    private String trnStatus;

    @Column(name = "trn_description")
    private String trnDescription;

    @Column(name = "trn_payment_type")
    private String trnPaymentType;

    @ManyToOne
    @JsonIgnoreProperties(value = "transactions", allowSetters = true)
    private Obligation obligation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrnNumber() {
        return trnNumber;
    }

    public Transaction trnNumber(String trnNumber) {
        this.trnNumber = trnNumber;
        return this;
    }

    public void setTrnNumber(String trnNumber) {
        this.trnNumber = trnNumber;
    }

    public Instant getTrnDate() {
        return trnDate;
    }

    public Transaction trnDate(Instant trnDate) {
        this.trnDate = trnDate;
        return this;
    }

    public void setTrnDate(Instant trnDate) {
        this.trnDate = trnDate;
    }

    public String getTrnSystem() {
        return trnSystem;
    }

    public Transaction trnSystem(String trnSystem) {
        this.trnSystem = trnSystem;
        return this;
    }

    public void setTrnSystem(String trnSystem) {
        this.trnSystem = trnSystem;
    }

    public BigDecimal getTrnAmount() {
        return trnAmount;
    }

    public Transaction trnAmount(BigDecimal trnAmount) {
        this.trnAmount = trnAmount;
        return this;
    }

    public void setTrnAmount(BigDecimal trnAmount) {
        this.trnAmount = trnAmount;
    }

    public Instant getTrnCreationDate() {
        return trnCreationDate;
    }

    public Transaction trnCreationDate(Instant trnCreationDate) {
        this.trnCreationDate = trnCreationDate;
        return this;
    }

    public void setTrnCreationDate(Instant trnCreationDate) {
        this.trnCreationDate = trnCreationDate;
    }

    public Instant getTrnSysDate() {
        return trnSysDate;
    }

    public Transaction trnSysDate(Instant trnSysDate) {
        this.trnSysDate = trnSysDate;
        return this;
    }

    public void setTrnSysDate(Instant trnSysDate) {
        this.trnSysDate = trnSysDate;
    }

    public String getTrnCreatedBy() {
        return trnCreatedBy;
    }

    public Transaction trnCreatedBy(String trnCreatedBy) {
        this.trnCreatedBy = trnCreatedBy;
        return this;
    }

    public void setTrnCreatedBy(String trnCreatedBy) {
        this.trnCreatedBy = trnCreatedBy;
    }

    public String getTrnType() {
        return trnType;
    }

    public Transaction trnType(String trnType) {
        this.trnType = trnType;
        return this;
    }

    public void setTrnType(String trnType) {
        this.trnType = trnType;
    }

    public String getTrnCode() {
        return trnCode;
    }

    public Transaction trnCode(String trnCode) {
        this.trnCode = trnCode;
        return this;
    }

    public void setTrnCode(String trnCode) {
        this.trnCode = trnCode;
    }

    public String getTrnStatus() {
        return trnStatus;
    }

    public Transaction trnStatus(String trnStatus) {
        this.trnStatus = trnStatus;
        return this;
    }

    public void setTrnStatus(String trnStatus) {
        this.trnStatus = trnStatus;
    }

    public String getTrnDescription() {
        return trnDescription;
    }

    public Transaction trnDescription(String trnDescription) {
        this.trnDescription = trnDescription;
        return this;
    }

    public void setTrnDescription(String trnDescription) {
        this.trnDescription = trnDescription;
    }

    public String getTrnPaymentType() {
        return trnPaymentType;
    }

    public Transaction trnPaymentType(String trnPaymentType) {
        this.trnPaymentType = trnPaymentType;
        return this;
    }

    public void setTrnPaymentType(String trnPaymentType) {
        this.trnPaymentType = trnPaymentType;
    }

    public Obligation getObligation() {
        return obligation;
    }

    public Transaction obligation(Obligation obligation) {
        this.obligation = obligation;
        return this;
    }

    public void setObligation(Obligation obligation) {
        this.obligation = obligation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        return id != null && id.equals(((Transaction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transaction{" +
            "id=" + getId() +
            ", trnNumber='" + getTrnNumber() + "'" +
            ", trnDate='" + getTrnDate() + "'" +
            ", trnSystem='" + getTrnSystem() + "'" +
            ", trnAmount=" + getTrnAmount() +
            ", trnCreationDate='" + getTrnCreationDate() + "'" +
            ", trnSysDate='" + getTrnSysDate() + "'" +
            ", trnCreatedBy='" + getTrnCreatedBy() + "'" +
            ", trnType='" + getTrnType() + "'" +
            ", trnCode='" + getTrnCode() + "'" +
            ", trnStatus='" + getTrnStatus() + "'" +
            ", trnDescription='" + getTrnDescription() + "'" +
            ", trnPaymentType='" + getTrnPaymentType() + "'" +
            "}";
    }
}
