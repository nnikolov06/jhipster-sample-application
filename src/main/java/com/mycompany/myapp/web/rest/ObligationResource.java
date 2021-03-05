package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Obligation;
import com.mycompany.myapp.repository.ObligationRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Obligation}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ObligationResource {

    private final Logger log = LoggerFactory.getLogger(ObligationResource.class);

    private static final String ENTITY_NAME = "obligation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ObligationRepository obligationRepository;

    public ObligationResource(ObligationRepository obligationRepository) {
        this.obligationRepository = obligationRepository;
    }

    /**
     * {@code POST  /obligations} : Create a new obligation.
     *
     * @param obligation the obligation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new obligation, or with status {@code 400 (Bad Request)} if the obligation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/obligations")
    public ResponseEntity<Obligation> createObligation(@RequestBody Obligation obligation) throws URISyntaxException {
        log.debug("REST request to save Obligation : {}", obligation);
        if (obligation.getId() != null) {
            throw new BadRequestAlertException("A new obligation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Obligation result = obligationRepository.save(obligation);
        return ResponseEntity.created(new URI("/api/obligations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /obligations} : Updates an existing obligation.
     *
     * @param obligation the obligation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated obligation,
     * or with status {@code 400 (Bad Request)} if the obligation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the obligation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/obligations")
    public ResponseEntity<Obligation> updateObligation(@RequestBody Obligation obligation) throws URISyntaxException {
        log.debug("REST request to update Obligation : {}", obligation);
        if (obligation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Obligation result = obligationRepository.save(obligation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, obligation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /obligations} : get all the obligations.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of obligations in body.
     */
    @GetMapping("/obligations")
    public List<Obligation> getAllObligations(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Obligations");
        return obligationRepository.findAllWithEagerRelationships();
    }

    /**
     * {@code GET  /obligations/:id} : get the "id" obligation.
     *
     * @param id the id of the obligation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the obligation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/obligations/{id}")
    public ResponseEntity<Obligation> getObligation(@PathVariable Long id) {
        log.debug("REST request to get Obligation : {}", id);
        Optional<Obligation> obligation = obligationRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(obligation);
    }

    /**
     * {@code DELETE  /obligations/:id} : delete the "id" obligation.
     *
     * @param id the id of the obligation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/obligations/{id}")
    public ResponseEntity<Void> deleteObligation(@PathVariable Long id) {
        log.debug("REST request to delete Obligation : {}", id);
        obligationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
