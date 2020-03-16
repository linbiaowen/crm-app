package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.CustCommOptoutMaster;
import com.hthk.crm.repository.CustCommOptoutMasterRepository;
import com.hthk.crm.service.CustCommOptoutMasterService;
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
 * Integration tests for the {@link CustCommOptoutMasterResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class CustCommOptoutMasterResourceIT {

    private static final String DEFAULT_OPTOUT_ID = "AAAAAAAAAA";
    private static final String UPDATED_OPTOUT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_ACCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUST_ACCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIPTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_MOB_NBR = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_MOB_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_OPTOUT_TYPE_ID = "AAAAAAAAAA";
    private static final String UPDATED_OPTOUT_TYPE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OPTOUT_MEDIA_ID = "AAAAAAAAAA";
    private static final String UPDATED_OPTOUT_MEDIA_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OPTOUT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_OPTOUT_STATUS = "BBBBBBBBBB";

    private static final Instant DEFAULT_OPTOUT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OPTOUT_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_OPTOUT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OPTOUT_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

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
    private CustCommOptoutMasterRepository custCommOptoutMasterRepository;

    @Autowired
    private CustCommOptoutMasterService custCommOptoutMasterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCustCommOptoutMasterMockMvc;

    private CustCommOptoutMaster custCommOptoutMaster;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustCommOptoutMasterResource custCommOptoutMasterResource = new CustCommOptoutMasterResource(custCommOptoutMasterService);
        this.restCustCommOptoutMasterMockMvc = MockMvcBuilders.standaloneSetup(custCommOptoutMasterResource)
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
    public static CustCommOptoutMaster createEntity() {
        CustCommOptoutMaster custCommOptoutMaster = new CustCommOptoutMaster()
            .optoutId(DEFAULT_OPTOUT_ID)
            .custAcctId(DEFAULT_CUST_ACCT_ID)
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .primaryMobNbr(DEFAULT_PRIMARY_MOB_NBR)
            .optoutTypeId(DEFAULT_OPTOUT_TYPE_ID)
            .optoutMediaId(DEFAULT_OPTOUT_MEDIA_ID)
            .optoutStatus(DEFAULT_OPTOUT_STATUS)
            .optoutStartDate(DEFAULT_OPTOUT_START_DATE)
            .optoutEndDate(DEFAULT_OPTOUT_END_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return custCommOptoutMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustCommOptoutMaster createUpdatedEntity() {
        CustCommOptoutMaster custCommOptoutMaster = new CustCommOptoutMaster()
            .optoutId(UPDATED_OPTOUT_ID)
            .custAcctId(UPDATED_CUST_ACCT_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .primaryMobNbr(UPDATED_PRIMARY_MOB_NBR)
            .optoutTypeId(UPDATED_OPTOUT_TYPE_ID)
            .optoutMediaId(UPDATED_OPTOUT_MEDIA_ID)
            .optoutStatus(UPDATED_OPTOUT_STATUS)
            .optoutStartDate(UPDATED_OPTOUT_START_DATE)
            .optoutEndDate(UPDATED_OPTOUT_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return custCommOptoutMaster;
    }

    @BeforeEach
    public void initTest() {
        custCommOptoutMasterRepository.deleteAll();
        custCommOptoutMaster = createEntity();
    }

    @Test
    public void createCustCommOptoutMaster() throws Exception {
        int databaseSizeBeforeCreate = custCommOptoutMasterRepository.findAll().size();

        // Create the CustCommOptoutMaster
        restCustCommOptoutMasterMockMvc.perform(post("/api/cust-comm-optout-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custCommOptoutMaster)))
            .andExpect(status().isCreated());

        // Validate the CustCommOptoutMaster in the database
        List<CustCommOptoutMaster> custCommOptoutMasterList = custCommOptoutMasterRepository.findAll();
        assertThat(custCommOptoutMasterList).hasSize(databaseSizeBeforeCreate + 1);
        CustCommOptoutMaster testCustCommOptoutMaster = custCommOptoutMasterList.get(custCommOptoutMasterList.size() - 1);
        assertThat(testCustCommOptoutMaster.getOptoutId()).isEqualTo(DEFAULT_OPTOUT_ID);
        assertThat(testCustCommOptoutMaster.getCustAcctId()).isEqualTo(DEFAULT_CUST_ACCT_ID);
        assertThat(testCustCommOptoutMaster.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testCustCommOptoutMaster.getPrimaryMobNbr()).isEqualTo(DEFAULT_PRIMARY_MOB_NBR);
        assertThat(testCustCommOptoutMaster.getOptoutTypeId()).isEqualTo(DEFAULT_OPTOUT_TYPE_ID);
        assertThat(testCustCommOptoutMaster.getOptoutMediaId()).isEqualTo(DEFAULT_OPTOUT_MEDIA_ID);
        assertThat(testCustCommOptoutMaster.getOptoutStatus()).isEqualTo(DEFAULT_OPTOUT_STATUS);
        assertThat(testCustCommOptoutMaster.getOptoutStartDate()).isEqualTo(DEFAULT_OPTOUT_START_DATE);
        assertThat(testCustCommOptoutMaster.getOptoutEndDate()).isEqualTo(DEFAULT_OPTOUT_END_DATE);
        assertThat(testCustCommOptoutMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCustCommOptoutMaster.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustCommOptoutMaster.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testCustCommOptoutMaster.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testCustCommOptoutMaster.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createCustCommOptoutMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = custCommOptoutMasterRepository.findAll().size();

        // Create the CustCommOptoutMaster with an existing ID
        custCommOptoutMaster.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustCommOptoutMasterMockMvc.perform(post("/api/cust-comm-optout-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custCommOptoutMaster)))
            .andExpect(status().isBadRequest());

        // Validate the CustCommOptoutMaster in the database
        List<CustCommOptoutMaster> custCommOptoutMasterList = custCommOptoutMasterRepository.findAll();
        assertThat(custCommOptoutMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkOptoutIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custCommOptoutMasterRepository.findAll().size();
        // set the field null
        custCommOptoutMaster.setOptoutId(null);

        // Create the CustCommOptoutMaster, which fails.

        restCustCommOptoutMasterMockMvc.perform(post("/api/cust-comm-optout-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custCommOptoutMaster)))
            .andExpect(status().isBadRequest());

        List<CustCommOptoutMaster> custCommOptoutMasterList = custCommOptoutMasterRepository.findAll();
        assertThat(custCommOptoutMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOptoutTypeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custCommOptoutMasterRepository.findAll().size();
        // set the field null
        custCommOptoutMaster.setOptoutTypeId(null);

        // Create the CustCommOptoutMaster, which fails.

        restCustCommOptoutMasterMockMvc.perform(post("/api/cust-comm-optout-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custCommOptoutMaster)))
            .andExpect(status().isBadRequest());

        List<CustCommOptoutMaster> custCommOptoutMasterList = custCommOptoutMasterRepository.findAll();
        assertThat(custCommOptoutMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOptoutMediaIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custCommOptoutMasterRepository.findAll().size();
        // set the field null
        custCommOptoutMaster.setOptoutMediaId(null);

        // Create the CustCommOptoutMaster, which fails.

        restCustCommOptoutMasterMockMvc.perform(post("/api/cust-comm-optout-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custCommOptoutMaster)))
            .andExpect(status().isBadRequest());

        List<CustCommOptoutMaster> custCommOptoutMasterList = custCommOptoutMasterRepository.findAll();
        assertThat(custCommOptoutMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOptoutStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = custCommOptoutMasterRepository.findAll().size();
        // set the field null
        custCommOptoutMaster.setOptoutStatus(null);

        // Create the CustCommOptoutMaster, which fails.

        restCustCommOptoutMasterMockMvc.perform(post("/api/cust-comm-optout-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custCommOptoutMaster)))
            .andExpect(status().isBadRequest());

        List<CustCommOptoutMaster> custCommOptoutMasterList = custCommOptoutMasterRepository.findAll();
        assertThat(custCommOptoutMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = custCommOptoutMasterRepository.findAll().size();
        // set the field null
        custCommOptoutMaster.setCreatedDate(null);

        // Create the CustCommOptoutMaster, which fails.

        restCustCommOptoutMasterMockMvc.perform(post("/api/cust-comm-optout-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custCommOptoutMaster)))
            .andExpect(status().isBadRequest());

        List<CustCommOptoutMaster> custCommOptoutMasterList = custCommOptoutMasterRepository.findAll();
        assertThat(custCommOptoutMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = custCommOptoutMasterRepository.findAll().size();
        // set the field null
        custCommOptoutMaster.setCreatedBy(null);

        // Create the CustCommOptoutMaster, which fails.

        restCustCommOptoutMasterMockMvc.perform(post("/api/cust-comm-optout-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custCommOptoutMaster)))
            .andExpect(status().isBadRequest());

        List<CustCommOptoutMaster> custCommOptoutMasterList = custCommOptoutMasterRepository.findAll();
        assertThat(custCommOptoutMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = custCommOptoutMasterRepository.findAll().size();
        // set the field null
        custCommOptoutMaster.setLastUpdatedDate(null);

        // Create the CustCommOptoutMaster, which fails.

        restCustCommOptoutMasterMockMvc.perform(post("/api/cust-comm-optout-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custCommOptoutMaster)))
            .andExpect(status().isBadRequest());

        List<CustCommOptoutMaster> custCommOptoutMasterList = custCommOptoutMasterRepository.findAll();
        assertThat(custCommOptoutMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = custCommOptoutMasterRepository.findAll().size();
        // set the field null
        custCommOptoutMaster.setLastUpdatedBy(null);

        // Create the CustCommOptoutMaster, which fails.

        restCustCommOptoutMasterMockMvc.perform(post("/api/cust-comm-optout-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custCommOptoutMaster)))
            .andExpect(status().isBadRequest());

        List<CustCommOptoutMaster> custCommOptoutMasterList = custCommOptoutMasterRepository.findAll();
        assertThat(custCommOptoutMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custCommOptoutMasterRepository.findAll().size();
        // set the field null
        custCommOptoutMaster.setTenantId(null);

        // Create the CustCommOptoutMaster, which fails.

        restCustCommOptoutMasterMockMvc.perform(post("/api/cust-comm-optout-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custCommOptoutMaster)))
            .andExpect(status().isBadRequest());

        List<CustCommOptoutMaster> custCommOptoutMasterList = custCommOptoutMasterRepository.findAll();
        assertThat(custCommOptoutMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCustCommOptoutMasters() throws Exception {
        // Initialize the database
        custCommOptoutMasterRepository.save(custCommOptoutMaster);

        // Get all the custCommOptoutMasterList
        restCustCommOptoutMasterMockMvc.perform(get("/api/cust-comm-optout-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(custCommOptoutMaster.getId())))
            .andExpect(jsonPath("$.[*].optoutId").value(hasItem(DEFAULT_OPTOUT_ID)))
            .andExpect(jsonPath("$.[*].custAcctId").value(hasItem(DEFAULT_CUST_ACCT_ID)))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID)))
            .andExpect(jsonPath("$.[*].primaryMobNbr").value(hasItem(DEFAULT_PRIMARY_MOB_NBR)))
            .andExpect(jsonPath("$.[*].optoutTypeId").value(hasItem(DEFAULT_OPTOUT_TYPE_ID)))
            .andExpect(jsonPath("$.[*].optoutMediaId").value(hasItem(DEFAULT_OPTOUT_MEDIA_ID)))
            .andExpect(jsonPath("$.[*].optoutStatus").value(hasItem(DEFAULT_OPTOUT_STATUS)))
            .andExpect(jsonPath("$.[*].optoutStartDate").value(hasItem(DEFAULT_OPTOUT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].optoutEndDate").value(hasItem(DEFAULT_OPTOUT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getCustCommOptoutMaster() throws Exception {
        // Initialize the database
        custCommOptoutMasterRepository.save(custCommOptoutMaster);

        // Get the custCommOptoutMaster
        restCustCommOptoutMasterMockMvc.perform(get("/api/cust-comm-optout-masters/{id}", custCommOptoutMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(custCommOptoutMaster.getId()))
            .andExpect(jsonPath("$.optoutId").value(DEFAULT_OPTOUT_ID))
            .andExpect(jsonPath("$.custAcctId").value(DEFAULT_CUST_ACCT_ID))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID))
            .andExpect(jsonPath("$.primaryMobNbr").value(DEFAULT_PRIMARY_MOB_NBR))
            .andExpect(jsonPath("$.optoutTypeId").value(DEFAULT_OPTOUT_TYPE_ID))
            .andExpect(jsonPath("$.optoutMediaId").value(DEFAULT_OPTOUT_MEDIA_ID))
            .andExpect(jsonPath("$.optoutStatus").value(DEFAULT_OPTOUT_STATUS))
            .andExpect(jsonPath("$.optoutStartDate").value(DEFAULT_OPTOUT_START_DATE.toString()))
            .andExpect(jsonPath("$.optoutEndDate").value(DEFAULT_OPTOUT_END_DATE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingCustCommOptoutMaster() throws Exception {
        // Get the custCommOptoutMaster
        restCustCommOptoutMasterMockMvc.perform(get("/api/cust-comm-optout-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCustCommOptoutMaster() throws Exception {
        // Initialize the database
        custCommOptoutMasterService.save(custCommOptoutMaster);

        int databaseSizeBeforeUpdate = custCommOptoutMasterRepository.findAll().size();

        // Update the custCommOptoutMaster
        CustCommOptoutMaster updatedCustCommOptoutMaster = custCommOptoutMasterRepository.findById(custCommOptoutMaster.getId()).get();
        updatedCustCommOptoutMaster
            .optoutId(UPDATED_OPTOUT_ID)
            .custAcctId(UPDATED_CUST_ACCT_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .primaryMobNbr(UPDATED_PRIMARY_MOB_NBR)
            .optoutTypeId(UPDATED_OPTOUT_TYPE_ID)
            .optoutMediaId(UPDATED_OPTOUT_MEDIA_ID)
            .optoutStatus(UPDATED_OPTOUT_STATUS)
            .optoutStartDate(UPDATED_OPTOUT_START_DATE)
            .optoutEndDate(UPDATED_OPTOUT_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restCustCommOptoutMasterMockMvc.perform(put("/api/cust-comm-optout-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCustCommOptoutMaster)))
            .andExpect(status().isOk());

        // Validate the CustCommOptoutMaster in the database
        List<CustCommOptoutMaster> custCommOptoutMasterList = custCommOptoutMasterRepository.findAll();
        assertThat(custCommOptoutMasterList).hasSize(databaseSizeBeforeUpdate);
        CustCommOptoutMaster testCustCommOptoutMaster = custCommOptoutMasterList.get(custCommOptoutMasterList.size() - 1);
        assertThat(testCustCommOptoutMaster.getOptoutId()).isEqualTo(UPDATED_OPTOUT_ID);
        assertThat(testCustCommOptoutMaster.getCustAcctId()).isEqualTo(UPDATED_CUST_ACCT_ID);
        assertThat(testCustCommOptoutMaster.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testCustCommOptoutMaster.getPrimaryMobNbr()).isEqualTo(UPDATED_PRIMARY_MOB_NBR);
        assertThat(testCustCommOptoutMaster.getOptoutTypeId()).isEqualTo(UPDATED_OPTOUT_TYPE_ID);
        assertThat(testCustCommOptoutMaster.getOptoutMediaId()).isEqualTo(UPDATED_OPTOUT_MEDIA_ID);
        assertThat(testCustCommOptoutMaster.getOptoutStatus()).isEqualTo(UPDATED_OPTOUT_STATUS);
        assertThat(testCustCommOptoutMaster.getOptoutStartDate()).isEqualTo(UPDATED_OPTOUT_START_DATE);
        assertThat(testCustCommOptoutMaster.getOptoutEndDate()).isEqualTo(UPDATED_OPTOUT_END_DATE);
        assertThat(testCustCommOptoutMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCustCommOptoutMaster.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustCommOptoutMaster.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testCustCommOptoutMaster.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testCustCommOptoutMaster.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingCustCommOptoutMaster() throws Exception {
        int databaseSizeBeforeUpdate = custCommOptoutMasterRepository.findAll().size();

        // Create the CustCommOptoutMaster

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustCommOptoutMasterMockMvc.perform(put("/api/cust-comm-optout-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custCommOptoutMaster)))
            .andExpect(status().isBadRequest());

        // Validate the CustCommOptoutMaster in the database
        List<CustCommOptoutMaster> custCommOptoutMasterList = custCommOptoutMasterRepository.findAll();
        assertThat(custCommOptoutMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCustCommOptoutMaster() throws Exception {
        // Initialize the database
        custCommOptoutMasterService.save(custCommOptoutMaster);

        int databaseSizeBeforeDelete = custCommOptoutMasterRepository.findAll().size();

        // Delete the custCommOptoutMaster
        restCustCommOptoutMasterMockMvc.perform(delete("/api/cust-comm-optout-masters/{id}", custCommOptoutMaster.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustCommOptoutMaster> custCommOptoutMasterList = custCommOptoutMasterRepository.findAll();
        assertThat(custCommOptoutMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
