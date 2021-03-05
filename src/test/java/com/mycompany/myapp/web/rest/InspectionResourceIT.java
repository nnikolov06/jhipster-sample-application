package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.RedisTestContainerExtension;
import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Inspection;
import com.mycompany.myapp.repository.InspectionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link InspectionResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class InspectionResourceIT {

    private static final String DEFAULT_INS_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_INS_NUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_INS_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INS_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_INS_CUSTOMS_OFFICE = "AAAAAAAAAA";
    private static final String UPDATED_INS_CUSTOMS_OFFICE = "BBBBBBBBBB";

    private static final String DEFAULT_INS_USER = "AAAAAAAAAA";
    private static final String UPDATED_INS_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_INS_SYS_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INS_SYS_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_INS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_INS_TYPE = "BBBBBBBBBB";

    @Autowired
    private InspectionRepository inspectionRepository;

    @Mock
    private InspectionRepository inspectionRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionMockMvc;

    private Inspection inspection;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Inspection createEntity(EntityManager em) {
        Inspection inspection = new Inspection()
            .insNumber(DEFAULT_INS_NUMBER)
            .insDate(DEFAULT_INS_DATE)
            .insCustomsOffice(DEFAULT_INS_CUSTOMS_OFFICE)
            .insUser(DEFAULT_INS_USER)
            .insSysDate(DEFAULT_INS_SYS_DATE)
            .insType(DEFAULT_INS_TYPE);
        return inspection;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Inspection createUpdatedEntity(EntityManager em) {
        Inspection inspection = new Inspection()
            .insNumber(UPDATED_INS_NUMBER)
            .insDate(UPDATED_INS_DATE)
            .insCustomsOffice(UPDATED_INS_CUSTOMS_OFFICE)
            .insUser(UPDATED_INS_USER)
            .insSysDate(UPDATED_INS_SYS_DATE)
            .insType(UPDATED_INS_TYPE);
        return inspection;
    }

    @BeforeEach
    public void initTest() {
        inspection = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspection() throws Exception {
        int databaseSizeBeforeCreate = inspectionRepository.findAll().size();
        // Create the Inspection
        restInspectionMockMvc.perform(post("/api/inspections")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspection)))
            .andExpect(status().isCreated());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeCreate + 1);
        Inspection testInspection = inspectionList.get(inspectionList.size() - 1);
        assertThat(testInspection.getInsNumber()).isEqualTo(DEFAULT_INS_NUMBER);
        assertThat(testInspection.getInsDate()).isEqualTo(DEFAULT_INS_DATE);
        assertThat(testInspection.getInsCustomsOffice()).isEqualTo(DEFAULT_INS_CUSTOMS_OFFICE);
        assertThat(testInspection.getInsUser()).isEqualTo(DEFAULT_INS_USER);
        assertThat(testInspection.getInsSysDate()).isEqualTo(DEFAULT_INS_SYS_DATE);
        assertThat(testInspection.getInsType()).isEqualTo(DEFAULT_INS_TYPE);
    }

    @Test
    @Transactional
    public void createInspectionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionRepository.findAll().size();

        // Create the Inspection with an existing ID
        inspection.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionMockMvc.perform(post("/api/inspections")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspection)))
            .andExpect(status().isBadRequest());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInspections() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList
        restInspectionMockMvc.perform(get("/api/inspections?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspection.getId().intValue())))
            .andExpect(jsonPath("$.[*].insNumber").value(hasItem(DEFAULT_INS_NUMBER)))
            .andExpect(jsonPath("$.[*].insDate").value(hasItem(DEFAULT_INS_DATE.toString())))
            .andExpect(jsonPath("$.[*].insCustomsOffice").value(hasItem(DEFAULT_INS_CUSTOMS_OFFICE)))
            .andExpect(jsonPath("$.[*].insUser").value(hasItem(DEFAULT_INS_USER)))
            .andExpect(jsonPath("$.[*].insSysDate").value(hasItem(DEFAULT_INS_SYS_DATE.toString())))
            .andExpect(jsonPath("$.[*].insType").value(hasItem(DEFAULT_INS_TYPE)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllInspectionsWithEagerRelationshipsIsEnabled() throws Exception {
        when(inspectionRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restInspectionMockMvc.perform(get("/api/inspections?eagerload=true"))
            .andExpect(status().isOk());

        verify(inspectionRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllInspectionsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(inspectionRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restInspectionMockMvc.perform(get("/api/inspections?eagerload=true"))
            .andExpect(status().isOk());

        verify(inspectionRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getInspection() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get the inspection
        restInspectionMockMvc.perform(get("/api/inspections/{id}", inspection.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspection.getId().intValue()))
            .andExpect(jsonPath("$.insNumber").value(DEFAULT_INS_NUMBER))
            .andExpect(jsonPath("$.insDate").value(DEFAULT_INS_DATE.toString()))
            .andExpect(jsonPath("$.insCustomsOffice").value(DEFAULT_INS_CUSTOMS_OFFICE))
            .andExpect(jsonPath("$.insUser").value(DEFAULT_INS_USER))
            .andExpect(jsonPath("$.insSysDate").value(DEFAULT_INS_SYS_DATE.toString()))
            .andExpect(jsonPath("$.insType").value(DEFAULT_INS_TYPE));
    }
    @Test
    @Transactional
    public void getNonExistingInspection() throws Exception {
        // Get the inspection
        restInspectionMockMvc.perform(get("/api/inspections/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspection() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        int databaseSizeBeforeUpdate = inspectionRepository.findAll().size();

        // Update the inspection
        Inspection updatedInspection = inspectionRepository.findById(inspection.getId()).get();
        // Disconnect from session so that the updates on updatedInspection are not directly saved in db
        em.detach(updatedInspection);
        updatedInspection
            .insNumber(UPDATED_INS_NUMBER)
            .insDate(UPDATED_INS_DATE)
            .insCustomsOffice(UPDATED_INS_CUSTOMS_OFFICE)
            .insUser(UPDATED_INS_USER)
            .insSysDate(UPDATED_INS_SYS_DATE)
            .insType(UPDATED_INS_TYPE);

        restInspectionMockMvc.perform(put("/api/inspections")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInspection)))
            .andExpect(status().isOk());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeUpdate);
        Inspection testInspection = inspectionList.get(inspectionList.size() - 1);
        assertThat(testInspection.getInsNumber()).isEqualTo(UPDATED_INS_NUMBER);
        assertThat(testInspection.getInsDate()).isEqualTo(UPDATED_INS_DATE);
        assertThat(testInspection.getInsCustomsOffice()).isEqualTo(UPDATED_INS_CUSTOMS_OFFICE);
        assertThat(testInspection.getInsUser()).isEqualTo(UPDATED_INS_USER);
        assertThat(testInspection.getInsSysDate()).isEqualTo(UPDATED_INS_SYS_DATE);
        assertThat(testInspection.getInsType()).isEqualTo(UPDATED_INS_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingInspection() throws Exception {
        int databaseSizeBeforeUpdate = inspectionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionMockMvc.perform(put("/api/inspections")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspection)))
            .andExpect(status().isBadRequest());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspection() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        int databaseSizeBeforeDelete = inspectionRepository.findAll().size();

        // Delete the inspection
        restInspectionMockMvc.perform(delete("/api/inspections/{id}", inspection.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
