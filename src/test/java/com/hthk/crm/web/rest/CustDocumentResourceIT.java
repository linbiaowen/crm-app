package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.CustDocument;
import com.hthk.crm.repository.CustDocumentRepository;
import com.hthk.crm.service.CustDocumentService;
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

import com.hthk.crm.domain.enumeration.DocType;
/**
 * Integration tests for the {@link CustDocumentResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class CustDocumentResourceIT {

    private static final String DEFAULT_CUST_DOC_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUST_DOC_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_ACCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUST_ACCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIPTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_ID = "BBBBBBBBBB";

    private static final DocType DEFAULT_DOC_TYPE = DocType.HKID;
    private static final DocType UPDATED_DOC_TYPE = DocType.PASSPORT;

    private static final String DEFAULT_DOC_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DOC_ID_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_DOC_DATA_STORE_ID = 1L;
    private static final Long UPDATED_DOC_DATA_STORE_ID = 2L;

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
    private CustDocumentRepository custDocumentRepository;

    @Autowired
    private CustDocumentService custDocumentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCustDocumentMockMvc;

    private CustDocument custDocument;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustDocumentResource custDocumentResource = new CustDocumentResource(custDocumentService);
        this.restCustDocumentMockMvc = MockMvcBuilders.standaloneSetup(custDocumentResource)
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
    public static CustDocument createEntity() {
        CustDocument custDocument = new CustDocument()
            .custDocId(DEFAULT_CUST_DOC_ID)
            .custAcctId(DEFAULT_CUST_ACCT_ID)
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .docType(DEFAULT_DOC_TYPE)
            .docIdNumber(DEFAULT_DOC_ID_NUMBER)
            .docDataStoreId(DEFAULT_DOC_DATA_STORE_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return custDocument;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustDocument createUpdatedEntity() {
        CustDocument custDocument = new CustDocument()
            .custDocId(UPDATED_CUST_DOC_ID)
            .custAcctId(UPDATED_CUST_ACCT_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .docType(UPDATED_DOC_TYPE)
            .docIdNumber(UPDATED_DOC_ID_NUMBER)
            .docDataStoreId(UPDATED_DOC_DATA_STORE_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return custDocument;
    }

    @BeforeEach
    public void initTest() {
        custDocumentRepository.deleteAll();
        custDocument = createEntity();
    }

    @Test
    public void createCustDocument() throws Exception {
        int databaseSizeBeforeCreate = custDocumentRepository.findAll().size();

        // Create the CustDocument
        restCustDocumentMockMvc.perform(post("/api/cust-documents")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocument)))
            .andExpect(status().isCreated());

        // Validate the CustDocument in the database
        List<CustDocument> custDocumentList = custDocumentRepository.findAll();
        assertThat(custDocumentList).hasSize(databaseSizeBeforeCreate + 1);
        CustDocument testCustDocument = custDocumentList.get(custDocumentList.size() - 1);
        assertThat(testCustDocument.getCustDocId()).isEqualTo(DEFAULT_CUST_DOC_ID);
        assertThat(testCustDocument.getCustAcctId()).isEqualTo(DEFAULT_CUST_ACCT_ID);
        assertThat(testCustDocument.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testCustDocument.getDocType()).isEqualTo(DEFAULT_DOC_TYPE);
        assertThat(testCustDocument.getDocIdNumber()).isEqualTo(DEFAULT_DOC_ID_NUMBER);
        assertThat(testCustDocument.getDocDataStoreId()).isEqualTo(DEFAULT_DOC_DATA_STORE_ID);
        assertThat(testCustDocument.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCustDocument.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustDocument.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testCustDocument.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testCustDocument.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createCustDocumentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = custDocumentRepository.findAll().size();

        // Create the CustDocument with an existing ID
        custDocument.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustDocumentMockMvc.perform(post("/api/cust-documents")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocument)))
            .andExpect(status().isBadRequest());

        // Validate the CustDocument in the database
        List<CustDocument> custDocumentList = custDocumentRepository.findAll();
        assertThat(custDocumentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkCustDocIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custDocumentRepository.findAll().size();
        // set the field null
        custDocument.setCustDocId(null);

        // Create the CustDocument, which fails.

        restCustDocumentMockMvc.perform(post("/api/cust-documents")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocument)))
            .andExpect(status().isBadRequest());

        List<CustDocument> custDocumentList = custDocumentRepository.findAll();
        assertThat(custDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDocTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = custDocumentRepository.findAll().size();
        // set the field null
        custDocument.setDocType(null);

        // Create the CustDocument, which fails.

        restCustDocumentMockMvc.perform(post("/api/cust-documents")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocument)))
            .andExpect(status().isBadRequest());

        List<CustDocument> custDocumentList = custDocumentRepository.findAll();
        assertThat(custDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = custDocumentRepository.findAll().size();
        // set the field null
        custDocument.setCreatedDate(null);

        // Create the CustDocument, which fails.

        restCustDocumentMockMvc.perform(post("/api/cust-documents")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocument)))
            .andExpect(status().isBadRequest());

        List<CustDocument> custDocumentList = custDocumentRepository.findAll();
        assertThat(custDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = custDocumentRepository.findAll().size();
        // set the field null
        custDocument.setCreatedBy(null);

        // Create the CustDocument, which fails.

        restCustDocumentMockMvc.perform(post("/api/cust-documents")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocument)))
            .andExpect(status().isBadRequest());

        List<CustDocument> custDocumentList = custDocumentRepository.findAll();
        assertThat(custDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = custDocumentRepository.findAll().size();
        // set the field null
        custDocument.setLastUpdatedDate(null);

        // Create the CustDocument, which fails.

        restCustDocumentMockMvc.perform(post("/api/cust-documents")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocument)))
            .andExpect(status().isBadRequest());

        List<CustDocument> custDocumentList = custDocumentRepository.findAll();
        assertThat(custDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = custDocumentRepository.findAll().size();
        // set the field null
        custDocument.setLastUpdatedBy(null);

        // Create the CustDocument, which fails.

        restCustDocumentMockMvc.perform(post("/api/cust-documents")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocument)))
            .andExpect(status().isBadRequest());

        List<CustDocument> custDocumentList = custDocumentRepository.findAll();
        assertThat(custDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custDocumentRepository.findAll().size();
        // set the field null
        custDocument.setTenantId(null);

        // Create the CustDocument, which fails.

        restCustDocumentMockMvc.perform(post("/api/cust-documents")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocument)))
            .andExpect(status().isBadRequest());

        List<CustDocument> custDocumentList = custDocumentRepository.findAll();
        assertThat(custDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCustDocuments() throws Exception {
        // Initialize the database
        custDocumentRepository.save(custDocument);

        // Get all the custDocumentList
        restCustDocumentMockMvc.perform(get("/api/cust-documents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(custDocument.getId())))
            .andExpect(jsonPath("$.[*].custDocId").value(hasItem(DEFAULT_CUST_DOC_ID)))
            .andExpect(jsonPath("$.[*].custAcctId").value(hasItem(DEFAULT_CUST_ACCT_ID)))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID)))
            .andExpect(jsonPath("$.[*].docType").value(hasItem(DEFAULT_DOC_TYPE.toString())))
            .andExpect(jsonPath("$.[*].docIdNumber").value(hasItem(DEFAULT_DOC_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].docDataStoreId").value(hasItem(DEFAULT_DOC_DATA_STORE_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getCustDocument() throws Exception {
        // Initialize the database
        custDocumentRepository.save(custDocument);

        // Get the custDocument
        restCustDocumentMockMvc.perform(get("/api/cust-documents/{id}", custDocument.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(custDocument.getId()))
            .andExpect(jsonPath("$.custDocId").value(DEFAULT_CUST_DOC_ID))
            .andExpect(jsonPath("$.custAcctId").value(DEFAULT_CUST_ACCT_ID))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID))
            .andExpect(jsonPath("$.docType").value(DEFAULT_DOC_TYPE.toString()))
            .andExpect(jsonPath("$.docIdNumber").value(DEFAULT_DOC_ID_NUMBER))
            .andExpect(jsonPath("$.docDataStoreId").value(DEFAULT_DOC_DATA_STORE_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingCustDocument() throws Exception {
        // Get the custDocument
        restCustDocumentMockMvc.perform(get("/api/cust-documents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCustDocument() throws Exception {
        // Initialize the database
        custDocumentService.save(custDocument);

        int databaseSizeBeforeUpdate = custDocumentRepository.findAll().size();

        // Update the custDocument
        CustDocument updatedCustDocument = custDocumentRepository.findById(custDocument.getId()).get();
        updatedCustDocument
            .custDocId(UPDATED_CUST_DOC_ID)
            .custAcctId(UPDATED_CUST_ACCT_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .docType(UPDATED_DOC_TYPE)
            .docIdNumber(UPDATED_DOC_ID_NUMBER)
            .docDataStoreId(UPDATED_DOC_DATA_STORE_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restCustDocumentMockMvc.perform(put("/api/cust-documents")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCustDocument)))
            .andExpect(status().isOk());

        // Validate the CustDocument in the database
        List<CustDocument> custDocumentList = custDocumentRepository.findAll();
        assertThat(custDocumentList).hasSize(databaseSizeBeforeUpdate);
        CustDocument testCustDocument = custDocumentList.get(custDocumentList.size() - 1);
        assertThat(testCustDocument.getCustDocId()).isEqualTo(UPDATED_CUST_DOC_ID);
        assertThat(testCustDocument.getCustAcctId()).isEqualTo(UPDATED_CUST_ACCT_ID);
        assertThat(testCustDocument.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testCustDocument.getDocType()).isEqualTo(UPDATED_DOC_TYPE);
        assertThat(testCustDocument.getDocIdNumber()).isEqualTo(UPDATED_DOC_ID_NUMBER);
        assertThat(testCustDocument.getDocDataStoreId()).isEqualTo(UPDATED_DOC_DATA_STORE_ID);
        assertThat(testCustDocument.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCustDocument.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustDocument.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testCustDocument.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testCustDocument.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingCustDocument() throws Exception {
        int databaseSizeBeforeUpdate = custDocumentRepository.findAll().size();

        // Create the CustDocument

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustDocumentMockMvc.perform(put("/api/cust-documents")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custDocument)))
            .andExpect(status().isBadRequest());

        // Validate the CustDocument in the database
        List<CustDocument> custDocumentList = custDocumentRepository.findAll();
        assertThat(custDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCustDocument() throws Exception {
        // Initialize the database
        custDocumentService.save(custDocument);

        int databaseSizeBeforeDelete = custDocumentRepository.findAll().size();

        // Delete the custDocument
        restCustDocumentMockMvc.perform(delete("/api/cust-documents/{id}", custDocument.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustDocument> custDocumentList = custDocumentRepository.findAll();
        assertThat(custDocumentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
