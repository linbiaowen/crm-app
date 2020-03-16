package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.hthk.crm.domain.enumeration.ProductSpecType;

import com.hthk.crm.domain.enumeration.SkuType;

import com.hthk.crm.domain.enumeration.SimType;

import com.hthk.crm.domain.enumeration.BoxType;

/**
 * A ProductSpecification.
 */
@Document(collection = "product_specification")
public class ProductSpecification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("product_spec_id")
    private Long productSpecId;

    @NotNull
    @Field("product_id")
    private String productId;

    @Field("service_spec_id")
    private String serviceSpecId;

    @Field("product_spec_type")
    private ProductSpecType productSpecType;

    @Field("sku_type")
    private SkuType skuType;

    @Field("sim_type")
    private SimType simType;

    @Field("box_type")
    private BoxType boxType;

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
    @Field("voice")
    private ProductVoice voice;

    @DBRef
    @Field("data")
    private ProductData data;

    @DBRef
    @Field("sms")
    private ProductSms sms;

    @DBRef
    @Field("cfsServiceSpec")
    private Set<CfsServiceSpec> cfsServiceSpecs = new HashSet<>();

    @DBRef
    @Field("product")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Product product;

    @DBRef
    @Field("offer")
    @JsonIgnoreProperties("productSpecifications")
    private Offer offer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getProductSpecId() {
        return productSpecId;
    }

    public ProductSpecification productSpecId(Long productSpecId) {
        this.productSpecId = productSpecId;
        return this;
    }

    public void setProductSpecId(Long productSpecId) {
        this.productSpecId = productSpecId;
    }

    public String getProductId() {
        return productId;
    }

    public ProductSpecification productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getServiceSpecId() {
        return serviceSpecId;
    }

    public ProductSpecification serviceSpecId(String serviceSpecId) {
        this.serviceSpecId = serviceSpecId;
        return this;
    }

    public void setServiceSpecId(String serviceSpecId) {
        this.serviceSpecId = serviceSpecId;
    }

    public ProductSpecType getProductSpecType() {
        return productSpecType;
    }

    public ProductSpecification productSpecType(ProductSpecType productSpecType) {
        this.productSpecType = productSpecType;
        return this;
    }

    public void setProductSpecType(ProductSpecType productSpecType) {
        this.productSpecType = productSpecType;
    }

    public SkuType getSkuType() {
        return skuType;
    }

    public ProductSpecification skuType(SkuType skuType) {
        this.skuType = skuType;
        return this;
    }

    public void setSkuType(SkuType skuType) {
        this.skuType = skuType;
    }

    public SimType getSimType() {
        return simType;
    }

    public ProductSpecification simType(SimType simType) {
        this.simType = simType;
        return this;
    }

    public void setSimType(SimType simType) {
        this.simType = simType;
    }

    public BoxType getBoxType() {
        return boxType;
    }

    public ProductSpecification boxType(BoxType boxType) {
        this.boxType = boxType;
        return this;
    }

    public void setBoxType(BoxType boxType) {
        this.boxType = boxType;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ProductSpecification createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ProductSpecification createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public ProductSpecification lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public ProductSpecification lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public ProductSpecification tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public ProductVoice getVoice() {
        return voice;
    }

    public ProductSpecification voice(ProductVoice productVoice) {
        this.voice = productVoice;
        return this;
    }

    public void setVoice(ProductVoice productVoice) {
        this.voice = productVoice;
    }

    public ProductData getData() {
        return data;
    }

    public ProductSpecification data(ProductData productData) {
        this.data = productData;
        return this;
    }

    public void setData(ProductData productData) {
        this.data = productData;
    }

    public ProductSms getSms() {
        return sms;
    }

    public ProductSpecification sms(ProductSms productSms) {
        this.sms = productSms;
        return this;
    }

    public void setSms(ProductSms productSms) {
        this.sms = productSms;
    }

    public Set<CfsServiceSpec> getCfsServiceSpecs() {
        return cfsServiceSpecs;
    }

    public ProductSpecification cfsServiceSpecs(Set<CfsServiceSpec> cfsServiceSpecs) {
        this.cfsServiceSpecs = cfsServiceSpecs;
        return this;
    }

    public ProductSpecification addCfsServiceSpec(CfsServiceSpec cfsServiceSpec) {
        this.cfsServiceSpecs.add(cfsServiceSpec);
        cfsServiceSpec.setProductSpecification(this);
        return this;
    }

    public ProductSpecification removeCfsServiceSpec(CfsServiceSpec cfsServiceSpec) {
        this.cfsServiceSpecs.remove(cfsServiceSpec);
        cfsServiceSpec.setProductSpecification(null);
        return this;
    }

    public void setCfsServiceSpecs(Set<CfsServiceSpec> cfsServiceSpecs) {
        this.cfsServiceSpecs = cfsServiceSpecs;
    }

    public Product getProduct() {
        return product;
    }

    public ProductSpecification product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Offer getOffer() {
        return offer;
    }

    public ProductSpecification offer(Offer offer) {
        this.offer = offer;
        return this;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductSpecification)) {
            return false;
        }
        return id != null && id.equals(((ProductSpecification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProductSpecification{" +
            "id=" + getId() +
            ", productSpecId=" + getProductSpecId() +
            ", productId='" + getProductId() + "'" +
            ", serviceSpecId='" + getServiceSpecId() + "'" +
            ", productSpecType='" + getProductSpecType() + "'" +
            ", skuType='" + getSkuType() + "'" +
            ", simType='" + getSimType() + "'" +
            ", boxType='" + getBoxType() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
