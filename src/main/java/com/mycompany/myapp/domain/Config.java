package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Config.
 */
@Entity
@Table(name = "config")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cfg_section")
    private String cfgSection;

    @Column(name = "cfg_environmnent")
    private String cfgEnvironmnent;

    @Column(name = "cfg_item")
    private String cfgItem;

    @Column(name = "cfg_value")
    private String cfgValue;

    @ManyToOne
    @JsonIgnoreProperties(value = "configs", allowSetters = true)
    private ModuleConfiguration moduleConfiguration;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCfgSection() {
        return cfgSection;
    }

    public Config cfgSection(String cfgSection) {
        this.cfgSection = cfgSection;
        return this;
    }

    public void setCfgSection(String cfgSection) {
        this.cfgSection = cfgSection;
    }

    public String getCfgEnvironmnent() {
        return cfgEnvironmnent;
    }

    public Config cfgEnvironmnent(String cfgEnvironmnent) {
        this.cfgEnvironmnent = cfgEnvironmnent;
        return this;
    }

    public void setCfgEnvironmnent(String cfgEnvironmnent) {
        this.cfgEnvironmnent = cfgEnvironmnent;
    }

    public String getCfgItem() {
        return cfgItem;
    }

    public Config cfgItem(String cfgItem) {
        this.cfgItem = cfgItem;
        return this;
    }

    public void setCfgItem(String cfgItem) {
        this.cfgItem = cfgItem;
    }

    public String getCfgValue() {
        return cfgValue;
    }

    public Config cfgValue(String cfgValue) {
        this.cfgValue = cfgValue;
        return this;
    }

    public void setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue;
    }

    public ModuleConfiguration getModuleConfiguration() {
        return moduleConfiguration;
    }

    public Config moduleConfiguration(ModuleConfiguration moduleConfiguration) {
        this.moduleConfiguration = moduleConfiguration;
        return this;
    }

    public void setModuleConfiguration(ModuleConfiguration moduleConfiguration) {
        this.moduleConfiguration = moduleConfiguration;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Config)) {
            return false;
        }
        return id != null && id.equals(((Config) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Config{" +
            "id=" + getId() +
            ", cfgSection='" + getCfgSection() + "'" +
            ", cfgEnvironmnent='" + getCfgEnvironmnent() + "'" +
            ", cfgItem='" + getCfgItem() + "'" +
            ", cfgValue='" + getCfgValue() + "'" +
            "}";
    }
}
