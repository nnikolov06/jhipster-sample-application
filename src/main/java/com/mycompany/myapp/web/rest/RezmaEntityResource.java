package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.RezmaEntity;
import com.mycompany.myapp.repository.RezmaEntityRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.RezmaEntity}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RezmaEntityResource {

    private final Logger log = LoggerFactory.getLogger(RezmaEntityResource.class);

    private static final String ENTITY_NAME = "rezmaEntity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RezmaEntityRepository rezmaEntityRepository;

    public RezmaEntityResource(RezmaEntityRepository rezmaEntityRepository) {
        this.rezmaEntityRepository = rezmaEntityRepository;
    }

    /**
     * {@code POST  /rezma-entities} : Create a new rezmaEntity.
     *
     * @param rezmaEntity the rezmaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rezmaEntity, or with status {@code 400 (Bad Request)} if the rezmaEntity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rezma-entities")
    public ResponseEntity<RezmaEntity> createRezmaEntity(@RequestBody RezmaEntity rezmaEntity) throws URISyntaxException {
        log.debug("REST request to save RezmaEntity : {}", rezmaEntity);
        if (rezmaEntity.getId() != null) {
            throw new BadRequestAlertException("A new rezmaEntity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RezmaEntity result = rezmaEntityRepository.save(rezmaEntity);
        return ResponseEntity.created(new URI("/api/rezma-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rezma-entities} : Updates an existing rezmaEntity.
     *
     * @param rezmaEntity the rezmaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rezmaEntity,
     * or with status {@code 400 (Bad Request)} if the rezmaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rezmaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rezma-entities")
    public ResponseEntity<RezmaEntity> updateRezmaEntity(@RequestBody RezmaEntity rezmaEntity) throws URISyntaxException {
        log.debug("REST request to update RezmaEntity : {}", rezmaEntity);
        if (rezmaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RezmaEntity result = rezmaEntityRepository.save(rezmaEntity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rezmaEntity.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /rezma-entities} : get all the rezmaEntities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rezmaEntities in body.
     */
    @GetMapping("/rezma-entities")
    public List<RezmaEntity> getAllRezmaEntities() {
        log.debug("REST request to get all RezmaEntities");
        return rezmaEntityRepository.findAll();
    }

    /**
     * {@code GET  /rezma-entities/:id} : get the "id" rezmaEntity.
     *
     * @param id the id of the rezmaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rezmaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rezma-entities/{id}")
    public ResponseEntity<RezmaEntity> getRezmaEntity(@PathVariable Long id) {
        log.debug("REST request to get RezmaEntity : {}", id);
        Optional<RezmaEntity> rezmaEntity = rezmaEntityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(rezmaEntity);
    }

    /**
     * {@code DELETE  /rezma-entities/:id} : delete the "id" rezmaEntity.
     *
     * @param id the id of the rezmaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rezma-entities/{id}")
    public ResponseEntity<Void> deleteRezmaEntity(@PathVariable Long id) {
        log.debug("REST request to delete RezmaEntity : {}", id);
        rezmaEntityRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
