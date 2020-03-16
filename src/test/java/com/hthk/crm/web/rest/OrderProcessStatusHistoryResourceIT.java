package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.OrderProcessStatusHistory;
import com.hthk.crm.repository.OrderProcessStatusHistoryRepository;
import com.hthk.crm.service.OrderProcessStatusHistoryService;
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
 * Integration tests for the {@link OrderProcessStatusHistoryResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class OrderProcessStatusHistoryResourceIT {

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
    private OrderProcessStatusHistoryRepository orderProcessStatusHistoryRepository;

    @Autowired
    private OrderProcessStatusHistoryService orderProcessStatusHistoryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restOrderProcessStatusHistoryMockMvc;

    private OrderProcessStatusHistory orderProcessStatusHistory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderProcessStatusHistoryResource orderProcessStatusHistoryResource = new OrderProcessStatusHistoryResource(orderProcessStatusHistoryService);
        this.restOrderProcessStatusHistoryMockMvc = MockMvcBuilders.standaloneSetup(orderProcessStatusHistoryResource)
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
    public static OrderProcessStatusHistory createEntity() {
        OrderProcessStatusHistory orderProcessStatusHistory = new OrderProcessStatusHistory()
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
        return orderProcessStatusHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderProcessStatusHistory createUpdatedEntity() {
        OrderProcessStatusHistory orderProcessStatusHistory = new OrderProcessStatusHistory()
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
        return orderProcessStatusHistory;
    }

    @BeforeEach
    public void initTest() {
        orderProcessStatusHistoryRepository.deleteAll();
        orderProcessStatusHistory = createEntity();
    }

    @Test
    public void createOrderProcessStatusHistory() throws Exception {
        int databaseSizeBeforeCreate = orderProcessStatusHistoryRepository.findAll().size();

        // Create the OrderProcessStatusHistory
        restOrderProcessStatusHistoryMockMvc.perform(post("/api/order-process-status-histories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatusHistory)))
            .andExpect(status().isCreated());

        // Validate the OrderProcessStatusHistory in the database
        List<OrderProcessStatusHistory> orderProcessStatusHistoryList = orderProcessStatusHistoryRepository.findAll();
        assertThat(orderProcessStatusHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        OrderProcessStatusHistory testOrderProcessStatusHistory = orderProcessStatusHistoryList.get(orderProcessStatusHistoryList.size() - 1);
        assertThat(testOrderProcessStatusHistory.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testOrderProcessStatusHistory.getEntryOrderStatus()).isEqualTo(DEFAULT_ENTRY_ORDER_STATUS);
        assertThat(testOrderProcessStatusHistory.getExitOrderStatus()).isEqualTo(DEFAULT_EXIT_ORDER_STATUS);
        assertThat(testOrderProcessStatusHistory.getStatusUpdatedDate()).isEqualTo(DEFAULT_STATUS_UPDATED_DATE);
        assertThat(testOrderProcessStatusHistory.getProcessName()).isEqualTo(DEFAULT_PROCESS_NAME);
        assertThat(testOrderProcessStatusHistory.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testOrderProcessStatusHistory.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testOrderProcessStatusHistory.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOrderProcessStatusHistory.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOrderProcessStatusHistory.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testOrderProcessStatusHistory.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testOrderProcessStatusHistory.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createOrderProcessStatusHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderProcessStatusHistoryRepository.findAll().size();

        // Create the OrderProcessStatusHistory with an existing ID
        orderProcessStatusHistory.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderProcessStatusHistoryMockMvc.perform(post("/api/order-process-status-histories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatusHistory)))
            .andExpect(status().isBadRequest());

        // Validate the OrderProcessStatusHistory in the database
        List<OrderProcessStatusHistory> orderProcessStatusHistoryList = orderProcessStatusHistoryRepository.findAll();
        assertThat(orderProcessStatusHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkOrderIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusHistoryRepository.findAll().size();
        // set the field null
        orderProcessStatusHistory.setOrderId(null);

        // Create the OrderProcessStatusHistory, which fails.

        restOrderProcessStatusHistoryMockMvc.perform(post("/api/order-process-status-histories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatusHistory)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatusHistory> orderProcessStatusHistoryList = orderProcessStatusHistoryRepository.findAll();
        assertThat(orderProcessStatusHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEntryOrderStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusHistoryRepository.findAll().size();
        // set the field null
        orderProcessStatusHistory.setEntryOrderStatus(null);

        // Create the OrderProcessStatusHistory, which fails.

        restOrderProcessStatusHistoryMockMvc.perform(post("/api/order-process-status-histories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatusHistory)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatusHistory> orderProcessStatusHistoryList = orderProcessStatusHistoryRepository.findAll();
        assertThat(orderProcessStatusHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkExitOrderStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusHistoryRepository.findAll().size();
        // set the field null
        orderProcessStatusHistory.setExitOrderStatus(null);

        // Create the OrderProcessStatusHistory, which fails.

        restOrderProcessStatusHistoryMockMvc.perform(post("/api/order-process-status-histories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatusHistory)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatusHistory> orderProcessStatusHistoryList = orderProcessStatusHistoryRepository.findAll();
        assertThat(orderProcessStatusHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStatusUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusHistoryRepository.findAll().size();
        // set the field null
        orderProcessStatusHistory.setStatusUpdatedDate(null);

        // Create the OrderProcessStatusHistory, which fails.

        restOrderProcessStatusHistoryMockMvc.perform(post("/api/order-process-status-histories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatusHistory)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatusHistory> orderProcessStatusHistoryList = orderProcessStatusHistoryRepository.findAll();
        assertThat(orderProcessStatusHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusHistoryRepository.findAll().size();
        // set the field null
        orderProcessStatusHistory.setCreatedDate(null);

        // Create the OrderProcessStatusHistory, which fails.

        restOrderProcessStatusHistoryMockMvc.perform(post("/api/order-process-status-histories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatusHistory)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatusHistory> orderProcessStatusHistoryList = orderProcessStatusHistoryRepository.findAll();
        assertThat(orderProcessStatusHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusHistoryRepository.findAll().size();
        // set the field null
        orderProcessStatusHistory.setCreatedBy(null);

        // Create the OrderProcessStatusHistory, which fails.

        restOrderProcessStatusHistoryMockMvc.perform(post("/api/order-process-status-histories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatusHistory)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatusHistory> orderProcessStatusHistoryList = orderProcessStatusHistoryRepository.findAll();
        assertThat(orderProcessStatusHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusHistoryRepository.findAll().size();
        // set the field null
        orderProcessStatusHistory.setLastUpdatedDate(null);

        // Create the OrderProcessStatusHistory, which fails.

        restOrderProcessStatusHistoryMockMvc.perform(post("/api/order-process-status-histories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatusHistory)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatusHistory> orderProcessStatusHistoryList = orderProcessStatusHistoryRepository.findAll();
        assertThat(orderProcessStatusHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusHistoryRepository.findAll().size();
        // set the field null
        orderProcessStatusHistory.setLastUpdatedBy(null);

        // Create the OrderProcessStatusHistory, which fails.

        restOrderProcessStatusHistoryMockMvc.perform(post("/api/order-process-status-histories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatusHistory)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatusHistory> orderProcessStatusHistoryList = orderProcessStatusHistoryRepository.findAll();
        assertThat(orderProcessStatusHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderProcessStatusHistoryRepository.findAll().size();
        // set the field null
        orderProcessStatusHistory.setTenantId(null);

        // Create the OrderProcessStatusHistory, which fails.

        restOrderProcessStatusHistoryMockMvc.perform(post("/api/order-process-status-histories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatusHistory)))
            .andExpect(status().isBadRequest());

        List<OrderProcessStatusHistory> orderProcessStatusHistoryList = orderProcessStatusHistoryRepository.findAll();
        assertThat(orderProcessStatusHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllOrderProcessStatusHistories() throws Exception {
        // Initialize the database
        orderProcessStatusHistoryRepository.save(orderProcessStatusHistory);

        // Get all the orderProcessStatusHistoryList
        restOrderProcessStatusHistoryMockMvc.perform(get("/api/order-process-status-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderProcessStatusHistory.getId())))
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
    public void getOrderProcessStatusHistory() throws Exception {
        // Initialize the database
        orderProcessStatusHistoryRepository.save(orderProcessStatusHistory);

        // Get the orderProcessStatusHistory
        restOrderProcessStatusHistoryMockMvc.perform(get("/api/order-process-status-histories/{id}", orderProcessStatusHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderProcessStatusHistory.getId()))
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
    public void getNonExistingOrderProcessStatusHistory() throws Exception {
        // Get the orderProcessStatusHistory
        restOrderProcessStatusHistoryMockMvc.perform(get("/api/order-process-status-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOrderProcessStatusHistory() throws Exception {
        // Initialize the database
        orderProcessStatusHistoryService.save(orderProcessStatusHistory);

        int databaseSizeBeforeUpdate = orderProcessStatusHistoryRepository.findAll().size();

        // Update the orderProcessStatusHistory
        OrderProcessStatusHistory updatedOrderProcessStatusHistory = orderProcessStatusHistoryRepository.findById(orderProcessStatusHistory.getId()).get();
        updatedOrderProcessStatusHistory
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

        restOrderProcessStatusHistoryMockMvc.perform(put("/api/order-process-status-histories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrderProcessStatusHistory)))
            .andExpect(status().isOk());

        // Validate the OrderProcessStatusHistory in the database
        List<OrderProcessStatusHistory> orderProcessStatusHistoryList = orderProcessStatusHistoryRepository.findAll();
        assertThat(orderProcessStatusHistoryList).hasSize(databaseSizeBeforeUpdate);
        OrderProcessStatusHistory testOrderProcessStatusHistory = orderProcessStatusHistoryList.get(orderProcessStatusHistoryList.size() - 1);
        assertThat(testOrderProcessStatusHistory.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testOrderProcessStatusHistory.getEntryOrderStatus()).isEqualTo(UPDATED_ENTRY_ORDER_STATUS);
        assertThat(testOrderProcessStatusHistory.getExitOrderStatus()).isEqualTo(UPDATED_EXIT_ORDER_STATUS);
        assertThat(testOrderProcessStatusHistory.getStatusUpdatedDate()).isEqualTo(UPDATED_STATUS_UPDATED_DATE);
        assertThat(testOrderProcessStatusHistory.getProcessName()).isEqualTo(UPDATED_PROCESS_NAME);
        assertThat(testOrderProcessStatusHistory.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOrderProcessStatusHistory.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testOrderProcessStatusHistory.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOrderProcessStatusHistory.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOrderProcessStatusHistory.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testOrderProcessStatusHistory.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testOrderProcessStatusHistory.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingOrderProcessStatusHistory() throws Exception {
        int databaseSizeBeforeUpdate = orderProcessStatusHistoryRepository.findAll().size();

        // Create the OrderProcessStatusHistory

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderProcessStatusHistoryMockMvc.perform(put("/api/order-process-status-histories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderProcessStatusHistory)))
            .andExpect(status().isBadRequest());

        // Validate the OrderProcessStatusHistory in the database
        List<OrderProcessStatusHistory> orderProcessStatusHistoryList = orderProcessStatusHistoryRepository.findAll();
        assertThat(orderProcessStatusHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOrderProcessStatusHistory() throws Exception {
        // Initialize the database
        orderProcessStatusHistoryService.save(orderProcessStatusHistory);

        int databaseSizeBeforeDelete = orderProcessStatusHistoryRepository.findAll().size();

        // Delete the orderProcessStatusHistory
        restOrderProcessStatusHistoryMockMvc.perform(delete("/api/order-process-status-histories/{id}", orderProcessStatusHistory.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderProcessStatusHistory> orderProcessStatusHistoryList = orderProcessStatusHistoryRepository.findAll();
        assertThat(orderProcessStatusHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
