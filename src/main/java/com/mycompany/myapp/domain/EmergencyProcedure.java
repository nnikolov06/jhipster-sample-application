package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A EmergencyProcedure.
 */
@Entity
@Table(name = "emergency_procedure")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmergencyProcedure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "emp_system")
    private String empSystem;

    @Column(name = "emp_reason")
    private String empReason;

    @Column(name = "emp_user")
    private String empUser;

    @Column(name = "emp_is_active")
    private Boolean empIsActive;

    @Column(name = "emp_date_start")
    private Instant empDateStart;

    @Column(name = "emp_date_end")
    private Instant empDateEnd;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpSystem() {
        return empSystem;
    }

    public EmergencyProcedure empSystem(String empSystem) {
        this.empSystem = empSystem;
        return this;
    }

    public void setEmpSystem(String empSystem) {
        this.empSystem = empSystem;
    }

    public String getEmpReason() {
        return empReason;
    }

    public EmergencyProcedure empReason(String empReason) {
        this.empReason = empReason;
        return this;
    }

    public void setEmpReason(String empReason) {
        this.empReason = empReason;
    }

    public String getEmpUser() {
        return empUser;
    }

    public EmergencyProcedure empUser(String empUser) {
        this.empUser = empUser;
        return this;
    }

    public void setEmpUser(String empUser) {
        this.empUser = empUser;
    }

    public Boolean isEmpIsActive() {
        return empIsActive;
    }

    public EmergencyProcedure empIsActive(Boolean empIsActive) {
        this.empIsActive = empIsActive;
        return this;
    }

    public void setEmpIsActive(Boolean empIsActive) {
        this.empIsActive = empIsActive;
    }

    public Instant getEmpDateStart() {
        return empDateStart;
    }

    public EmergencyProcedure empDateStart(Instant empDateStart) {
        this.empDateStart = empDateStart;
        return this;
    }

    public void setEmpDateStart(Instant empDateStart) {
        this.empDateStart = empDateStart;
    }

    public Instant getEmpDateEnd() {
        return empDateEnd;
    }

    public EmergencyProcedure empDateEnd(Instant empDateEnd) {
        this.empDateEnd = empDateEnd;
        return this;
    }

    public void setEmpDateEnd(Instant empDateEnd) {
        this.empDateEnd = empDateEnd;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmergencyProcedure)) {
            return false;
        }
        return id != null && id.equals(((EmergencyProcedure) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmergencyProcedure{" +
            "id=" + getId() +
            ", empSystem='" + getEmpSystem() + "'" +
            ", empReason='" + getEmpReason() + "'" +
            ", empUser='" + getEmpUser() + "'" +
            ", empIsActive='" + isEmpIsActive() + "'" +
            ", empDateStart='" + getEmpDateStart() + "'" +
            ", empDateEnd='" + getEmpDateEnd() + "'" +
            "}";
    }
}
