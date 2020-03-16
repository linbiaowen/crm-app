package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.SubscriptionProvision;
import com.hthk.crm.repository.SubscriptionProvisionRepository;
import com.hthk.crm.service.SubscriptionProvisionService;
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
 * Integration tests for the {@link SubscriptionProvisionResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class SubscriptionProvisionResourceIT {

    private static final String DEFAULT_PROVISION_SEQ_ID = "AAAAAAAAAA";
    private static final String UPDATED_PROVISION_SEQ_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIPTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MSISDN = "AAAAAAAAAA";
    private static final String UPDATED_MSISDN = "BBBBBBBBBB";

    private static final String DEFAULT_ICCID = "AAAAAAAAAA";
    private static final String UPDATED_ICCID = "BBBBBBBBBB";

    private static final String DEFAULT_IMSI = "AAAAAAAAAA";
    private static final String UPDATED_IMSI = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_SPEC_ID = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_SPEC_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RESOURCE_SPEC_ID = "AAAAAAAAAA";
    private static final String UPDATED_RESOURCE_SPEC_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RFS = "AAAAAAAAAA";
    private static final String UPDATED_RFS = "BBBBBBBBBB";

    private static final String DEFAULT_PROVISION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PROVISION_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PROVISION_STATUS_DESC = "AAAAAAAAAA";
    private static final String UPDATED_PROVISION_STATUS_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_PROVISION_RESPONSE = "AAAAAAAAAA";
    private static final String UPDATED_PROVISION_RESPONSE = "BBBBBBBBBB";

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
    private SubscriptionProvisionRepository subscriptionProvisionRepository;

    @Autowired
    private SubscriptionProvisionService subscriptionProvisionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restSubscriptionProvisionMockMvc;

    private SubscriptionProvision subscriptionProvision;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SubscriptionProvisionResource subscriptionProvisionResource = new SubscriptionProvisionResource(subscriptionProvisionService);
        this.restSubscriptionProvisionMockMvc = MockMvcBuilders.standaloneSetup(subscriptionProvisionResource)
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
    public static SubscriptionProvision createEntity() {
        SubscriptionProvision subscriptionProvision = new SubscriptionProvision()
            .provisionSeqId(DEFAULT_PROVISION_SEQ_ID)
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .orderId(DEFAULT_ORDER_ID)
            .productId(DEFAULT_PRODUCT_ID)
            .msisdn(DEFAULT_MSISDN)
            .iccid(DEFAULT_ICCID)
            .imsi(DEFAULT_IMSI)
            .serviceSpecId(DEFAULT_SERVICE_SPEC_ID)
            .resourceSpecId(DEFAULT_RESOURCE_SPEC_ID)
            .rfs(DEFAULT_RFS)
            .provisionStatus(DEFAULT_PROVISION_STATUS)
            .provisionStatusDesc(DEFAULT_PROVISION_STATUS_DESC)
            .provisionResponse(DEFAULT_PROVISION_RESPONSE)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return subscriptionProvision;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubscriptionProvision createUpdatedEntity() {
        SubscriptionProvision subscriptionProvision = new SubscriptionProvision()
            .provisionSeqId(UPDATED_PROVISION_SEQ_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .orderId(UPDATED_ORDER_ID)
            .productId(UPDATED_PRODUCT_ID)
            .msisdn(UPDATED_MSISDN)
            .iccid(UPDATED_ICCID)
            .imsi(UPDATED_IMSI)
            .serviceSpecId(UPDATED_SERVICE_SPEC_ID)
            .resourceSpecId(UPDATED_RESOURCE_SPEC_ID)
            .rfs(UPDATED_RFS)
            .provisionStatus(UPDATED_PROVISION_STATUS)
            .provisionStatusDesc(UPDATED_PROVISION_STATUS_DESC)
            .provisionResponse(UPDATED_PROVISION_RESPONSE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return subscriptionProvision;
    }

    @BeforeEach
    public void initTest() {
        subscriptionProvisionRepository.deleteAll();
        subscriptionProvision = createEntity();
    }

    @Test
    public void createSubscriptionProvision() throws Exception {
        int databaseSizeBeforeCreate = subscriptionProvisionRepository.findAll().size();

        // Create the SubscriptionProvision
        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isCreated());

        // Validate the SubscriptionProvision in the database
        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeCreate + 1);
        SubscriptionProvision testSubscriptionProvision = subscriptionProvisionList.get(subscriptionProvisionList.size() - 1);
        assertThat(testSubscriptionProvision.getProvisionSeqId()).isEqualTo(DEFAULT_PROVISION_SEQ_ID);
        assertThat(testSubscriptionProvision.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testSubscriptionProvision.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testSubscriptionProvision.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testSubscriptionProvision.getMsisdn()).isEqualTo(DEFAULT_MSISDN);
        assertThat(testSubscriptionProvision.getIccid()).isEqualTo(DEFAULT_ICCID);
        assertThat(testSubscriptionProvision.getImsi()).isEqualTo(DEFAULT_IMSI);
        assertThat(testSubscriptionProvision.getServiceSpecId()).isEqualTo(DEFAULT_SERVICE_SPEC_ID);
        assertThat(testSubscriptionProvision.getResourceSpecId()).isEqualTo(DEFAULT_RESOURCE_SPEC_ID);
        assertThat(testSubscriptionProvision.getRfs()).isEqualTo(DEFAULT_RFS);
        assertThat(testSubscriptionProvision.getProvisionStatus()).isEqualTo(DEFAULT_PROVISION_STATUS);
        assertThat(testSubscriptionProvision.getProvisionStatusDesc()).isEqualTo(DEFAULT_PROVISION_STATUS_DESC);
        assertThat(testSubscriptionProvision.getProvisionResponse()).isEqualTo(DEFAULT_PROVISION_RESPONSE);
        assertThat(testSubscriptionProvision.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testSubscriptionProvision.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testSubscriptionProvision.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSubscriptionProvision.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSubscriptionProvision.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testSubscriptionProvision.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testSubscriptionProvision.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createSubscriptionProvisionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subscriptionProvisionRepository.findAll().size();

        // Create the SubscriptionProvision with an existing ID
        subscriptionProvision.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        // Validate the SubscriptionProvision in the database
        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkProvisionSeqIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProvisionRepository.findAll().size();
        // set the field null
        subscriptionProvision.setProvisionSeqId(null);

        // Create the SubscriptionProvision, which fails.

        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSubscriptionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProvisionRepository.findAll().size();
        // set the field null
        subscriptionProvision.setSubscriptionId(null);

        // Create the SubscriptionProvision, which fails.

        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOrderIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProvisionRepository.findAll().size();
        // set the field null
        subscriptionProvision.setOrderId(null);

        // Create the SubscriptionProvision, which fails.

        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkProductIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProvisionRepository.findAll().size();
        // set the field null
        subscriptionProvision.setProductId(null);

        // Create the SubscriptionProvision, which fails.

        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkMsisdnIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProvisionRepository.findAll().size();
        // set the field null
        subscriptionProvision.setMsisdn(null);

        // Create the SubscriptionProvision, which fails.

        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIccidIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProvisionRepository.findAll().size();
        // set the field null
        subscriptionProvision.setIccid(null);

        // Create the SubscriptionProvision, which fails.

        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkServiceSpecIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProvisionRepository.findAll().size();
        // set the field null
        subscriptionProvision.setServiceSpecId(null);

        // Create the SubscriptionProvision, which fails.

        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkResourceSpecIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProvisionRepository.findAll().size();
        // set the field null
        subscriptionProvision.setResourceSpecId(null);

        // Create the SubscriptionProvision, which fails.

        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkRfsIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProvisionRepository.findAll().size();
        // set the field null
        subscriptionProvision.setRfs(null);

        // Create the SubscriptionProvision, which fails.

        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkProvisionStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProvisionRepository.findAll().size();
        // set the field null
        subscriptionProvision.setProvisionStatus(null);

        // Create the SubscriptionProvision, which fails.

        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProvisionRepository.findAll().size();
        // set the field null
        subscriptionProvision.setCreatedDate(null);

        // Create the SubscriptionProvision, which fails.

        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProvisionRepository.findAll().size();
        // set the field null
        subscriptionProvision.setCreatedBy(null);

        // Create the SubscriptionProvision, which fails.

        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProvisionRepository.findAll().size();
        // set the field null
        subscriptionProvision.setLastUpdatedDate(null);

        // Create the SubscriptionProvision, which fails.

        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProvisionRepository.findAll().size();
        // set the field null
        subscriptionProvision.setLastUpdatedBy(null);

        // Create the SubscriptionProvision, which fails.

        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProvisionRepository.findAll().size();
        // set the field null
        subscriptionProvision.setTenantId(null);

        // Create the SubscriptionProvision, which fails.

        restSubscriptionProvisionMockMvc.perform(post("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllSubscriptionProvisions() throws Exception {
        // Initialize the database
        subscriptionProvisionRepository.save(subscriptionProvision);

        // Get all the subscriptionProvisionList
        restSubscriptionProvisionMockMvc.perform(get("/api/subscription-provisions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subscriptionProvision.getId())))
            .andExpect(jsonPath("$.[*].provisionSeqId").value(hasItem(DEFAULT_PROVISION_SEQ_ID)))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID)))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID)))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].msisdn").value(hasItem(DEFAULT_MSISDN)))
            .andExpect(jsonPath("$.[*].iccid").value(hasItem(DEFAULT_ICCID)))
            .andExpect(jsonPath("$.[*].imsi").value(hasItem(DEFAULT_IMSI)))
            .andExpect(jsonPath("$.[*].serviceSpecId").value(hasItem(DEFAULT_SERVICE_SPEC_ID)))
            .andExpect(jsonPath("$.[*].resourceSpecId").value(hasItem(DEFAULT_RESOURCE_SPEC_ID)))
            .andExpect(jsonPath("$.[*].rfs").value(hasItem(DEFAULT_RFS)))
            .andExpect(jsonPath("$.[*].provisionStatus").value(hasItem(DEFAULT_PROVISION_STATUS)))
            .andExpect(jsonPath("$.[*].provisionStatusDesc").value(hasItem(DEFAULT_PROVISION_STATUS_DESC)))
            .andExpect(jsonPath("$.[*].provisionResponse").value(hasItem(DEFAULT_PROVISION_RESPONSE)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getSubscriptionProvision() throws Exception {
        // Initialize the database
        subscriptionProvisionRepository.save(subscriptionProvision);

        // Get the subscriptionProvision
        restSubscriptionProvisionMockMvc.perform(get("/api/subscription-provisions/{id}", subscriptionProvision.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subscriptionProvision.getId()))
            .andExpect(jsonPath("$.provisionSeqId").value(DEFAULT_PROVISION_SEQ_ID))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.msisdn").value(DEFAULT_MSISDN))
            .andExpect(jsonPath("$.iccid").value(DEFAULT_ICCID))
            .andExpect(jsonPath("$.imsi").value(DEFAULT_IMSI))
            .andExpect(jsonPath("$.serviceSpecId").value(DEFAULT_SERVICE_SPEC_ID))
            .andExpect(jsonPath("$.resourceSpecId").value(DEFAULT_RESOURCE_SPEC_ID))
            .andExpect(jsonPath("$.rfs").value(DEFAULT_RFS))
            .andExpect(jsonPath("$.provisionStatus").value(DEFAULT_PROVISION_STATUS))
            .andExpect(jsonPath("$.provisionStatusDesc").value(DEFAULT_PROVISION_STATUS_DESC))
            .andExpect(jsonPath("$.provisionResponse").value(DEFAULT_PROVISION_RESPONSE))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingSubscriptionProvision() throws Exception {
        // Get the subscriptionProvision
        restSubscriptionProvisionMockMvc.perform(get("/api/subscription-provisions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateSubscriptionProvision() throws Exception {
        // Initialize the database
        subscriptionProvisionService.save(subscriptionProvision);

        int databaseSizeBeforeUpdate = subscriptionProvisionRepository.findAll().size();

        // Update the subscriptionProvision
        SubscriptionProvision updatedSubscriptionProvision = subscriptionProvisionRepository.findById(subscriptionProvision.getId()).get();
        updatedSubscriptionProvision
            .provisionSeqId(UPDATED_PROVISION_SEQ_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .orderId(UPDATED_ORDER_ID)
            .productId(UPDATED_PRODUCT_ID)
            .msisdn(UPDATED_MSISDN)
            .iccid(UPDATED_ICCID)
            .imsi(UPDATED_IMSI)
            .serviceSpecId(UPDATED_SERVICE_SPEC_ID)
            .resourceSpecId(UPDATED_RESOURCE_SPEC_ID)
            .rfs(UPDATED_RFS)
            .provisionStatus(UPDATED_PROVISION_STATUS)
            .provisionStatusDesc(UPDATED_PROVISION_STATUS_DESC)
            .provisionResponse(UPDATED_PROVISION_RESPONSE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restSubscriptionProvisionMockMvc.perform(put("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSubscriptionProvision)))
            .andExpect(status().isOk());

        // Validate the SubscriptionProvision in the database
        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeUpdate);
        SubscriptionProvision testSubscriptionProvision = subscriptionProvisionList.get(subscriptionProvisionList.size() - 1);
        assertThat(testSubscriptionProvision.getProvisionSeqId()).isEqualTo(UPDATED_PROVISION_SEQ_ID);
        assertThat(testSubscriptionProvision.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testSubscriptionProvision.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testSubscriptionProvision.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testSubscriptionProvision.getMsisdn()).isEqualTo(UPDATED_MSISDN);
        assertThat(testSubscriptionProvision.getIccid()).isEqualTo(UPDATED_ICCID);
        assertThat(testSubscriptionProvision.getImsi()).isEqualTo(UPDATED_IMSI);
        assertThat(testSubscriptionProvision.getServiceSpecId()).isEqualTo(UPDATED_SERVICE_SPEC_ID);
        assertThat(testSubscriptionProvision.getResourceSpecId()).isEqualTo(UPDATED_RESOURCE_SPEC_ID);
        assertThat(testSubscriptionProvision.getRfs()).isEqualTo(UPDATED_RFS);
        assertThat(testSubscriptionProvision.getProvisionStatus()).isEqualTo(UPDATED_PROVISION_STATUS);
        assertThat(testSubscriptionProvision.getProvisionStatusDesc()).isEqualTo(UPDATED_PROVISION_STATUS_DESC);
        assertThat(testSubscriptionProvision.getProvisionResponse()).isEqualTo(UPDATED_PROVISION_RESPONSE);
        assertThat(testSubscriptionProvision.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testSubscriptionProvision.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testSubscriptionProvision.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSubscriptionProvision.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSubscriptionProvision.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testSubscriptionProvision.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testSubscriptionProvision.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingSubscriptionProvision() throws Exception {
        int databaseSizeBeforeUpdate = subscriptionProvisionRepository.findAll().size();

        // Create the SubscriptionProvision

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubscriptionProvisionMockMvc.perform(put("/api/subscription-provisions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProvision)))
            .andExpect(status().isBadRequest());

        // Validate the SubscriptionProvision in the database
        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteSubscriptionProvision() throws Exception {
        // Initialize the database
        subscriptionProvisionService.save(subscriptionProvision);

        int databaseSizeBeforeDelete = subscriptionProvisionRepository.findAll().size();

        // Delete the subscriptionProvision
        restSubscriptionProvisionMockMvc.perform(delete("/api/subscription-provisions/{id}", subscriptionProvision.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SubscriptionProvision> subscriptionProvisionList = subscriptionProvisionRepository.findAll();
        assertThat(subscriptionProvisionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
