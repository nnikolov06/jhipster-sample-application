package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.RedisTestContainerExtension;
import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Certificate;
import com.mycompany.myapp.repository.CertificateRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CertificateResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class CertificateResourceIT {

    private static final String DEFAULT_CER_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CER_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CER_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CER_DATE = "BBBBBBBBBB";

    private static final Instant DEFAULT_CER_SYS_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CER_SYS_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CER_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CER_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_CER_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CER_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CER_SERVICE_APPLICANT = "AAAAAAAAAA";
    private static final String UPDATED_CER_SERVICE_APPLICANT = "BBBBBBBBBB";

    private static final String DEFAULT_CER_SIGNING_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_CER_SIGNING_PERSON = "BBBBBBBBBB";

    private static final String DEFAULT_CER_SIGNING_PERSON_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_CER_SIGNING_PERSON_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_CER_CERTIFICATE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CER_CERTIFICATE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CER_CHECK_BOX_VALUES = "AAAAAAAAAA";
    private static final String UPDATED_CER_CHECK_BOX_VALUES = "BBBBBBBBBB";

    private static final String DEFAULT_CER_REQUEST_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CER_REQUEST_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CER_REQUEST_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CER_REQUEST_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_CER_IS_DEACTIVATED = false;
    private static final Boolean UPDATED_CER_IS_DEACTIVATED = true;

    private static final String DEFAULT_CER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_CER_STATE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CER_HAS_OBLIGATION = false;
    private static final Boolean UPDATED_CER_HAS_OBLIGATION = true;

    private static final String DEFAULT_CER_DOCUMENT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_CER_DOCUMENT_STATE = "BBBBBBBBBB";

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCertificateMockMvc;

    private Certificate certificate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Certificate createEntity(EntityManager em) {
        Certificate certificate = new Certificate()
            .cerNumber(DEFAULT_CER_NUMBER)
            .cerDate(DEFAULT_CER_DATE)
            .cerSysDate(DEFAULT_CER_SYS_DATE)
            .cerCreatedBy(DEFAULT_CER_CREATED_BY)
            .cerFileName(DEFAULT_CER_FILE_NAME)
            .cerServiceApplicant(DEFAULT_CER_SERVICE_APPLICANT)
            .cerSigningPerson(DEFAULT_CER_SIGNING_PERSON)
            .cerSigningPersonPosition(DEFAULT_CER_SIGNING_PERSON_POSITION)
            .cerCertificateType(DEFAULT_CER_CERTIFICATE_TYPE)
            .cerCheckBoxValues(DEFAULT_CER_CHECK_BOX_VALUES)
            .cerRequestNumber(DEFAULT_CER_REQUEST_NUMBER)
            .cerRequestDate(DEFAULT_CER_REQUEST_DATE)
            .cerIsDeactivated(DEFAULT_CER_IS_DEACTIVATED)
            .cerState(DEFAULT_CER_STATE)
            .cerHasObligation(DEFAULT_CER_HAS_OBLIGATION)
            .cerDocumentState(DEFAULT_CER_DOCUMENT_STATE);
        return certificate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Certificate createUpdatedEntity(EntityManager em) {
        Certificate certificate = new Certificate()
            .cerNumber(UPDATED_CER_NUMBER)
            .cerDate(UPDATED_CER_DATE)
            .cerSysDate(UPDATED_CER_SYS_DATE)
            .cerCreatedBy(UPDATED_CER_CREATED_BY)
            .cerFileName(UPDATED_CER_FILE_NAME)
            .cerServiceApplicant(UPDATED_CER_SERVICE_APPLICANT)
            .cerSigningPerson(UPDATED_CER_SIGNING_PERSON)
            .cerSigningPersonPosition(UPDATED_CER_SIGNING_PERSON_POSITION)
            .cerCertificateType(UPDATED_CER_CERTIFICATE_TYPE)
            .cerCheckBoxValues(UPDATED_CER_CHECK_BOX_VALUES)
            .cerRequestNumber(UPDATED_CER_REQUEST_NUMBER)
            .cerRequestDate(UPDATED_CER_REQUEST_DATE)
            .cerIsDeactivated(UPDATED_CER_IS_DEACTIVATED)
            .cerState(UPDATED_CER_STATE)
            .cerHasObligation(UPDATED_CER_HAS_OBLIGATION)
            .cerDocumentState(UPDATED_CER_DOCUMENT_STATE);
        return certificate;
    }

    @BeforeEach
    public void initTest() {
        certificate = createEntity(em);
    }

    @Test
    @Transactional
    public void createCertificate() throws Exception {
        int databaseSizeBeforeCreate = certificateRepository.findAll().size();
        // Create the Certificate
        restCertificateMockMvc.perform(post("/api/certificates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificate)))
            .andExpect(status().isCreated());

        // Validate the Certificate in the database
        List<Certificate> certificateList = certificateRepository.findAll();
        assertThat(certificateList).hasSize(databaseSizeBeforeCreate + 1);
        Certificate testCertificate = certificateList.get(certificateList.size() - 1);
        assertThat(testCertificate.getCerNumber()).isEqualTo(DEFAULT_CER_NUMBER);
        assertThat(testCertificate.getCerDate()).isEqualTo(DEFAULT_CER_DATE);
        assertThat(testCertificate.getCerSysDate()).isEqualTo(DEFAULT_CER_SYS_DATE);
        assertThat(testCertificate.getCerCreatedBy()).isEqualTo(DEFAULT_CER_CREATED_BY);
        assertThat(testCertificate.getCerFileName()).isEqualTo(DEFAULT_CER_FILE_NAME);
        assertThat(testCertificate.getCerServiceApplicant()).isEqualTo(DEFAULT_CER_SERVICE_APPLICANT);
        assertThat(testCertificate.getCerSigningPerson()).isEqualTo(DEFAULT_CER_SIGNING_PERSON);
        assertThat(testCertificate.getCerSigningPersonPosition()).isEqualTo(DEFAULT_CER_SIGNING_PERSON_POSITION);
        assertThat(testCertificate.getCerCertificateType()).isEqualTo(DEFAULT_CER_CERTIFICATE_TYPE);
        assertThat(testCertificate.getCerCheckBoxValues()).isEqualTo(DEFAULT_CER_CHECK_BOX_VALUES);
        assertThat(testCertificate.getCerRequestNumber()).isEqualTo(DEFAULT_CER_REQUEST_NUMBER);
        assertThat(testCertificate.getCerRequestDate()).isEqualTo(DEFAULT_CER_REQUEST_DATE);
        assertThat(testCertificate.isCerIsDeactivated()).isEqualTo(DEFAULT_CER_IS_DEACTIVATED);
        assertThat(testCertificate.getCerState()).isEqualTo(DEFAULT_CER_STATE);
        assertThat(testCertificate.isCerHasObligation()).isEqualTo(DEFAULT_CER_HAS_OBLIGATION);
        assertThat(testCertificate.getCerDocumentState()).isEqualTo(DEFAULT_CER_DOCUMENT_STATE);
    }

    @Test
    @Transactional
    public void createCertificateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = certificateRepository.findAll().size();

        // Create the Certificate with an existing ID
        certificate.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCertificateMockMvc.perform(post("/api/certificates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificate)))
            .andExpect(status().isBadRequest());

        // Validate the Certificate in the database
        List<Certificate> certificateList = certificateRepository.findAll();
        assertThat(certificateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCertificates() throws Exception {
        // Initialize the database
        certificateRepository.saveAndFlush(certificate);

        // Get all the certificateList
        restCertificateMockMvc.perform(get("/api/certificates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(certificate.getId().intValue())))
            .andExpect(jsonPath("$.[*].cerNumber").value(hasItem(DEFAULT_CER_NUMBER)))
            .andExpect(jsonPath("$.[*].cerDate").value(hasItem(DEFAULT_CER_DATE)))
            .andExpect(jsonPath("$.[*].cerSysDate").value(hasItem(DEFAULT_CER_SYS_DATE.toString())))
            .andExpect(jsonPath("$.[*].cerCreatedBy").value(hasItem(DEFAULT_CER_CREATED_BY)))
            .andExpect(jsonPath("$.[*].cerFileName").value(hasItem(DEFAULT_CER_FILE_NAME)))
            .andExpect(jsonPath("$.[*].cerServiceApplicant").value(hasItem(DEFAULT_CER_SERVICE_APPLICANT)))
            .andExpect(jsonPath("$.[*].cerSigningPerson").value(hasItem(DEFAULT_CER_SIGNING_PERSON)))
            .andExpect(jsonPath("$.[*].cerSigningPersonPosition").value(hasItem(DEFAULT_CER_SIGNING_PERSON_POSITION)))
            .andExpect(jsonPath("$.[*].cerCertificateType").value(hasItem(DEFAULT_CER_CERTIFICATE_TYPE)))
            .andExpect(jsonPath("$.[*].cerCheckBoxValues").value(hasItem(DEFAULT_CER_CHECK_BOX_VALUES)))
            .andExpect(jsonPath("$.[*].cerRequestNumber").value(hasItem(DEFAULT_CER_REQUEST_NUMBER)))
            .andExpect(jsonPath("$.[*].cerRequestDate").value(hasItem(DEFAULT_CER_REQUEST_DATE.toString())))
            .andExpect(jsonPath("$.[*].cerIsDeactivated").value(hasItem(DEFAULT_CER_IS_DEACTIVATED.booleanValue())))
            .andExpect(jsonPath("$.[*].cerState").value(hasItem(DEFAULT_CER_STATE)))
            .andExpect(jsonPath("$.[*].cerHasObligation").value(hasItem(DEFAULT_CER_HAS_OBLIGATION.booleanValue())))
            .andExpect(jsonPath("$.[*].cerDocumentState").value(hasItem(DEFAULT_CER_DOCUMENT_STATE)));
    }
    
    @Test
    @Transactional
    public void getCertificate() throws Exception {
        // Initialize the database
        certificateRepository.saveAndFlush(certificate);

        // Get the certificate
        restCertificateMockMvc.perform(get("/api/certificates/{id}", certificate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(certificate.getId().intValue()))
            .andExpect(jsonPath("$.cerNumber").value(DEFAULT_CER_NUMBER))
            .andExpect(jsonPath("$.cerDate").value(DEFAULT_CER_DATE))
            .andExpect(jsonPath("$.cerSysDate").value(DEFAULT_CER_SYS_DATE.toString()))
            .andExpect(jsonPath("$.cerCreatedBy").value(DEFAULT_CER_CREATED_BY))
            .andExpect(jsonPath("$.cerFileName").value(DEFAULT_CER_FILE_NAME))
            .andExpect(jsonPath("$.cerServiceApplicant").value(DEFAULT_CER_SERVICE_APPLICANT))
            .andExpect(jsonPath("$.cerSigningPerson").value(DEFAULT_CER_SIGNING_PERSON))
            .andExpect(jsonPath("$.cerSigningPersonPosition").value(DEFAULT_CER_SIGNING_PERSON_POSITION))
            .andExpect(jsonPath("$.cerCertificateType").value(DEFAULT_CER_CERTIFICATE_TYPE))
            .andExpect(jsonPath("$.cerCheckBoxValues").value(DEFAULT_CER_CHECK_BOX_VALUES))
            .andExpect(jsonPath("$.cerRequestNumber").value(DEFAULT_CER_REQUEST_NUMBER))
            .andExpect(jsonPath("$.cerRequestDate").value(DEFAULT_CER_REQUEST_DATE.toString()))
            .andExpect(jsonPath("$.cerIsDeactivated").value(DEFAULT_CER_IS_DEACTIVATED.booleanValue()))
            .andExpect(jsonPath("$.cerState").value(DEFAULT_CER_STATE))
            .andExpect(jsonPath("$.cerHasObligation").value(DEFAULT_CER_HAS_OBLIGATION.booleanValue()))
            .andExpect(jsonPath("$.cerDocumentState").value(DEFAULT_CER_DOCUMENT_STATE));
    }
    @Test
    @Transactional
    public void getNonExistingCertificate() throws Exception {
        // Get the certificate
        restCertificateMockMvc.perform(get("/api/certificates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCertificate() throws Exception {
        // Initialize the database
        certificateRepository.saveAndFlush(certificate);

        int databaseSizeBeforeUpdate = certificateRepository.findAll().size();

        // Update the certificate
        Certificate updatedCertificate = certificateRepository.findById(certificate.getId()).get();
        // Disconnect from session so that the updates on updatedCertificate are not directly saved in db
        em.detach(updatedCertificate);
        updatedCertificate
            .cerNumber(UPDATED_CER_NUMBER)
            .cerDate(UPDATED_CER_DATE)
            .cerSysDate(UPDATED_CER_SYS_DATE)
            .cerCreatedBy(UPDATED_CER_CREATED_BY)
            .cerFileName(UPDATED_CER_FILE_NAME)
            .cerServiceApplicant(UPDATED_CER_SERVICE_APPLICANT)
            .cerSigningPerson(UPDATED_CER_SIGNING_PERSON)
            .cerSigningPersonPosition(UPDATED_CER_SIGNING_PERSON_POSITION)
            .cerCertificateType(UPDATED_CER_CERTIFICATE_TYPE)
            .cerCheckBoxValues(UPDATED_CER_CHECK_BOX_VALUES)
            .cerRequestNumber(UPDATED_CER_REQUEST_NUMBER)
            .cerRequestDate(UPDATED_CER_REQUEST_DATE)
            .cerIsDeactivated(UPDATED_CER_IS_DEACTIVATED)
            .cerState(UPDATED_CER_STATE)
            .cerHasObligation(UPDATED_CER_HAS_OBLIGATION)
            .cerDocumentState(UPDATED_CER_DOCUMENT_STATE);

        restCertificateMockMvc.perform(put("/api/certificates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCertificate)))
            .andExpect(status().isOk());

        // Validate the Certificate in the database
        List<Certificate> certificateList = certificateRepository.findAll();
        assertThat(certificateList).hasSize(databaseSizeBeforeUpdate);
        Certificate testCertificate = certificateList.get(certificateList.size() - 1);
        assertThat(testCertificate.getCerNumber()).isEqualTo(UPDATED_CER_NUMBER);
        assertThat(testCertificate.getCerDate()).isEqualTo(UPDATED_CER_DATE);
        assertThat(testCertificate.getCerSysDate()).isEqualTo(UPDATED_CER_SYS_DATE);
        assertThat(testCertificate.getCerCreatedBy()).isEqualTo(UPDATED_CER_CREATED_BY);
        assertThat(testCertificate.getCerFileName()).isEqualTo(UPDATED_CER_FILE_NAME);
        assertThat(testCertificate.getCerServiceApplicant()).isEqualTo(UPDATED_CER_SERVICE_APPLICANT);
        assertThat(testCertificate.getCerSigningPerson()).isEqualTo(UPDATED_CER_SIGNING_PERSON);
        assertThat(testCertificate.getCerSigningPersonPosition()).isEqualTo(UPDATED_CER_SIGNING_PERSON_POSITION);
        assertThat(testCertificate.getCerCertificateType()).isEqualTo(UPDATED_CER_CERTIFICATE_TYPE);
        assertThat(testCertificate.getCerCheckBoxValues()).isEqualTo(UPDATED_CER_CHECK_BOX_VALUES);
        assertThat(testCertificate.getCerRequestNumber()).isEqualTo(UPDATED_CER_REQUEST_NUMBER);
        assertThat(testCertificate.getCerRequestDate()).isEqualTo(UPDATED_CER_REQUEST_DATE);
        assertThat(testCertificate.isCerIsDeactivated()).isEqualTo(UPDATED_CER_IS_DEACTIVATED);
        assertThat(testCertificate.getCerState()).isEqualTo(UPDATED_CER_STATE);
        assertThat(testCertificate.isCerHasObligation()).isEqualTo(UPDATED_CER_HAS_OBLIGATION);
        assertThat(testCertificate.getCerDocumentState()).isEqualTo(UPDATED_CER_DOCUMENT_STATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCertificate() throws Exception {
        int databaseSizeBeforeUpdate = certificateRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCertificateMockMvc.perform(put("/api/certificates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificate)))
            .andExpect(status().isBadRequest());

        // Validate the Certificate in the database
        List<Certificate> certificateList = certificateRepository.findAll();
        assertThat(certificateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCertificate() throws Exception {
        // Initialize the database
        certificateRepository.saveAndFlush(certificate);

        int databaseSizeBeforeDelete = certificateRepository.findAll().size();

        // Delete the certificate
        restCertificateMockMvc.perform(delete("/api/certificates/{id}", certificate.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Certificate> certificateList = certificateRepository.findAll();
        assertThat(certificateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
