package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ScheduleLock;
import com.mycompany.myapp.repository.ScheduleLockRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ScheduleLock}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ScheduleLockResource {

    private final Logger log = LoggerFactory.getLogger(ScheduleLockResource.class);

    private static final String ENTITY_NAME = "scheduleLock";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ScheduleLockRepository scheduleLockRepository;

    public ScheduleLockResource(ScheduleLockRepository scheduleLockRepository) {
        this.scheduleLockRepository = scheduleLockRepository;
    }

    /**
     * {@code POST  /schedule-locks} : Create a new scheduleLock.
     *
     * @param scheduleLock the scheduleLock to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new scheduleLock, or with status {@code 400 (Bad Request)} if the scheduleLock has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/schedule-locks")
    public ResponseEntity<ScheduleLock> createScheduleLock(@RequestBody ScheduleLock scheduleLock) throws URISyntaxException {
        log.debug("REST request to save ScheduleLock : {}", scheduleLock);
        if (scheduleLock.getId() != null) {
            throw new BadRequestAlertException("A new scheduleLock cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScheduleLock result = scheduleLockRepository.save(scheduleLock);
        return ResponseEntity.created(new URI("/api/schedule-locks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /schedule-locks} : Updates an existing scheduleLock.
     *
     * @param scheduleLock the scheduleLock to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scheduleLock,
     * or with status {@code 400 (Bad Request)} if the scheduleLock is not valid,
     * or with status {@code 500 (Internal Server Error)} if the scheduleLock couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/schedule-locks")
    public ResponseEntity<ScheduleLock> updateScheduleLock(@RequestBody ScheduleLock scheduleLock) throws URISyntaxException {
        log.debug("REST request to update ScheduleLock : {}", scheduleLock);
        if (scheduleLock.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ScheduleLock result = scheduleLockRepository.save(scheduleLock);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, scheduleLock.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /schedule-locks} : get all the scheduleLocks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of scheduleLocks in body.
     */
    @GetMapping("/schedule-locks")
    public List<ScheduleLock> getAllScheduleLocks() {
        log.debug("REST request to get all ScheduleLocks");
        return scheduleLockRepository.findAll();
    }

    /**
     * {@code GET  /schedule-locks/:id} : get the "id" scheduleLock.
     *
     * @param id the id of the scheduleLock to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the scheduleLock, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/schedule-locks/{id}")
    public ResponseEntity<ScheduleLock> getScheduleLock(@PathVariable Long id) {
        log.debug("REST request to get ScheduleLock : {}", id);
        Optional<ScheduleLock> scheduleLock = scheduleLockRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(scheduleLock);
    }

    /**
     * {@code DELETE  /schedule-locks/:id} : delete the "id" scheduleLock.
     *
     * @param id the id of the scheduleLock to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/schedule-locks/{id}")
    public ResponseEntity<Void> deleteScheduleLock(@PathVariable Long id) {
        log.debug("REST request to delete ScheduleLock : {}", id);
        scheduleLockRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
