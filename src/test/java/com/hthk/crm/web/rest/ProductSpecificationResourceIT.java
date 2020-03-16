package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.ProductSpecification;
import com.hthk.crm.repository.ProductSpecificationRepository;
import com.hthk.crm.service.ProductSpecificationService;
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

import com.hthk.crm.domain.enumeration.ProductSpecType;
import com.hthk.crm.domain.enumeration.SkuType;
import com.hthk.crm.domain.enumeration.SimType;
import com.hthk.crm.domain.enumeration.BoxType;
/**
 * Integration tests for the {@link ProductSpecificationResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class ProductSpecificationResourceIT {

    private static final Long DEFAULT_PRODUCT_SPEC_ID = 1L;
    private static final Long UPDATED_PRODUCT_SPEC_ID = 2L;

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_SPEC_ID = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_SPEC_ID = "BBBBBBBBBB";

    private static final ProductSpecType DEFAULT_PRODUCT_SPEC_TYPE = ProductSpecType.DEVICE;
    private static final ProductSpecType UPDATED_PRODUCT_SPEC_TYPE = ProductSpecType.ACCESSORY;

    private static final SkuType DEFAULT_SKU_TYPE = SkuType.DEVICE;
    private static final SkuType UPDATED_SKU_TYPE = SkuType.ACCESSORY;

    private static final SimType DEFAULT_SIM_TYPE = SimType.PHYSICAL_SIM;
    private static final SimType UPDATED_SIM_TYPE = SimType.ESIM;

    private static final BoxType DEFAULT_BOX_TYPE = BoxType.TRAVEL;
    private static final BoxType UPDATED_BOX_TYPE = BoxType.BEAUTY;

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
    private ProductSpecificationRepository productSpecificationRepository;

    @Autowired
    private ProductSpecificationService productSpecificationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restProductSpecificationMockMvc;

    private ProductSpecification productSpecification;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductSpecificationResource productSpecificationResource = new ProductSpecificationResource(productSpecificationService);
        this.restProductSpecificationMockMvc = MockMvcBuilders.standaloneSetup(productSpecificationResource)
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
    public static ProductSpecification createEntity() {
        ProductSpecification productSpecification = new ProductSpecification()
            .productSpecId(DEFAULT_PRODUCT_SPEC_ID)
            .productId(DEFAULT_PRODUCT_ID)
            .serviceSpecId(DEFAULT_SERVICE_SPEC_ID)
            .productSpecType(DEFAULT_PRODUCT_SPEC_TYPE)
            .skuType(DEFAULT_SKU_TYPE)
            .simType(DEFAULT_SIM_TYPE)
            .boxType(DEFAULT_BOX_TYPE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return productSpecification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductSpecification createUpdatedEntity() {
        ProductSpecification productSpecification = new ProductSpecification()
            .productSpecId(UPDATED_PRODUCT_SPEC_ID)
            .productId(UPDATED_PRODUCT_ID)
            .serviceSpecId(UPDATED_SERVICE_SPEC_ID)
            .productSpecType(UPDATED_PRODUCT_SPEC_TYPE)
            .skuType(UPDATED_SKU_TYPE)
            .simType(UPDATED_SIM_TYPE)
            .boxType(UPDATED_BOX_TYPE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return productSpecification;
    }

    @BeforeEach
    public void initTest() {
        productSpecificationRepository.deleteAll();
        productSpecification = createEntity();
    }

    @Test
    public void createProductSpecification() throws Exception {
        int databaseSizeBeforeCreate = productSpecificationRepository.findAll().size();

        // Create the ProductSpecification
        restProductSpecificationMockMvc.perform(post("/api/product-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSpecification)))
            .andExpect(status().isCreated());

        // Validate the ProductSpecification in the database
        List<ProductSpecification> productSpecificationList = productSpecificationRepository.findAll();
        assertThat(productSpecificationList).hasSize(databaseSizeBeforeCreate + 1);
        ProductSpecification testProductSpecification = productSpecificationList.get(productSpecificationList.size() - 1);
        assertThat(testProductSpecification.getProductSpecId()).isEqualTo(DEFAULT_PRODUCT_SPEC_ID);
        assertThat(testProductSpecification.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testProductSpecification.getServiceSpecId()).isEqualTo(DEFAULT_SERVICE_SPEC_ID);
        assertThat(testProductSpecification.getProductSpecType()).isEqualTo(DEFAULT_PRODUCT_SPEC_TYPE);
        assertThat(testProductSpecification.getSkuType()).isEqualTo(DEFAULT_SKU_TYPE);
        assertThat(testProductSpecification.getSimType()).isEqualTo(DEFAULT_SIM_TYPE);
        assertThat(testProductSpecification.getBoxType()).isEqualTo(DEFAULT_BOX_TYPE);
        assertThat(testProductSpecification.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProductSpecification.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductSpecification.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testProductSpecification.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testProductSpecification.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createProductSpecificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productSpecificationRepository.findAll().size();

        // Create the ProductSpecification with an existing ID
        productSpecification.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductSpecificationMockMvc.perform(post("/api/product-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSpecification)))
            .andExpect(status().isBadRequest());

        // Validate the ProductSpecification in the database
        List<ProductSpecification> productSpecificationList = productSpecificationRepository.findAll();
        assertThat(productSpecificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkProductSpecIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSpecificationRepository.findAll().size();
        // set the field null
        productSpecification.setProductSpecId(null);

        // Create the ProductSpecification, which fails.

        restProductSpecificationMockMvc.perform(post("/api/product-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSpecification)))
            .andExpect(status().isBadRequest());

        List<ProductSpecification> productSpecificationList = productSpecificationRepository.findAll();
        assertThat(productSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkProductIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSpecificationRepository.findAll().size();
        // set the field null
        productSpecification.setProductId(null);

        // Create the ProductSpecification, which fails.

        restProductSpecificationMockMvc.perform(post("/api/product-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSpecification)))
            .andExpect(status().isBadRequest());

        List<ProductSpecification> productSpecificationList = productSpecificationRepository.findAll();
        assertThat(productSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSpecificationRepository.findAll().size();
        // set the field null
        productSpecification.setCreatedDate(null);

        // Create the ProductSpecification, which fails.

        restProductSpecificationMockMvc.perform(post("/api/product-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSpecification)))
            .andExpect(status().isBadRequest());

        List<ProductSpecification> productSpecificationList = productSpecificationRepository.findAll();
        assertThat(productSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSpecificationRepository.findAll().size();
        // set the field null
        productSpecification.setCreatedBy(null);

        // Create the ProductSpecification, which fails.

        restProductSpecificationMockMvc.perform(post("/api/product-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSpecification)))
            .andExpect(status().isBadRequest());

        List<ProductSpecification> productSpecificationList = productSpecificationRepository.findAll();
        assertThat(productSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSpecificationRepository.findAll().size();
        // set the field null
        productSpecification.setLastUpdatedDate(null);

        // Create the ProductSpecification, which fails.

        restProductSpecificationMockMvc.perform(post("/api/product-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSpecification)))
            .andExpect(status().isBadRequest());

        List<ProductSpecification> productSpecificationList = productSpecificationRepository.findAll();
        assertThat(productSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSpecificationRepository.findAll().size();
        // set the field null
        productSpecification.setLastUpdatedBy(null);

        // Create the ProductSpecification, which fails.

        restProductSpecificationMockMvc.perform(post("/api/product-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSpecification)))
            .andExpect(status().isBadRequest());

        List<ProductSpecification> productSpecificationList = productSpecificationRepository.findAll();
        assertThat(productSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSpecificationRepository.findAll().size();
        // set the field null
        productSpecification.setTenantId(null);

        // Create the ProductSpecification, which fails.

        restProductSpecificationMockMvc.perform(post("/api/product-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSpecification)))
            .andExpect(status().isBadRequest());

        List<ProductSpecification> productSpecificationList = productSpecificationRepository.findAll();
        assertThat(productSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProductSpecifications() throws Exception {
        // Initialize the database
        productSpecificationRepository.save(productSpecification);

        // Get all the productSpecificationList
        restProductSpecificationMockMvc.perform(get("/api/product-specifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productSpecification.getId())))
            .andExpect(jsonPath("$.[*].productSpecId").value(hasItem(DEFAULT_PRODUCT_SPEC_ID.intValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].serviceSpecId").value(hasItem(DEFAULT_SERVICE_SPEC_ID)))
            .andExpect(jsonPath("$.[*].productSpecType").value(hasItem(DEFAULT_PRODUCT_SPEC_TYPE.toString())))
            .andExpect(jsonPath("$.[*].skuType").value(hasItem(DEFAULT_SKU_TYPE.toString())))
            .andExpect(jsonPath("$.[*].simType").value(hasItem(DEFAULT_SIM_TYPE.toString())))
            .andExpect(jsonPath("$.[*].boxType").value(hasItem(DEFAULT_BOX_TYPE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getProductSpecification() throws Exception {
        // Initialize the database
        productSpecificationRepository.save(productSpecification);

        // Get the productSpecification
        restProductSpecificationMockMvc.perform(get("/api/product-specifications/{id}", productSpecification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productSpecification.getId()))
            .andExpect(jsonPath("$.productSpecId").value(DEFAULT_PRODUCT_SPEC_ID.intValue()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.serviceSpecId").value(DEFAULT_SERVICE_SPEC_ID))
            .andExpect(jsonPath("$.productSpecType").value(DEFAULT_PRODUCT_SPEC_TYPE.toString()))
            .andExpect(jsonPath("$.skuType").value(DEFAULT_SKU_TYPE.toString()))
            .andExpect(jsonPath("$.simType").value(DEFAULT_SIM_TYPE.toString()))
            .andExpect(jsonPath("$.boxType").value(DEFAULT_BOX_TYPE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingProductSpecification() throws Exception {
        // Get the productSpecification
        restProductSpecificationMockMvc.perform(get("/api/product-specifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProductSpecification() throws Exception {
        // Initialize the database
        productSpecificationService.save(productSpecification);

        int databaseSizeBeforeUpdate = productSpecificationRepository.findAll().size();

        // Update the productSpecification
        ProductSpecification updatedProductSpecification = productSpecificationRepository.findById(productSpecification.getId()).get();
        updatedProductSpecification
            .productSpecId(UPDATED_PRODUCT_SPEC_ID)
            .productId(UPDATED_PRODUCT_ID)
            .serviceSpecId(UPDATED_SERVICE_SPEC_ID)
            .productSpecType(UPDATED_PRODUCT_SPEC_TYPE)
            .skuType(UPDATED_SKU_TYPE)
            .simType(UPDATED_SIM_TYPE)
            .boxType(UPDATED_BOX_TYPE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restProductSpecificationMockMvc.perform(put("/api/product-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductSpecification)))
            .andExpect(status().isOk());

        // Validate the ProductSpecification in the database
        List<ProductSpecification> productSpecificationList = productSpecificationRepository.findAll();
        assertThat(productSpecificationList).hasSize(databaseSizeBeforeUpdate);
        ProductSpecification testProductSpecification = productSpecificationList.get(productSpecificationList.size() - 1);
        assertThat(testProductSpecification.getProductSpecId()).isEqualTo(UPDATED_PRODUCT_SPEC_ID);
        assertThat(testProductSpecification.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testProductSpecification.getServiceSpecId()).isEqualTo(UPDATED_SERVICE_SPEC_ID);
        assertThat(testProductSpecification.getProductSpecType()).isEqualTo(UPDATED_PRODUCT_SPEC_TYPE);
        assertThat(testProductSpecification.getSkuType()).isEqualTo(UPDATED_SKU_TYPE);
        assertThat(testProductSpecification.getSimType()).isEqualTo(UPDATED_SIM_TYPE);
        assertThat(testProductSpecification.getBoxType()).isEqualTo(UPDATED_BOX_TYPE);
        assertThat(testProductSpecification.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProductSpecification.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductSpecification.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testProductSpecification.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testProductSpecification.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingProductSpecification() throws Exception {
        int databaseSizeBeforeUpdate = productSpecificationRepository.findAll().size();

        // Create the ProductSpecification

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductSpecificationMockMvc.perform(put("/api/product-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSpecification)))
            .andExpect(status().isBadRequest());

        // Validate the ProductSpecification in the database
        List<ProductSpecification> productSpecificationList = productSpecificationRepository.findAll();
        assertThat(productSpecificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteProductSpecification() throws Exception {
        // Initialize the database
        productSpecificationService.save(productSpecification);

        int databaseSizeBeforeDelete = productSpecificationRepository.findAll().size();

        // Delete the productSpecification
        restProductSpecificationMockMvc.perform(delete("/api/product-specifications/{id}", productSpecification.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductSpecification> productSpecificationList = productSpecificationRepository.findAll();
        assertThat(productSpecificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
