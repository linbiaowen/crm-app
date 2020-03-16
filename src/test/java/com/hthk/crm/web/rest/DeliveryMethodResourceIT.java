package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.DeliveryMethod;
import com.hthk.crm.repository.DeliveryMethodRepository;
import com.hthk.crm.service.DeliveryMethodService;
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
 * Integration tests for the {@link DeliveryMethodResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class DeliveryMethodResourceIT {

    private static final Long DEFAULT_DELIVERY_METHOD_ID = 1L;
    private static final Long UPDATED_DELIVERY_METHOD_ID = 2L;

    private static final String DEFAULT_DELIVERY_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_METHOD_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_METHOD_DESC = "BBBBBBBBBB";

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
    private DeliveryMethodRepository deliveryMethodRepository;

    @Autowired
    private DeliveryMethodService deliveryMethodService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restDeliveryMethodMockMvc;

    private DeliveryMethod deliveryMethod;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DeliveryMethodResource deliveryMethodResource = new DeliveryMethodResource(deliveryMethodService);
        this.restDeliveryMethodMockMvc = MockMvcBuilders.standaloneSetup(deliveryMethodResource)
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
    public static DeliveryMethod createEntity() {
        DeliveryMethod deliveryMethod = new DeliveryMethod()
            .deliveryMethodId(DEFAULT_DELIVERY_METHOD_ID)
            .deliveryMethod(DEFAULT_DELIVERY_METHOD)
            .deliveryMethodDesc(DEFAULT_DELIVERY_METHOD_DESC)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return deliveryMethod;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryMethod createUpdatedEntity() {
        DeliveryMethod deliveryMethod = new DeliveryMethod()
            .deliveryMethodId(UPDATED_DELIVERY_METHOD_ID)
            .deliveryMethod(UPDATED_DELIVERY_METHOD)
            .deliveryMethodDesc(UPDATED_DELIVERY_METHOD_DESC)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return deliveryMethod;
    }

    @BeforeEach
    public void initTest() {
        deliveryMethodRepository.deleteAll();
        deliveryMethod = createEntity();
    }

    @Test
    public void createDeliveryMethod() throws Exception {
        int databaseSizeBeforeCreate = deliveryMethodRepository.findAll().size();

        // Create the DeliveryMethod
        restDeliveryMethodMockMvc.perform(post("/api/delivery-methods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryMethod)))
            .andExpect(status().isCreated());

        // Validate the DeliveryMethod in the database
        List<DeliveryMethod> deliveryMethodList = deliveryMethodRepository.findAll();
        assertThat(deliveryMethodList).hasSize(databaseSizeBeforeCreate + 1);
        DeliveryMethod testDeliveryMethod = deliveryMethodList.get(deliveryMethodList.size() - 1);
        assertThat(testDeliveryMethod.getDeliveryMethodId()).isEqualTo(DEFAULT_DELIVERY_METHOD_ID);
        assertThat(testDeliveryMethod.getDeliveryMethod()).isEqualTo(DEFAULT_DELIVERY_METHOD);
        assertThat(testDeliveryMethod.getDeliveryMethodDesc()).isEqualTo(DEFAULT_DELIVERY_METHOD_DESC);
        assertThat(testDeliveryMethod.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testDeliveryMethod.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testDeliveryMethod.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDeliveryMethod.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDeliveryMethod.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testDeliveryMethod.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testDeliveryMethod.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createDeliveryMethodWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deliveryMethodRepository.findAll().size();

        // Create the DeliveryMethod with an existing ID
        deliveryMethod.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliveryMethodMockMvc.perform(post("/api/delivery-methods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryMethod)))
            .andExpect(status().isBadRequest());

        // Validate the DeliveryMethod in the database
        List<DeliveryMethod> deliveryMethodList = deliveryMethodRepository.findAll();
        assertThat(deliveryMethodList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkDeliveryMethodIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryMethodRepository.findAll().size();
        // set the field null
        deliveryMethod.setDeliveryMethodId(null);

        // Create the DeliveryMethod, which fails.

        restDeliveryMethodMockMvc.perform(post("/api/delivery-methods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryMethod)))
            .andExpect(status().isBadRequest());

        List<DeliveryMethod> deliveryMethodList = deliveryMethodRepository.findAll();
        assertThat(deliveryMethodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDeliveryMethodIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryMethodRepository.findAll().size();
        // set the field null
        deliveryMethod.setDeliveryMethod(null);

        // Create the DeliveryMethod, which fails.

        restDeliveryMethodMockMvc.perform(post("/api/delivery-methods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryMethod)))
            .andExpect(status().isBadRequest());

        List<DeliveryMethod> deliveryMethodList = deliveryMethodRepository.findAll();
        assertThat(deliveryMethodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryMethodRepository.findAll().size();
        // set the field null
        deliveryMethod.setCreatedDate(null);

        // Create the DeliveryMethod, which fails.

        restDeliveryMethodMockMvc.perform(post("/api/delivery-methods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryMethod)))
            .andExpect(status().isBadRequest());

        List<DeliveryMethod> deliveryMethodList = deliveryMethodRepository.findAll();
        assertThat(deliveryMethodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryMethodRepository.findAll().size();
        // set the field null
        deliveryMethod.setCreatedBy(null);

        // Create the DeliveryMethod, which fails.

        restDeliveryMethodMockMvc.perform(post("/api/delivery-methods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryMethod)))
            .andExpect(status().isBadRequest());

        List<DeliveryMethod> deliveryMethodList = deliveryMethodRepository.findAll();
        assertThat(deliveryMethodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryMethodRepository.findAll().size();
        // set the field null
        deliveryMethod.setLastUpdatedDate(null);

        // Create the DeliveryMethod, which fails.

        restDeliveryMethodMockMvc.perform(post("/api/delivery-methods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryMethod)))
            .andExpect(status().isBadRequest());

        List<DeliveryMethod> deliveryMethodList = deliveryMethodRepository.findAll();
        assertThat(deliveryMethodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryMethodRepository.findAll().size();
        // set the field null
        deliveryMethod.setLastUpdatedBy(null);

        // Create the DeliveryMethod, which fails.

        restDeliveryMethodMockMvc.perform(post("/api/delivery-methods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryMethod)))
            .andExpect(status().isBadRequest());

        List<DeliveryMethod> deliveryMethodList = deliveryMethodRepository.findAll();
        assertThat(deliveryMethodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryMethodRepository.findAll().size();
        // set the field null
        deliveryMethod.setTenantId(null);

        // Create the DeliveryMethod, which fails.

        restDeliveryMethodMockMvc.perform(post("/api/delivery-methods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryMethod)))
            .andExpect(status().isBadRequest());

        List<DeliveryMethod> deliveryMethodList = deliveryMethodRepository.findAll();
        assertThat(deliveryMethodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllDeliveryMethods() throws Exception {
        // Initialize the database
        deliveryMethodRepository.save(deliveryMethod);

        // Get all the deliveryMethodList
        restDeliveryMethodMockMvc.perform(get("/api/delivery-methods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deliveryMethod.getId())))
            .andExpect(jsonPath("$.[*].deliveryMethodId").value(hasItem(DEFAULT_DELIVERY_METHOD_ID.intValue())))
            .andExpect(jsonPath("$.[*].deliveryMethod").value(hasItem(DEFAULT_DELIVERY_METHOD)))
            .andExpect(jsonPath("$.[*].deliveryMethodDesc").value(hasItem(DEFAULT_DELIVERY_METHOD_DESC)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getDeliveryMethod() throws Exception {
        // Initialize the database
        deliveryMethodRepository.save(deliveryMethod);

        // Get the deliveryMethod
        restDeliveryMethodMockMvc.perform(get("/api/delivery-methods/{id}", deliveryMethod.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(deliveryMethod.getId()))
            .andExpect(jsonPath("$.deliveryMethodId").value(DEFAULT_DELIVERY_METHOD_ID.intValue()))
            .andExpect(jsonPath("$.deliveryMethod").value(DEFAULT_DELIVERY_METHOD))
            .andExpect(jsonPath("$.deliveryMethodDesc").value(DEFAULT_DELIVERY_METHOD_DESC))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingDeliveryMethod() throws Exception {
        // Get the deliveryMethod
        restDeliveryMethodMockMvc.perform(get("/api/delivery-methods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDeliveryMethod() throws Exception {
        // Initialize the database
        deliveryMethodService.save(deliveryMethod);

        int databaseSizeBeforeUpdate = deliveryMethodRepository.findAll().size();

        // Update the deliveryMethod
        DeliveryMethod updatedDeliveryMethod = deliveryMethodRepository.findById(deliveryMethod.getId()).get();
        updatedDeliveryMethod
            .deliveryMethodId(UPDATED_DELIVERY_METHOD_ID)
            .deliveryMethod(UPDATED_DELIVERY_METHOD)
            .deliveryMethodDesc(UPDATED_DELIVERY_METHOD_DESC)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restDeliveryMethodMockMvc.perform(put("/api/delivery-methods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDeliveryMethod)))
            .andExpect(status().isOk());

        // Validate the DeliveryMethod in the database
        List<DeliveryMethod> deliveryMethodList = deliveryMethodRepository.findAll();
        assertThat(deliveryMethodList).hasSize(databaseSizeBeforeUpdate);
        DeliveryMethod testDeliveryMethod = deliveryMethodList.get(deliveryMethodList.size() - 1);
        assertThat(testDeliveryMethod.getDeliveryMethodId()).isEqualTo(UPDATED_DELIVERY_METHOD_ID);
        assertThat(testDeliveryMethod.getDeliveryMethod()).isEqualTo(UPDATED_DELIVERY_METHOD);
        assertThat(testDeliveryMethod.getDeliveryMethodDesc()).isEqualTo(UPDATED_DELIVERY_METHOD_DESC);
        assertThat(testDeliveryMethod.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testDeliveryMethod.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testDeliveryMethod.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryMethod.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDeliveryMethod.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testDeliveryMethod.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testDeliveryMethod.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingDeliveryMethod() throws Exception {
        int databaseSizeBeforeUpdate = deliveryMethodRepository.findAll().size();

        // Create the DeliveryMethod

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryMethodMockMvc.perform(put("/api/delivery-methods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryMethod)))
            .andExpect(status().isBadRequest());

        // Validate the DeliveryMethod in the database
        List<DeliveryMethod> deliveryMethodList = deliveryMethodRepository.findAll();
        assertThat(deliveryMethodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteDeliveryMethod() throws Exception {
        // Initialize the database
        deliveryMethodService.save(deliveryMethod);

        int databaseSizeBeforeDelete = deliveryMethodRepository.findAll().size();

        // Delete the deliveryMethod
        restDeliveryMethodMockMvc.perform(delete("/api/delivery-methods/{id}", deliveryMethod.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DeliveryMethod> deliveryMethodList = deliveryMethodRepository.findAll();
        assertThat(deliveryMethodList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
