package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.CommOptoutType;
import com.hthk.crm.repository.CommOptoutTypeRepository;
import com.hthk.crm.service.CommOptoutTypeService;
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

/**
 * Integration tests for the {@link CommOptoutTypeResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class CommOptoutTypeResourceIT {

    private static final String DEFAULT_OPTOUT_TYPE_ID = "AAAAAAAAAA";
    private static final String UPDATED_OPTOUT_TYPE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OPTOUT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_OPTOUT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_OPTOUT_TYPE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_OPTOUT_TYPE_DESC = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

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
    private CommOptoutTypeRepository commOptoutTypeRepository;

    @Autowired
    private CommOptoutTypeService commOptoutTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCommOptoutTypeMockMvc;

    private CommOptoutType commOptoutType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CommOptoutTypeResource commOptoutTypeResource = new CommOptoutTypeResource(commOptoutTypeService);
        this.restCommOptoutTypeMockMvc = MockMvcBuilders.standaloneSetup(commOptoutTypeResource)
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
    public static CommOptoutType createEntity() {
        CommOptoutType commOptoutType = new CommOptoutType()
            .optoutTypeId(DEFAULT_OPTOUT_TYPE_ID)
            .optoutType(DEFAULT_OPTOUT_TYPE)
            .optoutTypeDesc(DEFAULT_OPTOUT_TYPE_DESC)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return commOptoutType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommOptoutType createUpdatedEntity() {
        CommOptoutType commOptoutType = new CommOptoutType()
            .optoutTypeId(UPDATED_OPTOUT_TYPE_ID)
            .optoutType(UPDATED_OPTOUT_TYPE)
            .optoutTypeDesc(UPDATED_OPTOUT_TYPE_DESC)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return commOptoutType;
    }

    @BeforeEach
    public void initTest() {
        commOptoutTypeRepository.deleteAll();
        commOptoutType = createEntity();
    }

    @Test
    public void createCommOptoutType() throws Exception {
        int databaseSizeBeforeCreate = commOptoutTypeRepository.findAll().size();

        // Create the CommOptoutType
        restCommOptoutTypeMockMvc.perform(post("/api/comm-optout-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commOptoutType)))
            .andExpect(status().isCreated());

        // Validate the CommOptoutType in the database
        List<CommOptoutType> commOptoutTypeList = commOptoutTypeRepository.findAll();
        assertThat(commOptoutTypeList).hasSize(databaseSizeBeforeCreate + 1);
        CommOptoutType testCommOptoutType = commOptoutTypeList.get(commOptoutTypeList.size() - 1);
        assertThat(testCommOptoutType.getOptoutTypeId()).isEqualTo(DEFAULT_OPTOUT_TYPE_ID);
        assertThat(testCommOptoutType.getOptoutType()).isEqualTo(DEFAULT_OPTOUT_TYPE);
        assertThat(testCommOptoutType.getOptoutTypeDesc()).isEqualTo(DEFAULT_OPTOUT_TYPE_DESC);
        assertThat(testCommOptoutType.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testCommOptoutType.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testCommOptoutType.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCommOptoutType.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCommOptoutType.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testCommOptoutType.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testCommOptoutType.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createCommOptoutTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commOptoutTypeRepository.findAll().size();

        // Create the CommOptoutType with an existing ID
        commOptoutType.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommOptoutTypeMockMvc.perform(post("/api/comm-optout-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commOptoutType)))
            .andExpect(status().isBadRequest());

        // Validate the CommOptoutType in the database
        List<CommOptoutType> commOptoutTypeList = commOptoutTypeRepository.findAll();
        assertThat(commOptoutTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkOptoutTypeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = commOptoutTypeRepository.findAll().size();
        // set the field null
        commOptoutType.setOptoutTypeId(null);

        // Create the CommOptoutType, which fails.

        restCommOptoutTypeMockMvc.perform(post("/api/comm-optout-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commOptoutType)))
            .andExpect(status().isBadRequest());

        List<CommOptoutType> commOptoutTypeList = commOptoutTypeRepository.findAll();
        assertThat(commOptoutTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOptoutTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = commOptoutTypeRepository.findAll().size();
        // set the field null
        commOptoutType.setOptoutType(null);

        // Create the CommOptoutType, which fails.

        restCommOptoutTypeMockMvc.perform(post("/api/comm-optout-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commOptoutType)))
            .andExpect(status().isBadRequest());

        List<CommOptoutType> commOptoutTypeList = commOptoutTypeRepository.findAll();
        assertThat(commOptoutTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = commOptoutTypeRepository.findAll().size();
        // set the field null
        commOptoutType.setCreatedDate(null);

        // Create the CommOptoutType, which fails.

        restCommOptoutTypeMockMvc.perform(post("/api/comm-optout-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commOptoutType)))
            .andExpect(status().isBadRequest());

        List<CommOptoutType> commOptoutTypeList = commOptoutTypeRepository.findAll();
        assertThat(commOptoutTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = commOptoutTypeRepository.findAll().size();
        // set the field null
        commOptoutType.setCreatedBy(null);

        // Create the CommOptoutType, which fails.

        restCommOptoutTypeMockMvc.perform(post("/api/comm-optout-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commOptoutType)))
            .andExpect(status().isBadRequest());

        List<CommOptoutType> commOptoutTypeList = commOptoutTypeRepository.findAll();
        assertThat(commOptoutTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = commOptoutTypeRepository.findAll().size();
        // set the field null
        commOptoutType.setLastUpdatedDate(null);

        // Create the CommOptoutType, which fails.

        restCommOptoutTypeMockMvc.perform(post("/api/comm-optout-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commOptoutType)))
            .andExpect(status().isBadRequest());

        List<CommOptoutType> commOptoutTypeList = commOptoutTypeRepository.findAll();
        assertThat(commOptoutTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = commOptoutTypeRepository.findAll().size();
        // set the field null
        commOptoutType.setLastUpdatedBy(null);

        // Create the CommOptoutType, which fails.

        restCommOptoutTypeMockMvc.perform(post("/api/comm-optout-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commOptoutType)))
            .andExpect(status().isBadRequest());

        List<CommOptoutType> commOptoutTypeList = commOptoutTypeRepository.findAll();
        assertThat(commOptoutTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = commOptoutTypeRepository.findAll().size();
        // set the field null
        commOptoutType.setTenantId(null);

        // Create the CommOptoutType, which fails.

        restCommOptoutTypeMockMvc.perform(post("/api/comm-optout-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commOptoutType)))
            .andExpect(status().isBadRequest());

        List<CommOptoutType> commOptoutTypeList = commOptoutTypeRepository.findAll();
        assertThat(commOptoutTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCommOptoutTypes() throws Exception {
        // Initialize the database
        commOptoutTypeRepository.save(commOptoutType);

        // Get all the commOptoutTypeList
        restCommOptoutTypeMockMvc.perform(get("/api/comm-optout-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commOptoutType.getId())))
            .andExpect(jsonPath("$.[*].optoutTypeId").value(hasItem(DEFAULT_OPTOUT_TYPE_ID)))
            .andExpect(jsonPath("$.[*].optoutType").value(hasItem(DEFAULT_OPTOUT_TYPE)))
            .andExpect(jsonPath("$.[*].optoutTypeDesc").value(hasItem(DEFAULT_OPTOUT_TYPE_DESC)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getCommOptoutType() throws Exception {
        // Initialize the database
        commOptoutTypeRepository.save(commOptoutType);

        // Get the commOptoutType
        restCommOptoutTypeMockMvc.perform(get("/api/comm-optout-types/{id}", commOptoutType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(commOptoutType.getId()))
            .andExpect(jsonPath("$.optoutTypeId").value(DEFAULT_OPTOUT_TYPE_ID))
            .andExpect(jsonPath("$.optoutType").value(DEFAULT_OPTOUT_TYPE))
            .andExpect(jsonPath("$.optoutTypeDesc").value(DEFAULT_OPTOUT_TYPE_DESC))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingCommOptoutType() throws Exception {
        // Get the commOptoutType
        restCommOptoutTypeMockMvc.perform(get("/api/comm-optout-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCommOptoutType() throws Exception {
        // Initialize the database
        commOptoutTypeService.save(commOptoutType);

        int databaseSizeBeforeUpdate = commOptoutTypeRepository.findAll().size();

        // Update the commOptoutType
        CommOptoutType updatedCommOptoutType = commOptoutTypeRepository.findById(commOptoutType.getId()).get();
        updatedCommOptoutType
            .optoutTypeId(UPDATED_OPTOUT_TYPE_ID)
            .optoutType(UPDATED_OPTOUT_TYPE)
            .optoutTypeDesc(UPDATED_OPTOUT_TYPE_DESC)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restCommOptoutTypeMockMvc.perform(put("/api/comm-optout-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCommOptoutType)))
            .andExpect(status().isOk());

        // Validate the CommOptoutType in the database
        List<CommOptoutType> commOptoutTypeList = commOptoutTypeRepository.findAll();
        assertThat(commOptoutTypeList).hasSize(databaseSizeBeforeUpdate);
        CommOptoutType testCommOptoutType = commOptoutTypeList.get(commOptoutTypeList.size() - 1);
        assertThat(testCommOptoutType.getOptoutTypeId()).isEqualTo(UPDATED_OPTOUT_TYPE_ID);
        assertThat(testCommOptoutType.getOptoutType()).isEqualTo(UPDATED_OPTOUT_TYPE);
        assertThat(testCommOptoutType.getOptoutTypeDesc()).isEqualTo(UPDATED_OPTOUT_TYPE_DESC);
        assertThat(testCommOptoutType.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testCommOptoutType.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testCommOptoutType.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCommOptoutType.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCommOptoutType.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testCommOptoutType.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testCommOptoutType.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingCommOptoutType() throws Exception {
        int databaseSizeBeforeUpdate = commOptoutTypeRepository.findAll().size();

        // Create the CommOptoutType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommOptoutTypeMockMvc.perform(put("/api/comm-optout-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commOptoutType)))
            .andExpect(status().isBadRequest());

        // Validate the CommOptoutType in the database
        List<CommOptoutType> commOptoutTypeList = commOptoutTypeRepository.findAll();
        assertThat(commOptoutTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCommOptoutType() throws Exception {
        // Initialize the database
        commOptoutTypeService.save(commOptoutType);

        int databaseSizeBeforeDelete = commOptoutTypeRepository.findAll().size();

        // Delete the commOptoutType
        restCommOptoutTypeMockMvc.perform(delete("/api/comm-optout-types/{id}", commOptoutType.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CommOptoutType> commOptoutTypeList = commOptoutTypeRepository.findAll();
        assertThat(commOptoutTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
