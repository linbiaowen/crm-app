package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.ProductVoice;
import com.hthk.crm.repository.ProductVoiceRepository;
import com.hthk.crm.service.ProductVoiceService;
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
 * Integration tests for the {@link ProductVoiceResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class ProductVoiceResourceIT {

    private static final String DEFAULT_VOICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_VOICE_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_PRODUCT_SPEC_ID = 1L;
    private static final Long UPDATED_PRODUCT_SPEC_ID = 2L;

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final Integer DEFAULT_VOLUME = 1;
    private static final Integer UPDATED_VOLUME = 2;

    private static final Boolean DEFAULT_ROAMING_FLAG = false;
    private static final Boolean UPDATED_ROAMING_FLAG = true;

    private static final BigDecimal DEFAULT_MIN_TRANSFER_QUOTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_TRANSFER_QUOTA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAX_TRANSFER_QUOTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAX_TRANSFER_QUOTA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MIN_RETENTION_QUOTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_RETENTION_QUOTA = new BigDecimal(2);

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
    private ProductVoiceRepository productVoiceRepository;

    @Autowired
    private ProductVoiceService productVoiceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restProductVoiceMockMvc;

    private ProductVoice productVoice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductVoiceResource productVoiceResource = new ProductVoiceResource(productVoiceService);
        this.restProductVoiceMockMvc = MockMvcBuilders.standaloneSetup(productVoiceResource)
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
    public static ProductVoice createEntity() {
        ProductVoice productVoice = new ProductVoice()
            .voiceId(DEFAULT_VOICE_ID)
            .productSpecId(DEFAULT_PRODUCT_SPEC_ID)
            .unit(DEFAULT_UNIT)
            .volume(DEFAULT_VOLUME)
            .roamingFlag(DEFAULT_ROAMING_FLAG)
            .minTransferQuota(DEFAULT_MIN_TRANSFER_QUOTA)
            .maxTransferQuota(DEFAULT_MAX_TRANSFER_QUOTA)
            .minRetentionQuota(DEFAULT_MIN_RETENTION_QUOTA)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return productVoice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductVoice createUpdatedEntity() {
        ProductVoice productVoice = new ProductVoice()
            .voiceId(UPDATED_VOICE_ID)
            .productSpecId(UPDATED_PRODUCT_SPEC_ID)
            .unit(UPDATED_UNIT)
            .volume(UPDATED_VOLUME)
            .roamingFlag(UPDATED_ROAMING_FLAG)
            .minTransferQuota(UPDATED_MIN_TRANSFER_QUOTA)
            .maxTransferQuota(UPDATED_MAX_TRANSFER_QUOTA)
            .minRetentionQuota(UPDATED_MIN_RETENTION_QUOTA)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return productVoice;
    }

    @BeforeEach
    public void initTest() {
        productVoiceRepository.deleteAll();
        productVoice = createEntity();
    }

    @Test
    public void createProductVoice() throws Exception {
        int databaseSizeBeforeCreate = productVoiceRepository.findAll().size();

        // Create the ProductVoice
        restProductVoiceMockMvc.perform(post("/api/product-voices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productVoice)))
            .andExpect(status().isCreated());

        // Validate the ProductVoice in the database
        List<ProductVoice> productVoiceList = productVoiceRepository.findAll();
        assertThat(productVoiceList).hasSize(databaseSizeBeforeCreate + 1);
        ProductVoice testProductVoice = productVoiceList.get(productVoiceList.size() - 1);
        assertThat(testProductVoice.getVoiceId()).isEqualTo(DEFAULT_VOICE_ID);
        assertThat(testProductVoice.getProductSpecId()).isEqualTo(DEFAULT_PRODUCT_SPEC_ID);
        assertThat(testProductVoice.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testProductVoice.getVolume()).isEqualTo(DEFAULT_VOLUME);
        assertThat(testProductVoice.isRoamingFlag()).isEqualTo(DEFAULT_ROAMING_FLAG);
        assertThat(testProductVoice.getMinTransferQuota()).isEqualTo(DEFAULT_MIN_TRANSFER_QUOTA);
        assertThat(testProductVoice.getMaxTransferQuota()).isEqualTo(DEFAULT_MAX_TRANSFER_QUOTA);
        assertThat(testProductVoice.getMinRetentionQuota()).isEqualTo(DEFAULT_MIN_RETENTION_QUOTA);
        assertThat(testProductVoice.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProductVoice.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductVoice.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testProductVoice.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testProductVoice.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createProductVoiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productVoiceRepository.findAll().size();

        // Create the ProductVoice with an existing ID
        productVoice.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductVoiceMockMvc.perform(post("/api/product-voices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productVoice)))
            .andExpect(status().isBadRequest());

        // Validate the ProductVoice in the database
        List<ProductVoice> productVoiceList = productVoiceRepository.findAll();
        assertThat(productVoiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkProductSpecIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productVoiceRepository.findAll().size();
        // set the field null
        productVoice.setProductSpecId(null);

        // Create the ProductVoice, which fails.

        restProductVoiceMockMvc.perform(post("/api/product-voices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productVoice)))
            .andExpect(status().isBadRequest());

        List<ProductVoice> productVoiceList = productVoiceRepository.findAll();
        assertThat(productVoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkUnitIsRequired() throws Exception {
        int databaseSizeBeforeTest = productVoiceRepository.findAll().size();
        // set the field null
        productVoice.setUnit(null);

        // Create the ProductVoice, which fails.

        restProductVoiceMockMvc.perform(post("/api/product-voices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productVoice)))
            .andExpect(status().isBadRequest());

        List<ProductVoice> productVoiceList = productVoiceRepository.findAll();
        assertThat(productVoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkVolumeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productVoiceRepository.findAll().size();
        // set the field null
        productVoice.setVolume(null);

        // Create the ProductVoice, which fails.

        restProductVoiceMockMvc.perform(post("/api/product-voices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productVoice)))
            .andExpect(status().isBadRequest());

        List<ProductVoice> productVoiceList = productVoiceRepository.findAll();
        assertThat(productVoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productVoiceRepository.findAll().size();
        // set the field null
        productVoice.setCreatedDate(null);

        // Create the ProductVoice, which fails.

        restProductVoiceMockMvc.perform(post("/api/product-voices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productVoice)))
            .andExpect(status().isBadRequest());

        List<ProductVoice> productVoiceList = productVoiceRepository.findAll();
        assertThat(productVoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productVoiceRepository.findAll().size();
        // set the field null
        productVoice.setCreatedBy(null);

        // Create the ProductVoice, which fails.

        restProductVoiceMockMvc.perform(post("/api/product-voices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productVoice)))
            .andExpect(status().isBadRequest());

        List<ProductVoice> productVoiceList = productVoiceRepository.findAll();
        assertThat(productVoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productVoiceRepository.findAll().size();
        // set the field null
        productVoice.setLastUpdatedDate(null);

        // Create the ProductVoice, which fails.

        restProductVoiceMockMvc.perform(post("/api/product-voices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productVoice)))
            .andExpect(status().isBadRequest());

        List<ProductVoice> productVoiceList = productVoiceRepository.findAll();
        assertThat(productVoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productVoiceRepository.findAll().size();
        // set the field null
        productVoice.setLastUpdatedBy(null);

        // Create the ProductVoice, which fails.

        restProductVoiceMockMvc.perform(post("/api/product-voices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productVoice)))
            .andExpect(status().isBadRequest());

        List<ProductVoice> productVoiceList = productVoiceRepository.findAll();
        assertThat(productVoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productVoiceRepository.findAll().size();
        // set the field null
        productVoice.setTenantId(null);

        // Create the ProductVoice, which fails.

        restProductVoiceMockMvc.perform(post("/api/product-voices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productVoice)))
            .andExpect(status().isBadRequest());

        List<ProductVoice> productVoiceList = productVoiceRepository.findAll();
        assertThat(productVoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProductVoices() throws Exception {
        // Initialize the database
        productVoiceRepository.save(productVoice);

        // Get all the productVoiceList
        restProductVoiceMockMvc.perform(get("/api/product-voices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productVoice.getId())))
            .andExpect(jsonPath("$.[*].voiceId").value(hasItem(DEFAULT_VOICE_ID)))
            .andExpect(jsonPath("$.[*].productSpecId").value(hasItem(DEFAULT_PRODUCT_SPEC_ID.intValue())))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].volume").value(hasItem(DEFAULT_VOLUME)))
            .andExpect(jsonPath("$.[*].roamingFlag").value(hasItem(DEFAULT_ROAMING_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].minTransferQuota").value(hasItem(DEFAULT_MIN_TRANSFER_QUOTA.intValue())))
            .andExpect(jsonPath("$.[*].maxTransferQuota").value(hasItem(DEFAULT_MAX_TRANSFER_QUOTA.intValue())))
            .andExpect(jsonPath("$.[*].minRetentionQuota").value(hasItem(DEFAULT_MIN_RETENTION_QUOTA.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getProductVoice() throws Exception {
        // Initialize the database
        productVoiceRepository.save(productVoice);

        // Get the productVoice
        restProductVoiceMockMvc.perform(get("/api/product-voices/{id}", productVoice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productVoice.getId()))
            .andExpect(jsonPath("$.voiceId").value(DEFAULT_VOICE_ID))
            .andExpect(jsonPath("$.productSpecId").value(DEFAULT_PRODUCT_SPEC_ID.intValue()))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
            .andExpect(jsonPath("$.volume").value(DEFAULT_VOLUME))
            .andExpect(jsonPath("$.roamingFlag").value(DEFAULT_ROAMING_FLAG.booleanValue()))
            .andExpect(jsonPath("$.minTransferQuota").value(DEFAULT_MIN_TRANSFER_QUOTA.intValue()))
            .andExpect(jsonPath("$.maxTransferQuota").value(DEFAULT_MAX_TRANSFER_QUOTA.intValue()))
            .andExpect(jsonPath("$.minRetentionQuota").value(DEFAULT_MIN_RETENTION_QUOTA.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingProductVoice() throws Exception {
        // Get the productVoice
        restProductVoiceMockMvc.perform(get("/api/product-voices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProductVoice() throws Exception {
        // Initialize the database
        productVoiceService.save(productVoice);

        int databaseSizeBeforeUpdate = productVoiceRepository.findAll().size();

        // Update the productVoice
        ProductVoice updatedProductVoice = productVoiceRepository.findById(productVoice.getId()).get();
        updatedProductVoice
            .voiceId(UPDATED_VOICE_ID)
            .productSpecId(UPDATED_PRODUCT_SPEC_ID)
            .unit(UPDATED_UNIT)
            .volume(UPDATED_VOLUME)
            .roamingFlag(UPDATED_ROAMING_FLAG)
            .minTransferQuota(UPDATED_MIN_TRANSFER_QUOTA)
            .maxTransferQuota(UPDATED_MAX_TRANSFER_QUOTA)
            .minRetentionQuota(UPDATED_MIN_RETENTION_QUOTA)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restProductVoiceMockMvc.perform(put("/api/product-voices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductVoice)))
            .andExpect(status().isOk());

        // Validate the ProductVoice in the database
        List<ProductVoice> productVoiceList = productVoiceRepository.findAll();
        assertThat(productVoiceList).hasSize(databaseSizeBeforeUpdate);
        ProductVoice testProductVoice = productVoiceList.get(productVoiceList.size() - 1);
        assertThat(testProductVoice.getVoiceId()).isEqualTo(UPDATED_VOICE_ID);
        assertThat(testProductVoice.getProductSpecId()).isEqualTo(UPDATED_PRODUCT_SPEC_ID);
        assertThat(testProductVoice.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testProductVoice.getVolume()).isEqualTo(UPDATED_VOLUME);
        assertThat(testProductVoice.isRoamingFlag()).isEqualTo(UPDATED_ROAMING_FLAG);
        assertThat(testProductVoice.getMinTransferQuota()).isEqualTo(UPDATED_MIN_TRANSFER_QUOTA);
        assertThat(testProductVoice.getMaxTransferQuota()).isEqualTo(UPDATED_MAX_TRANSFER_QUOTA);
        assertThat(testProductVoice.getMinRetentionQuota()).isEqualTo(UPDATED_MIN_RETENTION_QUOTA);
        assertThat(testProductVoice.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProductVoice.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductVoice.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testProductVoice.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testProductVoice.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingProductVoice() throws Exception {
        int databaseSizeBeforeUpdate = productVoiceRepository.findAll().size();

        // Create the ProductVoice

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductVoiceMockMvc.perform(put("/api/product-voices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productVoice)))
            .andExpect(status().isBadRequest());

        // Validate the ProductVoice in the database
        List<ProductVoice> productVoiceList = productVoiceRepository.findAll();
        assertThat(productVoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteProductVoice() throws Exception {
        // Initialize the database
        productVoiceService.save(productVoice);

        int databaseSizeBeforeDelete = productVoiceRepository.findAll().size();

        // Delete the productVoice
        restProductVoiceMockMvc.perform(delete("/api/product-voices/{id}", productVoice.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductVoice> productVoiceList = productVoiceRepository.findAll();
        assertThat(productVoiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
