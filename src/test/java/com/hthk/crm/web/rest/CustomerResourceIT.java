package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.Customer;
import com.hthk.crm.repository.CustomerRepository;
import com.hthk.crm.service.CustomerService;
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


import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.hthk.crm.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.hthk.crm.domain.enumeration.AcctStatus;
import com.hthk.crm.domain.enumeration.Language;
import com.hthk.crm.domain.enumeration.CustomerSegment;
/**
 * Integration tests for the {@link CustomerResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class CustomerResourceIT {

    private static final String DEFAULT_CUST_ACCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUST_ACCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_CUST_ACCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_CUST_ACCT_ID = "BBBBBBBBBB";

    private static final AcctStatus DEFAULT_ACCT_STATUS = AcctStatus.PREACTIVE;
    private static final AcctStatus UPDATED_ACCT_STATUS = AcctStatus.ACTIVE;

    private static final Instant DEFAULT_ACCT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ACCT_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ACCT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ACCT_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CABS_ACCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CABS_ACCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_GIVEN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GIVEN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FAMILY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GIVEN_NAME_CHI = "AAAAAAAAAA";
    private static final String UPDATED_GIVEN_NAME_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILY_NAME_CHI = "AAAAAAAAAA";
    private static final String UPDATED_FAMILY_NAME_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_ALIAS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ALIAS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ID_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ID_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME_ENG = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME_ENG = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME_CHI = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME_CHI = "BBBBBBBBBB";

    private static final Boolean DEFAULT_UNLIMITED_COMPANY = false;
    private static final Boolean UPDATED_UNLIMITED_COMPANY = true;

    private static final Language DEFAULT_LANG = Language.CHINESE;
    private static final Language UPDATED_LANG = Language.ENGLISH;

    private static final String DEFAULT_SELF_CARE_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_SELF_CARE_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SELF_CARE_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_SELF_CARE_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_IVR_PIN = "AAAAAAAAAA";
    private static final String UPDATED_IVR_PIN = "BBBBBBBBBB";

    private static final String DEFAULT_MARITIAL_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_MARITIAL_STATUS = "BBBBBBBBBB";

    private static final CustomerSegment DEFAULT_CUSTOMER_SEGMENT = CustomerSegment.MASS;
    private static final CustomerSegment UPDATED_CUSTOMER_SEGMENT = CustomerSegment.CORP;

    private static final String DEFAULT_CUSTOMER_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_CLASS = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ACCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ACCT_ID = "BBBBBBBBBB";

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
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCustomerMockMvc;

    private Customer customer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomerResource customerResource = new CustomerResource(customerService);
        this.restCustomerMockMvc = MockMvcBuilders.standaloneSetup(customerResource)
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
    public static Customer createEntity() {
        Customer customer = new Customer()
            .custAcctId(DEFAULT_CUST_ACCT_ID)
            .parentCustAcctId(DEFAULT_PARENT_CUST_ACCT_ID)
            .acctStatus(DEFAULT_ACCT_STATUS)
            .acctStartDate(DEFAULT_ACCT_START_DATE)
            .acctEndDate(DEFAULT_ACCT_END_DATE)
            .cabsAcctId(DEFAULT_CABS_ACCT_ID)
            .title(DEFAULT_TITLE)
            .givenName(DEFAULT_GIVEN_NAME)
            .familyName(DEFAULT_FAMILY_NAME)
            .givenNameChi(DEFAULT_GIVEN_NAME_CHI)
            .familyNameChi(DEFAULT_FAMILY_NAME_CHI)
            .aliasName(DEFAULT_ALIAS_NAME)
            .gender(DEFAULT_GENDER)
            .birthDate(DEFAULT_BIRTH_DATE)
            .idType(DEFAULT_ID_TYPE)
            .idNumber(DEFAULT_ID_NUMBER)
            .companyNameEng(DEFAULT_COMPANY_NAME_ENG)
            .companyNameChi(DEFAULT_COMPANY_NAME_CHI)
            .unlimitedCompany(DEFAULT_UNLIMITED_COMPANY)
            .lang(DEFAULT_LANG)
            .selfCareUserId(DEFAULT_SELF_CARE_USER_ID)
            .selfCarePassword(DEFAULT_SELF_CARE_PASSWORD)
            .ivrPin(DEFAULT_IVR_PIN)
            .maritialStatus(DEFAULT_MARITIAL_STATUS)
            .customerSegment(DEFAULT_CUSTOMER_SEGMENT)
            .customerClass(DEFAULT_CUSTOMER_CLASS)
            .billingAcctId(DEFAULT_BILLING_ACCT_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return customer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createUpdatedEntity() {
        Customer customer = new Customer()
            .custAcctId(UPDATED_CUST_ACCT_ID)
            .parentCustAcctId(UPDATED_PARENT_CUST_ACCT_ID)
            .acctStatus(UPDATED_ACCT_STATUS)
            .acctStartDate(UPDATED_ACCT_START_DATE)
            .acctEndDate(UPDATED_ACCT_END_DATE)
            .cabsAcctId(UPDATED_CABS_ACCT_ID)
            .title(UPDATED_TITLE)
            .givenName(UPDATED_GIVEN_NAME)
            .familyName(UPDATED_FAMILY_NAME)
            .givenNameChi(UPDATED_GIVEN_NAME_CHI)
            .familyNameChi(UPDATED_FAMILY_NAME_CHI)
            .aliasName(UPDATED_ALIAS_NAME)
            .gender(UPDATED_GENDER)
            .birthDate(UPDATED_BIRTH_DATE)
            .idType(UPDATED_ID_TYPE)
            .idNumber(UPDATED_ID_NUMBER)
            .companyNameEng(UPDATED_COMPANY_NAME_ENG)
            .companyNameChi(UPDATED_COMPANY_NAME_CHI)
            .unlimitedCompany(UPDATED_UNLIMITED_COMPANY)
            .lang(UPDATED_LANG)
            .selfCareUserId(UPDATED_SELF_CARE_USER_ID)
            .selfCarePassword(UPDATED_SELF_CARE_PASSWORD)
            .ivrPin(UPDATED_IVR_PIN)
            .maritialStatus(UPDATED_MARITIAL_STATUS)
            .customerSegment(UPDATED_CUSTOMER_SEGMENT)
            .customerClass(UPDATED_CUSTOMER_CLASS)
            .billingAcctId(UPDATED_BILLING_ACCT_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return customer;
    }

    @BeforeEach
    public void initTest() {
        customerRepository.deleteAll();
        customer = createEntity();
    }

    @Test
    public void createCustomer() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().size();

        // Create the Customer
        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isCreated());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate + 1);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getCustAcctId()).isEqualTo(DEFAULT_CUST_ACCT_ID);
        assertThat(testCustomer.getParentCustAcctId()).isEqualTo(DEFAULT_PARENT_CUST_ACCT_ID);
        assertThat(testCustomer.getAcctStatus()).isEqualTo(DEFAULT_ACCT_STATUS);
        assertThat(testCustomer.getAcctStartDate()).isEqualTo(DEFAULT_ACCT_START_DATE);
        assertThat(testCustomer.getAcctEndDate()).isEqualTo(DEFAULT_ACCT_END_DATE);
        assertThat(testCustomer.getCabsAcctId()).isEqualTo(DEFAULT_CABS_ACCT_ID);
        assertThat(testCustomer.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testCustomer.getGivenName()).isEqualTo(DEFAULT_GIVEN_NAME);
        assertThat(testCustomer.getFamilyName()).isEqualTo(DEFAULT_FAMILY_NAME);
        assertThat(testCustomer.getGivenNameChi()).isEqualTo(DEFAULT_GIVEN_NAME_CHI);
        assertThat(testCustomer.getFamilyNameChi()).isEqualTo(DEFAULT_FAMILY_NAME_CHI);
        assertThat(testCustomer.getAliasName()).isEqualTo(DEFAULT_ALIAS_NAME);
        assertThat(testCustomer.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testCustomer.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
        assertThat(testCustomer.getIdType()).isEqualTo(DEFAULT_ID_TYPE);
        assertThat(testCustomer.getIdNumber()).isEqualTo(DEFAULT_ID_NUMBER);
        assertThat(testCustomer.getCompanyNameEng()).isEqualTo(DEFAULT_COMPANY_NAME_ENG);
        assertThat(testCustomer.getCompanyNameChi()).isEqualTo(DEFAULT_COMPANY_NAME_CHI);
        assertThat(testCustomer.isUnlimitedCompany()).isEqualTo(DEFAULT_UNLIMITED_COMPANY);
        assertThat(testCustomer.getLang()).isEqualTo(DEFAULT_LANG);
        assertThat(testCustomer.getSelfCareUserId()).isEqualTo(DEFAULT_SELF_CARE_USER_ID);
        assertThat(testCustomer.getSelfCarePassword()).isEqualTo(DEFAULT_SELF_CARE_PASSWORD);
        assertThat(testCustomer.getIvrPin()).isEqualTo(DEFAULT_IVR_PIN);
        assertThat(testCustomer.getMaritialStatus()).isEqualTo(DEFAULT_MARITIAL_STATUS);
        assertThat(testCustomer.getCustomerSegment()).isEqualTo(DEFAULT_CUSTOMER_SEGMENT);
        assertThat(testCustomer.getCustomerClass()).isEqualTo(DEFAULT_CUSTOMER_CLASS);
        assertThat(testCustomer.getBillingAcctId()).isEqualTo(DEFAULT_BILLING_ACCT_ID);
        assertThat(testCustomer.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCustomer.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustomer.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testCustomer.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testCustomer.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createCustomerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().size();

        // Create the Customer with an existing ID
        customer.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkCustAcctIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().size();
        // set the field null
        customer.setCustAcctId(null);

        // Create the Customer, which fails.

        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAcctStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().size();
        // set the field null
        customer.setAcctStatus(null);

        // Create the Customer, which fails.

        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAcctStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().size();
        // set the field null
        customer.setAcctStartDate(null);

        // Create the Customer, which fails.

        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLangIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().size();
        // set the field null
        customer.setLang(null);

        // Create the Customer, which fails.

        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().size();
        // set the field null
        customer.setCreatedDate(null);

        // Create the Customer, which fails.

        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().size();
        // set the field null
        customer.setCreatedBy(null);

        // Create the Customer, which fails.

        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().size();
        // set the field null
        customer.setLastUpdatedDate(null);

        // Create the Customer, which fails.

        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().size();
        // set the field null
        customer.setLastUpdatedBy(null);

        // Create the Customer, which fails.

        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().size();
        // set the field null
        customer.setTenantId(null);

        // Create the Customer, which fails.

        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCustomers() throws Exception {
        // Initialize the database
        customerRepository.save(customer);

        // Get all the customerList
        restCustomerMockMvc.perform(get("/api/customers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId())))
            .andExpect(jsonPath("$.[*].custAcctId").value(hasItem(DEFAULT_CUST_ACCT_ID)))
            .andExpect(jsonPath("$.[*].parentCustAcctId").value(hasItem(DEFAULT_PARENT_CUST_ACCT_ID)))
            .andExpect(jsonPath("$.[*].acctStatus").value(hasItem(DEFAULT_ACCT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].acctStartDate").value(hasItem(DEFAULT_ACCT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].acctEndDate").value(hasItem(DEFAULT_ACCT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].cabsAcctId").value(hasItem(DEFAULT_CABS_ACCT_ID)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].givenName").value(hasItem(DEFAULT_GIVEN_NAME)))
            .andExpect(jsonPath("$.[*].familyName").value(hasItem(DEFAULT_FAMILY_NAME)))
            .andExpect(jsonPath("$.[*].givenNameChi").value(hasItem(DEFAULT_GIVEN_NAME_CHI)))
            .andExpect(jsonPath("$.[*].familyNameChi").value(hasItem(DEFAULT_FAMILY_NAME_CHI)))
            .andExpect(jsonPath("$.[*].aliasName").value(hasItem(DEFAULT_ALIAS_NAME)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(DEFAULT_BIRTH_DATE.toString())))
            .andExpect(jsonPath("$.[*].idType").value(hasItem(DEFAULT_ID_TYPE)))
            .andExpect(jsonPath("$.[*].idNumber").value(hasItem(DEFAULT_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].companyNameEng").value(hasItem(DEFAULT_COMPANY_NAME_ENG)))
            .andExpect(jsonPath("$.[*].companyNameChi").value(hasItem(DEFAULT_COMPANY_NAME_CHI)))
            .andExpect(jsonPath("$.[*].unlimitedCompany").value(hasItem(DEFAULT_UNLIMITED_COMPANY.booleanValue())))
            .andExpect(jsonPath("$.[*].lang").value(hasItem(DEFAULT_LANG.toString())))
            .andExpect(jsonPath("$.[*].selfCareUserId").value(hasItem(DEFAULT_SELF_CARE_USER_ID)))
            .andExpect(jsonPath("$.[*].selfCarePassword").value(hasItem(DEFAULT_SELF_CARE_PASSWORD)))
            .andExpect(jsonPath("$.[*].ivrPin").value(hasItem(DEFAULT_IVR_PIN)))
            .andExpect(jsonPath("$.[*].maritialStatus").value(hasItem(DEFAULT_MARITIAL_STATUS)))
            .andExpect(jsonPath("$.[*].customerSegment").value(hasItem(DEFAULT_CUSTOMER_SEGMENT.toString())))
            .andExpect(jsonPath("$.[*].customerClass").value(hasItem(DEFAULT_CUSTOMER_CLASS)))
            .andExpect(jsonPath("$.[*].billingAcctId").value(hasItem(DEFAULT_BILLING_ACCT_ID)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getCustomer() throws Exception {
        // Initialize the database
        customerRepository.save(customer);

        // Get the customer
        restCustomerMockMvc.perform(get("/api/customers/{id}", customer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customer.getId()))
            .andExpect(jsonPath("$.custAcctId").value(DEFAULT_CUST_ACCT_ID))
            .andExpect(jsonPath("$.parentCustAcctId").value(DEFAULT_PARENT_CUST_ACCT_ID))
            .andExpect(jsonPath("$.acctStatus").value(DEFAULT_ACCT_STATUS.toString()))
            .andExpect(jsonPath("$.acctStartDate").value(DEFAULT_ACCT_START_DATE.toString()))
            .andExpect(jsonPath("$.acctEndDate").value(DEFAULT_ACCT_END_DATE.toString()))
            .andExpect(jsonPath("$.cabsAcctId").value(DEFAULT_CABS_ACCT_ID))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.givenName").value(DEFAULT_GIVEN_NAME))
            .andExpect(jsonPath("$.familyName").value(DEFAULT_FAMILY_NAME))
            .andExpect(jsonPath("$.givenNameChi").value(DEFAULT_GIVEN_NAME_CHI))
            .andExpect(jsonPath("$.familyNameChi").value(DEFAULT_FAMILY_NAME_CHI))
            .andExpect(jsonPath("$.aliasName").value(DEFAULT_ALIAS_NAME))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER))
            .andExpect(jsonPath("$.birthDate").value(DEFAULT_BIRTH_DATE.toString()))
            .andExpect(jsonPath("$.idType").value(DEFAULT_ID_TYPE))
            .andExpect(jsonPath("$.idNumber").value(DEFAULT_ID_NUMBER))
            .andExpect(jsonPath("$.companyNameEng").value(DEFAULT_COMPANY_NAME_ENG))
            .andExpect(jsonPath("$.companyNameChi").value(DEFAULT_COMPANY_NAME_CHI))
            .andExpect(jsonPath("$.unlimitedCompany").value(DEFAULT_UNLIMITED_COMPANY.booleanValue()))
            .andExpect(jsonPath("$.lang").value(DEFAULT_LANG.toString()))
            .andExpect(jsonPath("$.selfCareUserId").value(DEFAULT_SELF_CARE_USER_ID))
            .andExpect(jsonPath("$.selfCarePassword").value(DEFAULT_SELF_CARE_PASSWORD))
            .andExpect(jsonPath("$.ivrPin").value(DEFAULT_IVR_PIN))
            .andExpect(jsonPath("$.maritialStatus").value(DEFAULT_MARITIAL_STATUS))
            .andExpect(jsonPath("$.customerSegment").value(DEFAULT_CUSTOMER_SEGMENT.toString()))
            .andExpect(jsonPath("$.customerClass").value(DEFAULT_CUSTOMER_CLASS))
            .andExpect(jsonPath("$.billingAcctId").value(DEFAULT_BILLING_ACCT_ID))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingCustomer() throws Exception {
        // Get the customer
        restCustomerMockMvc.perform(get("/api/customers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCustomer() throws Exception {
        // Initialize the database
        customerService.save(customer);

        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Update the customer
        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        updatedCustomer
            .custAcctId(UPDATED_CUST_ACCT_ID)
            .parentCustAcctId(UPDATED_PARENT_CUST_ACCT_ID)
            .acctStatus(UPDATED_ACCT_STATUS)
            .acctStartDate(UPDATED_ACCT_START_DATE)
            .acctEndDate(UPDATED_ACCT_END_DATE)
            .cabsAcctId(UPDATED_CABS_ACCT_ID)
            .title(UPDATED_TITLE)
            .givenName(UPDATED_GIVEN_NAME)
            .familyName(UPDATED_FAMILY_NAME)
            .givenNameChi(UPDATED_GIVEN_NAME_CHI)
            .familyNameChi(UPDATED_FAMILY_NAME_CHI)
            .aliasName(UPDATED_ALIAS_NAME)
            .gender(UPDATED_GENDER)
            .birthDate(UPDATED_BIRTH_DATE)
            .idType(UPDATED_ID_TYPE)
            .idNumber(UPDATED_ID_NUMBER)
            .companyNameEng(UPDATED_COMPANY_NAME_ENG)
            .companyNameChi(UPDATED_COMPANY_NAME_CHI)
            .unlimitedCompany(UPDATED_UNLIMITED_COMPANY)
            .lang(UPDATED_LANG)
            .selfCareUserId(UPDATED_SELF_CARE_USER_ID)
            .selfCarePassword(UPDATED_SELF_CARE_PASSWORD)
            .ivrPin(UPDATED_IVR_PIN)
            .maritialStatus(UPDATED_MARITIAL_STATUS)
            .customerSegment(UPDATED_CUSTOMER_SEGMENT)
            .customerClass(UPDATED_CUSTOMER_CLASS)
            .billingAcctId(UPDATED_BILLING_ACCT_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restCustomerMockMvc.perform(put("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCustomer)))
            .andExpect(status().isOk());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getCustAcctId()).isEqualTo(UPDATED_CUST_ACCT_ID);
        assertThat(testCustomer.getParentCustAcctId()).isEqualTo(UPDATED_PARENT_CUST_ACCT_ID);
        assertThat(testCustomer.getAcctStatus()).isEqualTo(UPDATED_ACCT_STATUS);
        assertThat(testCustomer.getAcctStartDate()).isEqualTo(UPDATED_ACCT_START_DATE);
        assertThat(testCustomer.getAcctEndDate()).isEqualTo(UPDATED_ACCT_END_DATE);
        assertThat(testCustomer.getCabsAcctId()).isEqualTo(UPDATED_CABS_ACCT_ID);
        assertThat(testCustomer.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testCustomer.getGivenName()).isEqualTo(UPDATED_GIVEN_NAME);
        assertThat(testCustomer.getFamilyName()).isEqualTo(UPDATED_FAMILY_NAME);
        assertThat(testCustomer.getGivenNameChi()).isEqualTo(UPDATED_GIVEN_NAME_CHI);
        assertThat(testCustomer.getFamilyNameChi()).isEqualTo(UPDATED_FAMILY_NAME_CHI);
        assertThat(testCustomer.getAliasName()).isEqualTo(UPDATED_ALIAS_NAME);
        assertThat(testCustomer.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testCustomer.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
        assertThat(testCustomer.getIdType()).isEqualTo(UPDATED_ID_TYPE);
        assertThat(testCustomer.getIdNumber()).isEqualTo(UPDATED_ID_NUMBER);
        assertThat(testCustomer.getCompanyNameEng()).isEqualTo(UPDATED_COMPANY_NAME_ENG);
        assertThat(testCustomer.getCompanyNameChi()).isEqualTo(UPDATED_COMPANY_NAME_CHI);
        assertThat(testCustomer.isUnlimitedCompany()).isEqualTo(UPDATED_UNLIMITED_COMPANY);
        assertThat(testCustomer.getLang()).isEqualTo(UPDATED_LANG);
        assertThat(testCustomer.getSelfCareUserId()).isEqualTo(UPDATED_SELF_CARE_USER_ID);
        assertThat(testCustomer.getSelfCarePassword()).isEqualTo(UPDATED_SELF_CARE_PASSWORD);
        assertThat(testCustomer.getIvrPin()).isEqualTo(UPDATED_IVR_PIN);
        assertThat(testCustomer.getMaritialStatus()).isEqualTo(UPDATED_MARITIAL_STATUS);
        assertThat(testCustomer.getCustomerSegment()).isEqualTo(UPDATED_CUSTOMER_SEGMENT);
        assertThat(testCustomer.getCustomerClass()).isEqualTo(UPDATED_CUSTOMER_CLASS);
        assertThat(testCustomer.getBillingAcctId()).isEqualTo(UPDATED_BILLING_ACCT_ID);
        assertThat(testCustomer.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCustomer.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustomer.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testCustomer.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testCustomer.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Create the Customer

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerMockMvc.perform(put("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCustomer() throws Exception {
        // Initialize the database
        customerService.save(customer);

        int databaseSizeBeforeDelete = customerRepository.findAll().size();

        // Delete the customer
        restCustomerMockMvc.perform(delete("/api/customers/{id}", customer.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
