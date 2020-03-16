package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.SubscriptionGroup;
import com.hthk.crm.repository.SubscriptionGroupRepository;
import com.hthk.crm.service.SubscriptionGroupService;
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
 * Integration tests for the {@link SubscriptionGroupResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class SubscriptionGroupResourceIT {

    private static final Long DEFAULT_GROUP_ID = 1L;
    private static final Long UPDATED_GROUP_ID = 2L;

    private static final String DEFAULT_CUST_ACCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUST_ACCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_NAME = "BBBBBBBBBB";

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
    private SubscriptionGroupRepository subscriptionGroupRepository;

    @Autowired
    private SubscriptionGroupService subscriptionGroupService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restSubscriptionGroupMockMvc;

    private SubscriptionGroup subscriptionGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SubscriptionGroupResource subscriptionGroupResource = new SubscriptionGroupResource(subscriptionGroupService);
        this.restSubscriptionGroupMockMvc = MockMvcBuilders.standaloneSetup(subscriptionGroupResource)
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
    public static SubscriptionGroup createEntity() {
        SubscriptionGroup subscriptionGroup = new SubscriptionGroup()
            .groupId(DEFAULT_GROUP_ID)
            .custAcctId(DEFAULT_CUST_ACCT_ID)
            .groupType(DEFAULT_GROUP_TYPE)
            .groupName(DEFAULT_GROUP_NAME)
            .status(DEFAULT_STATUS)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return subscriptionGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubscriptionGroup createUpdatedEntity() {
        SubscriptionGroup subscriptionGroup = new SubscriptionGroup()
            .groupId(UPDATED_GROUP_ID)
            .custAcctId(UPDATED_CUST_ACCT_ID)
            .groupType(UPDATED_GROUP_TYPE)
            .groupName(UPDATED_GROUP_NAME)
            .status(UPDATED_STATUS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return subscriptionGroup;
    }

    @BeforeEach
    public void initTest() {
        subscriptionGroupRepository.deleteAll();
        subscriptionGroup = createEntity();
    }

    @Test
    public void createSubscriptionGroup() throws Exception {
        int databaseSizeBeforeCreate = subscriptionGroupRepository.findAll().size();

        // Create the SubscriptionGroup
        restSubscriptionGroupMockMvc.perform(post("/api/subscription-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionGroup)))
            .andExpect(status().isCreated());

        // Validate the SubscriptionGroup in the database
        List<SubscriptionGroup> subscriptionGroupList = subscriptionGroupRepository.findAll();
        assertThat(subscriptionGroupList).hasSize(databaseSizeBeforeCreate + 1);
        SubscriptionGroup testSubscriptionGroup = subscriptionGroupList.get(subscriptionGroupList.size() - 1);
        assertThat(testSubscriptionGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testSubscriptionGroup.getCustAcctId()).isEqualTo(DEFAULT_CUST_ACCT_ID);
        assertThat(testSubscriptionGroup.getGroupType()).isEqualTo(DEFAULT_GROUP_TYPE);
        assertThat(testSubscriptionGroup.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
        assertThat(testSubscriptionGroup.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSubscriptionGroup.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testSubscriptionGroup.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testSubscriptionGroup.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSubscriptionGroup.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSubscriptionGroup.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testSubscriptionGroup.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testSubscriptionGroup.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createSubscriptionGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subscriptionGroupRepository.findAll().size();

        // Create the SubscriptionGroup with an existing ID
        subscriptionGroup.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubscriptionGroupMockMvc.perform(post("/api/subscription-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionGroup)))
            .andExpect(status().isBadRequest());

        // Validate the SubscriptionGroup in the database
        List<SubscriptionGroup> subscriptionGroupList = subscriptionGroupRepository.findAll();
        assertThat(subscriptionGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionGroupRepository.findAll().size();
        // set the field null
        subscriptionGroup.setGroupId(null);

        // Create the SubscriptionGroup, which fails.

        restSubscriptionGroupMockMvc.perform(post("/api/subscription-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionGroup)))
            .andExpect(status().isBadRequest());

        List<SubscriptionGroup> subscriptionGroupList = subscriptionGroupRepository.findAll();
        assertThat(subscriptionGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCustAcctIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionGroupRepository.findAll().size();
        // set the field null
        subscriptionGroup.setCustAcctId(null);

        // Create the SubscriptionGroup, which fails.

        restSubscriptionGroupMockMvc.perform(post("/api/subscription-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionGroup)))
            .andExpect(status().isBadRequest());

        List<SubscriptionGroup> subscriptionGroupList = subscriptionGroupRepository.findAll();
        assertThat(subscriptionGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkGroupTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionGroupRepository.findAll().size();
        // set the field null
        subscriptionGroup.setGroupType(null);

        // Create the SubscriptionGroup, which fails.

        restSubscriptionGroupMockMvc.perform(post("/api/subscription-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionGroup)))
            .andExpect(status().isBadRequest());

        List<SubscriptionGroup> subscriptionGroupList = subscriptionGroupRepository.findAll();
        assertThat(subscriptionGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionGroupRepository.findAll().size();
        // set the field null
        subscriptionGroup.setStatus(null);

        // Create the SubscriptionGroup, which fails.

        restSubscriptionGroupMockMvc.perform(post("/api/subscription-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionGroup)))
            .andExpect(status().isBadRequest());

        List<SubscriptionGroup> subscriptionGroupList = subscriptionGroupRepository.findAll();
        assertThat(subscriptionGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionGroupRepository.findAll().size();
        // set the field null
        subscriptionGroup.setCreatedDate(null);

        // Create the SubscriptionGroup, which fails.

        restSubscriptionGroupMockMvc.perform(post("/api/subscription-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionGroup)))
            .andExpect(status().isBadRequest());

        List<SubscriptionGroup> subscriptionGroupList = subscriptionGroupRepository.findAll();
        assertThat(subscriptionGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionGroupRepository.findAll().size();
        // set the field null
        subscriptionGroup.setCreatedBy(null);

        // Create the SubscriptionGroup, which fails.

        restSubscriptionGroupMockMvc.perform(post("/api/subscription-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionGroup)))
            .andExpect(status().isBadRequest());

        List<SubscriptionGroup> subscriptionGroupList = subscriptionGroupRepository.findAll();
        assertThat(subscriptionGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionGroupRepository.findAll().size();
        // set the field null
        subscriptionGroup.setLastUpdatedDate(null);

        // Create the SubscriptionGroup, which fails.

        restSubscriptionGroupMockMvc.perform(post("/api/subscription-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionGroup)))
            .andExpect(status().isBadRequest());

        List<SubscriptionGroup> subscriptionGroupList = subscriptionGroupRepository.findAll();
        assertThat(subscriptionGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionGroupRepository.findAll().size();
        // set the field null
        subscriptionGroup.setLastUpdatedBy(null);

        // Create the SubscriptionGroup, which fails.

        restSubscriptionGroupMockMvc.perform(post("/api/subscription-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionGroup)))
            .andExpect(status().isBadRequest());

        List<SubscriptionGroup> subscriptionGroupList = subscriptionGroupRepository.findAll();
        assertThat(subscriptionGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptionGroupRepository.findAll().size();
        // set the field null
        subscriptionGroup.setTenantId(null);

        // Create the SubscriptionGroup, which fails.

        restSubscriptionGroupMockMvc.perform(post("/api/subscription-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionGroup)))
            .andExpect(status().isBadRequest());

        List<SubscriptionGroup> subscriptionGroupList = subscriptionGroupRepository.findAll();
        assertThat(subscriptionGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllSubscriptionGroups() throws Exception {
        // Initialize the database
        subscriptionGroupRepository.save(subscriptionGroup);

        // Get all the subscriptionGroupList
        restSubscriptionGroupMockMvc.perform(get("/api/subscription-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subscriptionGroup.getId())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID.intValue())))
            .andExpect(jsonPath("$.[*].custAcctId").value(hasItem(DEFAULT_CUST_ACCT_ID)))
            .andExpect(jsonPath("$.[*].groupType").value(hasItem(DEFAULT_GROUP_TYPE)))
            .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME)))
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
    public void getSubscriptionGroup() throws Exception {
        // Initialize the database
        subscriptionGroupRepository.save(subscriptionGroup);

        // Get the subscriptionGroup
        restSubscriptionGroupMockMvc.perform(get("/api/subscription-groups/{id}", subscriptionGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subscriptionGroup.getId()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID.intValue()))
            .andExpect(jsonPath("$.custAcctId").value(DEFAULT_CUST_ACCT_ID))
            .andExpect(jsonPath("$.groupType").value(DEFAULT_GROUP_TYPE))
            .andExpect(jsonPath("$.groupName").value(DEFAULT_GROUP_NAME))
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
    public void getNonExistingSubscriptionGroup() throws Exception {
        // Get the subscriptionGroup
        restSubscriptionGroupMockMvc.perform(get("/api/subscription-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateSubscriptionGroup() throws Exception {
        // Initialize the database
        subscriptionGroupService.save(subscriptionGroup);

        int databaseSizeBeforeUpdate = subscriptionGroupRepository.findAll().size();

        // Update the subscriptionGroup
        SubscriptionGroup updatedSubscriptionGroup = subscriptionGroupRepository.findById(subscriptionGroup.getId()).get();
        updatedSubscriptionGroup
            .groupId(UPDATED_GROUP_ID)
            .custAcctId(UPDATED_CUST_ACCT_ID)
            .groupType(UPDATED_GROUP_TYPE)
            .groupName(UPDATED_GROUP_NAME)
            .status(UPDATED_STATUS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restSubscriptionGroupMockMvc.perform(put("/api/subscription-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSubscriptionGroup)))
            .andExpect(status().isOk());

        // Validate the SubscriptionGroup in the database
        List<SubscriptionGroup> subscriptionGroupList = subscriptionGroupRepository.findAll();
        assertThat(subscriptionGroupList).hasSize(databaseSizeBeforeUpdate);
        SubscriptionGroup testSubscriptionGroup = subscriptionGroupList.get(subscriptionGroupList.size() - 1);
        assertThat(testSubscriptionGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testSubscriptionGroup.getCustAcctId()).isEqualTo(UPDATED_CUST_ACCT_ID);
        assertThat(testSubscriptionGroup.getGroupType()).isEqualTo(UPDATED_GROUP_TYPE);
        assertThat(testSubscriptionGroup.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
        assertThat(testSubscriptionGroup.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSubscriptionGroup.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testSubscriptionGroup.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testSubscriptionGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSubscriptionGroup.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSubscriptionGroup.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testSubscriptionGroup.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testSubscriptionGroup.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingSubscriptionGroup() throws Exception {
        int databaseSizeBeforeUpdate = subscriptionGroupRepository.findAll().size();

        // Create the SubscriptionGroup

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubscriptionGroupMockMvc.perform(put("/api/subscription-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionGroup)))
            .andExpect(status().isBadRequest());

        // Validate the SubscriptionGroup in the database
        List<SubscriptionGroup> subscriptionGroupList = subscriptionGroupRepository.findAll();
        assertThat(subscriptionGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteSubscriptionGroup() throws Exception {
        // Initialize the database
        subscriptionGroupService.save(subscriptionGroup);

        int databaseSizeBeforeDelete = subscriptionGroupRepository.findAll().size();

        // Delete the subscriptionGroup
        restSubscriptionGroupMockMvc.perform(delete("/api/subscription-groups/{id}", subscriptionGroup.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SubscriptionGroup> subscriptionGroupList = subscriptionGroupRepository.findAll();
        assertThat(subscriptionGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
