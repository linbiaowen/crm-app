package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.ProductSimAttributes;
import com.hthk.crm.repository.ProductSimAttributesRepository;
import com.hthk.crm.service.ProductSimAttributesService;
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

import com.hthk.crm.domain.enumeration.SimType;
/**
 * Integration tests for the {@link ProductSimAttributesResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class ProductSimAttributesResourceIT {

    private static final SimType DEFAULT_SIM_TYPE = SimType.PHYSICAL_SIM;
    private static final SimType UPDATED_SIM_TYPE = SimType.ESIM;

    private static final String DEFAULT_IMSI_RANGE_FROM = "AAAAAAAAAA";
    private static final String UPDATED_IMSI_RANGE_FROM = "BBBBBBBBBB";

    private static final String DEFAULT_IMSI_RANGE_TO = "AAAAAAAAAA";
    private static final String UPDATED_IMSI_RANGE_TO = "BBBBBBBBBB";

    private static final String DEFAULT_SIM_REPOSITORY_REF = "AAAAAAAAAA";
    private static final String UPDATED_SIM_REPOSITORY_REF = "BBBBBBBBBB";

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
    private ProductSimAttributesRepository productSimAttributesRepository;

    @Autowired
    private ProductSimAttributesService productSimAttributesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restProductSimAttributesMockMvc;

    private ProductSimAttributes productSimAttributes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductSimAttributesResource productSimAttributesResource = new ProductSimAttributesResource(productSimAttributesService);
        this.restProductSimAttributesMockMvc = MockMvcBuilders.standaloneSetup(productSimAttributesResource)
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
    public static ProductSimAttributes createEntity() {
        ProductSimAttributes productSimAttributes = new ProductSimAttributes()
            .simType(DEFAULT_SIM_TYPE)
            .imsiRangeFrom(DEFAULT_IMSI_RANGE_FROM)
            .imsiRangeTo(DEFAULT_IMSI_RANGE_TO)
            .simRepositoryRef(DEFAULT_SIM_REPOSITORY_REF)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return productSimAttributes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductSimAttributes createUpdatedEntity() {
        ProductSimAttributes productSimAttributes = new ProductSimAttributes()
            .simType(UPDATED_SIM_TYPE)
            .imsiRangeFrom(UPDATED_IMSI_RANGE_FROM)
            .imsiRangeTo(UPDATED_IMSI_RANGE_TO)
            .simRepositoryRef(UPDATED_SIM_REPOSITORY_REF)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return productSimAttributes;
    }

    @BeforeEach
    public void initTest() {
        productSimAttributesRepository.deleteAll();
        productSimAttributes = createEntity();
    }

    @Test
    public void createProductSimAttributes() throws Exception {
        int databaseSizeBeforeCreate = productSimAttributesRepository.findAll().size();

        // Create the ProductSimAttributes
        restProductSimAttributesMockMvc.perform(post("/api/product-sim-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimAttributes)))
            .andExpect(status().isCreated());

        // Validate the ProductSimAttributes in the database
        List<ProductSimAttributes> productSimAttributesList = productSimAttributesRepository.findAll();
        assertThat(productSimAttributesList).hasSize(databaseSizeBeforeCreate + 1);
        ProductSimAttributes testProductSimAttributes = productSimAttributesList.get(productSimAttributesList.size() - 1);
        assertThat(testProductSimAttributes.getSimType()).isEqualTo(DEFAULT_SIM_TYPE);
        assertThat(testProductSimAttributes.getImsiRangeFrom()).isEqualTo(DEFAULT_IMSI_RANGE_FROM);
        assertThat(testProductSimAttributes.getImsiRangeTo()).isEqualTo(DEFAULT_IMSI_RANGE_TO);
        assertThat(testProductSimAttributes.getSimRepositoryRef()).isEqualTo(DEFAULT_SIM_REPOSITORY_REF);
        assertThat(testProductSimAttributes.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProductSimAttributes.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductSimAttributes.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testProductSimAttributes.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testProductSimAttributes.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createProductSimAttributesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productSimAttributesRepository.findAll().size();

        // Create the ProductSimAttributes with an existing ID
        productSimAttributes.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductSimAttributesMockMvc.perform(post("/api/product-sim-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimAttributes)))
            .andExpect(status().isBadRequest());

        // Validate the ProductSimAttributes in the database
        List<ProductSimAttributes> productSimAttributesList = productSimAttributesRepository.findAll();
        assertThat(productSimAttributesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkSimTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSimAttributesRepository.findAll().size();
        // set the field null
        productSimAttributes.setSimType(null);

        // Create the ProductSimAttributes, which fails.

        restProductSimAttributesMockMvc.perform(post("/api/product-sim-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimAttributes)))
            .andExpect(status().isBadRequest());

        List<ProductSimAttributes> productSimAttributesList = productSimAttributesRepository.findAll();
        assertThat(productSimAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSimAttributesRepository.findAll().size();
        // set the field null
        productSimAttributes.setCreatedDate(null);

        // Create the ProductSimAttributes, which fails.

        restProductSimAttributesMockMvc.perform(post("/api/product-sim-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimAttributes)))
            .andExpect(status().isBadRequest());

        List<ProductSimAttributes> productSimAttributesList = productSimAttributesRepository.findAll();
        assertThat(productSimAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSimAttributesRepository.findAll().size();
        // set the field null
        productSimAttributes.setCreatedBy(null);

        // Create the ProductSimAttributes, which fails.

        restProductSimAttributesMockMvc.perform(post("/api/product-sim-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimAttributes)))
            .andExpect(status().isBadRequest());

        List<ProductSimAttributes> productSimAttributesList = productSimAttributesRepository.findAll();
        assertThat(productSimAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSimAttributesRepository.findAll().size();
        // set the field null
        productSimAttributes.setLastUpdatedDate(null);

        // Create the ProductSimAttributes, which fails.

        restProductSimAttributesMockMvc.perform(post("/api/product-sim-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimAttributes)))
            .andExpect(status().isBadRequest());

        List<ProductSimAttributes> productSimAttributesList = productSimAttributesRepository.findAll();
        assertThat(productSimAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSimAttributesRepository.findAll().size();
        // set the field null
        productSimAttributes.setLastUpdatedBy(null);

        // Create the ProductSimAttributes, which fails.

        restProductSimAttributesMockMvc.perform(post("/api/product-sim-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimAttributes)))
            .andExpect(status().isBadRequest());

        List<ProductSimAttributes> productSimAttributesList = productSimAttributesRepository.findAll();
        assertThat(productSimAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productSimAttributesRepository.findAll().size();
        // set the field null
        productSimAttributes.setTenantId(null);

        // Create the ProductSimAttributes, which fails.

        restProductSimAttributesMockMvc.perform(post("/api/product-sim-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimAttributes)))
            .andExpect(status().isBadRequest());

        List<ProductSimAttributes> productSimAttributesList = productSimAttributesRepository.findAll();
        assertThat(productSimAttributesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProductSimAttributes() throws Exception {
        // Initialize the database
        productSimAttributesRepository.save(productSimAttributes);

        // Get all the productSimAttributesList
        restProductSimAttributesMockMvc.perform(get("/api/product-sim-attributes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productSimAttributes.getId())))
            .andExpect(jsonPath("$.[*].simType").value(hasItem(DEFAULT_SIM_TYPE.toString())))
            .andExpect(jsonPath("$.[*].imsiRangeFrom").value(hasItem(DEFAULT_IMSI_RANGE_FROM)))
            .andExpect(jsonPath("$.[*].imsiRangeTo").value(hasItem(DEFAULT_IMSI_RANGE_TO)))
            .andExpect(jsonPath("$.[*].simRepositoryRef").value(hasItem(DEFAULT_SIM_REPOSITORY_REF)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getProductSimAttributes() throws Exception {
        // Initialize the database
        productSimAttributesRepository.save(productSimAttributes);

        // Get the productSimAttributes
        restProductSimAttributesMockMvc.perform(get("/api/product-sim-attributes/{id}", productSimAttributes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productSimAttributes.getId()))
            .andExpect(jsonPath("$.simType").value(DEFAULT_SIM_TYPE.toString()))
            .andExpect(jsonPath("$.imsiRangeFrom").value(DEFAULT_IMSI_RANGE_FROM))
            .andExpect(jsonPath("$.imsiRangeTo").value(DEFAULT_IMSI_RANGE_TO))
            .andExpect(jsonPath("$.simRepositoryRef").value(DEFAULT_SIM_REPOSITORY_REF))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingProductSimAttributes() throws Exception {
        // Get the productSimAttributes
        restProductSimAttributesMockMvc.perform(get("/api/product-sim-attributes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProductSimAttributes() throws Exception {
        // Initialize the database
        productSimAttributesService.save(productSimAttributes);

        int databaseSizeBeforeUpdate = productSimAttributesRepository.findAll().size();

        // Update the productSimAttributes
        ProductSimAttributes updatedProductSimAttributes = productSimAttributesRepository.findById(productSimAttributes.getId()).get();
        updatedProductSimAttributes
            .simType(UPDATED_SIM_TYPE)
            .imsiRangeFrom(UPDATED_IMSI_RANGE_FROM)
            .imsiRangeTo(UPDATED_IMSI_RANGE_TO)
            .simRepositoryRef(UPDATED_SIM_REPOSITORY_REF)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restProductSimAttributesMockMvc.perform(put("/api/product-sim-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductSimAttributes)))
            .andExpect(status().isOk());

        // Validate the ProductSimAttributes in the database
        List<ProductSimAttributes> productSimAttributesList = productSimAttributesRepository.findAll();
        assertThat(productSimAttributesList).hasSize(databaseSizeBeforeUpdate);
        ProductSimAttributes testProductSimAttributes = productSimAttributesList.get(productSimAttributesList.size() - 1);
        assertThat(testProductSimAttributes.getSimType()).isEqualTo(UPDATED_SIM_TYPE);
        assertThat(testProductSimAttributes.getImsiRangeFrom()).isEqualTo(UPDATED_IMSI_RANGE_FROM);
        assertThat(testProductSimAttributes.getImsiRangeTo()).isEqualTo(UPDATED_IMSI_RANGE_TO);
        assertThat(testProductSimAttributes.getSimRepositoryRef()).isEqualTo(UPDATED_SIM_REPOSITORY_REF);
        assertThat(testProductSimAttributes.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProductSimAttributes.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductSimAttributes.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testProductSimAttributes.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testProductSimAttributes.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingProductSimAttributes() throws Exception {
        int databaseSizeBeforeUpdate = productSimAttributesRepository.findAll().size();

        // Create the ProductSimAttributes

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductSimAttributesMockMvc.perform(put("/api/product-sim-attributes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productSimAttributes)))
            .andExpect(status().isBadRequest());

        // Validate the ProductSimAttributes in the database
        List<ProductSimAttributes> productSimAttributesList = productSimAttributesRepository.findAll();
        assertThat(productSimAttributesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteProductSimAttributes() throws Exception {
        // Initialize the database
        productSimAttributesService.save(productSimAttributes);

        int databaseSizeBeforeDelete = productSimAttributesRepository.findAll().size();

        // Delete the productSimAttributes
        restProductSimAttributesMockMvc.perform(delete("/api/product-sim-attributes/{id}", productSimAttributes.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductSimAttributes> productSimAttributesList = productSimAttributesRepository.findAll();
        assertThat(productSimAttributesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
