package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.CfsServices;
import com.hthk.crm.repository.CfsServicesRepository;
import com.hthk.crm.service.CfsServicesService;
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
 * Integration tests for the {@link CfsServicesResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class CfsServicesResourceIT {

    private static final String DEFAULT_SERVICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_NAME = "BBBBBBBBBB";

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
    private CfsServicesRepository cfsServicesRepository;

    @Autowired
    private CfsServicesService cfsServicesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCfsServicesMockMvc;

    private CfsServices cfsServices;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CfsServicesResource cfsServicesResource = new CfsServicesResource(cfsServicesService);
        this.restCfsServicesMockMvc = MockMvcBuilders.standaloneSetup(cfsServicesResource)
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
    public static CfsServices createEntity() {
        CfsServices cfsServices = new CfsServices()
            .serviceId(DEFAULT_SERVICE_ID)
            .serviceName(DEFAULT_SERVICE_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return cfsServices;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CfsServices createUpdatedEntity() {
        CfsServices cfsServices = new CfsServices()
            .serviceId(UPDATED_SERVICE_ID)
            .serviceName(UPDATED_SERVICE_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return cfsServices;
    }

    @BeforeEach
    public void initTest() {
        cfsServicesRepository.deleteAll();
        cfsServices = createEntity();
    }

    @Test
    public void createCfsServices() throws Exception {
        int databaseSizeBeforeCreate = cfsServicesRepository.findAll().size();

        // Create the CfsServices
        restCfsServicesMockMvc.perform(post("/api/cfs-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServices)))
            .andExpect(status().isCreated());

        // Validate the CfsServices in the database
        List<CfsServices> cfsServicesList = cfsServicesRepository.findAll();
        assertThat(cfsServicesList).hasSize(databaseSizeBeforeCreate + 1);
        CfsServices testCfsServices = cfsServicesList.get(cfsServicesList.size() - 1);
        assertThat(testCfsServices.getServiceId()).isEqualTo(DEFAULT_SERVICE_ID);
        assertThat(testCfsServices.getServiceName()).isEqualTo(DEFAULT_SERVICE_NAME);
        assertThat(testCfsServices.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCfsServices.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCfsServices.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testCfsServices.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testCfsServices.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createCfsServicesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cfsServicesRepository.findAll().size();

        // Create the CfsServices with an existing ID
        cfsServices.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCfsServicesMockMvc.perform(post("/api/cfs-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServices)))
            .andExpect(status().isBadRequest());

        // Validate the CfsServices in the database
        List<CfsServices> cfsServicesList = cfsServicesRepository.findAll();
        assertThat(cfsServicesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkServiceIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServicesRepository.findAll().size();
        // set the field null
        cfsServices.setServiceId(null);

        // Create the CfsServices, which fails.

        restCfsServicesMockMvc.perform(post("/api/cfs-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServices)))
            .andExpect(status().isBadRequest());

        List<CfsServices> cfsServicesList = cfsServicesRepository.findAll();
        assertThat(cfsServicesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkServiceNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServicesRepository.findAll().size();
        // set the field null
        cfsServices.setServiceName(null);

        // Create the CfsServices, which fails.

        restCfsServicesMockMvc.perform(post("/api/cfs-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServices)))
            .andExpect(status().isBadRequest());

        List<CfsServices> cfsServicesList = cfsServicesRepository.findAll();
        assertThat(cfsServicesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServicesRepository.findAll().size();
        // set the field null
        cfsServices.setCreatedDate(null);

        // Create the CfsServices, which fails.

        restCfsServicesMockMvc.perform(post("/api/cfs-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServices)))
            .andExpect(status().isBadRequest());

        List<CfsServices> cfsServicesList = cfsServicesRepository.findAll();
        assertThat(cfsServicesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServicesRepository.findAll().size();
        // set the field null
        cfsServices.setCreatedBy(null);

        // Create the CfsServices, which fails.

        restCfsServicesMockMvc.perform(post("/api/cfs-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServices)))
            .andExpect(status().isBadRequest());

        List<CfsServices> cfsServicesList = cfsServicesRepository.findAll();
        assertThat(cfsServicesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServicesRepository.findAll().size();
        // set the field null
        cfsServices.setLastUpdatedDate(null);

        // Create the CfsServices, which fails.

        restCfsServicesMockMvc.perform(post("/api/cfs-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServices)))
            .andExpect(status().isBadRequest());

        List<CfsServices> cfsServicesList = cfsServicesRepository.findAll();
        assertThat(cfsServicesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServicesRepository.findAll().size();
        // set the field null
        cfsServices.setLastUpdatedBy(null);

        // Create the CfsServices, which fails.

        restCfsServicesMockMvc.perform(post("/api/cfs-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServices)))
            .andExpect(status().isBadRequest());

        List<CfsServices> cfsServicesList = cfsServicesRepository.findAll();
        assertThat(cfsServicesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = cfsServicesRepository.findAll().size();
        // set the field null
        cfsServices.setTenantId(null);

        // Create the CfsServices, which fails.

        restCfsServicesMockMvc.perform(post("/api/cfs-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServices)))
            .andExpect(status().isBadRequest());

        List<CfsServices> cfsServicesList = cfsServicesRepository.findAll();
        assertThat(cfsServicesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCfsServices() throws Exception {
        // Initialize the database
        cfsServicesRepository.save(cfsServices);

        // Get all the cfsServicesList
        restCfsServicesMockMvc.perform(get("/api/cfs-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cfsServices.getId())))
            .andExpect(jsonPath("$.[*].serviceId").value(hasItem(DEFAULT_SERVICE_ID)))
            .andExpect(jsonPath("$.[*].serviceName").value(hasItem(DEFAULT_SERVICE_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getCfsServices() throws Exception {
        // Initialize the database
        cfsServicesRepository.save(cfsServices);

        // Get the cfsServices
        restCfsServicesMockMvc.perform(get("/api/cfs-services/{id}", cfsServices.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cfsServices.getId()))
            .andExpect(jsonPath("$.serviceId").value(DEFAULT_SERVICE_ID))
            .andExpect(jsonPath("$.serviceName").value(DEFAULT_SERVICE_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingCfsServices() throws Exception {
        // Get the cfsServices
        restCfsServicesMockMvc.perform(get("/api/cfs-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCfsServices() throws Exception {
        // Initialize the database
        cfsServicesService.save(cfsServices);

        int databaseSizeBeforeUpdate = cfsServicesRepository.findAll().size();

        // Update the cfsServices
        CfsServices updatedCfsServices = cfsServicesRepository.findById(cfsServices.getId()).get();
        updatedCfsServices
            .serviceId(UPDATED_SERVICE_ID)
            .serviceName(UPDATED_SERVICE_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restCfsServicesMockMvc.perform(put("/api/cfs-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCfsServices)))
            .andExpect(status().isOk());

        // Validate the CfsServices in the database
        List<CfsServices> cfsServicesList = cfsServicesRepository.findAll();
        assertThat(cfsServicesList).hasSize(databaseSizeBeforeUpdate);
        CfsServices testCfsServices = cfsServicesList.get(cfsServicesList.size() - 1);
        assertThat(testCfsServices.getServiceId()).isEqualTo(UPDATED_SERVICE_ID);
        assertThat(testCfsServices.getServiceName()).isEqualTo(UPDATED_SERVICE_NAME);
        assertThat(testCfsServices.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCfsServices.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCfsServices.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testCfsServices.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testCfsServices.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingCfsServices() throws Exception {
        int databaseSizeBeforeUpdate = cfsServicesRepository.findAll().size();

        // Create the CfsServices

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCfsServicesMockMvc.perform(put("/api/cfs-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cfsServices)))
            .andExpect(status().isBadRequest());

        // Validate the CfsServices in the database
        List<CfsServices> cfsServicesList = cfsServicesRepository.findAll();
        assertThat(cfsServicesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCfsServices() throws Exception {
        // Initialize the database
        cfsServicesService.save(cfsServices);

        int databaseSizeBeforeDelete = cfsServicesRepository.findAll().size();

        // Delete the cfsServices
        restCfsServicesMockMvc.perform(delete("/api/cfs-services/{id}", cfsServices.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CfsServices> cfsServicesList = cfsServicesRepository.findAll();
        assertThat(cfsServicesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
