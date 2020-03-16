package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.Offer;
import com.hthk.crm.repository.OfferRepository;
import com.hthk.crm.service.OfferService;
import com.hthk.crm.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.hthk.crm.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.hthk.crm.domain.enumeration.OfferType;
/**
 * Integration tests for the {@link OfferResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class OfferResourceIT {

    private static final String DEFAULT_OFFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_EXTERNAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_EXTERNAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_NAME_CHI = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_NAME_CHI = "BBBBBBBBBB";

    private static final OfferType DEFAULT_OFFER_TYPE = OfferType.BASE;
    private static final OfferType UPDATED_OFFER_TYPE = OfferType.ADDON;

    private static final BigDecimal DEFAULT_OFFER_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_OFFER_PRICE = new BigDecimal(2);

    private static final String DEFAULT_CUSTOMER_SEGMENTS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_SEGMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_CLASSES = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_CLASSES = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_CHANNELS = "AAAAAAAAAA";
    private static final String UPDATED_SALES_CHANNELS = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CHILD_OFFER_IDS = "AAAAAAAAAA";
    private static final String UPDATED_CHILD_OFFER_IDS = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_SPEC_IDS = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_SPEC_IDS = "BBBBBBBBBB";

    private static final String DEFAULT_ADVANCE_PAYMENT_IDS = "AAAAAAAAAA";
    private static final String UPDATED_ADVANCE_PAYMENT_IDS = "BBBBBBBBBB";

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
    private OfferRepository offerRepository;

    @Mock
    private OfferRepository offerRepositoryMock;

    @Mock
    private OfferService offerServiceMock;

    @Autowired
    private OfferService offerService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restOfferMockMvc;

    private Offer offer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OfferResource offerResource = new OfferResource(offerService);
        this.restOfferMockMvc = MockMvcBuilders.standaloneSetup(offerResource)
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
    public static Offer createEntity() {
        Offer offer = new Offer()
            .offerId(DEFAULT_OFFER_ID)
            .offerExternalId(DEFAULT_OFFER_EXTERNAL_ID)
            .offerName(DEFAULT_OFFER_NAME)
            .offerNameChi(DEFAULT_OFFER_NAME_CHI)
            .offerType(DEFAULT_OFFER_TYPE)
            .offerPrice(DEFAULT_OFFER_PRICE)
            .customerSegments(DEFAULT_CUSTOMER_SEGMENTS)
            .customerClasses(DEFAULT_CUSTOMER_CLASSES)
            .salesChannels(DEFAULT_SALES_CHANNELS)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .childOfferIds(DEFAULT_CHILD_OFFER_IDS)
            .productSpecIds(DEFAULT_PRODUCT_SPEC_IDS)
            .advancePaymentIds(DEFAULT_ADVANCE_PAYMENT_IDS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return offer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Offer createUpdatedEntity() {
        Offer offer = new Offer()
            .offerId(UPDATED_OFFER_ID)
            .offerExternalId(UPDATED_OFFER_EXTERNAL_ID)
            .offerName(UPDATED_OFFER_NAME)
            .offerNameChi(UPDATED_OFFER_NAME_CHI)
            .offerType(UPDATED_OFFER_TYPE)
            .offerPrice(UPDATED_OFFER_PRICE)
            .customerSegments(UPDATED_CUSTOMER_SEGMENTS)
            .customerClasses(UPDATED_CUSTOMER_CLASSES)
            .salesChannels(UPDATED_SALES_CHANNELS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .childOfferIds(UPDATED_CHILD_OFFER_IDS)
            .productSpecIds(UPDATED_PRODUCT_SPEC_IDS)
            .advancePaymentIds(UPDATED_ADVANCE_PAYMENT_IDS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return offer;
    }

    @BeforeEach
    public void initTest() {
        offerRepository.deleteAll();
        offer = createEntity();
    }

    @Test
    public void createOffer() throws Exception {
        int databaseSizeBeforeCreate = offerRepository.findAll().size();

        // Create the Offer
        restOfferMockMvc.perform(post("/api/offers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isCreated());

        // Validate the Offer in the database
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeCreate + 1);
        Offer testOffer = offerList.get(offerList.size() - 1);
        assertThat(testOffer.getOfferId()).isEqualTo(DEFAULT_OFFER_ID);
        assertThat(testOffer.getOfferExternalId()).isEqualTo(DEFAULT_OFFER_EXTERNAL_ID);
        assertThat(testOffer.getOfferName()).isEqualTo(DEFAULT_OFFER_NAME);
        assertThat(testOffer.getOfferNameChi()).isEqualTo(DEFAULT_OFFER_NAME_CHI);
        assertThat(testOffer.getOfferType()).isEqualTo(DEFAULT_OFFER_TYPE);
        assertThat(testOffer.getOfferPrice()).isEqualTo(DEFAULT_OFFER_PRICE);
        assertThat(testOffer.getCustomerSegments()).isEqualTo(DEFAULT_CUSTOMER_SEGMENTS);
        assertThat(testOffer.getCustomerClasses()).isEqualTo(DEFAULT_CUSTOMER_CLASSES);
        assertThat(testOffer.getSalesChannels()).isEqualTo(DEFAULT_SALES_CHANNELS);
        assertThat(testOffer.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testOffer.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testOffer.getChildOfferIds()).isEqualTo(DEFAULT_CHILD_OFFER_IDS);
        assertThat(testOffer.getProductSpecIds()).isEqualTo(DEFAULT_PRODUCT_SPEC_IDS);
        assertThat(testOffer.getAdvancePaymentIds()).isEqualTo(DEFAULT_ADVANCE_PAYMENT_IDS);
        assertThat(testOffer.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOffer.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOffer.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testOffer.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testOffer.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createOfferWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offerRepository.findAll().size();

        // Create the Offer with an existing ID
        offer.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfferMockMvc.perform(post("/api/offers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        // Validate the Offer in the database
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkOfferIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setOfferId(null);

        // Create the Offer, which fails.

        restOfferMockMvc.perform(post("/api/offers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOfferNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setOfferName(null);

        // Create the Offer, which fails.

        restOfferMockMvc.perform(post("/api/offers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setCreatedDate(null);

        // Create the Offer, which fails.

        restOfferMockMvc.perform(post("/api/offers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setCreatedBy(null);

        // Create the Offer, which fails.

        restOfferMockMvc.perform(post("/api/offers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setLastUpdatedDate(null);

        // Create the Offer, which fails.

        restOfferMockMvc.perform(post("/api/offers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setLastUpdatedBy(null);

        // Create the Offer, which fails.

        restOfferMockMvc.perform(post("/api/offers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerRepository.findAll().size();
        // set the field null
        offer.setTenantId(null);

        // Create the Offer, which fails.

        restOfferMockMvc.perform(post("/api/offers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllOffers() throws Exception {
        // Initialize the database
        offerRepository.save(offer);

        // Get all the offerList
        restOfferMockMvc.perform(get("/api/offers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offer.getId())))
            .andExpect(jsonPath("$.[*].offerId").value(hasItem(DEFAULT_OFFER_ID)))
            .andExpect(jsonPath("$.[*].offerExternalId").value(hasItem(DEFAULT_OFFER_EXTERNAL_ID)))
            .andExpect(jsonPath("$.[*].offerName").value(hasItem(DEFAULT_OFFER_NAME)))
            .andExpect(jsonPath("$.[*].offerNameChi").value(hasItem(DEFAULT_OFFER_NAME_CHI)))
            .andExpect(jsonPath("$.[*].offerType").value(hasItem(DEFAULT_OFFER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].offerPrice").value(hasItem(DEFAULT_OFFER_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].customerSegments").value(hasItem(DEFAULT_CUSTOMER_SEGMENTS)))
            .andExpect(jsonPath("$.[*].customerClasses").value(hasItem(DEFAULT_CUSTOMER_CLASSES)))
            .andExpect(jsonPath("$.[*].salesChannels").value(hasItem(DEFAULT_SALES_CHANNELS)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].childOfferIds").value(hasItem(DEFAULT_CHILD_OFFER_IDS)))
            .andExpect(jsonPath("$.[*].productSpecIds").value(hasItem(DEFAULT_PRODUCT_SPEC_IDS)))
            .andExpect(jsonPath("$.[*].advancePaymentIds").value(hasItem(DEFAULT_ADVANCE_PAYMENT_IDS)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllOffersWithEagerRelationshipsIsEnabled() throws Exception {
        OfferResource offerResource = new OfferResource(offerServiceMock);
        when(offerServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restOfferMockMvc = MockMvcBuilders.standaloneSetup(offerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restOfferMockMvc.perform(get("/api/offers?eagerload=true"))
        .andExpect(status().isOk());

        verify(offerServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllOffersWithEagerRelationshipsIsNotEnabled() throws Exception {
        OfferResource offerResource = new OfferResource(offerServiceMock);
            when(offerServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restOfferMockMvc = MockMvcBuilders.standaloneSetup(offerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restOfferMockMvc.perform(get("/api/offers?eagerload=true"))
        .andExpect(status().isOk());

            verify(offerServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    public void getOffer() throws Exception {
        // Initialize the database
        offerRepository.save(offer);

        // Get the offer
        restOfferMockMvc.perform(get("/api/offers/{id}", offer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offer.getId()))
            .andExpect(jsonPath("$.offerId").value(DEFAULT_OFFER_ID))
            .andExpect(jsonPath("$.offerExternalId").value(DEFAULT_OFFER_EXTERNAL_ID))
            .andExpect(jsonPath("$.offerName").value(DEFAULT_OFFER_NAME))
            .andExpect(jsonPath("$.offerNameChi").value(DEFAULT_OFFER_NAME_CHI))
            .andExpect(jsonPath("$.offerType").value(DEFAULT_OFFER_TYPE.toString()))
            .andExpect(jsonPath("$.offerPrice").value(DEFAULT_OFFER_PRICE.intValue()))
            .andExpect(jsonPath("$.customerSegments").value(DEFAULT_CUSTOMER_SEGMENTS))
            .andExpect(jsonPath("$.customerClasses").value(DEFAULT_CUSTOMER_CLASSES))
            .andExpect(jsonPath("$.salesChannels").value(DEFAULT_SALES_CHANNELS))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.childOfferIds").value(DEFAULT_CHILD_OFFER_IDS))
            .andExpect(jsonPath("$.productSpecIds").value(DEFAULT_PRODUCT_SPEC_IDS))
            .andExpect(jsonPath("$.advancePaymentIds").value(DEFAULT_ADVANCE_PAYMENT_IDS))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingOffer() throws Exception {
        // Get the offer
        restOfferMockMvc.perform(get("/api/offers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOffer() throws Exception {
        // Initialize the database
        offerService.save(offer);

        int databaseSizeBeforeUpdate = offerRepository.findAll().size();

        // Update the offer
        Offer updatedOffer = offerRepository.findById(offer.getId()).get();
        updatedOffer
            .offerId(UPDATED_OFFER_ID)
            .offerExternalId(UPDATED_OFFER_EXTERNAL_ID)
            .offerName(UPDATED_OFFER_NAME)
            .offerNameChi(UPDATED_OFFER_NAME_CHI)
            .offerType(UPDATED_OFFER_TYPE)
            .offerPrice(UPDATED_OFFER_PRICE)
            .customerSegments(UPDATED_CUSTOMER_SEGMENTS)
            .customerClasses(UPDATED_CUSTOMER_CLASSES)
            .salesChannels(UPDATED_SALES_CHANNELS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .childOfferIds(UPDATED_CHILD_OFFER_IDS)
            .productSpecIds(UPDATED_PRODUCT_SPEC_IDS)
            .advancePaymentIds(UPDATED_ADVANCE_PAYMENT_IDS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restOfferMockMvc.perform(put("/api/offers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOffer)))
            .andExpect(status().isOk());

        // Validate the Offer in the database
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeUpdate);
        Offer testOffer = offerList.get(offerList.size() - 1);
        assertThat(testOffer.getOfferId()).isEqualTo(UPDATED_OFFER_ID);
        assertThat(testOffer.getOfferExternalId()).isEqualTo(UPDATED_OFFER_EXTERNAL_ID);
        assertThat(testOffer.getOfferName()).isEqualTo(UPDATED_OFFER_NAME);
        assertThat(testOffer.getOfferNameChi()).isEqualTo(UPDATED_OFFER_NAME_CHI);
        assertThat(testOffer.getOfferType()).isEqualTo(UPDATED_OFFER_TYPE);
        assertThat(testOffer.getOfferPrice()).isEqualTo(UPDATED_OFFER_PRICE);
        assertThat(testOffer.getCustomerSegments()).isEqualTo(UPDATED_CUSTOMER_SEGMENTS);
        assertThat(testOffer.getCustomerClasses()).isEqualTo(UPDATED_CUSTOMER_CLASSES);
        assertThat(testOffer.getSalesChannels()).isEqualTo(UPDATED_SALES_CHANNELS);
        assertThat(testOffer.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testOffer.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testOffer.getChildOfferIds()).isEqualTo(UPDATED_CHILD_OFFER_IDS);
        assertThat(testOffer.getProductSpecIds()).isEqualTo(UPDATED_PRODUCT_SPEC_IDS);
        assertThat(testOffer.getAdvancePaymentIds()).isEqualTo(UPDATED_ADVANCE_PAYMENT_IDS);
        assertThat(testOffer.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOffer.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOffer.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testOffer.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testOffer.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingOffer() throws Exception {
        int databaseSizeBeforeUpdate = offerRepository.findAll().size();

        // Create the Offer

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferMockMvc.perform(put("/api/offers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offer)))
            .andExpect(status().isBadRequest());

        // Validate the Offer in the database
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOffer() throws Exception {
        // Initialize the database
        offerService.save(offer);

        int databaseSizeBeforeDelete = offerRepository.findAll().size();

        // Delete the offer
        restOfferMockMvc.perform(delete("/api/offers/{id}", offer.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
