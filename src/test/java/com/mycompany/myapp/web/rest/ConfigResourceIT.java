package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.RedisTestContainerExtension;
import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Config;
import com.mycompany.myapp.repository.ConfigRepository;

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
 * Integration tests for the {@link ConfigResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class ConfigResourceIT {

    private static final String DEFAULT_CFG_SECTION = "AAAAAAAAAA";
    private static final String UPDATED_CFG_SECTION = "BBBBBBBBBB";

    private static final String DEFAULT_CFG_ENVIRONMNENT = "AAAAAAAAAA";
    private static final String UPDATED_CFG_ENVIRONMNENT = "BBBBBBBBBB";

    private static final String DEFAULT_CFG_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_CFG_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_CFG_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_CFG_VALUE = "BBBBBBBBBB";

    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restConfigMockMvc;

    private Config config;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Config createEntity(EntityManager em) {
        Config config = new Config()
            .cfgSection(DEFAULT_CFG_SECTION)
            .cfgEnvironmnent(DEFAULT_CFG_ENVIRONMNENT)
            .cfgItem(DEFAULT_CFG_ITEM)
            .cfgValue(DEFAULT_CFG_VALUE);
        return config;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Config createUpdatedEntity(EntityManager em) {
        Config config = new Config()
            .cfgSection(UPDATED_CFG_SECTION)
            .cfgEnvironmnent(UPDATED_CFG_ENVIRONMNENT)
            .cfgItem(UPDATED_CFG_ITEM)
            .cfgValue(UPDATED_CFG_VALUE);
        return config;
    }

    @BeforeEach
    public void initTest() {
        config = createEntity(em);
    }

    @Test
    @Transactional
    public void createConfig() throws Exception {
        int databaseSizeBeforeCreate = configRepository.findAll().size();
        // Create the Config
        restConfigMockMvc.perform(post("/api/configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(config)))
            .andExpect(status().isCreated());

        // Validate the Config in the database
        List<Config> configList = configRepository.findAll();
        assertThat(configList).hasSize(databaseSizeBeforeCreate + 1);
        Config testConfig = configList.get(configList.size() - 1);
        assertThat(testConfig.getCfgSection()).isEqualTo(DEFAULT_CFG_SECTION);
        assertThat(testConfig.getCfgEnvironmnent()).isEqualTo(DEFAULT_CFG_ENVIRONMNENT);
        assertThat(testConfig.getCfgItem()).isEqualTo(DEFAULT_CFG_ITEM);
        assertThat(testConfig.getCfgValue()).isEqualTo(DEFAULT_CFG_VALUE);
    }

    @Test
    @Transactional
    public void createConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = configRepository.findAll().size();

        // Create the Config with an existing ID
        config.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConfigMockMvc.perform(post("/api/configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(config)))
            .andExpect(status().isBadRequest());

        // Validate the Config in the database
        List<Config> configList = configRepository.findAll();
        assertThat(configList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllConfigs() throws Exception {
        // Initialize the database
        configRepository.saveAndFlush(config);

        // Get all the configList
        restConfigMockMvc.perform(get("/api/configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(config.getId().intValue())))
            .andExpect(jsonPath("$.[*].cfgSection").value(hasItem(DEFAULT_CFG_SECTION)))
            .andExpect(jsonPath("$.[*].cfgEnvironmnent").value(hasItem(DEFAULT_CFG_ENVIRONMNENT)))
            .andExpect(jsonPath("$.[*].cfgItem").value(hasItem(DEFAULT_CFG_ITEM)))
            .andExpect(jsonPath("$.[*].cfgValue").value(hasItem(DEFAULT_CFG_VALUE)));
    }
    
    @Test
    @Transactional
    public void getConfig() throws Exception {
        // Initialize the database
        configRepository.saveAndFlush(config);

        // Get the config
        restConfigMockMvc.perform(get("/api/configs/{id}", config.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(config.getId().intValue()))
            .andExpect(jsonPath("$.cfgSection").value(DEFAULT_CFG_SECTION))
            .andExpect(jsonPath("$.cfgEnvironmnent").value(DEFAULT_CFG_ENVIRONMNENT))
            .andExpect(jsonPath("$.cfgItem").value(DEFAULT_CFG_ITEM))
            .andExpect(jsonPath("$.cfgValue").value(DEFAULT_CFG_VALUE));
    }
    @Test
    @Transactional
    public void getNonExistingConfig() throws Exception {
        // Get the config
        restConfigMockMvc.perform(get("/api/configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConfig() throws Exception {
        // Initialize the database
        configRepository.saveAndFlush(config);

        int databaseSizeBeforeUpdate = configRepository.findAll().size();

        // Update the config
        Config updatedConfig = configRepository.findById(config.getId()).get();
        // Disconnect from session so that the updates on updatedConfig are not directly saved in db
        em.detach(updatedConfig);
        updatedConfig
            .cfgSection(UPDATED_CFG_SECTION)
            .cfgEnvironmnent(UPDATED_CFG_ENVIRONMNENT)
            .cfgItem(UPDATED_CFG_ITEM)
            .cfgValue(UPDATED_CFG_VALUE);

        restConfigMockMvc.perform(put("/api/configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedConfig)))
            .andExpect(status().isOk());

        // Validate the Config in the database
        List<Config> configList = configRepository.findAll();
        assertThat(configList).hasSize(databaseSizeBeforeUpdate);
        Config testConfig = configList.get(configList.size() - 1);
        assertThat(testConfig.getCfgSection()).isEqualTo(UPDATED_CFG_SECTION);
        assertThat(testConfig.getCfgEnvironmnent()).isEqualTo(UPDATED_CFG_ENVIRONMNENT);
        assertThat(testConfig.getCfgItem()).isEqualTo(UPDATED_CFG_ITEM);
        assertThat(testConfig.getCfgValue()).isEqualTo(UPDATED_CFG_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingConfig() throws Exception {
        int databaseSizeBeforeUpdate = configRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConfigMockMvc.perform(put("/api/configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(config)))
            .andExpect(status().isBadRequest());

        // Validate the Config in the database
        List<Config> configList = configRepository.findAll();
        assertThat(configList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConfig() throws Exception {
        // Initialize the database
        configRepository.saveAndFlush(config);

        int databaseSizeBeforeDelete = configRepository.findAll().size();

        // Delete the config
        restConfigMockMvc.perform(delete("/api/configs/{id}", config.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Config> configList = configRepository.findAll();
        assertThat(configList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
