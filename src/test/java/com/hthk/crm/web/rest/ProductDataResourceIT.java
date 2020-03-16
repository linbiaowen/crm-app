package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.ProductData;
import com.hthk.crm.repository.ProductDataRepository;
import com.hthk.crm.service.ProductDataService;
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


import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.hthk.crm.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProductDataResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class ProductDataResourceIT {

    private static final String DEFAULT_DATA_ID = "AAAAAAAAAA";
    private static final String UPDATED_DATA_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_PRODUCT_SPEC_ID = 1L;
    private static final Long UPDATED_PRODUCT_SPEC_ID = 2L;

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final Integer DEFAULT_VOLUME = 1;
    private static final Integer UPDATED_VOLUME = 2;

    private static final String DEFAULT_DATA_SLAB = "AAAAAAAAAA";
    private static final String UPDATED_DATA_SLAB = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_SPEED_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_DATA_SPEED_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_SPECICAL_PACK_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SPECICAL_PACK_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_SERVICE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DATA_SERVICE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ROAMING_REGIONS = "AAAAAAAAAA";
    private static final String UPDATED_ROAMING_REGIONS = "BBBBBBBBBB";

    private static final String DEFAULT_ROAMING_COUNTRIES = "AAAAAAAAAA";
    private static final String UPDATED_ROAMING_COUNTRIES = "BBBBBBBBBB";

    private static final String DEFAULT_ROAMING_DAY_PASS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ROAMING_DAY_PASS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ROAMING_PACK_VALID_PERIOD_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ROAMING_PACK_VALID_PERIOD_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ROAMING_PACK_PERIOD = 1;
    private static final Integer UPDATED_ROAMING_PACK_PERIOD = 2;

    private static final String DEFAULT_ROAMING_PACK_TERM = "AAAAAAAAAA";
    private static final String UPDATED_ROAMING_PACK_TERM = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_MIN_TRANSFER_QUOTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_TRANSFER_QUOTA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAX_TRANSFER_QUOTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAX_TRANSFER_QUOTA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MIN_RETENTION_QUOTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_RETENTION_QUOTA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MIN_TP_TRANSFER_QUOTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_TP_TRANSFER_QUOTA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAX_TP_TRANSFER_QUOTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAX_TP_TRANSFER_QUOTA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MIN_TP_RETENTION_QUOTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_TP_RETENTION_QUOTA = new BigDecimal(2);

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
    private ProductDataRepository productDataRepository;

    @Autowired
    private ProductDataService productDataService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restProductDataMockMvc;

    private ProductData productData;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductDataResource productDataResource = new ProductDataResource(productDataService);
        this.restProductDataMockMvc = MockMvcBuilders.standaloneSetup(productDataResource)
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
    public static ProductData createEntity() {
        ProductData productData = new ProductData()
            .dataId(DEFAULT_DATA_ID)
            .productSpecId(DEFAULT_PRODUCT_SPEC_ID)
            .unit(DEFAULT_UNIT)
            .volume(DEFAULT_VOLUME)
            .dataSlab(DEFAULT_DATA_SLAB)
            .dataSpeedCategory(DEFAULT_DATA_SPEED_CATEGORY)
            .specicalPackType(DEFAULT_SPECICAL_PACK_TYPE)
            .dataServiceType(DEFAULT_DATA_SERVICE_TYPE)
            .roamingRegions(DEFAULT_ROAMING_REGIONS)
            .roamingCountries(DEFAULT_ROAMING_COUNTRIES)
            .roamingDayPassType(DEFAULT_ROAMING_DAY_PASS_TYPE)
            .roamingPackValidPeriodType(DEFAULT_ROAMING_PACK_VALID_PERIOD_TYPE)
            .roamingPackPeriod(DEFAULT_ROAMING_PACK_PERIOD)
            .roamingPackTerm(DEFAULT_ROAMING_PACK_TERM)
            .minTransferQuota(DEFAULT_MIN_TRANSFER_QUOTA)
            .maxTransferQuota(DEFAULT_MAX_TRANSFER_QUOTA)
            .minRetentionQuota(DEFAULT_MIN_RETENTION_QUOTA)
            .minTpTransferQuota(DEFAULT_MIN_TP_TRANSFER_QUOTA)
            .maxTpTransferQuota(DEFAULT_MAX_TP_TRANSFER_QUOTA)
            .minTpRetentionQuota(DEFAULT_MIN_TP_RETENTION_QUOTA)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return productData;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductData createUpdatedEntity() {
        ProductData productData = new ProductData()
            .dataId(UPDATED_DATA_ID)
            .productSpecId(UPDATED_PRODUCT_SPEC_ID)
            .unit(UPDATED_UNIT)
            .volume(UPDATED_VOLUME)
            .dataSlab(UPDATED_DATA_SLAB)
            .dataSpeedCategory(UPDATED_DATA_SPEED_CATEGORY)
            .specicalPackType(UPDATED_SPECICAL_PACK_TYPE)
            .dataServiceType(UPDATED_DATA_SERVICE_TYPE)
            .roamingRegions(UPDATED_ROAMING_REGIONS)
            .roamingCountries(UPDATED_ROAMING_COUNTRIES)
            .roamingDayPassType(UPDATED_ROAMING_DAY_PASS_TYPE)
            .roamingPackValidPeriodType(UPDATED_ROAMING_PACK_VALID_PERIOD_TYPE)
            .roamingPackPeriod(UPDATED_ROAMING_PACK_PERIOD)
            .roamingPackTerm(UPDATED_ROAMING_PACK_TERM)
            .minTransferQuota(UPDATED_MIN_TRANSFER_QUOTA)
            .maxTransferQuota(UPDATED_MAX_TRANSFER_QUOTA)
            .minRetentionQuota(UPDATED_MIN_RETENTION_QUOTA)
            .minTpTransferQuota(UPDATED_MIN_TP_TRANSFER_QUOTA)
            .maxTpTransferQuota(UPDATED_MAX_TP_TRANSFER_QUOTA)
            .minTpRetentionQuota(UPDATED_MIN_TP_RETENTION_QUOTA)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return productData;
    }

    @BeforeEach
    public void initTest() {
        productDataRepository.deleteAll();
        productData = createEntity();
    }

    @Test
    public void createProductData() throws Exception {
        int databaseSizeBeforeCreate = productDataRepository.findAll().size();

        // Create the ProductData
        restProductDataMockMvc.perform(post("/api/product-data")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productData)))
            .andExpect(status().isCreated());

        // Validate the ProductData in the database
        List<ProductData> productDataList = productDataRepository.findAll();
        assertThat(productDataList).hasSize(databaseSizeBeforeCreate + 1);
        ProductData testProductData = productDataList.get(productDataList.size() - 1);
        assertThat(testProductData.getDataId()).isEqualTo(DEFAULT_DATA_ID);
        assertThat(testProductData.getProductSpecId()).isEqualTo(DEFAULT_PRODUCT_SPEC_ID);
        assertThat(testProductData.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testProductData.getVolume()).isEqualTo(DEFAULT_VOLUME);
        assertThat(testProductData.getDataSlab()).isEqualTo(DEFAULT_DATA_SLAB);
        assertThat(testProductData.getDataSpeedCategory()).isEqualTo(DEFAULT_DATA_SPEED_CATEGORY);
        assertThat(testProductData.getSpecicalPackType()).isEqualTo(DEFAULT_SPECICAL_PACK_TYPE);
        assertThat(testProductData.getDataServiceType()).isEqualTo(DEFAULT_DATA_SERVICE_TYPE);
        assertThat(testProductData.getRoamingRegions()).isEqualTo(DEFAULT_ROAMING_REGIONS);
        assertThat(testProductData.getRoamingCountries()).isEqualTo(DEFAULT_ROAMING_COUNTRIES);
        assertThat(testProductData.getRoamingDayPassType()).isEqualTo(DEFAULT_ROAMING_DAY_PASS_TYPE);
        assertThat(testProductData.getRoamingPackValidPeriodType()).isEqualTo(DEFAULT_ROAMING_PACK_VALID_PERIOD_TYPE);
        assertThat(testProductData.getRoamingPackPeriod()).isEqualTo(DEFAULT_ROAMING_PACK_PERIOD);
        assertThat(testProductData.getRoamingPackTerm()).isEqualTo(DEFAULT_ROAMING_PACK_TERM);
        assertThat(testProductData.getMinTransferQuota()).isEqualTo(DEFAULT_MIN_TRANSFER_QUOTA);
        assertThat(testProductData.getMaxTransferQuota()).isEqualTo(DEFAULT_MAX_TRANSFER_QUOTA);
        assertThat(testProductData.getMinRetentionQuota()).isEqualTo(DEFAULT_MIN_RETENTION_QUOTA);
        assertThat(testProductData.getMinTpTransferQuota()).isEqualTo(DEFAULT_MIN_TP_TRANSFER_QUOTA);
        assertThat(testProductData.getMaxTpTransferQuota()).isEqualTo(DEFAULT_MAX_TP_TRANSFER_QUOTA);
        assertThat(testProductData.getMinTpRetentionQuota()).isEqualTo(DEFAULT_MIN_TP_RETENTION_QUOTA);
        assertThat(testProductData.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProductData.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductData.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testProductData.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testProductData.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createProductDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productDataRepository.findAll().size();

        // Create the ProductData with an existing ID
        productData.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductDataMockMvc.perform(post("/api/product-data")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productData)))
            .andExpect(status().isBadRequest());

        // Validate the ProductData in the database
        List<ProductData> productDataList = productDataRepository.findAll();
        assertThat(productDataList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkProductSpecIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productDataRepository.findAll().size();
        // set the field null
        productData.setProductSpecId(null);

        // Create the ProductData, which fails.

        restProductDataMockMvc.perform(post("/api/product-data")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productData)))
            .andExpect(status().isBadRequest());

        List<ProductData> productDataList = productDataRepository.findAll();
        assertThat(productDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkUnitIsRequired() throws Exception {
        int databaseSizeBeforeTest = productDataRepository.findAll().size();
        // set the field null
        productData.setUnit(null);

        // Create the ProductData, which fails.

        restProductDataMockMvc.perform(post("/api/product-data")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productData)))
            .andExpect(status().isBadRequest());

        List<ProductData> productDataList = productDataRepository.findAll();
        assertThat(productDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkVolumeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productDataRepository.findAll().size();
        // set the field null
        productData.setVolume(null);

        // Create the ProductData, which fails.

        restProductDataMockMvc.perform(post("/api/product-data")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productData)))
            .andExpect(status().isBadRequest());

        List<ProductData> productDataList = productDataRepository.findAll();
        assertThat(productDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productDataRepository.findAll().size();
        // set the field null
        productData.setCreatedDate(null);

        // Create the ProductData, which fails.

        restProductDataMockMvc.perform(post("/api/product-data")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productData)))
            .andExpect(status().isBadRequest());

        List<ProductData> productDataList = productDataRepository.findAll();
        assertThat(productDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productDataRepository.findAll().size();
        // set the field null
        productData.setCreatedBy(null);

        // Create the ProductData, which fails.

        restProductDataMockMvc.perform(post("/api/product-data")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productData)))
            .andExpect(status().isBadRequest());

        List<ProductData> productDataList = productDataRepository.findAll();
        assertThat(productDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productDataRepository.findAll().size();
        // set the field null
        productData.setLastUpdatedDate(null);

        // Create the ProductData, which fails.

        restProductDataMockMvc.perform(post("/api/product-data")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productData)))
            .andExpect(status().isBadRequest());

        List<ProductData> productDataList = productDataRepository.findAll();
        assertThat(productDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productDataRepository.findAll().size();
        // set the field null
        productData.setLastUpdatedBy(null);

        // Create the ProductData, which fails.

        restProductDataMockMvc.perform(post("/api/product-data")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productData)))
            .andExpect(status().isBadRequest());

        List<ProductData> productDataList = productDataRepository.findAll();
        assertThat(productDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productDataRepository.findAll().size();
        // set the field null
        productData.setTenantId(null);

        // Create the ProductData, which fails.

        restProductDataMockMvc.perform(post("/api/product-data")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productData)))
            .andExpect(status().isBadRequest());

        List<ProductData> productDataList = productDataRepository.findAll();
        assertThat(productDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProductData() throws Exception {
        // Initialize the database
        productDataRepository.save(productData);

        // Get all the productDataList
        restProductDataMockMvc.perform(get("/api/product-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productData.getId())))
            .andExpect(jsonPath("$.[*].dataId").value(hasItem(DEFAULT_DATA_ID)))
            .andExpect(jsonPath("$.[*].productSpecId").value(hasItem(DEFAULT_PRODUCT_SPEC_ID.intValue())))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].volume").value(hasItem(DEFAULT_VOLUME)))
            .andExpect(jsonPath("$.[*].dataSlab").value(hasItem(DEFAULT_DATA_SLAB)))
            .andExpect(jsonPath("$.[*].dataSpeedCategory").value(hasItem(DEFAULT_DATA_SPEED_CATEGORY)))
            .andExpect(jsonPath("$.[*].specicalPackType").value(hasItem(DEFAULT_SPECICAL_PACK_TYPE)))
            .andExpect(jsonPath("$.[*].dataServiceType").value(hasItem(DEFAULT_DATA_SERVICE_TYPE)))
            .andExpect(jsonPath("$.[*].roamingRegions").value(hasItem(DEFAULT_ROAMING_REGIONS)))
            .andExpect(jsonPath("$.[*].roamingCountries").value(hasItem(DEFAULT_ROAMING_COUNTRIES)))
            .andExpect(jsonPath("$.[*].roamingDayPassType").value(hasItem(DEFAULT_ROAMING_DAY_PASS_TYPE)))
            .andExpect(jsonPath("$.[*].roamingPackValidPeriodType").value(hasItem(DEFAULT_ROAMING_PACK_VALID_PERIOD_TYPE)))
            .andExpect(jsonPath("$.[*].roamingPackPeriod").value(hasItem(DEFAULT_ROAMING_PACK_PERIOD)))
            .andExpect(jsonPath("$.[*].roamingPackTerm").value(hasItem(DEFAULT_ROAMING_PACK_TERM)))
            .andExpect(jsonPath("$.[*].minTransferQuota").value(hasItem(DEFAULT_MIN_TRANSFER_QUOTA.intValue())))
            .andExpect(jsonPath("$.[*].maxTransferQuota").value(hasItem(DEFAULT_MAX_TRANSFER_QUOTA.intValue())))
            .andExpect(jsonPath("$.[*].minRetentionQuota").value(hasItem(DEFAULT_MIN_RETENTION_QUOTA.intValue())))
            .andExpect(jsonPath("$.[*].minTpTransferQuota").value(hasItem(DEFAULT_MIN_TP_TRANSFER_QUOTA.intValue())))
            .andExpect(jsonPath("$.[*].maxTpTransferQuota").value(hasItem(DEFAULT_MAX_TP_TRANSFER_QUOTA.intValue())))
            .andExpect(jsonPath("$.[*].minTpRetentionQuota").value(hasItem(DEFAULT_MIN_TP_RETENTION_QUOTA.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getProductData() throws Exception {
        // Initialize the database
        productDataRepository.save(productData);

        // Get the productData
        restProductDataMockMvc.perform(get("/api/product-data/{id}", productData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productData.getId()))
            .andExpect(jsonPath("$.dataId").value(DEFAULT_DATA_ID))
            .andExpect(jsonPath("$.productSpecId").value(DEFAULT_PRODUCT_SPEC_ID.intValue()))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
            .andExpect(jsonPath("$.volume").value(DEFAULT_VOLUME))
            .andExpect(jsonPath("$.dataSlab").value(DEFAULT_DATA_SLAB))
            .andExpect(jsonPath("$.dataSpeedCategory").value(DEFAULT_DATA_SPEED_CATEGORY))
            .andExpect(jsonPath("$.specicalPackType").value(DEFAULT_SPECICAL_PACK_TYPE))
            .andExpect(jsonPath("$.dataServiceType").value(DEFAULT_DATA_SERVICE_TYPE))
            .andExpect(jsonPath("$.roamingRegions").value(DEFAULT_ROAMING_REGIONS))
            .andExpect(jsonPath("$.roamingCountries").value(DEFAULT_ROAMING_COUNTRIES))
            .andExpect(jsonPath("$.roamingDayPassType").value(DEFAULT_ROAMING_DAY_PASS_TYPE))
            .andExpect(jsonPath("$.roamingPackValidPeriodType").value(DEFAULT_ROAMING_PACK_VALID_PERIOD_TYPE))
            .andExpect(jsonPath("$.roamingPackPeriod").value(DEFAULT_ROAMING_PACK_PERIOD))
            .andExpect(jsonPath("$.roamingPackTerm").value(DEFAULT_ROAMING_PACK_TERM))
            .andExpect(jsonPath("$.minTransferQuota").value(DEFAULT_MIN_TRANSFER_QUOTA.intValue()))
            .andExpect(jsonPath("$.maxTransferQuota").value(DEFAULT_MAX_TRANSFER_QUOTA.intValue()))
            .andExpect(jsonPath("$.minRetentionQuota").value(DEFAULT_MIN_RETENTION_QUOTA.intValue()))
            .andExpect(jsonPath("$.minTpTransferQuota").value(DEFAULT_MIN_TP_TRANSFER_QUOTA.intValue()))
            .andExpect(jsonPath("$.maxTpTransferQuota").value(DEFAULT_MAX_TP_TRANSFER_QUOTA.intValue()))
            .andExpect(jsonPath("$.minTpRetentionQuota").value(DEFAULT_MIN_TP_RETENTION_QUOTA.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingProductData() throws Exception {
        // Get the productData
        restProductDataMockMvc.perform(get("/api/product-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProductData() throws Exception {
        // Initialize the database
        productDataService.save(productData);

        int databaseSizeBeforeUpdate = productDataRepository.findAll().size();

        // Update the productData
        ProductData updatedProductData = productDataRepository.findById(productData.getId()).get();
        updatedProductData
            .dataId(UPDATED_DATA_ID)
            .productSpecId(UPDATED_PRODUCT_SPEC_ID)
            .unit(UPDATED_UNIT)
            .volume(UPDATED_VOLUME)
            .dataSlab(UPDATED_DATA_SLAB)
            .dataSpeedCategory(UPDATED_DATA_SPEED_CATEGORY)
            .specicalPackType(UPDATED_SPECICAL_PACK_TYPE)
            .dataServiceType(UPDATED_DATA_SERVICE_TYPE)
            .roamingRegions(UPDATED_ROAMING_REGIONS)
            .roamingCountries(UPDATED_ROAMING_COUNTRIES)
            .roamingDayPassType(UPDATED_ROAMING_DAY_PASS_TYPE)
            .roamingPackValidPeriodType(UPDATED_ROAMING_PACK_VALID_PERIOD_TYPE)
            .roamingPackPeriod(UPDATED_ROAMING_PACK_PERIOD)
            .roamingPackTerm(UPDATED_ROAMING_PACK_TERM)
            .minTransferQuota(UPDATED_MIN_TRANSFER_QUOTA)
            .maxTransferQuota(UPDATED_MAX_TRANSFER_QUOTA)
            .minRetentionQuota(UPDATED_MIN_RETENTION_QUOTA)
            .minTpTransferQuota(UPDATED_MIN_TP_TRANSFER_QUOTA)
            .maxTpTransferQuota(UPDATED_MAX_TP_TRANSFER_QUOTA)
            .minTpRetentionQuota(UPDATED_MIN_TP_RETENTION_QUOTA)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restProductDataMockMvc.perform(put("/api/product-data")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductData)))
            .andExpect(status().isOk());

        // Validate the ProductData in the database
        List<ProductData> productDataList = productDataRepository.findAll();
        assertThat(productDataList).hasSize(databaseSizeBeforeUpdate);
        ProductData testProductData = productDataList.get(productDataList.size() - 1);
        assertThat(testProductData.getDataId()).isEqualTo(UPDATED_DATA_ID);
        assertThat(testProductData.getProductSpecId()).isEqualTo(UPDATED_PRODUCT_SPEC_ID);
        assertThat(testProductData.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testProductData.getVolume()).isEqualTo(UPDATED_VOLUME);
        assertThat(testProductData.getDataSlab()).isEqualTo(UPDATED_DATA_SLAB);
        assertThat(testProductData.getDataSpeedCategory()).isEqualTo(UPDATED_DATA_SPEED_CATEGORY);
        assertThat(testProductData.getSpecicalPackType()).isEqualTo(UPDATED_SPECICAL_PACK_TYPE);
        assertThat(testProductData.getDataServiceType()).isEqualTo(UPDATED_DATA_SERVICE_TYPE);
        assertThat(testProductData.getRoamingRegions()).isEqualTo(UPDATED_ROAMING_REGIONS);
        assertThat(testProductData.getRoamingCountries()).isEqualTo(UPDATED_ROAMING_COUNTRIES);
        assertThat(testProductData.getRoamingDayPassType()).isEqualTo(UPDATED_ROAMING_DAY_PASS_TYPE);
        assertThat(testProductData.getRoamingPackValidPeriodType()).isEqualTo(UPDATED_ROAMING_PACK_VALID_PERIOD_TYPE);
        assertThat(testProductData.getRoamingPackPeriod()).isEqualTo(UPDATED_ROAMING_PACK_PERIOD);
        assertThat(testProductData.getRoamingPackTerm()).isEqualTo(UPDATED_ROAMING_PACK_TERM);
        assertThat(testProductData.getMinTransferQuota()).isEqualTo(UPDATED_MIN_TRANSFER_QUOTA);
        assertThat(testProductData.getMaxTransferQuota()).isEqualTo(UPDATED_MAX_TRANSFER_QUOTA);
        assertThat(testProductData.getMinRetentionQuota()).isEqualTo(UPDATED_MIN_RETENTION_QUOTA);
        assertThat(testProductData.getMinTpTransferQuota()).isEqualTo(UPDATED_MIN_TP_TRANSFER_QUOTA);
        assertThat(testProductData.getMaxTpTransferQuota()).isEqualTo(UPDATED_MAX_TP_TRANSFER_QUOTA);
        assertThat(testProductData.getMinTpRetentionQuota()).isEqualTo(UPDATED_MIN_TP_RETENTION_QUOTA);
        assertThat(testProductData.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProductData.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductData.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testProductData.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testProductData.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingProductData() throws Exception {
        int databaseSizeBeforeUpdate = productDataRepository.findAll().size();

        // Create the ProductData

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductDataMockMvc.perform(put("/api/product-data")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productData)))
            .andExpect(status().isBadRequest());

        // Validate the ProductData in the database
        List<ProductData> productDataList = productDataRepository.findAll();
        assertThat(productDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteProductData() throws Exception {
        // Initialize the database
        productDataService.save(productData);

        int databaseSizeBeforeDelete = productDataRepository.findAll().size();

        // Delete the productData
        restProductDataMockMvc.perform(delete("/api/product-data/{id}", productData.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductData> productDataList = productDataRepository.findAll();
        assertThat(productDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
