package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.VoiceServiceSpec;
import com.hthk.crm.repository.VoiceServiceSpecRepository;
import com.hthk.crm.service.VoiceServiceSpecService;
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
 * Integration tests for the {@link VoiceServiceSpecResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class VoiceServiceSpecResourceIT {

    private static final Long DEFAULT_VOICE_SPEC_ID = 1L;
    private static final Long UPDATED_VOICE_SPEC_ID = 2L;

    private static final String DEFAULT_SERVICE_SPEC_ID = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_SPEC_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ROAMING_INCOMING_ENABLED = false;
    private static final Boolean UPDATED_ROAMING_INCOMING_ENABLED = true;

    private static final Boolean DEFAULT_ROAMING_OUTGOING_ENABLED = false;
    private static final Boolean UPDATED_ROAMING_OUTGOING_ENABLED = true;

    private static final Boolean DEFAULT_IDD_ENABLED = false;
    private static final Boolean UPDATED_IDD_ENABLED = true;

    private static final String DEFAULT_IDD_OPTIONS = "AAAAAAAAAA";
    private static final String UPDATED_IDD_OPTIONS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CALL_FORWARD_ENABLED = false;
    private static final Boolean UPDATED_CALL_FORWARD_ENABLED = true;

    private static final Boolean DEFAULT_CALL_WAITING_ENABLED = false;
    private static final Boolean UPDATED_CALL_WAITING_ENABLED = true;

    private static final Boolean DEFAULT_CLIP_ENABLED = false;
    private static final Boolean UPDATED_CLIP_ENABLED = true;

    private static final Boolean DEFAULT_CALL_BARRING_ENABLED = false;
    private static final Boolean UPDATED_CALL_BARRING_ENABLED = true;

    private static final Boolean DEFAULT_MVRS_ENABLED = false;
    private static final Boolean UPDATED_MVRS_ENABLED = true;

    private static final String DEFAULT_SPECIAL_CALL_SERVICES = "AAAAAAAAAA";
    private static final String UPDATED_SPECIAL_CALL_SERVICES = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CALL_ROUTING_SUPPORTED = false;
    private static final Boolean UPDATED_CALL_ROUTING_SUPPORTED = true;

    private static final Boolean DEFAULT_PRBT_SUPPORTED = false;
    private static final Boolean UPDATED_PRBT_SUPPORTED = true;

    private static final Boolean DEFAULT_HRBT_SUPPORTED = false;
    private static final Boolean UPDATED_HRBT_SUPPORTED = true;

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
    private VoiceServiceSpecRepository voiceServiceSpecRepository;

    @Autowired
    private VoiceServiceSpecService voiceServiceSpecService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restVoiceServiceSpecMockMvc;

    private VoiceServiceSpec voiceServiceSpec;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VoiceServiceSpecResource voiceServiceSpecResource = new VoiceServiceSpecResource(voiceServiceSpecService);
        this.restVoiceServiceSpecMockMvc = MockMvcBuilders.standaloneSetup(voiceServiceSpecResource)
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
    public static VoiceServiceSpec createEntity() {
        VoiceServiceSpec voiceServiceSpec = new VoiceServiceSpec()
            .voiceSpecId(DEFAULT_VOICE_SPEC_ID)
            .serviceSpecId(DEFAULT_SERVICE_SPEC_ID)
            .roamingIncomingEnabled(DEFAULT_ROAMING_INCOMING_ENABLED)
            .roamingOutgoingEnabled(DEFAULT_ROAMING_OUTGOING_ENABLED)
            .iddEnabled(DEFAULT_IDD_ENABLED)
            .iddOptions(DEFAULT_IDD_OPTIONS)
            .callForwardEnabled(DEFAULT_CALL_FORWARD_ENABLED)
            .callWaitingEnabled(DEFAULT_CALL_WAITING_ENABLED)
            .clipEnabled(DEFAULT_CLIP_ENABLED)
            .callBarringEnabled(DEFAULT_CALL_BARRING_ENABLED)
            .mvrsEnabled(DEFAULT_MVRS_ENABLED)
            .specialCallServices(DEFAULT_SPECIAL_CALL_SERVICES)
            .callRoutingSupported(DEFAULT_CALL_ROUTING_SUPPORTED)
            .prbtSupported(DEFAULT_PRBT_SUPPORTED)
            .hrbtSupported(DEFAULT_HRBT_SUPPORTED)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return voiceServiceSpec;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VoiceServiceSpec createUpdatedEntity() {
        VoiceServiceSpec voiceServiceSpec = new VoiceServiceSpec()
            .voiceSpecId(UPDATED_VOICE_SPEC_ID)
            .serviceSpecId(UPDATED_SERVICE_SPEC_ID)
            .roamingIncomingEnabled(UPDATED_ROAMING_INCOMING_ENABLED)
            .roamingOutgoingEnabled(UPDATED_ROAMING_OUTGOING_ENABLED)
            .iddEnabled(UPDATED_IDD_ENABLED)
            .iddOptions(UPDATED_IDD_OPTIONS)
            .callForwardEnabled(UPDATED_CALL_FORWARD_ENABLED)
            .callWaitingEnabled(UPDATED_CALL_WAITING_ENABLED)
            .clipEnabled(UPDATED_CLIP_ENABLED)
            .callBarringEnabled(UPDATED_CALL_BARRING_ENABLED)
            .mvrsEnabled(UPDATED_MVRS_ENABLED)
            .specialCallServices(UPDATED_SPECIAL_CALL_SERVICES)
            .callRoutingSupported(UPDATED_CALL_ROUTING_SUPPORTED)
            .prbtSupported(UPDATED_PRBT_SUPPORTED)
            .hrbtSupported(UPDATED_HRBT_SUPPORTED)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return voiceServiceSpec;
    }

    @BeforeEach
    public void initTest() {
        voiceServiceSpecRepository.deleteAll();
        voiceServiceSpec = createEntity();
    }

    @Test
    public void createVoiceServiceSpec() throws Exception {
        int databaseSizeBeforeCreate = voiceServiceSpecRepository.findAll().size();

        // Create the VoiceServiceSpec
        restVoiceServiceSpecMockMvc.perform(post("/api/voice-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voiceServiceSpec)))
            .andExpect(status().isCreated());

        // Validate the VoiceServiceSpec in the database
        List<VoiceServiceSpec> voiceServiceSpecList = voiceServiceSpecRepository.findAll();
        assertThat(voiceServiceSpecList).hasSize(databaseSizeBeforeCreate + 1);
        VoiceServiceSpec testVoiceServiceSpec = voiceServiceSpecList.get(voiceServiceSpecList.size() - 1);
        assertThat(testVoiceServiceSpec.getVoiceSpecId()).isEqualTo(DEFAULT_VOICE_SPEC_ID);
        assertThat(testVoiceServiceSpec.getServiceSpecId()).isEqualTo(DEFAULT_SERVICE_SPEC_ID);
        assertThat(testVoiceServiceSpec.isRoamingIncomingEnabled()).isEqualTo(DEFAULT_ROAMING_INCOMING_ENABLED);
        assertThat(testVoiceServiceSpec.isRoamingOutgoingEnabled()).isEqualTo(DEFAULT_ROAMING_OUTGOING_ENABLED);
        assertThat(testVoiceServiceSpec.isIddEnabled()).isEqualTo(DEFAULT_IDD_ENABLED);
        assertThat(testVoiceServiceSpec.getIddOptions()).isEqualTo(DEFAULT_IDD_OPTIONS);
        assertThat(testVoiceServiceSpec.isCallForwardEnabled()).isEqualTo(DEFAULT_CALL_FORWARD_ENABLED);
        assertThat(testVoiceServiceSpec.isCallWaitingEnabled()).isEqualTo(DEFAULT_CALL_WAITING_ENABLED);
        assertThat(testVoiceServiceSpec.isClipEnabled()).isEqualTo(DEFAULT_CLIP_ENABLED);
        assertThat(testVoiceServiceSpec.isCallBarringEnabled()).isEqualTo(DEFAULT_CALL_BARRING_ENABLED);
        assertThat(testVoiceServiceSpec.isMvrsEnabled()).isEqualTo(DEFAULT_MVRS_ENABLED);
        assertThat(testVoiceServiceSpec.getSpecialCallServices()).isEqualTo(DEFAULT_SPECIAL_CALL_SERVICES);
        assertThat(testVoiceServiceSpec.isCallRoutingSupported()).isEqualTo(DEFAULT_CALL_ROUTING_SUPPORTED);
        assertThat(testVoiceServiceSpec.isPrbtSupported()).isEqualTo(DEFAULT_PRBT_SUPPORTED);
        assertThat(testVoiceServiceSpec.isHrbtSupported()).isEqualTo(DEFAULT_HRBT_SUPPORTED);
        assertThat(testVoiceServiceSpec.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testVoiceServiceSpec.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testVoiceServiceSpec.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testVoiceServiceSpec.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testVoiceServiceSpec.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createVoiceServiceSpecWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = voiceServiceSpecRepository.findAll().size();

        // Create the VoiceServiceSpec with an existing ID
        voiceServiceSpec.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restVoiceServiceSpecMockMvc.perform(post("/api/voice-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voiceServiceSpec)))
            .andExpect(status().isBadRequest());

        // Validate the VoiceServiceSpec in the database
        List<VoiceServiceSpec> voiceServiceSpecList = voiceServiceSpecRepository.findAll();
        assertThat(voiceServiceSpecList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkVoiceSpecIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = voiceServiceSpecRepository.findAll().size();
        // set the field null
        voiceServiceSpec.setVoiceSpecId(null);

        // Create the VoiceServiceSpec, which fails.

        restVoiceServiceSpecMockMvc.perform(post("/api/voice-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voiceServiceSpec)))
            .andExpect(status().isBadRequest());

        List<VoiceServiceSpec> voiceServiceSpecList = voiceServiceSpecRepository.findAll();
        assertThat(voiceServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkServiceSpecIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = voiceServiceSpecRepository.findAll().size();
        // set the field null
        voiceServiceSpec.setServiceSpecId(null);

        // Create the VoiceServiceSpec, which fails.

        restVoiceServiceSpecMockMvc.perform(post("/api/voice-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voiceServiceSpec)))
            .andExpect(status().isBadRequest());

        List<VoiceServiceSpec> voiceServiceSpecList = voiceServiceSpecRepository.findAll();
        assertThat(voiceServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = voiceServiceSpecRepository.findAll().size();
        // set the field null
        voiceServiceSpec.setCreatedDate(null);

        // Create the VoiceServiceSpec, which fails.

        restVoiceServiceSpecMockMvc.perform(post("/api/voice-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voiceServiceSpec)))
            .andExpect(status().isBadRequest());

        List<VoiceServiceSpec> voiceServiceSpecList = voiceServiceSpecRepository.findAll();
        assertThat(voiceServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = voiceServiceSpecRepository.findAll().size();
        // set the field null
        voiceServiceSpec.setCreatedBy(null);

        // Create the VoiceServiceSpec, which fails.

        restVoiceServiceSpecMockMvc.perform(post("/api/voice-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voiceServiceSpec)))
            .andExpect(status().isBadRequest());

        List<VoiceServiceSpec> voiceServiceSpecList = voiceServiceSpecRepository.findAll();
        assertThat(voiceServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = voiceServiceSpecRepository.findAll().size();
        // set the field null
        voiceServiceSpec.setLastUpdatedDate(null);

        // Create the VoiceServiceSpec, which fails.

        restVoiceServiceSpecMockMvc.perform(post("/api/voice-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voiceServiceSpec)))
            .andExpect(status().isBadRequest());

        List<VoiceServiceSpec> voiceServiceSpecList = voiceServiceSpecRepository.findAll();
        assertThat(voiceServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = voiceServiceSpecRepository.findAll().size();
        // set the field null
        voiceServiceSpec.setLastUpdatedBy(null);

        // Create the VoiceServiceSpec, which fails.

        restVoiceServiceSpecMockMvc.perform(post("/api/voice-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voiceServiceSpec)))
            .andExpect(status().isBadRequest());

        List<VoiceServiceSpec> voiceServiceSpecList = voiceServiceSpecRepository.findAll();
        assertThat(voiceServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = voiceServiceSpecRepository.findAll().size();
        // set the field null
        voiceServiceSpec.setTenantId(null);

        // Create the VoiceServiceSpec, which fails.

        restVoiceServiceSpecMockMvc.perform(post("/api/voice-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voiceServiceSpec)))
            .andExpect(status().isBadRequest());

        List<VoiceServiceSpec> voiceServiceSpecList = voiceServiceSpecRepository.findAll();
        assertThat(voiceServiceSpecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllVoiceServiceSpecs() throws Exception {
        // Initialize the database
        voiceServiceSpecRepository.save(voiceServiceSpec);

        // Get all the voiceServiceSpecList
        restVoiceServiceSpecMockMvc.perform(get("/api/voice-service-specs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(voiceServiceSpec.getId())))
            .andExpect(jsonPath("$.[*].voiceSpecId").value(hasItem(DEFAULT_VOICE_SPEC_ID.intValue())))
            .andExpect(jsonPath("$.[*].serviceSpecId").value(hasItem(DEFAULT_SERVICE_SPEC_ID)))
            .andExpect(jsonPath("$.[*].roamingIncomingEnabled").value(hasItem(DEFAULT_ROAMING_INCOMING_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].roamingOutgoingEnabled").value(hasItem(DEFAULT_ROAMING_OUTGOING_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].iddEnabled").value(hasItem(DEFAULT_IDD_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].iddOptions").value(hasItem(DEFAULT_IDD_OPTIONS)))
            .andExpect(jsonPath("$.[*].callForwardEnabled").value(hasItem(DEFAULT_CALL_FORWARD_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].callWaitingEnabled").value(hasItem(DEFAULT_CALL_WAITING_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].clipEnabled").value(hasItem(DEFAULT_CLIP_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].callBarringEnabled").value(hasItem(DEFAULT_CALL_BARRING_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].mvrsEnabled").value(hasItem(DEFAULT_MVRS_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].specialCallServices").value(hasItem(DEFAULT_SPECIAL_CALL_SERVICES)))
            .andExpect(jsonPath("$.[*].callRoutingSupported").value(hasItem(DEFAULT_CALL_ROUTING_SUPPORTED.booleanValue())))
            .andExpect(jsonPath("$.[*].prbtSupported").value(hasItem(DEFAULT_PRBT_SUPPORTED.booleanValue())))
            .andExpect(jsonPath("$.[*].hrbtSupported").value(hasItem(DEFAULT_HRBT_SUPPORTED.booleanValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getVoiceServiceSpec() throws Exception {
        // Initialize the database
        voiceServiceSpecRepository.save(voiceServiceSpec);

        // Get the voiceServiceSpec
        restVoiceServiceSpecMockMvc.perform(get("/api/voice-service-specs/{id}", voiceServiceSpec.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(voiceServiceSpec.getId()))
            .andExpect(jsonPath("$.voiceSpecId").value(DEFAULT_VOICE_SPEC_ID.intValue()))
            .andExpect(jsonPath("$.serviceSpecId").value(DEFAULT_SERVICE_SPEC_ID))
            .andExpect(jsonPath("$.roamingIncomingEnabled").value(DEFAULT_ROAMING_INCOMING_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.roamingOutgoingEnabled").value(DEFAULT_ROAMING_OUTGOING_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.iddEnabled").value(DEFAULT_IDD_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.iddOptions").value(DEFAULT_IDD_OPTIONS))
            .andExpect(jsonPath("$.callForwardEnabled").value(DEFAULT_CALL_FORWARD_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.callWaitingEnabled").value(DEFAULT_CALL_WAITING_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.clipEnabled").value(DEFAULT_CLIP_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.callBarringEnabled").value(DEFAULT_CALL_BARRING_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.mvrsEnabled").value(DEFAULT_MVRS_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.specialCallServices").value(DEFAULT_SPECIAL_CALL_SERVICES))
            .andExpect(jsonPath("$.callRoutingSupported").value(DEFAULT_CALL_ROUTING_SUPPORTED.booleanValue()))
            .andExpect(jsonPath("$.prbtSupported").value(DEFAULT_PRBT_SUPPORTED.booleanValue()))
            .andExpect(jsonPath("$.hrbtSupported").value(DEFAULT_HRBT_SUPPORTED.booleanValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingVoiceServiceSpec() throws Exception {
        // Get the voiceServiceSpec
        restVoiceServiceSpecMockMvc.perform(get("/api/voice-service-specs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateVoiceServiceSpec() throws Exception {
        // Initialize the database
        voiceServiceSpecService.save(voiceServiceSpec);

        int databaseSizeBeforeUpdate = voiceServiceSpecRepository.findAll().size();

        // Update the voiceServiceSpec
        VoiceServiceSpec updatedVoiceServiceSpec = voiceServiceSpecRepository.findById(voiceServiceSpec.getId()).get();
        updatedVoiceServiceSpec
            .voiceSpecId(UPDATED_VOICE_SPEC_ID)
            .serviceSpecId(UPDATED_SERVICE_SPEC_ID)
            .roamingIncomingEnabled(UPDATED_ROAMING_INCOMING_ENABLED)
            .roamingOutgoingEnabled(UPDATED_ROAMING_OUTGOING_ENABLED)
            .iddEnabled(UPDATED_IDD_ENABLED)
            .iddOptions(UPDATED_IDD_OPTIONS)
            .callForwardEnabled(UPDATED_CALL_FORWARD_ENABLED)
            .callWaitingEnabled(UPDATED_CALL_WAITING_ENABLED)
            .clipEnabled(UPDATED_CLIP_ENABLED)
            .callBarringEnabled(UPDATED_CALL_BARRING_ENABLED)
            .mvrsEnabled(UPDATED_MVRS_ENABLED)
            .specialCallServices(UPDATED_SPECIAL_CALL_SERVICES)
            .callRoutingSupported(UPDATED_CALL_ROUTING_SUPPORTED)
            .prbtSupported(UPDATED_PRBT_SUPPORTED)
            .hrbtSupported(UPDATED_HRBT_SUPPORTED)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restVoiceServiceSpecMockMvc.perform(put("/api/voice-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedVoiceServiceSpec)))
            .andExpect(status().isOk());

        // Validate the VoiceServiceSpec in the database
        List<VoiceServiceSpec> voiceServiceSpecList = voiceServiceSpecRepository.findAll();
        assertThat(voiceServiceSpecList).hasSize(databaseSizeBeforeUpdate);
        VoiceServiceSpec testVoiceServiceSpec = voiceServiceSpecList.get(voiceServiceSpecList.size() - 1);
        assertThat(testVoiceServiceSpec.getVoiceSpecId()).isEqualTo(UPDATED_VOICE_SPEC_ID);
        assertThat(testVoiceServiceSpec.getServiceSpecId()).isEqualTo(UPDATED_SERVICE_SPEC_ID);
        assertThat(testVoiceServiceSpec.isRoamingIncomingEnabled()).isEqualTo(UPDATED_ROAMING_INCOMING_ENABLED);
        assertThat(testVoiceServiceSpec.isRoamingOutgoingEnabled()).isEqualTo(UPDATED_ROAMING_OUTGOING_ENABLED);
        assertThat(testVoiceServiceSpec.isIddEnabled()).isEqualTo(UPDATED_IDD_ENABLED);
        assertThat(testVoiceServiceSpec.getIddOptions()).isEqualTo(UPDATED_IDD_OPTIONS);
        assertThat(testVoiceServiceSpec.isCallForwardEnabled()).isEqualTo(UPDATED_CALL_FORWARD_ENABLED);
        assertThat(testVoiceServiceSpec.isCallWaitingEnabled()).isEqualTo(UPDATED_CALL_WAITING_ENABLED);
        assertThat(testVoiceServiceSpec.isClipEnabled()).isEqualTo(UPDATED_CLIP_ENABLED);
        assertThat(testVoiceServiceSpec.isCallBarringEnabled()).isEqualTo(UPDATED_CALL_BARRING_ENABLED);
        assertThat(testVoiceServiceSpec.isMvrsEnabled()).isEqualTo(UPDATED_MVRS_ENABLED);
        assertThat(testVoiceServiceSpec.getSpecialCallServices()).isEqualTo(UPDATED_SPECIAL_CALL_SERVICES);
        assertThat(testVoiceServiceSpec.isCallRoutingSupported()).isEqualTo(UPDATED_CALL_ROUTING_SUPPORTED);
        assertThat(testVoiceServiceSpec.isPrbtSupported()).isEqualTo(UPDATED_PRBT_SUPPORTED);
        assertThat(testVoiceServiceSpec.isHrbtSupported()).isEqualTo(UPDATED_HRBT_SUPPORTED);
        assertThat(testVoiceServiceSpec.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testVoiceServiceSpec.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testVoiceServiceSpec.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testVoiceServiceSpec.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testVoiceServiceSpec.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingVoiceServiceSpec() throws Exception {
        int databaseSizeBeforeUpdate = voiceServiceSpecRepository.findAll().size();

        // Create the VoiceServiceSpec

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVoiceServiceSpecMockMvc.perform(put("/api/voice-service-specs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voiceServiceSpec)))
            .andExpect(status().isBadRequest());

        // Validate the VoiceServiceSpec in the database
        List<VoiceServiceSpec> voiceServiceSpecList = voiceServiceSpecRepository.findAll();
        assertThat(voiceServiceSpecList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteVoiceServiceSpec() throws Exception {
        // Initialize the database
        voiceServiceSpecService.save(voiceServiceSpec);

        int databaseSizeBeforeDelete = voiceServiceSpecRepository.findAll().size();

        // Delete the voiceServiceSpec
        restVoiceServiceSpecMockMvc.perform(delete("/api/voice-service-specs/{id}", voiceServiceSpec.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VoiceServiceSpec> voiceServiceSpecList = voiceServiceSpecRepository.findAll();
        assertThat(voiceServiceSpecList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
