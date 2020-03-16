package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.ProductBoxType;
import com.hthk.crm.repository.ProductBoxTypeRepository;
import com.hthk.crm.service.ProductBoxTypeService;
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
 * Integration tests for the {@link ProductBoxTypeResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class ProductBoxTypeResourceIT {

    private static final String DEFAULT_BOX_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_BOX_TYPE = "BBBBBBBBBB";

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
    private ProductBoxTypeRepository productBoxTypeRepository;

    @Autowired
    private ProductBoxTypeService productBoxTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restProductBoxTypeMockMvc;

    private ProductBoxType productBoxType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductBoxTypeResource productBoxTypeResource = new ProductBoxTypeResource(productBoxTypeService);
        this.restProductBoxTypeMockMvc = MockMvcBuilders.standaloneSetup(productBoxTypeResource)
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
    public static ProductBoxType createEntity() {
        ProductBoxType productBoxType = new ProductBoxType()
            .boxType(DEFAULT_BOX_TYPE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return productBoxType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductBoxType createUpdatedEntity() {
        ProductBoxType productBoxType = new ProductBoxType()
            .boxType(UPDATED_BOX_TYPE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return productBoxType;
    }

    @BeforeEach
    public void initTest() {
        productBoxTypeRepository.deleteAll();
        productBoxType = createEntity();
    }

    @Test
    public void createProductBoxType() throws Exception {
        int databaseSizeBeforeCreate = productBoxTypeRepository.findAll().size();

        // Create the ProductBoxType
        restProductBoxTypeMockMvc.perform(post("/api/product-box-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productBoxType)))
            .andExpect(status().isCreated());

        // Validate the ProductBoxType in the database
        List<ProductBoxType> productBoxTypeList = productBoxTypeRepository.findAll();
        assertThat(productBoxTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ProductBoxType testProductBoxType = productBoxTypeList.get(productBoxTypeList.size() - 1);
        assertThat(testProductBoxType.getBoxType()).isEqualTo(DEFAULT_BOX_TYPE);
        assertThat(testProductBoxType.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProductBoxType.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductBoxType.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testProductBoxType.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testProductBoxType.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createProductBoxTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productBoxTypeRepository.findAll().size();

        // Create the ProductBoxType with an existing ID
        productBoxType.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductBoxTypeMockMvc.perform(post("/api/product-box-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productBoxType)))
            .andExpect(status().isBadRequest());

        // Validate the ProductBoxType in the database
        List<ProductBoxType> productBoxTypeList = productBoxTypeRepository.findAll();
        assertThat(productBoxTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkBoxTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productBoxTypeRepository.findAll().size();
        // set the field null
        productBoxType.setBoxType(null);

        // Create the ProductBoxType, which fails.

        restProductBoxTypeMockMvc.perform(post("/api/product-box-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productBoxType)))
            .andExpect(status().isBadRequest());

        List<ProductBoxType> productBoxTypeList = productBoxTypeRepository.findAll();
        assertThat(productBoxTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productBoxTypeRepository.findAll().size();
        // set the field null
        productBoxType.setCreatedDate(null);

        // Create the ProductBoxType, which fails.

        restProductBoxTypeMockMvc.perform(post("/api/product-box-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productBoxType)))
            .andExpect(status().isBadRequest());

        List<ProductBoxType> productBoxTypeList = productBoxTypeRepository.findAll();
        assertThat(productBoxTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productBoxTypeRepository.findAll().size();
        // set the field null
        productBoxType.setCreatedBy(null);

        // Create the ProductBoxType, which fails.

        restProductBoxTypeMockMvc.perform(post("/api/product-box-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productBoxType)))
            .andExpect(status().isBadRequest());

        List<ProductBoxType> productBoxTypeList = productBoxTypeRepository.findAll();
        assertThat(productBoxTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productBoxTypeRepository.findAll().size();
        // set the field null
        productBoxType.setLastUpdatedDate(null);

        // Create the ProductBoxType, which fails.

        restProductBoxTypeMockMvc.perform(post("/api/product-box-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productBoxType)))
            .andExpect(status().isBadRequest());

        List<ProductBoxType> productBoxTypeList = productBoxTypeRepository.findAll();
        assertThat(productBoxTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productBoxTypeRepository.findAll().size();
        // set the field null
        productBoxType.setLastUpdatedBy(null);

        // Create the ProductBoxType, which fails.

        restProductBoxTypeMockMvc.perform(post("/api/product-box-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productBoxType)))
            .andExpect(status().isBadRequest());

        List<ProductBoxType> productBoxTypeList = productBoxTypeRepository.findAll();
        assertThat(productBoxTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productBoxTypeRepository.findAll().size();
        // set the field null
        productBoxType.setTenantId(null);

        // Create the ProductBoxType, which fails.

        restProductBoxTypeMockMvc.perform(post("/api/product-box-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productBoxType)))
            .andExpect(status().isBadRequest());

        List<ProductBoxType> productBoxTypeList = productBoxTypeRepository.findAll();
        assertThat(productBoxTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProductBoxTypes() throws Exception {
        // Initialize the database
        productBoxTypeRepository.save(productBoxType);

        // Get all the productBoxTypeList
        restProductBoxTypeMockMvc.perform(get("/api/product-box-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productBoxType.getId())))
            .andExpect(jsonPath("$.[*].boxType").value(hasItem(DEFAULT_BOX_TYPE)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getProductBoxType() throws Exception {
        // Initialize the database
        productBoxTypeRepository.save(productBoxType);

        // Get the productBoxType
        restProductBoxTypeMockMvc.perform(get("/api/product-box-types/{id}", productBoxType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productBoxType.getId()))
            .andExpect(jsonPath("$.boxType").value(DEFAULT_BOX_TYPE))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingProductBoxType() throws Exception {
        // Get the productBoxType
        restProductBoxTypeMockMvc.perform(get("/api/product-box-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProductBoxType() throws Exception {
        // Initialize the database
        productBoxTypeService.save(productBoxType);

        int databaseSizeBeforeUpdate = productBoxTypeRepository.findAll().size();

        // Update the productBoxType
        ProductBoxType updatedProductBoxType = productBoxTypeRepository.findById(productBoxType.getId()).get();
        updatedProductBoxType
            .boxType(UPDATED_BOX_TYPE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restProductBoxTypeMockMvc.perform(put("/api/product-box-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductBoxType)))
            .andExpect(status().isOk());

        // Validate the ProductBoxType in the database
        List<ProductBoxType> productBoxTypeList = productBoxTypeRepository.findAll();
        assertThat(productBoxTypeList).hasSize(databaseSizeBeforeUpdate);
        ProductBoxType testProductBoxType = productBoxTypeList.get(productBoxTypeList.size() - 1);
        assertThat(testProductBoxType.getBoxType()).isEqualTo(UPDATED_BOX_TYPE);
        assertThat(testProductBoxType.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProductBoxType.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductBoxType.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testProductBoxType.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testProductBoxType.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingProductBoxType() throws Exception {
        int databaseSizeBeforeUpdate = productBoxTypeRepository.findAll().size();

        // Create the ProductBoxType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductBoxTypeMockMvc.perform(put("/api/product-box-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productBoxType)))
            .andExpect(status().isBadRequest());

        // Validate the ProductBoxType in the database
        List<ProductBoxType> productBoxTypeList = productBoxTypeRepository.findAll();
        assertThat(productBoxTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteProductBoxType() throws Exception {
        // Initialize the database
        productBoxTypeService.save(productBoxType);

        int databaseSizeBeforeDelete = productBoxTypeRepository.findAll().size();

        // Delete the productBoxType
        restProductBoxTypeMockMvc.perform(delete("/api/product-box-types/{id}", productBoxType.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductBoxType> productBoxTypeList = productBoxTypeRepository.findAll();
        assertThat(productBoxTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
