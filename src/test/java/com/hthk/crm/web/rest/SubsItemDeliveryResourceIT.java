package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.SubsItemDelivery;
import com.hthk.crm.repository.SubsItemDeliveryRepository;
import com.hthk.crm.service.SubsItemDeliveryService;
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
 * Integration tests for the {@link SubsItemDeliveryResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class SubsItemDeliveryResourceIT {

    private static final Long DEFAULT_DELIVERY_ID = 1L;
    private static final Long UPDATED_DELIVERY_ID = 2L;

    private static final String DEFAULT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIPTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIPTION_ITEM_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_ITEM_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_METHOD_ID = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_METHOD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_REF_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_REF_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_FILE_GENERATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FILE_GENERATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_FILE_RECEIVED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FILE_RECEIVED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DELIVERY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DELIVERY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

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
    private SubsItemDeliveryRepository subsItemDeliveryRepository;

    @Autowired
    private SubsItemDeliveryService subsItemDeliveryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restSubsItemDeliveryMockMvc;

    private SubsItemDelivery subsItemDelivery;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SubsItemDeliveryResource subsItemDeliveryResource = new SubsItemDeliveryResource(subsItemDeliveryService);
        this.restSubsItemDeliveryMockMvc = MockMvcBuilders.standaloneSetup(subsItemDeliveryResource)
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
    public static SubsItemDelivery createEntity() {
        SubsItemDelivery subsItemDelivery = new SubsItemDelivery()
            .deliveryId(DEFAULT_DELIVERY_ID)
            .orderId(DEFAULT_ORDER_ID)
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .subscriptionItemId(DEFAULT_SUBSCRIPTION_ITEM_ID)
            .deliveryStatus(DEFAULT_DELIVERY_STATUS)
            .deliveryMethodId(DEFAULT_DELIVERY_METHOD_ID)
            .deliveryRefCode(DEFAULT_DELIVERY_REF_CODE)
            .fileGenerationDate(DEFAULT_FILE_GENERATION_DATE)
            .fileReceivedDate(DEFAULT_FILE_RECEIVED_DATE)
            .deliveryDate(DEFAULT_DELIVERY_DATE)
            .remarks(DEFAULT_REMARKS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return subsItemDelivery;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubsItemDelivery createUpdatedEntity() {
        SubsItemDelivery subsItemDelivery = new SubsItemDelivery()
            .deliveryId(UPDATED_DELIVERY_ID)
            .orderId(UPDATED_ORDER_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .subscriptionItemId(UPDATED_SUBSCRIPTION_ITEM_ID)
            .deliveryStatus(UPDATED_DELIVERY_STATUS)
            .deliveryMethodId(UPDATED_DELIVERY_METHOD_ID)
            .deliveryRefCode(UPDATED_DELIVERY_REF_CODE)
            .fileGenerationDate(UPDATED_FILE_GENERATION_DATE)
            .fileReceivedDate(UPDATED_FILE_RECEIVED_DATE)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .remarks(UPDATED_REMARKS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return subsItemDelivery;
    }

    @BeforeEach
    public void initTest() {
        subsItemDeliveryRepository.deleteAll();
        subsItemDelivery = createEntity();
    }

    @Test
    public void createSubsItemDelivery() throws Exception {
        int databaseSizeBeforeCreate = subsItemDeliveryRepository.findAll().size();

        // Create the SubsItemDelivery
        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isCreated());

        // Validate the SubsItemDelivery in the database
        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeCreate + 1);
        SubsItemDelivery testSubsItemDelivery = subsItemDeliveryList.get(subsItemDeliveryList.size() - 1);
        assertThat(testSubsItemDelivery.getDeliveryId()).isEqualTo(DEFAULT_DELIVERY_ID);
        assertThat(testSubsItemDelivery.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testSubsItemDelivery.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testSubsItemDelivery.getSubscriptionItemId()).isEqualTo(DEFAULT_SUBSCRIPTION_ITEM_ID);
        assertThat(testSubsItemDelivery.getDeliveryStatus()).isEqualTo(DEFAULT_DELIVERY_STATUS);
        assertThat(testSubsItemDelivery.getDeliveryMethodId()).isEqualTo(DEFAULT_DELIVERY_METHOD_ID);
        assertThat(testSubsItemDelivery.getDeliveryRefCode()).isEqualTo(DEFAULT_DELIVERY_REF_CODE);
        assertThat(testSubsItemDelivery.getFileGenerationDate()).isEqualTo(DEFAULT_FILE_GENERATION_DATE);
        assertThat(testSubsItemDelivery.getFileReceivedDate()).isEqualTo(DEFAULT_FILE_RECEIVED_DATE);
        assertThat(testSubsItemDelivery.getDeliveryDate()).isEqualTo(DEFAULT_DELIVERY_DATE);
        assertThat(testSubsItemDelivery.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testSubsItemDelivery.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSubsItemDelivery.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSubsItemDelivery.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testSubsItemDelivery.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testSubsItemDelivery.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createSubsItemDeliveryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subsItemDeliveryRepository.findAll().size();

        // Create the SubsItemDelivery with an existing ID
        subsItemDelivery.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        // Validate the SubsItemDelivery in the database
        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkDeliveryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setDeliveryId(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOrderIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setOrderId(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSubscriptionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setSubscriptionId(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSubscriptionItemIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setSubscriptionItemId(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDeliveryStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setDeliveryStatus(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDeliveryMethodIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setDeliveryMethodId(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setCreatedDate(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setCreatedBy(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setLastUpdatedDate(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setLastUpdatedBy(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsItemDeliveryRepository.findAll().size();
        // set the field null
        subsItemDelivery.setTenantId(null);

        // Create the SubsItemDelivery, which fails.

        restSubsItemDeliveryMockMvc.perform(post("/api/subs-item-deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllSubsItemDeliveries() throws Exception {
        // Initialize the database
        subsItemDeliveryRepository.save(subsItemDelivery);

        // Get all the subsItemDeliveryList
        restSubsItemDeliveryMockMvc.perform(get("/api/subs-item-deliveries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subsItemDelivery.getId())))
            .andExpect(jsonPath("$.[*].deliveryId").value(hasItem(DEFAULT_DELIVERY_ID.intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID)))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID)))
            .andExpect(jsonPath("$.[*].subscriptionItemId").value(hasItem(DEFAULT_SUBSCRIPTION_ITEM_ID)))
            .andExpect(jsonPath("$.[*].deliveryStatus").value(hasItem(DEFAULT_DELIVERY_STATUS)))
            .andExpect(jsonPath("$.[*].deliveryMethodId").value(hasItem(DEFAULT_DELIVERY_METHOD_ID)))
            .andExpect(jsonPath("$.[*].deliveryRefCode").value(hasItem(DEFAULT_DELIVERY_REF_CODE)))
            .andExpect(jsonPath("$.[*].fileGenerationDate").value(hasItem(DEFAULT_FILE_GENERATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].fileReceivedDate").value(hasItem(DEFAULT_FILE_RECEIVED_DATE.toString())))
            .andExpect(jsonPath("$.[*].deliveryDate").value(hasItem(DEFAULT_DELIVERY_DATE.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getSubsItemDelivery() throws Exception {
        // Initialize the database
        subsItemDeliveryRepository.save(subsItemDelivery);

        // Get the subsItemDelivery
        restSubsItemDeliveryMockMvc.perform(get("/api/subs-item-deliveries/{id}", subsItemDelivery.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subsItemDelivery.getId()))
            .andExpect(jsonPath("$.deliveryId").value(DEFAULT_DELIVERY_ID.intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID))
            .andExpect(jsonPath("$.subscriptionItemId").value(DEFAULT_SUBSCRIPTION_ITEM_ID))
            .andExpect(jsonPath("$.deliveryStatus").value(DEFAULT_DELIVERY_STATUS))
            .andExpect(jsonPath("$.deliveryMethodId").value(DEFAULT_DELIVERY_METHOD_ID))
            .andExpect(jsonPath("$.deliveryRefCode").value(DEFAULT_DELIVERY_REF_CODE))
            .andExpect(jsonPath("$.fileGenerationDate").value(DEFAULT_FILE_GENERATION_DATE.toString()))
            .andExpect(jsonPath("$.fileReceivedDate").value(DEFAULT_FILE_RECEIVED_DATE.toString()))
            .andExpect(jsonPath("$.deliveryDate").value(DEFAULT_DELIVERY_DATE.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingSubsItemDelivery() throws Exception {
        // Get the subsItemDelivery
        restSubsItemDeliveryMockMvc.perform(get("/api/subs-item-deliveries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateSubsItemDelivery() throws Exception {
        // Initialize the database
        subsItemDeliveryService.save(subsItemDelivery);

        int databaseSizeBeforeUpdate = subsItemDeliveryRepository.findAll().size();

        // Update the subsItemDelivery
        SubsItemDelivery updatedSubsItemDelivery = subsItemDeliveryRepository.findById(subsItemDelivery.getId()).get();
        updatedSubsItemDelivery
            .deliveryId(UPDATED_DELIVERY_ID)
            .orderId(UPDATED_ORDER_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .subscriptionItemId(UPDATED_SUBSCRIPTION_ITEM_ID)
            .deliveryStatus(UPDATED_DELIVERY_STATUS)
            .deliveryMethodId(UPDATED_DELIVERY_METHOD_ID)
            .deliveryRefCode(UPDATED_DELIVERY_REF_CODE)
            .fileGenerationDate(UPDATED_FILE_GENERATION_DATE)
            .fileReceivedDate(UPDATED_FILE_RECEIVED_DATE)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .remarks(UPDATED_REMARKS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restSubsItemDeliveryMockMvc.perform(put("/api/subs-item-deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSubsItemDelivery)))
            .andExpect(status().isOk());

        // Validate the SubsItemDelivery in the database
        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeUpdate);
        SubsItemDelivery testSubsItemDelivery = subsItemDeliveryList.get(subsItemDeliveryList.size() - 1);
        assertThat(testSubsItemDelivery.getDeliveryId()).isEqualTo(UPDATED_DELIVERY_ID);
        assertThat(testSubsItemDelivery.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testSubsItemDelivery.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testSubsItemDelivery.getSubscriptionItemId()).isEqualTo(UPDATED_SUBSCRIPTION_ITEM_ID);
        assertThat(testSubsItemDelivery.getDeliveryStatus()).isEqualTo(UPDATED_DELIVERY_STATUS);
        assertThat(testSubsItemDelivery.getDeliveryMethodId()).isEqualTo(UPDATED_DELIVERY_METHOD_ID);
        assertThat(testSubsItemDelivery.getDeliveryRefCode()).isEqualTo(UPDATED_DELIVERY_REF_CODE);
        assertThat(testSubsItemDelivery.getFileGenerationDate()).isEqualTo(UPDATED_FILE_GENERATION_DATE);
        assertThat(testSubsItemDelivery.getFileReceivedDate()).isEqualTo(UPDATED_FILE_RECEIVED_DATE);
        assertThat(testSubsItemDelivery.getDeliveryDate()).isEqualTo(UPDATED_DELIVERY_DATE);
        assertThat(testSubsItemDelivery.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testSubsItemDelivery.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSubsItemDelivery.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSubsItemDelivery.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testSubsItemDelivery.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testSubsItemDelivery.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingSubsItemDelivery() throws Exception {
        int databaseSizeBeforeUpdate = subsItemDeliveryRepository.findAll().size();

        // Create the SubsItemDelivery

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubsItemDeliveryMockMvc.perform(put("/api/subs-item-deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsItemDelivery)))
            .andExpect(status().isBadRequest());

        // Validate the SubsItemDelivery in the database
        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteSubsItemDelivery() throws Exception {
        // Initialize the database
        subsItemDeliveryService.save(subsItemDelivery);

        int databaseSizeBeforeDelete = subsItemDeliveryRepository.findAll().size();

        // Delete the subsItemDelivery
        restSubsItemDeliveryMockMvc.perform(delete("/api/subs-item-deliveries/{id}", subsItemDelivery.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SubsItemDelivery> subsItemDeliveryList = subsItemDeliveryRepository.findAll();
        assertThat(subsItemDeliveryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
