package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.Product;
import com.hthk.crm.repository.ProductRepository;
import com.hthk.crm.service.ProductService;
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

import com.hthk.crm.domain.enumeration.ProductCate;
import com.hthk.crm.domain.enumeration.ProductNature;
import com.hthk.crm.domain.enumeration.ProductFamily;
import com.hthk.crm.domain.enumeration.ProductType;
/**
 * Integration tests for the {@link ProductResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class ProductResourceIT {

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_NAME_CHI = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NAME_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_DESC_CHI = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_DESC_CHI = "BBBBBBBBBB";

    private static final ProductCate DEFAULT_PRODUCT_CATE = ProductCate.TELCO;
    private static final ProductCate UPDATED_PRODUCT_CATE = ProductCate.NON_TELCO;

    private static final ProductNature DEFAULT_PRODUCT_NATURE = ProductNature.DEVICE;
    private static final ProductNature UPDATED_PRODUCT_NATURE = ProductNature.SERVICE;

    private static final ProductFamily DEFAULT_PRODUCT_FAMILY = ProductFamily.VOICE;
    private static final ProductFamily UPDATED_PRODUCT_FAMILY = ProductFamily.DATA;

    private static final ProductType DEFAULT_PRODUCT_TYPE = ProductType.BASE;
    private static final ProductType UPDATED_PRODUCT_TYPE = ProductType.VAS;

    private static final String DEFAULT_MODEL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MODEL_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_INDEPENDENTLY_ORDERABLE = false;
    private static final Boolean UPDATED_INDEPENDENTLY_ORDERABLE = true;

    private static final Boolean DEFAULT_NETWORK_PROVISION_REQUIRED = false;
    private static final Boolean UPDATED_NETWORK_PROVISION_REQUIRED = true;

    private static final Boolean DEFAULT_CHANGE_ENTITLEMENT_ALLOWED = false;
    private static final Boolean UPDATED_CHANGE_ENTITLEMENT_ALLOWED = true;

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
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restProductMockMvc;

    private Product product;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductResource productResource = new ProductResource(productService);
        this.restProductMockMvc = MockMvcBuilders.standaloneSetup(productResource)
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
    public static Product createEntity() {
        Product product = new Product()
            .productId(DEFAULT_PRODUCT_ID)
            .productName(DEFAULT_PRODUCT_NAME)
            .productNameChi(DEFAULT_PRODUCT_NAME_CHI)
            .productDesc(DEFAULT_PRODUCT_DESC)
            .productDescChi(DEFAULT_PRODUCT_DESC_CHI)
            .productCate(DEFAULT_PRODUCT_CATE)
            .productNature(DEFAULT_PRODUCT_NATURE)
            .productFamily(DEFAULT_PRODUCT_FAMILY)
            .productType(DEFAULT_PRODUCT_TYPE)
            .modelCode(DEFAULT_MODEL_CODE)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .independentlyOrderable(DEFAULT_INDEPENDENTLY_ORDERABLE)
            .networkProvisionRequired(DEFAULT_NETWORK_PROVISION_REQUIRED)
            .changeEntitlementAllowed(DEFAULT_CHANGE_ENTITLEMENT_ALLOWED)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return product;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Product createUpdatedEntity() {
        Product product = new Product()
            .productId(UPDATED_PRODUCT_ID)
            .productName(UPDATED_PRODUCT_NAME)
            .productNameChi(UPDATED_PRODUCT_NAME_CHI)
            .productDesc(UPDATED_PRODUCT_DESC)
            .productDescChi(UPDATED_PRODUCT_DESC_CHI)
            .productCate(UPDATED_PRODUCT_CATE)
            .productNature(UPDATED_PRODUCT_NATURE)
            .productFamily(UPDATED_PRODUCT_FAMILY)
            .productType(UPDATED_PRODUCT_TYPE)
            .modelCode(UPDATED_MODEL_CODE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .independentlyOrderable(UPDATED_INDEPENDENTLY_ORDERABLE)
            .networkProvisionRequired(UPDATED_NETWORK_PROVISION_REQUIRED)
            .changeEntitlementAllowed(UPDATED_CHANGE_ENTITLEMENT_ALLOWED)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return product;
    }

    @BeforeEach
    public void initTest() {
        productRepository.deleteAll();
        product = createEntity();
    }

    @Test
    public void createProduct() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product
        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate + 1);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testProduct.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testProduct.getProductNameChi()).isEqualTo(DEFAULT_PRODUCT_NAME_CHI);
        assertThat(testProduct.getProductDesc()).isEqualTo(DEFAULT_PRODUCT_DESC);
        assertThat(testProduct.getProductDescChi()).isEqualTo(DEFAULT_PRODUCT_DESC_CHI);
        assertThat(testProduct.getProductCate()).isEqualTo(DEFAULT_PRODUCT_CATE);
        assertThat(testProduct.getProductNature()).isEqualTo(DEFAULT_PRODUCT_NATURE);
        assertThat(testProduct.getProductFamily()).isEqualTo(DEFAULT_PRODUCT_FAMILY);
        assertThat(testProduct.getProductType()).isEqualTo(DEFAULT_PRODUCT_TYPE);
        assertThat(testProduct.getModelCode()).isEqualTo(DEFAULT_MODEL_CODE);
        assertThat(testProduct.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testProduct.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testProduct.isIndependentlyOrderable()).isEqualTo(DEFAULT_INDEPENDENTLY_ORDERABLE);
        assertThat(testProduct.isNetworkProvisionRequired()).isEqualTo(DEFAULT_NETWORK_PROVISION_REQUIRED);
        assertThat(testProduct.isChangeEntitlementAllowed()).isEqualTo(DEFAULT_CHANGE_ENTITLEMENT_ALLOWED);
        assertThat(testProduct.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProduct.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProduct.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testProduct.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testProduct.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product with an existing ID
        product.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkProductIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setProductId(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkProductNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setProductName(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setCreatedDate(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setCreatedBy(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setLastUpdatedDate(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setLastUpdatedBy(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setTenantId(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProducts() throws Exception {
        // Initialize the database
        productRepository.save(product);

        // Get all the productList
        restProductMockMvc.perform(get("/api/products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME)))
            .andExpect(jsonPath("$.[*].productNameChi").value(hasItem(DEFAULT_PRODUCT_NAME_CHI)))
            .andExpect(jsonPath("$.[*].productDesc").value(hasItem(DEFAULT_PRODUCT_DESC)))
            .andExpect(jsonPath("$.[*].productDescChi").value(hasItem(DEFAULT_PRODUCT_DESC_CHI)))
            .andExpect(jsonPath("$.[*].productCate").value(hasItem(DEFAULT_PRODUCT_CATE.toString())))
            .andExpect(jsonPath("$.[*].productNature").value(hasItem(DEFAULT_PRODUCT_NATURE.toString())))
            .andExpect(jsonPath("$.[*].productFamily").value(hasItem(DEFAULT_PRODUCT_FAMILY.toString())))
            .andExpect(jsonPath("$.[*].productType").value(hasItem(DEFAULT_PRODUCT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].modelCode").value(hasItem(DEFAULT_MODEL_CODE)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].independentlyOrderable").value(hasItem(DEFAULT_INDEPENDENTLY_ORDERABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].networkProvisionRequired").value(hasItem(DEFAULT_NETWORK_PROVISION_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].changeEntitlementAllowed").value(hasItem(DEFAULT_CHANGE_ENTITLEMENT_ALLOWED.booleanValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getProduct() throws Exception {
        // Initialize the database
        productRepository.save(product);

        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(product.getId()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.productName").value(DEFAULT_PRODUCT_NAME))
            .andExpect(jsonPath("$.productNameChi").value(DEFAULT_PRODUCT_NAME_CHI))
            .andExpect(jsonPath("$.productDesc").value(DEFAULT_PRODUCT_DESC))
            .andExpect(jsonPath("$.productDescChi").value(DEFAULT_PRODUCT_DESC_CHI))
            .andExpect(jsonPath("$.productCate").value(DEFAULT_PRODUCT_CATE.toString()))
            .andExpect(jsonPath("$.productNature").value(DEFAULT_PRODUCT_NATURE.toString()))
            .andExpect(jsonPath("$.productFamily").value(DEFAULT_PRODUCT_FAMILY.toString()))
            .andExpect(jsonPath("$.productType").value(DEFAULT_PRODUCT_TYPE.toString()))
            .andExpect(jsonPath("$.modelCode").value(DEFAULT_MODEL_CODE))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.independentlyOrderable").value(DEFAULT_INDEPENDENTLY_ORDERABLE.booleanValue()))
            .andExpect(jsonPath("$.networkProvisionRequired").value(DEFAULT_NETWORK_PROVISION_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.changeEntitlementAllowed").value(DEFAULT_CHANGE_ENTITLEMENT_ALLOWED.booleanValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingProduct() throws Exception {
        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProduct() throws Exception {
        // Initialize the database
        productService.save(product);

        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product
        Product updatedProduct = productRepository.findById(product.getId()).get();
        updatedProduct
            .productId(UPDATED_PRODUCT_ID)
            .productName(UPDATED_PRODUCT_NAME)
            .productNameChi(UPDATED_PRODUCT_NAME_CHI)
            .productDesc(UPDATED_PRODUCT_DESC)
            .productDescChi(UPDATED_PRODUCT_DESC_CHI)
            .productCate(UPDATED_PRODUCT_CATE)
            .productNature(UPDATED_PRODUCT_NATURE)
            .productFamily(UPDATED_PRODUCT_FAMILY)
            .productType(UPDATED_PRODUCT_TYPE)
            .modelCode(UPDATED_MODEL_CODE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .independentlyOrderable(UPDATED_INDEPENDENTLY_ORDERABLE)
            .networkProvisionRequired(UPDATED_NETWORK_PROVISION_REQUIRED)
            .changeEntitlementAllowed(UPDATED_CHANGE_ENTITLEMENT_ALLOWED)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restProductMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProduct)))
            .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testProduct.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testProduct.getProductNameChi()).isEqualTo(UPDATED_PRODUCT_NAME_CHI);
        assertThat(testProduct.getProductDesc()).isEqualTo(UPDATED_PRODUCT_DESC);
        assertThat(testProduct.getProductDescChi()).isEqualTo(UPDATED_PRODUCT_DESC_CHI);
        assertThat(testProduct.getProductCate()).isEqualTo(UPDATED_PRODUCT_CATE);
        assertThat(testProduct.getProductNature()).isEqualTo(UPDATED_PRODUCT_NATURE);
        assertThat(testProduct.getProductFamily()).isEqualTo(UPDATED_PRODUCT_FAMILY);
        assertThat(testProduct.getProductType()).isEqualTo(UPDATED_PRODUCT_TYPE);
        assertThat(testProduct.getModelCode()).isEqualTo(UPDATED_MODEL_CODE);
        assertThat(testProduct.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testProduct.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testProduct.isIndependentlyOrderable()).isEqualTo(UPDATED_INDEPENDENTLY_ORDERABLE);
        assertThat(testProduct.isNetworkProvisionRequired()).isEqualTo(UPDATED_NETWORK_PROVISION_REQUIRED);
        assertThat(testProduct.isChangeEntitlementAllowed()).isEqualTo(UPDATED_CHANGE_ENTITLEMENT_ALLOWED);
        assertThat(testProduct.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProduct.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProduct.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testProduct.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testProduct.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Create the Product

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteProduct() throws Exception {
        // Initialize the database
        productService.save(product);

        int databaseSizeBeforeDelete = productRepository.findAll().size();

        // Delete the product
        restProductMockMvc.perform(delete("/api/products/{id}", product.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
