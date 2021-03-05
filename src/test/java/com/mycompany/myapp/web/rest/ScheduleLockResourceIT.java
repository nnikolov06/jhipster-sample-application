package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.RedisTestContainerExtension;
import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.ScheduleLock;
import com.mycompany.myapp.repository.ScheduleLockRepository;

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
 * Integration tests for the {@link ScheduleLockResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class ScheduleLockResourceIT {

    private static final String DEFAULT_SLC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SLC_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_SLC_LOCK_UNTIL = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SLC_LOCK_UNTIL = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SLC_LOCKED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SLC_LOCKED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SLC_LOCKED_BY = "AAAAAAAAAA";
    private static final String UPDATED_SLC_LOCKED_BY = "BBBBBBBBBB";

    @Autowired
    private ScheduleLockRepository scheduleLockRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restScheduleLockMockMvc;

    private ScheduleLock scheduleLock;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ScheduleLock createEntity(EntityManager em) {
        ScheduleLock scheduleLock = new ScheduleLock()
            .slcName(DEFAULT_SLC_NAME)
            .slcLockUntil(DEFAULT_SLC_LOCK_UNTIL)
            .slcLockedAt(DEFAULT_SLC_LOCKED_AT)
            .slcLockedBy(DEFAULT_SLC_LOCKED_BY);
        return scheduleLock;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ScheduleLock createUpdatedEntity(EntityManager em) {
        ScheduleLock scheduleLock = new ScheduleLock()
            .slcName(UPDATED_SLC_NAME)
            .slcLockUntil(UPDATED_SLC_LOCK_UNTIL)
            .slcLockedAt(UPDATED_SLC_LOCKED_AT)
            .slcLockedBy(UPDATED_SLC_LOCKED_BY);
        return scheduleLock;
    }

    @BeforeEach
    public void initTest() {
        scheduleLock = createEntity(em);
    }

    @Test
    @Transactional
    public void createScheduleLock() throws Exception {
        int databaseSizeBeforeCreate = scheduleLockRepository.findAll().size();
        // Create the ScheduleLock
        restScheduleLockMockMvc.perform(post("/api/schedule-locks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(scheduleLock)))
            .andExpect(status().isCreated());

        // Validate the ScheduleLock in the database
        List<ScheduleLock> scheduleLockList = scheduleLockRepository.findAll();
        assertThat(scheduleLockList).hasSize(databaseSizeBeforeCreate + 1);
        ScheduleLock testScheduleLock = scheduleLockList.get(scheduleLockList.size() - 1);
        assertThat(testScheduleLock.getSlcName()).isEqualTo(DEFAULT_SLC_NAME);
        assertThat(testScheduleLock.getSlcLockUntil()).isEqualTo(DEFAULT_SLC_LOCK_UNTIL);
        assertThat(testScheduleLock.getSlcLockedAt()).isEqualTo(DEFAULT_SLC_LOCKED_AT);
        assertThat(testScheduleLock.getSlcLockedBy()).isEqualTo(DEFAULT_SLC_LOCKED_BY);
    }

    @Test
    @Transactional
    public void createScheduleLockWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = scheduleLockRepository.findAll().size();

        // Create the ScheduleLock with an existing ID
        scheduleLock.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restScheduleLockMockMvc.perform(post("/api/schedule-locks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(scheduleLock)))
            .andExpect(status().isBadRequest());

        // Validate the ScheduleLock in the database
        List<ScheduleLock> scheduleLockList = scheduleLockRepository.findAll();
        assertThat(scheduleLockList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllScheduleLocks() throws Exception {
        // Initialize the database
        scheduleLockRepository.saveAndFlush(scheduleLock);

        // Get all the scheduleLockList
        restScheduleLockMockMvc.perform(get("/api/schedule-locks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(scheduleLock.getId().intValue())))
            .andExpect(jsonPath("$.[*].slcName").value(hasItem(DEFAULT_SLC_NAME)))
            .andExpect(jsonPath("$.[*].slcLockUntil").value(hasItem(DEFAULT_SLC_LOCK_UNTIL.toString())))
            .andExpect(jsonPath("$.[*].slcLockedAt").value(hasItem(DEFAULT_SLC_LOCKED_AT.toString())))
            .andExpect(jsonPath("$.[*].slcLockedBy").value(hasItem(DEFAULT_SLC_LOCKED_BY)));
    }
    
    @Test
    @Transactional
    public void getScheduleLock() throws Exception {
        // Initialize the database
        scheduleLockRepository.saveAndFlush(scheduleLock);

        // Get the scheduleLock
        restScheduleLockMockMvc.perform(get("/api/schedule-locks/{id}", scheduleLock.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(scheduleLock.getId().intValue()))
            .andExpect(jsonPath("$.slcName").value(DEFAULT_SLC_NAME))
            .andExpect(jsonPath("$.slcLockUntil").value(DEFAULT_SLC_LOCK_UNTIL.toString()))
            .andExpect(jsonPath("$.slcLockedAt").value(DEFAULT_SLC_LOCKED_AT.toString()))
            .andExpect(jsonPath("$.slcLockedBy").value(DEFAULT_SLC_LOCKED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingScheduleLock() throws Exception {
        // Get the scheduleLock
        restScheduleLockMockMvc.perform(get("/api/schedule-locks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateScheduleLock() throws Exception {
        // Initialize the database
        scheduleLockRepository.saveAndFlush(scheduleLock);

        int databaseSizeBeforeUpdate = scheduleLockRepository.findAll().size();

        // Update the scheduleLock
        ScheduleLock updatedScheduleLock = scheduleLockRepository.findById(scheduleLock.getId()).get();
        // Disconnect from session so that the updates on updatedScheduleLock are not directly saved in db
        em.detach(updatedScheduleLock);
        updatedScheduleLock
            .slcName(UPDATED_SLC_NAME)
            .slcLockUntil(UPDATED_SLC_LOCK_UNTIL)
            .slcLockedAt(UPDATED_SLC_LOCKED_AT)
            .slcLockedBy(UPDATED_SLC_LOCKED_BY);

        restScheduleLockMockMvc.perform(put("/api/schedule-locks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedScheduleLock)))
            .andExpect(status().isOk());

        // Validate the ScheduleLock in the database
        List<ScheduleLock> scheduleLockList = scheduleLockRepository.findAll();
        assertThat(scheduleLockList).hasSize(databaseSizeBeforeUpdate);
        ScheduleLock testScheduleLock = scheduleLockList.get(scheduleLockList.size() - 1);
        assertThat(testScheduleLock.getSlcName()).isEqualTo(UPDATED_SLC_NAME);
        assertThat(testScheduleLock.getSlcLockUntil()).isEqualTo(UPDATED_SLC_LOCK_UNTIL);
        assertThat(testScheduleLock.getSlcLockedAt()).isEqualTo(UPDATED_SLC_LOCKED_AT);
        assertThat(testScheduleLock.getSlcLockedBy()).isEqualTo(UPDATED_SLC_LOCKED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingScheduleLock() throws Exception {
        int databaseSizeBeforeUpdate = scheduleLockRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restScheduleLockMockMvc.perform(put("/api/schedule-locks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(scheduleLock)))
            .andExpect(status().isBadRequest());

        // Validate the ScheduleLock in the database
        List<ScheduleLock> scheduleLockList = scheduleLockRepository.findAll();
        assertThat(scheduleLockList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteScheduleLock() throws Exception {
        // Initialize the database
        scheduleLockRepository.saveAndFlush(scheduleLock);

        int databaseSizeBeforeDelete = scheduleLockRepository.findAll().size();

        // Delete the scheduleLock
        restScheduleLockMockMvc.perform(delete("/api/schedule-locks/{id}", scheduleLock.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ScheduleLock> scheduleLockList = scheduleLockRepository.findAll();
        assertThat(scheduleLockList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
