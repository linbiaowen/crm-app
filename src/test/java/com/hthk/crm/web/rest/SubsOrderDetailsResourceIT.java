package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.SubsOrderDetails;
import com.hthk.crm.repository.SubsOrderDetailsRepository;
import com.hthk.crm.service.SubsOrderDetailsService;
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
import com.hthk.crm.domain.enumeration.ServiceType;
/**
 * Integration tests for the {@link SubsOrderDetailsResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class SubsOrderDetailsResourceIT {

    private static final Long DEFAULT_SUBS_ORDER_SEQ_ID = 1L;
    private static final Long UPDATED_SUBS_ORDER_SEQ_ID = 2L;

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

    private static final Boolean DEFAULT_SIM_VERIFIED = false;
    private static final Boolean UPDATED_SIM_VERIFIED = true;

    private static final Instant DEFAULT_SIM_VERIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SIM_VERIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_BILLING_ACCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ACCT_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_BILL_CYCLE_ID = 1;
    private static final Integer UPDATED_BILL_CYCLE_ID = 2;

    private static final Instant DEFAULT_MNP_REQUESTED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MNP_REQUESTED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_MNP_TICKET = "AAAAAAAAAA";
    private static final String UPDATED_MNP_TICKET = "BBBBBBBBBB";

    private static final String DEFAULT_MNP_PORT_IN_SESSION = "AAAAAAAAAA";
    private static final String UPDATED_MNP_PORT_IN_SESSION = "BBBBBBBBBB";

    private static final String DEFAULT_MNP_ORIGINAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_MNP_ORIGINAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MNP_CUST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MNP_CUST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MNP_ID_NBR = "AAAAAAAAAA";
    private static final String UPDATED_MNP_ID_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_MNP_ID_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_MNP_ID_TYPE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_HTHK_MSISDN = false;
    private static final Boolean UPDATED_HTHK_MSISDN = true;

    private static final Language DEFAULT_LANG = Language.CHINESE;
    private static final Language UPDATED_LANG = Language.ENGLISH;

    private static final String DEFAULT_OFFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_NAME = "BBBBBBBBBB";

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

    private static final ServiceType DEFAULT_SERVICETYPE = ServiceType.PREPAID;
    private static final ServiceType UPDATED_SERVICETYPE = ServiceType.POSTPAID;

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
    private SubsOrderDetailsRepository subsOrderDetailsRepository;

    @Autowired
    private SubsOrderDetailsService subsOrderDetailsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restSubsOrderDetailsMockMvc;

    private SubsOrderDetails subsOrderDetails;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SubsOrderDetailsResource subsOrderDetailsResource = new SubsOrderDetailsResource(subsOrderDetailsService);
        this.restSubsOrderDetailsMockMvc = MockMvcBuilders.standaloneSetup(subsOrderDetailsResource)
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
    public static SubsOrderDetails createEntity() {
        SubsOrderDetails subsOrderDetails = new SubsOrderDetails()
            .subsOrderSeqId(DEFAULT_SUBS_ORDER_SEQ_ID)
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .orderId(DEFAULT_ORDER_ID)
            .ssaNbr(DEFAULT_SSA_NBR)
            .primaryMsisdn(DEFAULT_PRIMARY_MSISDN)
            .iccid(DEFAULT_ICCID)
            .imsi(DEFAULT_IMSI)
            .simVerified(DEFAULT_SIM_VERIFIED)
            .simVerifiedDate(DEFAULT_SIM_VERIFIED_DATE)
            .billingAcctId(DEFAULT_BILLING_ACCT_ID)
            .billCycleId(DEFAULT_BILL_CYCLE_ID)
            .mnpRequestedDate(DEFAULT_MNP_REQUESTED_DATE)
            .mnpTicket(DEFAULT_MNP_TICKET)
            .mnpPortInSession(DEFAULT_MNP_PORT_IN_SESSION)
            .mnpOriginalId(DEFAULT_MNP_ORIGINAL_ID)
            .mnpCustName(DEFAULT_MNP_CUST_NAME)
            .mnpIdNbr(DEFAULT_MNP_ID_NBR)
            .mnpIdType(DEFAULT_MNP_ID_TYPE)
            .hthkMsisdn(DEFAULT_HTHK_MSISDN)
            .lang(DEFAULT_LANG)
            .offerId(DEFAULT_OFFER_ID)
            .offerName(DEFAULT_OFFER_NAME)
            .matrixxCatalogId(DEFAULT_MATRIXX_CATALOG_ID)
            .matrixxResourceId(DEFAULT_MATRIXX_RESOURCE_ID)
            .matrixxObjectId(DEFAULT_MATRIXX_OBJECT_ID)
            .salesChannel(DEFAULT_SALES_CHANNEL)
            .advancePaymentMonths(DEFAULT_ADVANCE_PAYMENT_MONTHS)
            .offerPrice(DEFAULT_OFFER_PRICE)
            .networkType(DEFAULT_NETWORK_TYPE)
            .servicetype(DEFAULT_SERVICETYPE)
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
        return subsOrderDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubsOrderDetails createUpdatedEntity() {
        SubsOrderDetails subsOrderDetails = new SubsOrderDetails()
            .subsOrderSeqId(UPDATED_SUBS_ORDER_SEQ_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .orderId(UPDATED_ORDER_ID)
            .ssaNbr(UPDATED_SSA_NBR)
            .primaryMsisdn(UPDATED_PRIMARY_MSISDN)
            .iccid(UPDATED_ICCID)
            .imsi(UPDATED_IMSI)
            .simVerified(UPDATED_SIM_VERIFIED)
            .simVerifiedDate(UPDATED_SIM_VERIFIED_DATE)
            .billingAcctId(UPDATED_BILLING_ACCT_ID)
            .billCycleId(UPDATED_BILL_CYCLE_ID)
            .mnpRequestedDate(UPDATED_MNP_REQUESTED_DATE)
            .mnpTicket(UPDATED_MNP_TICKET)
            .mnpPortInSession(UPDATED_MNP_PORT_IN_SESSION)
            .mnpOriginalId(UPDATED_MNP_ORIGINAL_ID)
            .mnpCustName(UPDATED_MNP_CUST_NAME)
            .mnpIdNbr(UPDATED_MNP_ID_NBR)
            .mnpIdType(UPDATED_MNP_ID_TYPE)
            .hthkMsisdn(UPDATED_HTHK_MSISDN)
            .lang(UPDATED_LANG)
            .offerId(UPDATED_OFFER_ID)
            .offerName(UPDATED_OFFER_NAME)
            .matrixxCatalogId(UPDATED_MATRIXX_CATALOG_ID)
            .matrixxResourceId(UPDATED_MATRIXX_RESOURCE_ID)
            .matrixxObjectId(UPDATED_MATRIXX_OBJECT_ID)
            .salesChannel(UPDATED_SALES_CHANNEL)
            .advancePaymentMonths(UPDATED_ADVANCE_PAYMENT_MONTHS)
            .offerPrice(UPDATED_OFFER_PRICE)
            .networkType(UPDATED_NETWORK_TYPE)
            .servicetype(UPDATED_SERVICETYPE)
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
        return subsOrderDetails;
    }

    @BeforeEach
    public void initTest() {
        subsOrderDetailsRepository.deleteAll();
        subsOrderDetails = createEntity();
    }

    @Test
    public void createSubsOrderDetails() throws Exception {
        int databaseSizeBeforeCreate = subsOrderDetailsRepository.findAll().size();

        // Create the SubsOrderDetails
        restSubsOrderDetailsMockMvc.perform(post("/api/subs-order-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsOrderDetails)))
            .andExpect(status().isCreated());

        // Validate the SubsOrderDetails in the database
        List<SubsOrderDetails> subsOrderDetailsList = subsOrderDetailsRepository.findAll();
        assertThat(subsOrderDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        SubsOrderDetails testSubsOrderDetails = subsOrderDetailsList.get(subsOrderDetailsList.size() - 1);
        assertThat(testSubsOrderDetails.getSubsOrderSeqId()).isEqualTo(DEFAULT_SUBS_ORDER_SEQ_ID);
        assertThat(testSubsOrderDetails.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testSubsOrderDetails.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testSubsOrderDetails.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testSubsOrderDetails.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testSubsOrderDetails.getSsaNbr()).isEqualTo(DEFAULT_SSA_NBR);
        assertThat(testSubsOrderDetails.getPrimaryMsisdn()).isEqualTo(DEFAULT_PRIMARY_MSISDN);
        assertThat(testSubsOrderDetails.getIccid()).isEqualTo(DEFAULT_ICCID);
        assertThat(testSubsOrderDetails.getImsi()).isEqualTo(DEFAULT_IMSI);
        assertThat(testSubsOrderDetails.isSimVerified()).isEqualTo(DEFAULT_SIM_VERIFIED);
        assertThat(testSubsOrderDetails.getSimVerifiedDate()).isEqualTo(DEFAULT_SIM_VERIFIED_DATE);
        assertThat(testSubsOrderDetails.getBillingAcctId()).isEqualTo(DEFAULT_BILLING_ACCT_ID);
        assertThat(testSubsOrderDetails.getBillCycleId()).isEqualTo(DEFAULT_BILL_CYCLE_ID);
        assertThat(testSubsOrderDetails.getMnpRequestedDate()).isEqualTo(DEFAULT_MNP_REQUESTED_DATE);
        assertThat(testSubsOrderDetails.getMnpTicket()).isEqualTo(DEFAULT_MNP_TICKET);
        assertThat(testSubsOrderDetails.getMnpPortInSession()).isEqualTo(DEFAULT_MNP_PORT_IN_SESSION);
        assertThat(testSubsOrderDetails.getMnpOriginalId()).isEqualTo(DEFAULT_MNP_ORIGINAL_ID);
        assertThat(testSubsOrderDetails.getMnpCustName()).isEqualTo(DEFAULT_MNP_CUST_NAME);
        assertThat(testSubsOrderDetails.getMnpIdNbr()).isEqualTo(DEFAULT_MNP_ID_NBR);
        assertThat(testSubsOrderDetails.getMnpIdType()).isEqualTo(DEFAULT_MNP_ID_TYPE);
        assertThat(testSubsOrderDetails.isHthkMsisdn()).isEqualTo(DEFAULT_HTHK_MSISDN);
        assertThat(testSubsOrderDetails.getLang()).isEqualTo(DEFAULT_LANG);
        assertThat(testSubsOrderDetails.getOfferId()).isEqualTo(DEFAULT_OFFER_ID);
        assertThat(testSubsOrderDetails.getOfferName()).isEqualTo(DEFAULT_OFFER_NAME);
        assertThat(testSubsOrderDetails.getMatrixxCatalogId()).isEqualTo(DEFAULT_MATRIXX_CATALOG_ID);
        assertThat(testSubsOrderDetails.getMatrixxResourceId()).isEqualTo(DEFAULT_MATRIXX_RESOURCE_ID);
        assertThat(testSubsOrderDetails.getMatrixxObjectId()).isEqualTo(DEFAULT_MATRIXX_OBJECT_ID);
        assertThat(testSubsOrderDetails.getSalesChannel()).isEqualTo(DEFAULT_SALES_CHANNEL);
        assertThat(testSubsOrderDetails.getAdvancePaymentMonths()).isEqualTo(DEFAULT_ADVANCE_PAYMENT_MONTHS);
        assertThat(testSubsOrderDetails.getOfferPrice()).isEqualTo(DEFAULT_OFFER_PRICE);
        assertThat(testSubsOrderDetails.getNetworkType()).isEqualTo(DEFAULT_NETWORK_TYPE);
        assertThat(testSubsOrderDetails.getServicetype()).isEqualTo(DEFAULT_SERVICETYPE);
        assertThat(testSubsOrderDetails.getOfferPlanCode()).isEqualTo(DEFAULT_OFFER_PLAN_CODE);
        assertThat(testSubsOrderDetails.getServiceInPerson()).isEqualTo(DEFAULT_SERVICE_IN_PERSON);
        assertThat(testSubsOrderDetails.getFcmToken()).isEqualTo(DEFAULT_FCM_TOKEN);
        assertThat(testSubsOrderDetails.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testSubsOrderDetails.getCdVersion()).isEqualTo(DEFAULT_CD_VERSION);
        assertThat(testSubsOrderDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSubsOrderDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSubsOrderDetails.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testSubsOrderDetails.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testSubsOrderDetails.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createSubsOrderDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subsOrderDetailsRepository.findAll().size();

        // Create the SubsOrderDetails with an existing ID
        subsOrderDetails.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubsOrderDetailsMockMvc.perform(post("/api/subs-order-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsOrderDetails)))
            .andExpect(status().isBadRequest());

        // Validate the SubsOrderDetails in the database
        List<SubsOrderDetails> subsOrderDetailsList = subsOrderDetailsRepository.findAll();
        assertThat(subsOrderDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkSubsOrderSeqIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsOrderDetailsRepository.findAll().size();
        // set the field null
        subsOrderDetails.setSubsOrderSeqId(null);

        // Create the SubsOrderDetails, which fails.

        restSubsOrderDetailsMockMvc.perform(post("/api/subs-order-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsOrderDetails)))
            .andExpect(status().isBadRequest());

        List<SubsOrderDetails> subsOrderDetailsList = subsOrderDetailsRepository.findAll();
        assertThat(subsOrderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSubscriptionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsOrderDetailsRepository.findAll().size();
        // set the field null
        subsOrderDetails.setSubscriptionId(null);

        // Create the SubsOrderDetails, which fails.

        restSubsOrderDetailsMockMvc.perform(post("/api/subs-order-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsOrderDetails)))
            .andExpect(status().isBadRequest());

        List<SubsOrderDetails> subsOrderDetailsList = subsOrderDetailsRepository.findAll();
        assertThat(subsOrderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOrderIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsOrderDetailsRepository.findAll().size();
        // set the field null
        subsOrderDetails.setOrderId(null);

        // Create the SubsOrderDetails, which fails.

        restSubsOrderDetailsMockMvc.perform(post("/api/subs-order-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsOrderDetails)))
            .andExpect(status().isBadRequest());

        List<SubsOrderDetails> subsOrderDetailsList = subsOrderDetailsRepository.findAll();
        assertThat(subsOrderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLangIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsOrderDetailsRepository.findAll().size();
        // set the field null
        subsOrderDetails.setLang(null);

        // Create the SubsOrderDetails, which fails.

        restSubsOrderDetailsMockMvc.perform(post("/api/subs-order-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsOrderDetails)))
            .andExpect(status().isBadRequest());

        List<SubsOrderDetails> subsOrderDetailsList = subsOrderDetailsRepository.findAll();
        assertThat(subsOrderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsOrderDetailsRepository.findAll().size();
        // set the field null
        subsOrderDetails.setCreatedDate(null);

        // Create the SubsOrderDetails, which fails.

        restSubsOrderDetailsMockMvc.perform(post("/api/subs-order-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsOrderDetails)))
            .andExpect(status().isBadRequest());

        List<SubsOrderDetails> subsOrderDetailsList = subsOrderDetailsRepository.findAll();
        assertThat(subsOrderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsOrderDetailsRepository.findAll().size();
        // set the field null
        subsOrderDetails.setCreatedBy(null);

        // Create the SubsOrderDetails, which fails.

        restSubsOrderDetailsMockMvc.perform(post("/api/subs-order-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsOrderDetails)))
            .andExpect(status().isBadRequest());

        List<SubsOrderDetails> subsOrderDetailsList = subsOrderDetailsRepository.findAll();
        assertThat(subsOrderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsOrderDetailsRepository.findAll().size();
        // set the field null
        subsOrderDetails.setLastUpdatedDate(null);

        // Create the SubsOrderDetails, which fails.

        restSubsOrderDetailsMockMvc.perform(post("/api/subs-order-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsOrderDetails)))
            .andExpect(status().isBadRequest());

        List<SubsOrderDetails> subsOrderDetailsList = subsOrderDetailsRepository.findAll();
        assertThat(subsOrderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsOrderDetailsRepository.findAll().size();
        // set the field null
        subsOrderDetails.setLastUpdatedBy(null);

        // Create the SubsOrderDetails, which fails.

        restSubsOrderDetailsMockMvc.perform(post("/api/subs-order-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsOrderDetails)))
            .andExpect(status().isBadRequest());

        List<SubsOrderDetails> subsOrderDetailsList = subsOrderDetailsRepository.findAll();
        assertThat(subsOrderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsOrderDetailsRepository.findAll().size();
        // set the field null
        subsOrderDetails.setTenantId(null);

        // Create the SubsOrderDetails, which fails.

        restSubsOrderDetailsMockMvc.perform(post("/api/subs-order-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsOrderDetails)))
            .andExpect(status().isBadRequest());

        List<SubsOrderDetails> subsOrderDetailsList = subsOrderDetailsRepository.findAll();
        assertThat(subsOrderDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllSubsOrderDetails() throws Exception {
        // Initialize the database
        subsOrderDetailsRepository.save(subsOrderDetails);

        // Get all the subsOrderDetailsList
        restSubsOrderDetailsMockMvc.perform(get("/api/subs-order-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subsOrderDetails.getId())))
            .andExpect(jsonPath("$.[*].subsOrderSeqId").value(hasItem(DEFAULT_SUBS_ORDER_SEQ_ID.intValue())))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID)))
            .andExpect(jsonPath("$.[*].ssaNbr").value(hasItem(DEFAULT_SSA_NBR)))
            .andExpect(jsonPath("$.[*].primaryMsisdn").value(hasItem(DEFAULT_PRIMARY_MSISDN)))
            .andExpect(jsonPath("$.[*].iccid").value(hasItem(DEFAULT_ICCID)))
            .andExpect(jsonPath("$.[*].imsi").value(hasItem(DEFAULT_IMSI)))
            .andExpect(jsonPath("$.[*].simVerified").value(hasItem(DEFAULT_SIM_VERIFIED.booleanValue())))
            .andExpect(jsonPath("$.[*].simVerifiedDate").value(hasItem(DEFAULT_SIM_VERIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].billingAcctId").value(hasItem(DEFAULT_BILLING_ACCT_ID)))
            .andExpect(jsonPath("$.[*].billCycleId").value(hasItem(DEFAULT_BILL_CYCLE_ID)))
            .andExpect(jsonPath("$.[*].mnpRequestedDate").value(hasItem(DEFAULT_MNP_REQUESTED_DATE.toString())))
            .andExpect(jsonPath("$.[*].mnpTicket").value(hasItem(DEFAULT_MNP_TICKET)))
            .andExpect(jsonPath("$.[*].mnpPortInSession").value(hasItem(DEFAULT_MNP_PORT_IN_SESSION)))
            .andExpect(jsonPath("$.[*].mnpOriginalId").value(hasItem(DEFAULT_MNP_ORIGINAL_ID)))
            .andExpect(jsonPath("$.[*].mnpCustName").value(hasItem(DEFAULT_MNP_CUST_NAME)))
            .andExpect(jsonPath("$.[*].mnpIdNbr").value(hasItem(DEFAULT_MNP_ID_NBR)))
            .andExpect(jsonPath("$.[*].mnpIdType").value(hasItem(DEFAULT_MNP_ID_TYPE)))
            .andExpect(jsonPath("$.[*].hthkMsisdn").value(hasItem(DEFAULT_HTHK_MSISDN.booleanValue())))
            .andExpect(jsonPath("$.[*].lang").value(hasItem(DEFAULT_LANG.toString())))
            .andExpect(jsonPath("$.[*].offerId").value(hasItem(DEFAULT_OFFER_ID)))
            .andExpect(jsonPath("$.[*].offerName").value(hasItem(DEFAULT_OFFER_NAME)))
            .andExpect(jsonPath("$.[*].matrixxCatalogId").value(hasItem(DEFAULT_MATRIXX_CATALOG_ID)))
            .andExpect(jsonPath("$.[*].matrixxResourceId").value(hasItem(DEFAULT_MATRIXX_RESOURCE_ID)))
            .andExpect(jsonPath("$.[*].matrixxObjectId").value(hasItem(DEFAULT_MATRIXX_OBJECT_ID)))
            .andExpect(jsonPath("$.[*].salesChannel").value(hasItem(DEFAULT_SALES_CHANNEL)))
            .andExpect(jsonPath("$.[*].advancePaymentMonths").value(hasItem(DEFAULT_ADVANCE_PAYMENT_MONTHS)))
            .andExpect(jsonPath("$.[*].offerPrice").value(hasItem(DEFAULT_OFFER_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].networkType").value(hasItem(DEFAULT_NETWORK_TYPE)))
            .andExpect(jsonPath("$.[*].servicetype").value(hasItem(DEFAULT_SERVICETYPE.toString())))
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
    public void getSubsOrderDetails() throws Exception {
        // Initialize the database
        subsOrderDetailsRepository.save(subsOrderDetails);

        // Get the subsOrderDetails
        restSubsOrderDetailsMockMvc.perform(get("/api/subs-order-details/{id}", subsOrderDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subsOrderDetails.getId()))
            .andExpect(jsonPath("$.subsOrderSeqId").value(DEFAULT_SUBS_ORDER_SEQ_ID.intValue()))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID))
            .andExpect(jsonPath("$.ssaNbr").value(DEFAULT_SSA_NBR))
            .andExpect(jsonPath("$.primaryMsisdn").value(DEFAULT_PRIMARY_MSISDN))
            .andExpect(jsonPath("$.iccid").value(DEFAULT_ICCID))
            .andExpect(jsonPath("$.imsi").value(DEFAULT_IMSI))
            .andExpect(jsonPath("$.simVerified").value(DEFAULT_SIM_VERIFIED.booleanValue()))
            .andExpect(jsonPath("$.simVerifiedDate").value(DEFAULT_SIM_VERIFIED_DATE.toString()))
            .andExpect(jsonPath("$.billingAcctId").value(DEFAULT_BILLING_ACCT_ID))
            .andExpect(jsonPath("$.billCycleId").value(DEFAULT_BILL_CYCLE_ID))
            .andExpect(jsonPath("$.mnpRequestedDate").value(DEFAULT_MNP_REQUESTED_DATE.toString()))
            .andExpect(jsonPath("$.mnpTicket").value(DEFAULT_MNP_TICKET))
            .andExpect(jsonPath("$.mnpPortInSession").value(DEFAULT_MNP_PORT_IN_SESSION))
            .andExpect(jsonPath("$.mnpOriginalId").value(DEFAULT_MNP_ORIGINAL_ID))
            .andExpect(jsonPath("$.mnpCustName").value(DEFAULT_MNP_CUST_NAME))
            .andExpect(jsonPath("$.mnpIdNbr").value(DEFAULT_MNP_ID_NBR))
            .andExpect(jsonPath("$.mnpIdType").value(DEFAULT_MNP_ID_TYPE))
            .andExpect(jsonPath("$.hthkMsisdn").value(DEFAULT_HTHK_MSISDN.booleanValue()))
            .andExpect(jsonPath("$.lang").value(DEFAULT_LANG.toString()))
            .andExpect(jsonPath("$.offerId").value(DEFAULT_OFFER_ID))
            .andExpect(jsonPath("$.offerName").value(DEFAULT_OFFER_NAME))
            .andExpect(jsonPath("$.matrixxCatalogId").value(DEFAULT_MATRIXX_CATALOG_ID))
            .andExpect(jsonPath("$.matrixxResourceId").value(DEFAULT_MATRIXX_RESOURCE_ID))
            .andExpect(jsonPath("$.matrixxObjectId").value(DEFAULT_MATRIXX_OBJECT_ID))
            .andExpect(jsonPath("$.salesChannel").value(DEFAULT_SALES_CHANNEL))
            .andExpect(jsonPath("$.advancePaymentMonths").value(DEFAULT_ADVANCE_PAYMENT_MONTHS))
            .andExpect(jsonPath("$.offerPrice").value(DEFAULT_OFFER_PRICE.intValue()))
            .andExpect(jsonPath("$.networkType").value(DEFAULT_NETWORK_TYPE))
            .andExpect(jsonPath("$.servicetype").value(DEFAULT_SERVICETYPE.toString()))
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
    public void getNonExistingSubsOrderDetails() throws Exception {
        // Get the subsOrderDetails
        restSubsOrderDetailsMockMvc.perform(get("/api/subs-order-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateSubsOrderDetails() throws Exception {
        // Initialize the database
        subsOrderDetailsService.save(subsOrderDetails);

        int databaseSizeBeforeUpdate = subsOrderDetailsRepository.findAll().size();

        // Update the subsOrderDetails
        SubsOrderDetails updatedSubsOrderDetails = subsOrderDetailsRepository.findById(subsOrderDetails.getId()).get();
        updatedSubsOrderDetails
            .subsOrderSeqId(UPDATED_SUBS_ORDER_SEQ_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .orderId(UPDATED_ORDER_ID)
            .ssaNbr(UPDATED_SSA_NBR)
            .primaryMsisdn(UPDATED_PRIMARY_MSISDN)
            .iccid(UPDATED_ICCID)
            .imsi(UPDATED_IMSI)
            .simVerified(UPDATED_SIM_VERIFIED)
            .simVerifiedDate(UPDATED_SIM_VERIFIED_DATE)
            .billingAcctId(UPDATED_BILLING_ACCT_ID)
            .billCycleId(UPDATED_BILL_CYCLE_ID)
            .mnpRequestedDate(UPDATED_MNP_REQUESTED_DATE)
            .mnpTicket(UPDATED_MNP_TICKET)
            .mnpPortInSession(UPDATED_MNP_PORT_IN_SESSION)
            .mnpOriginalId(UPDATED_MNP_ORIGINAL_ID)
            .mnpCustName(UPDATED_MNP_CUST_NAME)
            .mnpIdNbr(UPDATED_MNP_ID_NBR)
            .mnpIdType(UPDATED_MNP_ID_TYPE)
            .hthkMsisdn(UPDATED_HTHK_MSISDN)
            .lang(UPDATED_LANG)
            .offerId(UPDATED_OFFER_ID)
            .offerName(UPDATED_OFFER_NAME)
            .matrixxCatalogId(UPDATED_MATRIXX_CATALOG_ID)
            .matrixxResourceId(UPDATED_MATRIXX_RESOURCE_ID)
            .matrixxObjectId(UPDATED_MATRIXX_OBJECT_ID)
            .salesChannel(UPDATED_SALES_CHANNEL)
            .advancePaymentMonths(UPDATED_ADVANCE_PAYMENT_MONTHS)
            .offerPrice(UPDATED_OFFER_PRICE)
            .networkType(UPDATED_NETWORK_TYPE)
            .servicetype(UPDATED_SERVICETYPE)
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

        restSubsOrderDetailsMockMvc.perform(put("/api/subs-order-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSubsOrderDetails)))
            .andExpect(status().isOk());

        // Validate the SubsOrderDetails in the database
        List<SubsOrderDetails> subsOrderDetailsList = subsOrderDetailsRepository.findAll();
        assertThat(subsOrderDetailsList).hasSize(databaseSizeBeforeUpdate);
        SubsOrderDetails testSubsOrderDetails = subsOrderDetailsList.get(subsOrderDetailsList.size() - 1);
        assertThat(testSubsOrderDetails.getSubsOrderSeqId()).isEqualTo(UPDATED_SUBS_ORDER_SEQ_ID);
        assertThat(testSubsOrderDetails.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testSubsOrderDetails.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testSubsOrderDetails.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testSubsOrderDetails.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testSubsOrderDetails.getSsaNbr()).isEqualTo(UPDATED_SSA_NBR);
        assertThat(testSubsOrderDetails.getPrimaryMsisdn()).isEqualTo(UPDATED_PRIMARY_MSISDN);
        assertThat(testSubsOrderDetails.getIccid()).isEqualTo(UPDATED_ICCID);
        assertThat(testSubsOrderDetails.getImsi()).isEqualTo(UPDATED_IMSI);
        assertThat(testSubsOrderDetails.isSimVerified()).isEqualTo(UPDATED_SIM_VERIFIED);
        assertThat(testSubsOrderDetails.getSimVerifiedDate()).isEqualTo(UPDATED_SIM_VERIFIED_DATE);
        assertThat(testSubsOrderDetails.getBillingAcctId()).isEqualTo(UPDATED_BILLING_ACCT_ID);
        assertThat(testSubsOrderDetails.getBillCycleId()).isEqualTo(UPDATED_BILL_CYCLE_ID);
        assertThat(testSubsOrderDetails.getMnpRequestedDate()).isEqualTo(UPDATED_MNP_REQUESTED_DATE);
        assertThat(testSubsOrderDetails.getMnpTicket()).isEqualTo(UPDATED_MNP_TICKET);
        assertThat(testSubsOrderDetails.getMnpPortInSession()).isEqualTo(UPDATED_MNP_PORT_IN_SESSION);
        assertThat(testSubsOrderDetails.getMnpOriginalId()).isEqualTo(UPDATED_MNP_ORIGINAL_ID);
        assertThat(testSubsOrderDetails.getMnpCustName()).isEqualTo(UPDATED_MNP_CUST_NAME);
        assertThat(testSubsOrderDetails.getMnpIdNbr()).isEqualTo(UPDATED_MNP_ID_NBR);
        assertThat(testSubsOrderDetails.getMnpIdType()).isEqualTo(UPDATED_MNP_ID_TYPE);
        assertThat(testSubsOrderDetails.isHthkMsisdn()).isEqualTo(UPDATED_HTHK_MSISDN);
        assertThat(testSubsOrderDetails.getLang()).isEqualTo(UPDATED_LANG);
        assertThat(testSubsOrderDetails.getOfferId()).isEqualTo(UPDATED_OFFER_ID);
        assertThat(testSubsOrderDetails.getOfferName()).isEqualTo(UPDATED_OFFER_NAME);
        assertThat(testSubsOrderDetails.getMatrixxCatalogId()).isEqualTo(UPDATED_MATRIXX_CATALOG_ID);
        assertThat(testSubsOrderDetails.getMatrixxResourceId()).isEqualTo(UPDATED_MATRIXX_RESOURCE_ID);
        assertThat(testSubsOrderDetails.getMatrixxObjectId()).isEqualTo(UPDATED_MATRIXX_OBJECT_ID);
        assertThat(testSubsOrderDetails.getSalesChannel()).isEqualTo(UPDATED_SALES_CHANNEL);
        assertThat(testSubsOrderDetails.getAdvancePaymentMonths()).isEqualTo(UPDATED_ADVANCE_PAYMENT_MONTHS);
        assertThat(testSubsOrderDetails.getOfferPrice()).isEqualTo(UPDATED_OFFER_PRICE);
        assertThat(testSubsOrderDetails.getNetworkType()).isEqualTo(UPDATED_NETWORK_TYPE);
        assertThat(testSubsOrderDetails.getServicetype()).isEqualTo(UPDATED_SERVICETYPE);
        assertThat(testSubsOrderDetails.getOfferPlanCode()).isEqualTo(UPDATED_OFFER_PLAN_CODE);
        assertThat(testSubsOrderDetails.getServiceInPerson()).isEqualTo(UPDATED_SERVICE_IN_PERSON);
        assertThat(testSubsOrderDetails.getFcmToken()).isEqualTo(UPDATED_FCM_TOKEN);
        assertThat(testSubsOrderDetails.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testSubsOrderDetails.getCdVersion()).isEqualTo(UPDATED_CD_VERSION);
        assertThat(testSubsOrderDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSubsOrderDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSubsOrderDetails.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testSubsOrderDetails.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testSubsOrderDetails.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingSubsOrderDetails() throws Exception {
        int databaseSizeBeforeUpdate = subsOrderDetailsRepository.findAll().size();

        // Create the SubsOrderDetails

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubsOrderDetailsMockMvc.perform(put("/api/subs-order-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsOrderDetails)))
            .andExpect(status().isBadRequest());

        // Validate the SubsOrderDetails in the database
        List<SubsOrderDetails> subsOrderDetailsList = subsOrderDetailsRepository.findAll();
        assertThat(subsOrderDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteSubsOrderDetails() throws Exception {
        // Initialize the database
        subsOrderDetailsService.save(subsOrderDetails);

        int databaseSizeBeforeDelete = subsOrderDetailsRepository.findAll().size();

        // Delete the subsOrderDetails
        restSubsOrderDetailsMockMvc.perform(delete("/api/subs-order-details/{id}", subsOrderDetails.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SubsOrderDetails> subsOrderDetailsList = subsOrderDetailsRepository.findAll();
        assertThat(subsOrderDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
