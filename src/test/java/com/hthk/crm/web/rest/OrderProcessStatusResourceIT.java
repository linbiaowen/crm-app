package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.OrderProcessStatus;
import com.hthk.crm.repository.OrderProcessStatusRepository;
import com.hthk.crm.service.OrderProcessStatusService;
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

import com.hthk.crm.domain.enumeration.OrderStatus;
import com.hthk.crm.domain.enumeration.OrderStatus;
import com.hthk.crm.domain.enumeration.ProcessStatus;
/**
 * Integration tests for the {@link OrderProcessStatusResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class OrderProcessStatusResourceIT {

    private static final String DEFAULT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBBBBBBB";

    private static final OrderStatus DEFAULT_ENTRY_ORDER_STATUS = OrderStatus.NEW;
    private static final OrderStatus UPDATED_ENTRY_ORDER_STATUS = OrderStatus.PAYMENT_CONFIRMED;

    private static final OrderStatus DEFAULT_EXIT_ORDER_STATUS = OrderStatus.NEW;
    private static final OrderStatus UPDATED_EXIT_ORDER_STATUS = OrderStatus.PAYMENT_CONFIRMED;

    private static final Instant DEFAULT_STATUS_UPDATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_STATUS_UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PROCESS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROCESS_NAME = "BBBBBBBBBB";

    private static final ProcessStatus DEFAULT_STATUS = ProcessStatus.NEW;
    private static final ProcessStatus UPDATED_STATUS = ProcessStatus.PENDING;

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
    private OrderProcessStatusRepository orderProcessStatusRepository;

    @Autowired
    private OrderProcessStatusService orderProcessStatusService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restOrderProcessStatusMockMvc;

    private OrderProcessStatus orderProcessStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderProcessStatusResource orderProcessStatusResource = new OrderProcessStatusResource(orderProcessStatusService);
        this.restOrderProcessStatusMockMvc = MockMvcBuilders.standaloneSetup(orderProcessStatusResource)
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
    public static OrderProcessStatus createEntity() {
        OrderProcessStatus orderProcessStatus = new OrderProcessStatus()
            .orderId(DEFAULT_ORDER_ID)
            .entryOrderStatus(DEFAULT_ENTRY_ORDER_STATUS)
            .exitOrderStatus(DEFAULT_EXIT_ORDER_STATUS)
            .statusUpdatedDate(DEFAULT_STATUS_UPDATED_DATE)
            .processName(DEFAULT_PROCESS_NAME)
            .status(DEFAULT_STATUS)
            .remarks(DEFAULT_REMARKS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return orderProcessStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderProcessStatus createUpdatedEntity() {
        OrderProcessStatus orderProcessStatus = new OrderProcessStatus()
            .orderId(UPDATED_ORDER_ID)
            .entryOrderStatus(UPDATED_ENTRY_ORDER_STATUS)
            .exitOrderStatus(UPDATED_EXIT_ORDER_STATUS)
            .statusUpdatedDate(UPDATED_STATUS_UPDATED_DATE)
            .processName(UPDATED_PROCESS_NAME)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return orderProcessStatus;
    }

    @BeforeEach
    public void initTest() {
        orderProcessStatusRepository.deleteAll();
        orderProcessStatus = createEntity();
    }

    @Test
    public void createOrderProcessStatus() throws Exception {
        int databaseSizeBeforeCreate = orderProcessStatusRepository.findAll().size();

        // Create the OrderProcessStatus
        restOrderProcessStatusMockMvc.perform(post("/api/order-process-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatus)))
            .andExpect(status().isCreated());

        // Validate the OrderProcessStatus in the database
        List<OrderProcessStatus> orderProcessStatusList = orderProcessStatusRepository.findAll();
        assertThat(orderProcessStatusList).hasSize(databaseSizeBeforeCreate + 1);
        OrderProcessStatus testOrderProcessStatus = orderProcessStatusList.get(orderProcessStatusList.size() - 1);
        assertThat(testOrderProcessStatus.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testOrderProcessStatus.getEntryOrderStatus()).isEqualTo(DEFAULT_ENTRY_ORDER_STATUS);
        assertThat(testOrderProcessStatus.getExitOrderStatus()).isEqualTo(DEFAULT_EXIT_ORDER_STATUS);
        assertThat(testOrderProcessStatus.getStatusUpdatedDate()).isEqualTo(DEFAULT_STATUS_UPDATED_DATE);
        assertThat(testOrderProcessStatus.getProcessName()).isEqualTo(DEFAULT_PROCESS_NAME);
        assertThat(testOrderProcessStatus.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testOrderProcessStatus.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testOrderProcessStatus.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOrderProcessStatus.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOrderProcessStatus.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testOrderProcessStatus.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testOrderProcessStatus.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createOrderProcessStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderProcessStatusRepository.findAll().size();

        // Create the OrderProcessStatus with an existing ID
        orderProcessStatus.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderProcessStatusMockMvc.perform(post("/api/order-process-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatus)))
            .andExpect(status().isBadRequest());

        // Validate the OrderProcessStatus in the database
        List<OrderProcessStatus> orderProcessStatusList = orderProcessStatusRepository.findAll();
        assertThat(orderProcessStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkOrderIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusRepository.findAll().size();
        // set the field null
        orderProcessStatus.setOrderId(null);

        // Create the OrderProcessStatus, which fails.

        restOrderProcessStatusMockMvc.perform(post("/api/order-process-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatus)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatus> orderProcessStatusList = orderProcessStatusRepository.findAll();
        assertThat(orderProcessStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEntryOrderStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusRepository.findAll().size();
        // set the field null
        orderProcessStatus.setEntryOrderStatus(null);

        // Create the OrderProcessStatus, which fails.

        restOrderProcessStatusMockMvc.perform(post("/api/order-process-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatus)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatus> orderProcessStatusList = orderProcessStatusRepository.findAll();
        assertThat(orderProcessStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkExitOrderStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusRepository.findAll().size();
        // set the field null
        orderProcessStatus.setExitOrderStatus(null);

        // Create the OrderProcessStatus, which fails.

        restOrderProcessStatusMockMvc.perform(post("/api/order-process-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatus)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatus> orderProcessStatusList = orderProcessStatusRepository.findAll();
        assertThat(orderProcessStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStatusUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusRepository.findAll().size();
        // set the field null
        orderProcessStatus.setStatusUpdatedDate(null);

        // Create the OrderProcessStatus, which fails.

        restOrderProcessStatusMockMvc.perform(post("/api/order-process-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatus)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatus> orderProcessStatusList = orderProcessStatusRepository.findAll();
        assertThat(orderProcessStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusRepository.findAll().size();
        // set the field null
        orderProcessStatus.setCreatedDate(null);

        // Create the OrderProcessStatus, which fails.

        restOrderProcessStatusMockMvc.perform(post("/api/order-process-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatus)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatus> orderProcessStatusList = orderProcessStatusRepository.findAll();
        assertThat(orderProcessStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusRepository.findAll().size();
        // set the field null
        orderProcessStatus.setCreatedBy(null);

        // Create the OrderProcessStatus, which fails.

        restOrderProcessStatusMockMvc.perform(post("/api/order-process-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatus)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatus> orderProcessStatusList = orderProcessStatusRepository.findAll();
        assertThat(orderProcessStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusRepository.findAll().size();
        // set the field null
        orderProcessStatus.setLastUpdatedDate(null);

        // Create the OrderProcessStatus, which fails.

        restOrderProcessStatusMockMvc.perform(post("/api/order-process-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatus)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatus> orderProcessStatusList = orderProcessStatusRepository.findAll();
        assertThat(orderProcessStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusRepository.findAll().size();
        // set the field null
        orderProcessStatus.setLastUpdatedBy(null);

        // Create the OrderProcessStatus, which fails.

        restOrderProcessStatusMockMvc.perform(post("/api/order-process-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatus)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatus> orderProcessStatusList = orderProcessStatusRepository.findAll();
        assertThat(orderProcessStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusRepository.findAll().size();
        // set the field null
        orderProcessStatus.setTenantId(null);

        // Create the OrderProcessStatus, which fails.

        restOrderProcessStatusMockMvc.perform(post("/api/order-process-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatus)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatus> orderProcessStatusList = orderProcessStatusRepository.findAll();
        assertThat(orderProcessStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllOrderProcessStatuses() throws Exception {
        // Initialize the database
        orderProcessStatusRepository.save(orderProcessStatus);

        // Get all the orderProcessStatusList
        restOrderProcessStatusMockMvc.perform(get("/api/order-process-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderProcessStatus.getId())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID)))
            .andExpect(jsonPath("$.[*].entryOrderStatus").value(hasItem(DEFAULT_ENTRY_ORDER_STATUS.toString())))
            .andExpect(jsonPath("$.[*].exitOrderStatus").value(hasItem(DEFAULT_EXIT_ORDER_STATUS.toString())))
            .andExpect(jsonPath("$.[*].statusUpdatedDate").value(hasItem(DEFAULT_STATUS_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].processName").value(hasItem(DEFAULT_PROCESS_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getOrderProcessStatus() throws Exception {
        // Initialize the database
        orderProcessStatusRepository.save(orderProcessStatus);

        // Get the orderProcessStatus
        restOrderProcessStatusMockMvc.perform(get("/api/order-process-statuses/{id}", orderProcessStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderProcessStatus.getId()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID))
            .andExpect(jsonPath("$.entryOrderStatus").value(DEFAULT_ENTRY_ORDER_STATUS.toString()))
            .andExpect(jsonPath("$.exitOrderStatus").value(DEFAULT_EXIT_ORDER_STATUS.toString()))
            .andExpect(jsonPath("$.statusUpdatedDate").value(DEFAULT_STATUS_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.processName").value(DEFAULT_PROCESS_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingOrderProcessStatus() throws Exception {
        // Get the orderProcessStatus
        restOrderProcessStatusMockMvc.perform(get("/api/order-process-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOrderProcessStatus() throws Exception {
        // Initialize the database
        orderProcessStatusService.save(orderProcessStatus);

        int databaseSizeBeforeUpdate = orderProcessStatusRepository.findAll().size();

        // Update the orderProcessStatus
        OrderProcessStatus updatedOrderProcessStatus = orderProcessStatusRepository.findById(orderProcessStatus.getId()).get();
        updatedOrderProcessStatus
            .orderId(UPDATED_ORDER_ID)
            .entryOrderStatus(UPDATED_ENTRY_ORDER_STATUS)
            .exitOrderStatus(UPDATED_EXIT_ORDER_STATUS)
            .statusUpdatedDate(UPDATED_STATUS_UPDATED_DATE)
            .processName(UPDATED_PROCESS_NAME)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restOrderProcessStatusMockMvc.perform(put("/api/order-process-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrderProcessStatus)))
            .andExpect(status().isOk());

        // Validate the OrderProcessStatus in the database
        List<OrderProcessStatus> orderProcessStatusList = orderProcessStatusRepository.findAll();
        assertThat(orderProcessStatusList).hasSize(databaseSizeBeforeUpdate);
        OrderProcessStatus testOrderProcessStatus = orderProcessStatusList.get(orderProcessStatusList.size() - 1);
        assertThat(testOrderProcessStatus.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testOrderProcessStatus.getEntryOrderStatus()).isEqualTo(UPDATED_ENTRY_ORDER_STATUS);
        assertThat(testOrderProcessStatus.getExitOrderStatus()).isEqualTo(UPDATED_EXIT_ORDER_STATUS);
        assertThat(testOrderProcessStatus.getStatusUpdatedDate()).isEqualTo(UPDATED_STATUS_UPDATED_DATE);
        assertThat(testOrderProcessStatus.getProcessName()).isEqualTo(UPDATED_PROCESS_NAME);
        assertThat(testOrderProcessStatus.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOrderProcessStatus.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testOrderProcessStatus.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOrderProcessStatus.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOrderProcessStatus.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testOrderProcessStatus.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testOrderProcessStatus.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingOrderProcessStatus() throws Exception {
        int databaseSizeBeforeUpdate = orderProcessStatusRepository.findAll().size();

        // Create the OrderProcessStatus

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderProcessStatusMockMvc.perform(put("/api/order-process-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatus)))
            .andExpect(status().isBadRequest());

        // Validate the OrderProcessStatus in the database
        List<OrderProcessStatus> orderProcessStatusList = orderProcessStatusRepository.findAll();
        assertThat(orderProcessStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOrderProcessStatus() throws Exception {
        // Initialize the database
        orderProcessStatusService.save(orderProcessStatus);

        int databaseSizeBeforeDelete = orderProcessStatusRepository.findAll().size();

        // Delete the orderProcessStatus
        restOrderProcessStatusMockMvc.perform(delete("/api/order-process-statuses/{id}", orderProcessStatus.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderProcessStatus> orderProcessStatusList = orderProcessStatusRepository.findAll();
        assertThat(orderProcessStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
