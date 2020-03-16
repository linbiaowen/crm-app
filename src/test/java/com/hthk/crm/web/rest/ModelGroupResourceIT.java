package com.hthk.crm.web.rest;

import com.hthk.crm.RedisTestContainerExtension;
import com.hthk.crm.CrmwebApp;
import com.hthk.crm.config.TestSecurityConfiguration;
import com.hthk.crm.domain.ModelGroup;
import com.hthk.crm.repository.ModelGroupRepository;
import com.hthk.crm.service.ModelGroupService;
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

/**
 * Integration tests for the {@link ModelGroupResource} REST controller.
 */
@SpringBootTest(classes = {CrmwebApp.class, TestSecurityConfiguration.class})
@ExtendWith(RedisTestContainerExtension.class)
public class ModelGroupResourceIT {

    private static final String DEFAULT_MODEL_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_MODEL_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_DESC = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_DESC = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_LIST_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_LIST_PRICE = new BigDecimal(2);

    private static final String DEFAULT_BRAND = "AAAAAAAAAA";
    private static final String UPDATED_BRAND = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final String DEFAULT_ORIG_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_ORIG_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_NETWORK = "AAAAAAAAAA";
    private static final String UPDATED_NETWORK = "BBBBBBBBBB";

    private static final String DEFAULT_CAMERA = "AAAAAAAAAA";
    private static final String UPDATED_CAMERA = "BBBBBBBBBB";

    private static final String DEFAULT_MEM_CARD_SLOT = "AAAAAAAAAA";
    private static final String UPDATED_MEM_CARD_SLOT = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_TRANSFER = "AAAAAAAAAA";
    private static final String UPDATED_DATA_TRANSFER = "BBBBBBBBBB";

    private static final String DEFAULT_WARRANTY = "AAAAAAAAAA";
    private static final String UPDATED_WARRANTY = "BBBBBBBBBB";

    private static final String DEFAULT_WARRANTY_PROVIDER = "AAAAAAAAAA";
    private static final String UPDATED_WARRANTY_PROVIDER = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL_CATE = "AAAAAAAAAA";
    private static final String UPDATED_MODEL_CATE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS_END_DATE = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS_END_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_BRAND_CHI = "AAAAAAAAAA";
    private static final String UPDATED_BRAND_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL_CHI = "AAAAAAAAAA";
    private static final String UPDATED_MODEL_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_ORIG_COUNTRY_CHI = "AAAAAAAAAA";
    private static final String UPDATED_ORIG_COUNTRY_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_NETWORK_CHI = "AAAAAAAAAA";
    private static final String UPDATED_NETWORK_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_CAMERA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_CAMERA_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_MEM_CARD_SLOT_CHI = "AAAAAAAAAA";
    private static final String UPDATED_MEM_CARD_SLOT_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_TRANSFER_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DATA_TRANSFER_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_WARRANTY_CHI = "AAAAAAAAAA";
    private static final String UPDATED_WARRANTY_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_WARRANTY_PROVIDER_CHI = "AAAAAAAAAA";
    private static final String UPDATED_WARRANTY_PROVIDER_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL_CATE_CHI = "AAAAAAAAAA";
    private static final String UPDATED_MODEL_CATE_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS_CHI = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS_CHI_END_DATE = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS_CHI_END_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_COUPON_FLAG = "AAAAAAAAAA";
    private static final String UPDATED_COUPON_FLAG = "BBBBBBBBBB";

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
    private ModelGroupRepository modelGroupRepository;

    @Autowired
    private ModelGroupService modelGroupService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restModelGroupMockMvc;

