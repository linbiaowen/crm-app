package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.GroupMembers;
import com.hthk.crm.repository.GroupMembersRepository;
import com.hthk.crm.service.GroupMembersService;
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

import com.hthk.crm.domain.enumeration.GroupRole;
/**
 * Integration tests for the {@link GroupMembersResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class GroupMembersResourceIT {

    private static final Long DEFAULT_GROUP_ID = 1L;
    private static final Long UPDATED_GROUP_ID = 2L;

    private static final String DEFAULT_SUBSCRIPTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MSISDN = "AAAAAAAAAA";
    private static final String UPDATED_MSISDN = "BBBBBBBBBB";

    private static final GroupRole DEFAULT_GROUP_ROLE = GroupRole.ADMIN;
    private static final GroupRole UPDATED_GROUP_ROLE = GroupRole.MEMBER;

    private static final String DEFAULT_END_REASON_CODE = "AAAAAAAAAA";
    private static final String UPDATED_END_REASON_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

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
    private GroupMembersRepository groupMembersRepository;

    @Autowired
    private GroupMembersService groupMembersService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restGroupMembersMockMvc;

    private GroupMembers groupMembers;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GroupMembersResource groupMembersResource = new GroupMembersResource(groupMembersService);
        this.restGroupMembersMockMvc = MockMvcBuilders.standaloneSetup(groupMembersResource)
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
    public static GroupMembers createEntity() {
        GroupMembers groupMembers = new GroupMembers()
            .groupId(DEFAULT_GROUP_ID)
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .msisdn(DEFAULT_MSISDN)
            .groupRole(DEFAULT_GROUP_ROLE)
            .endReasonCode(DEFAULT_END_REASON_CODE)
            .remarks(DEFAULT_REMARKS)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return groupMembers;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GroupMembers createUpdatedEntity() {
        GroupMembers groupMembers = new GroupMembers()
            .groupId(UPDATED_GROUP_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .msisdn(UPDATED_MSISDN)
            .groupRole(UPDATED_GROUP_ROLE)
            .endReasonCode(UPDATED_END_REASON_CODE)
            .remarks(UPDATED_REMARKS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return groupMembers;
    }

    @BeforeEach
    public void initTest() {
        groupMembersRepository.deleteAll();
        groupMembers = createEntity();
    }

    @Test
    public void createGroupMembers() throws Exception {
        int databaseSizeBeforeCreate = groupMembersRepository.findAll().size();

        // Create the GroupMembers
        restGroupMembersMockMvc.perform(post("/api/group-members")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMembers)))
            .andExpect(status().isCreated());

        // Validate the GroupMembers in the database
        List<GroupMembers> groupMembersList = groupMembersRepository.findAll();
        assertThat(groupMembersList).hasSize(databaseSizeBeforeCreate + 1);
        GroupMembers testGroupMembers = groupMembersList.get(groupMembersList.size() - 1);
        assertThat(testGroupMembers.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testGroupMembers.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testGroupMembers.getMsisdn()).isEqualTo(DEFAULT_MSISDN);
        assertThat(testGroupMembers.getGroupRole()).isEqualTo(DEFAULT_GROUP_ROLE);
        assertThat(testGroupMembers.getEndReasonCode()).isEqualTo(DEFAULT_END_REASON_CODE);
        assertThat(testGroupMembers.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testGroupMembers.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testGroupMembers.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testGroupMembers.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testGroupMembers.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testGroupMembers.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testGroupMembers.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testGroupMembers.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createGroupMembersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groupMembersRepository.findAll().size();

        // Create the GroupMembers with an existing ID
        groupMembers.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupMembersMockMvc.perform(post("/api/group-members")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMembers)))
            .andExpect(status().isBadRequest());

        // Validate the GroupMembers in the database
        List<GroupMembers> groupMembersList = groupMembersRepository.findAll();
        assertThat(groupMembersList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMembersRepository.findAll().size();
        // set the field null
        groupMembers.setGroupId(null);

        // Create the GroupMembers, which fails.

        restGroupMembersMockMvc.perform(post("/api/group-members")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMembers)))
            .andExpect(status().isBadRequest());

        List<GroupMembers> groupMembersList = groupMembersRepository.findAll();
        assertThat(groupMembersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSubscriptionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMembersRepository.findAll().size();
        // set the field null
        groupMembers.setSubscriptionId(null);

        // Create the GroupMembers, which fails.

        restGroupMembersMockMvc.perform(post("/api/group-members")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMembers)))
            .andExpect(status().isBadRequest());

        List<GroupMembers> groupMembersList = groupMembersRepository.findAll();
        assertThat(groupMembersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkMsisdnIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMembersRepository.findAll().size();
        // set the field null
        groupMembers.setMsisdn(null);

        // Create the GroupMembers, which fails.

        restGroupMembersMockMvc.perform(post("/api/group-members")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMembers)))
            .andExpect(status().isBadRequest());

        List<GroupMembers> groupMembersList = groupMembersRepository.findAll();
        assertThat(groupMembersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkGroupRoleIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMembersRepository.findAll().size();
        // set the field null
        groupMembers.setGroupRole(null);

        // Create the GroupMembers, which fails.

        restGroupMembersMockMvc.perform(post("/api/group-members")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMembers)))
            .andExpect(status().isBadRequest());

        List<GroupMembers> groupMembersList = groupMembersRepository.findAll();
        assertThat(groupMembersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMembersRepository.findAll().size();
        // set the field null
        groupMembers.setCreatedDate(null);

        // Create the GroupMembers, which fails.

        restGroupMembersMockMvc.perform(post("/api/group-members")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMembers)))
            .andExpect(status().isBadRequest());

        List<GroupMembers> groupMembersList = groupMembersRepository.findAll();
        assertThat(groupMembersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMembersRepository.findAll().size();
        // set the field null
        groupMembers.setCreatedBy(null);

        // Create the GroupMembers, which fails.

        restGroupMembersMockMvc.perform(post("/api/group-members")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMembers)))
            .andExpect(status().isBadRequest());

        List<GroupMembers> groupMembersList = groupMembersRepository.findAll();
        assertThat(groupMembersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMembersRepository.findAll().size();
        // set the field null
        groupMembers.setLastUpdatedDate(null);

        // Create the GroupMembers, which fails.

        restGroupMembersMockMvc.perform(post("/api/group-members")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMembers)))
            .andExpect(status().isBadRequest());

        List<GroupMembers> groupMembersList = groupMembersRepository.findAll();
        assertThat(groupMembersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMembersRepository.findAll().size();
        // set the field null
        groupMembers.setLastUpdatedBy(null);

        // Create the GroupMembers, which fails.

        restGroupMembersMockMvc.perform(post("/api/group-members")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMembers)))
            .andExpect(status().isBadRequest());

        List<GroupMembers> groupMembersList = groupMembersRepository.findAll();
        assertThat(groupMembersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupMembersRepository.findAll().size();
        // set the field null
        groupMembers.setTenantId(null);

        // Create the GroupMembers, which fails.

        restGroupMembersMockMvc.perform(post("/api/group-members")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMembers)))
            .andExpect(status().isBadRequest());

        List<GroupMembers> groupMembersList = groupMembersRepository.findAll();
        assertThat(groupMembersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllGroupMembers() throws Exception {
        // Initialize the database
        groupMembersRepository.save(groupMembers);

        // Get all the groupMembersList
        restGroupMembersMockMvc.perform(get("/api/group-members?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groupMembers.getId())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID.intValue())))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID)))
            .andExpect(jsonPath("$.[*].msisdn").value(hasItem(DEFAULT_MSISDN)))
            .andExpect(jsonPath("$.[*].groupRole").value(hasItem(DEFAULT_GROUP_ROLE.toString())))
            .andExpect(jsonPath("$.[*].endReasonCode").value(hasItem(DEFAULT_END_REASON_CODE)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getGroupMembers() throws Exception {
        // Initialize the database
        groupMembersRepository.save(groupMembers);

        // Get the groupMembers
        restGroupMembersMockMvc.perform(get("/api/group-members/{id}", groupMembers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(groupMembers.getId()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID.intValue()))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID))
            .andExpect(jsonPath("$.msisdn").value(DEFAULT_MSISDN))
            .andExpect(jsonPath("$.groupRole").value(DEFAULT_GROUP_ROLE.toString()))
            .andExpect(jsonPath("$.endReasonCode").value(DEFAULT_END_REASON_CODE))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingGroupMembers() throws Exception {
        // Get the groupMembers
        restGroupMembersMockMvc.perform(get("/api/group-members/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateGroupMembers() throws Exception {
        // Initialize the database
        groupMembersService.save(groupMembers);

        int databaseSizeBeforeUpdate = groupMembersRepository.findAll().size();

        // Update the groupMembers
        GroupMembers updatedGroupMembers = groupMembersRepository.findById(groupMembers.getId()).get();
        updatedGroupMembers
            .groupId(UPDATED_GROUP_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .msisdn(UPDATED_MSISDN)
            .groupRole(UPDATED_GROUP_ROLE)
            .endReasonCode(UPDATED_END_REASON_CODE)
            .remarks(UPDATED_REMARKS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restGroupMembersMockMvc.perform(put("/api/group-members")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedGroupMembers)))
            .andExpect(status().isOk());

        // Validate the GroupMembers in the database
        List<GroupMembers> groupMembersList = groupMembersRepository.findAll();
        assertThat(groupMembersList).hasSize(databaseSizeBeforeUpdate);
        GroupMembers testGroupMembers = groupMembersList.get(groupMembersList.size() - 1);
        assertThat(testGroupMembers.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testGroupMembers.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testGroupMembers.getMsisdn()).isEqualTo(UPDATED_MSISDN);
        assertThat(testGroupMembers.getGroupRole()).isEqualTo(UPDATED_GROUP_ROLE);
        assertThat(testGroupMembers.getEndReasonCode()).isEqualTo(UPDATED_END_REASON_CODE);
        assertThat(testGroupMembers.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testGroupMembers.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testGroupMembers.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testGroupMembers.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testGroupMembers.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testGroupMembers.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testGroupMembers.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testGroupMembers.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingGroupMembers() throws Exception {
        int databaseSizeBeforeUpdate = groupMembersRepository.findAll().size();

        // Create the GroupMembers

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupMembersMockMvc.perform(put("/api/group-members")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupMembers)))
            .andExpect(status().isBadRequest());

        // Validate the GroupMembers in the database
        List<GroupMembers> groupMembersList = groupMembersRepository.findAll();
        assertThat(groupMembersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteGroupMembers() throws Exception {
        // Initialize the database
        groupMembersService.save(groupMembers);

        int databaseSizeBeforeDelete = groupMembersRepository.findAll().size();

        // Delete the groupMembers
        restGroupMembersMockMvc.perform(delete("/api/group-members/{id}", groupMembers.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GroupMembers> groupMembersList = groupMembersRepository.findAll();
        assertThat(groupMembersList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
