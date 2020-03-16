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
 * A SubsDeliveryItemDetails.
 */
@Document(collection = "subs_delivery_item_details")
public class SubsDeliveryItemDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("subscription_item_id")
    private Long subscriptionItemId;

    @NotNull
    @Field("delivery_id")
    private Long deliveryId;

    @NotNull
    @Field("product_id")
    private String productId;

    @Field("product_name")
    private String productName;

    @Field("device_type")
    private String deviceType;

    @Field("device_model")
    private String deviceModel;

    @Field("device_serial_nbr")
    private String deviceSerialNbr;

    @Field("imei")
    private String imei;

    @Field("product_theme")
    private String productTheme;

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
    @Field("subsItemDelivery")
    @JsonIgnoreProperties("subsDeliveryItemDetails")
    private SubsItemDelivery subsItemDelivery;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSubscriptionItemId() {
        return subscriptionItemId;
    }

    public SubsDeliveryItemDetails subscriptionItemId(Long subscriptionItemId) {
        this.subscriptionItemId = subscriptionItemId;
        return this;
    }

    public void setSubscriptionItemId(Long subscriptionItemId) {
        this.subscriptionItemId = subscriptionItemId;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public SubsDeliveryItemDetails deliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
        return this;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getProductId() {
        return productId;
    }

    public SubsDeliveryItemDetails productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public SubsDeliveryItemDetails productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public SubsDeliveryItemDetails deviceType(String deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public SubsDeliveryItemDetails deviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
        return this;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceSerialNbr() {
        return deviceSerialNbr;
    }

    public SubsDeliveryItemDetails deviceSerialNbr(String deviceSerialNbr) {
        this.deviceSerialNbr = deviceSerialNbr;
        return this;
    }

    public void setDeviceSerialNbr(String deviceSerialNbr) {
        this.deviceSerialNbr = deviceSerialNbr;
    }

    public String getImei() {
        return imei;
    }

    public SubsDeliveryItemDetails imei(String imei) {
        this.imei = imei;
        return this;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getProductTheme() {
        return productTheme;
    }

    public SubsDeliveryItemDetails productTheme(String productTheme) {
        this.productTheme = productTheme;
        return this;
    }

    public void setProductTheme(String productTheme) {
        this.productTheme = productTheme;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public SubsDeliveryItemDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public SubsDeliveryItemDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public SubsDeliveryItemDetails lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public SubsDeliveryItemDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public SubsDeliveryItemDetails tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public SubsItemDelivery getSubsItemDelivery() {
        return subsItemDelivery;
    }

    public SubsDeliveryItemDetails subsItemDelivery(SubsItemDelivery subsItemDelivery) {
        this.subsItemDelivery = subsItemDelivery;
        return this;
    }

    public void setSubsItemDelivery(SubsItemDelivery subsItemDelivery) {
        this.subsItemDelivery = subsItemDelivery;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubsDeliveryItemDetails)) {
            return false;
        }
        return id != null && id.equals(((SubsDeliveryItemDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SubsDeliveryItemDetails{" +
            "id=" + getId() +
            ", subscriptionItemId=" + getSubscriptionItemId() +
            ", deliveryId=" + getDeliveryId() +
            ", productId='" + getProductId() + "'" +
            ", productName='" + getProductName() + "'" +
            ", deviceType='" + getDeviceType() + "'" +
            ", deviceModel='" + getDeviceModel() + "'" +
            ", deviceSerialNbr='" + getDeviceSerialNbr() + "'" +
            ", imei='" + getImei() + "'" +
            ", productTheme='" + getProductTheme() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
