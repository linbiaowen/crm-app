package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.SalesChannelMaster;
import com.hthk.crm.repository.SalesChannelMasterRepository;
import com.hthk.crm.service.SalesChannelMasterService;
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
 * Integration tests for the {@link SalesChannelMasterResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class SalesChannelMasterResourceIT {

    private static final String DEFAULT_SALES_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_SALES_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_CHANNEL_DESC = "AAAAAAAAAA";
    private static final String UPDATED_SALES_CHANNEL_DESC = "BBBBBBBBBB";

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
    private SalesChannelMasterRepository salesChannelMasterRepository;

    @Autowired
    private SalesChannelMasterService salesChannelMasterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restSalesChannelMasterMockMvc;

    private SalesChannelMaster salesChannelMaster;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SalesChannelMasterResource salesChannelMasterResource = new SalesChannelMasterResource(salesChannelMasterService);
        this.restSalesChannelMasterMockMvc = MockMvcBuilders.standaloneSetup(salesChannelMasterResource)
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
    public static SalesChannelMaster createEntity() {
        SalesChannelMaster salesChannelMaster = new SalesChannelMaster()
            .salesChannel(DEFAULT_SALES_CHANNEL)
            .salesChannelDesc(DEFAULT_SALES_CHANNEL_DESC)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return salesChannelMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesChannelMaster createUpdatedEntity() {
        SalesChannelMaster salesChannelMaster = new SalesChannelMaster()
            .salesChannel(UPDATED_SALES_CHANNEL)
            .salesChannelDesc(UPDATED_SALES_CHANNEL_DESC)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return salesChannelMaster;
    }

    @BeforeEach
    public void initTest() {
        salesChannelMasterRepository.deleteAll();
        salesChannelMaster = createEntity();
    }

    @Test
    public void createSalesChannelMaster() throws Exception {
        int databaseSizeBeforeCreate = salesChannelMasterRepository.findAll().size();

        // Create the SalesChannelMaster
        restSalesChannelMasterMockMvc.perform(post("/api/sales-channel-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesChannelMaster)))
            .andExpect(status().isCreated());

        // Validate the SalesChannelMaster in the database
        List<SalesChannelMaster> salesChannelMasterList = salesChannelMasterRepository.findAll();
        assertThat(salesChannelMasterList).hasSize(databaseSizeBeforeCreate + 1);
        SalesChannelMaster testSalesChannelMaster = salesChannelMasterList.get(salesChannelMasterList.size() - 1);
        assertThat(testSalesChannelMaster.getSalesChannel()).isEqualTo(DEFAULT_SALES_CHANNEL);
        assertThat(testSalesChannelMaster.getSalesChannelDesc()).isEqualTo(DEFAULT_SALES_CHANNEL_DESC);
        assertThat(testSalesChannelMaster.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testSalesChannelMaster.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testSalesChannelMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSalesChannelMaster.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSalesChannelMaster.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testSalesChannelMaster.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testSalesChannelMaster.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createSalesChannelMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = salesChannelMasterRepository.findAll().size();

        // Create the SalesChannelMaster with an existing ID
        salesChannelMaster.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesChannelMasterMockMvc.perform(post("/api/sales-channel-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesChannelMaster)))
            .andExpect(status().isBadRequest());

        // Validate the SalesChannelMaster in the database
        List<SalesChannelMaster> salesChannelMasterList = salesChannelMasterRepository.findAll();
        assertThat(salesChannelMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkSalesChannelIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesChannelMasterRepository.findAll().size();
        // set the field null
        salesChannelMaster.setSalesChannel(null);

        // Create the SalesChannelMaster, which fails.

        restSalesChannelMasterMockMvc.perform(post("/api/sales-channel-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesChannelMaster)))
            .andExpect(status().isBadRequest());

        List<SalesChannelMaster> salesChannelMasterList = salesChannelMasterRepository.findAll();
        assertThat(salesChannelMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesChannelMasterRepository.findAll().size();
        // set the field null
        salesChannelMaster.setCreatedDate(null);

        // Create the SalesChannelMaster, which fails.

        restSalesChannelMasterMockMvc.perform(post("/api/sales-channel-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesChannelMaster)))
            .andExpect(status().isBadRequest());

        List<SalesChannelMaster> salesChannelMasterList = salesChannelMasterRepository.findAll();
        assertThat(salesChannelMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesChannelMasterRepository.findAll().size();
        // set the field null
        salesChannelMaster.setCreatedBy(null);

        // Create the SalesChannelMaster, which fails.

        restSalesChannelMasterMockMvc.perform(post("/api/sales-channel-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesChannelMaster)))
            .andExpect(status().isBadRequest());

        List<SalesChannelMaster> salesChannelMasterList = salesChannelMasterRepository.findAll();
        assertThat(salesChannelMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesChannelMasterRepository.findAll().size();
        // set the field null
        salesChannelMaster.setLastUpdatedDate(null);

        // Create the SalesChannelMaster, which fails.

        restSalesChannelMasterMockMvc.perform(post("/api/sales-channel-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesChannelMaster)))
            .andExpect(status().isBadRequest());

        List<SalesChannelMaster> salesChannelMasterList = salesChannelMasterRepository.findAll();
        assertThat(salesChannelMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesChannelMasterRepository.findAll().size();
        // set the field null
        salesChannelMaster.setLastUpdatedBy(null);

        // Create the SalesChannelMaster, which fails.

        restSalesChannelMasterMockMvc.perform(post("/api/sales-channel-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesChannelMaster)))
            .andExpect(status().isBadRequest());

        List<SalesChannelMaster> salesChannelMasterList = salesChannelMasterRepository.findAll();
        assertThat(salesChannelMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesChannelMasterRepository.findAll().size();
        // set the field null
        salesChannelMaster.setTenantId(null);

        // Create the SalesChannelMaster, which fails.

        restSalesChannelMasterMockMvc.perform(post("/api/sales-channel-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesChannelMaster)))
            .andExpect(status().isBadRequest());

        List<SalesChannelMaster> salesChannelMasterList = salesChannelMasterRepository.findAll();
        assertThat(salesChannelMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllSalesChannelMasters() throws Exception {
        // Initialize the database
        salesChannelMasterRepository.save(salesChannelMaster);

        // Get all the salesChannelMasterList
        restSalesChannelMasterMockMvc.perform(get("/api/sales-channel-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesChannelMaster.getId())))
            .andExpect(jsonPath("$.[*].salesChannel").value(hasItem(DEFAULT_SALES_CHANNEL)))
            .andExpect(jsonPath("$.[*].salesChannelDesc").value(hasItem(DEFAULT_SALES_CHANNEL_DESC)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getSalesChannelMaster() throws Exception {
        // Initialize the database
        salesChannelMasterRepository.save(salesChannelMaster);

        // Get the salesChannelMaster
        restSalesChannelMasterMockMvc.perform(get("/api/sales-channel-masters/{id}", salesChannelMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(salesChannelMaster.getId()))
            .andExpect(jsonPath("$.salesChannel").value(DEFAULT_SALES_CHANNEL))
            .andExpect(jsonPath("$.salesChannelDesc").value(DEFAULT_SALES_CHANNEL_DESC))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingSalesChannelMaster() throws Exception {
        // Get the salesChannelMaster
        restSalesChannelMasterMockMvc.perform(get("/api/sales-channel-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateSalesChannelMaster() throws Exception {
        // Initialize the database
        salesChannelMasterService.save(salesChannelMaster);

        int databaseSizeBeforeUpdate = salesChannelMasterRepository.findAll().size();

        // Update the salesChannelMaster
        SalesChannelMaster updatedSalesChannelMaster = salesChannelMasterRepository.findById(salesChannelMaster.getId()).get();
        updatedSalesChannelMaster
            .salesChannel(UPDATED_SALES_CHANNEL)
            .salesChannelDesc(UPDATED_SALES_CHANNEL_DESC)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restSalesChannelMasterMockMvc.perform(put("/api/sales-channel-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSalesChannelMaster)))
            .andExpect(status().isOk());

        // Validate the SalesChannelMaster in the database
        List<SalesChannelMaster> salesChannelMasterList = salesChannelMasterRepository.findAll();
        assertThat(salesChannelMasterList).hasSize(databaseSizeBeforeUpdate);
        SalesChannelMaster testSalesChannelMaster = salesChannelMasterList.get(salesChannelMasterList.size() - 1);
        assertThat(testSalesChannelMaster.getSalesChannel()).isEqualTo(UPDATED_SALES_CHANNEL);
        assertThat(testSalesChannelMaster.getSalesChannelDesc()).isEqualTo(UPDATED_SALES_CHANNEL_DESC);
        assertThat(testSalesChannelMaster.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testSalesChannelMaster.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testSalesChannelMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesChannelMaster.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSalesChannelMaster.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testSalesChannelMaster.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testSalesChannelMaster.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingSalesChannelMaster() throws Exception {
        int databaseSizeBeforeUpdate = salesChannelMasterRepository.findAll().size();

        // Create the SalesChannelMaster

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesChannelMasterMockMvc.perform(put("/api/sales-channel-masters")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesChannelMaster)))
            .andExpect(status().isBadRequest());

        // Validate the SalesChannelMaster in the database
        List<SalesChannelMaster> salesChannelMasterList = salesChannelMasterRepository.findAll();
        assertThat(salesChannelMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteSalesChannelMaster() throws Exception {
        // Initialize the database
        salesChannelMasterService.save(salesChannelMaster);

        int databaseSizeBeforeDelete = salesChannelMasterRepository.findAll().size();

        // Delete the salesChannelMaster
        restSalesChannelMasterMockMvc.perform(delete("/api/sales-channel-masters/{id}", salesChannelMaster.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SalesChannelMaster> salesChannelMasterList = salesChannelMasterRepository.findAll();
        assertThat(salesChannelMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
