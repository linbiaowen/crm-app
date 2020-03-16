package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.SubscriptionDetails;
import com.hthk.crm.repository.SubscriptionDetailsRepository;
import com.hthk.crm.service.SubscriptionDetailsService;
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


import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.hthk.crm.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.hthk.crm.domain.enumeration.Language;
/**
 * Integration tests for the {@link SubscriptionDetailsResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class SubscriptionDetailsResourceIT {

    private static final Long DEFAULT_SUBS_DETAIL_ID = 1L;
    private static final Long UPDATED_SUBS_DETAIL_ID = 2L;

    private static final String DEFAULT_SUBSCRIPTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SSA_NBR = "AAAAAAAAAA";
    private static final String UPDATED_SSA_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_MSISDN = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_MSISDN = "BBBBBBBBBB";

    private static final String DEFAULT_ICCID = "AAAAAAAAAA";
    private static final String UPDATED_ICCID = "BBBBBBBBBB";

    private static final String DEFAULT_IMSI = "AAAAAAAAAA";
    private static final String UPDATED_IMSI = "BBBBBBBBBB";

    private static final Instant DEFAULT_MNP_REQUESTED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MNP_REQUESTED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Language DEFAULT_LANG = Language.CHINESE;
    private static final Language UPDATED_LANG = Language.ENGLISH;

    private static final String DEFAULT_BASE_OFFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_BASE_OFFER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BASE_OFFER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BASE_OFFER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MATRIXX_CATALOG_ID = "AAAAAAAAAA";
    private static final String UPDATED_MATRIXX_CATALOG_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MATRIXX_RESOURCE_ID = "AAAAAAAAAA";
    private static final String UPDATED_MATRIXX_RESOURCE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MATRIXX_OBJECT_ID = "AAAAAAAAAA";
    private static final String UPDATED_MATRIXX_OBJECT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_SALES_CHANNEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_ADVANCE_PAYMENT_MONTHS = 1;
    private static final Integer UPDATED_ADVANCE_PAYMENT_MONTHS = 2;

    private static final BigDecimal DEFAULT_OFFER_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_OFFER_PRICE = new BigDecimal(2);

    private static final String DEFAULT_NETWORK_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_NETWORK_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_PLAN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_PLAN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_IN_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_IN_PERSON = "BBBBBBBBBB";

    private static final String DEFAULT_FCM_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_FCM_TOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_CD_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_CD_VERSION = "BBBBBBBBBB";

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
    private SubscriptionDetailsRepository subscriptionDetailsRepository;

    @Autowired
    private SubscriptionDetailsService subscriptionDetailsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restSubscriptionDetailsMockMvc;

    private SubscriptionDetails subscriptionDetails;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SubscriptionDetailsResource subscriptionDetailsResource = new SubscriptionDetailsResource(subscriptionDetailsService);
        this.restSubscriptionDetailsMockMvc = MockMvcBuilders.standaloneSetup(subscriptionDetailsResource)
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
    public static SubscriptionDetails createEntity() {
        SubscriptionDetails subscriptionDetails = new SubscriptionDetails()
            .subsDetailId(DEFAULT_SUBS_DETAIL_ID)
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .orderId(DEFAULT_ORDER_ID)
            .ssaNbr(DEFAULT_SSA_NBR)
            .primaryMsisdn(DEFAULT_PRIMARY_MSISDN)
            .iccid(DEFAULT_ICCID)
            .imsi(DEFAULT_IMSI)
            .mnpRequestedDate(DEFAULT_MNP_REQUESTED_DATE)
            .lang(DEFAULT_LANG)
            .baseOfferId(DEFAULT_BASE_OFFER_ID)
            .baseOfferName(DEFAULT_BASE_OFFER_NAME)
            .matrixxCatalogId(DEFAULT_MATRIXX_CATALOG_ID)
            .matrixxResourceId(DEFAULT_MATRIXX_RESOURCE_ID)
            .matrixxObjectId(DEFAULT_MATRIXX_OBJECT_ID)
            .salesChannel(DEFAULT_SALES_CHANNEL)
            .advancePaymentMonths(DEFAULT_ADVANCE_PAYMENT_MONTHS)
            .offerPrice(DEFAULT_OFFER_PRICE)
            .networkType(DEFAULT_NETWORK_TYPE)
            .serviceType(DEFAULT_SERVICE_TYPE)
            .offerPlanCode(DEFAULT_OFFER_PLAN_CODE)
            .serviceInPerson(DEFAULT_SERVICE_IN_PERSON)
            .fcmToken(DEFAULT_FCM_TOKEN)
            .remarks(DEFAULT_REMARKS)
            .cdVersion(DEFAULT_CD_VERSION)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return subscriptionDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubscriptionDetails createUpdatedEntity() {
        SubscriptionDetails subscriptionDetails = new SubscriptionDetails()
            .subsDetailId(UPDATED_SUBS_DETAIL_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .orderId(UPDATED_ORDER_ID)
            .ssaNbr(UPDATED_SSA_NBR)
            .primaryMsisdn(UPDATED_PRIMARY_MSISDN)
            .iccid(UPDATED_ICCID)
            .imsi(UPDATED_IMSI)
            .mnpRequestedDate(UPDATED_MNP_REQUESTED_DATE)
            .lang(UPDATED_LANG)
            .baseOfferId(UPDATED_BASE_OFFER_ID)
            .baseOfferName(UPDATED_BASE_OFFER_NAME)
            .matrixxCatalogId(UPDATED_MATRIXX_CATALOG_ID)
            .matrixxResourceId(UPDATED_MATRIXX_RESOURCE_ID)
            .matrixxObjectId(UPDATED_MATRIXX_OBJECT_ID)
            .salesChannel(UPDATED_SALES_CHANNEL)
            .advancePaymentMonths(UPDATED_ADVANCE_PAYMENT_MONTHS)
            .offerPrice(UPDATED_OFFER_PRICE)
            .networkType(UPDATED_NETWORK_TYPE)
            .serviceType(UPDATED_SERVICE_TYPE)
            .offerPlanCode(UPDATED_OFFER_PLAN_CODE)
            .serviceInPerson(UPDATED_SERVICE_IN_PERSON)
            .fcmToken(UPDATED_FCM_TOKEN)
            .remarks(UPDATED_REMARKS)
            .cdVersion(UPDATED_CD_VERSION)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return subscriptionDetails;
    }

    @BeforeEach
    public void initTest() {
        subscriptionDetailsRepository.deleteAll();
        subscriptionDetails = createEntity();
    }

    @Test
    public void createSubscriptionDetails() throws Exception {
        int databaseSizeBeforeCreate = subscriptionDetailsRepository.findAll().size();

        // Create the SubscriptionDetails
        restSubscriptionDetailsMockMvc.perform(post("/api/subscription-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionDetails)))
            .andExpect(status().isCreated());

        // Validate the SubscriptionDetails in the database
        List<SubscriptionDetails> subscriptionDetailsList = subscriptionDetailsRepository.findAll();
        assertThat(subscriptionDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        SubscriptionDetails testSubscriptionDetails = subscriptionDetailsList.get(subscriptionDetailsList.size() - 1);
        assertThat(testSubscriptionDetails.getSubsDetailId()).isEqualTo(DEFAULT_SUBS_DETAIL_ID);
        assertThat(testSubscriptionDetails.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testSubscriptionDetails.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testSubscriptionDetails.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testSubscriptionDetails.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testSubscriptionDetails.getSsaNbr()).isEqualTo(DEFAULT_SSA_NBR);
        assertThat(testSubscriptionDetails.getPrimaryMsisdn()).isEqualTo(DEFAULT_PRIMARY_MSISDN);
        assertThat(testSubscriptionDetails.getIccid()).isEqualTo(DEFAULT_ICCID);
        assertThat(testSubscriptionDetails.getImsi()).isEqualTo(DEFAULT_IMSI);
        assertThat(testSubscriptionDetails.getMnpRequestedDate()).isEqualTo(DEFAULT_MNP_REQUESTED_DATE);
        assertThat(testSubscriptionDetails.getLang()).isEqualTo(DEFAULT_LANG);
        assertThat(testSubscriptionDetails.getBaseOfferId()).isEqualTo(DEFAULT_BASE_OFFER_ID);
        assertThat(testSubscriptionDetails.getBaseOfferName()).isEqualTo(DEFAULT_BASE_OFFER_NAME);
        assertThat(testSubscriptionDetails.getMatrixxCatalogId()).isEqualTo(DEFAULT_MATRIXX_CATALOG_ID);
        assertThat(testSubscriptionDetails.getMatrixxResourceId()).isEqualTo(DEFAULT_MATRIXX_RESOURCE_ID);
        assertThat(testSubscriptionDetails.getMatrixxObjectId()).isEqualTo(DEFAULT_MATRIXX_OBJECT_ID);
        assertThat(testSubscriptionDetails.getSalesChannel()).isEqualTo(DEFAULT_SALES_CHANNEL);
        assertThat(testSubscriptionDetails.getAdvancePaymentMonths()).isEqualTo(DEFAULT_ADVANCE_PAYMENT_MONTHS);
        assertThat(testSubscriptionDetails.getOfferPrice()).isEqualTo(DEFAULT_OFFER_PRICE);
        assertThat(testSubscriptionDetails.getNetworkType()).isEqualTo(DEFAULT_NETWORK_TYPE);
        assertThat(testSubscriptionDetails.getServiceType()).isEqualTo(DEFAULT_SERVICE_TYPE);
        assertThat(testSubscriptionDetails.getOfferPlanCode()).isEqualTo(DEFAULT_OFFER_PLAN_CODE);
        assertThat(testSubscriptionDetails.getServiceInPerson()).isEqualTo(DEFAULT_SERVICE_IN_PERSON);
        assertThat(testSubscriptionDetails.getFcmToken()).isEqualTo(DEFAULT_FCM_TOKEN);
        assertThat(testSubscriptionDetails.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testSubscriptionDetails.getCdVersion()).isEqualTo(DEFAULT_CD_VERSION);
        assertThat(testSubscriptionDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSubscriptionDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSubscriptionDetails.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testSubscriptionDetails.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testSubscriptionDetails.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createSubscriptionDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subscriptionDetailsRepository.findAll().size();

        // Create the SubscriptionDetails with an existing ID
        subscriptionDetails.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubscriptionDetailsMockMvc.perform(post("/api/subscription-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionDetails)))
            .andExpect(status().isBadRequest());

        // Validate the SubscriptionDetails in the database
        List<SubscriptionDetails> subscriptionDetailsList = subscriptionDetailsRepository.findAll();
        assertThat(subscriptionDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkSubsDetailIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionDetailsRepository.findAll().size();
        // set the field null
        subscriptionDetails.setSubsDetailId(null);

        // Create the SubscriptionDetails, which fails.

        restSubscriptionDetailsMockMvc.perform(post("/api/subscription-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionDetails)))
            .andExpect(status().isBadRequest());

        List<SubscriptionDetails> subscriptionDetailsList = subscriptionDetailsRepository.findAll();
        assertThat(subscriptionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSubscriptionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionDetailsRepository.findAll().size();
        // set the field null
        subscriptionDetails.setSubscriptionId(null);

        // Create the SubscriptionDetails, which fails.

        restSubscriptionDetailsMockMvc.perform(post("/api/subscription-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionDetails)))
            .andExpect(status().isBadRequest());

        List<SubscriptionDetails> subscriptionDetailsList = subscriptionDetailsRepository.findAll();
        assertThat(subscriptionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLangIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionDetailsRepository.findAll().size();
        // set the field null
        subscriptionDetails.setLang(null);

        // Create the SubscriptionDetails, which fails.

        restSubscriptionDetailsMockMvc.perform(post("/api/subscription-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionDetails)))
            .andExpect(status().isBadRequest());

        List<SubscriptionDetails> subscriptionDetailsList = subscriptionDetailsRepository.findAll();
        assertThat(subscriptionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionDetailsRepository.findAll().size();
        // set the field null
        subscriptionDetails.setCreatedDate(null);

        // Create the SubscriptionDetails, which fails.

        restSubscriptionDetailsMockMvc.perform(post("/api/subscription-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionDetails)))
            .andExpect(status().isBadRequest());

        List<SubscriptionDetails> subscriptionDetailsList = subscriptionDetailsRepository.findAll();
        assertThat(subscriptionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionDetailsRepository.findAll().size();
        // set the field null
        subscriptionDetails.setCreatedBy(null);

        // Create the SubscriptionDetails, which fails.

        restSubscriptionDetailsMockMvc.perform(post("/api/subscription-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionDetails)))
            .andExpect(status().isBadRequest());

        List<SubscriptionDetails> subscriptionDetailsList = subscriptionDetailsRepository.findAll();
        assertThat(subscriptionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionDetailsRepository.findAll().size();
        // set the field null
        subscriptionDetails.setLastUpdatedDate(null);

        // Create the SubscriptionDetails, which fails.

        restSubscriptionDetailsMockMvc.perform(post("/api/subscription-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionDetails)))
            .andExpect(status().isBadRequest());

        List<SubscriptionDetails> subscriptionDetailsList = subscriptionDetailsRepository.findAll();
        assertThat(subscriptionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionDetailsRepository.findAll().size();
        // set the field null
        subscriptionDetails.setLastUpdatedBy(null);

        // Create the SubscriptionDetails, which fails.

        restSubscriptionDetailsMockMvc.perform(post("/api/subscription-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionDetails)))
            .andExpect(status().isBadRequest());

        List<SubscriptionDetails> subscriptionDetailsList = subscriptionDetailsRepository.findAll();
        assertThat(subscriptionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionDetailsRepository.findAll().size();
        // set the field null
        subscriptionDetails.setTenantId(null);

        // Create the SubscriptionDetails, which fails.

        restSubscriptionDetailsMockMvc.perform(post("/api/subscription-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionDetails)))
            .andExpect(status().isBadRequest());

        List<SubscriptionDetails> subscriptionDetailsList = subscriptionDetailsRepository.findAll();
        assertThat(subscriptionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllSubscriptionDetails() throws Exception {
        // Initialize the database
        subscriptionDetailsRepository.save(subscriptionDetails);

        // Get all the subscriptionDetailsList
        restSubscriptionDetailsMockMvc.perform(get("/api/subscription-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subscriptionDetails.getId())))
            .andExpect(jsonPath("$.[*].subsDetailId").value(hasItem(DEFAULT_SUBS_DETAIL_ID.intValue())))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID)))
            .andExpect(jsonPath("$.[*].ssaNbr").value(hasItem(DEFAULT_SSA_NBR)))
            .andExpect(jsonPath("$.[*].primaryMsisdn").value(hasItem(DEFAULT_PRIMARY_MSISDN)))
            .andExpect(jsonPath("$.[*].iccid").value(hasItem(DEFAULT_ICCID)))
            .andExpect(jsonPath("$.[*].imsi").value(hasItem(DEFAULT_IMSI)))
            .andExpect(jsonPath("$.[*].mnpRequestedDate").value(hasItem(DEFAULT_MNP_REQUESTED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lang").value(hasItem(DEFAULT_LANG.toString())))
            .andExpect(jsonPath("$.[*].baseOfferId").value(hasItem(DEFAULT_BASE_OFFER_ID)))
            .andExpect(jsonPath("$.[*].baseOfferName").value(hasItem(DEFAULT_BASE_OFFER_NAME)))
            .andExpect(jsonPath("$.[*].matrixxCatalogId").value(hasItem(DEFAULT_MATRIXX_CATALOG_ID)))
            .andExpect(jsonPath("$.[*].matrixxResourceId").value(hasItem(DEFAULT_MATRIXX_RESOURCE_ID)))
            .andExpect(jsonPath("$.[*].matrixxObjectId").value(hasItem(DEFAULT_MATRIXX_OBJECT_ID)))
            .andExpect(jsonPath("$.[*].salesChannel").value(hasItem(DEFAULT_SALES_CHANNEL)))
            .andExpect(jsonPath("$.[*].advancePaymentMonths").value(hasItem(DEFAULT_ADVANCE_PAYMENT_MONTHS)))
            .andExpect(jsonPath("$.[*].offerPrice").value(hasItem(DEFAULT_OFFER_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].networkType").value(hasItem(DEFAULT_NETWORK_TYPE)))
            .andExpect(jsonPath("$.[*].serviceType").value(hasItem(DEFAULT_SERVICE_TYPE)))
            .andExpect(jsonPath("$.[*].offerPlanCode").value(hasItem(DEFAULT_OFFER_PLAN_CODE)))
            .andExpect(jsonPath("$.[*].serviceInPerson").value(hasItem(DEFAULT_SERVICE_IN_PERSON)))
            .andExpect(jsonPath("$.[*].fcmToken").value(hasItem(DEFAULT_FCM_TOKEN)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].cdVersion").value(hasItem(DEFAULT_CD_VERSION)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getSubscriptionDetails() throws Exception {
        // Initialize the database
        subscriptionDetailsRepository.save(subscriptionDetails);

        // Get the subscriptionDetails
        restSubscriptionDetailsMockMvc.perform(get("/api/subscription-details/{id}", subscriptionDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subscriptionDetails.getId()))
            .andExpect(jsonPath("$.subsDetailId").value(DEFAULT_SUBS_DETAIL_ID.intValue()))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID))
            .andExpect(jsonPath("$.ssaNbr").value(DEFAULT_SSA_NBR))
            .andExpect(jsonPath("$.primaryMsisdn").value(DEFAULT_PRIMARY_MSISDN))
            .andExpect(jsonPath("$.iccid").value(DEFAULT_ICCID))
            .andExpect(jsonPath("$.imsi").value(DEFAULT_IMSI))
            .andExpect(jsonPath("$.mnpRequestedDate").value(DEFAULT_MNP_REQUESTED_DATE.toString()))
            .andExpect(jsonPath("$.lang").value(DEFAULT_LANG.toString()))
            .andExpect(jsonPath("$.baseOfferId").value(DEFAULT_BASE_OFFER_ID))
            .andExpect(jsonPath("$.baseOfferName").value(DEFAULT_BASE_OFFER_NAME))
            .andExpect(jsonPath("$.matrixxCatalogId").value(DEFAULT_MATRIXX_CATALOG_ID))
            .andExpect(jsonPath("$.matrixxResourceId").value(DEFAULT_MATRIXX_RESOURCE_ID))
            .andExpect(jsonPath("$.matrixxObjectId").value(DEFAULT_MATRIXX_OBJECT_ID))
            .andExpect(jsonPath("$.salesChannel").value(DEFAULT_SALES_CHANNEL))
            .andExpect(jsonPath("$.advancePaymentMonths").value(DEFAULT_ADVANCE_PAYMENT_MONTHS))
            .andExpect(jsonPath("$.offerPrice").value(DEFAULT_OFFER_PRICE.intValue()))
            .andExpect(jsonPath("$.networkType").value(DEFAULT_NETWORK_TYPE))
            .andExpect(jsonPath("$.serviceType").value(DEFAULT_SERVICE_TYPE))
            .andExpect(jsonPath("$.offerPlanCode").value(DEFAULT_OFFER_PLAN_CODE))
            .andExpect(jsonPath("$.serviceInPerson").value(DEFAULT_SERVICE_IN_PERSON))
            .andExpect(jsonPath("$.fcmToken").value(DEFAULT_FCM_TOKEN))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.cdVersion").value(DEFAULT_CD_VERSION))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingSubscriptionDetails() throws Exception {
        // Get the subscriptionDetails
        restSubscriptionDetailsMockMvc.perform(get("/api/subscription-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateSubscriptionDetails() throws Exception {
        // Initialize the database
        subscriptionDetailsService.save(subscriptionDetails);

        int databaseSizeBeforeUpdate = subscriptionDetailsRepository.findAll().size();

        // Update the subscriptionDetails
        SubscriptionDetails updatedSubscriptionDetails = subscriptionDetailsRepository.findById(subscriptionDetails.getId()).get();
        updatedSubscriptionDetails
            .subsDetailId(UPDATED_SUBS_DETAIL_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .orderId(UPDATED_ORDER_ID)
            .ssaNbr(UPDATED_SSA_NBR)
            .primaryMsisdn(UPDATED_PRIMARY_MSISDN)
            .iccid(UPDATED_ICCID)
            .imsi(UPDATED_IMSI)
            .mnpRequestedDate(UPDATED_MNP_REQUESTED_DATE)
            .lang(UPDATED_LANG)
            .baseOfferId(UPDATED_BASE_OFFER_ID)
            .baseOfferName(UPDATED_BASE_OFFER_NAME)
            .matrixxCatalogId(UPDATED_MATRIXX_CATALOG_ID)
            .matrixxResourceId(UPDATED_MATRIXX_RESOURCE_ID)
            .matrixxObjectId(UPDATED_MATRIXX_OBJECT_ID)
            .salesChannel(UPDATED_SALES_CHANNEL)
            .advancePaymentMonths(UPDATED_ADVANCE_PAYMENT_MONTHS)
            .offerPrice(UPDATED_OFFER_PRICE)
            .networkType(UPDATED_NETWORK_TYPE)
            .serviceType(UPDATED_SERVICE_TYPE)
            .offerPlanCode(UPDATED_OFFER_PLAN_CODE)
            .serviceInPerson(UPDATED_SERVICE_IN_PERSON)
            .fcmToken(UPDATED_FCM_TOKEN)
            .remarks(UPDATED_REMARKS)
            .cdVersion(UPDATED_CD_VERSION)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restSubscriptionDetailsMockMvc.perform(put("/api/subscription-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSubscriptionDetails)))
            .andExpect(status().isOk());

        // Validate the SubscriptionDetails in the database
        List<SubscriptionDetails> subscriptionDetailsList = subscriptionDetailsRepository.findAll();
        assertThat(subscriptionDetailsList).hasSize(databaseSizeBeforeUpdate);
        SubscriptionDetails testSubscriptionDetails = subscriptionDetailsList.get(subscriptionDetailsList.size() - 1);
        assertThat(testSubscriptionDetails.getSubsDetailId()).isEqualTo(UPDATED_SUBS_DETAIL_ID);
        assertThat(testSubscriptionDetails.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testSubscriptionDetails.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testSubscriptionDetails.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testSubscriptionDetails.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testSubscriptionDetails.getSsaNbr()).isEqualTo(UPDATED_SSA_NBR);
        assertThat(testSubscriptionDetails.getPrimaryMsisdn()).isEqualTo(UPDATED_PRIMARY_MSISDN);
        assertThat(testSubscriptionDetails.getIccid()).isEqualTo(UPDATED_ICCID);
        assertThat(testSubscriptionDetails.getImsi()).isEqualTo(UPDATED_IMSI);
        assertThat(testSubscriptionDetails.getMnpRequestedDate()).isEqualTo(UPDATED_MNP_REQUESTED_DATE);
        assertThat(testSubscriptionDetails.getLang()).isEqualTo(UPDATED_LANG);
        assertThat(testSubscriptionDetails.getBaseOfferId()).isEqualTo(UPDATED_BASE_OFFER_ID);
        assertThat(testSubscriptionDetails.getBaseOfferName()).isEqualTo(UPDATED_BASE_OFFER_NAME);
        assertThat(testSubscriptionDetails.getMatrixxCatalogId()).isEqualTo(UPDATED_MATRIXX_CATALOG_ID);
        assertThat(testSubscriptionDetails.getMatrixxResourceId()).isEqualTo(UPDATED_MATRIXX_RESOURCE_ID);
        assertThat(testSubscriptionDetails.getMatrixxObjectId()).isEqualTo(UPDATED_MATRIXX_OBJECT_ID);
        assertThat(testSubscriptionDetails.getSalesChannel()).isEqualTo(UPDATED_SALES_CHANNEL);
        assertThat(testSubscriptionDetails.getAdvancePaymentMonths()).isEqualTo(UPDATED_ADVANCE_PAYMENT_MONTHS);
        assertThat(testSubscriptionDetails.getOfferPrice()).isEqualTo(UPDATED_OFFER_PRICE);
        assertThat(testSubscriptionDetails.getNetworkType()).isEqualTo(UPDATED_NETWORK_TYPE);
        assertThat(testSubscriptionDetails.getServiceType()).isEqualTo(UPDATED_SERVICE_TYPE);
        assertThat(testSubscriptionDetails.getOfferPlanCode()).isEqualTo(UPDATED_OFFER_PLAN_CODE);
        assertThat(testSubscriptionDetails.getServiceInPerson()).isEqualTo(UPDATED_SERVICE_IN_PERSON);
        assertThat(testSubscriptionDetails.getFcmToken()).isEqualTo(UPDATED_FCM_TOKEN);
        assertThat(testSubscriptionDetails.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testSubscriptionDetails.getCdVersion()).isEqualTo(UPDATED_CD_VERSION);
        assertThat(testSubscriptionDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSubscriptionDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSubscriptionDetails.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testSubscriptionDetails.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testSubscriptionDetails.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingSubscriptionDetails() throws Exception {
        int databaseSizeBeforeUpdate = subscriptionDetailsRepository.findAll().size();

        // Create the SubscriptionDetails

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubscriptionDetailsMockMvc.perform(put("/api/subscription-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionDetails)))
            .andExpect(status().isBadRequest());

        // Validate the SubscriptionDetails in the database
        List<SubscriptionDetails> subscriptionDetailsList = subscriptionDetailsRepository.findAll();
        assertThat(subscriptionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteSubscriptionDetails() throws Exception {
        // Initialize the database
        subscriptionDetailsService.save(subscriptionDetails);

        int databaseSizeBeforeDelete = subscriptionDetailsRepository.findAll().size();

        // Delete the subscriptionDetails
        restSubscriptionDetailsMockMvc.perform(delete("/api/subscription-details/{id}", subscriptionDetails.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SubscriptionDetails> subscriptionDetailsList = subscriptionDetailsRepository.findAll();
        assertThat(subscriptionDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
