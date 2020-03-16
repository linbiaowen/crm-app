package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.CustSubscription;
import com.hthk.crm.repository.CustSubscriptionRepository;
import com.hthk.crm.service.CustSubscriptionService;
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

import com.hthk.crm.domain.enumeration.SubscriptionStatus;
/**
 * Integration tests for the {@link CustSubscriptionResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class CustSubscriptionResourceIT {

    private static final String DEFAULT_SUBSCRIPTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_ACTIVATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ACTIVATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SUBS_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SUBS_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SUBS_PURCHASE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SUBS_PURCHASE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ORIG_SERVICE_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ORIG_SERVICE_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final SubscriptionStatus DEFAULT_STATUS = SubscriptionStatus.PREACTIVE;
    private static final SubscriptionStatus UPDATED_STATUS = SubscriptionStatus.ACTIVE;

    private static final Boolean DEFAULT_PRIMARY_SUBS_IND = false;
    private static final Boolean UPDATED_PRIMARY_SUBS_IND = true;

    private static final String DEFAULT_SUBS_LAST_STATUS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SUBS_LAST_STATUS_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_STATUS_UPDATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_STATUS_UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CUST_ACCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUST_ACCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ACCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ACCT_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_BILL_CYCLE_ID = 1;
    private static final Integer UPDATED_BILL_CYCLE_ID = 2;

    private static final String DEFAULT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MATRIXX_OBJECT_ID = "AAAAAAAAAA";
    private static final String UPDATED_MATRIXX_OBJECT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBS_DEPT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBS_DEPT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SELF_CARE_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_SELF_CARE_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_SUBS_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_SUBS_CATEGORY = "BBBBBBBBBB";

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
    private CustSubscriptionRepository custSubscriptionRepository;

    @Autowired
    private CustSubscriptionService custSubscriptionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCustSubscriptionMockMvc;

    private CustSubscription custSubscription;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustSubscriptionResource custSubscriptionResource = new CustSubscriptionResource(custSubscriptionService);
        this.restCustSubscriptionMockMvc = MockMvcBuilders.standaloneSetup(custSubscriptionResource)
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
    public static CustSubscription createEntity() {
        CustSubscription custSubscription = new CustSubscription()
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .activationDate(DEFAULT_ACTIVATION_DATE)
            .subsEndDate(DEFAULT_SUBS_END_DATE)
            .subsPurchaseDate(DEFAULT_SUBS_PURCHASE_DATE)
            .origServiceStartDate(DEFAULT_ORIG_SERVICE_START_DATE)
            .status(DEFAULT_STATUS)
            .primarySubsInd(DEFAULT_PRIMARY_SUBS_IND)
            .subsLastStatusCode(DEFAULT_SUBS_LAST_STATUS_CODE)
            .lastStatusUpdatedDate(DEFAULT_LAST_STATUS_UPDATED_DATE)
            .custAcctId(DEFAULT_CUST_ACCT_ID)
            .billingAcctId(DEFAULT_BILLING_ACCT_ID)
            .billCycleId(DEFAULT_BILL_CYCLE_ID)
            .orderId(DEFAULT_ORDER_ID)
            .matrixxObjectId(DEFAULT_MATRIXX_OBJECT_ID)
            .subscriberName(DEFAULT_SUBSCRIBER_NAME)
            .subsDeptName(DEFAULT_SUBS_DEPT_NAME)
            .selfCarePassword(DEFAULT_SELF_CARE_PASSWORD)
            .subsCategory(DEFAULT_SUBS_CATEGORY)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return custSubscription;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustSubscription createUpdatedEntity() {
        CustSubscription custSubscription = new CustSubscription()
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .activationDate(UPDATED_ACTIVATION_DATE)
            .subsEndDate(UPDATED_SUBS_END_DATE)
            .subsPurchaseDate(UPDATED_SUBS_PURCHASE_DATE)
            .origServiceStartDate(UPDATED_ORIG_SERVICE_START_DATE)
            .status(UPDATED_STATUS)
            .primarySubsInd(UPDATED_PRIMARY_SUBS_IND)
            .subsLastStatusCode(UPDATED_SUBS_LAST_STATUS_CODE)
            .lastStatusUpdatedDate(UPDATED_LAST_STATUS_UPDATED_DATE)
            .custAcctId(UPDATED_CUST_ACCT_ID)
            .billingAcctId(UPDATED_BILLING_ACCT_ID)
            .billCycleId(UPDATED_BILL_CYCLE_ID)
            .orderId(UPDATED_ORDER_ID)
            .matrixxObjectId(UPDATED_MATRIXX_OBJECT_ID)
            .subscriberName(UPDATED_SUBSCRIBER_NAME)
            .subsDeptName(UPDATED_SUBS_DEPT_NAME)
            .selfCarePassword(UPDATED_SELF_CARE_PASSWORD)
            .subsCategory(UPDATED_SUBS_CATEGORY)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return custSubscription;
    }

    @BeforeEach
    public void initTest() {
        custSubscriptionRepository.deleteAll();
        custSubscription = createEntity();
    }

    @Test
    public void createCustSubscription() throws Exception {
        int databaseSizeBeforeCreate = custSubscriptionRepository.findAll().size();

        // Create the CustSubscription
        restCustSubscriptionMockMvc.perform(post("/api/cust-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custSubscription)))
            .andExpect(status().isCreated());

        // Validate the CustSubscription in the database
        List<CustSubscription> custSubscriptionList = custSubscriptionRepository.findAll();
        assertThat(custSubscriptionList).hasSize(databaseSizeBeforeCreate + 1);
        CustSubscription testCustSubscription = custSubscriptionList.get(custSubscriptionList.size() - 1);
        assertThat(testCustSubscription.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testCustSubscription.getActivationDate()).isEqualTo(DEFAULT_ACTIVATION_DATE);
        assertThat(testCustSubscription.getSubsEndDate()).isEqualTo(DEFAULT_SUBS_END_DATE);
        assertThat(testCustSubscription.getSubsPurchaseDate()).isEqualTo(DEFAULT_SUBS_PURCHASE_DATE);
        assertThat(testCustSubscription.getOrigServiceStartDate()).isEqualTo(DEFAULT_ORIG_SERVICE_START_DATE);
        assertThat(testCustSubscription.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCustSubscription.isPrimarySubsInd()).isEqualTo(DEFAULT_PRIMARY_SUBS_IND);
        assertThat(testCustSubscription.getSubsLastStatusCode()).isEqualTo(DEFAULT_SUBS_LAST_STATUS_CODE);
        assertThat(testCustSubscription.getLastStatusUpdatedDate()).isEqualTo(DEFAULT_LAST_STATUS_UPDATED_DATE);
        assertThat(testCustSubscription.getCustAcctId()).isEqualTo(DEFAULT_CUST_ACCT_ID);
        assertThat(testCustSubscription.getBillingAcctId()).isEqualTo(DEFAULT_BILLING_ACCT_ID);
        assertThat(testCustSubscription.getBillCycleId()).isEqualTo(DEFAULT_BILL_CYCLE_ID);
        assertThat(testCustSubscription.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testCustSubscription.getMatrixxObjectId()).isEqualTo(DEFAULT_MATRIXX_OBJECT_ID);
        assertThat(testCustSubscription.getSubscriberName()).isEqualTo(DEFAULT_SUBSCRIBER_NAME);
        assertThat(testCustSubscription.getSubsDeptName()).isEqualTo(DEFAULT_SUBS_DEPT_NAME);
        assertThat(testCustSubscription.getSelfCarePassword()).isEqualTo(DEFAULT_SELF_CARE_PASSWORD);
        assertThat(testCustSubscription.getSubsCategory()).isEqualTo(DEFAULT_SUBS_CATEGORY);
        assertThat(testCustSubscription.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCustSubscription.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustSubscription.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testCustSubscription.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testCustSubscription.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createCustSubscriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = custSubscriptionRepository.findAll().size();

        // Create the CustSubscription with an existing ID
        custSubscription.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustSubscriptionMockMvc.perform(post("/api/cust-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custSubscription)))
            .andExpect(status().isBadRequest());

        // Validate the CustSubscription in the database
        List<CustSubscription> custSubscriptionList = custSubscriptionRepository.findAll();
        assertThat(custSubscriptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkSubscriptionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custSubscriptionRepository.findAll().size();
        // set the field null
        custSubscription.setSubscriptionId(null);

        // Create the CustSubscription, which fails.

        restCustSubscriptionMockMvc.perform(post("/api/cust-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custSubscription)))
            .andExpect(status().isBadRequest());

        List<CustSubscription> custSubscriptionList = custSubscriptionRepository.findAll();
        assertThat(custSubscriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = custSubscriptionRepository.findAll().size();
        // set the field null
        custSubscription.setStatus(null);

        // Create the CustSubscription, which fails.

        restCustSubscriptionMockMvc.perform(post("/api/cust-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custSubscription)))
            .andExpect(status().isBadRequest());

        List<CustSubscription> custSubscriptionList = custSubscriptionRepository.findAll();
        assertThat(custSubscriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = custSubscriptionRepository.findAll().size();
        // set the field null
        custSubscription.setCreatedDate(null);

        // Create the CustSubscription, which fails.

        restCustSubscriptionMockMvc.perform(post("/api/cust-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custSubscription)))
            .andExpect(status().isBadRequest());

        List<CustSubscription> custSubscriptionList = custSubscriptionRepository.findAll();
        assertThat(custSubscriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = custSubscriptionRepository.findAll().size();
        // set the field null
        custSubscription.setCreatedBy(null);

        // Create the CustSubscription, which fails.

        restCustSubscriptionMockMvc.perform(post("/api/cust-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custSubscription)))
            .andExpect(status().isBadRequest());

        List<CustSubscription> custSubscriptionList = custSubscriptionRepository.findAll();
        assertThat(custSubscriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = custSubscriptionRepository.findAll().size();
        // set the field null
        custSubscription.setLastUpdatedDate(null);

        // Create the CustSubscription, which fails.

        restCustSubscriptionMockMvc.perform(post("/api/cust-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custSubscription)))
            .andExpect(status().isBadRequest());

        List<CustSubscription> custSubscriptionList = custSubscriptionRepository.findAll();
        assertThat(custSubscriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = custSubscriptionRepository.findAll().size();
        // set the field null
        custSubscription.setLastUpdatedBy(null);

        // Create the CustSubscription, which fails.

        restCustSubscriptionMockMvc.perform(post("/api/cust-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custSubscription)))
            .andExpect(status().isBadRequest());

        List<CustSubscription> custSubscriptionList = custSubscriptionRepository.findAll();
        assertThat(custSubscriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custSubscriptionRepository.findAll().size();
        // set the field null
        custSubscription.setTenantId(null);

        // Create the CustSubscription, which fails.

        restCustSubscriptionMockMvc.perform(post("/api/cust-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custSubscription)))
            .andExpect(status().isBadRequest());

        List<CustSubscription> custSubscriptionList = custSubscriptionRepository.findAll();
        assertThat(custSubscriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCustSubscriptions() throws Exception {
        // Initialize the database
        custSubscriptionRepository.save(custSubscription);

        // Get all the custSubscriptionList
        restCustSubscriptionMockMvc.perform(get("/api/cust-subscriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(custSubscription.getId())))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID)))
            .andExpect(jsonPath("$.[*].activationDate").value(hasItem(DEFAULT_ACTIVATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].subsEndDate").value(hasItem(DEFAULT_SUBS_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].subsPurchaseDate").value(hasItem(DEFAULT_SUBS_PURCHASE_DATE.toString())))
            .andExpect(jsonPath("$.[*].origServiceStartDate").value(hasItem(DEFAULT_ORIG_SERVICE_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].primarySubsInd").value(hasItem(DEFAULT_PRIMARY_SUBS_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].subsLastStatusCode").value(hasItem(DEFAULT_SUBS_LAST_STATUS_CODE)))
            .andExpect(jsonPath("$.[*].lastStatusUpdatedDate").value(hasItem(DEFAULT_LAST_STATUS_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].custAcctId").value(hasItem(DEFAULT_CUST_ACCT_ID)))
            .andExpect(jsonPath("$.[*].billingAcctId").value(hasItem(DEFAULT_BILLING_ACCT_ID)))
            .andExpect(jsonPath("$.[*].billCycleId").value(hasItem(DEFAULT_BILL_CYCLE_ID)))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID)))
            .andExpect(jsonPath("$.[*].matrixxObjectId").value(hasItem(DEFAULT_MATRIXX_OBJECT_ID)))
            .andExpect(jsonPath("$.[*].subscriberName").value(hasItem(DEFAULT_SUBSCRIBER_NAME)))
            .andExpect(jsonPath("$.[*].subsDeptName").value(hasItem(DEFAULT_SUBS_DEPT_NAME)))
            .andExpect(jsonPath("$.[*].selfCarePassword").value(hasItem(DEFAULT_SELF_CARE_PASSWORD)))
            .andExpect(jsonPath("$.[*].subsCategory").value(hasItem(DEFAULT_SUBS_CATEGORY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getCustSubscription() throws Exception {
        // Initialize the database
        custSubscriptionRepository.save(custSubscription);

        // Get the custSubscription
        restCustSubscriptionMockMvc.perform(get("/api/cust-subscriptions/{id}", custSubscription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(custSubscription.getId()))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID))
            .andExpect(jsonPath("$.activationDate").value(DEFAULT_ACTIVATION_DATE.toString()))
            .andExpect(jsonPath("$.subsEndDate").value(DEFAULT_SUBS_END_DATE.toString()))
            .andExpect(jsonPath("$.subsPurchaseDate").value(DEFAULT_SUBS_PURCHASE_DATE.toString()))
            .andExpect(jsonPath("$.origServiceStartDate").value(DEFAULT_ORIG_SERVICE_START_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.primarySubsInd").value(DEFAULT_PRIMARY_SUBS_IND.booleanValue()))
            .andExpect(jsonPath("$.subsLastStatusCode").value(DEFAULT_SUBS_LAST_STATUS_CODE))
            .andExpect(jsonPath("$.lastStatusUpdatedDate").value(DEFAULT_LAST_STATUS_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.custAcctId").value(DEFAULT_CUST_ACCT_ID))
            .andExpect(jsonPath("$.billingAcctId").value(DEFAULT_BILLING_ACCT_ID))
            .andExpect(jsonPath("$.billCycleId").value(DEFAULT_BILL_CYCLE_ID))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID))
            .andExpect(jsonPath("$.matrixxObjectId").value(DEFAULT_MATRIXX_OBJECT_ID))
            .andExpect(jsonPath("$.subscriberName").value(DEFAULT_SUBSCRIBER_NAME))
            .andExpect(jsonPath("$.subsDeptName").value(DEFAULT_SUBS_DEPT_NAME))
            .andExpect(jsonPath("$.selfCarePassword").value(DEFAULT_SELF_CARE_PASSWORD))
            .andExpect(jsonPath("$.subsCategory").value(DEFAULT_SUBS_CATEGORY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingCustSubscription() throws Exception {
        // Get the custSubscription
        restCustSubscriptionMockMvc.perform(get("/api/cust-subscriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCustSubscription() throws Exception {
        // Initialize the database
        custSubscriptionService.save(custSubscription);

        int databaseSizeBeforeUpdate = custSubscriptionRepository.findAll().size();

        // Update the custSubscription
        CustSubscription updatedCustSubscription = custSubscriptionRepository.findById(custSubscription.getId()).get();
        updatedCustSubscription
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .activationDate(UPDATED_ACTIVATION_DATE)
            .subsEndDate(UPDATED_SUBS_END_DATE)
            .subsPurchaseDate(UPDATED_SUBS_PURCHASE_DATE)
            .origServiceStartDate(UPDATED_ORIG_SERVICE_START_DATE)
            .status(UPDATED_STATUS)
            .primarySubsInd(UPDATED_PRIMARY_SUBS_IND)
            .subsLastStatusCode(UPDATED_SUBS_LAST_STATUS_CODE)
            .lastStatusUpdatedDate(UPDATED_LAST_STATUS_UPDATED_DATE)
            .custAcctId(UPDATED_CUST_ACCT_ID)
            .billingAcctId(UPDATED_BILLING_ACCT_ID)
            .billCycleId(UPDATED_BILL_CYCLE_ID)
            .orderId(UPDATED_ORDER_ID)
            .matrixxObjectId(UPDATED_MATRIXX_OBJECT_ID)
            .subscriberName(UPDATED_SUBSCRIBER_NAME)
            .subsDeptName(UPDATED_SUBS_DEPT_NAME)
            .selfCarePassword(UPDATED_SELF_CARE_PASSWORD)
            .subsCategory(UPDATED_SUBS_CATEGORY)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restCustSubscriptionMockMvc.perform(put("/api/cust-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCustSubscription)))
            .andExpect(status().isOk());

        // Validate the CustSubscription in the database
        List<CustSubscription> custSubscriptionList = custSubscriptionRepository.findAll();
        assertThat(custSubscriptionList).hasSize(databaseSizeBeforeUpdate);
        CustSubscription testCustSubscription = custSubscriptionList.get(custSubscriptionList.size() - 1);
        assertThat(testCustSubscription.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testCustSubscription.getActivationDate()).isEqualTo(UPDATED_ACTIVATION_DATE);
        assertThat(testCustSubscription.getSubsEndDate()).isEqualTo(UPDATED_SUBS_END_DATE);
        assertThat(testCustSubscription.getSubsPurchaseDate()).isEqualTo(UPDATED_SUBS_PURCHASE_DATE);
        assertThat(testCustSubscription.getOrigServiceStartDate()).isEqualTo(UPDATED_ORIG_SERVICE_START_DATE);
        assertThat(testCustSubscription.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCustSubscription.isPrimarySubsInd()).isEqualTo(UPDATED_PRIMARY_SUBS_IND);
        assertThat(testCustSubscription.getSubsLastStatusCode()).isEqualTo(UPDATED_SUBS_LAST_STATUS_CODE);
        assertThat(testCustSubscription.getLastStatusUpdatedDate()).isEqualTo(UPDATED_LAST_STATUS_UPDATED_DATE);
        assertThat(testCustSubscription.getCustAcctId()).isEqualTo(UPDATED_CUST_ACCT_ID);
        assertThat(testCustSubscription.getBillingAcctId()).isEqualTo(UPDATED_BILLING_ACCT_ID);
        assertThat(testCustSubscription.getBillCycleId()).isEqualTo(UPDATED_BILL_CYCLE_ID);
        assertThat(testCustSubscription.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testCustSubscription.getMatrixxObjectId()).isEqualTo(UPDATED_MATRIXX_OBJECT_ID);
        assertThat(testCustSubscription.getSubscriberName()).isEqualTo(UPDATED_SUBSCRIBER_NAME);
        assertThat(testCustSubscription.getSubsDeptName()).isEqualTo(UPDATED_SUBS_DEPT_NAME);
        assertThat(testCustSubscription.getSelfCarePassword()).isEqualTo(UPDATED_SELF_CARE_PASSWORD);
        assertThat(testCustSubscription.getSubsCategory()).isEqualTo(UPDATED_SUBS_CATEGORY);
        assertThat(testCustSubscription.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCustSubscription.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustSubscription.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testCustSubscription.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testCustSubscription.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingCustSubscription() throws Exception {
        int databaseSizeBeforeUpdate = custSubscriptionRepository.findAll().size();

        // Create the CustSubscription

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustSubscriptionMockMvc.perform(put("/api/cust-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custSubscription)))
            .andExpect(status().isBadRequest());

        // Validate the CustSubscription in the database
        List<CustSubscription> custSubscriptionList = custSubscriptionRepository.findAll();
        assertThat(custSubscriptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCustSubscription() throws Exception {
        // Initialize the database
        custSubscriptionService.save(custSubscription);

        int databaseSizeBeforeDelete = custSubscriptionRepository.findAll().size();

        // Delete the custSubscription
        restCustSubscriptionMockMvc.perform(delete("/api/cust-subscriptions/{id}", custSubscription.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustSubscription> custSubscriptionList = custSubscriptionRepository.findAll();
        assertThat(custSubscriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
