package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.OfferPromotion;
import com.hthk.crm.repository.OfferPromotionRepository;
import com.hthk.crm.service.OfferPromotionService;
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

import com.hthk.crm.domain.enumeration.RecordStatus;
/**
 * Integration tests for the {@link OfferPromotionResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class OfferPromotionResourceIT {

    private static final String DEFAULT_PROMO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROMO_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PROMO_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PROMO_TYPE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_OFFER_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_OFFER_PRICE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_OFFER_DISCOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_OFFER_DISCOUNT = new BigDecimal(2);

    private static final String DEFAULT_FREE_DATA_OFFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_FREE_DATA_OFFER_ID = "BBBBBBBBBB";

    private static final RecordStatus DEFAULT_STATUS = RecordStatus.INACTIVE;
    private static final RecordStatus UPDATED_STATUS = RecordStatus.ACTIVE;

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
    private OfferPromotionRepository offerPromotionRepository;

    @Autowired
    private OfferPromotionService offerPromotionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restOfferPromotionMockMvc;

    private OfferPromotion offerPromotion;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OfferPromotionResource offerPromotionResource = new OfferPromotionResource(offerPromotionService);
        this.restOfferPromotionMockMvc = MockMvcBuilders.standaloneSetup(offerPromotionResource)
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
    public static OfferPromotion createEntity() {
        OfferPromotion offerPromotion = new OfferPromotion()
            .promoCode(DEFAULT_PROMO_CODE)
            .offerId(DEFAULT_OFFER_ID)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .promoType(DEFAULT_PROMO_TYPE)
            .offerPrice(DEFAULT_OFFER_PRICE)
            .offerDiscount(DEFAULT_OFFER_DISCOUNT)
            .freeDataOfferId(DEFAULT_FREE_DATA_OFFER_ID)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return offerPromotion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferPromotion createUpdatedEntity() {
        OfferPromotion offerPromotion = new OfferPromotion()
            .promoCode(UPDATED_PROMO_CODE)
            .offerId(UPDATED_OFFER_ID)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .promoType(UPDATED_PROMO_TYPE)
            .offerPrice(UPDATED_OFFER_PRICE)
            .offerDiscount(UPDATED_OFFER_DISCOUNT)
            .freeDataOfferId(UPDATED_FREE_DATA_OFFER_ID)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return offerPromotion;
    }

    @BeforeEach
    public void initTest() {
        offerPromotionRepository.deleteAll();
        offerPromotion = createEntity();
    }

    @Test
    public void createOfferPromotion() throws Exception {
        int databaseSizeBeforeCreate = offerPromotionRepository.findAll().size();

        // Create the OfferPromotion
        restOfferPromotionMockMvc.perform(post("/api/offer-promotions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerPromotion)))
            .andExpect(status().isCreated());

        // Validate the OfferPromotion in the database
        List<OfferPromotion> offerPromotionList = offerPromotionRepository.findAll();
        assertThat(offerPromotionList).hasSize(databaseSizeBeforeCreate + 1);
        OfferPromotion testOfferPromotion = offerPromotionList.get(offerPromotionList.size() - 1);
        assertThat(testOfferPromotion.getPromoCode()).isEqualTo(DEFAULT_PROMO_CODE);
        assertThat(testOfferPromotion.getOfferId()).isEqualTo(DEFAULT_OFFER_ID);
        assertThat(testOfferPromotion.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testOfferPromotion.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testOfferPromotion.getPromoType()).isEqualTo(DEFAULT_PROMO_TYPE);
        assertThat(testOfferPromotion.getOfferPrice()).isEqualTo(DEFAULT_OFFER_PRICE);
        assertThat(testOfferPromotion.getOfferDiscount()).isEqualTo(DEFAULT_OFFER_DISCOUNT);
        assertThat(testOfferPromotion.getFreeDataOfferId()).isEqualTo(DEFAULT_FREE_DATA_OFFER_ID);
        assertThat(testOfferPromotion.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testOfferPromotion.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOfferPromotion.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOfferPromotion.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testOfferPromotion.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testOfferPromotion.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createOfferPromotionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offerPromotionRepository.findAll().size();

        // Create the OfferPromotion with an existing ID
        offerPromotion.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfferPromotionMockMvc.perform(post("/api/offer-promotions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerPromotion)))
            .andExpect(status().isBadRequest());

        // Validate the OfferPromotion in the database
        List<OfferPromotion> offerPromotionList = offerPromotionRepository.findAll();
        assertThat(offerPromotionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkPromoCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerPromotionRepository.findAll().size();
        // set the field null
        offerPromotion.setPromoCode(null);

        // Create the OfferPromotion, which fails.

        restOfferPromotionMockMvc.perform(post("/api/offer-promotions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerPromotion)))
            .andExpect(status().isBadRequest());

        List<OfferPromotion> offerPromotionList = offerPromotionRepository.findAll();
        assertThat(offerPromotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOfferIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerPromotionRepository.findAll().size();
        // set the field null
        offerPromotion.setOfferId(null);

        // Create the OfferPromotion, which fails.

        restOfferPromotionMockMvc.perform(post("/api/offer-promotions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerPromotion)))
            .andExpect(status().isBadRequest());

        List<OfferPromotion> offerPromotionList = offerPromotionRepository.findAll();
        assertThat(offerPromotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerPromotionRepository.findAll().size();
        // set the field null
        offerPromotion.setCreatedDate(null);

        // Create the OfferPromotion, which fails.

        restOfferPromotionMockMvc.perform(post("/api/offer-promotions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerPromotion)))
            .andExpect(status().isBadRequest());

        List<OfferPromotion> offerPromotionList = offerPromotionRepository.findAll();
        assertThat(offerPromotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerPromotionRepository.findAll().size();
        // set the field null
        offerPromotion.setCreatedBy(null);

        // Create the OfferPromotion, which fails.

        restOfferPromotionMockMvc.perform(post("/api/offer-promotions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerPromotion)))
            .andExpect(status().isBadRequest());

        List<OfferPromotion> offerPromotionList = offerPromotionRepository.findAll();
        assertThat(offerPromotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerPromotionRepository.findAll().size();
        // set the field null
        offerPromotion.setLastUpdatedDate(null);

        // Create the OfferPromotion, which fails.

        restOfferPromotionMockMvc.perform(post("/api/offer-promotions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerPromotion)))
            .andExpect(status().isBadRequest());

        List<OfferPromotion> offerPromotionList = offerPromotionRepository.findAll();
        assertThat(offerPromotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerPromotionRepository.findAll().size();
        // set the field null
        offerPromotion.setLastUpdatedBy(null);

        // Create the OfferPromotion, which fails.

        restOfferPromotionMockMvc.perform(post("/api/offer-promotions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerPromotion)))
            .andExpect(status().isBadRequest());

        List<OfferPromotion> offerPromotionList = offerPromotionRepository.findAll();
        assertThat(offerPromotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerPromotionRepository.findAll().size();
        // set the field null
        offerPromotion.setTenantId(null);

        // Create the OfferPromotion, which fails.

        restOfferPromotionMockMvc.perform(post("/api/offer-promotions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerPromotion)))
            .andExpect(status().isBadRequest());

        List<OfferPromotion> offerPromotionList = offerPromotionRepository.findAll();
        assertThat(offerPromotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllOfferPromotions() throws Exception {
        // Initialize the database
        offerPromotionRepository.save(offerPromotion);

        // Get all the offerPromotionList
        restOfferPromotionMockMvc.perform(get("/api/offer-promotions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offerPromotion.getId())))
            .andExpect(jsonPath("$.[*].promoCode").value(hasItem(DEFAULT_PROMO_CODE)))
            .andExpect(jsonPath("$.[*].offerId").value(hasItem(DEFAULT_OFFER_ID)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].promoType").value(hasItem(DEFAULT_PROMO_TYPE)))
            .andExpect(jsonPath("$.[*].offerPrice").value(hasItem(DEFAULT_OFFER_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].offerDiscount").value(hasItem(DEFAULT_OFFER_DISCOUNT.intValue())))
            .andExpect(jsonPath("$.[*].freeDataOfferId").value(hasItem(DEFAULT_FREE_DATA_OFFER_ID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getOfferPromotion() throws Exception {
        // Initialize the database
        offerPromotionRepository.save(offerPromotion);

        // Get the offerPromotion
        restOfferPromotionMockMvc.perform(get("/api/offer-promotions/{id}", offerPromotion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offerPromotion.getId()))
            .andExpect(jsonPath("$.promoCode").value(DEFAULT_PROMO_CODE))
            .andExpect(jsonPath("$.offerId").value(DEFAULT_OFFER_ID))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.promoType").value(DEFAULT_PROMO_TYPE))
            .andExpect(jsonPath("$.offerPrice").value(DEFAULT_OFFER_PRICE.intValue()))
            .andExpect(jsonPath("$.offerDiscount").value(DEFAULT_OFFER_DISCOUNT.intValue()))
            .andExpect(jsonPath("$.freeDataOfferId").value(DEFAULT_FREE_DATA_OFFER_ID))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingOfferPromotion() throws Exception {
        // Get the offerPromotion
        restOfferPromotionMockMvc.perform(get("/api/offer-promotions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOfferPromotion() throws Exception {
        // Initialize the database
        offerPromotionService.save(offerPromotion);

        int databaseSizeBeforeUpdate = offerPromotionRepository.findAll().size();

        // Update the offerPromotion
        OfferPromotion updatedOfferPromotion = offerPromotionRepository.findById(offerPromotion.getId()).get();
        updatedOfferPromotion
            .promoCode(UPDATED_PROMO_CODE)
            .offerId(UPDATED_OFFER_ID)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .promoType(UPDATED_PROMO_TYPE)
            .offerPrice(UPDATED_OFFER_PRICE)
            .offerDiscount(UPDATED_OFFER_DISCOUNT)
            .freeDataOfferId(UPDATED_FREE_DATA_OFFER_ID)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restOfferPromotionMockMvc.perform(put("/api/offer-promotions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOfferPromotion)))
            .andExpect(status().isOk());

        // Validate the OfferPromotion in the database
        List<OfferPromotion> offerPromotionList = offerPromotionRepository.findAll();
        assertThat(offerPromotionList).hasSize(databaseSizeBeforeUpdate);
        OfferPromotion testOfferPromotion = offerPromotionList.get(offerPromotionList.size() - 1);
        assertThat(testOfferPromotion.getPromoCode()).isEqualTo(UPDATED_PROMO_CODE);
        assertThat(testOfferPromotion.getOfferId()).isEqualTo(UPDATED_OFFER_ID);
        assertThat(testOfferPromotion.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testOfferPromotion.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testOfferPromotion.getPromoType()).isEqualTo(UPDATED_PROMO_TYPE);
        assertThat(testOfferPromotion.getOfferPrice()).isEqualTo(UPDATED_OFFER_PRICE);
        assertThat(testOfferPromotion.getOfferDiscount()).isEqualTo(UPDATED_OFFER_DISCOUNT);
        assertThat(testOfferPromotion.getFreeDataOfferId()).isEqualTo(UPDATED_FREE_DATA_OFFER_ID);
        assertThat(testOfferPromotion.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOfferPromotion.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOfferPromotion.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOfferPromotion.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testOfferPromotion.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testOfferPromotion.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingOfferPromotion() throws Exception {
        int databaseSizeBeforeUpdate = offerPromotionRepository.findAll().size();

        // Create the OfferPromotion

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferPromotionMockMvc.perform(put("/api/offer-promotions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerPromotion)))
            .andExpect(status().isBadRequest());

        // Validate the OfferPromotion in the database
        List<OfferPromotion> offerPromotionList = offerPromotionRepository.findAll();
        assertThat(offerPromotionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOfferPromotion() throws Exception {
        // Initialize the database
        offerPromotionService.save(offerPromotion);

        int databaseSizeBeforeDelete = offerPromotionRepository.findAll().size();

        // Delete the offerPromotion
        restOfferPromotionMockMvc.perform(delete("/api/offer-promotions/{id}", offerPromotion.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OfferPromotion> offerPromotionList = offerPromotionRepository.findAll();
        assertThat(offerPromotionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
