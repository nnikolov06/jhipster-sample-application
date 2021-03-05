package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A Document.
 */
@Entity
@Table(name = "document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "doc_number")
    private String docNumber;

    @Column(name = "doc_date")
    private LocalDate docDate;

    @Column(name = "doc_description")
    private String docDescription;

    @Column(name = "doc_type")
    private String docType;

    @Column(name = "doc_sys_date")
    private Instant docSysDate;

    @Column(name = "doc_created_by")
    private String docCreatedBy;

    @Column(name = "doc_mrn")
    private String docMrn;

    @Column(name = "doc_status")
    private String docStatus;

    @Column(name = "doc_last_change_date")
    private Instant docLastChangeDate;

    @Column(name = "doc_into_force")
    private Boolean docIntoForce;

    @Column(name = "doc_deferred")
    private Boolean docDeferred;

    @Column(name = "doc_rescheduled")
    private Boolean docRescheduled;

    @Column(name = "doc_into_force_date")
    private LocalDate docIntoForceDate;

    @Column(name = "doc_deferred_date")
    private LocalDate docDeferredDate;

    @Column(name = "doc_rescheduled_date")
    private LocalDate docRescheduledDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public Document docNumber(String docNumber) {
        this.docNumber = docNumber;
        return this;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public LocalDate getDocDate() {
        return docDate;
    }

    public Document docDate(LocalDate docDate) {
        this.docDate = docDate;
        return this;
    }

    public void setDocDate(LocalDate docDate) {
        this.docDate = docDate;
    }

    public String getDocDescription() {
        return docDescription;
    }

    public Document docDescription(String docDescription) {
        this.docDescription = docDescription;
        return this;
    }

    public void setDocDescription(String docDescription) {
        this.docDescription = docDescription;
    }

    public String getDocType() {
        return docType;
    }

    public Document docType(String docType) {
        this.docType = docType;
        return this;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public Instant getDocSysDate() {
        return docSysDate;
    }

    public Document docSysDate(Instant docSysDate) {
        this.docSysDate = docSysDate;
        return this;
    }

    public void setDocSysDate(Instant docSysDate) {
        this.docSysDate = docSysDate;
    }

    public String getDocCreatedBy() {
        return docCreatedBy;
    }

    public Document docCreatedBy(String docCreatedBy) {
        this.docCreatedBy = docCreatedBy;
        return this;
    }

    public void setDocCreatedBy(String docCreatedBy) {
        this.docCreatedBy = docCreatedBy;
    }

    public String getDocMrn() {
        return docMrn;
    }

    public Document docMrn(String docMrn) {
        this.docMrn = docMrn;
        return this;
    }

    public void setDocMrn(String docMrn) {
        this.docMrn = docMrn;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public Document docStatus(String docStatus) {
        this.docStatus = docStatus;
        return this;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public Instant getDocLastChangeDate() {
        return docLastChangeDate;
    }

    public Document docLastChangeDate(Instant docLastChangeDate) {
        this.docLastChangeDate = docLastChangeDate;
        return this;
    }

    public void setDocLastChangeDate(Instant docLastChangeDate) {
        this.docLastChangeDate = docLastChangeDate;
    }

    public Boolean isDocIntoForce() {
        return docIntoForce;
    }

    public Document docIntoForce(Boolean docIntoForce) {
        this.docIntoForce = docIntoForce;
        return this;
    }

    public void setDocIntoForce(Boolean docIntoForce) {
        this.docIntoForce = docIntoForce;
    }

    public Boolean isDocDeferred() {
        return docDeferred;
    }

    public Document docDeferred(Boolean docDeferred) {
        this.docDeferred = docDeferred;
        return this;
    }

    public void setDocDeferred(Boolean docDeferred) {
        this.docDeferred = docDeferred;
    }

    public Boolean isDocRescheduled() {
        return docRescheduled;
    }

    public Document docRescheduled(Boolean docRescheduled) {
        this.docRescheduled = docRescheduled;
        return this;
    }

    public void setDocRescheduled(Boolean docRescheduled) {
        this.docRescheduled = docRescheduled;
    }

    public LocalDate getDocIntoForceDate() {
        return docIntoForceDate;
    }

    public Document docIntoForceDate(LocalDate docIntoForceDate) {
        this.docIntoForceDate = docIntoForceDate;
        return this;
    }

    public void setDocIntoForceDate(LocalDate docIntoForceDate) {
        this.docIntoForceDate = docIntoForceDate;
    }

    public LocalDate getDocDeferredDate() {
        return docDeferredDate;
    }

    public Document docDeferredDate(LocalDate docDeferredDate) {
        this.docDeferredDate = docDeferredDate;
        return this;
    }

    public void setDocDeferredDate(LocalDate docDeferredDate) {
        this.docDeferredDate = docDeferredDate;
    }

    public LocalDate getDocRescheduledDate() {
        return docRescheduledDate;
    }

    public Document docRescheduledDate(LocalDate docRescheduledDate) {
        this.docRescheduledDate = docRescheduledDate;
        return this;
    }

    public void setDocRescheduledDate(LocalDate docRescheduledDate) {
        this.docRescheduledDate = docRescheduledDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Document)) {
            return false;
        }
        return id != null && id.equals(((Document) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Document{" +
            "id=" + getId() +
            ", docNumber='" + getDocNumber() + "'" +
            ", docDate='" + getDocDate() + "'" +
            ", docDescription='" + getDocDescription() + "'" +
            ", docType='" + getDocType() + "'" +
            ", docSysDate='" + getDocSysDate() + "'" +
            ", docCreatedBy='" + getDocCreatedBy() + "'" +
            ", docMrn='" + getDocMrn() + "'" +
            ", docStatus='" + getDocStatus() + "'" +
            ", docLastChangeDate='" + getDocLastChangeDate() + "'" +
            ", docIntoForce='" + isDocIntoForce() + "'" +
            ", docDeferred='" + isDocDeferred() + "'" +
            ", docRescheduled='" + isDocRescheduled() + "'" +
            ", docIntoForceDate='" + getDocIntoForceDate() + "'" +
            ", docDeferredDate='" + getDocDeferredDate() + "'" +
            ", docRescheduledDate='" + getDocRescheduledDate() + "'" +
            "}";
    }
}
