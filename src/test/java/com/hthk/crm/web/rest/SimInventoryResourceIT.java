package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.SimInventory;
import com.hthk.crm.repository.SimInventoryRepository;
import com.hthk.crm.service.SimInventoryService;
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
 * Integration tests for the {@link SimInventoryResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class SimInventoryResourceIT {

    private static final String DEFAULT_ICCID = "AAAAAAAAAA";
    private static final String UPDATED_ICCID = "BBBBBBBBBB";

    private static final String DEFAULT_IMSI = "AAAAAAAAAA";
    private static final String UPDATED_IMSI = "BBBBBBBBBB";

    private static final String DEFAULT_KI = "AAAAAAAAAA";
    private static final String UPDATED_KI = "BBBBBBBBBB";

    private static final String DEFAULT_K_4_SNO = "AAAAAAAAAA";
    private static final String UPDATED_K_4_SNO = "BBBBBBBBBB";

    private static final String DEFAULT_OPSNO = "AAAAAAAAAA";
    private static final String UPDATED_OPSNO = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_NAME = "BBBBBBBBBB";

    private static final RecordStatus DEFAULT_STATUS = RecordStatus.INACTIVE;
    private static final RecordStatus UPDATED_STATUS = RecordStatus.ACTIVE;

    private static final String DEFAULT_OFFER_VALID_FROM_DATE = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_VALID_FROM_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_VALID_TO_DATE = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_VALID_TO_DATE = "BBBBBBBBBB";

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
    private SimInventoryRepository simInventoryRepository;

    @Autowired
    private SimInventoryService simInventoryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restSimInventoryMockMvc;

    private SimInventory simInventory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SimInventoryResource simInventoryResource = new SimInventoryResource(simInventoryService);
        this.restSimInventoryMockMvc = MockMvcBuilders.standaloneSetup(simInventoryResource)
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
    public static SimInventory createEntity() {
        SimInventory simInventory = new SimInventory()
            .iccid(DEFAULT_ICCID)
            .imsi(DEFAULT_IMSI)
            .ki(DEFAULT_KI)
            .k4sno(DEFAULT_K_4_SNO)
            .opsno(DEFAULT_OPSNO)
            .offerId(DEFAULT_OFFER_ID)
            .offerName(DEFAULT_OFFER_NAME)
            .status(DEFAULT_STATUS)
            .offerValidFromDate(DEFAULT_OFFER_VALID_FROM_DATE)
            .offerValidToDate(DEFAULT_OFFER_VALID_TO_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return simInventory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SimInventory createUpdatedEntity() {
        SimInventory simInventory = new SimInventory()
            .iccid(UPDATED_ICCID)
            .imsi(UPDATED_IMSI)
            .ki(UPDATED_KI)
            .k4sno(UPDATED_K_4_SNO)
            .opsno(UPDATED_OPSNO)
            .offerId(UPDATED_OFFER_ID)
            .offerName(UPDATED_OFFER_NAME)
            .status(UPDATED_STATUS)
            .offerValidFromDate(UPDATED_OFFER_VALID_FROM_DATE)
            .offerValidToDate(UPDATED_OFFER_VALID_TO_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return simInventory;
    }

    @BeforeEach
    public void initTest() {
        simInventoryRepository.deleteAll();
        simInventory = createEntity();
    }

    @Test
    public void createSimInventory() throws Exception {
        int databaseSizeBeforeCreate = simInventoryRepository.findAll().size();

        // Create the SimInventory
        restSimInventoryMockMvc.perform(post("/api/sim-inventories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(simInventory)))
            .andExpect(status().isCreated());

        // Validate the SimInventory in the database
        List<SimInventory> simInventoryList = simInventoryRepository.findAll();
        assertThat(simInventoryList).hasSize(databaseSizeBeforeCreate + 1);
        SimInventory testSimInventory = simInventoryList.get(simInventoryList.size() - 1);
        assertThat(testSimInventory.getIccid()).isEqualTo(DEFAULT_ICCID);
        assertThat(testSimInventory.getImsi()).isEqualTo(DEFAULT_IMSI);
        assertThat(testSimInventory.getKi()).isEqualTo(DEFAULT_KI);
        assertThat(testSimInventory.getk4sno()).isEqualTo(DEFAULT_K_4_SNO);
        assertThat(testSimInventory.getOpsno()).isEqualTo(DEFAULT_OPSNO);
        assertThat(testSimInventory.getOfferId()).isEqualTo(DEFAULT_OFFER_ID);
        assertThat(testSimInventory.getOfferName()).isEqualTo(DEFAULT_OFFER_NAME);
        assertThat(testSimInventory.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSimInventory.getOfferValidFromDate()).isEqualTo(DEFAULT_OFFER_VALID_FROM_DATE);
        assertThat(testSimInventory.getOfferValidToDate()).isEqualTo(DEFAULT_OFFER_VALID_TO_DATE);
        assertThat(testSimInventory.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSimInventory.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSimInventory.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testSimInventory.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testSimInventory.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createSimInventoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = simInventoryRepository.findAll().size();

        // Create the SimInventory with an existing ID
        simInventory.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restSimInventoryMockMvc.perform(post("/api/sim-inventories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(simInventory)))
            .andExpect(status().isBadRequest());

        // Validate the SimInventory in the database
        List<SimInventory> simInventoryList = simInventoryRepository.findAll();
        assertThat(simInventoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkIccidIsRequired() throws Exception {
        int databaseSizeBeforeTest = simInventoryRepository.findAll().size();
        // set the field null
        simInventory.setIccid(null);

        // Create the SimInventory, which fails.

        restSimInventoryMockMvc.perform(post("/api/sim-inventories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(simInventory)))
            .andExpect(status().isBadRequest());

        List<SimInventory> simInventoryList = simInventoryRepository.findAll();
        assertThat(simInventoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = simInventoryRepository.findAll().size();
        // set the field null
        simInventory.setCreatedDate(null);

        // Create the SimInventory, which fails.

        restSimInventoryMockMvc.perform(post("/api/sim-inventories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(simInventory)))
            .andExpect(status().isBadRequest());

        List<SimInventory> simInventoryList = simInventoryRepository.findAll();
        assertThat(simInventoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = simInventoryRepository.findAll().size();
        // set the field null
        simInventory.setCreatedBy(null);

        // Create the SimInventory, which fails.

        restSimInventoryMockMvc.perform(post("/api/sim-inventories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(simInventory)))
            .andExpect(status().isBadRequest());

        List<SimInventory> simInventoryList = simInventoryRepository.findAll();
        assertThat(simInventoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = simInventoryRepository.findAll().size();
        // set the field null
        simInventory.setLastUpdatedDate(null);

        // Create the SimInventory, which fails.

        restSimInventoryMockMvc.perform(post("/api/sim-inventories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(simInventory)))
            .andExpect(status().isBadRequest());

        List<SimInventory> simInventoryList = simInventoryRepository.findAll();
        assertThat(simInventoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = simInventoryRepository.findAll().size();
        // set the field null
        simInventory.setLastUpdatedBy(null);

        // Create the SimInventory, which fails.

        restSimInventoryMockMvc.perform(post("/api/sim-inventories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(simInventory)))
            .andExpect(status().isBadRequest());

        List<SimInventory> simInventoryList = simInventoryRepository.findAll();
        assertThat(simInventoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = simInventoryRepository.findAll().size();
        // set the field null
        simInventory.setTenantId(null);

        // Create the SimInventory, which fails.

        restSimInventoryMockMvc.perform(post("/api/sim-inventories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(simInventory)))
            .andExpect(status().isBadRequest());

        List<SimInventory> simInventoryList = simInventoryRepository.findAll();
        assertThat(simInventoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllSimInventories() throws Exception {
        // Initialize the database
        simInventoryRepository.save(simInventory);

        // Get all the simInventoryList
        restSimInventoryMockMvc.perform(get("/api/sim-inventories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(simInventory.getId())))
            .andExpect(jsonPath("$.[*].iccid").value(hasItem(DEFAULT_ICCID)))
            .andExpect(jsonPath("$.[*].imsi").value(hasItem(DEFAULT_IMSI)))
            .andExpect(jsonPath("$.[*].ki").value(hasItem(DEFAULT_KI)))
            .andExpect(jsonPath("$.[*].k4sno").value(hasItem(DEFAULT_K_4_SNO)))
            .andExpect(jsonPath("$.[*].opsno").value(hasItem(DEFAULT_OPSNO)))
            .andExpect(jsonPath("$.[*].offerId").value(hasItem(DEFAULT_OFFER_ID)))
            .andExpect(jsonPath("$.[*].offerName").value(hasItem(DEFAULT_OFFER_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].offerValidFromDate").value(hasItem(DEFAULT_OFFER_VALID_FROM_DATE)))
            .andExpect(jsonPath("$.[*].offerValidToDate").value(hasItem(DEFAULT_OFFER_VALID_TO_DATE)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getSimInventory() throws Exception {
        // Initialize the database
        simInventoryRepository.save(simInventory);

        // Get the simInventory
        restSimInventoryMockMvc.perform(get("/api/sim-inventories/{id}", simInventory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(simInventory.getId()))
            .andExpect(jsonPath("$.iccid").value(DEFAULT_ICCID))
            .andExpect(jsonPath("$.imsi").value(DEFAULT_IMSI))
            .andExpect(jsonPath("$.ki").value(DEFAULT_KI))
            .andExpect(jsonPath("$.k4sno").value(DEFAULT_K_4_SNO))
            .andExpect(jsonPath("$.opsno").value(DEFAULT_OPSNO))
            .andExpect(jsonPath("$.offerId").value(DEFAULT_OFFER_ID))
            .andExpect(jsonPath("$.offerName").value(DEFAULT_OFFER_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.offerValidFromDate").value(DEFAULT_OFFER_VALID_FROM_DATE))
            .andExpect(jsonPath("$.offerValidToDate").value(DEFAULT_OFFER_VALID_TO_DATE))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingSimInventory() throws Exception {
        // Get the simInventory
        restSimInventoryMockMvc.perform(get("/api/sim-inventories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateSimInventory() throws Exception {
        // Initialize the database
        simInventoryService.save(simInventory);

        int databaseSizeBeforeUpdate = simInventoryRepository.findAll().size();

        // Update the simInventory
        SimInventory updatedSimInventory = simInventoryRepository.findById(simInventory.getId()).get();
        updatedSimInventory
            .iccid(UPDATED_ICCID)
            .imsi(UPDATED_IMSI)
            .ki(UPDATED_KI)
            .k4sno(UPDATED_K_4_SNO)
            .opsno(UPDATED_OPSNO)
            .offerId(UPDATED_OFFER_ID)
            .offerName(UPDATED_OFFER_NAME)
            .status(UPDATED_STATUS)
            .offerValidFromDate(UPDATED_OFFER_VALID_FROM_DATE)
            .offerValidToDate(UPDATED_OFFER_VALID_TO_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restSimInventoryMockMvc.perform(put("/api/sim-inventories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSimInventory)))
            .andExpect(status().isOk());

        // Validate the SimInventory in the database
        List<SimInventory> simInventoryList = simInventoryRepository.findAll();
        assertThat(simInventoryList).hasSize(databaseSizeBeforeUpdate);
        SimInventory testSimInventory = simInventoryList.get(simInventoryList.size() - 1);
        assertThat(testSimInventory.getIccid()).isEqualTo(UPDATED_ICCID);
        assertThat(testSimInventory.getImsi()).isEqualTo(UPDATED_IMSI);
        assertThat(testSimInventory.getKi()).isEqualTo(UPDATED_KI);
        assertThat(testSimInventory.getk4sno()).isEqualTo(UPDATED_K_4_SNO);
        assertThat(testSimInventory.getOpsno()).isEqualTo(UPDATED_OPSNO);
        assertThat(testSimInventory.getOfferId()).isEqualTo(UPDATED_OFFER_ID);
        assertThat(testSimInventory.getOfferName()).isEqualTo(UPDATED_OFFER_NAME);
        assertThat(testSimInventory.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSimInventory.getOfferValidFromDate()).isEqualTo(UPDATED_OFFER_VALID_FROM_DATE);
        assertThat(testSimInventory.getOfferValidToDate()).isEqualTo(UPDATED_OFFER_VALID_TO_DATE);
        assertThat(testSimInventory.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSimInventory.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSimInventory.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testSimInventory.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testSimInventory.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingSimInventory() throws Exception {
        int databaseSizeBeforeUpdate = simInventoryRepository.findAll().size();

        // Create the SimInventory

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSimInventoryMockMvc.perform(put("/api/sim-inventories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(simInventory)))
            .andExpect(status().isBadRequest());

        // Validate the SimInventory in the database
        List<SimInventory> simInventoryList = simInventoryRepository.findAll();
        assertThat(simInventoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteSimInventory() throws Exception {
        // Initialize the database
        simInventoryService.save(simInventory);

        int databaseSizeBeforeDelete = simInventoryRepository.findAll().size();

        // Delete the simInventory
        restSimInventoryMockMvc.perform(delete("/api/sim-inventories/{id}", simInventory.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SimInventory> simInventoryList = simInventoryRepository.findAll();
        assertThat(simInventoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
