package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.ResourceSpecification;
import com.hthk.crm.repository.ResourceSpecificationRepository;
import com.hthk.crm.service.ResourceSpecificationService;
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

import com.hthk.crm.domain.enumeration.ResourceType;
/**
 * Integration tests for the {@link ResourceSpecificationResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class ResourceSpecificationResourceIT {

    private static final String DEFAULT_RESOURCE_SPEC_ID = "AAAAAAAAAA";
    private static final String UPDATED_RESOURCE_SPEC_ID = "BBBBBBBBBB";

    private static final ResourceType DEFAULT_RESOURCE_TYPE = ResourceType.CORE_SUB;
    private static final ResourceType UPDATED_RESOURCE_TYPE = ResourceType.SERVICE;

    private static final String DEFAULT_SERVICE_SPEC_ID = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_SPEC_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RFS = "AAAAAAAAAA";
    private static final String UPDATED_RFS = "BBBBBBBBBB";

    private static final String DEFAULT_RFS_PARMS = "AAAAAAAAAA";
    private static final String UPDATED_RFS_PARMS = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

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
    private ResourceSpecificationRepository resourceSpecificationRepository;

    @Autowired
    private ResourceSpecificationService resourceSpecificationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restResourceSpecificationMockMvc;

    private ResourceSpecification resourceSpecification;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ResourceSpecificationResource resourceSpecificationResource = new ResourceSpecificationResource(resourceSpecificationService);
        this.restResourceSpecificationMockMvc = MockMvcBuilders.standaloneSetup(resourceSpecificationResource)
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
    public static ResourceSpecification createEntity() {
        ResourceSpecification resourceSpecification = new ResourceSpecification()
            .resourceSpecId(DEFAULT_RESOURCE_SPEC_ID)
            .resourceType(DEFAULT_RESOURCE_TYPE)
            .serviceSpecId(DEFAULT_SERVICE_SPEC_ID)
            .rfs(DEFAULT_RFS)
            .rfsParms(DEFAULT_RFS_PARMS)
            .remarks(DEFAULT_REMARKS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return resourceSpecification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceSpecification createUpdatedEntity() {
        ResourceSpecification resourceSpecification = new ResourceSpecification()
            .resourceSpecId(UPDATED_RESOURCE_SPEC_ID)
            .resourceType(UPDATED_RESOURCE_TYPE)
            .serviceSpecId(UPDATED_SERVICE_SPEC_ID)
            .rfs(UPDATED_RFS)
            .rfsParms(UPDATED_RFS_PARMS)
            .remarks(UPDATED_REMARKS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return resourceSpecification;
    }

    @BeforeEach
    public void initTest() {
        resourceSpecificationRepository.deleteAll();
        resourceSpecification = createEntity();
    }

    @Test
    public void createResourceSpecification() throws Exception {
        int databaseSizeBeforeCreate = resourceSpecificationRepository.findAll().size();

        // Create the ResourceSpecification
        restResourceSpecificationMockMvc.perform(post("/api/resource-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resourceSpecification)))
            .andExpect(status().isCreated());

        // Validate the ResourceSpecification in the database
        List<ResourceSpecification> resourceSpecificationList = resourceSpecificationRepository.findAll();
        assertThat(resourceSpecificationList).hasSize(databaseSizeBeforeCreate + 1);
        ResourceSpecification testResourceSpecification = resourceSpecificationList.get(resourceSpecificationList.size() - 1);
        assertThat(testResourceSpecification.getResourceSpecId()).isEqualTo(DEFAULT_RESOURCE_SPEC_ID);
        assertThat(testResourceSpecification.getResourceType()).isEqualTo(DEFAULT_RESOURCE_TYPE);
        assertThat(testResourceSpecification.getServiceSpecId()).isEqualTo(DEFAULT_SERVICE_SPEC_ID);
        assertThat(testResourceSpecification.getRfs()).isEqualTo(DEFAULT_RFS);
        assertThat(testResourceSpecification.getRfsParms()).isEqualTo(DEFAULT_RFS_PARMS);
        assertThat(testResourceSpecification.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testResourceSpecification.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testResourceSpecification.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testResourceSpecification.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testResourceSpecification.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testResourceSpecification.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createResourceSpecificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = resourceSpecificationRepository.findAll().size();

        // Create the ResourceSpecification with an existing ID
        resourceSpecification.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restResourceSpecificationMockMvc.perform(post("/api/resource-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resourceSpecification)))
            .andExpect(status().isBadRequest());

        // Validate the ResourceSpecification in the database
        List<ResourceSpecification> resourceSpecificationList = resourceSpecificationRepository.findAll();
        assertThat(resourceSpecificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkResourceSpecIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = resourceSpecificationRepository.findAll().size();
        // set the field null
        resourceSpecification.setResourceSpecId(null);

        // Create the ResourceSpecification, which fails.

        restResourceSpecificationMockMvc.perform(post("/api/resource-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resourceSpecification)))
            .andExpect(status().isBadRequest());

        List<ResourceSpecification> resourceSpecificationList = resourceSpecificationRepository.findAll();
        assertThat(resourceSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkResourceTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = resourceSpecificationRepository.findAll().size();
        // set the field null
        resourceSpecification.setResourceType(null);

        // Create the ResourceSpecification, which fails.

        restResourceSpecificationMockMvc.perform(post("/api/resource-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resourceSpecification)))
            .andExpect(status().isBadRequest());

        List<ResourceSpecification> resourceSpecificationList = resourceSpecificationRepository.findAll();
        assertThat(resourceSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkRfsIsRequired() throws Exception {
        int databaseSizeBeforeTest = resourceSpecificationRepository.findAll().size();
        // set the field null
        resourceSpecification.setRfs(null);

        // Create the ResourceSpecification, which fails.

        restResourceSpecificationMockMvc.perform(post("/api/resource-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resourceSpecification)))
            .andExpect(status().isBadRequest());

        List<ResourceSpecification> resourceSpecificationList = resourceSpecificationRepository.findAll();
        assertThat(resourceSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = resourceSpecificationRepository.findAll().size();
        // set the field null
        resourceSpecification.setCreatedDate(null);

        // Create the ResourceSpecification, which fails.

        restResourceSpecificationMockMvc.perform(post("/api/resource-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resourceSpecification)))
            .andExpect(status().isBadRequest());

        List<ResourceSpecification> resourceSpecificationList = resourceSpecificationRepository.findAll();
        assertThat(resourceSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = resourceSpecificationRepository.findAll().size();
        // set the field null
        resourceSpecification.setCreatedBy(null);

        // Create the ResourceSpecification, which fails.

        restResourceSpecificationMockMvc.perform(post("/api/resource-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resourceSpecification)))
            .andExpect(status().isBadRequest());

        List<ResourceSpecification> resourceSpecificationList = resourceSpecificationRepository.findAll();
        assertThat(resourceSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = resourceSpecificationRepository.findAll().size();
        // set the field null
        resourceSpecification.setLastUpdatedDate(null);

        // Create the ResourceSpecification, which fails.

        restResourceSpecificationMockMvc.perform(post("/api/resource-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resourceSpecification)))
            .andExpect(status().isBadRequest());

        List<ResourceSpecification> resourceSpecificationList = resourceSpecificationRepository.findAll();
        assertThat(resourceSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = resourceSpecificationRepository.findAll().size();
        // set the field null
        resourceSpecification.setLastUpdatedBy(null);

        // Create the ResourceSpecification, which fails.

        restResourceSpecificationMockMvc.perform(post("/api/resource-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resourceSpecification)))
            .andExpect(status().isBadRequest());

        List<ResourceSpecification> resourceSpecificationList = resourceSpecificationRepository.findAll();
        assertThat(resourceSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = resourceSpecificationRepository.findAll().size();
        // set the field null
        resourceSpecification.setTenantId(null);

        // Create the ResourceSpecification, which fails.

        restResourceSpecificationMockMvc.perform(post("/api/resource-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resourceSpecification)))
            .andExpect(status().isBadRequest());

        List<ResourceSpecification> resourceSpecificationList = resourceSpecificationRepository.findAll();
        assertThat(resourceSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllResourceSpecifications() throws Exception {
        // Initialize the database
        resourceSpecificationRepository.save(resourceSpecification);

        // Get all the resourceSpecificationList
        restResourceSpecificationMockMvc.perform(get("/api/resource-specifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resourceSpecification.getId())))
            .andExpect(jsonPath("$.[*].resourceSpecId").value(hasItem(DEFAULT_RESOURCE_SPEC_ID)))
            .andExpect(jsonPath("$.[*].resourceType").value(hasItem(DEFAULT_RESOURCE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].serviceSpecId").value(hasItem(DEFAULT_SERVICE_SPEC_ID)))
            .andExpect(jsonPath("$.[*].rfs").value(hasItem(DEFAULT_RFS)))
            .andExpect(jsonPath("$.[*].rfsParms").value(hasItem(DEFAULT_RFS_PARMS)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getResourceSpecification() throws Exception {
        // Initialize the database
        resourceSpecificationRepository.save(resourceSpecification);

        // Get the resourceSpecification
        restResourceSpecificationMockMvc.perform(get("/api/resource-specifications/{id}", resourceSpecification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resourceSpecification.getId()))
            .andExpect(jsonPath("$.resourceSpecId").value(DEFAULT_RESOURCE_SPEC_ID))
            .andExpect(jsonPath("$.resourceType").value(DEFAULT_RESOURCE_TYPE.toString()))
            .andExpect(jsonPath("$.serviceSpecId").value(DEFAULT_SERVICE_SPEC_ID))
            .andExpect(jsonPath("$.rfs").value(DEFAULT_RFS))
            .andExpect(jsonPath("$.rfsParms").value(DEFAULT_RFS_PARMS))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingResourceSpecification() throws Exception {
        // Get the resourceSpecification
        restResourceSpecificationMockMvc.perform(get("/api/resource-specifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateResourceSpecification() throws Exception {
        // Initialize the database
        resourceSpecificationService.save(resourceSpecification);

        int databaseSizeBeforeUpdate = resourceSpecificationRepository.findAll().size();

        // Update the resourceSpecification
        ResourceSpecification updatedResourceSpecification = resourceSpecificationRepository.findById(resourceSpecification.getId()).get();
        updatedResourceSpecification
            .resourceSpecId(UPDATED_RESOURCE_SPEC_ID)
            .resourceType(UPDATED_RESOURCE_TYPE)
            .serviceSpecId(UPDATED_SERVICE_SPEC_ID)
            .rfs(UPDATED_RFS)
            .rfsParms(UPDATED_RFS_PARMS)
            .remarks(UPDATED_REMARKS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restResourceSpecificationMockMvc.perform(put("/api/resource-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedResourceSpecification)))
            .andExpect(status().isOk());

        // Validate the ResourceSpecification in the database
        List<ResourceSpecification> resourceSpecificationList = resourceSpecificationRepository.findAll();
        assertThat(resourceSpecificationList).hasSize(databaseSizeBeforeUpdate);
        ResourceSpecification testResourceSpecification = resourceSpecificationList.get(resourceSpecificationList.size() - 1);
        assertThat(testResourceSpecification.getResourceSpecId()).isEqualTo(UPDATED_RESOURCE_SPEC_ID);
        assertThat(testResourceSpecification.getResourceType()).isEqualTo(UPDATED_RESOURCE_TYPE);
        assertThat(testResourceSpecification.getServiceSpecId()).isEqualTo(UPDATED_SERVICE_SPEC_ID);
        assertThat(testResourceSpecification.getRfs()).isEqualTo(UPDATED_RFS);
        assertThat(testResourceSpecification.getRfsParms()).isEqualTo(UPDATED_RFS_PARMS);
        assertThat(testResourceSpecification.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testResourceSpecification.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testResourceSpecification.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testResourceSpecification.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testResourceSpecification.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testResourceSpecification.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingResourceSpecification() throws Exception {
        int databaseSizeBeforeUpdate = resourceSpecificationRepository.findAll().size();

        // Create the ResourceSpecification

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourceSpecificationMockMvc.perform(put("/api/resource-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resourceSpecification)))
            .andExpect(status().isBadRequest());

        // Validate the ResourceSpecification in the database
        List<ResourceSpecification> resourceSpecificationList = resourceSpecificationRepository.findAll();
        assertThat(resourceSpecificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteResourceSpecification() throws Exception {
        // Initialize the database
        resourceSpecificationService.save(resourceSpecification);

        int databaseSizeBeforeDelete = resourceSpecificationRepository.findAll().size();

        // Delete the resourceSpecification
        restResourceSpecificationMockMvc.perform(delete("/api/resource-specifications/{id}", resourceSpecification.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ResourceSpecification> resourceSpecificationList = resourceSpecificationRepository.findAll();
        assertThat(resourceSpecificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
