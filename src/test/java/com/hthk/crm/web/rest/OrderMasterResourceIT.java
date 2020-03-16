package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.OrderMaster;
import com.hthk.crm.repository.OrderMasterRepository;
import com.hthk.crm.service.OrderMasterService;
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

import com.hthk.crm.domain.enumeration.OrderType;
import com.hthk.crm.domain.enumeration.SubOrderType;
import com.hthk.crm.domain.enumeration.OrderNature;
import com.hthk.crm.domain.enumeration.OrderStatus;
/**
 * Integration tests for the {@link OrderMasterResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class OrderMasterResourceIT {

    private static final String DEFAULT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_ACCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUST_ACCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIPTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_ID = "BBBBBBBBBB";

    private static final OrderType DEFAULT_ORDER_TYPE = OrderType.PURCHASE_OFFER;
    private static final OrderType UPDATED_ORDER_TYPE = OrderType.CANCEL_OFFER;

    private static final SubOrderType DEFAULT_SUB_ORDER_TYPE = SubOrderType.OFFER;
    private static final SubOrderType UPDATED_SUB_ORDER_TYPE = SubOrderType.SERVICE;

    private static final OrderNature DEFAULT_ORDER_NATURE = OrderNature.NORMAL;
    private static final OrderNature UPDATED_ORDER_NATURE = OrderNature.MNP;

    private static final Boolean DEFAULT_IS_CHANGE_PLAN = false;
    private static final Boolean UPDATED_IS_CHANGE_PLAN = true;

    private static final OrderStatus DEFAULT_ORDER_STATUS = OrderStatus.NEW;
    private static final OrderStatus UPDATED_ORDER_STATUS = OrderStatus.PAYMENT_CONFIRMED;

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
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restOrderMasterMockMvc;

    private OrderMaster orderMaster;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderMasterResource orderMasterResource = new OrderMasterResource(orderMasterService);
        this.restOrderMasterMockMvc = MockMvcBuilders.standaloneSetup(orderMasterResource)
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
    public static OrderMaster createEntity() {
        OrderMaster orderMaster = new OrderMaster()
            .orderId(DEFAULT_ORDER_ID)
            .custAcctId(DEFAULT_CUST_ACCT_ID)
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .orderType(DEFAULT_ORDER_TYPE)
            .subOrderType(DEFAULT_SUB_ORDER_TYPE)
            .orderNature(DEFAULT_ORDER_NATURE)
            .isChangePlan(DEFAULT_IS_CHANGE_PLAN)
            .orderStatus(DEFAULT_ORDER_STATUS)
            .remarks(DEFAULT_REMARKS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return orderMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderMaster createUpdatedEntity() {
        OrderMaster orderMaster = new OrderMaster()
            .orderId(UPDATED_ORDER_ID)
            .custAcctId(UPDATED_CUST_ACCT_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .orderType(UPDATED_ORDER_TYPE)
            .subOrderType(UPDATED_SUB_ORDER_TYPE)
            .orderNature(UPDATED_ORDER_NATURE)
            .isChangePlan(UPDATED_IS_CHANGE_PLAN)
            .orderStatus(UPDATED_ORDER_STATUS)
            .remarks(UPDATED_REMARKS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return orderMaster;
    }

    @BeforeEach
    public void initTest() {
        orderMasterRepository.deleteAll();
        orderMaster = createEntity();
    }

    @Test
    public void createOrderMaster() throws Exception {
        int databaseSizeBeforeCreate = orderMasterRepository.findAll().size();

        // Create the OrderMaster
        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderMaster)))
            .andExpect(status().isCreated());

        // Validate the OrderMaster in the database
        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeCreate + 1);
        OrderMaster testOrderMaster = orderMasterList.get(orderMasterList.size() - 1);
        assertThat(testOrderMaster.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testOrderMaster.getCustAcctId()).isEqualTo(DEFAULT_CUST_ACCT_ID);
        assertThat(testOrderMaster.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testOrderMaster.getOrderType()).isEqualTo(DEFAULT_ORDER_TYPE);
        assertThat(testOrderMaster.getSubOrderType()).isEqualTo(DEFAULT_SUB_ORDER_TYPE);
        assertThat(testOrderMaster.getOrderNature()).isEqualTo(DEFAULT_ORDER_NATURE);
        assertThat(testOrderMaster.isIsChangePlan()).isEqualTo(DEFAULT_IS_CHANGE_PLAN);
        assertThat(testOrderMaster.getOrderStatus()).isEqualTo(DEFAULT_ORDER_STATUS);
        assertThat(testOrderMaster.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testOrderMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOrderMaster.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOrderMaster.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testOrderMaster.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testOrderMaster.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createOrderMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderMasterRepository.findAll().size();

        // Create the OrderMaster with an existing ID
        orderMaster.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderMaster)))
            .andExpect(status().isBadRequest());

        // Validate the OrderMaster in the database
        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkOrderIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderMasterRepository.findAll().size();
        // set the field null
        orderMaster.setOrderId(null);

        // Create the OrderMaster, which fails.

        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderMaster)))
            .andExpect(status().isBadRequest());

        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCustAcctIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderMasterRepository.findAll().size();
        // set the field null
        orderMaster.setCustAcctId(null);

        // Create the OrderMaster, which fails.

        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderMaster)))
            .andExpect(status().isBadRequest());

        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSubscriptionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderMasterRepository.findAll().size();
        // set the field null
        orderMaster.setSubscriptionId(null);

        // Create the OrderMaster, which fails.

        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderMaster)))
            .andExpect(status().isBadRequest());

        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOrderTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderMasterRepository.findAll().size();
        // set the field null
        orderMaster.setOrderType(null);

        // Create the OrderMaster, which fails.

        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderMaster)))
            .andExpect(status().isBadRequest());

        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSubOrderTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderMasterRepository.findAll().size();
        // set the field null
        orderMaster.setSubOrderType(null);

        // Create the OrderMaster, which fails.

        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderMaster)))
            .andExpect(status().isBadRequest());

        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOrderStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderMasterRepository.findAll().size();
        // set the field null
        orderMaster.setOrderStatus(null);

        // Create the OrderMaster, which fails.

        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderMaster)))
            .andExpect(status().isBadRequest());

        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderMasterRepository.findAll().size();
        // set the field null
        orderMaster.setCreatedDate(null);

        // Create the OrderMaster, which fails.

        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderMaster)))
            .andExpect(status().isBadRequest());

        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderMasterRepository.findAll().size();
        // set the field null
        orderMaster.setCreatedBy(null);

        // Create the OrderMaster, which fails.

        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderMaster)))
            .andExpect(status().isBadRequest());

        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderMasterRepository.findAll().size();
        // set the field null
        orderMaster.setLastUpdatedDate(null);

        // Create the OrderMaster, which fails.

        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderMaster)))
            .andExpect(status().isBadRequest());

        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderMasterRepository.findAll().size();
        // set the field null
        orderMaster.setLastUpdatedBy(null);

        // Create the OrderMaster, which fails.

        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderMaster)))
            .andExpect(status().isBadRequest());

        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderMasterRepository.findAll().size();
        // set the field null
        orderMaster.setTenantId(null);

        // Create the OrderMaster, which fails.

        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderMaster)))
            .andExpect(status().isBadRequest());

        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllOrderMasters() throws Exception {
        // Initialize the database
        orderMasterRepository.save(orderMaster);

        // Get all the orderMasterList
        restOrderMasterMockMvc.perform(get("/api/order-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderMaster.getId())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID)))
            .andExpect(jsonPath("$.[*].custAcctId").value(hasItem(DEFAULT_CUST_ACCT_ID)))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID)))
            .andExpect(jsonPath("$.[*].orderType").value(hasItem(DEFAULT_ORDER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].subOrderType").value(hasItem(DEFAULT_SUB_ORDER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].orderNature").value(hasItem(DEFAULT_ORDER_NATURE.toString())))
            .andExpect(jsonPath("$.[*].isChangePlan").value(hasItem(DEFAULT_IS_CHANGE_PLAN.booleanValue())))
            .andExpect(jsonPath("$.[*].orderStatus").value(hasItem(DEFAULT_ORDER_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getOrderMaster() throws Exception {
        // Initialize the database
        orderMasterRepository.save(orderMaster);

        // Get the orderMaster
        restOrderMasterMockMvc.perform(get("/api/order-masters/{id}", orderMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderMaster.getId()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID))
            .andExpect(jsonPath("$.custAcctId").value(DEFAULT_CUST_ACCT_ID))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID))
            .andExpect(jsonPath("$.orderType").value(DEFAULT_ORDER_TYPE.toString()))
            .andExpect(jsonPath("$.subOrderType").value(DEFAULT_SUB_ORDER_TYPE.toString()))
            .andExpect(jsonPath("$.orderNature").value(DEFAULT_ORDER_NATURE.toString()))
            .andExpect(jsonPath("$.isChangePlan").value(DEFAULT_IS_CHANGE_PLAN.booleanValue()))
            .andExpect(jsonPath("$.orderStatus").value(DEFAULT_ORDER_STATUS.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingOrderMaster() throws Exception {
        // Get the orderMaster
        restOrderMasterMockMvc.perform(get("/api/order-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOrderMaster() throws Exception {
        // Initialize the database
        orderMasterService.save(orderMaster);

        int databaseSizeBeforeUpdate = orderMasterRepository.findAll().size();

        // Update the orderMaster
        OrderMaster updatedOrderMaster = orderMasterRepository.findById(orderMaster.getId()).get();
        updatedOrderMaster
            .orderId(UPDATED_ORDER_ID)
            .custAcctId(UPDATED_CUST_ACCT_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .orderType(UPDATED_ORDER_TYPE)
            .subOrderType(UPDATED_SUB_ORDER_TYPE)
            .orderNature(UPDATED_ORDER_NATURE)
            .isChangePlan(UPDATED_IS_CHANGE_PLAN)
            .orderStatus(UPDATED_ORDER_STATUS)
            .remarks(UPDATED_REMARKS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restOrderMasterMockMvc.perform(put("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrderMaster)))
            .andExpect(status().isOk());

        // Validate the OrderMaster in the database
        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeUpdate);
        OrderMaster testOrderMaster = orderMasterList.get(orderMasterList.size() - 1);
        assertThat(testOrderMaster.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testOrderMaster.getCustAcctId()).isEqualTo(UPDATED_CUST_ACCT_ID);
        assertThat(testOrderMaster.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testOrderMaster.getOrderType()).isEqualTo(UPDATED_ORDER_TYPE);
        assertThat(testOrderMaster.getSubOrderType()).isEqualTo(UPDATED_SUB_ORDER_TYPE);
        assertThat(testOrderMaster.getOrderNature()).isEqualTo(UPDATED_ORDER_NATURE);
        assertThat(testOrderMaster.isIsChangePlan()).isEqualTo(UPDATED_IS_CHANGE_PLAN);
        assertThat(testOrderMaster.getOrderStatus()).isEqualTo(UPDATED_ORDER_STATUS);
        assertThat(testOrderMaster.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testOrderMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOrderMaster.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOrderMaster.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testOrderMaster.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testOrderMaster.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingOrderMaster() throws Exception {
        int databaseSizeBeforeUpdate = orderMasterRepository.findAll().size();

        // Create the OrderMaster

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderMasterMockMvc.perform(put("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderMaster)))
            .andExpect(status().isBadRequest());

        // Validate the OrderMaster in the database
        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOrderMaster() throws Exception {
        // Initialize the database
        orderMasterService.save(orderMaster);

        int databaseSizeBeforeDelete = orderMasterRepository.findAll().size();

        // Delete the orderMaster
        restOrderMasterMockMvc.perform(delete("/api/order-masters/{id}", orderMaster.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
