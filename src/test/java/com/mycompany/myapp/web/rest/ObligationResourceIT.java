package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.RedisTestContainerExtension;
import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Obligation;
import com.mycompany.myapp.repository.ObligationRepository;

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
import java.math.BigDecimal;
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
 * Integration tests for the {@link ObligationResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class ObligationResourceIT {

    private static final Instant DEFAULT_OBL_SYS_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OBL_SYS_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_OBL_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_OBL_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_OBL_CUSTOMS_OFFICE = "AAAAAAAAAA";
    private static final String UPDATED_OBL_CUSTOMS_OFFICE = "BBBBBBBBBB";

    private static final String DEFAULT_OBL_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_OBL_TYPE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_OBL_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_OBL_AMOUNT = new BigDecimal(2);

    private static final Boolean DEFAULT_OBL_LAPSED = false;
    private static final Boolean UPDATED_OBL_LAPSED = true;

    private static final String DEFAULT_OBL_VPO_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_OBL_VPO_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_OBL_TRANSACTION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_OBL_TRANSACTION_NUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_OBL_MRA_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OBL_MRA_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_OBL_STATE = "AAAAAAAAAA";
    private static final String UPDATED_OBL_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_OBL_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_OBL_IDENTIFIER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_OBL_AMOUNT_DIFFERENCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_OBL_AMOUNT_DIFFERENCE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_OBL_GUARANTY_DIFFERENCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_OBL_GUARANTY_DIFFERENCE = new BigDecimal(2);

    private static final String DEFAULT_OBL_CUSTOMS_OFFICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OBL_CUSTOMS_OFFICE_NAME = "BBBBBBBBBB";

    @Autowired
    private ObligationRepository obligationRepository;

    @Mock
    private ObligationRepository obligationRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restObligationMockMvc;

    private Obligation obligation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Obligation createEntity(EntityManager em) {
        Obligation obligation = new Obligation()
            .oblSysDate(DEFAULT_OBL_SYS_DATE)
            .oblCreatedBy(DEFAULT_OBL_CREATED_BY)
            .oblCustomsOffice(DEFAULT_OBL_CUSTOMS_OFFICE)
            .oblType(DEFAULT_OBL_TYPE)
            .oblAmount(DEFAULT_OBL_AMOUNT)
            .oblLapsed(DEFAULT_OBL_LAPSED)
            .oblVpoNumber(DEFAULT_OBL_VPO_NUMBER)
            .oblTransactionNumber(DEFAULT_OBL_TRANSACTION_NUMBER)
            .oblMraDate(DEFAULT_OBL_MRA_DATE)
            .oblState(DEFAULT_OBL_STATE)
            .oblIdentifier(DEFAULT_OBL_IDENTIFIER)
            .oblAmountDifference(DEFAULT_OBL_AMOUNT_DIFFERENCE)
            .oblGuarantyDifference(DEFAULT_OBL_GUARANTY_DIFFERENCE)
            .oblCustomsOfficeName(DEFAULT_OBL_CUSTOMS_OFFICE_NAME);
        return obligation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Obligation createUpdatedEntity(EntityManager em) {
        Obligation obligation = new Obligation()
            .oblSysDate(UPDATED_OBL_SYS_DATE)
            .oblCreatedBy(UPDATED_OBL_CREATED_BY)
            .oblCustomsOffice(UPDATED_OBL_CUSTOMS_OFFICE)
            .oblType(UPDATED_OBL_TYPE)
            .oblAmount(UPDATED_OBL_AMOUNT)
            .oblLapsed(UPDATED_OBL_LAPSED)
            .oblVpoNumber(UPDATED_OBL_VPO_NUMBER)
            .oblTransactionNumber(UPDATED_OBL_TRANSACTION_NUMBER)
            .oblMraDate(UPDATED_OBL_MRA_DATE)
            .oblState(UPDATED_OBL_STATE)
            .oblIdentifier(UPDATED_OBL_IDENTIFIER)
            .oblAmountDifference(UPDATED_OBL_AMOUNT_DIFFERENCE)
            .oblGuarantyDifference(UPDATED_OBL_GUARANTY_DIFFERENCE)
            .oblCustomsOfficeName(UPDATED_OBL_CUSTOMS_OFFICE_NAME);
        return obligation;
    }

    @BeforeEach
    public void initTest() {
        obligation = createEntity(em);
    }

    @Test
    @Transactional
    public void createObligation() throws Exception {
        int databaseSizeBeforeCreate = obligationRepository.findAll().size();
        // Create the Obligation
        restObligationMockMvc.perform(post("/api/obligations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(obligation)))
            .andExpect(status().isCreated());

        // Validate the Obligation in the database
        List<Obligation> obligationList = obligationRepository.findAll();
        assertThat(obligationList).hasSize(databaseSizeBeforeCreate + 1);
        Obligation testObligation = obligationList.get(obligationList.size() - 1);
        assertThat(testObligation.getOblSysDate()).isEqualTo(DEFAULT_OBL_SYS_DATE);
        assertThat(testObligation.getOblCreatedBy()).isEqualTo(DEFAULT_OBL_CREATED_BY);
        assertThat(testObligation.getOblCustomsOffice()).isEqualTo(DEFAULT_OBL_CUSTOMS_OFFICE);
        assertThat(testObligation.getOblType()).isEqualTo(DEFAULT_OBL_TYPE);
        assertThat(testObligation.getOblAmount()).isEqualTo(DEFAULT_OBL_AMOUNT);
        assertThat(testObligation.isOblLapsed()).isEqualTo(DEFAULT_OBL_LAPSED);
        assertThat(testObligation.getOblVpoNumber()).isEqualTo(DEFAULT_OBL_VPO_NUMBER);
        assertThat(testObligation.getOblTransactionNumber()).isEqualTo(DEFAULT_OBL_TRANSACTION_NUMBER);
        assertThat(testObligation.getOblMraDate()).isEqualTo(DEFAULT_OBL_MRA_DATE);
        assertThat(testObligation.getOblState()).isEqualTo(DEFAULT_OBL_STATE);
        assertThat(testObligation.getOblIdentifier()).isEqualTo(DEFAULT_OBL_IDENTIFIER);
        assertThat(testObligation.getOblAmountDifference()).isEqualTo(DEFAULT_OBL_AMOUNT_DIFFERENCE);
        assertThat(testObligation.getOblGuarantyDifference()).isEqualTo(DEFAULT_OBL_GUARANTY_DIFFERENCE);
        assertThat(testObligation.getOblCustomsOfficeName()).isEqualTo(DEFAULT_OBL_CUSTOMS_OFFICE_NAME);
    }

    @Test
    @Transactional
    public void createObligationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = obligationRepository.findAll().size();

        // Create the Obligation with an existing ID
        obligation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restObligationMockMvc.perform(post("/api/obligations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(obligation)))
            .andExpect(status().isBadRequest());

        // Validate the Obligation in the database
        List<Obligation> obligationList = obligationRepository.findAll();
        assertThat(obligationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllObligations() throws Exception {
        // Initialize the database
        obligationRepository.saveAndFlush(obligation);

        // Get all the obligationList
        restObligationMockMvc.perform(get("/api/obligations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(obligation.getId().intValue())))
            .andExpect(jsonPath("$.[*].oblSysDate").value(hasItem(DEFAULT_OBL_SYS_DATE.toString())))
            .andExpect(jsonPath("$.[*].oblCreatedBy").value(hasItem(DEFAULT_OBL_CREATED_BY)))
            .andExpect(jsonPath("$.[*].oblCustomsOffice").value(hasItem(DEFAULT_OBL_CUSTOMS_OFFICE)))
            .andExpect(jsonPath("$.[*].oblType").value(hasItem(DEFAULT_OBL_TYPE)))
            .andExpect(jsonPath("$.[*].oblAmount").value(hasItem(DEFAULT_OBL_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].oblLapsed").value(hasItem(DEFAULT_OBL_LAPSED.booleanValue())))
            .andExpect(jsonPath("$.[*].oblVpoNumber").value(hasItem(DEFAULT_OBL_VPO_NUMBER)))
            .andExpect(jsonPath("$.[*].oblTransactionNumber").value(hasItem(DEFAULT_OBL_TRANSACTION_NUMBER)))
            .andExpect(jsonPath("$.[*].oblMraDate").value(hasItem(DEFAULT_OBL_MRA_DATE.toString())))
            .andExpect(jsonPath("$.[*].oblState").value(hasItem(DEFAULT_OBL_STATE)))
            .andExpect(jsonPath("$.[*].oblIdentifier").value(hasItem(DEFAULT_OBL_IDENTIFIER)))
            .andExpect(jsonPath("$.[*].oblAmountDifference").value(hasItem(DEFAULT_OBL_AMOUNT_DIFFERENCE.intValue())))
            .andExpect(jsonPath("$.[*].oblGuarantyDifference").value(hasItem(DEFAULT_OBL_GUARANTY_DIFFERENCE.intValue())))
            .andExpect(jsonPath("$.[*].oblCustomsOfficeName").value(hasItem(DEFAULT_OBL_CUSTOMS_OFFICE_NAME)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllObligationsWithEagerRelationshipsIsEnabled() throws Exception {
        when(obligationRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restObligationMockMvc.perform(get("/api/obligations?eagerload=true"))
            .andExpect(status().isOk());

        verify(obligationRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllObligationsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(obligationRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restObligationMockMvc.perform(get("/api/obligations?eagerload=true"))
            .andExpect(status().isOk());

        verify(obligationRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getObligation() throws Exception {
        // Initialize the database
        obligationRepository.saveAndFlush(obligation);

        // Get the obligation
        restObligationMockMvc.perform(get("/api/obligations/{id}", obligation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(obligation.getId().intValue()))
            .andExpect(jsonPath("$.oblSysDate").value(DEFAULT_OBL_SYS_DATE.toString()))
            .andExpect(jsonPath("$.oblCreatedBy").value(DEFAULT_OBL_CREATED_BY))
            .andExpect(jsonPath("$.oblCustomsOffice").value(DEFAULT_OBL_CUSTOMS_OFFICE))
            .andExpect(jsonPath("$.oblType").value(DEFAULT_OBL_TYPE))
            .andExpect(jsonPath("$.oblAmount").value(DEFAULT_OBL_AMOUNT.intValue()))
            .andExpect(jsonPath("$.oblLapsed").value(DEFAULT_OBL_LAPSED.booleanValue()))
            .andExpect(jsonPath("$.oblVpoNumber").value(DEFAULT_OBL_VPO_NUMBER))
            .andExpect(jsonPath("$.oblTransactionNumber").value(DEFAULT_OBL_TRANSACTION_NUMBER))
            .andExpect(jsonPath("$.oblMraDate").value(DEFAULT_OBL_MRA_DATE.toString()))
            .andExpect(jsonPath("$.oblState").value(DEFAULT_OBL_STATE))
            .andExpect(jsonPath("$.oblIdentifier").value(DEFAULT_OBL_IDENTIFIER))
            .andExpect(jsonPath("$.oblAmountDifference").value(DEFAULT_OBL_AMOUNT_DIFFERENCE.intValue()))
            .andExpect(jsonPath("$.oblGuarantyDifference").value(DEFAULT_OBL_GUARANTY_DIFFERENCE.intValue()))
            .andExpect(jsonPath("$.oblCustomsOfficeName").value(DEFAULT_OBL_CUSTOMS_OFFICE_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingObligation() throws Exception {
        // Get the obligation
        restObligationMockMvc.perform(get("/api/obligations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateObligation() throws Exception {
        // Initialize the database
        obligationRepository.saveAndFlush(obligation);

        int databaseSizeBeforeUpdate = obligationRepository.findAll().size();

        // Update the obligation
        Obligation updatedObligation = obligationRepository.findById(obligation.getId()).get();
        // Disconnect from session so that the updates on updatedObligation are not directly saved in db
        em.detach(updatedObligation);
        updatedObligation
            .oblSysDate(UPDATED_OBL_SYS_DATE)
            .oblCreatedBy(UPDATED_OBL_CREATED_BY)
            .oblCustomsOffice(UPDATED_OBL_CUSTOMS_OFFICE)
            .oblType(UPDATED_OBL_TYPE)
            .oblAmount(UPDATED_OBL_AMOUNT)
            .oblLapsed(UPDATED_OBL_LAPSED)
            .oblVpoNumber(UPDATED_OBL_VPO_NUMBER)
            .oblTransactionNumber(UPDATED_OBL_TRANSACTION_NUMBER)
            .oblMraDate(UPDATED_OBL_MRA_DATE)
            .oblState(UPDATED_OBL_STATE)
            .oblIdentifier(UPDATED_OBL_IDENTIFIER)
            .oblAmountDifference(UPDATED_OBL_AMOUNT_DIFFERENCE)
            .oblGuarantyDifference(UPDATED_OBL_GUARANTY_DIFFERENCE)
            .oblCustomsOfficeName(UPDATED_OBL_CUSTOMS_OFFICE_NAME);

        restObligationMockMvc.perform(put("/api/obligations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedObligation)))
            .andExpect(status().isOk());

        // Validate the Obligation in the database
        List<Obligation> obligationList = obligationRepository.findAll();
        assertThat(obligationList).hasSize(databaseSizeBeforeUpdate);
        Obligation testObligation = obligationList.get(obligationList.size() - 1);
        assertThat(testObligation.getOblSysDate()).isEqualTo(UPDATED_OBL_SYS_DATE);
        assertThat(testObligation.getOblCreatedBy()).isEqualTo(UPDATED_OBL_CREATED_BY);
        assertThat(testObligation.getOblCustomsOffice()).isEqualTo(UPDATED_OBL_CUSTOMS_OFFICE);
        assertThat(testObligation.getOblType()).isEqualTo(UPDATED_OBL_TYPE);
        assertThat(testObligation.getOblAmount()).isEqualTo(UPDATED_OBL_AMOUNT);
        assertThat(testObligation.isOblLapsed()).isEqualTo(UPDATED_OBL_LAPSED);
        assertThat(testObligation.getOblVpoNumber()).isEqualTo(UPDATED_OBL_VPO_NUMBER);
        assertThat(testObligation.getOblTransactionNumber()).isEqualTo(UPDATED_OBL_TRANSACTION_NUMBER);
        assertThat(testObligation.getOblMraDate()).isEqualTo(UPDATED_OBL_MRA_DATE);
        assertThat(testObligation.getOblState()).isEqualTo(UPDATED_OBL_STATE);
        assertThat(testObligation.getOblIdentifier()).isEqualTo(UPDATED_OBL_IDENTIFIER);
        assertThat(testObligation.getOblAmountDifference()).isEqualTo(UPDATED_OBL_AMOUNT_DIFFERENCE);
        assertThat(testObligation.getOblGuarantyDifference()).isEqualTo(UPDATED_OBL_GUARANTY_DIFFERENCE);
        assertThat(testObligation.getOblCustomsOfficeName()).isEqualTo(UPDATED_OBL_CUSTOMS_OFFICE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingObligation() throws Exception {
        int databaseSizeBeforeUpdate = obligationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObligationMockMvc.perform(put("/api/obligations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(obligation)))
            .andExpect(status().isBadRequest());

        // Validate the Obligation in the database
        List<Obligation> obligationList = obligationRepository.findAll();
        assertThat(obligationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteObligation() throws Exception {
        // Initialize the database
        obligationRepository.saveAndFlush(obligation);

        int databaseSizeBeforeDelete = obligationRepository.findAll().size();

        // Delete the obligation
        restObligationMockMvc.perform(delete("/api/obligations/{id}", obligation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Obligation> obligationList = obligationRepository.findAll();
        assertThat(obligationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
