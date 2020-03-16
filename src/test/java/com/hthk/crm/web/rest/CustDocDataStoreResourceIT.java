package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.CustDocDataStore;
import com.hthk.crm.repository.CustDocDataStoreRepository;
import com.hthk.crm.service.CustDocDataStoreService;
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
import org.springframework.util.Base64Utils;
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
 * Integration tests for the {@link CustDocDataStoreResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class CustDocDataStoreResourceIT {

    private static final Long DEFAULT_DOC_DATA_STORE_ID = 1L;
    private static final Long UPDATED_DOC_DATA_STORE_ID = 2L;

    private static final byte[] DEFAULT_DOCUMENT_DATA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DOCUMENT_DATA = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_DOCUMENT_DATA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DOCUMENT_DATA_CONTENT_TYPE = "image/png";

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
    private CustDocDataStoreRepository custDocDataStoreRepository;

    @Autowired
    private CustDocDataStoreService custDocDataStoreService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCustDocDataStoreMockMvc;

    private CustDocDataStore custDocDataStore;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustDocDataStoreResource custDocDataStoreResource = new CustDocDataStoreResource(custDocDataStoreService);
        this.restCustDocDataStoreMockMvc = MockMvcBuilders.standaloneSetup(custDocDataStoreResource)
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
    public static CustDocDataStore createEntity() {
        CustDocDataStore custDocDataStore = new CustDocDataStore()
            .docDataStoreId(DEFAULT_DOC_DATA_STORE_ID)
            .documentData(DEFAULT_DOCUMENT_DATA)
            .documentDataContentType(DEFAULT_DOCUMENT_DATA_CONTENT_TYPE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return custDocDataStore;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustDocDataStore createUpdatedEntity() {
        CustDocDataStore custDocDataStore = new CustDocDataStore()
            .docDataStoreId(UPDATED_DOC_DATA_STORE_ID)
            .documentData(UPDATED_DOCUMENT_DATA)
            .documentDataContentType(UPDATED_DOCUMENT_DATA_CONTENT_TYPE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return custDocDataStore;
    }

    @BeforeEach
    public void initTest() {
        custDocDataStoreRepository.deleteAll();
        custDocDataStore = createEntity();
    }

    @Test
    public void createCustDocDataStore() throws Exception {
        int databaseSizeBeforeCreate = custDocDataStoreRepository.findAll().size();

        // Create the CustDocDataStore
        restCustDocDataStoreMockMvc.perform(post("/api/cust-doc-data-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocDataStore)))
            .andExpect(status().isCreated());

        // Validate the CustDocDataStore in the database
        List<CustDocDataStore> custDocDataStoreList = custDocDataStoreRepository.findAll();
        assertThat(custDocDataStoreList).hasSize(databaseSizeBeforeCreate + 1);
        CustDocDataStore testCustDocDataStore = custDocDataStoreList.get(custDocDataStoreList.size() - 1);
        assertThat(testCustDocDataStore.getDocDataStoreId()).isEqualTo(DEFAULT_DOC_DATA_STORE_ID);
        assertThat(testCustDocDataStore.getDocumentData()).isEqualTo(DEFAULT_DOCUMENT_DATA);
        assertThat(testCustDocDataStore.getDocumentDataContentType()).isEqualTo(DEFAULT_DOCUMENT_DATA_CONTENT_TYPE);
        assertThat(testCustDocDataStore.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCustDocDataStore.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustDocDataStore.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testCustDocDataStore.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testCustDocDataStore.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createCustDocDataStoreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = custDocDataStoreRepository.findAll().size();

        // Create the CustDocDataStore with an existing ID
        custDocDataStore.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustDocDataStoreMockMvc.perform(post("/api/cust-doc-data-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocDataStore)))
            .andExpect(status().isBadRequest());

        // Validate the CustDocDataStore in the database
        List<CustDocDataStore> custDocDataStoreList = custDocDataStoreRepository.findAll();
        assertThat(custDocDataStoreList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkDocDataStoreIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custDocDataStoreRepository.findAll().size();
        // set the field null
        custDocDataStore.setDocDataStoreId(null);

        // Create the CustDocDataStore, which fails.

        restCustDocDataStoreMockMvc.perform(post("/api/cust-doc-data-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocDataStore)))
            .andExpect(status().isBadRequest());

        List<CustDocDataStore> custDocDataStoreList = custDocDataStoreRepository.findAll();
        assertThat(custDocDataStoreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = custDocDataStoreRepository.findAll().size();
        // set the field null
        custDocDataStore.setCreatedDate(null);

        // Create the CustDocDataStore, which fails.

        restCustDocDataStoreMockMvc.perform(post("/api/cust-doc-data-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocDataStore)))
            .andExpect(status().isBadRequest());

        List<CustDocDataStore> custDocDataStoreList = custDocDataStoreRepository.findAll();
        assertThat(custDocDataStoreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = custDocDataStoreRepository.findAll().size();
        // set the field null
        custDocDataStore.setCreatedBy(null);

        // Create the CustDocDataStore, which fails.

        restCustDocDataStoreMockMvc.perform(post("/api/cust-doc-data-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocDataStore)))
            .andExpect(status().isBadRequest());

        List<CustDocDataStore> custDocDataStoreList = custDocDataStoreRepository.findAll();
        assertThat(custDocDataStoreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = custDocDataStoreRepository.findAll().size();
        // set the field null
        custDocDataStore.setLastUpdatedDate(null);

        // Create the CustDocDataStore, which fails.

        restCustDocDataStoreMockMvc.perform(post("/api/cust-doc-data-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocDataStore)))
            .andExpect(status().isBadRequest());

        List<CustDocDataStore> custDocDataStoreList = custDocDataStoreRepository.findAll();
        assertThat(custDocDataStoreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = custDocDataStoreRepository.findAll().size();
        // set the field null
        custDocDataStore.setLastUpdatedBy(null);

        // Create the CustDocDataStore, which fails.

        restCustDocDataStoreMockMvc.perform(post("/api/cust-doc-data-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocDataStore)))
            .andExpect(status().isBadRequest());

        List<CustDocDataStore> custDocDataStoreList = custDocDataStoreRepository.findAll();
        assertThat(custDocDataStoreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custDocDataStoreRepository.findAll().size();
        // set the field null
        custDocDataStore.setTenantId(null);

        // Create the CustDocDataStore, which fails.

        restCustDocDataStoreMockMvc.perform(post("/api/cust-doc-data-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocDataStore)))
            .andExpect(status().isBadRequest());

        List<CustDocDataStore> custDocDataStoreList = custDocDataStoreRepository.findAll();
        assertThat(custDocDataStoreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCustDocDataStores() throws Exception {
        // Initialize the database
        custDocDataStoreRepository.save(custDocDataStore);

        // Get all the custDocDataStoreList
        restCustDocDataStoreMockMvc.perform(get("/api/cust-doc-data-stores?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(custDocDataStore.getId())))
            .andExpect(jsonPath("$.[*].docDataStoreId").value(hasItem(DEFAULT_DOC_DATA_STORE_ID.intValue())))
            .andExpect(jsonPath("$.[*].documentDataContentType").value(hasItem(DEFAULT_DOCUMENT_DATA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].documentData").value(hasItem(Base64Utils.encodeToString(DEFAULT_DOCUMENT_DATA))))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getCustDocDataStore() throws Exception {
        // Initialize the database
        custDocDataStoreRepository.save(custDocDataStore);

        // Get the custDocDataStore
        restCustDocDataStoreMockMvc.perform(get("/api/cust-doc-data-stores/{id}", custDocDataStore.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(custDocDataStore.getId()))
            .andExpect(jsonPath("$.docDataStoreId").value(DEFAULT_DOC_DATA_STORE_ID.intValue()))
            .andExpect(jsonPath("$.documentDataContentType").value(DEFAULT_DOCUMENT_DATA_CONTENT_TYPE))
            .andExpect(jsonPath("$.documentData").value(Base64Utils.encodeToString(DEFAULT_DOCUMENT_DATA)))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingCustDocDataStore() throws Exception {
        // Get the custDocDataStore
        restCustDocDataStoreMockMvc.perform(get("/api/cust-doc-data-stores/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCustDocDataStore() throws Exception {
        // Initialize the database
        custDocDataStoreService.save(custDocDataStore);

        int databaseSizeBeforeUpdate = custDocDataStoreRepository.findAll().size();

        // Update the custDocDataStore
        CustDocDataStore updatedCustDocDataStore = custDocDataStoreRepository.findById(custDocDataStore.getId()).get();
        updatedCustDocDataStore
            .docDataStoreId(UPDATED_DOC_DATA_STORE_ID)
            .documentData(UPDATED_DOCUMENT_DATA)
            .documentDataContentType(UPDATED_DOCUMENT_DATA_CONTENT_TYPE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restCustDocDataStoreMockMvc.perform(put("/api/cust-doc-data-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCustDocDataStore)))
            .andExpect(status().isOk());

        // Validate the CustDocDataStore in the database
        List<CustDocDataStore> custDocDataStoreList = custDocDataStoreRepository.findAll();
        assertThat(custDocDataStoreList).hasSize(databaseSizeBeforeUpdate);
        CustDocDataStore testCustDocDataStore = custDocDataStoreList.get(custDocDataStoreList.size() - 1);
        assertThat(testCustDocDataStore.getDocDataStoreId()).isEqualTo(UPDATED_DOC_DATA_STORE_ID);
        assertThat(testCustDocDataStore.getDocumentData()).isEqualTo(UPDATED_DOCUMENT_DATA);
        assertThat(testCustDocDataStore.getDocumentDataContentType()).isEqualTo(UPDATED_DOCUMENT_DATA_CONTENT_TYPE);
        assertThat(testCustDocDataStore.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCustDocDataStore.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustDocDataStore.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testCustDocDataStore.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testCustDocDataStore.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingCustDocDataStore() throws Exception {
        int databaseSizeBeforeUpdate = custDocDataStoreRepository.findAll().size();

        // Create the CustDocDataStore

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustDocDataStoreMockMvc.perform(put("/api/cust-doc-data-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocDataStore)))
            .andExpect(status().isBadRequest());

        // Validate the CustDocDataStore in the database
        List<CustDocDataStore> custDocDataStoreList = custDocDataStoreRepository.findAll();
        assertThat(custDocDataStoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCustDocDataStore() throws Exception {
        // Initialize the database
        custDocDataStoreService.save(custDocDataStore);

        int databaseSizeBeforeDelete = custDocDataStoreRepository.findAll().size();

        // Delete the custDocDataStore
        restCustDocDataStoreMockMvc.perform(delete("/api/cust-doc-data-stores/{id}", custDocDataStore.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustDocDataStore> custDocDataStoreList = custDocDataStoreRepository.findAll();
        assertThat(custDocDataStoreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
