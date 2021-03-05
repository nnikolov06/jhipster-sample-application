package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.RedisTestContainerExtension;
import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.ModuleConfiguration;
import com.mycompany.myapp.repository.ModuleConfigurationRepository;

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
 * Integration tests for the {@link ModuleConfigurationResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class ModuleConfigurationResourceIT {

    private static final String DEFAULT_MOC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOC_NAME = "BBBBBBBBBB";

    @Autowired
    private ModuleConfigurationRepository moduleConfigurationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restModuleConfigurationMockMvc;

    private ModuleConfiguration moduleConfiguration;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModuleConfiguration createEntity(EntityManager em) {
        ModuleConfiguration moduleConfiguration = new ModuleConfiguration()
            .mocName(DEFAULT_MOC_NAME);
        return moduleConfiguration;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModuleConfiguration createUpdatedEntity(EntityManager em) {
        ModuleConfiguration moduleConfiguration = new ModuleConfiguration()
            .mocName(UPDATED_MOC_NAME);
        return moduleConfiguration;
    }

    @BeforeEach
    public void initTest() {
        moduleConfiguration = createEntity(em);
    }

    @Test
    @Transactional
    public void createModuleConfiguration() throws Exception {
        int databaseSizeBeforeCreate = moduleConfigurationRepository.findAll().size();
        // Create the ModuleConfiguration
        restModuleConfigurationMockMvc.perform(post("/api/module-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(moduleConfiguration)))
            .andExpect(status().isCreated());

        // Validate the ModuleConfiguration in the database
        List<ModuleConfiguration> moduleConfigurationList = moduleConfigurationRepository.findAll();
        assertThat(moduleConfigurationList).hasSize(databaseSizeBeforeCreate + 1);
        ModuleConfiguration testModuleConfiguration = moduleConfigurationList.get(moduleConfigurationList.size() - 1);
        assertThat(testModuleConfiguration.getMocName()).isEqualTo(DEFAULT_MOC_NAME);
    }

    @Test
    @Transactional
    public void createModuleConfigurationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = moduleConfigurationRepository.findAll().size();

        // Create the ModuleConfiguration with an existing ID
        moduleConfiguration.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModuleConfigurationMockMvc.perform(post("/api/module-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(moduleConfiguration)))
            .andExpect(status().isBadRequest());

        // Validate the ModuleConfiguration in the database
        List<ModuleConfiguration> moduleConfigurationList = moduleConfigurationRepository.findAll();
        assertThat(moduleConfigurationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllModuleConfigurations() throws Exception {
        // Initialize the database
        moduleConfigurationRepository.saveAndFlush(moduleConfiguration);

        // Get all the moduleConfigurationList
        restModuleConfigurationMockMvc.perform(get("/api/module-configurations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(moduleConfiguration.getId().intValue())))
            .andExpect(jsonPath("$.[*].mocName").value(hasItem(DEFAULT_MOC_NAME)));
    }
    
    @Test
    @Transactional
    public void getModuleConfiguration() throws Exception {
        // Initialize the database
        moduleConfigurationRepository.saveAndFlush(moduleConfiguration);

        // Get the moduleConfiguration
        restModuleConfigurationMockMvc.perform(get("/api/module-configurations/{id}", moduleConfiguration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(moduleConfiguration.getId().intValue()))
            .andExpect(jsonPath("$.mocName").value(DEFAULT_MOC_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingModuleConfiguration() throws Exception {
        // Get the moduleConfiguration
        restModuleConfigurationMockMvc.perform(get("/api/module-configurations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModuleConfiguration() throws Exception {
        // Initialize the database
        moduleConfigurationRepository.saveAndFlush(moduleConfiguration);

        int databaseSizeBeforeUpdate = moduleConfigurationRepository.findAll().size();

        // Update the moduleConfiguration
        ModuleConfiguration updatedModuleConfiguration = moduleConfigurationRepository.findById(moduleConfiguration.getId()).get();
        // Disconnect from session so that the updates on updatedModuleConfiguration are not directly saved in db
        em.detach(updatedModuleConfiguration);
        updatedModuleConfiguration
            .mocName(UPDATED_MOC_NAME);

        restModuleConfigurationMockMvc.perform(put("/api/module-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedModuleConfiguration)))
            .andExpect(status().isOk());

        // Validate the ModuleConfiguration in the database
        List<ModuleConfiguration> moduleConfigurationList = moduleConfigurationRepository.findAll();
        assertThat(moduleConfigurationList).hasSize(databaseSizeBeforeUpdate);
        ModuleConfiguration testModuleConfiguration = moduleConfigurationList.get(moduleConfigurationList.size() - 1);
        assertThat(testModuleConfiguration.getMocName()).isEqualTo(UPDATED_MOC_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingModuleConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = moduleConfigurationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModuleConfigurationMockMvc.perform(put("/api/module-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(moduleConfiguration)))
            .andExpect(status().isBadRequest());

        // Validate the ModuleConfiguration in the database
        List<ModuleConfiguration> moduleConfigurationList = moduleConfigurationRepository.findAll();
        assertThat(moduleConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteModuleConfiguration() throws Exception {
        // Initialize the database
        moduleConfigurationRepository.saveAndFlush(moduleConfiguration);

        int databaseSizeBeforeDelete = moduleConfigurationRepository.findAll().size();

        // Delete the moduleConfiguration
        restModuleConfigurationMockMvc.perform(delete("/api/module-configurations/{id}", moduleConfiguration.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ModuleConfiguration> moduleConfigurationList = moduleConfigurationRepository.findAll();
        assertThat(moduleConfigurationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