    private ModelGroup modelGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ModelGroupResource modelGroupResource = new ModelGroupResource(modelGroupService);
        this.restModelGroupMockMvc = MockMvcBuilders.standaloneSetup(modelGroupResource)
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
    public static ModelGroup createEntity() {
        ModelGroup modelGroup = new ModelGroup()
            .modelGroup(DEFAULT_MODEL_GROUP)
            .groupDesc(DEFAULT_GROUP_DESC)
            .listPrice(DEFAULT_LIST_PRICE)
            .brand(DEFAULT_BRAND)
            .model(DEFAULT_MODEL)
            .origCountry(DEFAULT_ORIG_COUNTRY)
            .network(DEFAULT_NETWORK)
            .camera(DEFAULT_CAMERA)
            .memCardSlot(DEFAULT_MEM_CARD_SLOT)
            .dataTransfer(DEFAULT_DATA_TRANSFER)
            .warranty(DEFAULT_WARRANTY)
            .warrantyProvider(DEFAULT_WARRANTY_PROVIDER)
            .modelCate(DEFAULT_MODEL_CATE)
            .remarks(DEFAULT_REMARKS)
            .remarksEndDate(DEFAULT_REMARKS_END_DATE)
            .brandChi(DEFAULT_BRAND_CHI)
            .modelChi(DEFAULT_MODEL_CHI)
            .origCountryChi(DEFAULT_ORIG_COUNTRY_CHI)
            .networkChi(DEFAULT_NETWORK_CHI)
            .cameraChi(DEFAULT_CAMERA_CHI)
            .memCardSlotChi(DEFAULT_MEM_CARD_SLOT_CHI)
            .dataTransferChi(DEFAULT_DATA_TRANSFER_CHI)
            .warrantyChi(DEFAULT_WARRANTY_CHI)
            .warrantyProviderChi(DEFAULT_WARRANTY_PROVIDER_CHI)
            .modelCateChi(DEFAULT_MODEL_CATE_CHI)
            .remarksChi(DEFAULT_REMARKS_CHI)
            .remarksChiEndDate(DEFAULT_REMARKS_CHI_END_DATE)
            .couponFlag(DEFAULT_COUPON_FLAG)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .tenantId(DEFAULT_TENANT_ID);
        return modelGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModelGroup createUpdatedEntity() {
        ModelGroup modelGroup = new ModelGroup()
            .modelGroup(UPDATED_MODEL_GROUP)
            .groupDesc(UPDATED_GROUP_DESC)
            .listPrice(UPDATED_LIST_PRICE)
            .brand(UPDATED_BRAND)
            .model(UPDATED_MODEL)
            .origCountry(UPDATED_ORIG_COUNTRY)
            .network(UPDATED_NETWORK)
            .camera(UPDATED_CAMERA)
            .memCardSlot(UPDATED_MEM_CARD_SLOT)
            .dataTransfer(UPDATED_DATA_TRANSFER)
            .warranty(UPDATED_WARRANTY)
            .warrantyProvider(UPDATED_WARRANTY_PROVIDER)
            .modelCate(UPDATED_MODEL_CATE)
            .remarks(UPDATED_REMARKS)
            .remarksEndDate(UPDATED_REMARKS_END_DATE)
            .brandChi(UPDATED_BRAND_CHI)
            .modelChi(UPDATED_MODEL_CHI)
            .origCountryChi(UPDATED_ORIG_COUNTRY_CHI)
            .networkChi(UPDATED_NETWORK_CHI)
            .cameraChi(UPDATED_CAMERA_CHI)
            .memCardSlotChi(UPDATED_MEM_CARD_SLOT_CHI)
            .dataTransferChi(UPDATED_DATA_TRANSFER_CHI)
            .warrantyChi(UPDATED_WARRANTY_CHI)
            .warrantyProviderChi(UPDATED_WARRANTY_PROVIDER_CHI)
            .modelCateChi(UPDATED_MODEL_CATE_CHI)
            .remarksChi(UPDATED_REMARKS_CHI)
            .remarksChiEndDate(UPDATED_REMARKS_CHI_END_DATE)
            .couponFlag(UPDATED_COUPON_FLAG)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);
        return modelGroup;
    }

    @BeforeEach
    public void initTest() {
        modelGroupRepository.deleteAll();
        modelGroup = createEntity();
    }

