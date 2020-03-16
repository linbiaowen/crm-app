package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.OfferAdvancePayment;
import com.hthk.crm.repository.OfferAdvancePaymentRepository;
import com.hthk.crm.service.OfferAdvancePaymentService;
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
 * Integration tests for the {@link OfferAdvancePaymentResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class OfferAdvancePaymentResourceIT {

    private static final Long DEFAULT_ADVANCE_PAYMENT_ID = 1L;
    private static final Long UPDATED_ADVANCE_PAYMENT_ID = 2L;

    private static final String DEFAULT_OFFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_ADVANCE_PAYMENT_MONTHS = 1;
    private static final Integer UPDATED_ADVANCE_PAYMENT_MONTHS = 2;

    private static final BigDecimal DEFAULT_OFFER_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_OFFER_PRICE = new BigDecimal(2);

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
    private OfferAdvancePaymentRepository offerAdvancePaymentRepository;

    @Autowired
    private OfferAdvancePaymentService offerAdvancePaymentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restOfferAdvancePaymentMockMvc;

    private OfferAdvancePayment offerAdvancePayment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OfferAdvancePaymentResource offerAdvancePaymentResource = new OfferAdvancePaymentResource(offerAdvancePaymentService);
        this.restOfferAdvancePaymentMockMvc = MockMvcBuilders.standaloneSetup(offerAdvancePaymentResource)
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
    public static OfferAdvancePayment createEntity() {
        OfferAdvancePayment offerAdvancePayment = new OfferAdvancePayment()
            .advancePaymentId(DEFAULT_ADVANCE_PAYMENT_ID)
            .offerId(DEFAULT_OFFER_ID)
            .advancePaymentMonths(DEFAULT_ADVANCE_PAYMENT_MONTHS)
            .offerPrice(DEFAULT_OFFER_PRICE)
            .offerDiscount(DEFAULT_OFFER_DISCOUNT)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return offerAdvancePayment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferAdvancePayment createUpdatedEntity() {
        OfferAdvancePayment offerAdvancePayment = new OfferAdvancePayment()
            .advancePaymentId(UPDATED_ADVANCE_PAYMENT_ID)
            .offerId(UPDATED_OFFER_ID)
            .advancePaymentMonths(UPDATED_ADVANCE_PAYMENT_MONTHS)
            .offerPrice(UPDATED_OFFER_PRICE)
            .offerDiscount(UPDATED_OFFER_DISCOUNT)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return offerAdvancePayment;
    }

    @BeforeEach
    public void initTest() {
        offerAdvancePaymentRepository.deleteAll();
        offerAdvancePayment = createEntity();
    }

    @Test
    public void createOfferAdvancePayment() throws Exception {
        int databaseSizeBeforeCreate = offerAdvancePaymentRepository.findAll().size();

        // Create the OfferAdvancePayment
        restOfferAdvancePaymentMockMvc.perform(post("/api/offer-advance-payments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerAdvancePayment)))
            .andExpect(status().isCreated());

        // Validate the OfferAdvancePayment in the database
        List<OfferAdvancePayment> offerAdvancePaymentList = offerAdvancePaymentRepository.findAll();
        assertThat(offerAdvancePaymentList).hasSize(databaseSizeBeforeCreate + 1);
        OfferAdvancePayment testOfferAdvancePayment = offerAdvancePaymentList.get(offerAdvancePaymentList.size() - 1);
        assertThat(testOfferAdvancePayment.getAdvancePaymentId()).isEqualTo(DEFAULT_ADVANCE_PAYMENT_ID);
        assertThat(testOfferAdvancePayment.getOfferId()).isEqualTo(DEFAULT_OFFER_ID);
        assertThat(testOfferAdvancePayment.getAdvancePaymentMonths()).isEqualTo(DEFAULT_ADVANCE_PAYMENT_MONTHS);
        assertThat(testOfferAdvancePayment.getOfferPrice()).isEqualTo(DEFAULT_OFFER_PRICE);
        assertThat(testOfferAdvancePayment.getOfferDiscount()).isEqualTo(DEFAULT_OFFER_DISCOUNT);
        assertThat(testOfferAdvancePayment.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testOfferAdvancePayment.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOfferAdvancePayment.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOfferAdvancePayment.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testOfferAdvancePayment.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testOfferAdvancePayment.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createOfferAdvancePaymentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offerAdvancePaymentRepository.findAll().size();

        // Create the OfferAdvancePayment with an existing ID
        offerAdvancePayment.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfferAdvancePaymentMockMvc.perform(post("/api/offer-advance-payments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerAdvancePayment)))
            .andExpect(status().isBadRequest());

        // Validate the OfferAdvancePayment in the database
        List<OfferAdvancePayment> offerAdvancePaymentList = offerAdvancePaymentRepository.findAll();
        assertThat(offerAdvancePaymentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkAdvancePaymentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerAdvancePaymentRepository.findAll().size();
        // set the field null
        offerAdvancePayment.setAdvancePaymentId(null);

        // Create the OfferAdvancePayment, which fails.

        restOfferAdvancePaymentMockMvc.perform(post("/api/offer-advance-payments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerAdvancePayment)))
            .andExpect(status().isBadRequest());

        List<OfferAdvancePayment> offerAdvancePaymentList = offerAdvancePaymentRepository.findAll();
        assertThat(offerAdvancePaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOfferIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerAdvancePaymentRepository.findAll().size();
        // set the field null
        offerAdvancePayment.setOfferId(null);

        // Create the OfferAdvancePayment, which fails.

        restOfferAdvancePaymentMockMvc.perform(post("/api/offer-advance-payments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerAdvancePayment)))
            .andExpect(status().isBadRequest());

        List<OfferAdvancePayment> offerAdvancePaymentList = offerAdvancePaymentRepository.findAll();
        assertThat(offerAdvancePaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAdvancePaymentMonthsIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerAdvancePaymentRepository.findAll().size();
        // set the field null
        offerAdvancePayment.setAdvancePaymentMonths(null);

        // Create the OfferAdvancePayment, which fails.

        restOfferAdvancePaymentMockMvc.perform(post("/api/offer-advance-payments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerAdvancePayment)))
            .andExpect(status().isBadRequest());

        List<OfferAdvancePayment> offerAdvancePaymentList = offerAdvancePaymentRepository.findAll();
        assertThat(offerAdvancePaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerAdvancePaymentRepository.findAll().size();
        // set the field null
        offerAdvancePayment.setStatus(null);

        // Create the OfferAdvancePayment, which fails.

        restOfferAdvancePaymentMockMvc.perform(post("/api/offer-advance-payments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerAdvancePayment)))
            .andExpect(status().isBadRequest());

        List<OfferAdvancePayment> offerAdvancePaymentList = offerAdvancePaymentRepository.findAll();
        assertThat(offerAdvancePaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerAdvancePaymentRepository.findAll().size();
        // set the field null
        offerAdvancePayment.setCreatedDate(null);

        // Create the OfferAdvancePayment, which fails.

        restOfferAdvancePaymentMockMvc.perform(post("/api/offer-advance-payments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerAdvancePayment)))
            .andExpect(status().isBadRequest());

        List<OfferAdvancePayment> offerAdvancePaymentList = offerAdvancePaymentRepository.findAll();
        assertThat(offerAdvancePaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerAdvancePaymentRepository.findAll().size();
        // set the field null
        offerAdvancePayment.setCreatedBy(null);

        // Create the OfferAdvancePayment, which fails.

        restOfferAdvancePaymentMockMvc.perform(post("/api/offer-advance-payments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerAdvancePayment)))
            .andExpect(status().isBadRequest());

        List<OfferAdvancePayment> offerAdvancePaymentList = offerAdvancePaymentRepository.findAll();
        assertThat(offerAdvancePaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerAdvancePaymentRepository.findAll().size();
        // set the field null
        offerAdvancePayment.setLastUpdatedDate(null);

        // Create the OfferAdvancePayment, which fails.

        restOfferAdvancePaymentMockMvc.perform(post("/api/offer-advance-payments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerAdvancePayment)))
            .andExpect(status().isBadRequest());

        List<OfferAdvancePayment> offerAdvancePaymentList = offerAdvancePaymentRepository.findAll();
        assertThat(offerAdvancePaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerAdvancePaymentRepository.findAll().size();
        // set the field null
        offerAdvancePayment.setLastUpdatedBy(null);

        // Create the OfferAdvancePayment, which fails.

        restOfferAdvancePaymentMockMvc.perform(post("/api/offer-advance-payments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerAdvancePayment)))
            .andExpect(status().isBadRequest());

        List<OfferAdvancePayment> offerAdvancePaymentList = offerAdvancePaymentRepository.findAll();
        assertThat(offerAdvancePaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerAdvancePaymentRepository.findAll().size();
        // set the field null
        offerAdvancePayment.setTenantId(null);

        // Create the OfferAdvancePayment, which fails.

        restOfferAdvancePaymentMockMvc.perform(post("/api/offer-advance-payments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerAdvancePayment)))
            .andExpect(status().isBadRequest());

        List<OfferAdvancePayment> offerAdvancePaymentList = offerAdvancePaymentRepository.findAll();
        assertThat(offerAdvancePaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllOfferAdvancePayments() throws Exception {
        // Initialize the database
        offerAdvancePaymentRepository.save(offerAdvancePayment);

        // Get all the offerAdvancePaymentList
        restOfferAdvancePaymentMockMvc.perform(get("/api/offer-advance-payments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offerAdvancePayment.getId())))
            .andExpect(jsonPath("$.[*].advancePaymentId").value(hasItem(DEFAULT_ADVANCE_PAYMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].offerId").value(hasItem(DEFAULT_OFFER_ID)))
            .andExpect(jsonPath("$.[*].advancePaymentMonths").value(hasItem(DEFAULT_ADVANCE_PAYMENT_MONTHS)))
            .andExpect(jsonPath("$.[*].offerPrice").value(hasItem(DEFAULT_OFFER_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].offerDiscount").value(hasItem(DEFAULT_OFFER_DISCOUNT.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getOfferAdvancePayment() throws Exception {
        // Initialize the database
        offerAdvancePaymentRepository.save(offerAdvancePayment);

        // Get the offerAdvancePayment
        restOfferAdvancePaymentMockMvc.perform(get("/api/offer-advance-payments/{id}", offerAdvancePayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offerAdvancePayment.getId()))
            .andExpect(jsonPath("$.advancePaymentId").value(DEFAULT_ADVANCE_PAYMENT_ID.intValue()))
            .andExpect(jsonPath("$.offerId").value(DEFAULT_OFFER_ID))
            .andExpect(jsonPath("$.advancePaymentMonths").value(DEFAULT_ADVANCE_PAYMENT_MONTHS))
            .andExpect(jsonPath("$.offerPrice").value(DEFAULT_OFFER_PRICE.intValue()))
            .andExpect(jsonPath("$.offerDiscount").value(DEFAULT_OFFER_DISCOUNT.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingOfferAdvancePayment() throws Exception {
        // Get the offerAdvancePayment
        restOfferAdvancePaymentMockMvc.perform(get("/api/offer-advance-payments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOfferAdvancePayment() throws Exception {
        // Initialize the database
        offerAdvancePaymentService.save(offerAdvancePayment);

        int databaseSizeBeforeUpdate = offerAdvancePaymentRepository.findAll().size();

        // Update the offerAdvancePayment
        OfferAdvancePayment updatedOfferAdvancePayment = offerAdvancePaymentRepository.findById(offerAdvancePayment.getId()).get();
        updatedOfferAdvancePayment
            .advancePaymentId(UPDATED_ADVANCE_PAYMENT_ID)
            .offerId(UPDATED_OFFER_ID)
            .advancePaymentMonths(UPDATED_ADVANCE_PAYMENT_MONTHS)
            .offerPrice(UPDATED_OFFER_PRICE)
            .offerDiscount(UPDATED_OFFER_DISCOUNT)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restOfferAdvancePaymentMockMvc.perform(put("/api/offer-advance-payments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOfferAdvancePayment)))
            .andExpect(status().isOk());

        // Validate the OfferAdvancePayment in the database
        List<OfferAdvancePayment> offerAdvancePaymentList = offerAdvancePaymentRepository.findAll();
        assertThat(offerAdvancePaymentList).hasSize(databaseSizeBeforeUpdate);
        OfferAdvancePayment testOfferAdvancePayment = offerAdvancePaymentList.get(offerAdvancePaymentList.size() - 1);
        assertThat(testOfferAdvancePayment.getAdvancePaymentId()).isEqualTo(UPDATED_ADVANCE_PAYMENT_ID);
        assertThat(testOfferAdvancePayment.getOfferId()).isEqualTo(UPDATED_OFFER_ID);
        assertThat(testOfferAdvancePayment.getAdvancePaymentMonths()).isEqualTo(UPDATED_ADVANCE_PAYMENT_MONTHS);
        assertThat(testOfferAdvancePayment.getOfferPrice()).isEqualTo(UPDATED_OFFER_PRICE);
        assertThat(testOfferAdvancePayment.getOfferDiscount()).isEqualTo(UPDATED_OFFER_DISCOUNT);
        assertThat(testOfferAdvancePayment.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOfferAdvancePayment.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOfferAdvancePayment.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOfferAdvancePayment.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testOfferAdvancePayment.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testOfferAdvancePayment.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingOfferAdvancePayment() throws Exception {
        int databaseSizeBeforeUpdate = offerAdvancePaymentRepository.findAll().size();

        // Create the OfferAdvancePayment

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferAdvancePaymentMockMvc.perform(put("/api/offer-advance-payments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerAdvancePayment)))
            .andExpect(status().isBadRequest());

        // Validate the OfferAdvancePayment in the database
        List<OfferAdvancePayment> offerAdvancePaymentList = offerAdvancePaymentRepository.findAll();
        assertThat(offerAdvancePaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOfferAdvancePayment() throws Exception {
        // Initialize the database
        offerAdvancePaymentService.save(offerAdvancePayment);

        int databaseSizeBeforeDelete = offerAdvancePaymentRepository.findAll().size();

        // Delete the offerAdvancePayment
        restOfferAdvancePaymentMockMvc.perform(delete("/api/offer-advance-payments/{id}", offerAdvancePayment.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OfferAdvancePayment> offerAdvancePaymentList = offerAdvancePaymentRepository.findAll();
        assertThat(offerAdvancePaymentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
