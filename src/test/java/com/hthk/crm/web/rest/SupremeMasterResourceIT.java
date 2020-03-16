package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.SupremeMaster;
import com.hthk.crm.repository.SupremeMasterRepository;
import com.hthk.crm.service.SupremeMasterService;
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

import com.hthk.crm.domain.enumeration.MemberShipServiceType;
/**
 * Integration tests for the {@link SupremeMasterResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class SupremeMasterResourceIT {

    private static final Long DEFAULT_SUPREME_SEQ_ID = 1L;
    private static final Long UPDATED_SUPREME_SEQ_ID = 2L;

    private static final String DEFAULT_SUBSCRIPTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MSISDN = "AAAAAAAAAA";
    private static final String UPDATED_MSISDN = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final MemberShipServiceType DEFAULT_MEMBERSHIP_SERVICE_TYPE = MemberShipServiceType.VIP;
    private static final MemberShipServiceType UPDATED_MEMBERSHIP_SERVICE_TYPE = MemberShipServiceType.GOLD;

    private static final String DEFAULT_PE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PERSONAL_EXEC_DIRECT_LINE = "AAAAAAAAAA";
    private static final String UPDATED_PERSONAL_EXEC_DIRECT_LINE = "BBBBBBBBBB";

    private static final String DEFAULT_PERSONAL_EXEC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PERSONAL_EXEC_NAME = "BBBBBBBBBB";

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
    private SupremeMasterRepository supremeMasterRepository;

    @Autowired
    private SupremeMasterService supremeMasterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restSupremeMasterMockMvc;

    private SupremeMaster supremeMaster;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SupremeMasterResource supremeMasterResource = new SupremeMasterResource(supremeMasterService);
        this.restSupremeMasterMockMvc = MockMvcBuilders.standaloneSetup(supremeMasterResource)
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
    public static SupremeMaster createEntity() {
        SupremeMaster supremeMaster = new SupremeMaster()
            .supremeSeqId(DEFAULT_SUPREME_SEQ_ID)
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .msisdn(DEFAULT_MSISDN)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .membershipServiceType(DEFAULT_MEMBERSHIP_SERVICE_TYPE)
            .peCode(DEFAULT_PE_CODE)
            .personalExecDirectLine(DEFAULT_PERSONAL_EXEC_DIRECT_LINE)
            .personalExecName(DEFAULT_PERSONAL_EXEC_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return supremeMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SupremeMaster createUpdatedEntity() {
        SupremeMaster supremeMaster = new SupremeMaster()
            .supremeSeqId(UPDATED_SUPREME_SEQ_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .msisdn(UPDATED_MSISDN)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .membershipServiceType(UPDATED_MEMBERSHIP_SERVICE_TYPE)
            .peCode(UPDATED_PE_CODE)
            .personalExecDirectLine(UPDATED_PERSONAL_EXEC_DIRECT_LINE)
            .personalExecName(UPDATED_PERSONAL_EXEC_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return supremeMaster;
    }

    @BeforeEach
    public void initTest() {
        supremeMasterRepository.deleteAll();
        supremeMaster = createEntity();
    }

    @Test
    public void createSupremeMaster() throws Exception {
        int databaseSizeBeforeCreate = supremeMasterRepository.findAll().size();

        // Create the SupremeMaster
        restSupremeMasterMockMvc.perform(post("/api/supreme-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supremeMaster)))
            .andExpect(status().isCreated());

        // Validate the SupremeMaster in the database
        List<SupremeMaster> supremeMasterList = supremeMasterRepository.findAll();
        assertThat(supremeMasterList).hasSize(databaseSizeBeforeCreate + 1);
        SupremeMaster testSupremeMaster = supremeMasterList.get(supremeMasterList.size() - 1);
        assertThat(testSupremeMaster.getSupremeSeqId()).isEqualTo(DEFAULT_SUPREME_SEQ_ID);
        assertThat(testSupremeMaster.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testSupremeMaster.getMsisdn()).isEqualTo(DEFAULT_MSISDN);
        assertThat(testSupremeMaster.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testSupremeMaster.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testSupremeMaster.getMembershipServiceType()).isEqualTo(DEFAULT_MEMBERSHIP_SERVICE_TYPE);
        assertThat(testSupremeMaster.getPeCode()).isEqualTo(DEFAULT_PE_CODE);
        assertThat(testSupremeMaster.getPersonalExecDirectLine()).isEqualTo(DEFAULT_PERSONAL_EXEC_DIRECT_LINE);
        assertThat(testSupremeMaster.getPersonalExecName()).isEqualTo(DEFAULT_PERSONAL_EXEC_NAME);
        assertThat(testSupremeMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSupremeMaster.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSupremeMaster.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testSupremeMaster.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testSupremeMaster.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createSupremeMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = supremeMasterRepository.findAll().size();

        // Create the SupremeMaster with an existing ID
        supremeMaster.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restSupremeMasterMockMvc.perform(post("/api/supreme-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supremeMaster)))
            .andExpect(status().isBadRequest());

        // Validate the SupremeMaster in the database
        List<SupremeMaster> supremeMasterList = supremeMasterRepository.findAll();
        assertThat(supremeMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkSupremeSeqIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = supremeMasterRepository.findAll().size();
        // set the field null
        supremeMaster.setSupremeSeqId(null);

        // Create the SupremeMaster, which fails.

        restSupremeMasterMockMvc.perform(post("/api/supreme-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supremeMaster)))
            .andExpect(status().isBadRequest());

        List<SupremeMaster> supremeMasterList = supremeMasterRepository.findAll();
        assertThat(supremeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = supremeMasterRepository.findAll().size();
        // set the field null
        supremeMaster.setCreatedDate(null);

        // Create the SupremeMaster, which fails.

        restSupremeMasterMockMvc.perform(post("/api/supreme-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supremeMaster)))
            .andExpect(status().isBadRequest());

        List<SupremeMaster> supremeMasterList = supremeMasterRepository.findAll();
        assertThat(supremeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = supremeMasterRepository.findAll().size();
        // set the field null
        supremeMaster.setCreatedBy(null);

        // Create the SupremeMaster, which fails.

        restSupremeMasterMockMvc.perform(post("/api/supreme-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supremeMaster)))
            .andExpect(status().isBadRequest());

        List<SupremeMaster> supremeMasterList = supremeMasterRepository.findAll();
        assertThat(supremeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = supremeMasterRepository.findAll().size();
        // set the field null
        supremeMaster.setLastUpdatedDate(null);

        // Create the SupremeMaster, which fails.

        restSupremeMasterMockMvc.perform(post("/api/supreme-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supremeMaster)))
            .andExpect(status().isBadRequest());

        List<SupremeMaster> supremeMasterList = supremeMasterRepository.findAll();
        assertThat(supremeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = supremeMasterRepository.findAll().size();
        // set the field null
        supremeMaster.setLastUpdatedBy(null);

        // Create the SupremeMaster, which fails.

        restSupremeMasterMockMvc.perform(post("/api/supreme-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supremeMaster)))
            .andExpect(status().isBadRequest());

        List<SupremeMaster> supremeMasterList = supremeMasterRepository.findAll();
        assertThat(supremeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = supremeMasterRepository.findAll().size();
        // set the field null
        supremeMaster.setTenantId(null);

        // Create the SupremeMaster, which fails.

        restSupremeMasterMockMvc.perform(post("/api/supreme-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supremeMaster)))
            .andExpect(status().isBadRequest());

        List<SupremeMaster> supremeMasterList = supremeMasterRepository.findAll();
        assertThat(supremeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllSupremeMasters() throws Exception {
        // Initialize the database
        supremeMasterRepository.save(supremeMaster);

        // Get all the supremeMasterList
        restSupremeMasterMockMvc.perform(get("/api/supreme-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(supremeMaster.getId())))
            .andExpect(jsonPath("$.[*].supremeSeqId").value(hasItem(DEFAULT_SUPREME_SEQ_ID.intValue())))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID)))
            .andExpect(jsonPath("$.[*].msisdn").value(hasItem(DEFAULT_MSISDN)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].membershipServiceType").value(hasItem(DEFAULT_MEMBERSHIP_SERVICE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].peCode").value(hasItem(DEFAULT_PE_CODE)))
            .andExpect(jsonPath("$.[*].personalExecDirectLine").value(hasItem(DEFAULT_PERSONAL_EXEC_DIRECT_LINE)))
            .andExpect(jsonPath("$.[*].personalExecName").value(hasItem(DEFAULT_PERSONAL_EXEC_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getSupremeMaster() throws Exception {
        // Initialize the database
        supremeMasterRepository.save(supremeMaster);

        // Get the supremeMaster
        restSupremeMasterMockMvc.perform(get("/api/supreme-masters/{id}", supremeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(supremeMaster.getId()))
            .andExpect(jsonPath("$.supremeSeqId").value(DEFAULT_SUPREME_SEQ_ID.intValue()))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID))
            .andExpect(jsonPath("$.msisdn").value(DEFAULT_MSISDN))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.membershipServiceType").value(DEFAULT_MEMBERSHIP_SERVICE_TYPE.toString()))
            .andExpect(jsonPath("$.peCode").value(DEFAULT_PE_CODE))
            .andExpect(jsonPath("$.personalExecDirectLine").value(DEFAULT_PERSONAL_EXEC_DIRECT_LINE))
            .andExpect(jsonPath("$.personalExecName").value(DEFAULT_PERSONAL_EXEC_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingSupremeMaster() throws Exception {
        // Get the supremeMaster
        restSupremeMasterMockMvc.perform(get("/api/supreme-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateSupremeMaster() throws Exception {
        // Initialize the database
        supremeMasterService.save(supremeMaster);

        int databaseSizeBeforeUpdate = supremeMasterRepository.findAll().size();

        // Update the supremeMaster
        SupremeMaster updatedSupremeMaster = supremeMasterRepository.findById(supremeMaster.getId()).get();
        updatedSupremeMaster
            .supremeSeqId(UPDATED_SUPREME_SEQ_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .msisdn(UPDATED_MSISDN)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .membershipServiceType(UPDATED_MEMBERSHIP_SERVICE_TYPE)
            .peCode(UPDATED_PE_CODE)
            .personalExecDirectLine(UPDATED_PERSONAL_EXEC_DIRECT_LINE)
            .personalExecName(UPDATED_PERSONAL_EXEC_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restSupremeMasterMockMvc.perform(put("/api/supreme-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSupremeMaster)))
            .andExpect(status().isOk());

        // Validate the SupremeMaster in the database
        List<SupremeMaster> supremeMasterList = supremeMasterRepository.findAll();
        assertThat(supremeMasterList).hasSize(databaseSizeBeforeUpdate);
        SupremeMaster testSupremeMaster = supremeMasterList.get(supremeMasterList.size() - 1);
        assertThat(testSupremeMaster.getSupremeSeqId()).isEqualTo(UPDATED_SUPREME_SEQ_ID);
        assertThat(testSupremeMaster.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testSupremeMaster.getMsisdn()).isEqualTo(UPDATED_MSISDN);
        assertThat(testSupremeMaster.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testSupremeMaster.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testSupremeMaster.getMembershipServiceType()).isEqualTo(UPDATED_MEMBERSHIP_SERVICE_TYPE);
        assertThat(testSupremeMaster.getPeCode()).isEqualTo(UPDATED_PE_CODE);
        assertThat(testSupremeMaster.getPersonalExecDirectLine()).isEqualTo(UPDATED_PERSONAL_EXEC_DIRECT_LINE);
        assertThat(testSupremeMaster.getPersonalExecName()).isEqualTo(UPDATED_PERSONAL_EXEC_NAME);
        assertThat(testSupremeMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSupremeMaster.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSupremeMaster.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testSupremeMaster.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testSupremeMaster.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingSupremeMaster() throws Exception {
        int databaseSizeBeforeUpdate = supremeMasterRepository.findAll().size();

        // Create the SupremeMaster

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSupremeMasterMockMvc.perform(put("/api/supreme-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supremeMaster)))
            .andExpect(status().isBadRequest());

        // Validate the SupremeMaster in the database
        List<SupremeMaster> supremeMasterList = supremeMasterRepository.findAll();
        assertThat(supremeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteSupremeMaster() throws Exception {
        // Initialize the database
        supremeMasterService.save(supremeMaster);

        int databaseSizeBeforeDelete = supremeMasterRepository.findAll().size();

        // Delete the supremeMaster
        restSupremeMasterMockMvc.perform(delete("/api/supreme-masters/{id}", supremeMaster.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SupremeMaster> supremeMasterList = supremeMasterRepository.findAll();
        assertThat(supremeMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
