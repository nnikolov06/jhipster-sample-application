package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A Certificate.
 */
@Entity
@Table(name = "certificate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Certificate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cer_number")
    private String cerNumber;

    @Column(name = "cer_date")
    private String cerDate;

    @Column(name = "cer_sys_date")
    private Instant cerSysDate;

    @Column(name = "cer_created_by")
    private String cerCreatedBy;

    @Column(name = "cer_file_name")
    private String cerFileName;

    @Column(name = "cer_service_applicant")
    private String cerServiceApplicant;

    @Column(name = "cer_signing_person")
    private String cerSigningPerson;

    @Column(name = "cer_signing_person_position")
    private String cerSigningPersonPosition;

    @Column(name = "cer_certificate_type")
    private String cerCertificateType;

    @Column(name = "cer_check_box_values")
    private String cerCheckBoxValues;

    @Column(name = "cer_request_number")
    private String cerRequestNumber;

    @Column(name = "cer_request_date")
    private LocalDate cerRequestDate;

    @Column(name = "cer_is_deactivated")
    private Boolean cerIsDeactivated;

    @Column(name = "cer_state")
    private String cerState;

    @Column(name = "cer_has_obligation")
    private Boolean cerHasObligation;

    @Column(name = "cer_document_state")
    private String cerDocumentState;

    @ManyToOne
    @JsonIgnoreProperties(value = "certificates", allowSetters = true)
    private RezmaEntity rezmaEntity;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCerNumber() {
        return cerNumber;
    }

    public Certificate cerNumber(String cerNumber) {
        this.cerNumber = cerNumber;
        return this;
    }

    public void setCerNumber(String cerNumber) {
        this.cerNumber = cerNumber;
    }

    public String getCerDate() {
        return cerDate;
    }

    public Certificate cerDate(String cerDate) {
        this.cerDate = cerDate;
        return this;
    }

    public void setCerDate(String cerDate) {
        this.cerDate = cerDate;
    }

    public Instant getCerSysDate() {
        return cerSysDate;
    }

    public Certificate cerSysDate(Instant cerSysDate) {
        this.cerSysDate = cerSysDate;
        return this;
    }

    public void setCerSysDate(Instant cerSysDate) {
        this.cerSysDate = cerSysDate;
    }

    public String getCerCreatedBy() {
        return cerCreatedBy;
    }

    public Certificate cerCreatedBy(String cerCreatedBy) {
        this.cerCreatedBy = cerCreatedBy;
        return this;
    }

    public void setCerCreatedBy(String cerCreatedBy) {
        this.cerCreatedBy = cerCreatedBy;
    }

    public String getCerFileName() {
        return cerFileName;
    }

    public Certificate cerFileName(String cerFileName) {
        this.cerFileName = cerFileName;
        return this;
    }

    public void setCerFileName(String cerFileName) {
        this.cerFileName = cerFileName;
    }

    public String getCerServiceApplicant() {
        return cerServiceApplicant;
    }

    public Certificate cerServiceApplicant(String cerServiceApplicant) {
        this.cerServiceApplicant = cerServiceApplicant;
        return this;
    }

    public void setCerServiceApplicant(String cerServiceApplicant) {
        this.cerServiceApplicant = cerServiceApplicant;
    }

    public String getCerSigningPerson() {
        return cerSigningPerson;
    }

    public Certificate cerSigningPerson(String cerSigningPerson) {
        this.cerSigningPerson = cerSigningPerson;
        return this;
    }

    public void setCerSigningPerson(String cerSigningPerson) {
        this.cerSigningPerson = cerSigningPerson;
    }

    public String getCerSigningPersonPosition() {
        return cerSigningPersonPosition;
    }

    public Certificate cerSigningPersonPosition(String cerSigningPersonPosition) {
        this.cerSigningPersonPosition = cerSigningPersonPosition;
        return this;
    }

    public void setCerSigningPersonPosition(String cerSigningPersonPosition) {
        this.cerSigningPersonPosition = cerSigningPersonPosition;
    }

    public String getCerCertificateType() {
        return cerCertificateType;
    }

    public Certificate cerCertificateType(String cerCertificateType) {
        this.cerCertificateType = cerCertificateType;
        return this;
    }

    public void setCerCertificateType(String cerCertificateType) {
        this.cerCertificateType = cerCertificateType;
    }

    public String getCerCheckBoxValues() {
        return cerCheckBoxValues;
    }

    public Certificate cerCheckBoxValues(String cerCheckBoxValues) {
        this.cerCheckBoxValues = cerCheckBoxValues;
        return this;
    }

    public void setCerCheckBoxValues(String cerCheckBoxValues) {
        this.cerCheckBoxValues = cerCheckBoxValues;
    }

    public String getCerRequestNumber() {
        return cerRequestNumber;
    }

    public Certificate cerRequestNumber(String cerRequestNumber) {
        this.cerRequestNumber = cerRequestNumber;
        return this;
    }

    public void setCerRequestNumber(String cerRequestNumber) {
        this.cerRequestNumber = cerRequestNumber;
    }

    public LocalDate getCerRequestDate() {
        return cerRequestDate;
    }

    public Certificate cerRequestDate(LocalDate cerRequestDate) {
        this.cerRequestDate = cerRequestDate;
        return this;
    }

    public void setCerRequestDate(LocalDate cerRequestDate) {
        this.cerRequestDate = cerRequestDate;
    }

    public Boolean isCerIsDeactivated() {
        return cerIsDeactivated;
    }

    public Certificate cerIsDeactivated(Boolean cerIsDeactivated) {
        this.cerIsDeactivated = cerIsDeactivated;
        return this;
    }

    public void setCerIsDeactivated(Boolean cerIsDeactivated) {
        this.cerIsDeactivated = cerIsDeactivated;
    }

    public String getCerState() {
        return cerState;
    }

    public Certificate cerState(String cerState) {
        this.cerState = cerState;
        return this;
    }

    public void setCerState(String cerState) {
        this.cerState = cerState;
    }

    public Boolean isCerHasObligation() {
        return cerHasObligation;
    }

    public Certificate cerHasObligation(Boolean cerHasObligation) {
        this.cerHasObligation = cerHasObligation;
        return this;
    }

    public void setCerHasObligation(Boolean cerHasObligation) {
        this.cerHasObligation = cerHasObligation;
    }

    public String getCerDocumentState() {
        return cerDocumentState;
    }

    public Certificate cerDocumentState(String cerDocumentState) {
        this.cerDocumentState = cerDocumentState;
        return this;
    }

    public void setCerDocumentState(String cerDocumentState) {
        this.cerDocumentState = cerDocumentState;
    }

    public RezmaEntity getRezmaEntity() {
        return rezmaEntity;
    }

    public Certificate rezmaEntity(RezmaEntity rezmaEntity) {
        this.rezmaEntity = rezmaEntity;
        return this;
    }

    public void setRezmaEntity(RezmaEntity rezmaEntity) {
        this.rezmaEntity = rezmaEntity;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Certificate)) {
            return false;
        }
        return id != null && id.equals(((Certificate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Certificate{" +
            "id=" + getId() +
            ", cerNumber='" + getCerNumber() + "'" +
            ", cerDate='" + getCerDate() + "'" +
            ", cerSysDate='" + getCerSysDate() + "'" +
            ", cerCreatedBy='" + getCerCreatedBy() + "'" +
            ", cerFileName='" + getCerFileName() + "'" +
            ", cerServiceApplicant='" + getCerServiceApplicant() + "'" +
            ", cerSigningPerson='" + getCerSigningPerson() + "'" +
            ", cerSigningPersonPosition='" + getCerSigningPersonPosition() + "'" +
            ", cerCertificateType='" + getCerCertificateType() + "'" +
            ", cerCheckBoxValues='" + getCerCheckBoxValues() + "'" +
            ", cerRequestNumber='" + getCerRequestNumber() + "'" +
            ", cerRequestDate='" + getCerRequestDate() + "'" +
            ", cerIsDeactivated='" + isCerIsDeactivated() + "'" +
            ", cerState='" + getCerState() + "'" +
            ", cerHasObligation='" + isCerHasObligation() + "'" +
            ", cerDocumentState='" + getCerDocumentState() + "'" +
            "}";
    }
}
