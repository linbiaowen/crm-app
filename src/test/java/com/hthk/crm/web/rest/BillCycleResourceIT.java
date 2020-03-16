package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.BillCycle;
import com.hthk.crm.repository.BillCycleRepository;
import com.hthk.crm.service.BillCycleService;
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
 * Integration tests for the {@link BillCycleResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class BillCycleResourceIT {

    private static final Integer DEFAULT_BILL_CYCLE_ID = 1;
    private static final Integer UPDATED_BILL_CYCLE_ID = 2;

    private static final String DEFAULT_BILL_CYCLE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_BILL_CYCLE_DESC = "BBBBBBBBBB";

    private static final Integer DEFAULT_BILL_CYCLE = 1;
    private static final Integer UPDATED_BILL_CYCLE = 2;

    private static final String DEFAULT_BILL_FREQUENCY = "AAAAAAAAAA";
    private static final String UPDATED_BILL_FREQUENCY = "BBBBBBBBBB";

    private static final Instant DEFAULT_BILL_CYCLE_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BILL_CYCLE_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_BILL_CYCLE_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BILL_CYCLE_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_DUE_DATE_OFFSET = 1;
    private static final Integer UPDATED_DUE_DATE_OFFSET = 2;

    private static final Integer DEFAULT_DIRECT_DEBIT_PROCESS_DAY = 1;
    private static final Integer UPDATED_DIRECT_DEBIT_PROCESS_DAY = 2;

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
    private BillCycleRepository billCycleRepository;

    @Autowired
    private BillCycleService billCycleService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restBillCycleMockMvc;

    private BillCycle billCycle;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BillCycleResource billCycleResource = new BillCycleResource(billCycleService);
        this.restBillCycleMockMvc = MockMvcBuilders.standaloneSetup(billCycleResource)
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
    public static BillCycle createEntity() {
        BillCycle billCycle = new BillCycle()
            .billCycleId(DEFAULT_BILL_CYCLE_ID)
            .billCycleDesc(DEFAULT_BILL_CYCLE_DESC)
            .billCycle(DEFAULT_BILL_CYCLE)
            .billFrequency(DEFAULT_BILL_FREQUENCY)
            .billCycleStartDate(DEFAULT_BILL_CYCLE_START_DATE)
            .billCycleEndDate(DEFAULT_BILL_CYCLE_END_DATE)
            .dueDateOffset(DEFAULT_DUE_DATE_OFFSET)
            .directDebitProcessDay(DEFAULT_DIRECT_DEBIT_PROCESS_DAY)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return billCycle;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillCycle createUpdatedEntity() {
        BillCycle billCycle = new BillCycle()
            .billCycleId(UPDATED_BILL_CYCLE_ID)
            .billCycleDesc(UPDATED_BILL_CYCLE_DESC)
            .billCycle(UPDATED_BILL_CYCLE)
            .billFrequency(UPDATED_BILL_FREQUENCY)
            .billCycleStartDate(UPDATED_BILL_CYCLE_START_DATE)
            .billCycleEndDate(UPDATED_BILL_CYCLE_END_DATE)
            .dueDateOffset(UPDATED_DUE_DATE_OFFSET)
            .directDebitProcessDay(UPDATED_DIRECT_DEBIT_PROCESS_DAY)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return billCycle;
    }

    @BeforeEach
    public void initTest() {
        billCycleRepository.deleteAll();
        billCycle = createEntity();
    }

    @Test
    public void createBillCycle() throws Exception {
        int databaseSizeBeforeCreate = billCycleRepository.findAll().size();

        // Create the BillCycle
        restBillCycleMockMvc.perform(post("/api/bill-cycles")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billCycle)))
            .andExpect(status().isCreated());

        // Validate the BillCycle in the database
        List<BillCycle> billCycleList = billCycleRepository.findAll();
        assertThat(billCycleList).hasSize(databaseSizeBeforeCreate + 1);
        BillCycle testBillCycle = billCycleList.get(billCycleList.size() - 1);
        assertThat(testBillCycle.getBillCycleId()).isEqualTo(DEFAULT_BILL_CYCLE_ID);
        assertThat(testBillCycle.getBillCycleDesc()).isEqualTo(DEFAULT_BILL_CYCLE_DESC);
        assertThat(testBillCycle.getBillCycle()).isEqualTo(DEFAULT_BILL_CYCLE);
        assertThat(testBillCycle.getBillFrequency()).isEqualTo(DEFAULT_BILL_FREQUENCY);
        assertThat(testBillCycle.getBillCycleStartDate()).isEqualTo(DEFAULT_BILL_CYCLE_START_DATE);
        assertThat(testBillCycle.getBillCycleEndDate()).isEqualTo(DEFAULT_BILL_CYCLE_END_DATE);
        assertThat(testBillCycle.getDueDateOffset()).isEqualTo(DEFAULT_DUE_DATE_OFFSET);
        assertThat(testBillCycle.getDirectDebitProcessDay()).isEqualTo(DEFAULT_DIRECT_DEBIT_PROCESS_DAY);
        assertThat(testBillCycle.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBillCycle.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBillCycle.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testBillCycle.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testBillCycle.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createBillCycleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = billCycleRepository.findAll().size();

        // Create the BillCycle with an existing ID
        billCycle.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillCycleMockMvc.perform(post("/api/bill-cycles")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billCycle)))
            .andExpect(status().isBadRequest());

        // Validate the BillCycle in the database
        List<BillCycle> billCycleList = billCycleRepository.findAll();
        assertThat(billCycleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkBillCycleIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = billCycleRepository.findAll().size();
        // set the field null
        billCycle.setBillCycleId(null);

        // Create the BillCycle, which fails.

        restBillCycleMockMvc.perform(post("/api/bill-cycles")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billCycle)))
            .andExpect(status().isBadRequest());

        List<BillCycle> billCycleList = billCycleRepository.findAll();
        assertThat(billCycleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkBillCycleIsRequired() throws Exception {
        int databaseSizeBeforeTest = billCycleRepository.findAll().size();
        // set the field null
        billCycle.setBillCycle(null);

        // Create the BillCycle, which fails.

        restBillCycleMockMvc.perform(post("/api/bill-cycles")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billCycle)))
            .andExpect(status().isBadRequest());

        List<BillCycle> billCycleList = billCycleRepository.findAll();
        assertThat(billCycleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = billCycleRepository.findAll().size();
        // set the field null
        billCycle.setCreatedDate(null);

        // Create the BillCycle, which fails.

        restBillCycleMockMvc.perform(post("/api/bill-cycles")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billCycle)))
            .andExpect(status().isBadRequest());

        List<BillCycle> billCycleList = billCycleRepository.findAll();
        assertThat(billCycleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = billCycleRepository.findAll().size();
        // set the field null
        billCycle.setCreatedBy(null);

        // Create the BillCycle, which fails.

        restBillCycleMockMvc.perform(post("/api/bill-cycles")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billCycle)))
            .andExpect(status().isBadRequest());

        List<BillCycle> billCycleList = billCycleRepository.findAll();
        assertThat(billCycleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = billCycleRepository.findAll().size();
        // set the field null
        billCycle.setLastUpdatedDate(null);

        // Create the BillCycle, which fails.

        restBillCycleMockMvc.perform(post("/api/bill-cycles")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billCycle)))
            .andExpect(status().isBadRequest());

        List<BillCycle> billCycleList = billCycleRepository.findAll();
        assertThat(billCycleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = billCycleRepository.findAll().size();
        // set the field null
        billCycle.setLastUpdatedBy(null);

        // Create the BillCycle, which fails.

        restBillCycleMockMvc.perform(post("/api/bill-cycles")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billCycle)))
            .andExpect(status().isBadRequest());

        List<BillCycle> billCycleList = billCycleRepository.findAll();
        assertThat(billCycleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = billCycleRepository.findAll().size();
        // set the field null
        billCycle.setTenantId(null);

        // Create the BillCycle, which fails.

        restBillCycleMockMvc.perform(post("/api/bill-cycles")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billCycle)))
            .andExpect(status().isBadRequest());

        List<BillCycle> billCycleList = billCycleRepository.findAll();
        assertThat(billCycleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllBillCycles() throws Exception {
        // Initialize the database
        billCycleRepository.save(billCycle);

        // Get all the billCycleList
        restBillCycleMockMvc.perform(get("/api/bill-cycles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(billCycle.getId())))
            .andExpect(jsonPath("$.[*].billCycleId").value(hasItem(DEFAULT_BILL_CYCLE_ID)))
            .andExpect(jsonPath("$.[*].billCycleDesc").value(hasItem(DEFAULT_BILL_CYCLE_DESC)))
            .andExpect(jsonPath("$.[*].billCycle").value(hasItem(DEFAULT_BILL_CYCLE)))
            .andExpect(jsonPath("$.[*].billFrequency").value(hasItem(DEFAULT_BILL_FREQUENCY)))
            .andExpect(jsonPath("$.[*].billCycleStartDate").value(hasItem(DEFAULT_BILL_CYCLE_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].billCycleEndDate").value(hasItem(DEFAULT_BILL_CYCLE_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].dueDateOffset").value(hasItem(DEFAULT_DUE_DATE_OFFSET)))
            .andExpect(jsonPath("$.[*].directDebitProcessDay").value(hasItem(DEFAULT_DIRECT_DEBIT_PROCESS_DAY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getBillCycle() throws Exception {
        // Initialize the database
        billCycleRepository.save(billCycle);

        // Get the billCycle
        restBillCycleMockMvc.perform(get("/api/bill-cycles/{id}", billCycle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(billCycle.getId()))
            .andExpect(jsonPath("$.billCycleId").value(DEFAULT_BILL_CYCLE_ID))
            .andExpect(jsonPath("$.billCycleDesc").value(DEFAULT_BILL_CYCLE_DESC))
            .andExpect(jsonPath("$.billCycle").value(DEFAULT_BILL_CYCLE))
            .andExpect(jsonPath("$.billFrequency").value(DEFAULT_BILL_FREQUENCY))
            .andExpect(jsonPath("$.billCycleStartDate").value(DEFAULT_BILL_CYCLE_START_DATE.toString()))
            .andExpect(jsonPath("$.billCycleEndDate").value(DEFAULT_BILL_CYCLE_END_DATE.toString()))
            .andExpect(jsonPath("$.dueDateOffset").value(DEFAULT_DUE_DATE_OFFSET))
            .andExpect(jsonPath("$.directDebitProcessDay").value(DEFAULT_DIRECT_DEBIT_PROCESS_DAY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingBillCycle() throws Exception {
        // Get the billCycle
        restBillCycleMockMvc.perform(get("/api/bill-cycles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBillCycle() throws Exception {
        // Initialize the database
        billCycleService.save(billCycle);

        int databaseSizeBeforeUpdate = billCycleRepository.findAll().size();

        // Update the billCycle
        BillCycle updatedBillCycle = billCycleRepository.findById(billCycle.getId()).get();
        updatedBillCycle
            .billCycleId(UPDATED_BILL_CYCLE_ID)
            .billCycleDesc(UPDATED_BILL_CYCLE_DESC)
            .billCycle(UPDATED_BILL_CYCLE)
            .billFrequency(UPDATED_BILL_FREQUENCY)
            .billCycleStartDate(UPDATED_BILL_CYCLE_START_DATE)
            .billCycleEndDate(UPDATED_BILL_CYCLE_END_DATE)
            .dueDateOffset(UPDATED_DUE_DATE_OFFSET)
            .directDebitProcessDay(UPDATED_DIRECT_DEBIT_PROCESS_DAY)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restBillCycleMockMvc.perform(put("/api/bill-cycles")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBillCycle)))
            .andExpect(status().isOk());

        // Validate the BillCycle in the database
        List<BillCycle> billCycleList = billCycleRepository.findAll();
        assertThat(billCycleList).hasSize(databaseSizeBeforeUpdate);
        BillCycle testBillCycle = billCycleList.get(billCycleList.size() - 1);
        assertThat(testBillCycle.getBillCycleId()).isEqualTo(UPDATED_BILL_CYCLE_ID);
        assertThat(testBillCycle.getBillCycleDesc()).isEqualTo(UPDATED_BILL_CYCLE_DESC);
        assertThat(testBillCycle.getBillCycle()).isEqualTo(UPDATED_BILL_CYCLE);
        assertThat(testBillCycle.getBillFrequency()).isEqualTo(UPDATED_BILL_FREQUENCY);
        assertThat(testBillCycle.getBillCycleStartDate()).isEqualTo(UPDATED_BILL_CYCLE_START_DATE);
        assertThat(testBillCycle.getBillCycleEndDate()).isEqualTo(UPDATED_BILL_CYCLE_END_DATE);
        assertThat(testBillCycle.getDueDateOffset()).isEqualTo(UPDATED_DUE_DATE_OFFSET);
        assertThat(testBillCycle.getDirectDebitProcessDay()).isEqualTo(UPDATED_DIRECT_DEBIT_PROCESS_DAY);
        assertThat(testBillCycle.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBillCycle.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBillCycle.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testBillCycle.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testBillCycle.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingBillCycle() throws Exception {
        int databaseSizeBeforeUpdate = billCycleRepository.findAll().size();

        // Create the BillCycle

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillCycleMockMvc.perform(put("/api/bill-cycles")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billCycle)))
            .andExpect(status().isBadRequest());

        // Validate the BillCycle in the database
        List<BillCycle> billCycleList = billCycleRepository.findAll();
        assertThat(billCycleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteBillCycle() throws Exception {
        // Initialize the database
        billCycleService.save(billCycle);

        int databaseSizeBeforeDelete = billCycleRepository.findAll().size();

        // Delete the billCycle
        restBillCycleMockMvc.perform(delete("/api/bill-cycles/{id}", billCycle.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BillCycle> billCycleList = billCycleRepository.findAll();
        assertThat(billCycleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
