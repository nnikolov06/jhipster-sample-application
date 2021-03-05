package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.RedisTestContainerExtension;
import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.CertificateEmergencyText;
import com.mycompany.myapp.repository.CertificateEmergencyTextRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CertificateEmergencyTextResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class CertificateEmergencyTextResourceIT {

    private static final String DEFAULT_CET_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_CET_TEXT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CET_IS_ACTIVE = false;
    private static final Boolean UPDATED_CET_IS_ACTIVE = true;

    @Autowired
    private CertificateEmergencyTextRepository certificateEmergencyTextRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCertificateEmergencyTextMockMvc;

    private CertificateEmergencyText certificateEmergencyText;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CertificateEmergencyText createEntity(EntityManager em) {
        CertificateEmergencyText certificateEmergencyText = new CertificateEmergencyText()
            .cetText(DEFAULT_CET_TEXT)
            .cetIsActive(DEFAULT_CET_IS_ACTIVE);
        return certificateEmergencyText;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CertificateEmergencyText createUpdatedEntity(EntityManager em) {
        CertificateEmergencyText certificateEmergencyText = new CertificateEmergencyText()
            .cetText(UPDATED_CET_TEXT)
            .cetIsActive(UPDATED_CET_IS_ACTIVE);
        return certificateEmergencyText;
    }

    @BeforeEach
    public void initTest() {
        certificateEmergencyText = createEntity(em);
    }

    @Test
    @Transactional
    public void createCertificateEmergencyText() throws Exception {
        int databaseSizeBeforeCreate = certificateEmergencyTextRepository.findAll().size();
        // Create the CertificateEmergencyText
        restCertificateEmergencyTextMockMvc.perform(post("/api/certificate-emergency-texts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificateEmergencyText)))
            .andExpect(status().isCreated());

        // Validate the CertificateEmergencyText in the database
        List<CertificateEmergencyText> certificateEmergencyTextList = certificateEmergencyTextRepository.findAll();
        assertThat(certificateEmergencyTextList).hasSize(databaseSizeBeforeCreate + 1);
        CertificateEmergencyText testCertificateEmergencyText = certificateEmergencyTextList.get(certificateEmergencyTextList.size() - 1);
        assertThat(testCertificateEmergencyText.getCetText()).isEqualTo(DEFAULT_CET_TEXT);
        assertThat(testCertificateEmergencyText.isCetIsActive()).isEqualTo(DEFAULT_CET_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void createCertificateEmergencyTextWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = certificateEmergencyTextRepository.findAll().size();

        // Create the CertificateEmergencyText with an existing ID
        certificateEmergencyText.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCertificateEmergencyTextMockMvc.perform(post("/api/certificate-emergency-texts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificateEmergencyText)))
            .andExpect(status().isBadRequest());

        // Validate the CertificateEmergencyText in the database
        List<CertificateEmergencyText> certificateEmergencyTextList = certificateEmergencyTextRepository.findAll();
        assertThat(certificateEmergencyTextList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCertificateEmergencyTexts() throws Exception {
        // Initialize the database
        certificateEmergencyTextRepository.saveAndFlush(certificateEmergencyText);

        // Get all the certificateEmergencyTextList
        restCertificateEmergencyTextMockMvc.perform(get("/api/certificate-emergency-texts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(certificateEmergencyText.getId().intValue())))
            .andExpect(jsonPath("$.[*].cetText").value(hasItem(DEFAULT_CET_TEXT)))
            .andExpect(jsonPath("$.[*].cetIsActive").value(hasItem(DEFAULT_CET_IS_ACTIVE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCertificateEmergencyText() throws Exception {
        // Initialize the database
        certificateEmergencyTextRepository.saveAndFlush(certificateEmergencyText);

        // Get the certificateEmergencyText
        restCertificateEmergencyTextMockMvc.perform(get("/api/certificate-emergency-texts/{id}", certificateEmergencyText.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(certificateEmergencyText.getId().intValue()))
            .andExpect(jsonPath("$.cetText").value(DEFAULT_CET_TEXT))
            .andExpect(jsonPath("$.cetIsActive").value(DEFAULT_CET_IS_ACTIVE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCertificateEmergencyText() throws Exception {
        // Get the certificateEmergencyText
        restCertificateEmergencyTextMockMvc.perform(get("/api/certificate-emergency-texts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCertificateEmergencyText() throws Exception {
        // Initialize the database
        certificateEmergencyTextRepository.saveAndFlush(certificateEmergencyText);

        int databaseSizeBeforeUpdate = certificateEmergencyTextRepository.findAll().size();

        // Update the certificateEmergencyText
        CertificateEmergencyText updatedCertificateEmergencyText = certificateEmergencyTextRepository.findById(certificateEmergencyText.getId()).get();
        // Disconnect from session so that the updates on updatedCertificateEmergencyText are not directly saved in db
        em.detach(updatedCertificateEmergencyText);
        updatedCertificateEmergencyText
            .cetText(UPDATED_CET_TEXT)
            .cetIsActive(UPDATED_CET_IS_ACTIVE);

        restCertificateEmergencyTextMockMvc.perform(put("/api/certificate-emergency-texts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCertificateEmergencyText)))
            .andExpect(status().isOk());

        // Validate the CertificateEmergencyText in the database
        List<CertificateEmergencyText> certificateEmergencyTextList = certificateEmergencyTextRepository.findAll();
        assertThat(certificateEmergencyTextList).hasSize(databaseSizeBeforeUpdate);
        CertificateEmergencyText testCertificateEmergencyText = certificateEmergencyTextList.get(certificateEmergencyTextList.size() - 1);
        assertThat(testCertificateEmergencyText.getCetText()).isEqualTo(UPDATED_CET_TEXT);
        assertThat(testCertificateEmergencyText.isCetIsActive()).isEqualTo(UPDATED_CET_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void updateNonExistingCertificateEmergencyText() throws Exception {
        int databaseSizeBeforeUpdate = certificateEmergencyTextRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCertificateEmergencyTextMockMvc.perform(put("/api/certificate-emergency-texts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificateEmergencyText)))
            .andExpect(status().isBadRequest());

        // Validate the CertificateEmergencyText in the database
        List<CertificateEmergencyText> certificateEmergencyTextList = certificateEmergencyTextRepository.findAll();
        assertThat(certificateEmergencyTextList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCertificateEmergencyText() throws Exception {
        // Initialize the database
        certificateEmergencyTextRepository.saveAndFlush(certificateEmergencyText);

        int databaseSizeBeforeDelete = certificateEmergencyTextRepository.findAll().size();

        // Delete the certificateEmergencyText
        restCertificateEmergencyTextMockMvc.perform(delete("/api/certificate-emergency-texts/{id}", certificateEmergencyText.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CertificateEmergencyText> certificateEmergencyTextList = certificateEmergencyTextRepository.findAll();
        assertThat(certificateEmergencyTextList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
