package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.ProductSms;
import com.hthk.crm.repository.ProductSmsRepository;
import com.hthk.crm.service.ProductSmsService;
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

import com.hthk.crm.domain.enumeration.SmsType;
/**
 * Integration tests for the {@link ProductSmsResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class ProductSmsResourceIT {

    private static final String DEFAULT_SMS_ID = "AAAAAAAAAA";
    private static final String UPDATED_SMS_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_PRODUCT_SPEC_ID = 1L;
    private static final Long UPDATED_PRODUCT_SPEC_ID = 2L;

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final Integer DEFAULT_VOLUME = 1;
    private static final Integer UPDATED_VOLUME = 2;

    private static final SmsType DEFAULT_SMS_TYPE = SmsType.LOCAL;
    private static final SmsType UPDATED_SMS_TYPE = SmsType.INTERNATIONAL;

    private static final Boolean DEFAULT_ROAMING_ALLOWED = false;
    private static final Boolean UPDATED_ROAMING_ALLOWED = true;

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
    private ProductSmsRepository productSmsRepository;

    @Autowired
    private ProductSmsService productSmsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restProductSmsMockMvc;

    private ProductSms productSms;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductSmsResource productSmsResource = new ProductSmsResource(productSmsService);
        this.restProductSmsMockMvc = MockMvcBuilders.standaloneSetup(productSmsResource)
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
    public static ProductSms createEntity() {
        ProductSms productSms = new ProductSms()
            .smsId(DEFAULT_SMS_ID)
            .productSpecId(DEFAULT_PRODUCT_SPEC_ID)
            .unit(DEFAULT_UNIT)
            .volume(DEFAULT_VOLUME)
            .smsType(DEFAULT_SMS_TYPE)
            .roamingAllowed(DEFAULT_ROAMING_ALLOWED)
            .minTransferQuota(DEFAULT_MIN_TRANSFER_QUOTA)
            .maxTransferQuota(DEFAULT_MAX_TRANSFER_QUOTA)
            .minRetentionQuota(DEFAULT_MIN_RETENTION_QUOTA)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return productSms;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductSms createUpdatedEntity() {
        ProductSms productSms = new ProductSms()
            .smsId(UPDATED_SMS_ID)
            .productSpecId(UPDATED_PRODUCT_SPEC_ID)
            .unit(UPDATED_UNIT)
            .volume(UPDATED_VOLUME)
            .smsType(UPDATED_SMS_TYPE)
            .roamingAllowed(UPDATED_ROAMING_ALLOWED)
            .minTransferQuota(UPDATED_MIN_TRANSFER_QUOTA)
            .maxTransferQuota(UPDATED_MAX_TRANSFER_QUOTA)
            .minRetentionQuota(UPDATED_MIN_RETENTION_QUOTA)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return productSms;
    }

    @BeforeEach
    public void initTest() {
        productSmsRepository.deleteAll();
        productSms = createEntity();
    }

    @Test
    public void createProductSms() throws Exception {
        int databaseSizeBeforeCreate = productSmsRepository.findAll().size();

        // Create the ProductSms
        restProductSmsMockMvc.perform(post("/api/product-sms")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSms)))
            .andExpect(status().isCreated());

        // Validate the ProductSms in the database
        List<ProductSms> productSmsList = productSmsRepository.findAll();
        assertThat(productSmsList).hasSize(databaseSizeBeforeCreate + 1);
        ProductSms testProductSms = productSmsList.get(productSmsList.size() - 1);
        assertThat(testProductSms.getSmsId()).isEqualTo(DEFAULT_SMS_ID);
        assertThat(testProductSms.getProductSpecId()).isEqualTo(DEFAULT_PRODUCT_SPEC_ID);
        assertThat(testProductSms.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testProductSms.getVolume()).isEqualTo(DEFAULT_VOLUME);
        assertThat(testProductSms.getSmsType()).isEqualTo(DEFAULT_SMS_TYPE);
        assertThat(testProductSms.isRoamingAllowed()).isEqualTo(DEFAULT_ROAMING_ALLOWED);
        assertThat(testProductSms.getMinTransferQuota()).isEqualTo(DEFAULT_MIN_TRANSFER_QUOTA);
        assertThat(testProductSms.getMaxTransferQuota()).isEqualTo(DEFAULT_MAX_TRANSFER_QUOTA);
        assertThat(testProductSms.getMinRetentionQuota()).isEqualTo(DEFAULT_MIN_RETENTION_QUOTA);
        assertThat(testProductSms.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProductSms.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductSms.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testProductSms.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testProductSms.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createProductSmsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productSmsRepository.findAll().size();

        // Create the ProductSms with an existing ID
        productSms.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductSmsMockMvc.perform(post("/api/product-sms")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSms)))
            .andExpect(status().isBadRequest());

        // Validate the ProductSms in the database
        List<ProductSms> productSmsList = productSmsRepository.findAll();
        assertThat(productSmsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkProductSpecIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSmsRepository.findAll().size();
        // set the field null
        productSms.setProductSpecId(null);

        // Create the ProductSms, which fails.

        restProductSmsMockMvc.perform(post("/api/product-sms")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSms)))
            .andExpect(status().isBadRequest());

        List<ProductSms> productSmsList = productSmsRepository.findAll();
        assertThat(productSmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkUnitIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSmsRepository.findAll().size();
        // set the field null
        productSms.setUnit(null);

        // Create the ProductSms, which fails.

        restProductSmsMockMvc.perform(post("/api/product-sms")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSms)))
            .andExpect(status().isBadRequest());

        List<ProductSms> productSmsList = productSmsRepository.findAll();
        assertThat(productSmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkVolumeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSmsRepository.findAll().size();
        // set the field null
        productSms.setVolume(null);

        // Create the ProductSms, which fails.

        restProductSmsMockMvc.perform(post("/api/product-sms")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSms)))
            .andExpect(status().isBadRequest());

        List<ProductSms> productSmsList = productSmsRepository.findAll();
        assertThat(productSmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSmsRepository.findAll().size();
        // set the field null
        productSms.setCreatedDate(null);

        // Create the ProductSms, which fails.

        restProductSmsMockMvc.perform(post("/api/product-sms")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSms)))
            .andExpect(status().isBadRequest());

        List<ProductSms> productSmsList = productSmsRepository.findAll();
        assertThat(productSmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSmsRepository.findAll().size();
        // set the field null
        productSms.setCreatedBy(null);

        // Create the ProductSms, which fails.

        restProductSmsMockMvc.perform(post("/api/product-sms")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSms)))
            .andExpect(status().isBadRequest());

        List<ProductSms> productSmsList = productSmsRepository.findAll();
        assertThat(productSmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSmsRepository.findAll().size();
        // set the field null
        productSms.setLastUpdatedDate(null);

        // Create the ProductSms, which fails.

        restProductSmsMockMvc.perform(post("/api/product-sms")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSms)))
            .andExpect(status().isBadRequest());

        List<ProductSms> productSmsList = productSmsRepository.findAll();
        assertThat(productSmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSmsRepository.findAll().size();
        // set the field null
        productSms.setLastUpdatedBy(null);

        // Create the ProductSms, which fails.

        restProductSmsMockMvc.perform(post("/api/product-sms")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSms)))
            .andExpect(status().isBadRequest());

        List<ProductSms> productSmsList = productSmsRepository.findAll();
        assertThat(productSmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSmsRepository.findAll().size();
        // set the field null
        productSms.setTenantId(null);

        // Create the ProductSms, which fails.

        restProductSmsMockMvc.perform(post("/api/product-sms")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSms)))
            .andExpect(status().isBadRequest());

        List<ProductSms> productSmsList = productSmsRepository.findAll();
        assertThat(productSmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProductSms() throws Exception {
        // Initialize the database
        productSmsRepository.save(productSms);

        // Get all the productSmsList
        restProductSmsMockMvc.perform(get("/api/product-sms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productSms.getId())))
            .andExpect(jsonPath("$.[*].smsId").value(hasItem(DEFAULT_SMS_ID)))
            .andExpect(jsonPath("$.[*].productSpecId").value(hasItem(DEFAULT_PRODUCT_SPEC_ID.intValue())))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].volume").value(hasItem(DEFAULT_VOLUME)))
            .andExpect(jsonPath("$.[*].smsType").value(hasItem(DEFAULT_SMS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].roamingAllowed").value(hasItem(DEFAULT_ROAMING_ALLOWED.booleanValue())))
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
    public void getProductSms() throws Exception {
        // Initialize the database
        productSmsRepository.save(productSms);

        // Get the productSms
        restProductSmsMockMvc.perform(get("/api/product-sms/{id}", productSms.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productSms.getId()))
            .andExpect(jsonPath("$.smsId").value(DEFAULT_SMS_ID))
            .andExpect(jsonPath("$.productSpecId").value(DEFAULT_PRODUCT_SPEC_ID.intValue()))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
            .andExpect(jsonPath("$.volume").value(DEFAULT_VOLUME))
            .andExpect(jsonPath("$.smsType").value(DEFAULT_SMS_TYPE.toString()))
            .andExpect(jsonPath("$.roamingAllowed").value(DEFAULT_ROAMING_ALLOWED.booleanValue()))
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
    public void getNonExistingProductSms() throws Exception {
        // Get the productSms
        restProductSmsMockMvc.perform(get("/api/product-sms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProductSms() throws Exception {
        // Initialize the database
        productSmsService.save(productSms);

        int databaseSizeBeforeUpdate = productSmsRepository.findAll().size();

        // Update the productSms
        ProductSms updatedProductSms = productSmsRepository.findById(productSms.getId()).get();
        updatedProductSms
            .smsId(UPDATED_SMS_ID)
            .productSpecId(UPDATED_PRODUCT_SPEC_ID)
            .unit(UPDATED_UNIT)
            .volume(UPDATED_VOLUME)
            .smsType(UPDATED_SMS_TYPE)
            .roamingAllowed(UPDATED_ROAMING_ALLOWED)
            .minTransferQuota(UPDATED_MIN_TRANSFER_QUOTA)
            .maxTransferQuota(UPDATED_MAX_TRANSFER_QUOTA)
            .minRetentionQuota(UPDATED_MIN_RETENTION_QUOTA)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restProductSmsMockMvc.perform(put("/api/product-sms")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductSms)))
            .andExpect(status().isOk());

        // Validate the ProductSms in the database
        List<ProductSms> productSmsList = productSmsRepository.findAll();
        assertThat(productSmsList).hasSize(databaseSizeBeforeUpdate);
        ProductSms testProductSms = productSmsList.get(productSmsList.size() - 1);
        assertThat(testProductSms.getSmsId()).isEqualTo(UPDATED_SMS_ID);
        assertThat(testProductSms.getProductSpecId()).isEqualTo(UPDATED_PRODUCT_SPEC_ID);
        assertThat(testProductSms.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testProductSms.getVolume()).isEqualTo(UPDATED_VOLUME);
        assertThat(testProductSms.getSmsType()).isEqualTo(UPDATED_SMS_TYPE);
        assertThat(testProductSms.isRoamingAllowed()).isEqualTo(UPDATED_ROAMING_ALLOWED);
        assertThat(testProductSms.getMinTransferQuota()).isEqualTo(UPDATED_MIN_TRANSFER_QUOTA);
        assertThat(testProductSms.getMaxTransferQuota()).isEqualTo(UPDATED_MAX_TRANSFER_QUOTA);
        assertThat(testProductSms.getMinRetentionQuota()).isEqualTo(UPDATED_MIN_RETENTION_QUOTA);
        assertThat(testProductSms.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProductSms.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductSms.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testProductSms.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testProductSms.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingProductSms() throws Exception {
        int databaseSizeBeforeUpdate = productSmsRepository.findAll().size();

        // Create the ProductSms

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductSmsMockMvc.perform(put("/api/product-sms")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSms)))
            .andExpect(status().isBadRequest());

        // Validate the ProductSms in the database
        List<ProductSms> productSmsList = productSmsRepository.findAll();
        assertThat(productSmsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteProductSms() throws Exception {
        // Initialize the database
        productSmsService.save(productSms);

        int databaseSizeBeforeDelete = productSmsRepository.findAll().size();

        // Delete the productSms
        restProductSmsMockMvc.perform(delete("/api/product-sms/{id}", productSms.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductSms> productSmsList = productSmsRepository.findAll();
        assertThat(productSmsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
