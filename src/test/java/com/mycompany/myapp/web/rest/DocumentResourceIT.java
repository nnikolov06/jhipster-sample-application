package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.RedisTestContainerExtension;
import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Document;
import com.mycompany.myapp.repository.DocumentRepository;

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
 * Integration tests for the {@link DocumentResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class DocumentResourceIT {

    private static final String DEFAULT_DOC_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DOC_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DOC_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOC_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DOC_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DOC_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DOC_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DOC_TYPE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DOC_SYS_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DOC_SYS_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DOC_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_DOC_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_DOC_MRN = "AAAAAAAAAA";
    private static final String UPDATED_DOC_MRN = "BBBBBBBBBB";

    private static final String DEFAULT_DOC_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DOC_STATUS = "BBBBBBBBBB";

    private static final Instant DEFAULT_DOC_LAST_CHANGE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DOC_LAST_CHANGE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_DOC_INTO_FORCE = false;
    private static final Boolean UPDATED_DOC_INTO_FORCE = true;

    private static final Boolean DEFAULT_DOC_DEFERRED = false;
    private static final Boolean UPDATED_DOC_DEFERRED = true;

    private static final Boolean DEFAULT_DOC_RESCHEDULED = false;
    private static final Boolean UPDATED_DOC_RESCHEDULED = true;

    private static final LocalDate DEFAULT_DOC_INTO_FORCE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOC_INTO_FORCE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DOC_DEFERRED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOC_DEFERRED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DOC_RESCHEDULED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOC_RESCHEDULED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDocumentMockMvc;

    private Document document;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Document createEntity(EntityManager em) {
        Document document = new Document()
            .docNumber(DEFAULT_DOC_NUMBER)
            .docDate(DEFAULT_DOC_DATE)
            .docDescription(DEFAULT_DOC_DESCRIPTION)
            .docType(DEFAULT_DOC_TYPE)
            .docSysDate(DEFAULT_DOC_SYS_DATE)
            .docCreatedBy(DEFAULT_DOC_CREATED_BY)
            .docMrn(DEFAULT_DOC_MRN)
            .docStatus(DEFAULT_DOC_STATUS)
            .docLastChangeDate(DEFAULT_DOC_LAST_CHANGE_DATE)
            .docIntoForce(DEFAULT_DOC_INTO_FORCE)
            .docDeferred(DEFAULT_DOC_DEFERRED)
            .docRescheduled(DEFAULT_DOC_RESCHEDULED)
            .docIntoForceDate(DEFAULT_DOC_INTO_FORCE_DATE)
            .docDeferredDate(DEFAULT_DOC_DEFERRED_DATE)
            .docRescheduledDate(DEFAULT_DOC_RESCHEDULED_DATE);
        return document;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Document createUpdatedEntity(EntityManager em) {
        Document document = new Document()
            .docNumber(UPDATED_DOC_NUMBER)
            .docDate(UPDATED_DOC_DATE)
            .docDescription(UPDATED_DOC_DESCRIPTION)
            .docType(UPDATED_DOC_TYPE)
            .docSysDate(UPDATED_DOC_SYS_DATE)
            .docCreatedBy(UPDATED_DOC_CREATED_BY)
            .docMrn(UPDATED_DOC_MRN)
            .docStatus(UPDATED_DOC_STATUS)
            .docLastChangeDate(UPDATED_DOC_LAST_CHANGE_DATE)
            .docIntoForce(UPDATED_DOC_INTO_FORCE)
            .docDeferred(UPDATED_DOC_DEFERRED)
            .docRescheduled(UPDATED_DOC_RESCHEDULED)
            .docIntoForceDate(UPDATED_DOC_INTO_FORCE_DATE)
            .docDeferredDate(UPDATED_DOC_DEFERRED_DATE)
            .docRescheduledDate(UPDATED_DOC_RESCHEDULED_DATE);
        return document;
    }

    @BeforeEach
    public void initTest() {
        document = createEntity(em);
    }

    @Test
    @Transactional
    public void createDocument() throws Exception {
        int databaseSizeBeforeCreate = documentRepository.findAll().size();
        // Create the Document
        restDocumentMockMvc.perform(post("/api/documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(document)))
            .andExpect(status().isCreated());

        // Validate the Document in the database
        List<Document> documentList = documentRepository.findAll();
        assertThat(documentList).hasSize(databaseSizeBeforeCreate + 1);
        Document testDocument = documentList.get(documentList.size() - 1);
        assertThat(testDocument.getDocNumber()).isEqualTo(DEFAULT_DOC_NUMBER);
        assertThat(testDocument.getDocDate()).isEqualTo(DEFAULT_DOC_DATE);
        assertThat(testDocument.getDocDescription()).isEqualTo(DEFAULT_DOC_DESCRIPTION);
        assertThat(testDocument.getDocType()).isEqualTo(DEFAULT_DOC_TYPE);
        assertThat(testDocument.getDocSysDate()).isEqualTo(DEFAULT_DOC_SYS_DATE);
        assertThat(testDocument.getDocCreatedBy()).isEqualTo(DEFAULT_DOC_CREATED_BY);
        assertThat(testDocument.getDocMrn()).isEqualTo(DEFAULT_DOC_MRN);
        assertThat(testDocument.getDocStatus()).isEqualTo(DEFAULT_DOC_STATUS);
        assertThat(testDocument.getDocLastChangeDate()).isEqualTo(DEFAULT_DOC_LAST_CHANGE_DATE);
        assertThat(testDocument.isDocIntoForce()).isEqualTo(DEFAULT_DOC_INTO_FORCE);
        assertThat(testDocument.isDocDeferred()).isEqualTo(DEFAULT_DOC_DEFERRED);
        assertThat(testDocument.isDocRescheduled()).isEqualTo(DEFAULT_DOC_RESCHEDULED);
        assertThat(testDocument.getDocIntoForceDate()).isEqualTo(DEFAULT_DOC_INTO_FORCE_DATE);
        assertThat(testDocument.getDocDeferredDate()).isEqualTo(DEFAULT_DOC_DEFERRED_DATE);
        assertThat(testDocument.getDocRescheduledDate()).isEqualTo(DEFAULT_DOC_RESCHEDULED_DATE);
    }

    @Test
    @Transactional
    public void createDocumentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = documentRepository.findAll().size();

        // Create the Document with an existing ID
        document.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDocumentMockMvc.perform(post("/api/documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(document)))
            .andExpect(status().isBadRequest());

        // Validate the Document in the database
        List<Document> documentList = documentRepository.findAll();
        assertThat(documentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDocuments() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList
        restDocumentMockMvc.perform(get("/api/documents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(document.getId().intValue())))
            .andExpect(jsonPath("$.[*].docNumber").value(hasItem(DEFAULT_DOC_NUMBER)))
            .andExpect(jsonPath("$.[*].docDate").value(hasItem(DEFAULT_DOC_DATE.toString())))
            .andExpect(jsonPath("$.[*].docDescription").value(hasItem(DEFAULT_DOC_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].docType").value(hasItem(DEFAULT_DOC_TYPE)))
            .andExpect(jsonPath("$.[*].docSysDate").value(hasItem(DEFAULT_DOC_SYS_DATE.toString())))
            .andExpect(jsonPath("$.[*].docCreatedBy").value(hasItem(DEFAULT_DOC_CREATED_BY)))
            .andExpect(jsonPath("$.[*].docMrn").value(hasItem(DEFAULT_DOC_MRN)))
            .andExpect(jsonPath("$.[*].docStatus").value(hasItem(DEFAULT_DOC_STATUS)))
            .andExpect(jsonPath("$.[*].docLastChangeDate").value(hasItem(DEFAULT_DOC_LAST_CHANGE_DATE.toString())))
            .andExpect(jsonPath("$.[*].docIntoForce").value(hasItem(DEFAULT_DOC_INTO_FORCE.booleanValue())))
            .andExpect(jsonPath("$.[*].docDeferred").value(hasItem(DEFAULT_DOC_DEFERRED.booleanValue())))
            .andExpect(jsonPath("$.[*].docRescheduled").value(hasItem(DEFAULT_DOC_RESCHEDULED.booleanValue())))
            .andExpect(jsonPath("$.[*].docIntoForceDate").value(hasItem(DEFAULT_DOC_INTO_FORCE_DATE.toString())))
            .andExpect(jsonPath("$.[*].docDeferredDate").value(hasItem(DEFAULT_DOC_DEFERRED_DATE.toString())))
            .andExpect(jsonPath("$.[*].docRescheduledDate").value(hasItem(DEFAULT_DOC_RESCHEDULED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getDocument() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get the document
        restDocumentMockMvc.perform(get("/api/documents/{id}", document.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(document.getId().intValue()))
            .andExpect(jsonPath("$.docNumber").value(DEFAULT_DOC_NUMBER))
            .andExpect(jsonPath("$.docDate").value(DEFAULT_DOC_DATE.toString()))
            .andExpect(jsonPath("$.docDescription").value(DEFAULT_DOC_DESCRIPTION))
            .andExpect(jsonPath("$.docType").value(DEFAULT_DOC_TYPE))
            .andExpect(jsonPath("$.docSysDate").value(DEFAULT_DOC_SYS_DATE.toString()))
            .andExpect(jsonPath("$.docCreatedBy").value(DEFAULT_DOC_CREATED_BY))
            .andExpect(jsonPath("$.docMrn").value(DEFAULT_DOC_MRN))
            .andExpect(jsonPath("$.docStatus").value(DEFAULT_DOC_STATUS))
            .andExpect(jsonPath("$.docLastChangeDate").value(DEFAULT_DOC_LAST_CHANGE_DATE.toString()))
            .andExpect(jsonPath("$.docIntoForce").value(DEFAULT_DOC_INTO_FORCE.booleanValue()))
            .andExpect(jsonPath("$.docDeferred").value(DEFAULT_DOC_DEFERRED.booleanValue()))
            .andExpect(jsonPath("$.docRescheduled").value(DEFAULT_DOC_RESCHEDULED.booleanValue()))
            .andExpect(jsonPath("$.docIntoForceDate").value(DEFAULT_DOC_INTO_FORCE_DATE.toString()))
            .andExpect(jsonPath("$.docDeferredDate").value(DEFAULT_DOC_DEFERRED_DATE.toString()))
            .andExpect(jsonPath("$.docRescheduledDate").value(DEFAULT_DOC_RESCHEDULED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDocument() throws Exception {
        // Get the document
        restDocumentMockMvc.perform(get("/api/documents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDocument() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        int databaseSizeBeforeUpdate = documentRepository.findAll().size();

        // Update the document
        Document updatedDocument = documentRepository.findById(document.getId()).get();
        // Disconnect from session so that the updates on updatedDocument are not directly saved in db
        em.detach(updatedDocument);
        updatedDocument
            .docNumber(UPDATED_DOC_NUMBER)
            .docDate(UPDATED_DOC_DATE)
            .docDescription(UPDATED_DOC_DESCRIPTION)
            .docType(UPDATED_DOC_TYPE)
            .docSysDate(UPDATED_DOC_SYS_DATE)
            .docCreatedBy(UPDATED_DOC_CREATED_BY)
            .docMrn(UPDATED_DOC_MRN)
            .docStatus(UPDATED_DOC_STATUS)
            .docLastChangeDate(UPDATED_DOC_LAST_CHANGE_DATE)
            .docIntoForce(UPDATED_DOC_INTO_FORCE)
            .docDeferred(UPDATED_DOC_DEFERRED)
            .docRescheduled(UPDATED_DOC_RESCHEDULED)
            .docIntoForceDate(UPDATED_DOC_INTO_FORCE_DATE)
            .docDeferredDate(UPDATED_DOC_DEFERRED_DATE)
            .docRescheduledDate(UPDATED_DOC_RESCHEDULED_DATE);

        restDocumentMockMvc.perform(put("/api/documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDocument)))
            .andExpect(status().isOk());

        // Validate the Document in the database
        List<Document> documentList = documentRepository.findAll();
        assertThat(documentList).hasSize(databaseSizeBeforeUpdate);
        Document testDocument = documentList.get(documentList.size() - 1);
        assertThat(testDocument.getDocNumber()).isEqualTo(UPDATED_DOC_NUMBER);
        assertThat(testDocument.getDocDate()).isEqualTo(UPDATED_DOC_DATE);
        assertThat(testDocument.getDocDescription()).isEqualTo(UPDATED_DOC_DESCRIPTION);
        assertThat(testDocument.getDocType()).isEqualTo(UPDATED_DOC_TYPE);
        assertThat(testDocument.getDocSysDate()).isEqualTo(UPDATED_DOC_SYS_DATE);
        assertThat(testDocument.getDocCreatedBy()).isEqualTo(UPDATED_DOC_CREATED_BY);
        assertThat(testDocument.getDocMrn()).isEqualTo(UPDATED_DOC_MRN);
        assertThat(testDocument.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
        assertThat(testDocument.getDocLastChangeDate()).isEqualTo(UPDATED_DOC_LAST_CHANGE_DATE);
        assertThat(testDocument.isDocIntoForce()).isEqualTo(UPDATED_DOC_INTO_FORCE);
        assertThat(testDocument.isDocDeferred()).isEqualTo(UPDATED_DOC_DEFERRED);
        assertThat(testDocument.isDocRescheduled()).isEqualTo(UPDATED_DOC_RESCHEDULED);
        assertThat(testDocument.getDocIntoForceDate()).isEqualTo(UPDATED_DOC_INTO_FORCE_DATE);
        assertThat(testDocument.getDocDeferredDate()).isEqualTo(UPDATED_DOC_DEFERRED_DATE);
        assertThat(testDocument.getDocRescheduledDate()).isEqualTo(UPDATED_DOC_RESCHEDULED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingDocument() throws Exception {
        int databaseSizeBeforeUpdate = documentRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentMockMvc.perform(put("/api/documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(document)))
            .andExpect(status().isBadRequest());

        // Validate the Document in the database
        List<Document> documentList = documentRepository.findAll();
        assertThat(documentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDocument() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        int databaseSizeBeforeDelete = documentRepository.findAll().size();

        // Delete the document
        restDocumentMockMvc.perform(delete("/api/documents/{id}", document.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Document> documentList = documentRepository.findAll();
        assertThat(documentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
