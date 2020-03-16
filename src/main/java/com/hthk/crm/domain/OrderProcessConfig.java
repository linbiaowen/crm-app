package com.hthk.crm.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A OrderProcessConfig.
 */
@Document(collection = "order_process_config")
public class OrderProcessConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("order_type")
    private String orderType;

    @NotNull
    @Field("sub_order_type")
    private String subOrderType;

    @Field("order_nature")
    private String orderNature;

    @Field("process_name")
    private String processName;

    @Field("child_process_name")
    private String childProcessName;

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

    public String getOrderType() {
        return orderType;
    }

    public OrderProcessConfig orderType(String orderType) {
        this.orderType = orderType;
        return this;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getSubOrderType() {
        return subOrderType;
    }

    public OrderProcessConfig subOrderType(String subOrderType) {
        this.subOrderType = subOrderType;
        return this;
    }

    public void setSubOrderType(String subOrderType) {
        this.subOrderType = subOrderType;
    }

    public String getOrderNature() {
        return orderNature;
    }

    public OrderProcessConfig orderNature(String orderNature) {
        this.orderNature = orderNature;
        return this;
    }

    public void setOrderNature(String orderNature) {
        this.orderNature = orderNature;
    }

    public String getProcessName() {
        return processName;
    }

    public OrderProcessConfig processName(String processName) {
        this.processName = processName;
        return this;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getChildProcessName() {
        return childProcessName;
    }

    public OrderProcessConfig childProcessName(String childProcessName) {
        this.childProcessName = childProcessName;
        return this;
    }

    public void setChildProcessName(String childProcessName) {
        this.childProcessName = childProcessName;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public OrderProcessConfig createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public OrderProcessConfig createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public OrderProcessConfig lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public OrderProcessConfig lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public OrderProcessConfig tenantId(String tenantId) {
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
        if (!(o instanceof OrderProcessConfig)) {
            return false;
        }
        return id != null && id.equals(((OrderProcessConfig) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrderProcessConfig{" +
            "id=" + getId() +
            ", orderType='" + getOrderType() + "'" +
            ", subOrderType='" + getSubOrderType() + "'" +
            ", orderNature='" + getOrderNature() + "'" +
            ", processName='" + getProcessName() + "'" +
            ", childProcessName='" + getChildProcessName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
