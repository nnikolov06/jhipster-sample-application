package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ModuleConfiguration;
import com.mycompany.myapp.repository.ModuleConfigurationRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.ModuleConfiguration}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ModuleConfigurationResource {

    private final Logger log = LoggerFactory.getLogger(ModuleConfigurationResource.class);

    private static final String ENTITY_NAME = "moduleConfiguration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModuleConfigurationRepository moduleConfigurationRepository;

    public ModuleConfigurationResource(ModuleConfigurationRepository moduleConfigurationRepository) {
        this.moduleConfigurationRepository = moduleConfigurationRepository;
    }

    /**
     * {@code POST  /module-configurations} : Create a new moduleConfiguration.
     *
     * @param moduleConfiguration the moduleConfiguration to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new moduleConfiguration, or with status {@code 400 (Bad Request)} if the moduleConfiguration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/module-configurations")
    public ResponseEntity<ModuleConfiguration> createModuleConfiguration(@RequestBody ModuleConfiguration moduleConfiguration) throws URISyntaxException {
        log.debug("REST request to save ModuleConfiguration : {}", moduleConfiguration);
        if (moduleConfiguration.getId() != null) {
            throw new BadRequestAlertException("A new moduleConfiguration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ModuleConfiguration result = moduleConfigurationRepository.save(moduleConfiguration);
        return ResponseEntity.created(new URI("/api/module-configurations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /module-configurations} : Updates an existing moduleConfiguration.
     *
     * @param moduleConfiguration the moduleConfiguration to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated moduleConfiguration,
     * or with status {@code 400 (Bad Request)} if the moduleConfiguration is not valid,
     * or with status {@code 500 (Internal Server Error)} if the moduleConfiguration couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/module-configurations")
    public ResponseEntity<ModuleConfiguration> updateModuleConfiguration(@RequestBody ModuleConfiguration moduleConfiguration) throws URISyntaxException {
        log.debug("REST request to update ModuleConfiguration : {}", moduleConfiguration);
        if (moduleConfiguration.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ModuleConfiguration result = moduleConfigurationRepository.save(moduleConfiguration);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, moduleConfiguration.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /module-configurations} : get all the moduleConfigurations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of moduleConfigurations in body.
     */
    @GetMapping("/module-configurations")
    public List<ModuleConfiguration> getAllModuleConfigurations() {
        log.debug("REST request to get all ModuleConfigurations");
        return moduleConfigurationRepository.findAll();
    }

    /**
     * {@code GET  /module-configurations/:id} : get the "id" moduleConfiguration.
     *
     * @param id the id of the moduleConfiguration to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the moduleConfiguration, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/module-configurations/{id}")
    public ResponseEntity<ModuleConfiguration> getModuleConfiguration(@PathVariable Long id) {
        log.debug("REST request to get ModuleConfiguration : {}", id);
        Optional<ModuleConfiguration> moduleConfiguration = moduleConfigurationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(moduleConfiguration);
    }

    /**
     * {@code DELETE  /module-configurations/:id} : delete the "id" moduleConfiguration.
     *
     * @param id the id of the moduleConfiguration to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/module-configurations/{id}")
    public ResponseEntity<Void> deleteModuleConfiguration(@PathVariable Long id) {
        log.debug("REST request to delete ModuleConfiguration : {}", id);
        moduleConfigurationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
