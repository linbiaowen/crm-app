package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.SubsDeliveryItemDetails;
import com.hthk.crm.repository.SubsDeliveryItemDetailsRepository;
import com.hthk.crm.service.SubsDeliveryItemDetailsService;
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
 * Integration tests for the {@link SubsDeliveryItemDetailsResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class SubsDeliveryItemDetailsResourceIT {

    private static final Long DEFAULT_SUBSCRIPTION_ITEM_ID = 1L;
    private static final Long UPDATED_SUBSCRIPTION_ITEM_ID = 2L;

    private static final Long DEFAULT_DELIVERY_ID = 1L;
    private static final Long UPDATED_DELIVERY_ID = 2L;

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICE_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_MODEL = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICE_SERIAL_NBR = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_SERIAL_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_IMEI = "AAAAAAAAAA";
    private static final String UPDATED_IMEI = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_THEME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_THEME = "BBBBBBBBBB";

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
    private SubsDeliveryItemDetailsRepository subsDeliveryItemDetailsRepository;

    @Autowired
    private SubsDeliveryItemDetailsService subsDeliveryItemDetailsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restSubsDeliveryItemDetailsMockMvc;

    private SubsDeliveryItemDetails subsDeliveryItemDetails;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SubsDeliveryItemDetailsResource subsDeliveryItemDetailsResource = new SubsDeliveryItemDetailsResource(subsDeliveryItemDetailsService);
        this.restSubsDeliveryItemDetailsMockMvc = MockMvcBuilders.standaloneSetup(subsDeliveryItemDetailsResource)
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
    public static SubsDeliveryItemDetails createEntity() {
        SubsDeliveryItemDetails subsDeliveryItemDetails = new SubsDeliveryItemDetails()
            .subscriptionItemId(DEFAULT_SUBSCRIPTION_ITEM_ID)
            .deliveryId(DEFAULT_DELIVERY_ID)
            .productId(DEFAULT_PRODUCT_ID)
            .productName(DEFAULT_PRODUCT_NAME)
            .deviceType(DEFAULT_DEVICE_TYPE)
            .deviceModel(DEFAULT_DEVICE_MODEL)
            .deviceSerialNbr(DEFAULT_DEVICE_SERIAL_NBR)
            .imei(DEFAULT_IMEI)
            .productTheme(DEFAULT_PRODUCT_THEME)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return subsDeliveryItemDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubsDeliveryItemDetails createUpdatedEntity() {
        SubsDeliveryItemDetails subsDeliveryItemDetails = new SubsDeliveryItemDetails()
            .subscriptionItemId(UPDATED_SUBSCRIPTION_ITEM_ID)
            .deliveryId(UPDATED_DELIVERY_ID)
            .productId(UPDATED_PRODUCT_ID)
            .productName(UPDATED_PRODUCT_NAME)
            .deviceType(UPDATED_DEVICE_TYPE)
            .deviceModel(UPDATED_DEVICE_MODEL)
            .deviceSerialNbr(UPDATED_DEVICE_SERIAL_NBR)
            .imei(UPDATED_IMEI)
            .productTheme(UPDATED_PRODUCT_THEME)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return subsDeliveryItemDetails;
    }

    @BeforeEach
    public void initTest() {
        subsDeliveryItemDetailsRepository.deleteAll();
        subsDeliveryItemDetails = createEntity();
    }

    @Test
    public void createSubsDeliveryItemDetails() throws Exception {
        int databaseSizeBeforeCreate = subsDeliveryItemDetailsRepository.findAll().size();

        // Create the SubsDeliveryItemDetails
        restSubsDeliveryItemDetailsMockMvc.perform(post("/api/subs-delivery-item-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsDeliveryItemDetails)))
            .andExpect(status().isCreated());

        // Validate the SubsDeliveryItemDetails in the database
        List<SubsDeliveryItemDetails> subsDeliveryItemDetailsList = subsDeliveryItemDetailsRepository.findAll();
        assertThat(subsDeliveryItemDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        SubsDeliveryItemDetails testSubsDeliveryItemDetails = subsDeliveryItemDetailsList.get(subsDeliveryItemDetailsList.size() - 1);
        assertThat(testSubsDeliveryItemDetails.getSubscriptionItemId()).isEqualTo(DEFAULT_SUBSCRIPTION_ITEM_ID);
        assertThat(testSubsDeliveryItemDetails.getDeliveryId()).isEqualTo(DEFAULT_DELIVERY_ID);
        assertThat(testSubsDeliveryItemDetails.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testSubsDeliveryItemDetails.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testSubsDeliveryItemDetails.getDeviceType()).isEqualTo(DEFAULT_DEVICE_TYPE);
        assertThat(testSubsDeliveryItemDetails.getDeviceModel()).isEqualTo(DEFAULT_DEVICE_MODEL);
        assertThat(testSubsDeliveryItemDetails.getDeviceSerialNbr()).isEqualTo(DEFAULT_DEVICE_SERIAL_NBR);
        assertThat(testSubsDeliveryItemDetails.getImei()).isEqualTo(DEFAULT_IMEI);
        assertThat(testSubsDeliveryItemDetails.getProductTheme()).isEqualTo(DEFAULT_PRODUCT_THEME);
        assertThat(testSubsDeliveryItemDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSubsDeliveryItemDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSubsDeliveryItemDetails.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testSubsDeliveryItemDetails.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testSubsDeliveryItemDetails.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createSubsDeliveryItemDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subsDeliveryItemDetailsRepository.findAll().size();

        // Create the SubsDeliveryItemDetails with an existing ID
        subsDeliveryItemDetails.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubsDeliveryItemDetailsMockMvc.perform(post("/api/subs-delivery-item-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsDeliveryItemDetails)))
            .andExpect(status().isBadRequest());

        // Validate the SubsDeliveryItemDetails in the database
        List<SubsDeliveryItemDetails> subsDeliveryItemDetailsList = subsDeliveryItemDetailsRepository.findAll();
        assertThat(subsDeliveryItemDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkSubscriptionItemIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsDeliveryItemDetailsRepository.findAll().size();
        // set the field null
        subsDeliveryItemDetails.setSubscriptionItemId(null);

        // Create the SubsDeliveryItemDetails, which fails.

        restSubsDeliveryItemDetailsMockMvc.perform(post("/api/subs-delivery-item-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsDeliveryItemDetails)))
            .andExpect(status().isBadRequest());

        List<SubsDeliveryItemDetails> subsDeliveryItemDetailsList = subsDeliveryItemDetailsRepository.findAll();
        assertThat(subsDeliveryItemDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDeliveryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsDeliveryItemDetailsRepository.findAll().size();
        // set the field null
        subsDeliveryItemDetails.setDeliveryId(null);

        // Create the SubsDeliveryItemDetails, which fails.

        restSubsDeliveryItemDetailsMockMvc.perform(post("/api/subs-delivery-item-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsDeliveryItemDetails)))
            .andExpect(status().isBadRequest());

        List<SubsDeliveryItemDetails> subsDeliveryItemDetailsList = subsDeliveryItemDetailsRepository.findAll();
        assertThat(subsDeliveryItemDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkProductIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsDeliveryItemDetailsRepository.findAll().size();
        // set the field null
        subsDeliveryItemDetails.setProductId(null);

        // Create the SubsDeliveryItemDetails, which fails.

        restSubsDeliveryItemDetailsMockMvc.perform(post("/api/subs-delivery-item-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsDeliveryItemDetails)))
            .andExpect(status().isBadRequest());

        List<SubsDeliveryItemDetails> subsDeliveryItemDetailsList = subsDeliveryItemDetailsRepository.findAll();
        assertThat(subsDeliveryItemDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsDeliveryItemDetailsRepository.findAll().size();
        // set the field null
        subsDeliveryItemDetails.setCreatedDate(null);

        // Create the SubsDeliveryItemDetails, which fails.

        restSubsDeliveryItemDetailsMockMvc.perform(post("/api/subs-delivery-item-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsDeliveryItemDetails)))
            .andExpect(status().isBadRequest());

        List<SubsDeliveryItemDetails> subsDeliveryItemDetailsList = subsDeliveryItemDetailsRepository.findAll();
        assertThat(subsDeliveryItemDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsDeliveryItemDetailsRepository.findAll().size();
        // set the field null
        subsDeliveryItemDetails.setCreatedBy(null);

        // Create the SubsDeliveryItemDetails, which fails.

        restSubsDeliveryItemDetailsMockMvc.perform(post("/api/subs-delivery-item-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsDeliveryItemDetails)))
            .andExpect(status().isBadRequest());

        List<SubsDeliveryItemDetails> subsDeliveryItemDetailsList = subsDeliveryItemDetailsRepository.findAll();
        assertThat(subsDeliveryItemDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsDeliveryItemDetailsRepository.findAll().size();
        // set the field null
        subsDeliveryItemDetails.setLastUpdatedDate(null);

        // Create the SubsDeliveryItemDetails, which fails.

        restSubsDeliveryItemDetailsMockMvc.perform(post("/api/subs-delivery-item-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsDeliveryItemDetails)))
            .andExpect(status().isBadRequest());

        List<SubsDeliveryItemDetails> subsDeliveryItemDetailsList = subsDeliveryItemDetailsRepository.findAll();
        assertThat(subsDeliveryItemDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsDeliveryItemDetailsRepository.findAll().size();
        // set the field null
        subsDeliveryItemDetails.setLastUpdatedBy(null);

        // Create the SubsDeliveryItemDetails, which fails.

        restSubsDeliveryItemDetailsMockMvc.perform(post("/api/subs-delivery-item-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsDeliveryItemDetails)))
            .andExpect(status().isBadRequest());

        List<SubsDeliveryItemDetails> subsDeliveryItemDetailsList = subsDeliveryItemDetailsRepository.findAll();
        assertThat(subsDeliveryItemDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsDeliveryItemDetailsRepository.findAll().size();
        // set the field null
        subsDeliveryItemDetails.setTenantId(null);

        // Create the SubsDeliveryItemDetails, which fails.

        restSubsDeliveryItemDetailsMockMvc.perform(post("/api/subs-delivery-item-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsDeliveryItemDetails)))
            .andExpect(status().isBadRequest());

        List<SubsDeliveryItemDetails> subsDeliveryItemDetailsList = subsDeliveryItemDetailsRepository.findAll();
        assertThat(subsDeliveryItemDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllSubsDeliveryItemDetails() throws Exception {
        // Initialize the database
        subsDeliveryItemDetailsRepository.save(subsDeliveryItemDetails);

        // Get all the subsDeliveryItemDetailsList
        restSubsDeliveryItemDetailsMockMvc.perform(get("/api/subs-delivery-item-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subsDeliveryItemDetails.getId())))
            .andExpect(jsonPath("$.[*].subscriptionItemId").value(hasItem(DEFAULT_SUBSCRIPTION_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].deliveryId").value(hasItem(DEFAULT_DELIVERY_ID.intValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME)))
            .andExpect(jsonPath("$.[*].deviceType").value(hasItem(DEFAULT_DEVICE_TYPE)))
            .andExpect(jsonPath("$.[*].deviceModel").value(hasItem(DEFAULT_DEVICE_MODEL)))
            .andExpect(jsonPath("$.[*].deviceSerialNbr").value(hasItem(DEFAULT_DEVICE_SERIAL_NBR)))
            .andExpect(jsonPath("$.[*].imei").value(hasItem(DEFAULT_IMEI)))
            .andExpect(jsonPath("$.[*].productTheme").value(hasItem(DEFAULT_PRODUCT_THEME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getSubsDeliveryItemDetails() throws Exception {
        // Initialize the database
        subsDeliveryItemDetailsRepository.save(subsDeliveryItemDetails);

        // Get the subsDeliveryItemDetails
        restSubsDeliveryItemDetailsMockMvc.perform(get("/api/subs-delivery-item-details/{id}", subsDeliveryItemDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subsDeliveryItemDetails.getId()))
            .andExpect(jsonPath("$.subscriptionItemId").value(DEFAULT_SUBSCRIPTION_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.deliveryId").value(DEFAULT_DELIVERY_ID.intValue()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.productName").value(DEFAULT_PRODUCT_NAME))
            .andExpect(jsonPath("$.deviceType").value(DEFAULT_DEVICE_TYPE))
            .andExpect(jsonPath("$.deviceModel").value(DEFAULT_DEVICE_MODEL))
            .andExpect(jsonPath("$.deviceSerialNbr").value(DEFAULT_DEVICE_SERIAL_NBR))
            .andExpect(jsonPath("$.imei").value(DEFAULT_IMEI))
            .andExpect(jsonPath("$.productTheme").value(DEFAULT_PRODUCT_THEME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingSubsDeliveryItemDetails() throws Exception {
        // Get the subsDeliveryItemDetails
        restSubsDeliveryItemDetailsMockMvc.perform(get("/api/subs-delivery-item-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateSubsDeliveryItemDetails() throws Exception {
        // Initialize the database
        subsDeliveryItemDetailsService.save(subsDeliveryItemDetails);

        int databaseSizeBeforeUpdate = subsDeliveryItemDetailsRepository.findAll().size();

        // Update the subsDeliveryItemDetails
        SubsDeliveryItemDetails updatedSubsDeliveryItemDetails = subsDeliveryItemDetailsRepository.findById(subsDeliveryItemDetails.getId()).get();
        updatedSubsDeliveryItemDetails
            .subscriptionItemId(UPDATED_SUBSCRIPTION_ITEM_ID)
            .deliveryId(UPDATED_DELIVERY_ID)
            .productId(UPDATED_PRODUCT_ID)
            .productName(UPDATED_PRODUCT_NAME)
            .deviceType(UPDATED_DEVICE_TYPE)
            .deviceModel(UPDATED_DEVICE_MODEL)
            .deviceSerialNbr(UPDATED_DEVICE_SERIAL_NBR)
            .imei(UPDATED_IMEI)
            .productTheme(UPDATED_PRODUCT_THEME)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restSubsDeliveryItemDetailsMockMvc.perform(put("/api/subs-delivery-item-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSubsDeliveryItemDetails)))
            .andExpect(status().isOk());

        // Validate the SubsDeliveryItemDetails in the database
        List<SubsDeliveryItemDetails> subsDeliveryItemDetailsList = subsDeliveryItemDetailsRepository.findAll();
        assertThat(subsDeliveryItemDetailsList).hasSize(databaseSizeBeforeUpdate);
        SubsDeliveryItemDetails testSubsDeliveryItemDetails = subsDeliveryItemDetailsList.get(subsDeliveryItemDetailsList.size() - 1);
        assertThat(testSubsDeliveryItemDetails.getSubscriptionItemId()).isEqualTo(UPDATED_SUBSCRIPTION_ITEM_ID);
        assertThat(testSubsDeliveryItemDetails.getDeliveryId()).isEqualTo(UPDATED_DELIVERY_ID);
        assertThat(testSubsDeliveryItemDetails.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testSubsDeliveryItemDetails.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testSubsDeliveryItemDetails.getDeviceType()).isEqualTo(UPDATED_DEVICE_TYPE);
        assertThat(testSubsDeliveryItemDetails.getDeviceModel()).isEqualTo(UPDATED_DEVICE_MODEL);
        assertThat(testSubsDeliveryItemDetails.getDeviceSerialNbr()).isEqualTo(UPDATED_DEVICE_SERIAL_NBR);
        assertThat(testSubsDeliveryItemDetails.getImei()).isEqualTo(UPDATED_IMEI);
        assertThat(testSubsDeliveryItemDetails.getProductTheme()).isEqualTo(UPDATED_PRODUCT_THEME);
        assertThat(testSubsDeliveryItemDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSubsDeliveryItemDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSubsDeliveryItemDetails.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testSubsDeliveryItemDetails.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testSubsDeliveryItemDetails.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingSubsDeliveryItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = subsDeliveryItemDetailsRepository.findAll().size();

        // Create the SubsDeliveryItemDetails

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubsDeliveryItemDetailsMockMvc.perform(put("/api/subs-delivery-item-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsDeliveryItemDetails)))
            .andExpect(status().isBadRequest());

        // Validate the SubsDeliveryItemDetails in the database
        List<SubsDeliveryItemDetails> subsDeliveryItemDetailsList = subsDeliveryItemDetailsRepository.findAll();
        assertThat(subsDeliveryItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteSubsDeliveryItemDetails() throws Exception {
        // Initialize the database
        subsDeliveryItemDetailsService.save(subsDeliveryItemDetails);

        int databaseSizeBeforeDelete = subsDeliveryItemDetailsRepository.findAll().size();

        // Delete the subsDeliveryItemDetails
        restSubsDeliveryItemDetailsMockMvc.perform(delete("/api/subs-delivery-item-details/{id}", subsDeliveryItemDetails.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SubsDeliveryItemDetails> subsDeliveryItemDetailsList = subsDeliveryItemDetailsRepository.findAll();
        assertThat(subsDeliveryItemDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
