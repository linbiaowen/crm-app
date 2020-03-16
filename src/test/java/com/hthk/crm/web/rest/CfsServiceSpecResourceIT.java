package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.CfsServiceSpec;
import com.hthk.crm.repository.CfsServiceSpecRepository;
import com.hthk.crm.service.CfsServiceSpecService;
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
 * Integration tests for the {@link CfsServiceSpecResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class CfsServiceSpecResourceIT {

    private static final String DEFAULT_SERVICE_SPEC_ID = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_SPEC_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_SPEC_DESC = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_SPEC_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_VOICE_SPEC_ID = 1L;
    private static final Long UPDATED_VOICE_SPEC_ID = 2L;

    private static final Long DEFAULT_DATA_SPEC_ID = 1L;
    private static final Long UPDATED_DATA_SPEC_ID = 2L;

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
    private CfsServiceSpecRepository cfsServiceSpecRepository;

    @Autowired
    private CfsServiceSpecService cfsServiceSpecService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCfsServiceSpecMockMvc;

    private CfsServiceSpec cfsServiceSpec;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CfsServiceSpecResource cfsServiceSpecResource = new CfsServiceSpecResource(cfsServiceSpecService);
        this.restCfsServiceSpecMockMvc = MockMvcBuilders.standaloneSetup(cfsServiceSpecResource)
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
    public static CfsServiceSpec createEntity() {
        CfsServiceSpec cfsServiceSpec = new CfsServiceSpec()
            .serviceSpecId(DEFAULT_SERVICE_SPEC_ID)
            .serviceSpecDesc(DEFAULT_SERVICE_SPEC_DESC)
            .serviceId(DEFAULT_SERVICE_ID)
            .voiceSpecId(DEFAULT_VOICE_SPEC_ID)
            .dataSpecId(DEFAULT_DATA_SPEC_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return cfsServiceSpec;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CfsServiceSpec createUpdatedEntity() {
        CfsServiceSpec cfsServiceSpec = new CfsServiceSpec()
            .serviceSpecId(UPDATED_SERVICE_SPEC_ID)
            .serviceSpecDesc(UPDATED_SERVICE_SPEC_DESC)
            .serviceId(UPDATED_SERVICE_ID)
            .voiceSpecId(UPDATED_VOICE_SPEC_ID)
            .dataSpecId(UPDATED_DATA_SPEC_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return cfsServiceSpec;
    }

    @BeforeEach
    public void initTest() {
        cfsServiceSpecRepository.deleteAll();
        cfsServiceSpec = createEntity();
    }

    @Test
    public void createCfsServiceSpec() throws Exception {
        int databaseSizeBeforeCreate = cfsServiceSpecRepository.findAll().size();

        // Create the CfsServiceSpec
        restCfsServiceSpecMockMvc.perform(post("/api/cfs-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServiceSpec)))
            .andExpect(status().isCreated());

        // Validate the CfsServiceSpec in the database
        List<CfsServiceSpec> cfsServiceSpecList = cfsServiceSpecRepository.findAll();
        assertThat(cfsServiceSpecList).hasSize(databaseSizeBeforeCreate + 1);
        CfsServiceSpec testCfsServiceSpec = cfsServiceSpecList.get(cfsServiceSpecList.size() - 1);
        assertThat(testCfsServiceSpec.getServiceSpecId()).isEqualTo(DEFAULT_SERVICE_SPEC_ID);
        assertThat(testCfsServiceSpec.getServiceSpecDesc()).isEqualTo(DEFAULT_SERVICE_SPEC_DESC);
        assertThat(testCfsServiceSpec.getServiceId()).isEqualTo(DEFAULT_SERVICE_ID);
        assertThat(testCfsServiceSpec.getVoiceSpecId()).isEqualTo(DEFAULT_VOICE_SPEC_ID);
        assertThat(testCfsServiceSpec.getDataSpecId()).isEqualTo(DEFAULT_DATA_SPEC_ID);
        assertThat(testCfsServiceSpec.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCfsServiceSpec.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCfsServiceSpec.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testCfsServiceSpec.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testCfsServiceSpec.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createCfsServiceSpecWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cfsServiceSpecRepository.findAll().size();

        // Create the CfsServiceSpec with an existing ID
        cfsServiceSpec.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCfsServiceSpecMockMvc.perform(post("/api/cfs-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServiceSpec)))
            .andExpect(status().isBadRequest());

        // Validate the CfsServiceSpec in the database
        List<CfsServiceSpec> cfsServiceSpecList = cfsServiceSpecRepository.findAll();
        assertThat(cfsServiceSpecList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkServiceSpecIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServiceSpecRepository.findAll().size();
        // set the field null
        cfsServiceSpec.setServiceSpecId(null);

        // Create the CfsServiceSpec, which fails.

        restCfsServiceSpecMockMvc.perform(post("/api/cfs-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServiceSpec)))
            .andExpect(status().isBadRequest());

        List<CfsServiceSpec> cfsServiceSpecList = cfsServiceSpecRepository.findAll();
        assertThat(cfsServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkServiceIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServiceSpecRepository.findAll().size();
        // set the field null
        cfsServiceSpec.setServiceId(null);

        // Create the CfsServiceSpec, which fails.

        restCfsServiceSpecMockMvc.perform(post("/api/cfs-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServiceSpec)))
            .andExpect(status().isBadRequest());

        List<CfsServiceSpec> cfsServiceSpecList = cfsServiceSpecRepository.findAll();
        assertThat(cfsServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServiceSpecRepository.findAll().size();
        // set the field null
        cfsServiceSpec.setCreatedDate(null);

        // Create the CfsServiceSpec, which fails.

        restCfsServiceSpecMockMvc.perform(post("/api/cfs-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServiceSpec)))
            .andExpect(status().isBadRequest());

        List<CfsServiceSpec> cfsServiceSpecList = cfsServiceSpecRepository.findAll();
        assertThat(cfsServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServiceSpecRepository.findAll().size();
        // set the field null
        cfsServiceSpec.setCreatedBy(null);

        // Create the CfsServiceSpec, which fails.

        restCfsServiceSpecMockMvc.perform(post("/api/cfs-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServiceSpec)))
            .andExpect(status().isBadRequest());

        List<CfsServiceSpec> cfsServiceSpecList = cfsServiceSpecRepository.findAll();
        assertThat(cfsServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServiceSpecRepository.findAll().size();
        // set the field null
        cfsServiceSpec.setLastUpdatedDate(null);

        // Create the CfsServiceSpec, which fails.

        restCfsServiceSpecMockMvc.perform(post("/api/cfs-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServiceSpec)))
            .andExpect(status().isBadRequest());

        List<CfsServiceSpec> cfsServiceSpecList = cfsServiceSpecRepository.findAll();
        assertThat(cfsServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServiceSpecRepository.findAll().size();
        // set the field null
        cfsServiceSpec.setLastUpdatedBy(null);

        // Create the CfsServiceSpec, which fails.

        restCfsServiceSpecMockMvc.perform(post("/api/cfs-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServiceSpec)))
            .andExpect(status().isBadRequest());

        List<CfsServiceSpec> cfsServiceSpecList = cfsServiceSpecRepository.findAll();
        assertThat(cfsServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServiceSpecRepository.findAll().size();
        // set the field null
        cfsServiceSpec.setTenantId(null);

        // Create the CfsServiceSpec, which fails.

        restCfsServiceSpecMockMvc.perform(post("/api/cfs-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServiceSpec)))
            .andExpect(status().isBadRequest());

        List<CfsServiceSpec> cfsServiceSpecList = cfsServiceSpecRepository.findAll();
        assertThat(cfsServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCfsServiceSpecs() throws Exception {
        // Initialize the database
        cfsServiceSpecRepository.save(cfsServiceSpec);

        // Get all the cfsServiceSpecList
        restCfsServiceSpecMockMvc.perform(get("/api/cfs-service-specs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cfsServiceSpec.getId())))
            .andExpect(jsonPath("$.[*].serviceSpecId").value(hasItem(DEFAULT_SERVICE_SPEC_ID)))
            .andExpect(jsonPath("$.[*].serviceSpecDesc").value(hasItem(DEFAULT_SERVICE_SPEC_DESC)))
            .andExpect(jsonPath("$.[*].serviceId").value(hasItem(DEFAULT_SERVICE_ID)))
            .andExpect(jsonPath("$.[*].voiceSpecId").value(hasItem(DEFAULT_VOICE_SPEC_ID.intValue())))
            .andExpect(jsonPath("$.[*].dataSpecId").value(hasItem(DEFAULT_DATA_SPEC_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getCfsServiceSpec() throws Exception {
        // Initialize the database
        cfsServiceSpecRepository.save(cfsServiceSpec);

        // Get the cfsServiceSpec
        restCfsServiceSpecMockMvc.perform(get("/api/cfs-service-specs/{id}", cfsServiceSpec.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cfsServiceSpec.getId()))
            .andExpect(jsonPath("$.serviceSpecId").value(DEFAULT_SERVICE_SPEC_ID))
            .andExpect(jsonPath("$.serviceSpecDesc").value(DEFAULT_SERVICE_SPEC_DESC))
            .andExpect(jsonPath("$.serviceId").value(DEFAULT_SERVICE_ID))
            .andExpect(jsonPath("$.voiceSpecId").value(DEFAULT_VOICE_SPEC_ID.intValue()))
            .andExpect(jsonPath("$.dataSpecId").value(DEFAULT_DATA_SPEC_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingCfsServiceSpec() throws Exception {
        // Get the cfsServiceSpec
        restCfsServiceSpecMockMvc.perform(get("/api/cfs-service-specs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCfsServiceSpec() throws Exception {
        // Initialize the database
        cfsServiceSpecService.save(cfsServiceSpec);

        int databaseSizeBeforeUpdate = cfsServiceSpecRepository.findAll().size();

        // Update the cfsServiceSpec
        CfsServiceSpec updatedCfsServiceSpec = cfsServiceSpecRepository.findById(cfsServiceSpec.getId()).get();
        updatedCfsServiceSpec
            .serviceSpecId(UPDATED_SERVICE_SPEC_ID)
            .serviceSpecDesc(UPDATED_SERVICE_SPEC_DESC)
            .serviceId(UPDATED_SERVICE_ID)
            .voiceSpecId(UPDATED_VOICE_SPEC_ID)
            .dataSpecId(UPDATED_DATA_SPEC_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restCfsServiceSpecMockMvc.perform(put("/api/cfs-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCfsServiceSpec)))
            .andExpect(status().isOk());

        // Validate the CfsServiceSpec in the database
        List<CfsServiceSpec> cfsServiceSpecList = cfsServiceSpecRepository.findAll();
        assertThat(cfsServiceSpecList).hasSize(databaseSizeBeforeUpdate);
        CfsServiceSpec testCfsServiceSpec = cfsServiceSpecList.get(cfsServiceSpecList.size() - 1);
        assertThat(testCfsServiceSpec.getServiceSpecId()).isEqualTo(UPDATED_SERVICE_SPEC_ID);
        assertThat(testCfsServiceSpec.getServiceSpecDesc()).isEqualTo(UPDATED_SERVICE_SPEC_DESC);
        assertThat(testCfsServiceSpec.getServiceId()).isEqualTo(UPDATED_SERVICE_ID);
        assertThat(testCfsServiceSpec.getVoiceSpecId()).isEqualTo(UPDATED_VOICE_SPEC_ID);
        assertThat(testCfsServiceSpec.getDataSpecId()).isEqualTo(UPDATED_DATA_SPEC_ID);
        assertThat(testCfsServiceSpec.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCfsServiceSpec.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCfsServiceSpec.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testCfsServiceSpec.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testCfsServiceSpec.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingCfsServiceSpec() throws Exception {
        int databaseSizeBeforeUpdate = cfsServiceSpecRepository.findAll().size();

        // Create the CfsServiceSpec

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCfsServiceSpecMockMvc.perform(put("/api/cfs-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServiceSpec)))
            .andExpect(status().isBadRequest());

        // Validate the CfsServiceSpec in the database
        List<CfsServiceSpec> cfsServiceSpecList = cfsServiceSpecRepository.findAll();
        assertThat(cfsServiceSpecList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCfsServiceSpec() throws Exception {
        // Initialize the database
        cfsServiceSpecService.save(cfsServiceSpec);

        int databaseSizeBeforeDelete = cfsServiceSpecRepository.findAll().size();

        // Delete the cfsServiceSpec
        restCfsServiceSpecMockMvc.perform(delete("/api/cfs-service-specs/{id}", cfsServiceSpec.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CfsServiceSpec> cfsServiceSpecList = cfsServiceSpecRepository.findAll();
        assertThat(cfsServiceSpecList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
