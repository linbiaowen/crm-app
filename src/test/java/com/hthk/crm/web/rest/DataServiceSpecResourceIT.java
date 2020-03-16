package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.DataServiceSpec;
import com.hthk.crm.repository.DataServiceSpecRepository;
import com.hthk.crm.service.DataServiceSpecService;
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
 * Integration tests for the {@link DataServiceSpecResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class DataServiceSpecResourceIT {

    private static final Long DEFAULT_DATA_SPEC_ID = 1L;
    private static final Long UPDATED_DATA_SPEC_ID = 2L;

    private static final String DEFAULT_SERVICE_SPEC_ID = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_SPEC_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MAX_ENTITLEMENT = "AAAAAAAAAA";
    private static final String UPDATED_MAX_ENTITLEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_MAX_ACCESS_SPEED = "AAAAAAAAAA";
    private static final String UPDATED_MAX_ACCESS_SPEED = "BBBBBBBBBB";

    private static final String DEFAULT_THROTTLED_SPEED = "AAAAAAAAAA";
    private static final String UPDATED_THROTTLED_SPEED = "BBBBBBBBBB";

    private static final String DEFAULT_UPSTREAM_SPEED = "AAAAAAAAAA";
    private static final String UPDATED_UPSTREAM_SPEED = "BBBBBBBBBB";

    private static final String DEFAULT_DOWNSTREAM_SPEED = "AAAAAAAAAA";
    private static final String UPDATED_DOWNSTREAM_SPEED = "BBBBBBBBBB";

    private static final String DEFAULT_SOCIAL_SITES = "AAAAAAAAAA";
    private static final String UPDATED_SOCIAL_SITES = "BBBBBBBBBB";

    private static final String DEFAULT_ENTERTAINMENT_PACK_OPTIONS = "AAAAAAAAAA";
    private static final String UPDATED_ENTERTAINMENT_PACK_OPTIONS = "BBBBBBBBBB";

    private static final String DEFAULT_ROAMING_DATA_SPEED = "AAAAAAAAAA";
    private static final String UPDATED_ROAMING_DATA_SPEED = "BBBBBBBBBB";

    private static final String DEFAULT_ROAMING_DATA_VOLUME = "AAAAAAAAAA";
    private static final String UPDATED_ROAMING_DATA_VOLUME = "BBBBBBBBBB";

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
    private DataServiceSpecRepository dataServiceSpecRepository;

    @Autowired
    private DataServiceSpecService dataServiceSpecService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restDataServiceSpecMockMvc;

    private DataServiceSpec dataServiceSpec;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DataServiceSpecResource dataServiceSpecResource = new DataServiceSpecResource(dataServiceSpecService);
        this.restDataServiceSpecMockMvc = MockMvcBuilders.standaloneSetup(dataServiceSpecResource)
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
    public static DataServiceSpec createEntity() {
        DataServiceSpec dataServiceSpec = new DataServiceSpec()
            .dataSpecId(DEFAULT_DATA_SPEC_ID)
            .serviceSpecId(DEFAULT_SERVICE_SPEC_ID)
            .maxEntitlement(DEFAULT_MAX_ENTITLEMENT)
            .maxAccessSpeed(DEFAULT_MAX_ACCESS_SPEED)
            .throttledSpeed(DEFAULT_THROTTLED_SPEED)
            .upstreamSpeed(DEFAULT_UPSTREAM_SPEED)
            .downstreamSpeed(DEFAULT_DOWNSTREAM_SPEED)
            .socialSites(DEFAULT_SOCIAL_SITES)
            .entertainmentPackOptions(DEFAULT_ENTERTAINMENT_PACK_OPTIONS)
            .roamingDataSpeed(DEFAULT_ROAMING_DATA_SPEED)
            .roamingDataVolume(DEFAULT_ROAMING_DATA_VOLUME)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return dataServiceSpec;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataServiceSpec createUpdatedEntity() {
        DataServiceSpec dataServiceSpec = new DataServiceSpec()
            .dataSpecId(UPDATED_DATA_SPEC_ID)
            .serviceSpecId(UPDATED_SERVICE_SPEC_ID)
            .maxEntitlement(UPDATED_MAX_ENTITLEMENT)
            .maxAccessSpeed(UPDATED_MAX_ACCESS_SPEED)
            .throttledSpeed(UPDATED_THROTTLED_SPEED)
            .upstreamSpeed(UPDATED_UPSTREAM_SPEED)
            .downstreamSpeed(UPDATED_DOWNSTREAM_SPEED)
            .socialSites(UPDATED_SOCIAL_SITES)
            .entertainmentPackOptions(UPDATED_ENTERTAINMENT_PACK_OPTIONS)
            .roamingDataSpeed(UPDATED_ROAMING_DATA_SPEED)
            .roamingDataVolume(UPDATED_ROAMING_DATA_VOLUME)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return dataServiceSpec;
    }

    @BeforeEach
    public void initTest() {
        dataServiceSpecRepository.deleteAll();
        dataServiceSpec = createEntity();
    }

    @Test
    public void createDataServiceSpec() throws Exception {
        int databaseSizeBeforeCreate = dataServiceSpecRepository.findAll().size();

        // Create the DataServiceSpec
        restDataServiceSpecMockMvc.perform(post("/api/data-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataServiceSpec)))
            .andExpect(status().isCreated());

        // Validate the DataServiceSpec in the database
        List<DataServiceSpec> dataServiceSpecList = dataServiceSpecRepository.findAll();
        assertThat(dataServiceSpecList).hasSize(databaseSizeBeforeCreate + 1);
        DataServiceSpec testDataServiceSpec = dataServiceSpecList.get(dataServiceSpecList.size() - 1);
        assertThat(testDataServiceSpec.getDataSpecId()).isEqualTo(DEFAULT_DATA_SPEC_ID);
        assertThat(testDataServiceSpec.getServiceSpecId()).isEqualTo(DEFAULT_SERVICE_SPEC_ID);
        assertThat(testDataServiceSpec.getMaxEntitlement()).isEqualTo(DEFAULT_MAX_ENTITLEMENT);
        assertThat(testDataServiceSpec.getMaxAccessSpeed()).isEqualTo(DEFAULT_MAX_ACCESS_SPEED);
        assertThat(testDataServiceSpec.getThrottledSpeed()).isEqualTo(DEFAULT_THROTTLED_SPEED);
        assertThat(testDataServiceSpec.getUpstreamSpeed()).isEqualTo(DEFAULT_UPSTREAM_SPEED);
        assertThat(testDataServiceSpec.getDownstreamSpeed()).isEqualTo(DEFAULT_DOWNSTREAM_SPEED);
        assertThat(testDataServiceSpec.getSocialSites()).isEqualTo(DEFAULT_SOCIAL_SITES);
        assertThat(testDataServiceSpec.getEntertainmentPackOptions()).isEqualTo(DEFAULT_ENTERTAINMENT_PACK_OPTIONS);
        assertThat(testDataServiceSpec.getRoamingDataSpeed()).isEqualTo(DEFAULT_ROAMING_DATA_SPEED);
        assertThat(testDataServiceSpec.getRoamingDataVolume()).isEqualTo(DEFAULT_ROAMING_DATA_VOLUME);
        assertThat(testDataServiceSpec.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDataServiceSpec.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDataServiceSpec.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testDataServiceSpec.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testDataServiceSpec.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createDataServiceSpecWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dataServiceSpecRepository.findAll().size();

        // Create the DataServiceSpec with an existing ID
        dataServiceSpec.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataServiceSpecMockMvc.perform(post("/api/data-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataServiceSpec)))
            .andExpect(status().isBadRequest());

        // Validate the DataServiceSpec in the database
        List<DataServiceSpec> dataServiceSpecList = dataServiceSpecRepository.findAll();
        assertThat(dataServiceSpecList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkDataSpecIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = dataServiceSpecRepository.findAll().size();
        // set the field null
        dataServiceSpec.setDataSpecId(null);

        // Create the DataServiceSpec, which fails.

        restDataServiceSpecMockMvc.perform(post("/api/data-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataServiceSpec)))
            .andExpect(status().isBadRequest());

        List<DataServiceSpec> dataServiceSpecList = dataServiceSpecRepository.findAll();
        assertThat(dataServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkServiceSpecIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = dataServiceSpecRepository.findAll().size();
        // set the field null
        dataServiceSpec.setServiceSpecId(null);

        // Create the DataServiceSpec, which fails.

        restDataServiceSpecMockMvc.perform(post("/api/data-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataServiceSpec)))
            .andExpect(status().isBadRequest());

        List<DataServiceSpec> dataServiceSpecList = dataServiceSpecRepository.findAll();
        assertThat(dataServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = dataServiceSpecRepository.findAll().size();
        // set the field null
        dataServiceSpec.setCreatedDate(null);

        // Create the DataServiceSpec, which fails.

        restDataServiceSpecMockMvc.perform(post("/api/data-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataServiceSpec)))
            .andExpect(status().isBadRequest());

        List<DataServiceSpec> dataServiceSpecList = dataServiceSpecRepository.findAll();
        assertThat(dataServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = dataServiceSpecRepository.findAll().size();
        // set the field null
        dataServiceSpec.setCreatedBy(null);

        // Create the DataServiceSpec, which fails.

        restDataServiceSpecMockMvc.perform(post("/api/data-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataServiceSpec)))
            .andExpect(status().isBadRequest());

        List<DataServiceSpec> dataServiceSpecList = dataServiceSpecRepository.findAll();
        assertThat(dataServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = dataServiceSpecRepository.findAll().size();
        // set the field null
        dataServiceSpec.setLastUpdatedDate(null);

        // Create the DataServiceSpec, which fails.

        restDataServiceSpecMockMvc.perform(post("/api/data-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataServiceSpec)))
            .andExpect(status().isBadRequest());

        List<DataServiceSpec> dataServiceSpecList = dataServiceSpecRepository.findAll();
        assertThat(dataServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = dataServiceSpecRepository.findAll().size();
        // set the field null
        dataServiceSpec.setLastUpdatedBy(null);

        // Create the DataServiceSpec, which fails.

        restDataServiceSpecMockMvc.perform(post("/api/data-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataServiceSpec)))
            .andExpect(status().isBadRequest());

        List<DataServiceSpec> dataServiceSpecList = dataServiceSpecRepository.findAll();
        assertThat(dataServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = dataServiceSpecRepository.findAll().size();
        // set the field null
        dataServiceSpec.setTenantId(null);

        // Create the DataServiceSpec, which fails.

        restDataServiceSpecMockMvc.perform(post("/api/data-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataServiceSpec)))
            .andExpect(status().isBadRequest());

        List<DataServiceSpec> dataServiceSpecList = dataServiceSpecRepository.findAll();
        assertThat(dataServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllDataServiceSpecs() throws Exception {
        // Initialize the database
        dataServiceSpecRepository.save(dataServiceSpec);

        // Get all the dataServiceSpecList
        restDataServiceSpecMockMvc.perform(get("/api/data-service-specs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataServiceSpec.getId())))
            .andExpect(jsonPath("$.[*].dataSpecId").value(hasItem(DEFAULT_DATA_SPEC_ID.intValue())))
            .andExpect(jsonPath("$.[*].serviceSpecId").value(hasItem(DEFAULT_SERVICE_SPEC_ID)))
            .andExpect(jsonPath("$.[*].maxEntitlement").value(hasItem(DEFAULT_MAX_ENTITLEMENT)))
            .andExpect(jsonPath("$.[*].maxAccessSpeed").value(hasItem(DEFAULT_MAX_ACCESS_SPEED)))
            .andExpect(jsonPath("$.[*].throttledSpeed").value(hasItem(DEFAULT_THROTTLED_SPEED)))
            .andExpect(jsonPath("$.[*].upstreamSpeed").value(hasItem(DEFAULT_UPSTREAM_SPEED)))
            .andExpect(jsonPath("$.[*].downstreamSpeed").value(hasItem(DEFAULT_DOWNSTREAM_SPEED)))
            .andExpect(jsonPath("$.[*].socialSites").value(hasItem(DEFAULT_SOCIAL_SITES)))
            .andExpect(jsonPath("$.[*].entertainmentPackOptions").value(hasItem(DEFAULT_ENTERTAINMENT_PACK_OPTIONS)))
            .andExpect(jsonPath("$.[*].roamingDataSpeed").value(hasItem(DEFAULT_ROAMING_DATA_SPEED)))
            .andExpect(jsonPath("$.[*].roamingDataVolume").value(hasItem(DEFAULT_ROAMING_DATA_VOLUME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getDataServiceSpec() throws Exception {
        // Initialize the database
        dataServiceSpecRepository.save(dataServiceSpec);

        // Get the dataServiceSpec
        restDataServiceSpecMockMvc.perform(get("/api/data-service-specs/{id}", dataServiceSpec.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dataServiceSpec.getId()))
            .andExpect(jsonPath("$.dataSpecId").value(DEFAULT_DATA_SPEC_ID.intValue()))
            .andExpect(jsonPath("$.serviceSpecId").value(DEFAULT_SERVICE_SPEC_ID))
            .andExpect(jsonPath("$.maxEntitlement").value(DEFAULT_MAX_ENTITLEMENT))
            .andExpect(jsonPath("$.maxAccessSpeed").value(DEFAULT_MAX_ACCESS_SPEED))
            .andExpect(jsonPath("$.throttledSpeed").value(DEFAULT_THROTTLED_SPEED))
            .andExpect(jsonPath("$.upstreamSpeed").value(DEFAULT_UPSTREAM_SPEED))
            .andExpect(jsonPath("$.downstreamSpeed").value(DEFAULT_DOWNSTREAM_SPEED))
            .andExpect(jsonPath("$.socialSites").value(DEFAULT_SOCIAL_SITES))
            .andExpect(jsonPath("$.entertainmentPackOptions").value(DEFAULT_ENTERTAINMENT_PACK_OPTIONS))
            .andExpect(jsonPath("$.roamingDataSpeed").value(DEFAULT_ROAMING_DATA_SPEED))
            .andExpect(jsonPath("$.roamingDataVolume").value(DEFAULT_ROAMING_DATA_VOLUME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingDataServiceSpec() throws Exception {
        // Get the dataServiceSpec
        restDataServiceSpecMockMvc.perform(get("/api/data-service-specs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDataServiceSpec() throws Exception {
        // Initialize the database
        dataServiceSpecService.save(dataServiceSpec);

        int databaseSizeBeforeUpdate = dataServiceSpecRepository.findAll().size();

        // Update the dataServiceSpec
        DataServiceSpec updatedDataServiceSpec = dataServiceSpecRepository.findById(dataServiceSpec.getId()).get();
        updatedDataServiceSpec
            .dataSpecId(UPDATED_DATA_SPEC_ID)
            .serviceSpecId(UPDATED_SERVICE_SPEC_ID)
            .maxEntitlement(UPDATED_MAX_ENTITLEMENT)
            .maxAccessSpeed(UPDATED_MAX_ACCESS_SPEED)
            .throttledSpeed(UPDATED_THROTTLED_SPEED)
            .upstreamSpeed(UPDATED_UPSTREAM_SPEED)
            .downstreamSpeed(UPDATED_DOWNSTREAM_SPEED)
            .socialSites(UPDATED_SOCIAL_SITES)
            .entertainmentPackOptions(UPDATED_ENTERTAINMENT_PACK_OPTIONS)
            .roamingDataSpeed(UPDATED_ROAMING_DATA_SPEED)
            .roamingDataVolume(UPDATED_ROAMING_DATA_VOLUME)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restDataServiceSpecMockMvc.perform(put("/api/data-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDataServiceSpec)))
            .andExpect(status().isOk());

        // Validate the DataServiceSpec in the database
        List<DataServiceSpec> dataServiceSpecList = dataServiceSpecRepository.findAll();
        assertThat(dataServiceSpecList).hasSize(databaseSizeBeforeUpdate);
        DataServiceSpec testDataServiceSpec = dataServiceSpecList.get(dataServiceSpecList.size() - 1);
        assertThat(testDataServiceSpec.getDataSpecId()).isEqualTo(UPDATED_DATA_SPEC_ID);
        assertThat(testDataServiceSpec.getServiceSpecId()).isEqualTo(UPDATED_SERVICE_SPEC_ID);
        assertThat(testDataServiceSpec.getMaxEntitlement()).isEqualTo(UPDATED_MAX_ENTITLEMENT);
        assertThat(testDataServiceSpec.getMaxAccessSpeed()).isEqualTo(UPDATED_MAX_ACCESS_SPEED);
        assertThat(testDataServiceSpec.getThrottledSpeed()).isEqualTo(UPDATED_THROTTLED_SPEED);
        assertThat(testDataServiceSpec.getUpstreamSpeed()).isEqualTo(UPDATED_UPSTREAM_SPEED);
        assertThat(testDataServiceSpec.getDownstreamSpeed()).isEqualTo(UPDATED_DOWNSTREAM_SPEED);
        assertThat(testDataServiceSpec.getSocialSites()).isEqualTo(UPDATED_SOCIAL_SITES);
        assertThat(testDataServiceSpec.getEntertainmentPackOptions()).isEqualTo(UPDATED_ENTERTAINMENT_PACK_OPTIONS);
        assertThat(testDataServiceSpec.getRoamingDataSpeed()).isEqualTo(UPDATED_ROAMING_DATA_SPEED);
        assertThat(testDataServiceSpec.getRoamingDataVolume()).isEqualTo(UPDATED_ROAMING_DATA_VOLUME);
        assertThat(testDataServiceSpec.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDataServiceSpec.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDataServiceSpec.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testDataServiceSpec.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testDataServiceSpec.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingDataServiceSpec() throws Exception {
        int databaseSizeBeforeUpdate = dataServiceSpecRepository.findAll().size();

        // Create the DataServiceSpec

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDataServiceSpecMockMvc.perform(put("/api/data-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataServiceSpec)))
            .andExpect(status().isBadRequest());

        // Validate the DataServiceSpec in the database
        List<DataServiceSpec> dataServiceSpecList = dataServiceSpecRepository.findAll();
        assertThat(dataServiceSpecList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteDataServiceSpec() throws Exception {
        // Initialize the database
        dataServiceSpecService.save(dataServiceSpec);

        int databaseSizeBeforeDelete = dataServiceSpecRepository.findAll().size();

        // Delete the dataServiceSpec
        restDataServiceSpecMockMvc.perform(delete("/api/data-service-specs/{id}", dataServiceSpec.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DataServiceSpec> dataServiceSpecList = dataServiceSpecRepository.findAll();
        assertThat(dataServiceSpecList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
