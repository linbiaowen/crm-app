package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.Model;
import com.hthk.crm.repository.ModelRepository;
import com.hthk.crm.service.ModelService;
import com.hthk.crm.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.hthk.crm.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.hthk.crm.domain.enumeration.RecordStatus;
/**
 * Integration tests for the {@link ModelResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class ModelResourceIT {

    private static final String DEFAULT_MODEL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MODEL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_MODEL_GROUP = "BBBBBBBBBB";

    private static final RecordStatus DEFAULT_STATUS = RecordStatus.INACTIVE;
    private static final RecordStatus UPDATED_STATUS = RecordStatus.ACTIVE;

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_UPDATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_ID = "BBBBBBBBBB";

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ModelService modelService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restModelMockMvc;

    private Model model;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ModelResource modelResource = new ModelResource(modelService);
        this.restModelMockMvc = MockMvcBuilders.standaloneSetup(modelResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Model createEntity() {
        Model model = new Model()
            .modelCode(DEFAULT_MODEL_CODE)
            .modelGroup(DEFAULT_MODEL_GROUP)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return model;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Model createUpdatedEntity() {
        Model model = new Model()
            .modelCode(UPDATED_MODEL_CODE)
            .modelGroup(UPDATED_MODEL_GROUP)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return model;
    }

    @BeforeEach
    public void initTest() {
        modelRepository.deleteAll();
        model = createEntity();
    }

    @Test
    public void createModel() throws Exception {
        int databaseSizeBeforeCreate = modelRepository.findAll().size();

        // Create the Model
        restModelMockMvc.perform(post("/api/models")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(model)))
            .andExpect(status().isCreated());

        // Validate the Model in the database
        List<Model> modelList = modelRepository.findAll();
        assertThat(modelList).hasSize(databaseSizeBeforeCreate + 1);
        Model testModel = modelList.get(modelList.size() - 1);
        assertThat(testModel.getModelCode()).isEqualTo(DEFAULT_MODEL_CODE);
        assertThat(testModel.getModelGroup()).isEqualTo(DEFAULT_MODEL_GROUP);
        assertThat(testModel.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testModel.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testModel.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testModel.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testModel.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testModel.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createModelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modelRepository.findAll().size();

        // Create the Model with an existing ID
        model.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restModelMockMvc.perform(post("/api/models")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(model)))
            .andExpect(status().isBadRequest());

        // Validate the Model in the database
        List<Model> modelList = modelRepository.findAll();
        assertThat(modelList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkModelCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelRepository.findAll().size();
        // set the field null
        model.setModelCode(null);

        // Create the Model, which fails.

        restModelMockMvc.perform(post("/api/models")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(model)))
            .andExpect(status().isBadRequest());

        List<Model> modelList = modelRepository.findAll();
        assertThat(modelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelRepository.findAll().size();
        // set the field null
        model.setCreatedDate(null);

        // Create the Model, which fails.

        restModelMockMvc.perform(post("/api/models")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(model)))
            .andExpect(status().isBadRequest());

        List<Model> modelList = modelRepository.findAll();
        assertThat(modelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelRepository.findAll().size();
        // set the field null
        model.setCreatedBy(null);

        // Create the Model, which fails.

        restModelMockMvc.perform(post("/api/models")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(model)))
            .andExpect(status().isBadRequest());

        List<Model> modelList = modelRepository.findAll();
        assertThat(modelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelRepository.findAll().size();
        // set the field null
        model.setLastUpdatedDate(null);

        // Create the Model, which fails.

        restModelMockMvc.perform(post("/api/models")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(model)))
            .andExpect(status().isBadRequest());

        List<Model> modelList = modelRepository.findAll();
        assertThat(modelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelRepository.findAll().size();
        // set the field null
        model.setLastUpdatedBy(null);

        // Create the Model, which fails.

        restModelMockMvc.perform(post("/api/models")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(model)))
            .andExpect(status().isBadRequest());

        List<Model> modelList = modelRepository.findAll();
        assertThat(modelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelRepository.findAll().size();
        // set the field null
        model.setTenantId(null);

        // Create the Model, which fails.

        restModelMockMvc.perform(post("/api/models")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(model)))
            .andExpect(status().isBadRequest());

        List<Model> modelList = modelRepository.findAll();
        assertThat(modelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllModels() throws Exception {
        // Initialize the database
        modelRepository.save(model);

        // Get all the modelList
        restModelMockMvc.perform(get("/api/models?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(model.getId())))
            .andExpect(jsonPath("$.[*].modelCode").value(hasItem(DEFAULT_MODEL_CODE)))
            .andExpect(jsonPath("$.[*].modelGroup").value(hasItem(DEFAULT_MODEL_GROUP)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getModel() throws Exception {
        // Initialize the database
        modelRepository.save(model);

        // Get the model
        restModelMockMvc.perform(get("/api/models/{id}", model.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(model.getId()))
            .andExpect(jsonPath("$.modelCode").value(DEFAULT_MODEL_CODE))
            .andExpect(jsonPath("$.modelGroup").value(DEFAULT_MODEL_GROUP))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingModel() throws Exception {
        // Get the model
        restModelMockMvc.perform(get("/api/models/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateModel() throws Exception {
        // Initialize the database
        modelService.save(model);

        int databaseSizeBeforeUpdate = modelRepository.findAll().size();

        // Update the model
        Model updatedModel = modelRepository.findById(model.getId()).get();
        updatedModel
            .modelCode(UPDATED_MODEL_CODE)
            .modelGroup(UPDATED_MODEL_GROUP)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restModelMockMvc.perform(put("/api/models")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedModel)))
            .andExpect(status().isOk());

        // Validate the Model in the database
        List<Model> modelList = modelRepository.findAll();
        assertThat(modelList).hasSize(databaseSizeBeforeUpdate);
        Model testModel = modelList.get(modelList.size() - 1);
        assertThat(testModel.getModelCode()).isEqualTo(UPDATED_MODEL_CODE);
        assertThat(testModel.getModelGroup()).isEqualTo(UPDATED_MODEL_GROUP);
        assertThat(testModel.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testModel.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testModel.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testModel.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testModel.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testModel.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingModel() throws Exception {
        int databaseSizeBeforeUpdate = modelRepository.findAll().size();

        // Create the Model

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModelMockMvc.perform(put("/api/models")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(model)))
            .andExpect(status().isBadRequest());

        // Validate the Model in the database
        List<Model> modelList = modelRepository.findAll();
        assertThat(modelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteModel() throws Exception {
        // Initialize the database
        modelService.save(model);

        int databaseSizeBeforeDelete = modelRepository.findAll().size();

        // Delete the model
        restModelMockMvc.perform(delete("/api/models/{id}", model.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Model> modelList = modelRepository.findAll();
        assertThat(modelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
