package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.RedisTestContainerExtension;
import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.EmergencyProcedure;
import com.mycompany.myapp.repository.EmergencyProcedureRepository;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EmergencyProcedureResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class EmergencyProcedureResourceIT {

    private static final String DEFAULT_EMP_SYSTEM = "AAAAAAAAAA";
    private static final String UPDATED_EMP_SYSTEM = "BBBBBBBBBB";

    private static final String DEFAULT_EMP_REASON = "AAAAAAAAAA";
    private static final String UPDATED_EMP_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_EMP_USER = "AAAAAAAAAA";
    private static final String UPDATED_EMP_USER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EMP_IS_ACTIVE = false;
    private static final Boolean UPDATED_EMP_IS_ACTIVE = true;

    private static final Instant DEFAULT_EMP_DATE_START = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EMP_DATE_START = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_EMP_DATE_END = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EMP_DATE_END = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private EmergencyProcedureRepository emergencyProcedureRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmergencyProcedureMockMvc;

    private EmergencyProcedure emergencyProcedure;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmergencyProcedure createEntity(EntityManager em) {
        EmergencyProcedure emergencyProcedure = new EmergencyProcedure()
            .empSystem(DEFAULT_EMP_SYSTEM)
            .empReason(DEFAULT_EMP_REASON)
            .empUser(DEFAULT_EMP_USER)
            .empIsActive(DEFAULT_EMP_IS_ACTIVE)
            .empDateStart(DEFAULT_EMP_DATE_START)
            .empDateEnd(DEFAULT_EMP_DATE_END);
        return emergencyProcedure;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmergencyProcedure createUpdatedEntity(EntityManager em) {
        EmergencyProcedure emergencyProcedure = new EmergencyProcedure()
            .empSystem(UPDATED_EMP_SYSTEM)
            .empReason(UPDATED_EMP_REASON)
            .empUser(UPDATED_EMP_USER)
            .empIsActive(UPDATED_EMP_IS_ACTIVE)
            .empDateStart(UPDATED_EMP_DATE_START)
            .empDateEnd(UPDATED_EMP_DATE_END);
        return emergencyProcedure;
    }

    @BeforeEach
    public void initTest() {
        emergencyProcedure = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmergencyProcedure() throws Exception {
        int databaseSizeBeforeCreate = emergencyProcedureRepository.findAll().size();
        // Create the EmergencyProcedure
        restEmergencyProcedureMockMvc.perform(post("/api/emergency-procedures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emergencyProcedure)))
            .andExpect(status().isCreated());

        // Validate the EmergencyProcedure in the database
        List<EmergencyProcedure> emergencyProcedureList = emergencyProcedureRepository.findAll();
        assertThat(emergencyProcedureList).hasSize(databaseSizeBeforeCreate + 1);
        EmergencyProcedure testEmergencyProcedure = emergencyProcedureList.get(emergencyProcedureList.size() - 1);
        assertThat(testEmergencyProcedure.getEmpSystem()).isEqualTo(DEFAULT_EMP_SYSTEM);
        assertThat(testEmergencyProcedure.getEmpReason()).isEqualTo(DEFAULT_EMP_REASON);
        assertThat(testEmergencyProcedure.getEmpUser()).isEqualTo(DEFAULT_EMP_USER);
        assertThat(testEmergencyProcedure.isEmpIsActive()).isEqualTo(DEFAULT_EMP_IS_ACTIVE);
        assertThat(testEmergencyProcedure.getEmpDateStart()).isEqualTo(DEFAULT_EMP_DATE_START);
        assertThat(testEmergencyProcedure.getEmpDateEnd()).isEqualTo(DEFAULT_EMP_DATE_END);
    }

    @Test
    @Transactional
    public void createEmergencyProcedureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emergencyProcedureRepository.findAll().size();

        // Create the EmergencyProcedure with an existing ID
        emergencyProcedure.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmergencyProcedureMockMvc.perform(post("/api/emergency-procedures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emergencyProcedure)))
            .andExpect(status().isBadRequest());

        // Validate the EmergencyProcedure in the database
        List<EmergencyProcedure> emergencyProcedureList = emergencyProcedureRepository.findAll();
        assertThat(emergencyProcedureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEmergencyProcedures() throws Exception {
        // Initialize the database
        emergencyProcedureRepository.saveAndFlush(emergencyProcedure);

        // Get all the emergencyProcedureList
        restEmergencyProcedureMockMvc.perform(get("/api/emergency-procedures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emergencyProcedure.getId().intValue())))
            .andExpect(jsonPath("$.[*].empSystem").value(hasItem(DEFAULT_EMP_SYSTEM)))
            .andExpect(jsonPath("$.[*].empReason").value(hasItem(DEFAULT_EMP_REASON)))
            .andExpect(jsonPath("$.[*].empUser").value(hasItem(DEFAULT_EMP_USER)))
            .andExpect(jsonPath("$.[*].empIsActive").value(hasItem(DEFAULT_EMP_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].empDateStart").value(hasItem(DEFAULT_EMP_DATE_START.toString())))
            .andExpect(jsonPath("$.[*].empDateEnd").value(hasItem(DEFAULT_EMP_DATE_END.toString())));
    }
    
    @Test
    @Transactional
    public void getEmergencyProcedure() throws Exception {
        // Initialize the database
        emergencyProcedureRepository.saveAndFlush(emergencyProcedure);

        // Get the emergencyProcedure
        restEmergencyProcedureMockMvc.perform(get("/api/emergency-procedures/{id}", emergencyProcedure.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(emergencyProcedure.getId().intValue()))
            .andExpect(jsonPath("$.empSystem").value(DEFAULT_EMP_SYSTEM))
            .andExpect(jsonPath("$.empReason").value(DEFAULT_EMP_REASON))
            .andExpect(jsonPath("$.empUser").value(DEFAULT_EMP_USER))
            .andExpect(jsonPath("$.empIsActive").value(DEFAULT_EMP_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.empDateStart").value(DEFAULT_EMP_DATE_START.toString()))
            .andExpect(jsonPath("$.empDateEnd").value(DEFAULT_EMP_DATE_END.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEmergencyProcedure() throws Exception {
        // Get the emergencyProcedure
        restEmergencyProcedureMockMvc.perform(get("/api/emergency-procedures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmergencyProcedure() throws Exception {
        // Initialize the database
        emergencyProcedureRepository.saveAndFlush(emergencyProcedure);

        int databaseSizeBeforeUpdate = emergencyProcedureRepository.findAll().size();

        // Update the emergencyProcedure
        EmergencyProcedure updatedEmergencyProcedure = emergencyProcedureRepository.findById(emergencyProcedure.getId()).get();
        // Disconnect from session so that the updates on updatedEmergencyProcedure are not directly saved in db
        em.detach(updatedEmergencyProcedure);
        updatedEmergencyProcedure
            .empSystem(UPDATED_EMP_SYSTEM)
            .empReason(UPDATED_EMP_REASON)
            .empUser(UPDATED_EMP_USER)
            .empIsActive(UPDATED_EMP_IS_ACTIVE)
            .empDateStart(UPDATED_EMP_DATE_START)
            .empDateEnd(UPDATED_EMP_DATE_END);

        restEmergencyProcedureMockMvc.perform(put("/api/emergency-procedures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEmergencyProcedure)))
            .andExpect(status().isOk());

        // Validate the EmergencyProcedure in the database
        List<EmergencyProcedure> emergencyProcedureList = emergencyProcedureRepository.findAll();
        assertThat(emergencyProcedureList).hasSize(databaseSizeBeforeUpdate);
        EmergencyProcedure testEmergencyProcedure = emergencyProcedureList.get(emergencyProcedureList.size() - 1);
        assertThat(testEmergencyProcedure.getEmpSystem()).isEqualTo(UPDATED_EMP_SYSTEM);
        assertThat(testEmergencyProcedure.getEmpReason()).isEqualTo(UPDATED_EMP_REASON);
        assertThat(testEmergencyProcedure.getEmpUser()).isEqualTo(UPDATED_EMP_USER);
        assertThat(testEmergencyProcedure.isEmpIsActive()).isEqualTo(UPDATED_EMP_IS_ACTIVE);
        assertThat(testEmergencyProcedure.getEmpDateStart()).isEqualTo(UPDATED_EMP_DATE_START);
        assertThat(testEmergencyProcedure.getEmpDateEnd()).isEqualTo(UPDATED_EMP_DATE_END);
    }

    @Test
    @Transactional
    public void updateNonExistingEmergencyProcedure() throws Exception {
        int databaseSizeBeforeUpdate = emergencyProcedureRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmergencyProcedureMockMvc.perform(put("/api/emergency-procedures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emergencyProcedure)))
            .andExpect(status().isBadRequest());

        // Validate the EmergencyProcedure in the database
        List<EmergencyProcedure> emergencyProcedureList = emergencyProcedureRepository.findAll();
        assertThat(emergencyProcedureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmergencyProcedure() throws Exception {
        // Initialize the database
        emergencyProcedureRepository.saveAndFlush(emergencyProcedure);

        int databaseSizeBeforeDelete = emergencyProcedureRepository.findAll().size();

        // Delete the emergencyProcedure
        restEmergencyProcedureMockMvc.perform(delete("/api/emergency-procedures/{id}", emergencyProcedure.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EmergencyProcedure> emergencyProcedureList = emergencyProcedureRepository.findAll();
        assertThat(emergencyProcedureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
