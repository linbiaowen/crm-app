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

/**
 * A CfsServiceSpec.
 */
@Document(collection = "cfs_service_spec")
public class CfsServiceSpec implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("service_spec_id")
    private String serviceSpecId;

    @Field("service_spec_desc")
    private String serviceSpecDesc;

    @NotNull
    @Field("service_id")
    private String serviceId;

    @Field("voice_spec_id")
    private Long voiceSpecId;

    @Field("data_spec_id")
    private Long dataSpecId;

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
    @Field("voiceServiceSpec")
    private VoiceServiceSpec voiceServiceSpec;

    @DBRef
    @Field("dataServiceSpec")
    private DataServiceSpec dataServiceSpec;

    @DBRef
    @Field("resourceSpecifications")
    private Set<ResourceSpecification> resourceSpecifications = new HashSet<>();

    @DBRef
    @Field("cfsServices")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private CfsServices cfsServices;

    @DBRef
    @Field("productSpecification")
    @JsonIgnoreProperties("cfsServiceSpecs")
    private ProductSpecification productSpecification;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceSpecId() {
        return serviceSpecId;
    }

    public CfsServiceSpec serviceSpecId(String serviceSpecId) {
        this.serviceSpecId = serviceSpecId;
        return this;
    }

    public void setServiceSpecId(String serviceSpecId) {
        this.serviceSpecId = serviceSpecId;
    }

    public String getServiceSpecDesc() {
        return serviceSpecDesc;
    }

    public CfsServiceSpec serviceSpecDesc(String serviceSpecDesc) {
        this.serviceSpecDesc = serviceSpecDesc;
        return this;
    }

    public void setServiceSpecDesc(String serviceSpecDesc) {
        this.serviceSpecDesc = serviceSpecDesc;
    }

    public String getServiceId() {
        return serviceId;
    }

    public CfsServiceSpec serviceId(String serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Long getVoiceSpecId() {
        return voiceSpecId;
    }

    public CfsServiceSpec voiceSpecId(Long voiceSpecId) {
        this.voiceSpecId = voiceSpecId;
        return this;
    }

    public void setVoiceSpecId(Long voiceSpecId) {
        this.voiceSpecId = voiceSpecId;
    }

    public Long getDataSpecId() {
        return dataSpecId;
    }

    public CfsServiceSpec dataSpecId(Long dataSpecId) {
        this.dataSpecId = dataSpecId;
        return this;
    }

    public void setDataSpecId(Long dataSpecId) {
        this.dataSpecId = dataSpecId;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public CfsServiceSpec createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CfsServiceSpec createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public CfsServiceSpec lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public CfsServiceSpec lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public CfsServiceSpec tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public VoiceServiceSpec getVoiceServiceSpec() {
        return voiceServiceSpec;
    }

    public CfsServiceSpec voiceServiceSpec(VoiceServiceSpec voiceServiceSpec) {
        this.voiceServiceSpec = voiceServiceSpec;
        return this;
    }

    public void setVoiceServiceSpec(VoiceServiceSpec voiceServiceSpec) {
        this.voiceServiceSpec = voiceServiceSpec;
    }

    public DataServiceSpec getDataServiceSpec() {
        return dataServiceSpec;
    }

    public CfsServiceSpec dataServiceSpec(DataServiceSpec dataServiceSpec) {
        this.dataServiceSpec = dataServiceSpec;
        return this;
    }

    public void setDataServiceSpec(DataServiceSpec dataServiceSpec) {
        this.dataServiceSpec = dataServiceSpec;
    }

    public Set<ResourceSpecification> getResourceSpecifications() {
        return resourceSpecifications;
    }

    public CfsServiceSpec resourceSpecifications(Set<ResourceSpecification> resourceSpecifications) {
        this.resourceSpecifications = resourceSpecifications;
        return this;
    }

    public CfsServiceSpec addResourceSpecifications(ResourceSpecification resourceSpecification) {
        this.resourceSpecifications.add(resourceSpecification);
        resourceSpecification.setCfsServiceSpec(this);
        return this;
    }

    public CfsServiceSpec removeResourceSpecifications(ResourceSpecification resourceSpecification) {
        this.resourceSpecifications.remove(resourceSpecification);
        resourceSpecification.setCfsServiceSpec(null);
        return this;
    }

    public void setResourceSpecifications(Set<ResourceSpecification> resourceSpecifications) {
        this.resourceSpecifications = resourceSpecifications;
    }

    public CfsServices getCfsServices() {
        return cfsServices;
    }

    public CfsServiceSpec cfsServices(CfsServices cfsServices) {
        this.cfsServices = cfsServices;
        return this;
    }

    public void setCfsServices(CfsServices cfsServices) {
        this.cfsServices = cfsServices;
    }

    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public CfsServiceSpec productSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
        return this;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CfsServiceSpec)) {
            return false;
        }
        return id != null && id.equals(((CfsServiceSpec) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CfsServiceSpec{" +
            "id=" + getId() +
            ", serviceSpecId='" + getServiceSpecId() + "'" +
            ", serviceSpecDesc='" + getServiceSpecDesc() + "'" +
            ", serviceId='" + getServiceId() + "'" +
            ", voiceSpecId=" + getVoiceSpecId() +
            ", dataSpecId=" + getDataSpecId() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
