package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.CertificateEmergencyText;
import com.mycompany.myapp.repository.CertificateEmergencyTextRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.CertificateEmergencyText}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CertificateEmergencyTextResource {

    private final Logger log = LoggerFactory.getLogger(CertificateEmergencyTextResource.class);

    private static final String ENTITY_NAME = "certificateEmergencyText";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CertificateEmergencyTextRepository certificateEmergencyTextRepository;

    public CertificateEmergencyTextResource(CertificateEmergencyTextRepository certificateEmergencyTextRepository) {
        this.certificateEmergencyTextRepository = certificateEmergencyTextRepository;
    }

    /**
     * {@code POST  /certificate-emergency-texts} : Create a new certificateEmergencyText.
     *
     * @param certificateEmergencyText the certificateEmergencyText to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new certificateEmergencyText, or with status {@code 400 (Bad Request)} if the certificateEmergencyText has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/certificate-emergency-texts")
    public ResponseEntity<CertificateEmergencyText> createCertificateEmergencyText(@RequestBody CertificateEmergencyText certificateEmergencyText) throws URISyntaxException {
        log.debug("REST request to save CertificateEmergencyText : {}", certificateEmergencyText);
        if (certificateEmergencyText.getId() != null) {
            throw new BadRequestAlertException("A new certificateEmergencyText cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CertificateEmergencyText result = certificateEmergencyTextRepository.save(certificateEmergencyText);
        return ResponseEntity.created(new URI("/api/certificate-emergency-texts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /certificate-emergency-texts} : Updates an existing certificateEmergencyText.
     *
     * @param certificateEmergencyText the certificateEmergencyText to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated certificateEmergencyText,
     * or with status {@code 400 (Bad Request)} if the certificateEmergencyText is not valid,
     * or with status {@code 500 (Internal Server Error)} if the certificateEmergencyText couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/certificate-emergency-texts")
    public ResponseEntity<CertificateEmergencyText> updateCertificateEmergencyText(@RequestBody CertificateEmergencyText certificateEmergencyText) throws URISyntaxException {
        log.debug("REST request to update CertificateEmergencyText : {}", certificateEmergencyText);
        if (certificateEmergencyText.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CertificateEmergencyText result = certificateEmergencyTextRepository.save(certificateEmergencyText);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, certificateEmergencyText.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /certificate-emergency-texts} : get all the certificateEmergencyTexts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of certificateEmergencyTexts in body.
     */
    @GetMapping("/certificate-emergency-texts")
    public List<CertificateEmergencyText> getAllCertificateEmergencyTexts() {
        log.debug("REST request to get all CertificateEmergencyTexts");
        return certificateEmergencyTextRepository.findAll();
    }

    /**
     * {@code GET  /certificate-emergency-texts/:id} : get the "id" certificateEmergencyText.
     *
     * @param id the id of the certificateEmergencyText to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the certificateEmergencyText, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/certificate-emergency-texts/{id}")
    public ResponseEntity<CertificateEmergencyText> getCertificateEmergencyText(@PathVariable Long id) {
        log.debug("REST request to get CertificateEmergencyText : {}", id);
        Optional<CertificateEmergencyText> certificateEmergencyText = certificateEmergencyTextRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(certificateEmergencyText);
    }

    /**
     * {@code DELETE  /certificate-emergency-texts/:id} : delete the "id" certificateEmergencyText.
     *
     * @param id the id of the certificateEmergencyText to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/certificate-emergency-texts/{id}")
    public ResponseEntity<Void> deleteCertificateEmergencyText(@PathVariable Long id) {
        log.debug("REST request to delete CertificateEmergencyText : {}", id);
        certificateEmergencyTextRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
