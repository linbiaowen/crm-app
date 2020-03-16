package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.CustContact;
import com.hthk.crm.repository.CustContactRepository;
import com.hthk.crm.service.CustContactService;
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

import com.hthk.crm.domain.enumeration.AccountType;
import com.hthk.crm.domain.enumeration.ContactType;
import com.hthk.crm.domain.enumeration.RecordStatus;
/**
 * Integration tests for the {@link CustContactResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class CustContactResourceIT {

    private static final Long DEFAULT_CONTACT_ID = 1L;
    private static final Long UPDATED_CONTACT_ID = 2L;

    private static final String DEFAULT_ACCOUNT_ID = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_ID = "BBBBBBBBBB";

    private static final AccountType DEFAULT_ACCOUNT_TYPE = AccountType.CUSTOMER;
    private static final AccountType UPDATED_ACCOUNT_TYPE = AccountType.SUBSCRIPTION;

    private static final ContactType DEFAULT_CONTACT_TYPE = ContactType.HOME;
    private static final ContactType UPDATED_CONTACT_TYPE = ContactType.OFFICE;

    private static final String DEFAULT_CONTACT_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PERSON = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_EMAIL = "BBBBBBBBBB";

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
    private CustContactRepository custContactRepository;

    @Autowired
    private CustContactService custContactService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCustContactMockMvc;

    private CustContact custContact;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustContactResource custContactResource = new CustContactResource(custContactService);
        this.restCustContactMockMvc = MockMvcBuilders.standaloneSetup(custContactResource)
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
    public static CustContact createEntity() {
        CustContact custContact = new CustContact()
            .contactId(DEFAULT_CONTACT_ID)
            .accountId(DEFAULT_ACCOUNT_ID)
            .accountType(DEFAULT_ACCOUNT_TYPE)
            .contactType(DEFAULT_CONTACT_TYPE)
            .contactPerson(DEFAULT_CONTACT_PERSON)
            .contactPhone(DEFAULT_CONTACT_PHONE)
            .contactEmail(DEFAULT_CONTACT_EMAIL)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return custContact;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustContact createUpdatedEntity() {
        CustContact custContact = new CustContact()
            .contactId(UPDATED_CONTACT_ID)
            .accountId(UPDATED_ACCOUNT_ID)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .contactType(UPDATED_CONTACT_TYPE)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .contactPhone(UPDATED_CONTACT_PHONE)
            .contactEmail(UPDATED_CONTACT_EMAIL)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return custContact;
    }

    @BeforeEach
    public void initTest() {
        custContactRepository.deleteAll();
        custContact = createEntity();
    }

    @Test
    public void createCustContact() throws Exception {
        int databaseSizeBeforeCreate = custContactRepository.findAll().size();

        // Create the CustContact
        restCustContactMockMvc.perform(post("/api/cust-contacts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custContact)))
            .andExpect(status().isCreated());

        // Validate the CustContact in the database
        List<CustContact> custContactList = custContactRepository.findAll();
        assertThat(custContactList).hasSize(databaseSizeBeforeCreate + 1);
        CustContact testCustContact = custContactList.get(custContactList.size() - 1);
        assertThat(testCustContact.getContactId()).isEqualTo(DEFAULT_CONTACT_ID);
        assertThat(testCustContact.getAccountId()).isEqualTo(DEFAULT_ACCOUNT_ID);
        assertThat(testCustContact.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testCustContact.getContactType()).isEqualTo(DEFAULT_CONTACT_TYPE);
        assertThat(testCustContact.getContactPerson()).isEqualTo(DEFAULT_CONTACT_PERSON);
        assertThat(testCustContact.getContactPhone()).isEqualTo(DEFAULT_CONTACT_PHONE);
        assertThat(testCustContact.getContactEmail()).isEqualTo(DEFAULT_CONTACT_EMAIL);
        assertThat(testCustContact.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCustContact.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCustContact.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustContact.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testCustContact.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testCustContact.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createCustContactWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = custContactRepository.findAll().size();

        // Create the CustContact with an existing ID
        custContact.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustContactMockMvc.perform(post("/api/cust-contacts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custContact)))
            .andExpect(status().isBadRequest());

        // Validate the CustContact in the database
        List<CustContact> custContactList = custContactRepository.findAll();
        assertThat(custContactList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkContactIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custContactRepository.findAll().size();
        // set the field null
        custContact.setContactId(null);

        // Create the CustContact, which fails.

        restCustContactMockMvc.perform(post("/api/cust-contacts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custContact)))
            .andExpect(status().isBadRequest());

        List<CustContact> custContactList = custContactRepository.findAll();
        assertThat(custContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAccountIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custContactRepository.findAll().size();
        // set the field null
        custContact.setAccountId(null);

        // Create the CustContact, which fails.

        restCustContactMockMvc.perform(post("/api/cust-contacts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custContact)))
            .andExpect(status().isBadRequest());

        List<CustContact> custContactList = custContactRepository.findAll();
        assertThat(custContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAccountTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = custContactRepository.findAll().size();
        // set the field null
        custContact.setAccountType(null);

        // Create the CustContact, which fails.

        restCustContactMockMvc.perform(post("/api/cust-contacts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custContact)))
            .andExpect(status().isBadRequest());

        List<CustContact> custContactList = custContactRepository.findAll();
        assertThat(custContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkContactTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = custContactRepository.findAll().size();
        // set the field null
        custContact.setContactType(null);

        // Create the CustContact, which fails.

        restCustContactMockMvc.perform(post("/api/cust-contacts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custContact)))
            .andExpect(status().isBadRequest());

        List<CustContact> custContactList = custContactRepository.findAll();
        assertThat(custContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = custContactRepository.findAll().size();
        // set the field null
        custContact.setStatus(null);

        // Create the CustContact, which fails.

        restCustContactMockMvc.perform(post("/api/cust-contacts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custContact)))
            .andExpect(status().isBadRequest());

        List<CustContact> custContactList = custContactRepository.findAll();
        assertThat(custContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = custContactRepository.findAll().size();
        // set the field null
        custContact.setCreatedDate(null);

        // Create the CustContact, which fails.

        restCustContactMockMvc.perform(post("/api/cust-contacts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custContact)))
            .andExpect(status().isBadRequest());

        List<CustContact> custContactList = custContactRepository.findAll();
        assertThat(custContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = custContactRepository.findAll().size();
        // set the field null
        custContact.setCreatedBy(null);

        // Create the CustContact, which fails.

        restCustContactMockMvc.perform(post("/api/cust-contacts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custContact)))
            .andExpect(status().isBadRequest());

        List<CustContact> custContactList = custContactRepository.findAll();
        assertThat(custContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = custContactRepository.findAll().size();
        // set the field null
        custContact.setLastUpdatedDate(null);

        // Create the CustContact, which fails.

        restCustContactMockMvc.perform(post("/api/cust-contacts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custContact)))
            .andExpect(status().isBadRequest());

        List<CustContact> custContactList = custContactRepository.findAll();
        assertThat(custContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = custContactRepository.findAll().size();
        // set the field null
        custContact.setLastUpdatedBy(null);

        // Create the CustContact, which fails.

        restCustContactMockMvc.perform(post("/api/cust-contacts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custContact)))
            .andExpect(status().isBadRequest());

        List<CustContact> custContactList = custContactRepository.findAll();
        assertThat(custContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = custContactRepository.findAll().size();
        // set the field null
        custContact.setTenantId(null);

        // Create the CustContact, which fails.

        restCustContactMockMvc.perform(post("/api/cust-contacts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custContact)))
            .andExpect(status().isBadRequest());

        List<CustContact> custContactList = custContactRepository.findAll();
        assertThat(custContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCustContacts() throws Exception {
        // Initialize the database
        custContactRepository.save(custContact);

        // Get all the custContactList
        restCustContactMockMvc.perform(get("/api/cust-contacts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(custContact.getId())))
            .andExpect(jsonPath("$.[*].contactId").value(hasItem(DEFAULT_CONTACT_ID.intValue())))
            .andExpect(jsonPath("$.[*].accountId").value(hasItem(DEFAULT_ACCOUNT_ID)))
            .andExpect(jsonPath("$.[*].accountType").value(hasItem(DEFAULT_ACCOUNT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].contactType").value(hasItem(DEFAULT_CONTACT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].contactPerson").value(hasItem(DEFAULT_CONTACT_PERSON)))
            .andExpect(jsonPath("$.[*].contactPhone").value(hasItem(DEFAULT_CONTACT_PHONE)))
            .andExpect(jsonPath("$.[*].contactEmail").value(hasItem(DEFAULT_CONTACT_EMAIL)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getCustContact() throws Exception {
        // Initialize the database
        custContactRepository.save(custContact);

        // Get the custContact
        restCustContactMockMvc.perform(get("/api/cust-contacts/{id}", custContact.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(custContact.getId()))
            .andExpect(jsonPath("$.contactId").value(DEFAULT_CONTACT_ID.intValue()))
            .andExpect(jsonPath("$.accountId").value(DEFAULT_ACCOUNT_ID))
            .andExpect(jsonPath("$.accountType").value(DEFAULT_ACCOUNT_TYPE.toString()))
            .andExpect(jsonPath("$.contactType").value(DEFAULT_CONTACT_TYPE.toString()))
            .andExpect(jsonPath("$.contactPerson").value(DEFAULT_CONTACT_PERSON))
            .andExpect(jsonPath("$.contactPhone").value(DEFAULT_CONTACT_PHONE))
            .andExpect(jsonPath("$.contactEmail").value(DEFAULT_CONTACT_EMAIL))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingCustContact() throws Exception {
        // Get the custContact
        restCustContactMockMvc.perform(get("/api/cust-contacts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCustContact() throws Exception {
        // Initialize the database
        custContactService.save(custContact);

        int databaseSizeBeforeUpdate = custContactRepository.findAll().size();

        // Update the custContact
        CustContact updatedCustContact = custContactRepository.findById(custContact.getId()).get();
        updatedCustContact
            .contactId(UPDATED_CONTACT_ID)
            .accountId(UPDATED_ACCOUNT_ID)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .contactType(UPDATED_CONTACT_TYPE)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .contactPhone(UPDATED_CONTACT_PHONE)
            .contactEmail(UPDATED_CONTACT_EMAIL)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restCustContactMockMvc.perform(put("/api/cust-contacts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCustContact)))
            .andExpect(status().isOk());

        // Validate the CustContact in the database
        List<CustContact> custContactList = custContactRepository.findAll();
        assertThat(custContactList).hasSize(databaseSizeBeforeUpdate);
        CustContact testCustContact = custContactList.get(custContactList.size() - 1);
        assertThat(testCustContact.getContactId()).isEqualTo(UPDATED_CONTACT_ID);
        assertThat(testCustContact.getAccountId()).isEqualTo(UPDATED_ACCOUNT_ID);
        assertThat(testCustContact.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testCustContact.getContactType()).isEqualTo(UPDATED_CONTACT_TYPE);
        assertThat(testCustContact.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testCustContact.getContactPhone()).isEqualTo(UPDATED_CONTACT_PHONE);
        assertThat(testCustContact.getContactEmail()).isEqualTo(UPDATED_CONTACT_EMAIL);
        assertThat(testCustContact.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCustContact.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCustContact.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustContact.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testCustContact.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testCustContact.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingCustContact() throws Exception {
        int databaseSizeBeforeUpdate = custContactRepository.findAll().size();

        // Create the CustContact

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustContactMockMvc.perform(put("/api/cust-contacts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(custContact)))
            .andExpect(status().isBadRequest());

        // Validate the CustContact in the database
        List<CustContact> custContactList = custContactRepository.findAll();
        assertThat(custContactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCustContact() throws Exception {
        // Initialize the database
        custContactService.save(custContact);

        int databaseSizeBeforeDelete = custContactRepository.findAll().size();

        // Delete the custContact
        restCustContactMockMvc.perform(delete("/api/cust-contacts/{id}", custContact.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustContact> custContactList = custContactRepository.findAll();
        assertThat(custContactList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
