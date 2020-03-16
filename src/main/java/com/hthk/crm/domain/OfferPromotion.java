package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import com.hthk.crm.domain.enumeration.RecordStatus;

/**
 * A OfferPromotion.
 */
@Document(collection = "offer_promotion")
public class OfferPromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("promo_code")
    private String promoCode;

    @NotNull
    @Field("offer_id")
    private String offerId;

    @Field("start_date")
    private Instant startDate;

    @Field("end_date")
    private Instant endDate;

    @Field("promo_type")
    private String promoType;

    @Field("offer_price")
    private BigDecimal offerPrice;

    @Field("offer_discount")
    private BigDecimal offerDiscount;

    @Field("free_data_offer_id")
    private String freeDataOfferId;

    @Field("status")
    private RecordStatus status;

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
    @Field("offer")
    @JsonIgnoreProperties("offerPromotions")
    private Offer offer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public OfferPromotion promoCode(String promoCode) {
        this.promoCode = promoCode;
        return this;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getOfferId() {
        return offerId;
    }

    public OfferPromotion offerId(String offerId) {
        this.offerId = offerId;
        return this;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public OfferPromotion startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public OfferPromotion endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getPromoType() {
        return promoType;
    }

    public OfferPromotion promoType(String promoType) {
        this.promoType = promoType;
        return this;
    }

    public void setPromoType(String promoType) {
        this.promoType = promoType;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public OfferPromotion offerPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
        return this;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public BigDecimal getOfferDiscount() {
        return offerDiscount;
    }

    public OfferPromotion offerDiscount(BigDecimal offerDiscount) {
        this.offerDiscount = offerDiscount;
        return this;
    }

    public void setOfferDiscount(BigDecimal offerDiscount) {
        this.offerDiscount = offerDiscount;
    }

    public String getFreeDataOfferId() {
        return freeDataOfferId;
    }

    public OfferPromotion freeDataOfferId(String freeDataOfferId) {
        this.freeDataOfferId = freeDataOfferId;
        return this;
    }

    public void setFreeDataOfferId(String freeDataOfferId) {
        this.freeDataOfferId = freeDataOfferId;
    }

    public RecordStatus getStatus() {
        return status;
    }

    public OfferPromotion status(RecordStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public OfferPromotion createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public OfferPromotion createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public OfferPromotion lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public OfferPromotion lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public OfferPromotion tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Offer getOffer() {
        return offer;
    }

    public OfferPromotion offer(Offer offer) {
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
        if (!(o instanceof OfferPromotion)) {
            return false;
        }
        return id != null && id.equals(((OfferPromotion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OfferPromotion{" +
            "id=" + getId() +
            ", promoCode='" + getPromoCode() + "'" +
            ", offerId='" + getOfferId() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", promoType='" + getPromoType() + "'" +
            ", offerPrice=" + getOfferPrice() +
            ", offerDiscount=" + getOfferDiscount() +
            ", freeDataOfferId='" + getFreeDataOfferId() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
