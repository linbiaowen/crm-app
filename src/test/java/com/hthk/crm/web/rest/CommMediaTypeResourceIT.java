package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.CommMediaType;
import com.hthk.crm.repository.CommMediaTypeRepository;
import com.hthk.crm.service.CommMediaTypeService;
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
 * Integration tests for the {@link CommMediaTypeResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class CommMediaTypeResourceIT {

    private static final String DEFAULT_OPTOUT_MEDIA_ID = "AAAAAAAAAA";
    private static final String UPDATED_OPTOUT_MEDIA_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MEDIA_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_MEDIA_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_MEDIA_TYPE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_MEDIA_TYPE_DESC = "BBBBBBBBBB";

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
    private CommMediaTypeRepository commMediaTypeRepository;

    @Autowired
    private CommMediaTypeService commMediaTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCommMediaTypeMockMvc;

    private CommMediaType commMediaType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CommMediaTypeResource commMediaTypeResource = new CommMediaTypeResource(commMediaTypeService);
        this.restCommMediaTypeMockMvc = MockMvcBuilders.standaloneSetup(commMediaTypeResource)
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
    public static CommMediaType createEntity() {
        CommMediaType commMediaType = new CommMediaType()
            .optoutMediaId(DEFAULT_OPTOUT_MEDIA_ID)
            .mediaType(DEFAULT_MEDIA_TYPE)
            .mediaTypeDesc(DEFAULT_MEDIA_TYPE_DESC)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return commMediaType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommMediaType createUpdatedEntity() {
        CommMediaType commMediaType = new CommMediaType()
            .optoutMediaId(UPDATED_OPTOUT_MEDIA_ID)
            .mediaType(UPDATED_MEDIA_TYPE)
            .mediaTypeDesc(UPDATED_MEDIA_TYPE_DESC)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return commMediaType;
    }

    @BeforeEach
    public void initTest() {
        commMediaTypeRepository.deleteAll();
        commMediaType = createEntity();
    }

    @Test
    public void createCommMediaType() throws Exception {
        int databaseSizeBeforeCreate = commMediaTypeRepository.findAll().size();

        // Create the CommMediaType
        restCommMediaTypeMockMvc.perform(post("/api/comm-media-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commMediaType)))
            .andExpect(status().isCreated());

        // Validate the CommMediaType in the database
        List<CommMediaType> commMediaTypeList = commMediaTypeRepository.findAll();
        assertThat(commMediaTypeList).hasSize(databaseSizeBeforeCreate + 1);
        CommMediaType testCommMediaType = commMediaTypeList.get(commMediaTypeList.size() - 1);
        assertThat(testCommMediaType.getOptoutMediaId()).isEqualTo(DEFAULT_OPTOUT_MEDIA_ID);
        assertThat(testCommMediaType.getMediaType()).isEqualTo(DEFAULT_MEDIA_TYPE);
        assertThat(testCommMediaType.getMediaTypeDesc()).isEqualTo(DEFAULT_MEDIA_TYPE_DESC);
        assertThat(testCommMediaType.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testCommMediaType.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testCommMediaType.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCommMediaType.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCommMediaType.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testCommMediaType.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testCommMediaType.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createCommMediaTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commMediaTypeRepository.findAll().size();

        // Create the CommMediaType with an existing ID
        commMediaType.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommMediaTypeMockMvc.perform(post("/api/comm-media-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commMediaType)))
            .andExpect(status().isBadRequest());

        // Validate the CommMediaType in the database
        List<CommMediaType> commMediaTypeList = commMediaTypeRepository.findAll();
        assertThat(commMediaTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkOptoutMediaIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = commMediaTypeRepository.findAll().size();
        // set the field null
        commMediaType.setOptoutMediaId(null);

        // Create the CommMediaType, which fails.

        restCommMediaTypeMockMvc.perform(post("/api/comm-media-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commMediaType)))
            .andExpect(status().isBadRequest());

        List<CommMediaType> commMediaTypeList = commMediaTypeRepository.findAll();
        assertThat(commMediaTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkMediaTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = commMediaTypeRepository.findAll().size();
        // set the field null
        commMediaType.setMediaType(null);

        // Create the CommMediaType, which fails.

        restCommMediaTypeMockMvc.perform(post("/api/comm-media-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commMediaType)))
            .andExpect(status().isBadRequest());

        List<CommMediaType> commMediaTypeList = commMediaTypeRepository.findAll();
        assertThat(commMediaTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = commMediaTypeRepository.findAll().size();
        // set the field null
        commMediaType.setCreatedDate(null);

        // Create the CommMediaType, which fails.

        restCommMediaTypeMockMvc.perform(post("/api/comm-media-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commMediaType)))
            .andExpect(status().isBadRequest());

        List<CommMediaType> commMediaTypeList = commMediaTypeRepository.findAll();
        assertThat(commMediaTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = commMediaTypeRepository.findAll().size();
        // set the field null
        commMediaType.setCreatedBy(null);

        // Create the CommMediaType, which fails.

        restCommMediaTypeMockMvc.perform(post("/api/comm-media-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commMediaType)))
            .andExpect(status().isBadRequest());

        List<CommMediaType> commMediaTypeList = commMediaTypeRepository.findAll();
        assertThat(commMediaTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = commMediaTypeRepository.findAll().size();
        // set the field null
        commMediaType.setLastUpdatedDate(null);

        // Create the CommMediaType, which fails.

        restCommMediaTypeMockMvc.perform(post("/api/comm-media-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commMediaType)))
            .andExpect(status().isBadRequest());

        List<CommMediaType> commMediaTypeList = commMediaTypeRepository.findAll();
        assertThat(commMediaTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = commMediaTypeRepository.findAll().size();
        // set the field null
        commMediaType.setLastUpdatedBy(null);

        // Create the CommMediaType, which fails.

        restCommMediaTypeMockMvc.perform(post("/api/comm-media-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commMediaType)))
            .andExpect(status().isBadRequest());

        List<CommMediaType> commMediaTypeList = commMediaTypeRepository.findAll();
        assertThat(commMediaTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = commMediaTypeRepository.findAll().size();
        // set the field null
        commMediaType.setTenantId(null);

        // Create the CommMediaType, which fails.

        restCommMediaTypeMockMvc.perform(post("/api/comm-media-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commMediaType)))
            .andExpect(status().isBadRequest());

        List<CommMediaType> commMediaTypeList = commMediaTypeRepository.findAll();
        assertThat(commMediaTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCommMediaTypes() throws Exception {
        // Initialize the database
        commMediaTypeRepository.save(commMediaType);

        // Get all the commMediaTypeList
        restCommMediaTypeMockMvc.perform(get("/api/comm-media-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commMediaType.getId())))
            .andExpect(jsonPath("$.[*].optoutMediaId").value(hasItem(DEFAULT_OPTOUT_MEDIA_ID)))
            .andExpect(jsonPath("$.[*].mediaType").value(hasItem(DEFAULT_MEDIA_TYPE)))
            .andExpect(jsonPath("$.[*].mediaTypeDesc").value(hasItem(DEFAULT_MEDIA_TYPE_DESC)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getCommMediaType() throws Exception {
        // Initialize the database
        commMediaTypeRepository.save(commMediaType);

        // Get the commMediaType
        restCommMediaTypeMockMvc.perform(get("/api/comm-media-types/{id}", commMediaType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(commMediaType.getId()))
            .andExpect(jsonPath("$.optoutMediaId").value(DEFAULT_OPTOUT_MEDIA_ID))
            .andExpect(jsonPath("$.mediaType").value(DEFAULT_MEDIA_TYPE))
            .andExpect(jsonPath("$.mediaTypeDesc").value(DEFAULT_MEDIA_TYPE_DESC))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingCommMediaType() throws Exception {
        // Get the commMediaType
        restCommMediaTypeMockMvc.perform(get("/api/comm-media-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCommMediaType() throws Exception {
        // Initialize the database
        commMediaTypeService.save(commMediaType);

        int databaseSizeBeforeUpdate = commMediaTypeRepository.findAll().size();

        // Update the commMediaType
        CommMediaType updatedCommMediaType = commMediaTypeRepository.findById(commMediaType.getId()).get();
        updatedCommMediaType
            .optoutMediaId(UPDATED_OPTOUT_MEDIA_ID)
            .mediaType(UPDATED_MEDIA_TYPE)
            .mediaTypeDesc(UPDATED_MEDIA_TYPE_DESC)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restCommMediaTypeMockMvc.perform(put("/api/comm-media-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCommMediaType)))
            .andExpect(status().isOk());

        // Validate the CommMediaType in the database
        List<CommMediaType> commMediaTypeList = commMediaTypeRepository.findAll();
        assertThat(commMediaTypeList).hasSize(databaseSizeBeforeUpdate);
        CommMediaType testCommMediaType = commMediaTypeList.get(commMediaTypeList.size() - 1);
        assertThat(testCommMediaType.getOptoutMediaId()).isEqualTo(UPDATED_OPTOUT_MEDIA_ID);
        assertThat(testCommMediaType.getMediaType()).isEqualTo(UPDATED_MEDIA_TYPE);
        assertThat(testCommMediaType.getMediaTypeDesc()).isEqualTo(UPDATED_MEDIA_TYPE_DESC);
        assertThat(testCommMediaType.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testCommMediaType.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testCommMediaType.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCommMediaType.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCommMediaType.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testCommMediaType.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testCommMediaType.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingCommMediaType() throws Exception {
        int databaseSizeBeforeUpdate = commMediaTypeRepository.findAll().size();

        // Create the CommMediaType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommMediaTypeMockMvc.perform(put("/api/comm-media-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commMediaType)))
            .andExpect(status().isBadRequest());

        // Validate the CommMediaType in the database
        List<CommMediaType> commMediaTypeList = commMediaTypeRepository.findAll();
        assertThat(commMediaTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCommMediaType() throws Exception {
        // Initialize the database
        commMediaTypeService.save(commMediaType);

        int databaseSizeBeforeDelete = commMediaTypeRepository.findAll().size();

        // Delete the commMediaType
        restCommMediaTypeMockMvc.perform(delete("/api/comm-media-types/{id}", commMediaType.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CommMediaType> commMediaTypeList = commMediaTypeRepository.findAll();
        assertThat(commMediaTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
