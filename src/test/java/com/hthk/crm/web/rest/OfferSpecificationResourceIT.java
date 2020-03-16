package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.OfferSpecification;
import com.hthk.crm.repository.OfferSpecificationRepository;
import com.hthk.crm.service.OfferSpecificationService;
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
 * Integration tests for the {@link OfferSpecificationResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class OfferSpecificationResourceIT {

    private static final Long DEFAULT_OFFER_SPEC_ID = 1L;
    private static final Long UPDATED_OFFER_SPEC_ID = 2L;

    private static final String DEFAULT_OFFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_LIMITED_ACTIVATION_PERIOD = false;
    private static final Boolean UPDATED_LIMITED_ACTIVATION_PERIOD = true;

    private static final Instant DEFAULT_ALLOWED_ACTIVATION_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ALLOWED_ACTIVATION_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ALLOWED_ACTIVATION_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ALLOWED_ACTIVATION_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_IS_GROUP_SHARING_OFFER = false;
    private static final Boolean UPDATED_IS_GROUP_SHARING_OFFER = true;

    private static final Boolean DEFAULT_IS_MNP_OFFER = false;
    private static final Boolean UPDATED_IS_MNP_OFFER = true;

    private static final Boolean DEFAULT_AUTO_RENEWAL = false;
    private static final Boolean UPDATED_AUTO_RENEWAL = true;

    private static final Boolean DEFAULT_TRANSFER_ALLOWED = false;
    private static final Boolean UPDATED_TRANSFER_ALLOWED = true;

    private static final Boolean DEFAULT_INFO_SHARING_ALLOWED = false;
    private static final Boolean UPDATED_INFO_SHARING_ALLOWED = true;

    private static final String DEFAULT_INFO_SHARING_OPTIONS = "AAAAAAAAAA";
    private static final String UPDATED_INFO_SHARING_OPTIONS = "BBBBBBBBBB";

    private static final Integer DEFAULT_OFFER_PERIOD = 1;
    private static final Integer UPDATED_OFFER_PERIOD = 2;

    private static final String DEFAULT_OFFER_PERIOD_TERM = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_PERIOD_TERM = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_TYPE = "BBBBBBBBBB";

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
    private OfferSpecificationRepository offerSpecificationRepository;

    @Autowired
    private OfferSpecificationService offerSpecificationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restOfferSpecificationMockMvc;

    private OfferSpecification offerSpecification;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OfferSpecificationResource offerSpecificationResource = new OfferSpecificationResource(offerSpecificationService);
        this.restOfferSpecificationMockMvc = MockMvcBuilders.standaloneSetup(offerSpecificationResource)
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
    public static OfferSpecification createEntity() {
        OfferSpecification offerSpecification = new OfferSpecification()
            .offerSpecId(DEFAULT_OFFER_SPEC_ID)
            .offerId(DEFAULT_OFFER_ID)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .limitedActivationPeriod(DEFAULT_LIMITED_ACTIVATION_PERIOD)
            .allowedActivationStartDate(DEFAULT_ALLOWED_ACTIVATION_START_DATE)
            .allowedActivationEndDate(DEFAULT_ALLOWED_ACTIVATION_END_DATE)
            .isGroupSharingOffer(DEFAULT_IS_GROUP_SHARING_OFFER)
            .isMnpOffer(DEFAULT_IS_MNP_OFFER)
            .autoRenewal(DEFAULT_AUTO_RENEWAL)
            .transferAllowed(DEFAULT_TRANSFER_ALLOWED)
            .infoSharingAllowed(DEFAULT_INFO_SHARING_ALLOWED)
            .infoSharingOptions(DEFAULT_INFO_SHARING_OPTIONS)
            .offerPeriod(DEFAULT_OFFER_PERIOD)
            .offerPeriodTerm(DEFAULT_OFFER_PERIOD_TERM)
            .paymentType(DEFAULT_PAYMENT_TYPE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return offerSpecification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferSpecification createUpdatedEntity() {
        OfferSpecification offerSpecification = new OfferSpecification()
            .offerSpecId(UPDATED_OFFER_SPEC_ID)
            .offerId(UPDATED_OFFER_ID)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .limitedActivationPeriod(UPDATED_LIMITED_ACTIVATION_PERIOD)
            .allowedActivationStartDate(UPDATED_ALLOWED_ACTIVATION_START_DATE)
            .allowedActivationEndDate(UPDATED_ALLOWED_ACTIVATION_END_DATE)
            .isGroupSharingOffer(UPDATED_IS_GROUP_SHARING_OFFER)
            .isMnpOffer(UPDATED_IS_MNP_OFFER)
            .autoRenewal(UPDATED_AUTO_RENEWAL)
            .transferAllowed(UPDATED_TRANSFER_ALLOWED)
            .infoSharingAllowed(UPDATED_INFO_SHARING_ALLOWED)
            .infoSharingOptions(UPDATED_INFO_SHARING_OPTIONS)
            .offerPeriod(UPDATED_OFFER_PERIOD)
            .offerPeriodTerm(UPDATED_OFFER_PERIOD_TERM)
            .paymentType(UPDATED_PAYMENT_TYPE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return offerSpecification;
    }

    @BeforeEach
    public void initTest() {
        offerSpecificationRepository.deleteAll();
        offerSpecification = createEntity();
    }

    @Test
    public void createOfferSpecification() throws Exception {
        int databaseSizeBeforeCreate = offerSpecificationRepository.findAll().size();

        // Create the OfferSpecification
        restOfferSpecificationMockMvc.perform(post("/api/offer-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerSpecification)))
            .andExpect(status().isCreated());

        // Validate the OfferSpecification in the database
        List<OfferSpecification> offerSpecificationList = offerSpecificationRepository.findAll();
        assertThat(offerSpecificationList).hasSize(databaseSizeBeforeCreate + 1);
        OfferSpecification testOfferSpecification = offerSpecificationList.get(offerSpecificationList.size() - 1);
        assertThat(testOfferSpecification.getOfferSpecId()).isEqualTo(DEFAULT_OFFER_SPEC_ID);
        assertThat(testOfferSpecification.getOfferId()).isEqualTo(DEFAULT_OFFER_ID);
        assertThat(testOfferSpecification.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testOfferSpecification.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testOfferSpecification.isLimitedActivationPeriod()).isEqualTo(DEFAULT_LIMITED_ACTIVATION_PERIOD);
        assertThat(testOfferSpecification.getAllowedActivationStartDate()).isEqualTo(DEFAULT_ALLOWED_ACTIVATION_START_DATE);
        assertThat(testOfferSpecification.getAllowedActivationEndDate()).isEqualTo(DEFAULT_ALLOWED_ACTIVATION_END_DATE);
        assertThat(testOfferSpecification.isIsGroupSharingOffer()).isEqualTo(DEFAULT_IS_GROUP_SHARING_OFFER);
        assertThat(testOfferSpecification.isIsMnpOffer()).isEqualTo(DEFAULT_IS_MNP_OFFER);
        assertThat(testOfferSpecification.isAutoRenewal()).isEqualTo(DEFAULT_AUTO_RENEWAL);
        assertThat(testOfferSpecification.isTransferAllowed()).isEqualTo(DEFAULT_TRANSFER_ALLOWED);
        assertThat(testOfferSpecification.isInfoSharingAllowed()).isEqualTo(DEFAULT_INFO_SHARING_ALLOWED);
        assertThat(testOfferSpecification.getInfoSharingOptions()).isEqualTo(DEFAULT_INFO_SHARING_OPTIONS);
        assertThat(testOfferSpecification.getOfferPeriod()).isEqualTo(DEFAULT_OFFER_PERIOD);
        assertThat(testOfferSpecification.getOfferPeriodTerm()).isEqualTo(DEFAULT_OFFER_PERIOD_TERM);
        assertThat(testOfferSpecification.getPaymentType()).isEqualTo(DEFAULT_PAYMENT_TYPE);
        assertThat(testOfferSpecification.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOfferSpecification.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOfferSpecification.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testOfferSpecification.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testOfferSpecification.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createOfferSpecificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offerSpecificationRepository.findAll().size();

        // Create the OfferSpecification with an existing ID
        offerSpecification.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfferSpecificationMockMvc.perform(post("/api/offer-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerSpecification)))
            .andExpect(status().isBadRequest());

        // Validate the OfferSpecification in the database
        List<OfferSpecification> offerSpecificationList = offerSpecificationRepository.findAll();
        assertThat(offerSpecificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkOfferSpecIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerSpecificationRepository.findAll().size();
        // set the field null
        offerSpecification.setOfferSpecId(null);

        // Create the OfferSpecification, which fails.

        restOfferSpecificationMockMvc.perform(post("/api/offer-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerSpecification)))
            .andExpect(status().isBadRequest());

        List<OfferSpecification> offerSpecificationList = offerSpecificationRepository.findAll();
        assertThat(offerSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOfferIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerSpecificationRepository.findAll().size();
        // set the field null
        offerSpecification.setOfferId(null);

        // Create the OfferSpecification, which fails.

        restOfferSpecificationMockMvc.perform(post("/api/offer-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerSpecification)))
            .andExpect(status().isBadRequest());

        List<OfferSpecification> offerSpecificationList = offerSpecificationRepository.findAll();
        assertThat(offerSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerSpecificationRepository.findAll().size();
        // set the field null
        offerSpecification.setCreatedDate(null);

        // Create the OfferSpecification, which fails.

        restOfferSpecificationMockMvc.perform(post("/api/offer-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerSpecification)))
            .andExpect(status().isBadRequest());

        List<OfferSpecification> offerSpecificationList = offerSpecificationRepository.findAll();
        assertThat(offerSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerSpecificationRepository.findAll().size();
        // set the field null
        offerSpecification.setCreatedBy(null);

        // Create the OfferSpecification, which fails.

        restOfferSpecificationMockMvc.perform(post("/api/offer-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerSpecification)))
            .andExpect(status().isBadRequest());

        List<OfferSpecification> offerSpecificationList = offerSpecificationRepository.findAll();
        assertThat(offerSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerSpecificationRepository.findAll().size();
        // set the field null
        offerSpecification.setLastUpdatedDate(null);

        // Create the OfferSpecification, which fails.

        restOfferSpecificationMockMvc.perform(post("/api/offer-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerSpecification)))
            .andExpect(status().isBadRequest());

        List<OfferSpecification> offerSpecificationList = offerSpecificationRepository.findAll();
        assertThat(offerSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerSpecificationRepository.findAll().size();
        // set the field null
        offerSpecification.setLastUpdatedBy(null);

        // Create the OfferSpecification, which fails.

        restOfferSpecificationMockMvc.perform(post("/api/offer-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerSpecification)))
            .andExpect(status().isBadRequest());

        List<OfferSpecification> offerSpecificationList = offerSpecificationRepository.findAll();
        assertThat(offerSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = offerSpecificationRepository.findAll().size();
        // set the field null
        offerSpecification.setTenantId(null);

        // Create the OfferSpecification, which fails.

        restOfferSpecificationMockMvc.perform(post("/api/offer-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerSpecification)))
            .andExpect(status().isBadRequest());

        List<OfferSpecification> offerSpecificationList = offerSpecificationRepository.findAll();
        assertThat(offerSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllOfferSpecifications() throws Exception {
        // Initialize the database
        offerSpecificationRepository.save(offerSpecification);

        // Get all the offerSpecificationList
        restOfferSpecificationMockMvc.perform(get("/api/offer-specifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offerSpecification.getId())))
            .andExpect(jsonPath("$.[*].offerSpecId").value(hasItem(DEFAULT_OFFER_SPEC_ID.intValue())))
            .andExpect(jsonPath("$.[*].offerId").value(hasItem(DEFAULT_OFFER_ID)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].limitedActivationPeriod").value(hasItem(DEFAULT_LIMITED_ACTIVATION_PERIOD.booleanValue())))
            .andExpect(jsonPath("$.[*].allowedActivationStartDate").value(hasItem(DEFAULT_ALLOWED_ACTIVATION_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].allowedActivationEndDate").value(hasItem(DEFAULT_ALLOWED_ACTIVATION_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].isGroupSharingOffer").value(hasItem(DEFAULT_IS_GROUP_SHARING_OFFER.booleanValue())))
            .andExpect(jsonPath("$.[*].isMnpOffer").value(hasItem(DEFAULT_IS_MNP_OFFER.booleanValue())))
            .andExpect(jsonPath("$.[*].autoRenewal").value(hasItem(DEFAULT_AUTO_RENEWAL.booleanValue())))
            .andExpect(jsonPath("$.[*].transferAllowed").value(hasItem(DEFAULT_TRANSFER_ALLOWED.booleanValue())))
            .andExpect(jsonPath("$.[*].infoSharingAllowed").value(hasItem(DEFAULT_INFO_SHARING_ALLOWED.booleanValue())))
            .andExpect(jsonPath("$.[*].infoSharingOptions").value(hasItem(DEFAULT_INFO_SHARING_OPTIONS)))
            .andExpect(jsonPath("$.[*].offerPeriod").value(hasItem(DEFAULT_OFFER_PERIOD)))
            .andExpect(jsonPath("$.[*].offerPeriodTerm").value(hasItem(DEFAULT_OFFER_PERIOD_TERM)))
            .andExpect(jsonPath("$.[*].paymentType").value(hasItem(DEFAULT_PAYMENT_TYPE)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getOfferSpecification() throws Exception {
        // Initialize the database
        offerSpecificationRepository.save(offerSpecification);

        // Get the offerSpecification
        restOfferSpecificationMockMvc.perform(get("/api/offer-specifications/{id}", offerSpecification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offerSpecification.getId()))
            .andExpect(jsonPath("$.offerSpecId").value(DEFAULT_OFFER_SPEC_ID.intValue()))
            .andExpect(jsonPath("$.offerId").value(DEFAULT_OFFER_ID))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.limitedActivationPeriod").value(DEFAULT_LIMITED_ACTIVATION_PERIOD.booleanValue()))
            .andExpect(jsonPath("$.allowedActivationStartDate").value(DEFAULT_ALLOWED_ACTIVATION_START_DATE.toString()))
            .andExpect(jsonPath("$.allowedActivationEndDate").value(DEFAULT_ALLOWED_ACTIVATION_END_DATE.toString()))
            .andExpect(jsonPath("$.isGroupSharingOffer").value(DEFAULT_IS_GROUP_SHARING_OFFER.booleanValue()))
            .andExpect(jsonPath("$.isMnpOffer").value(DEFAULT_IS_MNP_OFFER.booleanValue()))
            .andExpect(jsonPath("$.autoRenewal").value(DEFAULT_AUTO_RENEWAL.booleanValue()))
            .andExpect(jsonPath("$.transferAllowed").value(DEFAULT_TRANSFER_ALLOWED.booleanValue()))
            .andExpect(jsonPath("$.infoSharingAllowed").value(DEFAULT_INFO_SHARING_ALLOWED.booleanValue()))
            .andExpect(jsonPath("$.infoSharingOptions").value(DEFAULT_INFO_SHARING_OPTIONS))
            .andExpect(jsonPath("$.offerPeriod").value(DEFAULT_OFFER_PERIOD))
            .andExpect(jsonPath("$.offerPeriodTerm").value(DEFAULT_OFFER_PERIOD_TERM))
            .andExpect(jsonPath("$.paymentType").value(DEFAULT_PAYMENT_TYPE))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingOfferSpecification() throws Exception {
        // Get the offerSpecification
        restOfferSpecificationMockMvc.perform(get("/api/offer-specifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOfferSpecification() throws Exception {
        // Initialize the database
        offerSpecificationService.save(offerSpecification);

        int databaseSizeBeforeUpdate = offerSpecificationRepository.findAll().size();

        // Update the offerSpecification
        OfferSpecification updatedOfferSpecification = offerSpecificationRepository.findById(offerSpecification.getId()).get();
        updatedOfferSpecification
            .offerSpecId(UPDATED_OFFER_SPEC_ID)
            .offerId(UPDATED_OFFER_ID)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .limitedActivationPeriod(UPDATED_LIMITED_ACTIVATION_PERIOD)
            .allowedActivationStartDate(UPDATED_ALLOWED_ACTIVATION_START_DATE)
            .allowedActivationEndDate(UPDATED_ALLOWED_ACTIVATION_END_DATE)
            .isGroupSharingOffer(UPDATED_IS_GROUP_SHARING_OFFER)
            .isMnpOffer(UPDATED_IS_MNP_OFFER)
            .autoRenewal(UPDATED_AUTO_RENEWAL)
            .transferAllowed(UPDATED_TRANSFER_ALLOWED)
            .infoSharingAllowed(UPDATED_INFO_SHARING_ALLOWED)
            .infoSharingOptions(UPDATED_INFO_SHARING_OPTIONS)
            .offerPeriod(UPDATED_OFFER_PERIOD)
            .offerPeriodTerm(UPDATED_OFFER_PERIOD_TERM)
            .paymentType(UPDATED_PAYMENT_TYPE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restOfferSpecificationMockMvc.perform(put("/api/offer-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOfferSpecification)))
            .andExpect(status().isOk());

        // Validate the OfferSpecification in the database
        List<OfferSpecification> offerSpecificationList = offerSpecificationRepository.findAll();
        assertThat(offerSpecificationList).hasSize(databaseSizeBeforeUpdate);
        OfferSpecification testOfferSpecification = offerSpecificationList.get(offerSpecificationList.size() - 1);
        assertThat(testOfferSpecification.getOfferSpecId()).isEqualTo(UPDATED_OFFER_SPEC_ID);
        assertThat(testOfferSpecification.getOfferId()).isEqualTo(UPDATED_OFFER_ID);
        assertThat(testOfferSpecification.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testOfferSpecification.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testOfferSpecification.isLimitedActivationPeriod()).isEqualTo(UPDATED_LIMITED_ACTIVATION_PERIOD);
        assertThat(testOfferSpecification.getAllowedActivationStartDate()).isEqualTo(UPDATED_ALLOWED_ACTIVATION_START_DATE);
        assertThat(testOfferSpecification.getAllowedActivationEndDate()).isEqualTo(UPDATED_ALLOWED_ACTIVATION_END_DATE);
        assertThat(testOfferSpecification.isIsGroupSharingOffer()).isEqualTo(UPDATED_IS_GROUP_SHARING_OFFER);
        assertThat(testOfferSpecification.isIsMnpOffer()).isEqualTo(UPDATED_IS_MNP_OFFER);
        assertThat(testOfferSpecification.isAutoRenewal()).isEqualTo(UPDATED_AUTO_RENEWAL);
        assertThat(testOfferSpecification.isTransferAllowed()).isEqualTo(UPDATED_TRANSFER_ALLOWED);
        assertThat(testOfferSpecification.isInfoSharingAllowed()).isEqualTo(UPDATED_INFO_SHARING_ALLOWED);
        assertThat(testOfferSpecification.getInfoSharingOptions()).isEqualTo(UPDATED_INFO_SHARING_OPTIONS);
        assertThat(testOfferSpecification.getOfferPeriod()).isEqualTo(UPDATED_OFFER_PERIOD);
        assertThat(testOfferSpecification.getOfferPeriodTerm()).isEqualTo(UPDATED_OFFER_PERIOD_TERM);
        assertThat(testOfferSpecification.getPaymentType()).isEqualTo(UPDATED_PAYMENT_TYPE);
        assertThat(testOfferSpecification.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOfferSpecification.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOfferSpecification.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testOfferSpecification.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testOfferSpecification.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingOfferSpecification() throws Exception {
        int databaseSizeBeforeUpdate = offerSpecificationRepository.findAll().size();

        // Create the OfferSpecification

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferSpecificationMockMvc.perform(put("/api/offer-specifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerSpecification)))
            .andExpect(status().isBadRequest());

        // Validate the OfferSpecification in the database
        List<OfferSpecification> offerSpecificationList = offerSpecificationRepository.findAll();
        assertThat(offerSpecificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOfferSpecification() throws Exception {
        // Initialize the database
        offerSpecificationService.save(offerSpecification);

        int databaseSizeBeforeDelete = offerSpecificationRepository.findAll().size();

        // Delete the offerSpecification
        restOfferSpecificationMockMvc.perform(delete("/api/offer-specifications/{id}", offerSpecification.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OfferSpecification> offerSpecificationList = offerSpecificationRepository.findAll();
        assertThat(offerSpecificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
