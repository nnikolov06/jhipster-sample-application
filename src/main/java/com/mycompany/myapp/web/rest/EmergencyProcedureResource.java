package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.EmergencyProcedure;
import com.mycompany.myapp.repository.EmergencyProcedureRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.EmergencyProcedure}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EmergencyProcedureResource {

    private final Logger log = LoggerFactory.getLogger(EmergencyProcedureResource.class);

    private static final String ENTITY_NAME = "emergencyProcedure";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmergencyProcedureRepository emergencyProcedureRepository;

    public EmergencyProcedureResource(EmergencyProcedureRepository emergencyProcedureRepository) {
        this.emergencyProcedureRepository = emergencyProcedureRepository;
    }

    /**
     * {@code POST  /emergency-procedures} : Create a new emergencyProcedure.
     *
     * @param emergencyProcedure the emergencyProcedure to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new emergencyProcedure, or with status {@code 400 (Bad Request)} if the emergencyProcedure has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/emergency-procedures")
    public ResponseEntity<EmergencyProcedure> createEmergencyProcedure(@RequestBody EmergencyProcedure emergencyProcedure) throws URISyntaxException {
        log.debug("REST request to save EmergencyProcedure : {}", emergencyProcedure);
        if (emergencyProcedure.getId() != null) {
            throw new BadRequestAlertException("A new emergencyProcedure cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmergencyProcedure result = emergencyProcedureRepository.save(emergencyProcedure);
        return ResponseEntity.created(new URI("/api/emergency-procedures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /emergency-procedures} : Updates an existing emergencyProcedure.
     *
     * @param emergencyProcedure the emergencyProcedure to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated emergencyProcedure,
     * or with status {@code 400 (Bad Request)} if the emergencyProcedure is not valid,
     * or with status {@code 500 (Internal Server Error)} if the emergencyProcedure couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/emergency-procedures")
    public ResponseEntity<EmergencyProcedure> updateEmergencyProcedure(@RequestBody EmergencyProcedure emergencyProcedure) throws URISyntaxException {
        log.debug("REST request to update EmergencyProcedure : {}", emergencyProcedure);
        if (emergencyProcedure.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmergencyProcedure result = emergencyProcedureRepository.save(emergencyProcedure);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, emergencyProcedure.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /emergency-procedures} : get all the emergencyProcedures.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of emergencyProcedures in body.
     */
    @GetMapping("/emergency-procedures")
    public List<EmergencyProcedure> getAllEmergencyProcedures() {
        log.debug("REST request to get all EmergencyProcedures");
        return emergencyProcedureRepository.findAll();
    }

    /**
     * {@code GET  /emergency-procedures/:id} : get the "id" emergencyProcedure.
     *
     * @param id the id of the emergencyProcedure to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the emergencyProcedure, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/emergency-procedures/{id}")
    public ResponseEntity<EmergencyProcedure> getEmergencyProcedure(@PathVariable Long id) {
        log.debug("REST request to get EmergencyProcedure : {}", id);
        Optional<EmergencyProcedure> emergencyProcedure = emergencyProcedureRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(emergencyProcedure);
    }

    /**
     * {@code DELETE  /emergency-procedures/:id} : delete the "id" emergencyProcedure.
     *
     * @param id the id of the emergencyProcedure to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/emergency-procedures/{id}")
    public ResponseEntity<Void> deleteEmergencyProcedure(@PathVariable Long id) {
        log.debug("REST request to delete EmergencyProcedure : {}", id);
        emergencyProcedureRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