    @Test
    public void createModelGroup() throws Exception {
        int databaseSizeBeforeCreate = modelGroupRepository.findAll().size();

        // Create the ModelGroup
        restModelGroupMockMvc.perform(post("/api/model-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelGroup)))
            .andExpect(status().isCreated());

        // Validate the ModelGroup in the database
        List<ModelGroup> modelGroupList = modelGroupRepository.findAll();
        assertThat(modelGroupList).hasSize(databaseSizeBeforeCreate + 1);
        ModelGroup testModelGroup = modelGroupList.get(modelGroupList.size() - 1);
        assertThat(testModelGroup.getModelGroup()).isEqualTo(DEFAULT_MODEL_GROUP);
        assertThat(testModelGroup.getGroupDesc()).isEqualTo(DEFAULT_GROUP_DESC);
        assertThat(testModelGroup.getListPrice()).isEqualTo(DEFAULT_LIST_PRICE);
        assertThat(testModelGroup.getBrand()).isEqualTo(DEFAULT_BRAND);
        assertThat(testModelGroup.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testModelGroup.getOrigCountry()).isEqualTo(DEFAULT_ORIG_COUNTRY);
        assertThat(testModelGroup.getNetwork()).isEqualTo(DEFAULT_NETWORK);
        assertThat(testModelGroup.getCamera()).isEqualTo(DEFAULT_CAMERA);
        assertThat(testModelGroup.getMemCardSlot()).isEqualTo(DEFAULT_MEM_CARD_SLOT);
        assertThat(testModelGroup.getDataTransfer()).isEqualTo(DEFAULT_DATA_TRANSFER);
        assertThat(testModelGroup.getWarranty()).isEqualTo(DEFAULT_WARRANTY);
        assertThat(testModelGroup.getWarrantyProvider()).isEqualTo(DEFAULT_WARRANTY_PROVIDER);
        assertThat(testModelGroup.getModelCate()).isEqualTo(DEFAULT_MODEL_CATE);
        assertThat(testModelGroup.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testModelGroup.getRemarksEndDate()).isEqualTo(DEFAULT_REMARKS_END_DATE);
        assertThat(testModelGroup.getBrandChi()).isEqualTo(DEFAULT_BRAND_CHI);
        assertThat(testModelGroup.getModelChi()).isEqualTo(DEFAULT_MODEL_CHI);
        assertThat(testModelGroup.getOrigCountryChi()).isEqualTo(DEFAULT_ORIG_COUNTRY_CHI);
        assertThat(testModelGroup.getNetworkChi()).isEqualTo(DEFAULT_NETWORK_CHI);
        assertThat(testModelGroup.getCameraChi()).isEqualTo(DEFAULT_CAMERA_CHI);
        assertThat(testModelGroup.getMemCardSlotChi()).isEqualTo(DEFAULT_MEM_CARD_SLOT_CHI);
        assertThat(testModelGroup.getDataTransferChi()).isEqualTo(DEFAULT_DATA_TRANSFER_CHI);
        assertThat(testModelGroup.getWarrantyChi()).isEqualTo(DEFAULT_WARRANTY_CHI);
        assertThat(testModelGroup.getWarrantyProviderChi()).isEqualTo(DEFAULT_WARRANTY_PROVIDER_CHI);
        assertThat(testModelGroup.getModelCateChi()).isEqualTo(DEFAULT_MODEL_CATE_CHI);
        assertThat(testModelGroup.getRemarksChi()).isEqualTo(DEFAULT_REMARKS_CHI);
        assertThat(testModelGroup.getRemarksChiEndDate()).isEqualTo(DEFAULT_REMARKS_CHI_END_DATE);
        assertThat(testModelGroup.getCouponFlag()).isEqualTo(DEFAULT_COUPON_FLAG);
        assertThat(testModelGroup.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testModelGroup.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testModelGroup.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testModelGroup.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testModelGroup.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    public void createModelGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modelGroupRepository.findAll().size();

        // Create the ModelGroup with an existing ID
        modelGroup.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restModelGroupMockMvc.perform(post("/api/model-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelGroup)))
            .andExpect(status().isBadRequest());

        // Validate the ModelGroup in the database
        List<ModelGroup> modelGroupList = modelGroupRepository.findAll();
        assertThat(modelGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkModelGroupIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelGroupRepository.findAll().size();
        // set the field null
        modelGroup.setModelGroup(null);

        // Create the ModelGroup, which fails.

        restModelGroupMockMvc.perform(post("/api/model-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelGroup)))
            .andExpect(status().isBadRequest());

        List<ModelGroup> modelGroupList = modelGroupRepository.findAll();
        assertThat(modelGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelGroupRepository.findAll().size();
        // set the field null
        modelGroup.setCreatedDate(null);

        // Create the ModelGroup, which fails.

        restModelGroupMockMvc.perform(post("/api/model-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelGroup)))
            .andExpect(status().isBadRequest());

        List<ModelGroup> modelGroupList = modelGroupRepository.findAll();
        assertThat(modelGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelGroupRepository.findAll().size();
        // set the field null
        modelGroup.setCreatedBy(null);

        // Create the ModelGroup, which fails.

        restModelGroupMockMvc.perform(post("/api/model-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelGroup)))
            .andExpect(status().isBadRequest());

        List<ModelGroup> modelGroupList = modelGroupRepository.findAll();
        assertThat(modelGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelGroupRepository.findAll().size();
        // set the field null
        modelGroup.setLastUpdatedDate(null);

        // Create the ModelGroup, which fails.

        restModelGroupMockMvc.perform(post("/api/model-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelGroup)))
            .andExpect(status().isBadRequest());

        List<ModelGroup> modelGroupList = modelGroupRepository.findAll();
        assertThat(modelGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelGroupRepository.findAll().size();
        // set the field null
        modelGroup.setLastUpdatedBy(null);

        // Create the ModelGroup, which fails.

        restModelGroupMockMvc.perform(post("/api/model-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelGroup)))
            .andExpect(status().isBadRequest());

        List<ModelGroup> modelGroupList = modelGroupRepository.findAll();
        assertThat(modelGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelGroupRepository.findAll().size();
        // set the field null
        modelGroup.setTenantId(null);

        // Create the ModelGroup, which fails.

        restModelGroupMockMvc.perform(post("/api/model-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelGroup)))
            .andExpect(status().isBadRequest());

        List<ModelGroup> modelGroupList = modelGroupRepository.findAll();
        assertThat(modelGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllModelGroups() throws Exception {
        // Initialize the database
        modelGroupRepository.save(modelGroup);

        // Get all the modelGroupList
        restModelGroupMockMvc.perform(get("/api/model-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modelGroup.getId())))
            .andExpect(jsonPath("$.[*].modelGroup").value(hasItem(DEFAULT_MODEL_GROUP)))
            .andExpect(jsonPath("$.[*].groupDesc").value(hasItem(DEFAULT_GROUP_DESC)))
            .andExpect(jsonPath("$.[*].listPrice").value(hasItem(DEFAULT_LIST_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].brand").value(hasItem(DEFAULT_BRAND)))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL)))
            .andExpect(jsonPath("$.[*].origCountry").value(hasItem(DEFAULT_ORIG_COUNTRY)))
            .andExpect(jsonPath("$.[*].network").value(hasItem(DEFAULT_NETWORK)))
            .andExpect(jsonPath("$.[*].camera").value(hasItem(DEFAULT_CAMERA)))
            .andExpect(jsonPath("$.[*].memCardSlot").value(hasItem(DEFAULT_MEM_CARD_SLOT)))
            .andExpect(jsonPath("$.[*].dataTransfer").value(hasItem(DEFAULT_DATA_TRANSFER)))
            .andExpect(jsonPath("$.[*].warranty").value(hasItem(DEFAULT_WARRANTY)))
            .andExpect(jsonPath("$.[*].warrantyProvider").value(hasItem(DEFAULT_WARRANTY_PROVIDER)))
            .andExpect(jsonPath("$.[*].modelCate").value(hasItem(DEFAULT_MODEL_CATE)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].remarksEndDate").value(hasItem(DEFAULT_REMARKS_END_DATE)))
            .andExpect(jsonPath("$.[*].brandChi").value(hasItem(DEFAULT_BRAND_CHI)))
            .andExpect(jsonPath("$.[*].modelChi").value(hasItem(DEFAULT_MODEL_CHI)))
            .andExpect(jsonPath("$.[*].origCountryChi").value(hasItem(DEFAULT_ORIG_COUNTRY_CHI)))
            .andExpect(jsonPath("$.[*].networkChi").value(hasItem(DEFAULT_NETWORK_CHI)))
            .andExpect(jsonPath("$.[*].cameraChi").value(hasItem(DEFAULT_CAMERA_CHI)))
            .andExpect(jsonPath("$.[*].memCardSlotChi").value(hasItem(DEFAULT_MEM_CARD_SLOT_CHI)))
            .andExpect(jsonPath("$.[*].dataTransferChi").value(hasItem(DEFAULT_DATA_TRANSFER_CHI)))
            .andExpect(jsonPath("$.[*].warrantyChi").value(hasItem(DEFAULT_WARRANTY_CHI)))
            .andExpect(jsonPath("$.[*].warrantyProviderChi").value(hasItem(DEFAULT_WARRANTY_PROVIDER_CHI)))
            .andExpect(jsonPath("$.[*].modelCateChi").value(hasItem(DEFAULT_MODEL_CATE_CHI)))
            .andExpect(jsonPath("$.[*].remarksChi").value(hasItem(DEFAULT_REMARKS_CHI)))
            .andExpect(jsonPath("$.[*].remarksChiEndDate").value(hasItem(DEFAULT_REMARKS_CHI_END_DATE)))
            .andExpect(jsonPath("$.[*].couponFlag").value(hasItem(DEFAULT_COUPON_FLAG)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)));
    }
    
    @Test
    public void getModelGroup() throws Exception {
        // Initialize the database
        modelGroupRepository.save(modelGroup);

        // Get the modelGroup
        restModelGroupMockMvc.perform(get("/api/model-groups/{id}", modelGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(modelGroup.getId()))
            .andExpect(jsonPath("$.modelGroup").value(DEFAULT_MODEL_GROUP))
            .andExpect(jsonPath("$.groupDesc").value(DEFAULT_GROUP_DESC))
            .andExpect(jsonPath("$.listPrice").value(DEFAULT_LIST_PRICE.intValue()))
            .andExpect(jsonPath("$.brand").value(DEFAULT_BRAND))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL))
            .andExpect(jsonPath("$.origCountry").value(DEFAULT_ORIG_COUNTRY))
            .andExpect(jsonPath("$.network").value(DEFAULT_NETWORK))
            .andExpect(jsonPath("$.camera").value(DEFAULT_CAMERA))
            .andExpect(jsonPath("$.memCardSlot").value(DEFAULT_MEM_CARD_SLOT))
            .andExpect(jsonPath("$.dataTransfer").value(DEFAULT_DATA_TRANSFER))
            .andExpect(jsonPath("$.warranty").value(DEFAULT_WARRANTY))
            .andExpect(jsonPath("$.warrantyProvider").value(DEFAULT_WARRANTY_PROVIDER))
            .andExpect(jsonPath("$.modelCate").value(DEFAULT_MODEL_CATE))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.remarksEndDate").value(DEFAULT_REMARKS_END_DATE))
            .andExpect(jsonPath("$.brandChi").value(DEFAULT_BRAND_CHI))
            .andExpect(jsonPath("$.modelChi").value(DEFAULT_MODEL_CHI))
            .andExpect(jsonPath("$.origCountryChi").value(DEFAULT_ORIG_COUNTRY_CHI))
            .andExpect(jsonPath("$.networkChi").value(DEFAULT_NETWORK_CHI))
            .andExpect(jsonPath("$.cameraChi").value(DEFAULT_CAMERA_CHI))
            .andExpect(jsonPath("$.memCardSlotChi").value(DEFAULT_MEM_CARD_SLOT_CHI))
            .andExpect(jsonPath("$.dataTransferChi").value(DEFAULT_DATA_TRANSFER_CHI))
            .andExpect(jsonPath("$.warrantyChi").value(DEFAULT_WARRANTY_CHI))
            .andExpect(jsonPath("$.warrantyProviderChi").value(DEFAULT_WARRANTY_PROVIDER_CHI))
            .andExpect(jsonPath("$.modelCateChi").value(DEFAULT_MODEL_CATE_CHI))
            .andExpect(jsonPath("$.remarksChi").value(DEFAULT_REMARKS_CHI))
            .andExpect(jsonPath("$.remarksChiEndDate").value(DEFAULT_REMARKS_CHI_END_DATE))
            .andExpect(jsonPath("$.couponFlag").value(DEFAULT_COUPON_FLAG))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID));
    }

    @Test
    public void getNonExistingModelGroup() throws Exception {
        // Get the modelGroup
        restModelGroupMockMvc.perform(get("/api/model-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateModelGroup() throws Exception {
        // Initialize the database
        modelGroupService.save(modelGroup);

        int databaseSizeBeforeUpdate = modelGroupRepository.findAll().size();

        // Update the modelGroup
        ModelGroup updatedModelGroup = modelGroupRepository.findById(modelGroup.getId()).get();
        updatedModelGroup
            .modelGroup(UPDATED_MODEL_GROUP)
            .groupDesc(UPDATED_GROUP_DESC)
            .listPrice(UPDATED_LIST_PRICE)
            .brand(UPDATED_BRAND)
            .model(UPDATED_MODEL)
            .origCountry(UPDATED_ORIG_COUNTRY)
            .network(UPDATED_NETWORK)
            .camera(UPDATED_CAMERA)
            .memCardSlot(UPDATED_MEM_CARD_SLOT)
            .dataTransfer(UPDATED_DATA_TRANSFER)
            .warranty(UPDATED_WARRANTY)
            .warrantyProvider(UPDATED_WARRANTY_PROVIDER)
            .modelCate(UPDATED_MODEL_CATE)
            .remarks(UPDATED_REMARKS)
            .remarksEndDate(UPDATED_REMARKS_END_DATE)
            .brandChi(UPDATED_BRAND_CHI)
            .modelChi(UPDATED_MODEL_CHI)
            .origCountryChi(UPDATED_ORIG_COUNTRY_CHI)
            .networkChi(UPDATED_NETWORK_CHI)
            .cameraChi(UPDATED_CAMERA_CHI)
            .memCardSlotChi(UPDATED_MEM_CARD_SLOT_CHI)
            .dataTransferChi(UPDATED_DATA_TRANSFER_CHI)
            .warrantyChi(UPDATED_WARRANTY_CHI)
            .warrantyProviderChi(UPDATED_WARRANTY_PROVIDER_CHI)
            .modelCateChi(UPDATED_MODEL_CATE_CHI)
            .remarksChi(UPDATED_REMARKS_CHI)
            .remarksChiEndDate(UPDATED_REMARKS_CHI_END_DATE)
            .couponFlag(UPDATED_COUPON_FLAG)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .tenantId(UPDATED_TENANT_ID);

        restModelGroupMockMvc.perform(put("/api/model-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedModelGroup)))
            .andExpect(status().isOk());

        // Validate the ModelGroup in the database
        List<ModelGroup> modelGroupList = modelGroupRepository.findAll();
        assertThat(modelGroupList).hasSize(databaseSizeBeforeUpdate);
        ModelGroup testModelGroup = modelGroupList.get(modelGroupList.size() - 1);
        assertThat(testModelGroup.getModelGroup()).isEqualTo(UPDATED_MODEL_GROUP);
        assertThat(testModelGroup.getGroupDesc()).isEqualTo(UPDATED_GROUP_DESC);
        assertThat(testModelGroup.getListPrice()).isEqualTo(UPDATED_LIST_PRICE);
        assertThat(testModelGroup.getBrand()).isEqualTo(UPDATED_BRAND);
        assertThat(testModelGroup.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testModelGroup.getOrigCountry()).isEqualTo(UPDATED_ORIG_COUNTRY);
        assertThat(testModelGroup.getNetwork()).isEqualTo(UPDATED_NETWORK);
        assertThat(testModelGroup.getCamera()).isEqualTo(UPDATED_CAMERA);
        assertThat(testModelGroup.getMemCardSlot()).isEqualTo(UPDATED_MEM_CARD_SLOT);
        assertThat(testModelGroup.getDataTransfer()).isEqualTo(UPDATED_DATA_TRANSFER);
        assertThat(testModelGroup.getWarranty()).isEqualTo(UPDATED_WARRANTY);
        assertThat(testModelGroup.getWarrantyProvider()).isEqualTo(UPDATED_WARRANTY_PROVIDER);
        assertThat(testModelGroup.getModelCate()).isEqualTo(UPDATED_MODEL_CATE);
        assertThat(testModelGroup.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testModelGroup.getRemarksEndDate()).isEqualTo(UPDATED_REMARKS_END_DATE);
        assertThat(testModelGroup.getBrandChi()).isEqualTo(UPDATED_BRAND_CHI);
        assertThat(testModelGroup.getModelChi()).isEqualTo(UPDATED_MODEL_CHI);
        assertThat(testModelGroup.getOrigCountryChi()).isEqualTo(UPDATED_ORIG_COUNTRY_CHI);
        assertThat(testModelGroup.getNetworkChi()).isEqualTo(UPDATED_NETWORK_CHI);
        assertThat(testModelGroup.getCameraChi()).isEqualTo(UPDATED_CAMERA_CHI);
        assertThat(testModelGroup.getMemCardSlotChi()).isEqualTo(UPDATED_MEM_CARD_SLOT_CHI);
        assertThat(testModelGroup.getDataTransferChi()).isEqualTo(UPDATED_DATA_TRANSFER_CHI);
        assertThat(testModelGroup.getWarrantyChi()).isEqualTo(UPDATED_WARRANTY_CHI);
        assertThat(testModelGroup.getWarrantyProviderChi()).isEqualTo(UPDATED_WARRANTY_PROVIDER_CHI);
        assertThat(testModelGroup.getModelCateChi()).isEqualTo(UPDATED_MODEL_CATE_CHI);
        assertThat(testModelGroup.getRemarksChi()).isEqualTo(UPDATED_REMARKS_CHI);
        assertThat(testModelGroup.getRemarksChiEndDate()).isEqualTo(UPDATED_REMARKS_CHI_END_DATE);
        assertThat(testModelGroup.getCouponFlag()).isEqualTo(UPDATED_COUPON_FLAG);
        assertThat(testModelGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testModelGroup.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testModelGroup.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testModelGroup.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testModelGroup.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    public void updateNonExistingModelGroup() throws Exception {
        int databaseSizeBeforeUpdate = modelGroupRepository.findAll().size();

        // Create the ModelGroup

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModelGroupMockMvc.perform(put("/api/model-groups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelGroup)))
            .andExpect(status().isBadRequest());

        // Validate the ModelGroup in the database
        List<ModelGroup> modelGroupList = modelGroupRepository.findAll();
        assertThat(modelGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteModelGroup() throws Exception {
        // Initialize the database
        modelGroupService.save(modelGroup);

        int databaseSizeBeforeDelete = modelGroupRepository.findAll().size();

        // Delete the modelGroup
        restModelGroupMockMvc.perform(delete("/api/model-groups/{id}", modelGroup.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ModelGroup> modelGroupList = modelGroupRepository.findAll();
        assertThat(modelGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
