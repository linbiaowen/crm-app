package com.hthk.crm.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.hthk.crm.domain.enumeration.SimType;

/**
 * A ProductSimAttributes.
 */
@Document(collection = "product_sim_attributes")
public class ProductSimAttributes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("sim_type")
    private SimType simType;

    @Field("imsi_range_from")
    private String imsiRangeFrom;

    @Field("imsi_range_to")
    private String imsiRangeTo;

    @Field("sim_repository_ref")
    private String simRepositoryRef;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SimType getSimType() {
        return simType;
    }

    public ProductSimAttributes simType(SimType simType) {
        this.simType = simType;
        return this;
    }

    public void setSimType(SimType simType) {
        this.simType = simType;
    }

    public String getImsiRangeFrom() {
        return imsiRangeFrom;
    }

    public ProductSimAttributes imsiRangeFrom(String imsiRangeFrom) {
        this.imsiRangeFrom = imsiRangeFrom;
        return this;
    }

    public void setImsiRangeFrom(String imsiRangeFrom) {
        this.imsiRangeFrom = imsiRangeFrom;
    }

    public String getImsiRangeTo() {
        return imsiRangeTo;
    }

    public ProductSimAttributes imsiRangeTo(String imsiRangeTo) {
        this.imsiRangeTo = imsiRangeTo;
        return this;
    }

    public void setImsiRangeTo(String imsiRangeTo) {
        this.imsiRangeTo = imsiRangeTo;
    }

    public String getSimRepositoryRef() {
        return simRepositoryRef;
    }

    public ProductSimAttributes simRepositoryRef(String simRepositoryRef) {
        this.simRepositoryRef = simRepositoryRef;
        return this;
    }

    public void setSimRepositoryRef(String simRepositoryRef) {
        this.simRepositoryRef = simRepositoryRef;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ProductSimAttributes createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ProductSimAttributes createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public ProductSimAttributes lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public ProductSimAttributes lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public ProductSimAttributes tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductSimAttributes)) {
            return false;
        }
        return id != null && id.equals(((ProductSimAttributes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProductSimAttributes{" +
            "id=" + getId() +
            ", simType='" + getSimType() + "'" +
            ", imsiRangeFrom='" + getImsiRangeFrom() + "'" +
            ", imsiRangeTo='" + getImsiRangeTo() + "'" +
            ", simRepositoryRef='" + getSimRepositoryRef() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
