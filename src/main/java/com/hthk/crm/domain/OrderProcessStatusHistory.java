package com.hthk.crm.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.hthk.crm.domain.enumeration.OrderStatus;

import com.hthk.crm.domain.enumeration.ProcessStatus;

/**
 * A OrderProcessStatusHistory.
 */
@Document(collection = "order_process_status_history")
public class OrderProcessStatusHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("order_id")
    private String orderId;

    @NotNull
    @Field("entry_order_status")
    private OrderStatus entryOrderStatus;

    @NotNull
    @Field("exit_order_status")
    private OrderStatus exitOrderStatus;

    @NotNull
    @Field("status_updated_date")
    private Instant statusUpdatedDate;

    @Field("process_name")
    private String processName;

    @Field("status")
    private ProcessStatus status;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderProcessStatusHistory orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getEntryOrderStatus() {
        return entryOrderStatus;
    }

    public OrderProcessStatusHistory entryOrderStatus(OrderStatus entryOrderStatus) {
        this.entryOrderStatus = entryOrderStatus;
        return this;
    }

    public void setEntryOrderStatus(OrderStatus entryOrderStatus) {
        this.entryOrderStatus = entryOrderStatus;
    }

    public OrderStatus getExitOrderStatus() {
        return exitOrderStatus;
    }

    public OrderProcessStatusHistory exitOrderStatus(OrderStatus exitOrderStatus) {
        this.exitOrderStatus = exitOrderStatus;
        return this;
    }

    public void setExitOrderStatus(OrderStatus exitOrderStatus) {
        this.exitOrderStatus = exitOrderStatus;
    }

    public Instant getStatusUpdatedDate() {
        return statusUpdatedDate;
    }

    public OrderProcessStatusHistory statusUpdatedDate(Instant statusUpdatedDate) {
        this.statusUpdatedDate = statusUpdatedDate;
        return this;
    }

    public void setStatusUpdatedDate(Instant statusUpdatedDate) {
        this.statusUpdatedDate = statusUpdatedDate;
    }

    public String getProcessName() {
        return processName;
    }

    public OrderProcessStatusHistory processName(String processName) {
        this.processName = processName;
        return this;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public ProcessStatus getStatus() {
        return status;
    }

    public OrderProcessStatusHistory status(ProcessStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(ProcessStatus status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public OrderProcessStatusHistory remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public OrderProcessStatusHistory createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public OrderProcessStatusHistory createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public OrderProcessStatusHistory lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public OrderProcessStatusHistory lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public OrderProcessStatusHistory tenantId(String tenantId) {
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
        if (!(o instanceof OrderProcessStatusHistory)) {
            return false;
        }
        return id != null && id.equals(((OrderProcessStatusHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrderProcessStatusHistory{" +
            "id=" + getId() +
            ", orderId='" + getOrderId() + "'" +
            ", entryOrderStatus='" + getEntryOrderStatus() + "'" +
            ", exitOrderStatus='" + getExitOrderStatus() + "'" +
            ", statusUpdatedDate='" + getStatusUpdatedDate() + "'" +
            ", processName='" + getProcessName() + "'" +
            ", status='" + getStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
