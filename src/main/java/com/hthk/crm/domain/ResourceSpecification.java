package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.hthk.crm.domain.enumeration.ResourceType;

/**
 * A ResourceSpecification.
 */
@Document(collection = "resource_specification")
public class ResourceSpecification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("resource_spec_id")
    private String resourceSpecId;

    @NotNull
    @Field("resource_type")
    private ResourceType resourceType;

    @Field("service_spec_id")
    private String serviceSpecId;

    @NotNull
    @Field("rfs")
    private String rfs;

    @Field("rfs_parms")
    private String rfsParms;

    @Field("remarks")
    private String remarks;

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
    @Field("cfsServiceSpec")
    @JsonIgnoreProperties("resourceSpecifications")
    private CfsServiceSpec cfsServiceSpec;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceSpecId() {
        return resourceSpecId;
    }

    public ResourceSpecification resourceSpecId(String resourceSpecId) {
        this.resourceSpecId = resourceSpecId;
        return this;
    }

    public void setResourceSpecId(String resourceSpecId) {
        this.resourceSpecId = resourceSpecId;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public ResourceSpecification resourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public String getServiceSpecId() {
        return serviceSpecId;
    }

    public ResourceSpecification serviceSpecId(String serviceSpecId) {
        this.serviceSpecId = serviceSpecId;
        return this;
    }

    public void setServiceSpecId(String serviceSpecId) {
        this.serviceSpecId = serviceSpecId;
    }

    public String getRfs() {
        return rfs;
    }

    public ResourceSpecification rfs(String rfs) {
        this.rfs = rfs;
        return this;
    }

    public void setRfs(String rfs) {
        this.rfs = rfs;
    }

    public String getRfsParms() {
        return rfsParms;
    }

    public ResourceSpecification rfsParms(String rfsParms) {
        this.rfsParms = rfsParms;
        return this;
    }

    public void setRfsParms(String rfsParms) {
        this.rfsParms = rfsParms;
    }

    public String getRemarks() {
        return remarks;
    }

    public ResourceSpecification remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ResourceSpecification createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ResourceSpecification createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public ResourceSpecification lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public ResourceSpecification lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public ResourceSpecification tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public CfsServiceSpec getCfsServiceSpec() {
        return cfsServiceSpec;
    }

    public ResourceSpecification cfsServiceSpec(CfsServiceSpec cfsServiceSpec) {
        this.cfsServiceSpec = cfsServiceSpec;
        return this;
    }

    public void setCfsServiceSpec(CfsServiceSpec cfsServiceSpec) {
        this.cfsServiceSpec = cfsServiceSpec;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResourceSpecification)) {
            return false;
        }
        return id != null && id.equals(((ResourceSpecification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ResourceSpecification{" +
            "id=" + getId() +
            ", resourceSpecId='" + getResourceSpecId() + "'" +
            ", resourceType='" + getResourceType() + "'" +
            ", serviceSpecId='" + getServiceSpecId() + "'" +
            ", rfs='" + getRfs() + "'" +
            ", rfsParms='" + getRfsParms() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
