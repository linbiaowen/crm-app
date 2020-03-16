package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.SubsPurchaseControl;
import com.hthk.crm.repository.SubsPurchaseControlRepository;
import com.hthk.crm.service.SubsPurchaseControlService;
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

import com.hthk.crm.domain.enumeration.RecordStatus;
/**
 * Integration tests for the {@link SubsPurchaseControlResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class SubsPurchaseControlResourceIT {

    private static final String DEFAULT_SUBSCRIPTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PURCHASE_CONTROL_FLAG = "AAAAAAAAAA";
    private static final String UPDATED_PURCHASE_CONTROL_FLAG = "BBBBBBBBBB";

    private static final RecordStatus DEFAULT_STATUS = RecordStatus.INACTIVE;
    private static final RecordStatus UPDATED_STATUS = RecordStatus.ACTIVE;

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
    private SubsPurchaseControlRepository subsPurchaseControlRepository;

    @Autowired
    private SubsPurchaseControlService subsPurchaseControlService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restSubsPurchaseControlMockMvc;

    private SubsPurchaseControl subsPurchaseControl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SubsPurchaseControlResource subsPurchaseControlResource = new SubsPurchaseControlResource(subsPurchaseControlService);
        this.restSubsPurchaseControlMockMvc = MockMvcBuilders.standaloneSetup(subsPurchaseControlResource)
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
    public static SubsPurchaseControl createEntity() {
        SubsPurchaseControl subsPurchaseControl = new SubsPurchaseControl()
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .serviceType(DEFAULT_SERVICE_TYPE)
            .purchaseControlFlag(DEFAULT_PURCHASE_CONTROL_FLAG)
            .status(DEFAULT_STATUS)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return subsPurchaseControl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubsPurchaseControl createUpdatedEntity() {
        SubsPurchaseControl subsPurchaseControl = new SubsPurchaseControl()
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .serviceType(UPDATED_SERVICE_TYPE)
            .purchaseControlFlag(UPDATED_PURCHASE_CONTROL_FLAG)
            .status(UPDATED_STATUS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return subsPurchaseControl;
    }

    @BeforeEach
    public void initTest() {
        subsPurchaseControlRepository.deleteAll();
        subsPurchaseControl = createEntity();
    }

    @Test
    public void createSubsPurchaseControl() throws Exception {
        int databaseSizeBeforeCreate = subsPurchaseControlRepository.findAll().size();

        // Create the SubsPurchaseControl
        restSubsPurchaseControlMockMvc.perform(post("/api/subs-purchase-controls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsPurchaseControl)))
            .andExpect(status().isCreated());

        // Validate the SubsPurchaseControl in the database
        List<SubsPurchaseControl> subsPurchaseControlList = subsPurchaseControlRepository.findAll();
        assertThat(subsPurchaseControlList).hasSize(databaseSizeBeforeCreate + 1);
        SubsPurchaseControl testSubsPurchaseControl = subsPurchaseControlList.get(subsPurchaseControlList.size() - 1);
        assertThat(testSubsPurchaseControl.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testSubsPurchaseControl.getServiceType()).isEqualTo(DEFAULT_SERVICE_TYPE);
        assertThat(testSubsPurchaseControl.getPurchaseControlFlag()).isEqualTo(DEFAULT_PURCHASE_CONTROL_FLAG);
        assertThat(testSubsPurchaseControl.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSubsPurchaseControl.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testSubsPurchaseControl.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testSubsPurchaseControl.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSubsPurchaseControl.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSubsPurchaseControl.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testSubsPurchaseControl.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testSubsPurchaseControl.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createSubsPurchaseControlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subsPurchaseControlRepository.findAll().size();

        // Create the SubsPurchaseControl with an existing ID
        subsPurchaseControl.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubsPurchaseControlMockMvc.perform(post("/api/subs-purchase-controls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsPurchaseControl)))
            .andExpect(status().isBadRequest());

        // Validate the SubsPurchaseControl in the database
        List<SubsPurchaseControl> subsPurchaseControlList = subsPurchaseControlRepository.findAll();
        assertThat(subsPurchaseControlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkSubscriptionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsPurchaseControlRepository.findAll().size();
        // set the field null
        subsPurchaseControl.setSubscriptionId(null);

        // Create the SubsPurchaseControl, which fails.

        restSubsPurchaseControlMockMvc.perform(post("/api/subs-purchase-controls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsPurchaseControl)))
            .andExpect(status().isBadRequest());

        List<SubsPurchaseControl> subsPurchaseControlList = subsPurchaseControlRepository.findAll();
        assertThat(subsPurchaseControlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkServiceTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsPurchaseControlRepository.findAll().size();
        // set the field null
        subsPurchaseControl.setServiceType(null);

        // Create the SubsPurchaseControl, which fails.

        restSubsPurchaseControlMockMvc.perform(post("/api/subs-purchase-controls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsPurchaseControl)))
            .andExpect(status().isBadRequest());

        List<SubsPurchaseControl> subsPurchaseControlList = subsPurchaseControlRepository.findAll();
        assertThat(subsPurchaseControlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkPurchaseControlFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsPurchaseControlRepository.findAll().size();
        // set the field null
        subsPurchaseControl.setPurchaseControlFlag(null);

        // Create the SubsPurchaseControl, which fails.

        restSubsPurchaseControlMockMvc.perform(post("/api/subs-purchase-controls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsPurchaseControl)))
            .andExpect(status().isBadRequest());

        List<SubsPurchaseControl> subsPurchaseControlList = subsPurchaseControlRepository.findAll();
        assertThat(subsPurchaseControlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsPurchaseControlRepository.findAll().size();
        // set the field null
        subsPurchaseControl.setStatus(null);

        // Create the SubsPurchaseControl, which fails.

        restSubsPurchaseControlMockMvc.perform(post("/api/subs-purchase-controls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsPurchaseControl)))
            .andExpect(status().isBadRequest());

        List<SubsPurchaseControl> subsPurchaseControlList = subsPurchaseControlRepository.findAll();
        assertThat(subsPurchaseControlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsPurchaseControlRepository.findAll().size();
        // set the field null
        subsPurchaseControl.setCreatedDate(null);

        // Create the SubsPurchaseControl, which fails.

        restSubsPurchaseControlMockMvc.perform(post("/api/subs-purchase-controls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsPurchaseControl)))
            .andExpect(status().isBadRequest());

        List<SubsPurchaseControl> subsPurchaseControlList = subsPurchaseControlRepository.findAll();
        assertThat(subsPurchaseControlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsPurchaseControlRepository.findAll().size();
        // set the field null
        subsPurchaseControl.setCreatedBy(null);

        // Create the SubsPurchaseControl, which fails.

        restSubsPurchaseControlMockMvc.perform(post("/api/subs-purchase-controls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsPurchaseControl)))
            .andExpect(status().isBadRequest());

        List<SubsPurchaseControl> subsPurchaseControlList = subsPurchaseControlRepository.findAll();
        assertThat(subsPurchaseControlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsPurchaseControlRepository.findAll().size();
        // set the field null
        subsPurchaseControl.setLastUpdatedDate(null);

        // Create the SubsPurchaseControl, which fails.

        restSubsPurchaseControlMockMvc.perform(post("/api/subs-purchase-controls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsPurchaseControl)))
            .andExpect(status().isBadRequest());

        List<SubsPurchaseControl> subsPurchaseControlList = subsPurchaseControlRepository.findAll();
        assertThat(subsPurchaseControlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsPurchaseControlRepository.findAll().size();
        // set the field null
        subsPurchaseControl.setLastUpdatedBy(null);

        // Create the SubsPurchaseControl, which fails.

        restSubsPurchaseControlMockMvc.perform(post("/api/subs-purchase-controls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsPurchaseControl)))
            .andExpect(status().isBadRequest());

        List<SubsPurchaseControl> subsPurchaseControlList = subsPurchaseControlRepository.findAll();
        assertThat(subsPurchaseControlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subsPurchaseControlRepository.findAll().size();
        // set the field null
        subsPurchaseControl.setTenantId(null);

        // Create the SubsPurchaseControl, which fails.

        restSubsPurchaseControlMockMvc.perform(post("/api/subs-purchase-controls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsPurchaseControl)))
            .andExpect(status().isBadRequest());

        List<SubsPurchaseControl> subsPurchaseControlList = subsPurchaseControlRepository.findAll();
        assertThat(subsPurchaseControlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllSubsPurchaseControls() throws Exception {
        // Initialize the database
        subsPurchaseControlRepository.save(subsPurchaseControl);

        // Get all the subsPurchaseControlList
        restSubsPurchaseControlMockMvc.perform(get("/api/subs-purchase-controls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subsPurchaseControl.getId())))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID)))
            .andExpect(jsonPath("$.[*].serviceType").value(hasItem(DEFAULT_SERVICE_TYPE)))
            .andExpect(jsonPath("$.[*].purchaseControlFlag").value(hasItem(DEFAULT_PURCHASE_CONTROL_FLAG)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getSubsPurchaseControl() throws Exception {
        // Initialize the database
        subsPurchaseControlRepository.save(subsPurchaseControl);

        // Get the subsPurchaseControl
        restSubsPurchaseControlMockMvc.perform(get("/api/subs-purchase-controls/{id}", subsPurchaseControl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subsPurchaseControl.getId()))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID))
            .andExpect(jsonPath("$.serviceType").value(DEFAULT_SERVICE_TYPE))
            .andExpect(jsonPath("$.purchaseControlFlag").value(DEFAULT_PURCHASE_CONTROL_FLAG))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingSubsPurchaseControl() throws Exception {
        // Get the subsPurchaseControl
        restSubsPurchaseControlMockMvc.perform(get("/api/subs-purchase-controls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateSubsPurchaseControl() throws Exception {
        // Initialize the database
        subsPurchaseControlService.save(subsPurchaseControl);

        int databaseSizeBeforeUpdate = subsPurchaseControlRepository.findAll().size();

        // Update the subsPurchaseControl
        SubsPurchaseControl updatedSubsPurchaseControl = subsPurchaseControlRepository.findById(subsPurchaseControl.getId()).get();
        updatedSubsPurchaseControl
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .serviceType(UPDATED_SERVICE_TYPE)
            .purchaseControlFlag(UPDATED_PURCHASE_CONTROL_FLAG)
            .status(UPDATED_STATUS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restSubsPurchaseControlMockMvc.perform(put("/api/subs-purchase-controls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSubsPurchaseControl)))
            .andExpect(status().isOk());

        // Validate the SubsPurchaseControl in the database
        List<SubsPurchaseControl> subsPurchaseControlList = subsPurchaseControlRepository.findAll();
        assertThat(subsPurchaseControlList).hasSize(databaseSizeBeforeUpdate);
        SubsPurchaseControl testSubsPurchaseControl = subsPurchaseControlList.get(subsPurchaseControlList.size() - 1);
        assertThat(testSubsPurchaseControl.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testSubsPurchaseControl.getServiceType()).isEqualTo(UPDATED_SERVICE_TYPE);
        assertThat(testSubsPurchaseControl.getPurchaseControlFlag()).isEqualTo(UPDATED_PURCHASE_CONTROL_FLAG);
        assertThat(testSubsPurchaseControl.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSubsPurchaseControl.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testSubsPurchaseControl.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testSubsPurchaseControl.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSubsPurchaseControl.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSubsPurchaseControl.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testSubsPurchaseControl.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testSubsPurchaseControl.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingSubsPurchaseControl() throws Exception {
        int databaseSizeBeforeUpdate = subsPurchaseControlRepository.findAll().size();

        // Create the SubsPurchaseControl

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubsPurchaseControlMockMvc.perform(put("/api/subs-purchase-controls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subsPurchaseControl)))
            .andExpect(status().isBadRequest());

        // Validate the SubsPurchaseControl in the database
        List<SubsPurchaseControl> subsPurchaseControlList = subsPurchaseControlRepository.findAll();
        assertThat(subsPurchaseControlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteSubsPurchaseControl() throws Exception {
        // Initialize the database
        subsPurchaseControlService.save(subsPurchaseControl);

        int databaseSizeBeforeDelete = subsPurchaseControlRepository.findAll().size();

        // Delete the subsPurchaseControl
        restSubsPurchaseControlMockMvc.perform(delete("/api/subs-purchase-controls/{id}", subsPurchaseControl.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SubsPurchaseControl> subsPurchaseControlList = subsPurchaseControlRepository.findAll();
        assertThat(subsPurchaseControlList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
