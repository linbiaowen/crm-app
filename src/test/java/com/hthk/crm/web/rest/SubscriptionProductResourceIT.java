package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.SubscriptionProduct;
import com.hthk.crm.repository.SubscriptionProductRepository;
import com.hthk.crm.service.SubscriptionProductService;
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
 * Integration tests for the {@link SubscriptionProductResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class SubscriptionProductResourceIT {

    private static final Long DEFAULT_PRODUCT_SUBSRIPTION_SEQ_ID = 1L;
    private static final Long UPDATED_PRODUCT_SUBSRIPTION_SEQ_ID = 2L;

    private static final String DEFAULT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIPTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_ACTIVATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ACTIVATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SECOND_MSISDN = "AAAAAAAAAA";
    private static final String UPDATED_SECOND_MSISDN = "BBBBBBBBBB";

    private static final String DEFAULT_SECOND_IMSI = "AAAAAAAAAA";
    private static final String UPDATED_SECOND_IMSI = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final String DEFAULT_TERMINATION_REASON_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TERMINATION_REASON_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_MATRIXX_CATALOG_ID = "AAAAAAAAAA";
    private static final String UPDATED_MATRIXX_CATALOG_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MATRIXX_RESOURCE_ID = "AAAAAAAAAA";
    private static final String UPDATED_MATRIXX_RESOURCE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MATRIXX_OBJECT_ID = "AAAAAAAAAA";
    private static final String UPDATED_MATRIXX_OBJECT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_SALES_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACT_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_AUTO_RENEWAL = false;
    private static final Boolean UPDATED_AUTO_RENEWAL = true;

    private static final Boolean DEFAULT_AUTO_PAY = false;
    private static final Boolean UPDATED_AUTO_PAY = true;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_VENDOR_PROVISION_IND = false;
    private static final Boolean UPDATED_VENDOR_PROVISION_IND = true;

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
    private SubscriptionProductRepository subscriptionProductRepository;

    @Autowired
    private SubscriptionProductService subscriptionProductService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restSubscriptionProductMockMvc;

    private SubscriptionProduct subscriptionProduct;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SubscriptionProductResource subscriptionProductResource = new SubscriptionProductResource(subscriptionProductService);
        this.restSubscriptionProductMockMvc = MockMvcBuilders.standaloneSetup(subscriptionProductResource)
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
    public static SubscriptionProduct createEntity() {
        SubscriptionProduct subscriptionProduct = new SubscriptionProduct()
            .productSubsriptionSeqId(DEFAULT_PRODUCT_SUBSRIPTION_SEQ_ID)
            .orderId(DEFAULT_ORDER_ID)
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .productId(DEFAULT_PRODUCT_ID)
            .productName(DEFAULT_PRODUCT_NAME)
            .activationDate(DEFAULT_ACTIVATION_DATE)
            .endDate(DEFAULT_END_DATE)
            .secondMsisdn(DEFAULT_SECOND_MSISDN)
            .secondImsi(DEFAULT_SECOND_IMSI)
            .quantity(DEFAULT_QUANTITY)
            .terminationReasonCode(DEFAULT_TERMINATION_REASON_CODE)
            .offerId(DEFAULT_OFFER_ID)
            .offerName(DEFAULT_OFFER_NAME)
            .offerType(DEFAULT_OFFER_TYPE)
            .matrixxCatalogId(DEFAULT_MATRIXX_CATALOG_ID)
            .matrixxResourceId(DEFAULT_MATRIXX_RESOURCE_ID)
            .matrixxObjectId(DEFAULT_MATRIXX_OBJECT_ID)
            .salesChannel(DEFAULT_SALES_CHANNEL)
            .contractId(DEFAULT_CONTRACT_ID)
            .autoRenewal(DEFAULT_AUTO_RENEWAL)
            .autoPay(DEFAULT_AUTO_PAY)
            .remarks(DEFAULT_REMARKS)
            .vendorProvisionInd(DEFAULT_VENDOR_PROVISION_IND)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return subscriptionProduct;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubscriptionProduct createUpdatedEntity() {
        SubscriptionProduct subscriptionProduct = new SubscriptionProduct()
            .productSubsriptionSeqId(UPDATED_PRODUCT_SUBSRIPTION_SEQ_ID)
            .orderId(UPDATED_ORDER_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .productId(UPDATED_PRODUCT_ID)
            .productName(UPDATED_PRODUCT_NAME)
            .activationDate(UPDATED_ACTIVATION_DATE)
            .endDate(UPDATED_END_DATE)
            .secondMsisdn(UPDATED_SECOND_MSISDN)
            .secondImsi(UPDATED_SECOND_IMSI)
            .quantity(UPDATED_QUANTITY)
            .terminationReasonCode(UPDATED_TERMINATION_REASON_CODE)
            .offerId(UPDATED_OFFER_ID)
            .offerName(UPDATED_OFFER_NAME)
            .offerType(UPDATED_OFFER_TYPE)
            .matrixxCatalogId(UPDATED_MATRIXX_CATALOG_ID)
            .matrixxResourceId(UPDATED_MATRIXX_RESOURCE_ID)
            .matrixxObjectId(UPDATED_MATRIXX_OBJECT_ID)
            .salesChannel(UPDATED_SALES_CHANNEL)
            .contractId(UPDATED_CONTRACT_ID)
            .autoRenewal(UPDATED_AUTO_RENEWAL)
            .autoPay(UPDATED_AUTO_PAY)
            .remarks(UPDATED_REMARKS)
            .vendorProvisionInd(UPDATED_VENDOR_PROVISION_IND)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return subscriptionProduct;
    }

    @BeforeEach
    public void initTest() {
        subscriptionProductRepository.deleteAll();
        subscriptionProduct = createEntity();
    }

    @Test
    public void createSubscriptionProduct() throws Exception {
        int databaseSizeBeforeCreate = subscriptionProductRepository.findAll().size();

        // Create the SubscriptionProduct
        restSubscriptionProductMockMvc.perform(post("/api/subscription-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProduct)))
            .andExpect(status().isCreated());

        // Validate the SubscriptionProduct in the database
        List<SubscriptionProduct> subscriptionProductList = subscriptionProductRepository.findAll();
        assertThat(subscriptionProductList).hasSize(databaseSizeBeforeCreate + 1);
        SubscriptionProduct testSubscriptionProduct = subscriptionProductList.get(subscriptionProductList.size() - 1);
        assertThat(testSubscriptionProduct.getProductSubsriptionSeqId()).isEqualTo(DEFAULT_PRODUCT_SUBSRIPTION_SEQ_ID);
        assertThat(testSubscriptionProduct.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testSubscriptionProduct.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testSubscriptionProduct.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testSubscriptionProduct.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testSubscriptionProduct.getActivationDate()).isEqualTo(DEFAULT_ACTIVATION_DATE);
        assertThat(testSubscriptionProduct.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testSubscriptionProduct.getSecondMsisdn()).isEqualTo(DEFAULT_SECOND_MSISDN);
        assertThat(testSubscriptionProduct.getSecondImsi()).isEqualTo(DEFAULT_SECOND_IMSI);
        assertThat(testSubscriptionProduct.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testSubscriptionProduct.getTerminationReasonCode()).isEqualTo(DEFAULT_TERMINATION_REASON_CODE);
        assertThat(testSubscriptionProduct.getOfferId()).isEqualTo(DEFAULT_OFFER_ID);
        assertThat(testSubscriptionProduct.getOfferName()).isEqualTo(DEFAULT_OFFER_NAME);
        assertThat(testSubscriptionProduct.getOfferType()).isEqualTo(DEFAULT_OFFER_TYPE);
        assertThat(testSubscriptionProduct.getMatrixxCatalogId()).isEqualTo(DEFAULT_MATRIXX_CATALOG_ID);
        assertThat(testSubscriptionProduct.getMatrixxResourceId()).isEqualTo(DEFAULT_MATRIXX_RESOURCE_ID);
        assertThat(testSubscriptionProduct.getMatrixxObjectId()).isEqualTo(DEFAULT_MATRIXX_OBJECT_ID);
        assertThat(testSubscriptionProduct.getSalesChannel()).isEqualTo(DEFAULT_SALES_CHANNEL);
        assertThat(testSubscriptionProduct.getContractId()).isEqualTo(DEFAULT_CONTRACT_ID);
        assertThat(testSubscriptionProduct.isAutoRenewal()).isEqualTo(DEFAULT_AUTO_RENEWAL);
        assertThat(testSubscriptionProduct.isAutoPay()).isEqualTo(DEFAULT_AUTO_PAY);
        assertThat(testSubscriptionProduct.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testSubscriptionProduct.isVendorProvisionInd()).isEqualTo(DEFAULT_VENDOR_PROVISION_IND);
        assertThat(testSubscriptionProduct.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSubscriptionProduct.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSubscriptionProduct.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testSubscriptionProduct.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testSubscriptionProduct.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createSubscriptionProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subscriptionProductRepository.findAll().size();

        // Create the SubscriptionProduct with an existing ID
        subscriptionProduct.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubscriptionProductMockMvc.perform(post("/api/subscription-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProduct)))
            .andExpect(status().isBadRequest());

        // Validate the SubscriptionProduct in the database
        List<SubscriptionProduct> subscriptionProductList = subscriptionProductRepository.findAll();
        assertThat(subscriptionProductList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkProductSubsriptionSeqIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProductRepository.findAll().size();
        // set the field null
        subscriptionProduct.setProductSubsriptionSeqId(null);

        // Create the SubscriptionProduct, which fails.

        restSubscriptionProductMockMvc.perform(post("/api/subscription-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProduct)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProduct> subscriptionProductList = subscriptionProductRepository.findAll();
        assertThat(subscriptionProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOrderIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProductRepository.findAll().size();
        // set the field null
        subscriptionProduct.setOrderId(null);

        // Create the SubscriptionProduct, which fails.

        restSubscriptionProductMockMvc.perform(post("/api/subscription-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProduct)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProduct> subscriptionProductList = subscriptionProductRepository.findAll();
        assertThat(subscriptionProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSubscriptionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProductRepository.findAll().size();
        // set the field null
        subscriptionProduct.setSubscriptionId(null);

        // Create the SubscriptionProduct, which fails.

        restSubscriptionProductMockMvc.perform(post("/api/subscription-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProduct)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProduct> subscriptionProductList = subscriptionProductRepository.findAll();
        assertThat(subscriptionProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkProductIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProductRepository.findAll().size();
        // set the field null
        subscriptionProduct.setProductId(null);

        // Create the SubscriptionProduct, which fails.

        restSubscriptionProductMockMvc.perform(post("/api/subscription-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProduct)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProduct> subscriptionProductList = subscriptionProductRepository.findAll();
        assertThat(subscriptionProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOfferIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProductRepository.findAll().size();
        // set the field null
        subscriptionProduct.setOfferId(null);

        // Create the SubscriptionProduct, which fails.

        restSubscriptionProductMockMvc.perform(post("/api/subscription-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProduct)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProduct> subscriptionProductList = subscriptionProductRepository.findAll();
        assertThat(subscriptionProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProductRepository.findAll().size();
        // set the field null
        subscriptionProduct.setCreatedDate(null);

        // Create the SubscriptionProduct, which fails.

        restSubscriptionProductMockMvc.perform(post("/api/subscription-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProduct)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProduct> subscriptionProductList = subscriptionProductRepository.findAll();
        assertThat(subscriptionProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProductRepository.findAll().size();
        // set the field null
        subscriptionProduct.setCreatedBy(null);

        // Create the SubscriptionProduct, which fails.

        restSubscriptionProductMockMvc.perform(post("/api/subscription-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProduct)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProduct> subscriptionProductList = subscriptionProductRepository.findAll();
        assertThat(subscriptionProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProductRepository.findAll().size();
        // set the field null
        subscriptionProduct.setLastUpdatedDate(null);

        // Create the SubscriptionProduct, which fails.

        restSubscriptionProductMockMvc.perform(post("/api/subscription-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProduct)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProduct> subscriptionProductList = subscriptionProductRepository.findAll();
        assertThat(subscriptionProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProductRepository.findAll().size();
        // set the field null
        subscriptionProduct.setLastUpdatedBy(null);

        // Create the SubscriptionProduct, which fails.

        restSubscriptionProductMockMvc.perform(post("/api/subscription-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProduct)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProduct> subscriptionProductList = subscriptionProductRepository.findAll();
        assertThat(subscriptionProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionProductRepository.findAll().size();
        // set the field null
        subscriptionProduct.setTenantId(null);

        // Create the SubscriptionProduct, which fails.

        restSubscriptionProductMockMvc.perform(post("/api/subscription-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProduct)))
            .andExpect(status().isBadRequest());

        List<SubscriptionProduct> subscriptionProductList = subscriptionProductRepository.findAll();
        assertThat(subscriptionProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllSubscriptionProducts() throws Exception {
        // Initialize the database
        subscriptionProductRepository.save(subscriptionProduct);

        // Get all the subscriptionProductList
        restSubscriptionProductMockMvc.perform(get("/api/subscription-products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subscriptionProduct.getId())))
            .andExpect(jsonPath("$.[*].productSubsriptionSeqId").value(hasItem(DEFAULT_PRODUCT_SUBSRIPTION_SEQ_ID.intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID)))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID)))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME)))
            .andExpect(jsonPath("$.[*].activationDate").value(hasItem(DEFAULT_ACTIVATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].secondMsisdn").value(hasItem(DEFAULT_SECOND_MSISDN)))
            .andExpect(jsonPath("$.[*].secondImsi").value(hasItem(DEFAULT_SECOND_IMSI)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].terminationReasonCode").value(hasItem(DEFAULT_TERMINATION_REASON_CODE)))
            .andExpect(jsonPath("$.[*].offerId").value(hasItem(DEFAULT_OFFER_ID)))
            .andExpect(jsonPath("$.[*].offerName").value(hasItem(DEFAULT_OFFER_NAME)))
            .andExpect(jsonPath("$.[*].offerType").value(hasItem(DEFAULT_OFFER_TYPE)))
            .andExpect(jsonPath("$.[*].matrixxCatalogId").value(hasItem(DEFAULT_MATRIXX_CATALOG_ID)))
            .andExpect(jsonPath("$.[*].matrixxResourceId").value(hasItem(DEFAULT_MATRIXX_RESOURCE_ID)))
            .andExpect(jsonPath("$.[*].matrixxObjectId").value(hasItem(DEFAULT_MATRIXX_OBJECT_ID)))
            .andExpect(jsonPath("$.[*].salesChannel").value(hasItem(DEFAULT_SALES_CHANNEL)))
            .andExpect(jsonPath("$.[*].contractId").value(hasItem(DEFAULT_CONTRACT_ID)))
            .andExpect(jsonPath("$.[*].autoRenewal").value(hasItem(DEFAULT_AUTO_RENEWAL.booleanValue())))
            .andExpect(jsonPath("$.[*].autoPay").value(hasItem(DEFAULT_AUTO_PAY.booleanValue())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].vendorProvisionInd").value(hasItem(DEFAULT_VENDOR_PROVISION_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getSubscriptionProduct() throws Exception {
        // Initialize the database
        subscriptionProductRepository.save(subscriptionProduct);

        // Get the subscriptionProduct
        restSubscriptionProductMockMvc.perform(get("/api/subscription-products/{id}", subscriptionProduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subscriptionProduct.getId()))
            .andExpect(jsonPath("$.productSubsriptionSeqId").value(DEFAULT_PRODUCT_SUBSRIPTION_SEQ_ID.intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.productName").value(DEFAULT_PRODUCT_NAME))
            .andExpect(jsonPath("$.activationDate").value(DEFAULT_ACTIVATION_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.secondMsisdn").value(DEFAULT_SECOND_MSISDN))
            .andExpect(jsonPath("$.secondImsi").value(DEFAULT_SECOND_IMSI))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.terminationReasonCode").value(DEFAULT_TERMINATION_REASON_CODE))
            .andExpect(jsonPath("$.offerId").value(DEFAULT_OFFER_ID))
            .andExpect(jsonPath("$.offerName").value(DEFAULT_OFFER_NAME))
            .andExpect(jsonPath("$.offerType").value(DEFAULT_OFFER_TYPE))
            .andExpect(jsonPath("$.matrixxCatalogId").value(DEFAULT_MATRIXX_CATALOG_ID))
            .andExpect(jsonPath("$.matrixxResourceId").value(DEFAULT_MATRIXX_RESOURCE_ID))
            .andExpect(jsonPath("$.matrixxObjectId").value(DEFAULT_MATRIXX_OBJECT_ID))
            .andExpect(jsonPath("$.salesChannel").value(DEFAULT_SALES_CHANNEL))
            .andExpect(jsonPath("$.contractId").value(DEFAULT_CONTRACT_ID))
            .andExpect(jsonPath("$.autoRenewal").value(DEFAULT_AUTO_RENEWAL.booleanValue()))
            .andExpect(jsonPath("$.autoPay").value(DEFAULT_AUTO_PAY.booleanValue()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.vendorProvisionInd").value(DEFAULT_VENDOR_PROVISION_IND.booleanValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingSubscriptionProduct() throws Exception {
        // Get the subscriptionProduct
        restSubscriptionProductMockMvc.perform(get("/api/subscription-products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateSubscriptionProduct() throws Exception {
        // Initialize the database
        subscriptionProductService.save(subscriptionProduct);

        int databaseSizeBeforeUpdate = subscriptionProductRepository.findAll().size();

        // Update the subscriptionProduct
        SubscriptionProduct updatedSubscriptionProduct = subscriptionProductRepository.findById(subscriptionProduct.getId()).get();
        updatedSubscriptionProduct
            .productSubsriptionSeqId(UPDATED_PRODUCT_SUBSRIPTION_SEQ_ID)
            .orderId(UPDATED_ORDER_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .productId(UPDATED_PRODUCT_ID)
            .productName(UPDATED_PRODUCT_NAME)
            .activationDate(UPDATED_ACTIVATION_DATE)
            .endDate(UPDATED_END_DATE)
            .secondMsisdn(UPDATED_SECOND_MSISDN)
            .secondImsi(UPDATED_SECOND_IMSI)
            .quantity(UPDATED_QUANTITY)
            .terminationReasonCode(UPDATED_TERMINATION_REASON_CODE)
            .offerId(UPDATED_OFFER_ID)
            .offerName(UPDATED_OFFER_NAME)
            .offerType(UPDATED_OFFER_TYPE)
            .matrixxCatalogId(UPDATED_MATRIXX_CATALOG_ID)
            .matrixxResourceId(UPDATED_MATRIXX_RESOURCE_ID)
            .matrixxObjectId(UPDATED_MATRIXX_OBJECT_ID)
            .salesChannel(UPDATED_SALES_CHANNEL)
            .contractId(UPDATED_CONTRACT_ID)
            .autoRenewal(UPDATED_AUTO_RENEWAL)
            .autoPay(UPDATED_AUTO_PAY)
            .remarks(UPDATED_REMARKS)
            .vendorProvisionInd(UPDATED_VENDOR_PROVISION_IND)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restSubscriptionProductMockMvc.perform(put("/api/subscription-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSubscriptionProduct)))
            .andExpect(status().isOk());

        // Validate the SubscriptionProduct in the database
        List<SubscriptionProduct> subscriptionProductList = subscriptionProductRepository.findAll();
        assertThat(subscriptionProductList).hasSize(databaseSizeBeforeUpdate);
        SubscriptionProduct testSubscriptionProduct = subscriptionProductList.get(subscriptionProductList.size() - 1);
        assertThat(testSubscriptionProduct.getProductSubsriptionSeqId()).isEqualTo(UPDATED_PRODUCT_SUBSRIPTION_SEQ_ID);
        assertThat(testSubscriptionProduct.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testSubscriptionProduct.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testSubscriptionProduct.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testSubscriptionProduct.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testSubscriptionProduct.getActivationDate()).isEqualTo(UPDATED_ACTIVATION_DATE);
        assertThat(testSubscriptionProduct.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testSubscriptionProduct.getSecondMsisdn()).isEqualTo(UPDATED_SECOND_MSISDN);
        assertThat(testSubscriptionProduct.getSecondImsi()).isEqualTo(UPDATED_SECOND_IMSI);
        assertThat(testSubscriptionProduct.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testSubscriptionProduct.getTerminationReasonCode()).isEqualTo(UPDATED_TERMINATION_REASON_CODE);
        assertThat(testSubscriptionProduct.getOfferId()).isEqualTo(UPDATED_OFFER_ID);
        assertThat(testSubscriptionProduct.getOfferName()).isEqualTo(UPDATED_OFFER_NAME);
        assertThat(testSubscriptionProduct.getOfferType()).isEqualTo(UPDATED_OFFER_TYPE);
        assertThat(testSubscriptionProduct.getMatrixxCatalogId()).isEqualTo(UPDATED_MATRIXX_CATALOG_ID);
        assertThat(testSubscriptionProduct.getMatrixxResourceId()).isEqualTo(UPDATED_MATRIXX_RESOURCE_ID);
        assertThat(testSubscriptionProduct.getMatrixxObjectId()).isEqualTo(UPDATED_MATRIXX_OBJECT_ID);
        assertThat(testSubscriptionProduct.getSalesChannel()).isEqualTo(UPDATED_SALES_CHANNEL);
        assertThat(testSubscriptionProduct.getContractId()).isEqualTo(UPDATED_CONTRACT_ID);
        assertThat(testSubscriptionProduct.isAutoRenewal()).isEqualTo(UPDATED_AUTO_RENEWAL);
        assertThat(testSubscriptionProduct.isAutoPay()).isEqualTo(UPDATED_AUTO_PAY);
        assertThat(testSubscriptionProduct.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testSubscriptionProduct.isVendorProvisionInd()).isEqualTo(UPDATED_VENDOR_PROVISION_IND);
        assertThat(testSubscriptionProduct.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSubscriptionProduct.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSubscriptionProduct.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testSubscriptionProduct.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testSubscriptionProduct.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingSubscriptionProduct() throws Exception {
        int databaseSizeBeforeUpdate = subscriptionProductRepository.findAll().size();

        // Create the SubscriptionProduct

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubscriptionProductMockMvc.perform(put("/api/subscription-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionProduct)))
            .andExpect(status().isBadRequest());

        // Validate the SubscriptionProduct in the database
        List<SubscriptionProduct> subscriptionProductList = subscriptionProductRepository.findAll();
        assertThat(subscriptionProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteSubscriptionProduct() throws Exception {
        // Initialize the database
        subscriptionProductService.save(subscriptionProduct);

        int databaseSizeBeforeDelete = subscriptionProductRepository.findAll().size();

        // Delete the subscriptionProduct
        restSubscriptionProductMockMvc.perform(delete("/api/subscription-products/{id}", subscriptionProduct.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SubscriptionProduct> subscriptionProductList = subscriptionProductRepository.findAll();
        assertThat(subscriptionProductList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
