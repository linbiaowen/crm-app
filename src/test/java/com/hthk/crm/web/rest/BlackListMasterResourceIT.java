package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.BlackListMaster;
import com.hthk.crm.repository.BlackListMasterRepository;
import com.hthk.crm.service.BlackListMasterService;
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
 * Integration tests for the {@link BlackListMasterResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class BlackListMasterResourceIT {

    private static final String DEFAULT_BLACK_LIST_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BLACK_LIST_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BLACK_LIST_REASON = "AAAAAAAAAA";
    private static final String UPDATED_BLACK_LIST_REASON = "BBBBBBBBBB";

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
    private BlackListMasterRepository blackListMasterRepository;

    @Autowired
    private BlackListMasterService blackListMasterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restBlackListMasterMockMvc;

    private BlackListMaster blackListMaster;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BlackListMasterResource blackListMasterResource = new BlackListMasterResource(blackListMasterService);
        this.restBlackListMasterMockMvc = MockMvcBuilders.standaloneSetup(blackListMasterResource)
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
    public static BlackListMaster createEntity() {
        BlackListMaster blackListMaster = new BlackListMaster()
            .blackListCode(DEFAULT_BLACK_LIST_CODE)
            .blackListReason(DEFAULT_BLACK_LIST_REASON)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return blackListMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BlackListMaster createUpdatedEntity() {
        BlackListMaster blackListMaster = new BlackListMaster()
            .blackListCode(UPDATED_BLACK_LIST_CODE)
            .blackListReason(UPDATED_BLACK_LIST_REASON)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return blackListMaster;
    }

    @BeforeEach
    public void initTest() {
        blackListMasterRepository.deleteAll();
        blackListMaster = createEntity();
    }

    @Test
    public void createBlackListMaster() throws Exception {
        int databaseSizeBeforeCreate = blackListMasterRepository.findAll().size();

        // Create the BlackListMaster
        restBlackListMasterMockMvc.perform(post("/api/black-list-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blackListMaster)))
            .andExpect(status().isCreated());

        // Validate the BlackListMaster in the database
        List<BlackListMaster> blackListMasterList = blackListMasterRepository.findAll();
        assertThat(blackListMasterList).hasSize(databaseSizeBeforeCreate + 1);
        BlackListMaster testBlackListMaster = blackListMasterList.get(blackListMasterList.size() - 1);
        assertThat(testBlackListMaster.getBlackListCode()).isEqualTo(DEFAULT_BLACK_LIST_CODE);
        assertThat(testBlackListMaster.getBlackListReason()).isEqualTo(DEFAULT_BLACK_LIST_REASON);
        assertThat(testBlackListMaster.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testBlackListMaster.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testBlackListMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBlackListMaster.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBlackListMaster.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testBlackListMaster.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testBlackListMaster.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createBlackListMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blackListMasterRepository.findAll().size();

        // Create the BlackListMaster with an existing ID
        blackListMaster.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlackListMasterMockMvc.perform(post("/api/black-list-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blackListMaster)))
            .andExpect(status().isBadRequest());

        // Validate the BlackListMaster in the database
        List<BlackListMaster> blackListMasterList = blackListMasterRepository.findAll();
        assertThat(blackListMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkBlackListCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = blackListMasterRepository.findAll().size();
        // set the field null
        blackListMaster.setBlackListCode(null);

        // Create the BlackListMaster, which fails.

        restBlackListMasterMockMvc.perform(post("/api/black-list-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blackListMaster)))
            .andExpect(status().isBadRequest());

        List<BlackListMaster> blackListMasterList = blackListMasterRepository.findAll();
        assertThat(blackListMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkBlackListReasonIsRequired() throws Exception {
        int databaseSizeBeforeTest = blackListMasterRepository.findAll().size();
        // set the field null
        blackListMaster.setBlackListReason(null);

        // Create the BlackListMaster, which fails.

        restBlackListMasterMockMvc.perform(post("/api/black-list-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blackListMaster)))
            .andExpect(status().isBadRequest());

        List<BlackListMaster> blackListMasterList = blackListMasterRepository.findAll();
        assertThat(blackListMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = blackListMasterRepository.findAll().size();
        // set the field null
        blackListMaster.setCreatedDate(null);

        // Create the BlackListMaster, which fails.

        restBlackListMasterMockMvc.perform(post("/api/black-list-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blackListMaster)))
            .andExpect(status().isBadRequest());

        List<BlackListMaster> blackListMasterList = blackListMasterRepository.findAll();
        assertThat(blackListMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = blackListMasterRepository.findAll().size();
        // set the field null
        blackListMaster.setCreatedBy(null);

        // Create the BlackListMaster, which fails.

        restBlackListMasterMockMvc.perform(post("/api/black-list-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blackListMaster)))
            .andExpect(status().isBadRequest());

        List<BlackListMaster> blackListMasterList = blackListMasterRepository.findAll();
        assertThat(blackListMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = blackListMasterRepository.findAll().size();
        // set the field null
        blackListMaster.setLastUpdatedDate(null);

        // Create the BlackListMaster, which fails.

        restBlackListMasterMockMvc.perform(post("/api/black-list-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blackListMaster)))
            .andExpect(status().isBadRequest());

        List<BlackListMaster> blackListMasterList = blackListMasterRepository.findAll();
        assertThat(blackListMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = blackListMasterRepository.findAll().size();
        // set the field null
        blackListMaster.setLastUpdatedBy(null);

        // Create the BlackListMaster, which fails.

        restBlackListMasterMockMvc.perform(post("/api/black-list-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blackListMaster)))
            .andExpect(status().isBadRequest());

        List<BlackListMaster> blackListMasterList = blackListMasterRepository.findAll();
        assertThat(blackListMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = blackListMasterRepository.findAll().size();
        // set the field null
        blackListMaster.setTenantId(null);

        // Create the BlackListMaster, which fails.

        restBlackListMasterMockMvc.perform(post("/api/black-list-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blackListMaster)))
            .andExpect(status().isBadRequest());

        List<BlackListMaster> blackListMasterList = blackListMasterRepository.findAll();
        assertThat(blackListMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllBlackListMasters() throws Exception {
        // Initialize the database
        blackListMasterRepository.save(blackListMaster);

        // Get all the blackListMasterList
        restBlackListMasterMockMvc.perform(get("/api/black-list-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blackListMaster.getId())))
            .andExpect(jsonPath("$.[*].blackListCode").value(hasItem(DEFAULT_BLACK_LIST_CODE)))
            .andExpect(jsonPath("$.[*].blackListReason").value(hasItem(DEFAULT_BLACK_LIST_REASON)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getBlackListMaster() throws Exception {
        // Initialize the database
        blackListMasterRepository.save(blackListMaster);

        // Get the blackListMaster
        restBlackListMasterMockMvc.perform(get("/api/black-list-masters/{id}", blackListMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(blackListMaster.getId()))
            .andExpect(jsonPath("$.blackListCode").value(DEFAULT_BLACK_LIST_CODE))
            .andExpect(jsonPath("$.blackListReason").value(DEFAULT_BLACK_LIST_REASON))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingBlackListMaster() throws Exception {
        // Get the blackListMaster
        restBlackListMasterMockMvc.perform(get("/api/black-list-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBlackListMaster() throws Exception {
        // Initialize the database
        blackListMasterService.save(blackListMaster);

        int databaseSizeBeforeUpdate = blackListMasterRepository.findAll().size();

        // Update the blackListMaster
        BlackListMaster updatedBlackListMaster = blackListMasterRepository.findById(blackListMaster.getId()).get();
        updatedBlackListMaster
            .blackListCode(UPDATED_BLACK_LIST_CODE)
            .blackListReason(UPDATED_BLACK_LIST_REASON)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restBlackListMasterMockMvc.perform(put("/api/black-list-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBlackListMaster)))
            .andExpect(status().isOk());

        // Validate the BlackListMaster in the database
        List<BlackListMaster> blackListMasterList = blackListMasterRepository.findAll();
        assertThat(blackListMasterList).hasSize(databaseSizeBeforeUpdate);
        BlackListMaster testBlackListMaster = blackListMasterList.get(blackListMasterList.size() - 1);
        assertThat(testBlackListMaster.getBlackListCode()).isEqualTo(UPDATED_BLACK_LIST_CODE);
        assertThat(testBlackListMaster.getBlackListReason()).isEqualTo(UPDATED_BLACK_LIST_REASON);
        assertThat(testBlackListMaster.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testBlackListMaster.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testBlackListMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBlackListMaster.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBlackListMaster.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testBlackListMaster.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testBlackListMaster.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingBlackListMaster() throws Exception {
        int databaseSizeBeforeUpdate = blackListMasterRepository.findAll().size();

        // Create the BlackListMaster

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlackListMasterMockMvc.perform(put("/api/black-list-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blackListMaster)))
            .andExpect(status().isBadRequest());

        // Validate the BlackListMaster in the database
        List<BlackListMaster> blackListMasterList = blackListMasterRepository.findAll();
        assertThat(blackListMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteBlackListMaster() throws Exception {
        // Initialize the database
        blackListMasterService.save(blackListMaster);

        int databaseSizeBeforeDelete = blackListMasterRepository.findAll().size();

        // Delete the blackListMaster
        restBlackListMasterMockMvc.perform(delete("/api/black-list-masters/{id}", blackListMaster.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BlackListMaster> blackListMasterList = blackListMasterRepository.findAll();
        assertThat(blackListMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
