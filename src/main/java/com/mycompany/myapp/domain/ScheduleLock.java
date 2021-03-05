package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A ScheduleLock.
 */
@Entity
@Table(name = "schedule_lock")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ScheduleLock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "slc_name")
    private String slcName;

    @Column(name = "slc_lock_until")
    private Instant slcLockUntil;

    @Column(name = "slc_locked_at")
    private Instant slcLockedAt;

    @Column(name = "slc_locked_by")
    private String slcLockedBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlcName() {
        return slcName;
    }

    public ScheduleLock slcName(String slcName) {
        this.slcName = slcName;
        return this;
    }

    public void setSlcName(String slcName) {
        this.slcName = slcName;
    }

    public Instant getSlcLockUntil() {
        return slcLockUntil;
    }

    public ScheduleLock slcLockUntil(Instant slcLockUntil) {
        this.slcLockUntil = slcLockUntil;
        return this;
    }

    public void setSlcLockUntil(Instant slcLockUntil) {
        this.slcLockUntil = slcLockUntil;
    }

    public Instant getSlcLockedAt() {
        return slcLockedAt;
    }

    public ScheduleLock slcLockedAt(Instant slcLockedAt) {
        this.slcLockedAt = slcLockedAt;
        return this;
    }

    public void setSlcLockedAt(Instant slcLockedAt) {
        this.slcLockedAt = slcLockedAt;
    }

    public String getSlcLockedBy() {
        return slcLockedBy;
    }

    public ScheduleLock slcLockedBy(String slcLockedBy) {
        this.slcLockedBy = slcLockedBy;
        return this;
    }

    public void setSlcLockedBy(String slcLockedBy) {
        this.slcLockedBy = slcLockedBy;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScheduleLock)) {
            return false;
        }
        return id != null && id.equals(((ScheduleLock) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ScheduleLock{" +
            "id=" + getId() +
            ", slcName='" + getSlcName() + "'" +
            ", slcLockUntil='" + getSlcLockUntil() + "'" +
            ", slcLockedAt='" + getSlcLockedAt() + "'" +
            ", slcLockedBy='" + getSlcLockedBy() + "'" +
            "}";
    }
}
