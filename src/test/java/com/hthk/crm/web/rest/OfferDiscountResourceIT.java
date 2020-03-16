package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.OfferDiscount;
import com.hthk.crm.repository.OfferDiscountRepository;
import com.hthk.crm.service.OfferDiscountService;
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
 * Integration tests for the {@link OfferDiscountResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class OfferDiscountResourceIT {

    private static final String DEFAULT_DISCOUNT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DISCOUNT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DISCOUNT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DISCOUNT_TYPE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_OFFER_DISCOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_OFFER_DISCOUNT = new BigDecimal(2);

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
    private OfferDiscountRepository offerDiscountRepository;

    @Autowired
    private OfferDiscountService offerDiscountService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restOfferDiscountMockMvc;

    private OfferDiscount offerDiscount;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OfferDiscountResource offerDiscountResource = new OfferDiscountResource(offerDiscountService);
        this.restOfferDiscountMockMvc = MockMvcBuilders.standaloneSetup(offerDiscountResource)
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
    public static OfferDiscount createEntity() {
        OfferDiscount offerDiscount = new OfferDiscount()
            .discountCode(DEFAULT_DISCOUNT_CODE)
            .offerId(DEFAULT_OFFER_ID)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .discountType(DEFAULT_DISCOUNT_TYPE)
            .offerDiscount(DEFAULT_OFFER_DISCOUNT)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return offerDiscount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferDiscount createUpdatedEntity() {
        OfferDiscount offerDiscount = new OfferDiscount()
            .discountCode(UPDATED_DISCOUNT_CODE)
            .offerId(UPDATED_OFFER_ID)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .discountType(UPDATED_DISCOUNT_TYPE)
            .offerDiscount(UPDATED_OFFER_DISCOUNT)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return offerDiscount;
    }

    @BeforeEach
    public void initTest() {
        offerDiscountRepository.deleteAll();
        offerDiscount = createEntity();
    }

    @Test
    public void createOfferDiscount() throws Exception {
        int databaseSizeBeforeCreate = offerDiscountRepository.findAll().size();

        // Create the OfferDiscount
        restOfferDiscountMockMvc.perform(post("/api/offer-discounts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerDiscount)))
            .andExpect(status().isCreated());

        // Validate the OfferDiscount in the database
        List<OfferDiscount> offerDiscountList = offerDiscountRepository.findAll();
        assertThat(offerDiscountList).hasSize(databaseSizeBeforeCreate + 1);
        OfferDiscount testOfferDiscount = offerDiscountList.get(offerDiscountList.size() - 1);
        assertThat(testOfferDiscount.getDiscountCode()).isEqualTo(DEFAULT_DISCOUNT_CODE);
        assertThat(testOfferDiscount.getOfferId()).isEqualTo(DEFAULT_OFFER_ID);
        assertThat(testOfferDiscount.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testOfferDiscount.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testOfferDiscount.getDiscountType()).isEqualTo(DEFAULT_DISCOUNT_TYPE);
        assertThat(testOfferDiscount.getOfferDiscount()).isEqualTo(DEFAULT_OFFER_DISCOUNT);
        assertThat(testOfferDiscount.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testOfferDiscount.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOfferDiscount.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOfferDiscount.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testOfferDiscount.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testOfferDiscount.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createOfferDiscountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offerDiscountRepository.findAll().size();

        // Create the OfferDiscount with an existing ID
        offerDiscount.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfferDiscountMockMvc.perform(post("/api/offer-discounts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerDiscount)))
            .andExpect(status().isBadRequest());

        // Validate the OfferDiscount in the database
        List<OfferDiscount> offerDiscountList = offerDiscountRepository.findAll();
        assertThat(offerDiscountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkDiscountCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerDiscountRepository.findAll().size();
        // set the field null
        offerDiscount.setDiscountCode(null);

        // Create the OfferDiscount, which fails.

        restOfferDiscountMockMvc.perform(post("/api/offer-discounts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerDiscount)))
            .andExpect(status().isBadRequest());

        List<OfferDiscount> offerDiscountList = offerDiscountRepository.findAll();
        assertThat(offerDiscountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerDiscountRepository.findAll().size();
        // set the field null
        offerDiscount.setCreatedDate(null);

        // Create the OfferDiscount, which fails.

        restOfferDiscountMockMvc.perform(post("/api/offer-discounts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerDiscount)))
            .andExpect(status().isBadRequest());

        List<OfferDiscount> offerDiscountList = offerDiscountRepository.findAll();
        assertThat(offerDiscountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerDiscountRepository.findAll().size();
        // set the field null
        offerDiscount.setCreatedBy(null);

        // Create the OfferDiscount, which fails.

        restOfferDiscountMockMvc.perform(post("/api/offer-discounts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerDiscount)))
            .andExpect(status().isBadRequest());

        List<OfferDiscount> offerDiscountList = offerDiscountRepository.findAll();
        assertThat(offerDiscountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerDiscountRepository.findAll().size();
        // set the field null
        offerDiscount.setLastUpdatedDate(null);

        // Create the OfferDiscount, which fails.

        restOfferDiscountMockMvc.perform(post("/api/offer-discounts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerDiscount)))
            .andExpect(status().isBadRequest());

        List<OfferDiscount> offerDiscountList = offerDiscountRepository.findAll();
        assertThat(offerDiscountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerDiscountRepository.findAll().size();
        // set the field null
        offerDiscount.setLastUpdatedBy(null);

        // Create the OfferDiscount, which fails.

        restOfferDiscountMockMvc.perform(post("/api/offer-discounts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerDiscount)))
            .andExpect(status().isBadRequest());

        List<OfferDiscount> offerDiscountList = offerDiscountRepository.findAll();
        assertThat(offerDiscountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerDiscountRepository.findAll().size();
        // set the field null
        offerDiscount.setTenantId(null);

        // Create the OfferDiscount, which fails.

        restOfferDiscountMockMvc.perform(post("/api/offer-discounts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerDiscount)))
            .andExpect(status().isBadRequest());

        List<OfferDiscount> offerDiscountList = offerDiscountRepository.findAll();
        assertThat(offerDiscountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllOfferDiscounts() throws Exception {
        // Initialize the database
        offerDiscountRepository.save(offerDiscount);

        // Get all the offerDiscountList
        restOfferDiscountMockMvc.perform(get("/api/offer-discounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offerDiscount.getId())))
            .andExpect(jsonPath("$.[*].discountCode").value(hasItem(DEFAULT_DISCOUNT_CODE)))
            .andExpect(jsonPath("$.[*].offerId").value(hasItem(DEFAULT_OFFER_ID)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].discountType").value(hasItem(DEFAULT_DISCOUNT_TYPE)))
            .andExpect(jsonPath("$.[*].offerDiscount").value(hasItem(DEFAULT_OFFER_DISCOUNT.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getOfferDiscount() throws Exception {
        // Initialize the database
        offerDiscountRepository.save(offerDiscount);

        // Get the offerDiscount
        restOfferDiscountMockMvc.perform(get("/api/offer-discounts/{id}", offerDiscount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offerDiscount.getId()))
            .andExpect(jsonPath("$.discountCode").value(DEFAULT_DISCOUNT_CODE))
            .andExpect(jsonPath("$.offerId").value(DEFAULT_OFFER_ID))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.discountType").value(DEFAULT_DISCOUNT_TYPE))
            .andExpect(jsonPath("$.offerDiscount").value(DEFAULT_OFFER_DISCOUNT.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingOfferDiscount() throws Exception {
        // Get the offerDiscount
        restOfferDiscountMockMvc.perform(get("/api/offer-discounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOfferDiscount() throws Exception {
        // Initialize the database
        offerDiscountService.save(offerDiscount);

        int databaseSizeBeforeUpdate = offerDiscountRepository.findAll().size();

        // Update the offerDiscount
        OfferDiscount updatedOfferDiscount = offerDiscountRepository.findById(offerDiscount.getId()).get();
        updatedOfferDiscount
            .discountCode(UPDATED_DISCOUNT_CODE)
            .offerId(UPDATED_OFFER_ID)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .discountType(UPDATED_DISCOUNT_TYPE)
            .offerDiscount(UPDATED_OFFER_DISCOUNT)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restOfferDiscountMockMvc.perform(put("/api/offer-discounts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOfferDiscount)))
            .andExpect(status().isOk());

        // Validate the OfferDiscount in the database
        List<OfferDiscount> offerDiscountList = offerDiscountRepository.findAll();
        assertThat(offerDiscountList).hasSize(databaseSizeBeforeUpdate);
        OfferDiscount testOfferDiscount = offerDiscountList.get(offerDiscountList.size() - 1);
        assertThat(testOfferDiscount.getDiscountCode()).isEqualTo(UPDATED_DISCOUNT_CODE);
        assertThat(testOfferDiscount.getOfferId()).isEqualTo(UPDATED_OFFER_ID);
        assertThat(testOfferDiscount.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testOfferDiscount.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testOfferDiscount.getDiscountType()).isEqualTo(UPDATED_DISCOUNT_TYPE);
        assertThat(testOfferDiscount.getOfferDiscount()).isEqualTo(UPDATED_OFFER_DISCOUNT);
        assertThat(testOfferDiscount.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOfferDiscount.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOfferDiscount.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOfferDiscount.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testOfferDiscount.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testOfferDiscount.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingOfferDiscount() throws Exception {
        int databaseSizeBeforeUpdate = offerDiscountRepository.findAll().size();

        // Create the OfferDiscount

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferDiscountMockMvc.perform(put("/api/offer-discounts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerDiscount)))
            .andExpect(status().isBadRequest());

        // Validate the OfferDiscount in the database
        List<OfferDiscount> offerDiscountList = offerDiscountRepository.findAll();
        assertThat(offerDiscountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOfferDiscount() throws Exception {
        // Initialize the database
        offerDiscountService.save(offerDiscount);

        int databaseSizeBeforeDelete = offerDiscountRepository.findAll().size();

        // Delete the offerDiscount
        restOfferDiscountMockMvc.perform(delete("/api/offer-discounts/{id}", offerDiscount.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OfferDiscount> offerDiscountList = offerDiscountRepository.findAll();
        assertThat(offerDiscountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
