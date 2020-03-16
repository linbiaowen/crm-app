package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.OrderProcessConfig;
import com.hthk.crm.repository.OrderProcessConfigRepository;
import com.hthk.crm.service.OrderProcessConfigService;
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
 * Integration tests for the {@link OrderProcessConfigResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class OrderProcessConfigResourceIT {

    private static final String DEFAULT_ORDER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_ORDER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SUB_ORDER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_NATURE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_NATURE = "BBBBBBBBBB";

    private static final String DEFAULT_PROCESS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROCESS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CHILD_PROCESS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHILD_PROCESS_NAME = "BBBBBBBBBB";

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
    private OrderProcessConfigRepository orderProcessConfigRepository;

    @Autowired
    private OrderProcessConfigService orderProcessConfigService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restOrderProcessConfigMockMvc;

    private OrderProcessConfig orderProcessConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderProcessConfigResource orderProcessConfigResource = new OrderProcessConfigResource(orderProcessConfigService);
        this.restOrderProcessConfigMockMvc = MockMvcBuilders.standaloneSetup(orderProcessConfigResource)
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
    public static OrderProcessConfig createEntity() {
        OrderProcessConfig orderProcessConfig = new OrderProcessConfig()
            .orderType(DEFAULT_ORDER_TYPE)
            .subOrderType(DEFAULT_SUB_ORDER_TYPE)
            .orderNature(DEFAULT_ORDER_NATURE)
            .processName(DEFAULT_PROCESS_NAME)
            .childProcessName(DEFAULT_CHILD_PROCESS_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return orderProcessConfig;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderProcessConfig createUpdatedEntity() {
        OrderProcessConfig orderProcessConfig = new OrderProcessConfig()
            .orderType(UPDATED_ORDER_TYPE)
            .subOrderType(UPDATED_SUB_ORDER_TYPE)
            .orderNature(UPDATED_ORDER_NATURE)
            .processName(UPDATED_PROCESS_NAME)
            .childProcessName(UPDATED_CHILD_PROCESS_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return orderProcessConfig;
    }

    @BeforeEach
    public void initTest() {
        orderProcessConfigRepository.deleteAll();
        orderProcessConfig = createEntity();
    }

    @Test
    public void createOrderProcessConfig() throws Exception {
        int databaseSizeBeforeCreate = orderProcessConfigRepository.findAll().size();

        // Create the OrderProcessConfig
        restOrderProcessConfigMockMvc.perform(post("/api/order-process-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessConfig)))
            .andExpect(status().isCreated());

        // Validate the OrderProcessConfig in the database
        List<OrderProcessConfig> orderProcessConfigList = orderProcessConfigRepository.findAll();
        assertThat(orderProcessConfigList).hasSize(databaseSizeBeforeCreate + 1);
        OrderProcessConfig testOrderProcessConfig = orderProcessConfigList.get(orderProcessConfigList.size() - 1);
        assertThat(testOrderProcessConfig.getOrderType()).isEqualTo(DEFAULT_ORDER_TYPE);
        assertThat(testOrderProcessConfig.getSubOrderType()).isEqualTo(DEFAULT_SUB_ORDER_TYPE);
        assertThat(testOrderProcessConfig.getOrderNature()).isEqualTo(DEFAULT_ORDER_NATURE);
        assertThat(testOrderProcessConfig.getProcessName()).isEqualTo(DEFAULT_PROCESS_NAME);
        assertThat(testOrderProcessConfig.getChildProcessName()).isEqualTo(DEFAULT_CHILD_PROCESS_NAME);
        assertThat(testOrderProcessConfig.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOrderProcessConfig.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOrderProcessConfig.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testOrderProcessConfig.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testOrderProcessConfig.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createOrderProcessConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderProcessConfigRepository.findAll().size();

        // Create the OrderProcessConfig with an existing ID
        orderProcessConfig.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderProcessConfigMockMvc.perform(post("/api/order-process-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessConfig)))
            .andExpect(status().isBadRequest());

        // Validate the OrderProcessConfig in the database
        List<OrderProcessConfig> orderProcessConfigList = orderProcessConfigRepository.findAll();
        assertThat(orderProcessConfigList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkOrderTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessConfigRepository.findAll().size();
        // set the field null
        orderProcessConfig.setOrderType(null);

        // Create the OrderProcessConfig, which fails.

        restOrderProcessConfigMockMvc.perform(post("/api/order-process-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessConfig)))
            .andExpect(status().isBadRequest());

        List<OrderProcessConfig> orderProcessConfigList = orderProcessConfigRepository.findAll();
        assertThat(orderProcessConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSubOrderTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessConfigRepository.findAll().size();
        // set the field null
        orderProcessConfig.setSubOrderType(null);

        // Create the OrderProcessConfig, which fails.

        restOrderProcessConfigMockMvc.perform(post("/api/order-process-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessConfig)))
            .andExpect(status().isBadRequest());

        List<OrderProcessConfig> orderProcessConfigList = orderProcessConfigRepository.findAll();
        assertThat(orderProcessConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessConfigRepository.findAll().size();
        // set the field null
        orderProcessConfig.setCreatedDate(null);

        // Create the OrderProcessConfig, which fails.

        restOrderProcessConfigMockMvc.perform(post("/api/order-process-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessConfig)))
            .andExpect(status().isBadRequest());

        List<OrderProcessConfig> orderProcessConfigList = orderProcessConfigRepository.findAll();
        assertThat(orderProcessConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessConfigRepository.findAll().size();
        // set the field null
        orderProcessConfig.setCreatedBy(null);

        // Create the OrderProcessConfig, which fails.

        restOrderProcessConfigMockMvc.perform(post("/api/order-process-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessConfig)))
            .andExpect(status().isBadRequest());

        List<OrderProcessConfig> orderProcessConfigList = orderProcessConfigRepository.findAll();
        assertThat(orderProcessConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessConfigRepository.findAll().size();
        // set the field null
        orderProcessConfig.setLastUpdatedDate(null);

        // Create the OrderProcessConfig, which fails.

        restOrderProcessConfigMockMvc.perform(post("/api/order-process-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessConfig)))
            .andExpect(status().isBadRequest());

        List<OrderProcessConfig> orderProcessConfigList = orderProcessConfigRepository.findAll();
        assertThat(orderProcessConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessConfigRepository.findAll().size();
        // set the field null
        orderProcessConfig.setLastUpdatedBy(null);

        // Create the OrderProcessConfig, which fails.

        restOrderProcessConfigMockMvc.perform(post("/api/order-process-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessConfig)))
            .andExpect(status().isBadRequest());

        List<OrderProcessConfig> orderProcessConfigList = orderProcessConfigRepository.findAll();
        assertThat(orderProcessConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessConfigRepository.findAll().size();
        // set the field null
        orderProcessConfig.setTenantId(null);

        // Create the OrderProcessConfig, which fails.

        restOrderProcessConfigMockMvc.perform(post("/api/order-process-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessConfig)))
            .andExpect(status().isBadRequest());

        List<OrderProcessConfig> orderProcessConfigList = orderProcessConfigRepository.findAll();
        assertThat(orderProcessConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllOrderProcessConfigs() throws Exception {
        // Initialize the database
        orderProcessConfigRepository.save(orderProcessConfig);

        // Get all the orderProcessConfigList
        restOrderProcessConfigMockMvc.perform(get("/api/order-process-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderProcessConfig.getId())))
            .andExpect(jsonPath("$.[*].orderType").value(hasItem(DEFAULT_ORDER_TYPE)))
            .andExpect(jsonPath("$.[*].subOrderType").value(hasItem(DEFAULT_SUB_ORDER_TYPE)))
            .andExpect(jsonPath("$.[*].orderNature").value(hasItem(DEFAULT_ORDER_NATURE)))
            .andExpect(jsonPath("$.[*].processName").value(hasItem(DEFAULT_PROCESS_NAME)))
            .andExpect(jsonPath("$.[*].childProcessName").value(hasItem(DEFAULT_CHILD_PROCESS_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getOrderProcessConfig() throws Exception {
        // Initialize the database
        orderProcessConfigRepository.save(orderProcessConfig);

        // Get the orderProcessConfig
        restOrderProcessConfigMockMvc.perform(get("/api/order-process-configs/{id}", orderProcessConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderProcessConfig.getId()))
            .andExpect(jsonPath("$.orderType").value(DEFAULT_ORDER_TYPE))
            .andExpect(jsonPath("$.subOrderType").value(DEFAULT_SUB_ORDER_TYPE))
            .andExpect(jsonPath("$.orderNature").value(DEFAULT_ORDER_NATURE))
            .andExpect(jsonPath("$.processName").value(DEFAULT_PROCESS_NAME))
            .andExpect(jsonPath("$.childProcessName").value(DEFAULT_CHILD_PROCESS_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingOrderProcessConfig() throws Exception {
        // Get the orderProcessConfig
        restOrderProcessConfigMockMvc.perform(get("/api/order-process-configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOrderProcessConfig() throws Exception {
        // Initialize the database
        orderProcessConfigService.save(orderProcessConfig);

        int databaseSizeBeforeUpdate = orderProcessConfigRepository.findAll().size();

        // Update the orderProcessConfig
        OrderProcessConfig updatedOrderProcessConfig = orderProcessConfigRepository.findById(orderProcessConfig.getId()).get();
        updatedOrderProcessConfig
            .orderType(UPDATED_ORDER_TYPE)
            .subOrderType(UPDATED_SUB_ORDER_TYPE)
            .orderNature(UPDATED_ORDER_NATURE)
            .processName(UPDATED_PROCESS_NAME)
            .childProcessName(UPDATED_CHILD_PROCESS_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restOrderProcessConfigMockMvc.perform(put("/api/order-process-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrderProcessConfig)))
            .andExpect(status().isOk());

        // Validate the OrderProcessConfig in the database
        List<OrderProcessConfig> orderProcessConfigList = orderProcessConfigRepository.findAll();
        assertThat(orderProcessConfigList).hasSize(databaseSizeBeforeUpdate);
        OrderProcessConfig testOrderProcessConfig = orderProcessConfigList.get(orderProcessConfigList.size() - 1);
        assertThat(testOrderProcessConfig.getOrderType()).isEqualTo(UPDATED_ORDER_TYPE);
        assertThat(testOrderProcessConfig.getSubOrderType()).isEqualTo(UPDATED_SUB_ORDER_TYPE);
        assertThat(testOrderProcessConfig.getOrderNature()).isEqualTo(UPDATED_ORDER_NATURE);
        assertThat(testOrderProcessConfig.getProcessName()).isEqualTo(UPDATED_PROCESS_NAME);
        assertThat(testOrderProcessConfig.getChildProcessName()).isEqualTo(UPDATED_CHILD_PROCESS_NAME);
        assertThat(testOrderProcessConfig.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOrderProcessConfig.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOrderProcessConfig.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testOrderProcessConfig.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testOrderProcessConfig.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingOrderProcessConfig() throws Exception {
        int databaseSizeBeforeUpdate = orderProcessConfigRepository.findAll().size();

        // Create the OrderProcessConfig

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderProcessConfigMockMvc.perform(put("/api/order-process-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessConfig)))
            .andExpect(status().isBadRequest());

        // Validate the OrderProcessConfig in the database
        List<OrderProcessConfig> orderProcessConfigList = orderProcessConfigRepository.findAll();
        assertThat(orderProcessConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOrderProcessConfig() throws Exception {
        // Initialize the database
        orderProcessConfigService.save(orderProcessConfig);

        int databaseSizeBeforeDelete = orderProcessConfigRepository.findAll().size();

        // Delete the orderProcessConfig
        restOrderProcessConfigMockMvc.perform(delete("/api/order-process-configs/{id}", orderProcessConfig.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderProcessConfig> orderProcessConfigList = orderProcessConfigRepository.findAll();
        assertThat(orderProcessConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
