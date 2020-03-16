package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.ProductAttributes;
import com.hthk.crm.repository.ProductAttributesRepository;
import com.hthk.crm.service.ProductAttributesService;
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

import com.hthk.crm.domain.enumeration.SkuType;
/**
 * Integration tests for the {@link ProductAttributesResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class ProductAttributesResourceIT {

    private static final SkuType DEFAULT_SKU_TYPE = SkuType.DEVICE;
    private static final SkuType UPDATED_SKU_TYPE = SkuType.ACCESSORY;

    private static final Boolean DEFAULT_SHIPPABLE = false;
    private static final Boolean UPDATED_SHIPPABLE = true;

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
    private ProductAttributesRepository productAttributesRepository;

    @Autowired
    private ProductAttributesService productAttributesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restProductAttributesMockMvc;

    private ProductAttributes productAttributes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductAttributesResource productAttributesResource = new ProductAttributesResource(productAttributesService);
        this.restProductAttributesMockMvc = MockMvcBuilders.standaloneSetup(productAttributesResource)
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
    public static ProductAttributes createEntity() {
        ProductAttributes productAttributes = new ProductAttributes()
            .skuType(DEFAULT_SKU_TYPE)
            .shippable(DEFAULT_SHIPPABLE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return productAttributes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductAttributes createUpdatedEntity() {
        ProductAttributes productAttributes = new ProductAttributes()
            .skuType(UPDATED_SKU_TYPE)
            .shippable(UPDATED_SHIPPABLE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return productAttributes;
    }

    @BeforeEach
    public void initTest() {
        productAttributesRepository.deleteAll();
        productAttributes = createEntity();
    }

    @Test
    public void createProductAttributes() throws Exception {
        int databaseSizeBeforeCreate = productAttributesRepository.findAll().size();

        // Create the ProductAttributes
        restProductAttributesMockMvc.perform(post("/api/product-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productAttributes)))
            .andExpect(status().isCreated());

        // Validate the ProductAttributes in the database
        List<ProductAttributes> productAttributesList = productAttributesRepository.findAll();
        assertThat(productAttributesList).hasSize(databaseSizeBeforeCreate + 1);
        ProductAttributes testProductAttributes = productAttributesList.get(productAttributesList.size() - 1);
        assertThat(testProductAttributes.getSkuType()).isEqualTo(DEFAULT_SKU_TYPE);
        assertThat(testProductAttributes.isShippable()).isEqualTo(DEFAULT_SHIPPABLE);
        assertThat(testProductAttributes.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProductAttributes.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductAttributes.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testProductAttributes.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testProductAttributes.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createProductAttributesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productAttributesRepository.findAll().size();

        // Create the ProductAttributes with an existing ID
        productAttributes.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductAttributesMockMvc.perform(post("/api/product-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productAttributes)))
            .andExpect(status().isBadRequest());

        // Validate the ProductAttributes in the database
        List<ProductAttributes> productAttributesList = productAttributesRepository.findAll();
        assertThat(productAttributesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productAttributesRepository.findAll().size();
        // set the field null
        productAttributes.setCreatedDate(null);

        // Create the ProductAttributes, which fails.

        restProductAttributesMockMvc.perform(post("/api/product-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productAttributes)))
            .andExpect(status().isBadRequest());

        List<ProductAttributes> productAttributesList = productAttributesRepository.findAll();
        assertThat(productAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productAttributesRepository.findAll().size();
        // set the field null
        productAttributes.setCreatedBy(null);

        // Create the ProductAttributes, which fails.

        restProductAttributesMockMvc.perform(post("/api/product-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productAttributes)))
            .andExpect(status().isBadRequest());

        List<ProductAttributes> productAttributesList = productAttributesRepository.findAll();
        assertThat(productAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productAttributesRepository.findAll().size();
        // set the field null
        productAttributes.setLastUpdatedDate(null);

        // Create the ProductAttributes, which fails.

        restProductAttributesMockMvc.perform(post("/api/product-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productAttributes)))
            .andExpect(status().isBadRequest());

        List<ProductAttributes> productAttributesList = productAttributesRepository.findAll();
        assertThat(productAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productAttributesRepository.findAll().size();
        // set the field null
        productAttributes.setLastUpdatedBy(null);

        // Create the ProductAttributes, which fails.

        restProductAttributesMockMvc.perform(post("/api/product-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productAttributes)))
            .andExpect(status().isBadRequest());

        List<ProductAttributes> productAttributesList = productAttributesRepository.findAll();
        assertThat(productAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productAttributesRepository.findAll().size();
        // set the field null
        productAttributes.setTenantId(null);

        // Create the ProductAttributes, which fails.

        restProductAttributesMockMvc.perform(post("/api/product-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productAttributes)))
            .andExpect(status().isBadRequest());

        List<ProductAttributes> productAttributesList = productAttributesRepository.findAll();
        assertThat(productAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProductAttributes() throws Exception {
        // Initialize the database
        productAttributesRepository.save(productAttributes);

        // Get all the productAttributesList
        restProductAttributesMockMvc.perform(get("/api/product-attributes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productAttributes.getId())))
            .andExpect(jsonPath("$.[*].skuType").value(hasItem(DEFAULT_SKU_TYPE.toString())))
            .andExpect(jsonPath("$.[*].shippable").value(hasItem(DEFAULT_SHIPPABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getProductAttributes() throws Exception {
        // Initialize the database
        productAttributesRepository.save(productAttributes);

        // Get the productAttributes
        restProductAttributesMockMvc.perform(get("/api/product-attributes/{id}", productAttributes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productAttributes.getId()))
            .andExpect(jsonPath("$.skuType").value(DEFAULT_SKU_TYPE.toString()))
            .andExpect(jsonPath("$.shippable").value(DEFAULT_SHIPPABLE.booleanValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingProductAttributes() throws Exception {
        // Get the productAttributes
        restProductAttributesMockMvc.perform(get("/api/product-attributes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProductAttributes() throws Exception {
        // Initialize the database
        productAttributesService.save(productAttributes);

        int databaseSizeBeforeUpdate = productAttributesRepository.findAll().size();

        // Update the productAttributes
        ProductAttributes updatedProductAttributes = productAttributesRepository.findById(productAttributes.getId()).get();
        updatedProductAttributes
            .skuType(UPDATED_SKU_TYPE)
            .shippable(UPDATED_SHIPPABLE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restProductAttributesMockMvc.perform(put("/api/product-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductAttributes)))
            .andExpect(status().isOk());

        // Validate the ProductAttributes in the database
        List<ProductAttributes> productAttributesList = productAttributesRepository.findAll();
        assertThat(productAttributesList).hasSize(databaseSizeBeforeUpdate);
        ProductAttributes testProductAttributes = productAttributesList.get(productAttributesList.size() - 1);
        assertThat(testProductAttributes.getSkuType()).isEqualTo(UPDATED_SKU_TYPE);
        assertThat(testProductAttributes.isShippable()).isEqualTo(UPDATED_SHIPPABLE);
        assertThat(testProductAttributes.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProductAttributes.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductAttributes.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testProductAttributes.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testProductAttributes.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingProductAttributes() throws Exception {
        int databaseSizeBeforeUpdate = productAttributesRepository.findAll().size();

        // Create the ProductAttributes

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductAttributesMockMvc.perform(put("/api/product-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productAttributes)))
            .andExpect(status().isBadRequest());

        // Validate the ProductAttributes in the database
        List<ProductAttributes> productAttributesList = productAttributesRepository.findAll();
        assertThat(productAttributesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteProductAttributes() throws Exception {
        // Initialize the database
        productAttributesService.save(productAttributes);

        int databaseSizeBeforeDelete = productAttributesRepository.findAll().size();

        // Delete the productAttributes
        restProductAttributesMockMvc.perform(delete("/api/product-attributes/{id}", productAttributes.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductAttributes> productAttributesList = productAttributesRepository.findAll();
        assertThat(productAttributesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
