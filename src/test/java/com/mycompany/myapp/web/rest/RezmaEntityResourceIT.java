package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.RedisTestContainerExtension;
import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.RezmaEntity;
import com.mycompany.myapp.repository.RezmaEntityRepository;

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
 * Integration tests for the {@link RezmaEntityResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class RezmaEntityResourceIT {

    private static final String DEFAULT_ENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ENT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ENT_ADDRESS = "BBBBBBBBBB";

    private static final Instant DEFAULT_ENT_SYSTEM_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ENT_SYSTEM_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ENT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_ENT_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_ENT_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_ENT_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_ENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ENT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_ENT_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_ENT_IDENTIFIER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ENT_IDENTIFIER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ENT_TIN = "AAAAAAAAAA";
    private static final String UPDATED_ENT_TIN = "BBBBBBBBBB";

    private static final String DEFAULT_ENT_DOB = "AAAAAAAAAA";
    private static final String UPDATED_ENT_DOB = "BBBBBBBBBB";

    @Autowired
    private RezmaEntityRepository rezmaEntityRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRezmaEntityMockMvc;

    private RezmaEntity rezmaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RezmaEntity createEntity(EntityManager em) {
        RezmaEntity rezmaEntity = new RezmaEntity()
            .entName(DEFAULT_ENT_NAME)
            .entAddress(DEFAULT_ENT_ADDRESS)
            .entSystemDate(DEFAULT_ENT_SYSTEM_DATE)
            .entCreatedBy(DEFAULT_ENT_CREATED_BY)
            .entIdentifier(DEFAULT_ENT_IDENTIFIER)
            .entType(DEFAULT_ENT_TYPE)
            .entCountry(DEFAULT_ENT_COUNTRY)
            .entIdentifierType(DEFAULT_ENT_IDENTIFIER_TYPE)
            .entTin(DEFAULT_ENT_TIN)
            .entDOB(DEFAULT_ENT_DOB);
        return rezmaEntity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RezmaEntity createUpdatedEntity(EntityManager em) {
        RezmaEntity rezmaEntity = new RezmaEntity()
            .entName(UPDATED_ENT_NAME)
            .entAddress(UPDATED_ENT_ADDRESS)
            .entSystemDate(UPDATED_ENT_SYSTEM_DATE)
            .entCreatedBy(UPDATED_ENT_CREATED_BY)
            .entIdentifier(UPDATED_ENT_IDENTIFIER)
            .entType(UPDATED_ENT_TYPE)
            .entCountry(UPDATED_ENT_COUNTRY)
            .entIdentifierType(UPDATED_ENT_IDENTIFIER_TYPE)
            .entTin(UPDATED_ENT_TIN)
            .entDOB(UPDATED_ENT_DOB);
        return rezmaEntity;
    }

    @BeforeEach
    public void initTest() {
        rezmaEntity = createEntity(em);
    }

    @Test
    @Transactional
    public void createRezmaEntity() throws Exception {
        int databaseSizeBeforeCreate = rezmaEntityRepository.findAll().size();
        // Create the RezmaEntity
        restRezmaEntityMockMvc.perform(post("/api/rezma-entities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rezmaEntity)))
            .andExpect(status().isCreated());

        // Validate the RezmaEntity in the database
        List<RezmaEntity> rezmaEntityList = rezmaEntityRepository.findAll();
        assertThat(rezmaEntityList).hasSize(databaseSizeBeforeCreate + 1);
        RezmaEntity testRezmaEntity = rezmaEntityList.get(rezmaEntityList.size() - 1);
        assertThat(testRezmaEntity.getEntName()).isEqualTo(DEFAULT_ENT_NAME);
        assertThat(testRezmaEntity.getEntAddress()).isEqualTo(DEFAULT_ENT_ADDRESS);
        assertThat(testRezmaEntity.getEntSystemDate()).isEqualTo(DEFAULT_ENT_SYSTEM_DATE);
        assertThat(testRezmaEntity.getEntCreatedBy()).isEqualTo(DEFAULT_ENT_CREATED_BY);
        assertThat(testRezmaEntity.getEntIdentifier()).isEqualTo(DEFAULT_ENT_IDENTIFIER);
        assertThat(testRezmaEntity.getEntType()).isEqualTo(DEFAULT_ENT_TYPE);
        assertThat(testRezmaEntity.getEntCountry()).isEqualTo(DEFAULT_ENT_COUNTRY);
        assertThat(testRezmaEntity.getEntIdentifierType()).isEqualTo(DEFAULT_ENT_IDENTIFIER_TYPE);
        assertThat(testRezmaEntity.getEntTin()).isEqualTo(DEFAULT_ENT_TIN);
        assertThat(testRezmaEntity.getEntDOB()).isEqualTo(DEFAULT_ENT_DOB);
    }

    @Test
    @Transactional
    public void createRezmaEntityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rezmaEntityRepository.findAll().size();

        // Create the RezmaEntity with an existing ID
        rezmaEntity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRezmaEntityMockMvc.perform(post("/api/rezma-entities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rezmaEntity)))
            .andExpect(status().isBadRequest());

        // Validate the RezmaEntity in the database
        List<RezmaEntity> rezmaEntityList = rezmaEntityRepository.findAll();
        assertThat(rezmaEntityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRezmaEntities() throws Exception {
        // Initialize the database
        rezmaEntityRepository.saveAndFlush(rezmaEntity);

        // Get all the rezmaEntityList
        restRezmaEntityMockMvc.perform(get("/api/rezma-entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rezmaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].entName").value(hasItem(DEFAULT_ENT_NAME)))
            .andExpect(jsonPath("$.[*].entAddress").value(hasItem(DEFAULT_ENT_ADDRESS)))
            .andExpect(jsonPath("$.[*].entSystemDate").value(hasItem(DEFAULT_ENT_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].entCreatedBy").value(hasItem(DEFAULT_ENT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].entIdentifier").value(hasItem(DEFAULT_ENT_IDENTIFIER)))
            .andExpect(jsonPath("$.[*].entType").value(hasItem(DEFAULT_ENT_TYPE)))
            .andExpect(jsonPath("$.[*].entCountry").value(hasItem(DEFAULT_ENT_COUNTRY)))
            .andExpect(jsonPath("$.[*].entIdentifierType").value(hasItem(DEFAULT_ENT_IDENTIFIER_TYPE)))
            .andExpect(jsonPath("$.[*].entTin").value(hasItem(DEFAULT_ENT_TIN)))
            .andExpect(jsonPath("$.[*].entDOB").value(hasItem(DEFAULT_ENT_DOB)));
    }
    
    @Test
    @Transactional
    public void getRezmaEntity() throws Exception {
        // Initialize the database
        rezmaEntityRepository.saveAndFlush(rezmaEntity);

        // Get the rezmaEntity
        restRezmaEntityMockMvc.perform(get("/api/rezma-entities/{id}", rezmaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rezmaEntity.getId().intValue()))
            .andExpect(jsonPath("$.entName").value(DEFAULT_ENT_NAME))
            .andExpect(jsonPath("$.entAddress").value(DEFAULT_ENT_ADDRESS))
            .andExpect(jsonPath("$.entSystemDate").value(DEFAULT_ENT_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.entCreatedBy").value(DEFAULT_ENT_CREATED_BY))
            .andExpect(jsonPath("$.entIdentifier").value(DEFAULT_ENT_IDENTIFIER))
            .andExpect(jsonPath("$.entType").value(DEFAULT_ENT_TYPE))
            .andExpect(jsonPath("$.entCountry").value(DEFAULT_ENT_COUNTRY))
            .andExpect(jsonPath("$.entIdentifierType").value(DEFAULT_ENT_IDENTIFIER_TYPE))
            .andExpect(jsonPath("$.entTin").value(DEFAULT_ENT_TIN))
            .andExpect(jsonPath("$.entDOB").value(DEFAULT_ENT_DOB));
    }
    @Test
    @Transactional
    public void getNonExistingRezmaEntity() throws Exception {
        // Get the rezmaEntity
        restRezmaEntityMockMvc.perform(get("/api/rezma-entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRezmaEntity() throws Exception {
        // Initialize the database
        rezmaEntityRepository.saveAndFlush(rezmaEntity);

        int databaseSizeBeforeUpdate = rezmaEntityRepository.findAll().size();

        // Update the rezmaEntity
        RezmaEntity updatedRezmaEntity = rezmaEntityRepository.findById(rezmaEntity.getId()).get();
        // Disconnect from session so that the updates on updatedRezmaEntity are not directly saved in db
        em.detach(updatedRezmaEntity);
        updatedRezmaEntity
            .entName(UPDATED_ENT_NAME)
            .entAddress(UPDATED_ENT_ADDRESS)
            .entSystemDate(UPDATED_ENT_SYSTEM_DATE)
            .entCreatedBy(UPDATED_ENT_CREATED_BY)
            .entIdentifier(UPDATED_ENT_IDENTIFIER)
            .entType(UPDATED_ENT_TYPE)
            .entCountry(UPDATED_ENT_COUNTRY)
            .entIdentifierType(UPDATED_ENT_IDENTIFIER_TYPE)
            .entTin(UPDATED_ENT_TIN)
            .entDOB(UPDATED_ENT_DOB);

        restRezmaEntityMockMvc.perform(put("/api/rezma-entities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRezmaEntity)))
            .andExpect(status().isOk());

        // Validate the RezmaEntity in the database
        List<RezmaEntity> rezmaEntityList = rezmaEntityRepository.findAll();
        assertThat(rezmaEntityList).hasSize(databaseSizeBeforeUpdate);
        RezmaEntity testRezmaEntity = rezmaEntityList.get(rezmaEntityList.size() - 1);
        assertThat(testRezmaEntity.getEntName()).isEqualTo(UPDATED_ENT_NAME);
        assertThat(testRezmaEntity.getEntAddress()).isEqualTo(UPDATED_ENT_ADDRESS);
        assertThat(testRezmaEntity.getEntSystemDate()).isEqualTo(UPDATED_ENT_SYSTEM_DATE);
        assertThat(testRezmaEntity.getEntCreatedBy()).isEqualTo(UPDATED_ENT_CREATED_BY);
        assertThat(testRezmaEntity.getEntIdentifier()).isEqualTo(UPDATED_ENT_IDENTIFIER);
        assertThat(testRezmaEntity.getEntType()).isEqualTo(UPDATED_ENT_TYPE);
        assertThat(testRezmaEntity.getEntCountry()).isEqualTo(UPDATED_ENT_COUNTRY);
        assertThat(testRezmaEntity.getEntIdentifierType()).isEqualTo(UPDATED_ENT_IDENTIFIER_TYPE);
        assertThat(testRezmaEntity.getEntTin()).isEqualTo(UPDATED_ENT_TIN);
        assertThat(testRezmaEntity.getEntDOB()).isEqualTo(UPDATED_ENT_DOB);
    }

    @Test
    @Transactional
    public void updateNonExistingRezmaEntity() throws Exception {
        int databaseSizeBeforeUpdate = rezmaEntityRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRezmaEntityMockMvc.perform(put("/api/rezma-entities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rezmaEntity)))
            .andExpect(status().isBadRequest());

        // Validate the RezmaEntity in the database
        List<RezmaEntity> rezmaEntityList = rezmaEntityRepository.findAll();
        assertThat(rezmaEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRezmaEntity() throws Exception {
        // Initialize the database
        rezmaEntityRepository.saveAndFlush(rezmaEntity);

        int databaseSizeBeforeDelete = rezmaEntityRepository.findAll().size();

        // Delete the rezmaEntity
        restRezmaEntityMockMvc.perform(delete("/api/rezma-entities/{id}", rezmaEntity.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RezmaEntity> rezmaEntityList = rezmaEntityRepository.findAll();
        assertThat(rezmaEntityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
