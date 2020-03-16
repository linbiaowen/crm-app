package com.hthk.crm.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.hthk.crm.domain.enumeration.ProductCate;

import com.hthk.crm.domain.enumeration.ProductNature;

import com.hthk.crm.domain.enumeration.ProductFamily;

import com.hthk.crm.domain.enumeration.ProductType;

/**
 * A Product.
 */
@Document(collection = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("product_id")
    private String productId;

    @NotNull
    @Field("product_name")
    private String productName;

    @Field("product_name_chi")
    private String productNameChi;

    @Field("product_desc")
    private String productDesc;

    @Field("product_desc_chi")
    private String productDescChi;

    @Field("product_cate")
    private ProductCate productCate;

    @Field("product_nature")
    private ProductNature productNature;

    @Field("product_family")
    private ProductFamily productFamily;

    @Field("product_type")
    private ProductType productType;

    @Field("model_code")
    private String modelCode;

    @Field("start_date")
    private Instant startDate;

    @Field("end_date")
    private Instant endDate;

    @Field("independently_orderable")
    private Boolean independentlyOrderable;

    @Field("network_provision_required")
    private Boolean networkProvisionRequired;

    @Field("change_entitlement_allowed")
    private Boolean changeEntitlementAllowed;

    @NotNull
    @Field("created_date")
    private Instant createdDate;

    @NotNull
    @Field("created_by")
    private String createdBy;

    @NotNull
    @Field("last_updated_date")
    private Instant lastUpdatedDate;

    @NotNull
    @Field("last_updated_by")
    private String lastUpdatedBy;

    @NotNull
    @Field("tenant_id")
    private String tenantId;

    @DBRef
    @Field("productSpec")
    private ProductSpecification productSpec;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public Product productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public Product productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNameChi() {
        return productNameChi;
    }

    public Product productNameChi(String productNameChi) {
        this.productNameChi = productNameChi;
        return this;
    }

    public void setProductNameChi(String productNameChi) {
        this.productNameChi = productNameChi;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public Product productDesc(String productDesc) {
        this.productDesc = productDesc;
        return this;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductDescChi() {
        return productDescChi;
    }

    public Product productDescChi(String productDescChi) {
        this.productDescChi = productDescChi;
        return this;
    }

    public void setProductDescChi(String productDescChi) {
        this.productDescChi = productDescChi;
    }

    public ProductCate getProductCate() {
        return productCate;
    }

    public Product productCate(ProductCate productCate) {
        this.productCate = productCate;
        return this;
    }

    public void setProductCate(ProductCate productCate) {
        this.productCate = productCate;
    }

    public ProductNature getProductNature() {
        return productNature;
    }

    public Product productNature(ProductNature productNature) {
        this.productNature = productNature;
        return this;
    }

    public void setProductNature(ProductNature productNature) {
        this.productNature = productNature;
    }

    public ProductFamily getProductFamily() {
        return productFamily;
    }

    public Product productFamily(ProductFamily productFamily) {
        this.productFamily = productFamily;
        return this;
    }

    public void setProductFamily(ProductFamily productFamily) {
        this.productFamily = productFamily;
    }

    public ProductType getProductType() {
        return productType;
    }

    public Product productType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getModelCode() {
        return modelCode;
    }

    public Product modelCode(String modelCode) {
        this.modelCode = modelCode;
        return this;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Product startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Product endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Boolean isIndependentlyOrderable() {
        return independentlyOrderable;
    }

    public Product independentlyOrderable(Boolean independentlyOrderable) {
        this.independentlyOrderable = independentlyOrderable;
        return this;
    }

    public void setIndependentlyOrderable(Boolean independentlyOrderable) {
        this.independentlyOrderable = independentlyOrderable;
    }

    public Boolean isNetworkProvisionRequired() {
        return networkProvisionRequired;
    }

    public Product networkProvisionRequired(Boolean networkProvisionRequired) {
        this.networkProvisionRequired = networkProvisionRequired;
        return this;
    }

    public void setNetworkProvisionRequired(Boolean networkProvisionRequired) {
        this.networkProvisionRequired = networkProvisionRequired;
    }

    public Boolean isChangeEntitlementAllowed() {
        return changeEntitlementAllowed;
    }

    public Product changeEntitlementAllowed(Boolean changeEntitlementAllowed) {
        this.changeEntitlementAllowed = changeEntitlementAllowed;
        return this;
    }

    public void setChangeEntitlementAllowed(Boolean changeEntitlementAllowed) {
        this.changeEntitlementAllowed = changeEntitlementAllowed;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Product createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Product createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public Product lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Product lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public Product tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public ProductSpecification getProductSpec() {
        return productSpec;
    }

    public Product productSpec(ProductSpecification productSpecification) {
        this.productSpec = productSpecification;
        return this;
    }

    public void setProductSpec(ProductSpecification productSpecification) {
        this.productSpec = productSpecification;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", productId='" + getProductId() + "'" +
            ", productName='" + getProductName() + "'" +
            ", productNameChi='" + getProductNameChi() + "'" +
            ", productDesc='" + getProductDesc() + "'" +
            ", productDescChi='" + getProductDescChi() + "'" +
            ", productCate='" + getProductCate() + "'" +
            ", productNature='" + getProductNature() + "'" +
            ", productFamily='" + getProductFamily() + "'" +
            ", productType='" + getProductType() + "'" +
            ", modelCode='" + getModelCode() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", independentlyOrderable='" + isIndependentlyOrderable() + "'" +
            ", networkProvisionRequired='" + isNetworkProvisionRequired() + "'" +
            ", changeEntitlementAllowed='" + isChangeEntitlementAllowed() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
