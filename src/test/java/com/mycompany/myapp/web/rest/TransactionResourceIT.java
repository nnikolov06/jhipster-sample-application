package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.RedisTestContainerExtension;
import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Transaction;
import com.mycompany.myapp.repository.TransactionRepository;

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
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TransactionResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class TransactionResourceIT {

    private static final String DEFAULT_TRN_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TRN_NUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_TRN_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRN_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TRN_SYSTEM = "AAAAAAAAAA";
    private static final String UPDATED_TRN_SYSTEM = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_TRN_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_TRN_AMOUNT = new BigDecimal(2);

    private static final Instant DEFAULT_TRN_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRN_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_TRN_SYS_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRN_SYS_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TRN_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_TRN_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_TRN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TRN_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TRN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TRN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TRN_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TRN_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_TRN_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_TRN_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TRN_PAYMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TRN_PAYMENT_TYPE = "BBBBBBBBBB";

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransactionMockMvc;

    private Transaction transaction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transaction createEntity(EntityManager em) {
        Transaction transaction = new Transaction()
            .trnNumber(DEFAULT_TRN_NUMBER)
            .trnDate(DEFAULT_TRN_DATE)
            .trnSystem(DEFAULT_TRN_SYSTEM)
            .trnAmount(DEFAULT_TRN_AMOUNT)
            .trnCreationDate(DEFAULT_TRN_CREATION_DATE)
            .trnSysDate(DEFAULT_TRN_SYS_DATE)
            .trnCreatedBy(DEFAULT_TRN_CREATED_BY)
            .trnType(DEFAULT_TRN_TYPE)
            .trnCode(DEFAULT_TRN_CODE)
            .trnStatus(DEFAULT_TRN_STATUS)
            .trnDescription(DEFAULT_TRN_DESCRIPTION)
            .trnPaymentType(DEFAULT_TRN_PAYMENT_TYPE);
        return transaction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transaction createUpdatedEntity(EntityManager em) {
        Transaction transaction = new Transaction()
            .trnNumber(UPDATED_TRN_NUMBER)
            .trnDate(UPDATED_TRN_DATE)
            .trnSystem(UPDATED_TRN_SYSTEM)
            .trnAmount(UPDATED_TRN_AMOUNT)
            .trnCreationDate(UPDATED_TRN_CREATION_DATE)
            .trnSysDate(UPDATED_TRN_SYS_DATE)
            .trnCreatedBy(UPDATED_TRN_CREATED_BY)
            .trnType(UPDATED_TRN_TYPE)
            .trnCode(UPDATED_TRN_CODE)
            .trnStatus(UPDATED_TRN_STATUS)
            .trnDescription(UPDATED_TRN_DESCRIPTION)
            .trnPaymentType(UPDATED_TRN_PAYMENT_TYPE);
        return transaction;
    }

    @BeforeEach
    public void initTest() {
        transaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransaction() throws Exception {
        int databaseSizeBeforeCreate = transactionRepository.findAll().size();
        // Create the Transaction
        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isCreated());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeCreate + 1);
        Transaction testTransaction = transactionList.get(transactionList.size() - 1);
        assertThat(testTransaction.getTrnNumber()).isEqualTo(DEFAULT_TRN_NUMBER);
        assertThat(testTransaction.getTrnDate()).isEqualTo(DEFAULT_TRN_DATE);
        assertThat(testTransaction.getTrnSystem()).isEqualTo(DEFAULT_TRN_SYSTEM);
        assertThat(testTransaction.getTrnAmount()).isEqualTo(DEFAULT_TRN_AMOUNT);
        assertThat(testTransaction.getTrnCreationDate()).isEqualTo(DEFAULT_TRN_CREATION_DATE);
        assertThat(testTransaction.getTrnSysDate()).isEqualTo(DEFAULT_TRN_SYS_DATE);
        assertThat(testTransaction.getTrnCreatedBy()).isEqualTo(DEFAULT_TRN_CREATED_BY);
        assertThat(testTransaction.getTrnType()).isEqualTo(DEFAULT_TRN_TYPE);
        assertThat(testTransaction.getTrnCode()).isEqualTo(DEFAULT_TRN_CODE);
        assertThat(testTransaction.getTrnStatus()).isEqualTo(DEFAULT_TRN_STATUS);
        assertThat(testTransaction.getTrnDescription()).isEqualTo(DEFAULT_TRN_DESCRIPTION);
        assertThat(testTransaction.getTrnPaymentType()).isEqualTo(DEFAULT_TRN_PAYMENT_TYPE);
    }

    @Test
    @Transactional
    public void createTransactionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transactionRepository.findAll().size();

        // Create the Transaction with an existing ID
        transaction.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isBadRequest());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTransactions() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        // Get all the transactionList
        restTransactionMockMvc.perform(get("/api/transactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].trnNumber").value(hasItem(DEFAULT_TRN_NUMBER)))
            .andExpect(jsonPath("$.[*].trnDate").value(hasItem(DEFAULT_TRN_DATE.toString())))
            .andExpect(jsonPath("$.[*].trnSystem").value(hasItem(DEFAULT_TRN_SYSTEM)))
            .andExpect(jsonPath("$.[*].trnAmount").value(hasItem(DEFAULT_TRN_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].trnCreationDate").value(hasItem(DEFAULT_TRN_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].trnSysDate").value(hasItem(DEFAULT_TRN_SYS_DATE.toString())))
            .andExpect(jsonPath("$.[*].trnCreatedBy").value(hasItem(DEFAULT_TRN_CREATED_BY)))
            .andExpect(jsonPath("$.[*].trnType").value(hasItem(DEFAULT_TRN_TYPE)))
            .andExpect(jsonPath("$.[*].trnCode").value(hasItem(DEFAULT_TRN_CODE)))
            .andExpect(jsonPath("$.[*].trnStatus").value(hasItem(DEFAULT_TRN_STATUS)))
            .andExpect(jsonPath("$.[*].trnDescription").value(hasItem(DEFAULT_TRN_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].trnPaymentType").value(hasItem(DEFAULT_TRN_PAYMENT_TYPE)));
    }
    
    @Test
    @Transactional
    public void getTransaction() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        // Get the transaction
        restTransactionMockMvc.perform(get("/api/transactions/{id}", transaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transaction.getId().intValue()))
            .andExpect(jsonPath("$.trnNumber").value(DEFAULT_TRN_NUMBER))
            .andExpect(jsonPath("$.trnDate").value(DEFAULT_TRN_DATE.toString()))
            .andExpect(jsonPath("$.trnSystem").value(DEFAULT_TRN_SYSTEM))
            .andExpect(jsonPath("$.trnAmount").value(DEFAULT_TRN_AMOUNT.intValue()))
            .andExpect(jsonPath("$.trnCreationDate").value(DEFAULT_TRN_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.trnSysDate").value(DEFAULT_TRN_SYS_DATE.toString()))
            .andExpect(jsonPath("$.trnCreatedBy").value(DEFAULT_TRN_CREATED_BY))
            .andExpect(jsonPath("$.trnType").value(DEFAULT_TRN_TYPE))
            .andExpect(jsonPath("$.trnCode").value(DEFAULT_TRN_CODE))
            .andExpect(jsonPath("$.trnStatus").value(DEFAULT_TRN_STATUS))
            .andExpect(jsonPath("$.trnDescription").value(DEFAULT_TRN_DESCRIPTION))
            .andExpect(jsonPath("$.trnPaymentType").value(DEFAULT_TRN_PAYMENT_TYPE));
    }
    @Test
    @Transactional
    public void getNonExistingTransaction() throws Exception {
        // Get the transaction
        restTransactionMockMvc.perform(get("/api/transactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransaction() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();

        // Update the transaction
        Transaction updatedTransaction = transactionRepository.findById(transaction.getId()).get();
        // Disconnect from session so that the updates on updatedTransaction are not directly saved in db
        em.detach(updatedTransaction);
        updatedTransaction
            .trnNumber(UPDATED_TRN_NUMBER)
            .trnDate(UPDATED_TRN_DATE)
            .trnSystem(UPDATED_TRN_SYSTEM)
            .trnAmount(UPDATED_TRN_AMOUNT)
            .trnCreationDate(UPDATED_TRN_CREATION_DATE)
            .trnSysDate(UPDATED_TRN_SYS_DATE)
            .trnCreatedBy(UPDATED_TRN_CREATED_BY)
            .trnType(UPDATED_TRN_TYPE)
            .trnCode(UPDATED_TRN_CODE)
            .trnStatus(UPDATED_TRN_STATUS)
            .trnDescription(UPDATED_TRN_DESCRIPTION)
            .trnPaymentType(UPDATED_TRN_PAYMENT_TYPE);

        restTransactionMockMvc.perform(put("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransaction)))
            .andExpect(status().isOk());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeUpdate);
        Transaction testTransaction = transactionList.get(transactionList.size() - 1);
        assertThat(testTransaction.getTrnNumber()).isEqualTo(UPDATED_TRN_NUMBER);
        assertThat(testTransaction.getTrnDate()).isEqualTo(UPDATED_TRN_DATE);
        assertThat(testTransaction.getTrnSystem()).isEqualTo(UPDATED_TRN_SYSTEM);
        assertThat(testTransaction.getTrnAmount()).isEqualTo(UPDATED_TRN_AMOUNT);
        assertThat(testTransaction.getTrnCreationDate()).isEqualTo(UPDATED_TRN_CREATION_DATE);
        assertThat(testTransaction.getTrnSysDate()).isEqualTo(UPDATED_TRN_SYS_DATE);
        assertThat(testTransaction.getTrnCreatedBy()).isEqualTo(UPDATED_TRN_CREATED_BY);
        assertThat(testTransaction.getTrnType()).isEqualTo(UPDATED_TRN_TYPE);
        assertThat(testTransaction.getTrnCode()).isEqualTo(UPDATED_TRN_CODE);
        assertThat(testTransaction.getTrnStatus()).isEqualTo(UPDATED_TRN_STATUS);
        assertThat(testTransaction.getTrnDescription()).isEqualTo(UPDATED_TRN_DESCRIPTION);
        assertThat(testTransaction.getTrnPaymentType()).isEqualTo(UPDATED_TRN_PAYMENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingTransaction() throws Exception {
        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionMockMvc.perform(put("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isBadRequest());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransaction() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        int databaseSizeBeforeDelete = transactionRepository.findAll().size();

        // Delete the transaction
        restTransactionMockMvc.perform(delete("/api/transactions/{id}", transaction.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
