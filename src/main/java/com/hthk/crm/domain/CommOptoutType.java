package com.hthk.crm.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A CommOptoutType.
 */
@Document(collection = "comm_optout_type")
public class CommOptoutType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("optout_type_id")
    private String optoutTypeId;

    @NotNull
    @Field("optout_type")
    private String optoutType;

    @Field("optout_type_desc")
    private String optoutTypeDesc;

    @Field("start_date")
    private Instant startDate;

    @Field("end_date")
    private Instant endDate;

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

    public String getOptoutTypeId() {
        return optoutTypeId;
    }

    public CommOptoutType optoutTypeId(String optoutTypeId) {
        this.optoutTypeId = optoutTypeId;
        return this;
    }

    public void setOptoutTypeId(String optoutTypeId) {
        this.optoutTypeId = optoutTypeId;
    }

    public String getOptoutType() {
        return optoutType;
    }

    public CommOptoutType optoutType(String optoutType) {
        this.optoutType = optoutType;
        return this;
    }

    public void setOptoutType(String optoutType) {
        this.optoutType = optoutType;
    }

    public String getOptoutTypeDesc() {
        return optoutTypeDesc;
    }

    public CommOptoutType optoutTypeDesc(String optoutTypeDesc) {
        this.optoutTypeDesc = optoutTypeDesc;
        return this;
    }

    public void setOptoutTypeDesc(String optoutTypeDesc) {
        this.optoutTypeDesc = optoutTypeDesc;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public CommOptoutType startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public CommOptoutType endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public CommOptoutType createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CommOptoutType createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public CommOptoutType lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public CommOptoutType lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public CommOptoutType tenantId(String tenantId) {
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
        if (!(o instanceof CommOptoutType)) {
            return false;
        }
        return id != null && id.equals(((CommOptoutType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CommOptoutType{" +
            "id=" + getId() +
            ", optoutTypeId='" + getOptoutTypeId() + "'" +
            ", optoutType='" + getOptoutType() + "'" +
            ", optoutTypeDesc='" + getOptoutTypeDesc() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
