package com.hthk.crm.domain;

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
 * A SubsItemDelivery.
 */
@Document(collection = "subs_item_delivery")
public class SubsItemDelivery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("delivery_id")
    private Long deliveryId;

    @NotNull
    @Field("order_id")
    private String orderId;

    @NotNull
    @Field("subscription_id")
    private String subscriptionId;

    @NotNull
    @Field("subscription_item_id")
    private String subscriptionItemId;

    @NotNull
    @Field("delivery_status")
    private String deliveryStatus;

    @NotNull
    @Field("delivery_method_id")
    private String deliveryMethodId;

    @Field("delivery_ref_code")
    private String deliveryRefCode;

    @Field("file_generation_date")
    private Instant fileGenerationDate;

    @Field("file_received_date")
    private Instant fileReceivedDate;

    @Field("delivery_date")
    private Instant deliveryDate;

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
    @Field("subsDeliveryItemDetails")
    private Set<SubsDeliveryItemDetails> subsDeliveryItemDetails = new HashSet<>();

    @DBRef
    @Field("orderMaster")
    @JsonIgnoreProperties("subsItemDeliverys")
    private OrderMaster orderMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public SubsItemDelivery deliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
        return this;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getOrderId() {
        return orderId;
    }

    public SubsItemDelivery orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public SubsItemDelivery subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionItemId() {
        return subscriptionItemId;
    }

    public SubsItemDelivery subscriptionItemId(String subscriptionItemId) {
        this.subscriptionItemId = subscriptionItemId;
        return this;
    }

    public void setSubscriptionItemId(String subscriptionItemId) {
        this.subscriptionItemId = subscriptionItemId;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public SubsItemDelivery deliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryMethodId() {
        return deliveryMethodId;
    }

    public SubsItemDelivery deliveryMethodId(String deliveryMethodId) {
        this.deliveryMethodId = deliveryMethodId;
        return this;
    }

    public void setDeliveryMethodId(String deliveryMethodId) {
        this.deliveryMethodId = deliveryMethodId;
    }

    public String getDeliveryRefCode() {
        return deliveryRefCode;
    }

    public SubsItemDelivery deliveryRefCode(String deliveryRefCode) {
        this.deliveryRefCode = deliveryRefCode;
        return this;
    }

    public void setDeliveryRefCode(String deliveryRefCode) {
        this.deliveryRefCode = deliveryRefCode;
    }

    public Instant getFileGenerationDate() {
        return fileGenerationDate;
    }

    public SubsItemDelivery fileGenerationDate(Instant fileGenerationDate) {
        this.fileGenerationDate = fileGenerationDate;
        return this;
    }

    public void setFileGenerationDate(Instant fileGenerationDate) {
        this.fileGenerationDate = fileGenerationDate;
    }

    public Instant getFileReceivedDate() {
        return fileReceivedDate;
    }

    public SubsItemDelivery fileReceivedDate(Instant fileReceivedDate) {
        this.fileReceivedDate = fileReceivedDate;
        return this;
    }

    public void setFileReceivedDate(Instant fileReceivedDate) {
        this.fileReceivedDate = fileReceivedDate;
    }

    public Instant getDeliveryDate() {
        return deliveryDate;
    }

    public SubsItemDelivery deliveryDate(Instant deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public void setDeliveryDate(Instant deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public SubsItemDelivery remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public SubsItemDelivery createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public SubsItemDelivery createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public SubsItemDelivery lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public SubsItemDelivery lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public SubsItemDelivery tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Set<SubsDeliveryItemDetails> getSubsDeliveryItemDetails() {
        return subsDeliveryItemDetails;
    }

    public SubsItemDelivery subsDeliveryItemDetails(Set<SubsDeliveryItemDetails> subsDeliveryItemDetails) {
        this.subsDeliveryItemDetails = subsDeliveryItemDetails;
        return this;
    }

    public SubsItemDelivery addSubsDeliveryItemDetails(SubsDeliveryItemDetails subsDeliveryItemDetails) {
        this.subsDeliveryItemDetails.add(subsDeliveryItemDetails);
        subsDeliveryItemDetails.setSubsItemDelivery(this);
        return this;
    }

    public SubsItemDelivery removeSubsDeliveryItemDetails(SubsDeliveryItemDetails subsDeliveryItemDetails) {
        this.subsDeliveryItemDetails.remove(subsDeliveryItemDetails);
        subsDeliveryItemDetails.setSubsItemDelivery(null);
        return this;
    }

    public void setSubsDeliveryItemDetails(Set<SubsDeliveryItemDetails> subsDeliveryItemDetails) {
        this.subsDeliveryItemDetails = subsDeliveryItemDetails;
    }

    public OrderMaster getOrderMaster() {
        return orderMaster;
    }

    public SubsItemDelivery orderMaster(OrderMaster orderMaster) {
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
        if (!(o instanceof SubsItemDelivery)) {
            return false;
        }
        return id != null && id.equals(((SubsItemDelivery) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SubsItemDelivery{" +
            "id=" + getId() +
            ", deliveryId=" + getDeliveryId() +
            ", orderId='" + getOrderId() + "'" +
            ", subscriptionId='" + getSubscriptionId() + "'" +
            ", subscriptionItemId='" + getSubscriptionItemId() + "'" +
            ", deliveryStatus='" + getDeliveryStatus() + "'" +
            ", deliveryMethodId='" + getDeliveryMethodId() + "'" +
            ", deliveryRefCode='" + getDeliveryRefCode() + "'" +
            ", fileGenerationDate='" + getFileGenerationDate() + "'" +
            ", fileReceivedDate='" + getFileReceivedDate() + "'" +
            ", deliveryDate='" + getDeliveryDate() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
