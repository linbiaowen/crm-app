package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A SubscriptionProvision.
 */
@Document(collection = "subscription_provision")
public class SubscriptionProvision implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("provision_seq_id")
    private String provisionSeqId;

    @NotNull
    @Field("subscription_id")
    private String subscriptionId;

    @NotNull
    @Field("order_id")
    private String orderId;

    @NotNull
    @Field("product_id")
    private String productId;

    @NotNull
    @Field("msisdn")
    private String msisdn;

    @NotNull
    @Field("iccid")
    private String iccid;

    @Field("imsi")
    private String imsi;

    @NotNull
    @Field("service_spec_id")
    private String serviceSpecId;

    @NotNull
    @Field("resource_spec_id")
    private String resourceSpecId;

    @NotNull
    @Field("rfs")
    private String rfs;

    @NotNull
    @Field("provision_status")
    private String provisionStatus;

    @Field("provision_status_desc")
    private String provisionStatusDesc;

    @Field("provision_response")
    private String provisionResponse;

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

    @DBRef
    @Field("orderMaster")
    @JsonIgnoreProperties("subscriptionProvisions")
    private OrderMaster orderMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvisionSeqId() {
        return provisionSeqId;
    }

    public SubscriptionProvision provisionSeqId(String provisionSeqId) {
        this.provisionSeqId = provisionSeqId;
        return this;
    }

    public void setProvisionSeqId(String provisionSeqId) {
        this.provisionSeqId = provisionSeqId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public SubscriptionProvision subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public SubscriptionProvision orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public SubscriptionProvision productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public SubscriptionProvision msisdn(String msisdn) {
        this.msisdn = msisdn;
        return this;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getIccid() {
        return iccid;
    }

    public SubscriptionProvision iccid(String iccid) {
        this.iccid = iccid;
        return this;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getImsi() {
        return imsi;
    }

    public SubscriptionProvision imsi(String imsi) {
        this.imsi = imsi;
        return this;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getServiceSpecId() {
        return serviceSpecId;
    }

    public SubscriptionProvision serviceSpecId(String serviceSpecId) {
        this.serviceSpecId = serviceSpecId;
        return this;
    }

    public void setServiceSpecId(String serviceSpecId) {
        this.serviceSpecId = serviceSpecId;
    }

    public String getResourceSpecId() {
        return resourceSpecId;
    }

    public SubscriptionProvision resourceSpecId(String resourceSpecId) {
        this.resourceSpecId = resourceSpecId;
        return this;
    }

    public void setResourceSpecId(String resourceSpecId) {
        this.resourceSpecId = resourceSpecId;
    }

    public String getRfs() {
        return rfs;
    }

    public SubscriptionProvision rfs(String rfs) {
        this.rfs = rfs;
        return this;
    }

    public void setRfs(String rfs) {
        this.rfs = rfs;
    }

    public String getProvisionStatus() {
        return provisionStatus;
    }

    public SubscriptionProvision provisionStatus(String provisionStatus) {
        this.provisionStatus = provisionStatus;
        return this;
    }

    public void setProvisionStatus(String provisionStatus) {
        this.provisionStatus = provisionStatus;
    }

    public String getProvisionStatusDesc() {
        return provisionStatusDesc;
    }

    public SubscriptionProvision provisionStatusDesc(String provisionStatusDesc) {
        this.provisionStatusDesc = provisionStatusDesc;
        return this;
    }

    public void setProvisionStatusDesc(String provisionStatusDesc) {
        this.provisionStatusDesc = provisionStatusDesc;
    }

    public String getProvisionResponse() {
        return provisionResponse;
    }

    public SubscriptionProvision provisionResponse(String provisionResponse) {
        this.provisionResponse = provisionResponse;
        return this;
    }

    public void setProvisionResponse(String provisionResponse) {
        this.provisionResponse = provisionResponse;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public SubscriptionProvision startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public SubscriptionProvision endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public SubscriptionProvision createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public SubscriptionProvision createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public SubscriptionProvision lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public SubscriptionProvision lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public SubscriptionProvision tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public OrderMaster getOrderMaster() {
        return orderMaster;
    }

    public SubscriptionProvision orderMaster(OrderMaster orderMaster) {
        this.orderMaster = orderMaster;
        return this;
    }

    public void setOrderMaster(OrderMaster orderMaster) {
        this.orderMaster = orderMaster;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubscriptionProvision)) {
            return false;
        }
        return id != null && id.equals(((SubscriptionProvision) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SubscriptionProvision{" +
            "id=" + getId() +
            ", provisionSeqId='" + getProvisionSeqId() + "'" +
            ", subscriptionId='" + getSubscriptionId() + "'" +
            ", orderId='" + getOrderId() + "'" +
            ", productId='" + getProductId() + "'" +
            ", msisdn='" + getMsisdn() + "'" +
            ", iccid='" + getIccid() + "'" +
            ", imsi='" + getImsi() + "'" +
            ", serviceSpecId='" + getServiceSpecId() + "'" +
            ", resourceSpecId='" + getResourceSpecId() + "'" +
            ", rfs='" + getRfs() + "'" +
            ", provisionStatus='" + getProvisionStatus() + "'" +
            ", provisionStatusDesc='" + getProvisionStatusDesc() + "'" +
            ", provisionResponse='" + getProvisionResponse() + "'" +
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
