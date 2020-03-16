package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.OfferProduct;
import com.hthk.crm.repository.OfferProductRepository;
import com.hthk.crm.service.OfferProductService;
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
 * Integration tests for the {@link OfferProductResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class OfferProductResourceIT {

    private static final Long DEFAULT_REC_SEQ_ID = 1L;
    private static final Long UPDATED_REC_SEQ_ID = 2L;

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_ID = "BBBBBBBBBB";

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
    private OfferProductRepository offerProductRepository;

    @Autowired
    private OfferProductService offerProductService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restOfferProductMockMvc;

    private OfferProduct offerProduct;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OfferProductResource offerProductResource = new OfferProductResource(offerProductService);
        this.restOfferProductMockMvc = MockMvcBuilders.standaloneSetup(offerProductResource)
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
    public static OfferProduct createEntity() {
        OfferProduct offerProduct = new OfferProduct()
            .recSeqId(DEFAULT_REC_SEQ_ID)
            .productId(DEFAULT_PRODUCT_ID)
            .offerId(DEFAULT_OFFER_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return offerProduct;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferProduct createUpdatedEntity() {
        OfferProduct offerProduct = new OfferProduct()
            .recSeqId(UPDATED_REC_SEQ_ID)
            .productId(UPDATED_PRODUCT_ID)
            .offerId(UPDATED_OFFER_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return offerProduct;
    }

    @BeforeEach
    public void initTest() {
        offerProductRepository.deleteAll();
        offerProduct = createEntity();
    }

    @Test
    public void createOfferProduct() throws Exception {
        int databaseSizeBeforeCreate = offerProductRepository.findAll().size();

        // Create the OfferProduct
        restOfferProductMockMvc.perform(post("/api/offer-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerProduct)))
            .andExpect(status().isCreated());

        // Validate the OfferProduct in the database
        List<OfferProduct> offerProductList = offerProductRepository.findAll();
        assertThat(offerProductList).hasSize(databaseSizeBeforeCreate + 1);
        OfferProduct testOfferProduct = offerProductList.get(offerProductList.size() - 1);
        assertThat(testOfferProduct.getRecSeqId()).isEqualTo(DEFAULT_REC_SEQ_ID);
        assertThat(testOfferProduct.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testOfferProduct.getOfferId()).isEqualTo(DEFAULT_OFFER_ID);
        assertThat(testOfferProduct.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOfferProduct.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOfferProduct.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testOfferProduct.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testOfferProduct.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createOfferProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offerProductRepository.findAll().size();

        // Create the OfferProduct with an existing ID
        offerProduct.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfferProductMockMvc.perform(post("/api/offer-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerProduct)))
            .andExpect(status().isBadRequest());

        // Validate the OfferProduct in the database
        List<OfferProduct> offerProductList = offerProductRepository.findAll();
        assertThat(offerProductList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerProductRepository.findAll().size();
        // set the field null
        offerProduct.setCreatedDate(null);

        // Create the OfferProduct, which fails.

        restOfferProductMockMvc.perform(post("/api/offer-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerProduct)))
            .andExpect(status().isBadRequest());

        List<OfferProduct> offerProductList = offerProductRepository.findAll();
        assertThat(offerProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerProductRepository.findAll().size();
        // set the field null
        offerProduct.setCreatedBy(null);

        // Create the OfferProduct, which fails.

        restOfferProductMockMvc.perform(post("/api/offer-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerProduct)))
            .andExpect(status().isBadRequest());

        List<OfferProduct> offerProductList = offerProductRepository.findAll();
        assertThat(offerProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerProductRepository.findAll().size();
        // set the field null
        offerProduct.setLastUpdatedDate(null);

        // Create the OfferProduct, which fails.

        restOfferProductMockMvc.perform(post("/api/offer-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerProduct)))
            .andExpect(status().isBadRequest());

        List<OfferProduct> offerProductList = offerProductRepository.findAll();
        assertThat(offerProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerProductRepository.findAll().size();
        // set the field null
        offerProduct.setLastUpdatedBy(null);

        // Create the OfferProduct, which fails.

        restOfferProductMockMvc.perform(post("/api/offer-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerProduct)))
            .andExpect(status().isBadRequest());

        List<OfferProduct> offerProductList = offerProductRepository.findAll();
        assertThat(offerProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerProductRepository.findAll().size();
        // set the field null
        offerProduct.setTenantId(null);

        // Create the OfferProduct, which fails.

        restOfferProductMockMvc.perform(post("/api/offer-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerProduct)))
            .andExpect(status().isBadRequest());

        List<OfferProduct> offerProductList = offerProductRepository.findAll();
        assertThat(offerProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllOfferProducts() throws Exception {
        // Initialize the database
        offerProductRepository.save(offerProduct);

        // Get all the offerProductList
        restOfferProductMockMvc.perform(get("/api/offer-products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offerProduct.getId())))
            .andExpect(jsonPath("$.[*].recSeqId").value(hasItem(DEFAULT_REC_SEQ_ID.intValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].offerId").value(hasItem(DEFAULT_OFFER_ID)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getOfferProduct() throws Exception {
        // Initialize the database
        offerProductRepository.save(offerProduct);

        // Get the offerProduct
        restOfferProductMockMvc.perform(get("/api/offer-products/{id}", offerProduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offerProduct.getId()))
            .andExpect(jsonPath("$.recSeqId").value(DEFAULT_REC_SEQ_ID.intValue()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.offerId").value(DEFAULT_OFFER_ID))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingOfferProduct() throws Exception {
        // Get the offerProduct
        restOfferProductMockMvc.perform(get("/api/offer-products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOfferProduct() throws Exception {
        // Initialize the database
        offerProductService.save(offerProduct);

        int databaseSizeBeforeUpdate = offerProductRepository.findAll().size();

        // Update the offerProduct
        OfferProduct updatedOfferProduct = offerProductRepository.findById(offerProduct.getId()).get();
        updatedOfferProduct
            .recSeqId(UPDATED_REC_SEQ_ID)
            .productId(UPDATED_PRODUCT_ID)
            .offerId(UPDATED_OFFER_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restOfferProductMockMvc.perform(put("/api/offer-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOfferProduct)))
            .andExpect(status().isOk());

        // Validate the OfferProduct in the database
        List<OfferProduct> offerProductList = offerProductRepository.findAll();
        assertThat(offerProductList).hasSize(databaseSizeBeforeUpdate);
        OfferProduct testOfferProduct = offerProductList.get(offerProductList.size() - 1);
        assertThat(testOfferProduct.getRecSeqId()).isEqualTo(UPDATED_REC_SEQ_ID);
        assertThat(testOfferProduct.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testOfferProduct.getOfferId()).isEqualTo(UPDATED_OFFER_ID);
        assertThat(testOfferProduct.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOfferProduct.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOfferProduct.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testOfferProduct.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testOfferProduct.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingOfferProduct() throws Exception {
        int databaseSizeBeforeUpdate = offerProductRepository.findAll().size();

        // Create the OfferProduct

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferProductMockMvc.perform(put("/api/offer-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerProduct)))
            .andExpect(status().isBadRequest());

        // Validate the OfferProduct in the database
        List<OfferProduct> offerProductList = offerProductRepository.findAll();
        assertThat(offerProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOfferProduct() throws Exception {
        // Initialize the database
        offerProductService.save(offerProduct);

        int databaseSizeBeforeDelete = offerProductRepository.findAll().size();

        // Delete the offerProduct
        restOfferProductMockMvc.perform(delete("/api/offer-products/{id}", offerProduct.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OfferProduct> offerProductList = offerProductRepository.findAll();
        assertThat(offerProductList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
