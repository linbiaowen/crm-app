package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.GroupEndReason;
import com.hthk.crm.repository.GroupEndReasonRepository;
import com.hthk.crm.service.GroupEndReasonService;
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
 * Integration tests for the {@link GroupEndReasonResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class GroupEndReasonResourceIT {

    private static final String DEFAULT_END_REASON_CODE = "AAAAAAAAAA";
    private static final String UPDATED_END_REASON_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_END_REASON = "AAAAAAAAAA";
    private static final String UPDATED_END_REASON = "BBBBBBBBBB";

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
    private GroupEndReasonRepository groupEndReasonRepository;

    @Autowired
    private GroupEndReasonService groupEndReasonService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restGroupEndReasonMockMvc;

    private GroupEndReason groupEndReason;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GroupEndReasonResource groupEndReasonResource = new GroupEndReasonResource(groupEndReasonService);
        this.restGroupEndReasonMockMvc = MockMvcBuilders.standaloneSetup(groupEndReasonResource)
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
    public static GroupEndReason createEntity() {
        GroupEndReason groupEndReason = new GroupEndReason()
            .endReasonCode(DEFAULT_END_REASON_CODE)
            .endReason(DEFAULT_END_REASON)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return groupEndReason;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GroupEndReason createUpdatedEntity() {
        GroupEndReason groupEndReason = new GroupEndReason()
            .endReasonCode(UPDATED_END_REASON_CODE)
            .endReason(UPDATED_END_REASON)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return groupEndReason;
    }

    @BeforeEach
    public void initTest() {
        groupEndReasonRepository.deleteAll();
        groupEndReason = createEntity();
    }

    @Test
    public void createGroupEndReason() throws Exception {
        int databaseSizeBeforeCreate = groupEndReasonRepository.findAll().size();

        // Create the GroupEndReason
        restGroupEndReasonMockMvc.perform(post("/api/group-end-reasons")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupEndReason)))
            .andExpect(status().isCreated());

        // Validate the GroupEndReason in the database
        List<GroupEndReason> groupEndReasonList = groupEndReasonRepository.findAll();
        assertThat(groupEndReasonList).hasSize(databaseSizeBeforeCreate + 1);
        GroupEndReason testGroupEndReason = groupEndReasonList.get(groupEndReasonList.size() - 1);
        assertThat(testGroupEndReason.getEndReasonCode()).isEqualTo(DEFAULT_END_REASON_CODE);
        assertThat(testGroupEndReason.getEndReason()).isEqualTo(DEFAULT_END_REASON);
        assertThat(testGroupEndReason.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testGroupEndReason.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testGroupEndReason.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testGroupEndReason.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testGroupEndReason.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testGroupEndReason.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testGroupEndReason.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createGroupEndReasonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groupEndReasonRepository.findAll().size();

        // Create the GroupEndReason with an existing ID
        groupEndReason.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupEndReasonMockMvc.perform(post("/api/group-end-reasons")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupEndReason)))
            .andExpect(status().isBadRequest());

        // Validate the GroupEndReason in the database
        List<GroupEndReason> groupEndReasonList = groupEndReasonRepository.findAll();
        assertThat(groupEndReasonList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkEndReasonCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupEndReasonRepository.findAll().size();
        // set the field null
        groupEndReason.setEndReasonCode(null);

        // Create the GroupEndReason, which fails.

        restGroupEndReasonMockMvc.perform(post("/api/group-end-reasons")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupEndReason)))
            .andExpect(status().isBadRequest());

        List<GroupEndReason> groupEndReasonList = groupEndReasonRepository.findAll();
        assertThat(groupEndReasonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEndReasonIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupEndReasonRepository.findAll().size();
        // set the field null
        groupEndReason.setEndReason(null);

        // Create the GroupEndReason, which fails.

        restGroupEndReasonMockMvc.perform(post("/api/group-end-reasons")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupEndReason)))
            .andExpect(status().isBadRequest());

        List<GroupEndReason> groupEndReasonList = groupEndReasonRepository.findAll();
        assertThat(groupEndReasonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupEndReasonRepository.findAll().size();
        // set the field null
        groupEndReason.setCreatedDate(null);

        // Create the GroupEndReason, which fails.

        restGroupEndReasonMockMvc.perform(post("/api/group-end-reasons")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupEndReason)))
            .andExpect(status().isBadRequest());

        List<GroupEndReason> groupEndReasonList = groupEndReasonRepository.findAll();
        assertThat(groupEndReasonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupEndReasonRepository.findAll().size();
        // set the field null
        groupEndReason.setCreatedBy(null);

        // Create the GroupEndReason, which fails.

        restGroupEndReasonMockMvc.perform(post("/api/group-end-reasons")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupEndReason)))
            .andExpect(status().isBadRequest());

        List<GroupEndReason> groupEndReasonList = groupEndReasonRepository.findAll();
        assertThat(groupEndReasonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupEndReasonRepository.findAll().size();
        // set the field null
        groupEndReason.setLastUpdatedDate(null);

        // Create the GroupEndReason, which fails.

        restGroupEndReasonMockMvc.perform(post("/api/group-end-reasons")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupEndReason)))
            .andExpect(status().isBadRequest());

        List<GroupEndReason> groupEndReasonList = groupEndReasonRepository.findAll();
        assertThat(groupEndReasonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupEndReasonRepository.findAll().size();
        // set the field null
        groupEndReason.setLastUpdatedBy(null);

        // Create the GroupEndReason, which fails.

        restGroupEndReasonMockMvc.perform(post("/api/group-end-reasons")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupEndReason)))
            .andExpect(status().isBadRequest());

        List<GroupEndReason> groupEndReasonList = groupEndReasonRepository.findAll();
        assertThat(groupEndReasonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupEndReasonRepository.findAll().size();
        // set the field null
        groupEndReason.setTenantId(null);

        // Create the GroupEndReason, which fails.

        restGroupEndReasonMockMvc.perform(post("/api/group-end-reasons")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupEndReason)))
            .andExpect(status().isBadRequest());

        List<GroupEndReason> groupEndReasonList = groupEndReasonRepository.findAll();
        assertThat(groupEndReasonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllGroupEndReasons() throws Exception {
        // Initialize the database
        groupEndReasonRepository.save(groupEndReason);

        // Get all the groupEndReasonList
        restGroupEndReasonMockMvc.perform(get("/api/group-end-reasons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groupEndReason.getId())))
            .andExpect(jsonPath("$.[*].endReasonCode").value(hasItem(DEFAULT_END_REASON_CODE)))
            .andExpect(jsonPath("$.[*].endReason").value(hasItem(DEFAULT_END_REASON)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getGroupEndReason() throws Exception {
        // Initialize the database
        groupEndReasonRepository.save(groupEndReason);

        // Get the groupEndReason
        restGroupEndReasonMockMvc.perform(get("/api/group-end-reasons/{id}", groupEndReason.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(groupEndReason.getId()))
            .andExpect(jsonPath("$.endReasonCode").value(DEFAULT_END_REASON_CODE))
            .andExpect(jsonPath("$.endReason").value(DEFAULT_END_REASON))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingGroupEndReason() throws Exception {
        // Get the groupEndReason
        restGroupEndReasonMockMvc.perform(get("/api/group-end-reasons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateGroupEndReason() throws Exception {
        // Initialize the database
        groupEndReasonService.save(groupEndReason);

        int databaseSizeBeforeUpdate = groupEndReasonRepository.findAll().size();

        // Update the groupEndReason
        GroupEndReason updatedGroupEndReason = groupEndReasonRepository.findById(groupEndReason.getId()).get();
        updatedGroupEndReason
            .endReasonCode(UPDATED_END_REASON_CODE)
            .endReason(UPDATED_END_REASON)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restGroupEndReasonMockMvc.perform(put("/api/group-end-reasons")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedGroupEndReason)))
            .andExpect(status().isOk());

        // Validate the GroupEndReason in the database
        List<GroupEndReason> groupEndReasonList = groupEndReasonRepository.findAll();
        assertThat(groupEndReasonList).hasSize(databaseSizeBeforeUpdate);
        GroupEndReason testGroupEndReason = groupEndReasonList.get(groupEndReasonList.size() - 1);
        assertThat(testGroupEndReason.getEndReasonCode()).isEqualTo(UPDATED_END_REASON_CODE);
        assertThat(testGroupEndReason.getEndReason()).isEqualTo(UPDATED_END_REASON);
        assertThat(testGroupEndReason.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testGroupEndReason.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testGroupEndReason.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testGroupEndReason.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testGroupEndReason.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testGroupEndReason.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testGroupEndReason.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingGroupEndReason() throws Exception {
        int databaseSizeBeforeUpdate = groupEndReasonRepository.findAll().size();

        // Create the GroupEndReason

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupEndReasonMockMvc.perform(put("/api/group-end-reasons")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupEndReason)))
            .andExpect(status().isBadRequest());

        // Validate the GroupEndReason in the database
        List<GroupEndReason> groupEndReasonList = groupEndReasonRepository.findAll();
        assertThat(groupEndReasonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteGroupEndReason() throws Exception {
        // Initialize the database
        groupEndReasonService.save(groupEndReason);

        int databaseSizeBeforeDelete = groupEndReasonRepository.findAll().size();

        // Delete the groupEndReason
        restGroupEndReasonMockMvc.perform(delete("/api/group-end-reasons/{id}", groupEndReason.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GroupEndReason> groupEndReasonList = groupEndReasonRepository.findAll();
        assertThat(groupEndReasonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
