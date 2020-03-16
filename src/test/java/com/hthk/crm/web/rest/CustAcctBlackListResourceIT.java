package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.CustAcctBlackList;
import com.hthk.crm.repository.CustAcctBlackListRepository;
import com.hthk.crm.service.CustAcctBlackListService;
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
 * Integration tests for the {@link CustAcctBlackListResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class CustAcctBlackListResourceIT {

    private static final Long DEFAULT_BLACK_LIST_ID = 1L;
    private static final Long UPDATED_BLACK_LIST_ID = 2L;

    private static final String DEFAULT_ID_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ID_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_BLACK_LIST_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BLACK_LIST_CODE = "BBBBBBBBBB";

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
    private CustAcctBlackListRepository custAcctBlackListRepository;

    @Autowired
    private CustAcctBlackListService custAcctBlackListService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCustAcctBlackListMockMvc;

    private CustAcctBlackList custAcctBlackList;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustAcctBlackListResource custAcctBlackListResource = new CustAcctBlackListResource(custAcctBlackListService);
        this.restCustAcctBlackListMockMvc = MockMvcBuilders.standaloneSetup(custAcctBlackListResource)
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
    public static CustAcctBlackList createEntity() {
        CustAcctBlackList custAcctBlackList = new CustAcctBlackList()
            .blackListId(DEFAULT_BLACK_LIST_ID)
            .idType(DEFAULT_ID_TYPE)
            .idNumber(DEFAULT_ID_NUMBER)
            .blackListCode(DEFAULT_BLACK_LIST_CODE)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return custAcctBlackList;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustAcctBlackList createUpdatedEntity() {
        CustAcctBlackList custAcctBlackList = new CustAcctBlackList()
            .blackListId(UPDATED_BLACK_LIST_ID)
            .idType(UPDATED_ID_TYPE)
            .idNumber(UPDATED_ID_NUMBER)
            .blackListCode(UPDATED_BLACK_LIST_CODE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return custAcctBlackList;
    }

    @BeforeEach
    public void initTest() {
        custAcctBlackListRepository.deleteAll();
        custAcctBlackList = createEntity();
    }

    @Test
    public void createCustAcctBlackList() throws Exception {
        int databaseSizeBeforeCreate = custAcctBlackListRepository.findAll().size();

        // Create the CustAcctBlackList
        restCustAcctBlackListMockMvc.perform(post("/api/cust-acct-black-lists")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAcctBlackList)))
            .andExpect(status().isCreated());

        // Validate the CustAcctBlackList in the database
        List<CustAcctBlackList> custAcctBlackListList = custAcctBlackListRepository.findAll();
        assertThat(custAcctBlackListList).hasSize(databaseSizeBeforeCreate + 1);
        CustAcctBlackList testCustAcctBlackList = custAcctBlackListList.get(custAcctBlackListList.size() - 1);
        assertThat(testCustAcctBlackList.getBlackListId()).isEqualTo(DEFAULT_BLACK_LIST_ID);
        assertThat(testCustAcctBlackList.getIdType()).isEqualTo(DEFAULT_ID_TYPE);
        assertThat(testCustAcctBlackList.getIdNumber()).isEqualTo(DEFAULT_ID_NUMBER);
        assertThat(testCustAcctBlackList.getBlackListCode()).isEqualTo(DEFAULT_BLACK_LIST_CODE);
        assertThat(testCustAcctBlackList.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testCustAcctBlackList.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testCustAcctBlackList.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCustAcctBlackList.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustAcctBlackList.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testCustAcctBlackList.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testCustAcctBlackList.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createCustAcctBlackListWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = custAcctBlackListRepository.findAll().size();

        // Create the CustAcctBlackList with an existing ID
        custAcctBlackList.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustAcctBlackListMockMvc.perform(post("/api/cust-acct-black-lists")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAcctBlackList)))
            .andExpect(status().isBadRequest());

        // Validate the CustAcctBlackList in the database
        List<CustAcctBlackList> custAcctBlackListList = custAcctBlackListRepository.findAll();
        assertThat(custAcctBlackListList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkBlackListIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAcctBlackListRepository.findAll().size();
        // set the field null
        custAcctBlackList.setBlackListId(null);

        // Create the CustAcctBlackList, which fails.

        restCustAcctBlackListMockMvc.perform(post("/api/cust-acct-black-lists")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAcctBlackList)))
            .andExpect(status().isBadRequest());

        List<CustAcctBlackList> custAcctBlackListList = custAcctBlackListRepository.findAll();
        assertThat(custAcctBlackListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAcctBlackListRepository.findAll().size();
        // set the field null
        custAcctBlackList.setIdType(null);

        // Create the CustAcctBlackList, which fails.

        restCustAcctBlackListMockMvc.perform(post("/api/cust-acct-black-lists")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAcctBlackList)))
            .andExpect(status().isBadRequest());

        List<CustAcctBlackList> custAcctBlackListList = custAcctBlackListRepository.findAll();
        assertThat(custAcctBlackListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAcctBlackListRepository.findAll().size();
        // set the field null
        custAcctBlackList.setIdNumber(null);

        // Create the CustAcctBlackList, which fails.

        restCustAcctBlackListMockMvc.perform(post("/api/cust-acct-black-lists")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAcctBlackList)))
            .andExpect(status().isBadRequest());

        List<CustAcctBlackList> custAcctBlackListList = custAcctBlackListRepository.findAll();
        assertThat(custAcctBlackListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkBlackListCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAcctBlackListRepository.findAll().size();
        // set the field null
        custAcctBlackList.setBlackListCode(null);

        // Create the CustAcctBlackList, which fails.

        restCustAcctBlackListMockMvc.perform(post("/api/cust-acct-black-lists")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAcctBlackList)))
            .andExpect(status().isBadRequest());

        List<CustAcctBlackList> custAcctBlackListList = custAcctBlackListRepository.findAll();
        assertThat(custAcctBlackListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAcctBlackListRepository.findAll().size();
        // set the field null
        custAcctBlackList.setCreatedDate(null);

        // Create the CustAcctBlackList, which fails.

        restCustAcctBlackListMockMvc.perform(post("/api/cust-acct-black-lists")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAcctBlackList)))
            .andExpect(status().isBadRequest());

        List<CustAcctBlackList> custAcctBlackListList = custAcctBlackListRepository.findAll();
        assertThat(custAcctBlackListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAcctBlackListRepository.findAll().size();
        // set the field null
        custAcctBlackList.setCreatedBy(null);

        // Create the CustAcctBlackList, which fails.

        restCustAcctBlackListMockMvc.perform(post("/api/cust-acct-black-lists")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAcctBlackList)))
            .andExpect(status().isBadRequest());

        List<CustAcctBlackList> custAcctBlackListList = custAcctBlackListRepository.findAll();
        assertThat(custAcctBlackListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAcctBlackListRepository.findAll().size();
        // set the field null
        custAcctBlackList.setLastUpdatedDate(null);

        // Create the CustAcctBlackList, which fails.

        restCustAcctBlackListMockMvc.perform(post("/api/cust-acct-black-lists")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAcctBlackList)))
            .andExpect(status().isBadRequest());

        List<CustAcctBlackList> custAcctBlackListList = custAcctBlackListRepository.findAll();
        assertThat(custAcctBlackListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAcctBlackListRepository.findAll().size();
        // set the field null
        custAcctBlackList.setLastUpdatedBy(null);

        // Create the CustAcctBlackList, which fails.

        restCustAcctBlackListMockMvc.perform(post("/api/cust-acct-black-lists")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAcctBlackList)))
            .andExpect(status().isBadRequest());

        List<CustAcctBlackList> custAcctBlackListList = custAcctBlackListRepository.findAll();
        assertThat(custAcctBlackListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custAcctBlackListRepository.findAll().size();
        // set the field null
        custAcctBlackList.setTenantId(null);

        // Create the CustAcctBlackList, which fails.

        restCustAcctBlackListMockMvc.perform(post("/api/cust-acct-black-lists")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAcctBlackList)))
            .andExpect(status().isBadRequest());

        List<CustAcctBlackList> custAcctBlackListList = custAcctBlackListRepository.findAll();
        assertThat(custAcctBlackListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCustAcctBlackLists() throws Exception {
        // Initialize the database
        custAcctBlackListRepository.save(custAcctBlackList);

        // Get all the custAcctBlackListList
        restCustAcctBlackListMockMvc.perform(get("/api/cust-acct-black-lists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(custAcctBlackList.getId())))
            .andExpect(jsonPath("$.[*].blackListId").value(hasItem(DEFAULT_BLACK_LIST_ID.intValue())))
            .andExpect(jsonPath("$.[*].idType").value(hasItem(DEFAULT_ID_TYPE)))
            .andExpect(jsonPath("$.[*].idNumber").value(hasItem(DEFAULT_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].blackListCode").value(hasItem(DEFAULT_BLACK_LIST_CODE)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getCustAcctBlackList() throws Exception {
        // Initialize the database
        custAcctBlackListRepository.save(custAcctBlackList);

        // Get the custAcctBlackList
        restCustAcctBlackListMockMvc.perform(get("/api/cust-acct-black-lists/{id}", custAcctBlackList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(custAcctBlackList.getId()))
            .andExpect(jsonPath("$.blackListId").value(DEFAULT_BLACK_LIST_ID.intValue()))
            .andExpect(jsonPath("$.idType").value(DEFAULT_ID_TYPE))
            .andExpect(jsonPath("$.idNumber").value(DEFAULT_ID_NUMBER))
            .andExpect(jsonPath("$.blackListCode").value(DEFAULT_BLACK_LIST_CODE))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingCustAcctBlackList() throws Exception {
        // Get the custAcctBlackList
        restCustAcctBlackListMockMvc.perform(get("/api/cust-acct-black-lists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCustAcctBlackList() throws Exception {
        // Initialize the database
        custAcctBlackListService.save(custAcctBlackList);

        int databaseSizeBeforeUpdate = custAcctBlackListRepository.findAll().size();

        // Update the custAcctBlackList
        CustAcctBlackList updatedCustAcctBlackList = custAcctBlackListRepository.findById(custAcctBlackList.getId()).get();
        updatedCustAcctBlackList
            .blackListId(UPDATED_BLACK_LIST_ID)
            .idType(UPDATED_ID_TYPE)
            .idNumber(UPDATED_ID_NUMBER)
            .blackListCode(UPDATED_BLACK_LIST_CODE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restCustAcctBlackListMockMvc.perform(put("/api/cust-acct-black-lists")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCustAcctBlackList)))
            .andExpect(status().isOk());

        // Validate the CustAcctBlackList in the database
        List<CustAcctBlackList> custAcctBlackListList = custAcctBlackListRepository.findAll();
        assertThat(custAcctBlackListList).hasSize(databaseSizeBeforeUpdate);
        CustAcctBlackList testCustAcctBlackList = custAcctBlackListList.get(custAcctBlackListList.size() - 1);
        assertThat(testCustAcctBlackList.getBlackListId()).isEqualTo(UPDATED_BLACK_LIST_ID);
        assertThat(testCustAcctBlackList.getIdType()).isEqualTo(UPDATED_ID_TYPE);
        assertThat(testCustAcctBlackList.getIdNumber()).isEqualTo(UPDATED_ID_NUMBER);
        assertThat(testCustAcctBlackList.getBlackListCode()).isEqualTo(UPDATED_BLACK_LIST_CODE);
        assertThat(testCustAcctBlackList.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testCustAcctBlackList.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testCustAcctBlackList.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCustAcctBlackList.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustAcctBlackList.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testCustAcctBlackList.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testCustAcctBlackList.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingCustAcctBlackList() throws Exception {
        int databaseSizeBeforeUpdate = custAcctBlackListRepository.findAll().size();

        // Create the CustAcctBlackList

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustAcctBlackListMockMvc.perform(put("/api/cust-acct-black-lists")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custAcctBlackList)))
            .andExpect(status().isBadRequest());

        // Validate the CustAcctBlackList in the database
        List<CustAcctBlackList> custAcctBlackListList = custAcctBlackListRepository.findAll();
        assertThat(custAcctBlackListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCustAcctBlackList() throws Exception {
        // Initialize the database
        custAcctBlackListService.save(custAcctBlackList);

        int databaseSizeBeforeDelete = custAcctBlackListRepository.findAll().size();

        // Delete the custAcctBlackList
        restCustAcctBlackListMockMvc.perform(delete("/api/cust-acct-black-lists/{id}", custAcctBlackList.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustAcctBlackList> custAcctBlackListList = custAcctBlackListRepository.findAll();
        assertThat(custAcctBlackListList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
