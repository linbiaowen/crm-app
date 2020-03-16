package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.DeliveryOption;
import com.hthk.crm.repository.DeliveryOptionRepository;
import com.hthk.crm.service.DeliveryOptionService;
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

import com.hthk.crm.domain.enumeration.DeliverOptions;
/**
 * Integration tests for the {@link DeliveryOptionResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class DeliveryOptionResourceIT {

    private static final DeliverOptions DEFAULT_DELIVERY_OPTION = DeliverOptions.EFLOCKER;
    private static final DeliverOptions UPDATED_DELIVERY_OPTION = DeliverOptions.POST;

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
    private DeliveryOptionRepository deliveryOptionRepository;

    @Autowired
    private DeliveryOptionService deliveryOptionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restDeliveryOptionMockMvc;

    private DeliveryOption deliveryOption;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DeliveryOptionResource deliveryOptionResource = new DeliveryOptionResource(deliveryOptionService);
        this.restDeliveryOptionMockMvc = MockMvcBuilders.standaloneSetup(deliveryOptionResource)
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
    public static DeliveryOption createEntity() {
        DeliveryOption deliveryOption = new DeliveryOption()
            .deliveryOption(DEFAULT_DELIVERY_OPTION)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return deliveryOption;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryOption createUpdatedEntity() {
        DeliveryOption deliveryOption = new DeliveryOption()
            .deliveryOption(UPDATED_DELIVERY_OPTION)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return deliveryOption;
    }

    @BeforeEach
    public void initTest() {
        deliveryOptionRepository.deleteAll();
        deliveryOption = createEntity();
    }

    @Test
    public void createDeliveryOption() throws Exception {
        int databaseSizeBeforeCreate = deliveryOptionRepository.findAll().size();

        // Create the DeliveryOption
        restDeliveryOptionMockMvc.perform(post("/api/delivery-options")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOption)))
            .andExpect(status().isCreated());

        // Validate the DeliveryOption in the database
        List<DeliveryOption> deliveryOptionList = deliveryOptionRepository.findAll();
        assertThat(deliveryOptionList).hasSize(databaseSizeBeforeCreate + 1);
        DeliveryOption testDeliveryOption = deliveryOptionList.get(deliveryOptionList.size() - 1);
        assertThat(testDeliveryOption.getDeliveryOption()).isEqualTo(DEFAULT_DELIVERY_OPTION);
        assertThat(testDeliveryOption.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDeliveryOption.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDeliveryOption.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testDeliveryOption.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testDeliveryOption.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createDeliveryOptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deliveryOptionRepository.findAll().size();

        // Create the DeliveryOption with an existing ID
        deliveryOption.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliveryOptionMockMvc.perform(post("/api/delivery-options")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOption)))
            .andExpect(status().isBadRequest());

        // Validate the DeliveryOption in the database
        List<DeliveryOption> deliveryOptionList = deliveryOptionRepository.findAll();
        assertThat(deliveryOptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOptionRepository.findAll().size();
        // set the field null
        deliveryOption.setCreatedDate(null);

        // Create the DeliveryOption, which fails.

        restDeliveryOptionMockMvc.perform(post("/api/delivery-options")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOption)))
            .andExpect(status().isBadRequest());

        List<DeliveryOption> deliveryOptionList = deliveryOptionRepository.findAll();
        assertThat(deliveryOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOptionRepository.findAll().size();
        // set the field null
        deliveryOption.setCreatedBy(null);

        // Create the DeliveryOption, which fails.

        restDeliveryOptionMockMvc.perform(post("/api/delivery-options")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOption)))
            .andExpect(status().isBadRequest());

        List<DeliveryOption> deliveryOptionList = deliveryOptionRepository.findAll();
        assertThat(deliveryOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOptionRepository.findAll().size();
        // set the field null
        deliveryOption.setLastUpdatedDate(null);

        // Create the DeliveryOption, which fails.

        restDeliveryOptionMockMvc.perform(post("/api/delivery-options")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOption)))
            .andExpect(status().isBadRequest());

        List<DeliveryOption> deliveryOptionList = deliveryOptionRepository.findAll();
        assertThat(deliveryOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOptionRepository.findAll().size();
        // set the field null
        deliveryOption.setLastUpdatedBy(null);

        // Create the DeliveryOption, which fails.

        restDeliveryOptionMockMvc.perform(post("/api/delivery-options")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOption)))
            .andExpect(status().isBadRequest());

        List<DeliveryOption> deliveryOptionList = deliveryOptionRepository.findAll();
        assertThat(deliveryOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryOptionRepository.findAll().size();
        // set the field null
        deliveryOption.setTenantId(null);

        // Create the DeliveryOption, which fails.

        restDeliveryOptionMockMvc.perform(post("/api/delivery-options")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOption)))
            .andExpect(status().isBadRequest());

        List<DeliveryOption> deliveryOptionList = deliveryOptionRepository.findAll();
        assertThat(deliveryOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllDeliveryOptions() throws Exception {
        // Initialize the database
        deliveryOptionRepository.save(deliveryOption);

        // Get all the deliveryOptionList
        restDeliveryOptionMockMvc.perform(get("/api/delivery-options?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deliveryOption.getId())))
            .andExpect(jsonPath("$.[*].deliveryOption").value(hasItem(DEFAULT_DELIVERY_OPTION.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getDeliveryOption() throws Exception {
        // Initialize the database
        deliveryOptionRepository.save(deliveryOption);

        // Get the deliveryOption
        restDeliveryOptionMockMvc.perform(get("/api/delivery-options/{id}", deliveryOption.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(deliveryOption.getId()))
            .andExpect(jsonPath("$.deliveryOption").value(DEFAULT_DELIVERY_OPTION.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingDeliveryOption() throws Exception {
        // Get the deliveryOption
        restDeliveryOptionMockMvc.perform(get("/api/delivery-options/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDeliveryOption() throws Exception {
        // Initialize the database
        deliveryOptionService.save(deliveryOption);

        int databaseSizeBeforeUpdate = deliveryOptionRepository.findAll().size();

        // Update the deliveryOption
        DeliveryOption updatedDeliveryOption = deliveryOptionRepository.findById(deliveryOption.getId()).get();
        updatedDeliveryOption
            .deliveryOption(UPDATED_DELIVERY_OPTION)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restDeliveryOptionMockMvc.perform(put("/api/delivery-options")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDeliveryOption)))
            .andExpect(status().isOk());

        // Validate the DeliveryOption in the database
        List<DeliveryOption> deliveryOptionList = deliveryOptionRepository.findAll();
        assertThat(deliveryOptionList).hasSize(databaseSizeBeforeUpdate);
        DeliveryOption testDeliveryOption = deliveryOptionList.get(deliveryOptionList.size() - 1);
        assertThat(testDeliveryOption.getDeliveryOption()).isEqualTo(UPDATED_DELIVERY_OPTION);
        assertThat(testDeliveryOption.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryOption.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDeliveryOption.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testDeliveryOption.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testDeliveryOption.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingDeliveryOption() throws Exception {
        int databaseSizeBeforeUpdate = deliveryOptionRepository.findAll().size();

        // Create the DeliveryOption

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryOptionMockMvc.perform(put("/api/delivery-options")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryOption)))
            .andExpect(status().isBadRequest());

        // Validate the DeliveryOption in the database
        List<DeliveryOption> deliveryOptionList = deliveryOptionRepository.findAll();
        assertThat(deliveryOptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteDeliveryOption() throws Exception {
        // Initialize the database
        deliveryOptionService.save(deliveryOption);

        int databaseSizeBeforeDelete = deliveryOptionRepository.findAll().size();

        // Delete the deliveryOption
        restDeliveryOptionMockMvc.perform(delete("/api/delivery-options/{id}", deliveryOption.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DeliveryOption> deliveryOptionList = deliveryOptionRepository.findAll();
        assertThat(deliveryOptionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
