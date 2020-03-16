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
 * A OfferAdvancePayment.
 */
@Document(collection = "offer_advance_payment")
public class OfferAdvancePayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("advance_payment_id")
    private Long advancePaymentId;

    @NotNull
    @Field("offer_id")
    private String offerId;

    @NotNull
    @Field("advance_payment_months")
    private Integer advancePaymentMonths;

    @Field("offer_price")
    private BigDecimal offerPrice;

    @Field("offer_discount")
    private BigDecimal offerDiscount;

    @NotNull
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
    @JsonIgnoreProperties("offerAdvancePayments")
    private Offer offer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAdvancePaymentId() {
        return advancePaymentId;
    }

    public OfferAdvancePayment advancePaymentId(Long advancePaymentId) {
        this.advancePaymentId = advancePaymentId;
        return this;
    }

    public void setAdvancePaymentId(Long advancePaymentId) {
        this.advancePaymentId = advancePaymentId;
    }

    public String getOfferId() {
        return offerId;
    }

    public OfferAdvancePayment offerId(String offerId) {
        this.offerId = offerId;
        return this;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public Integer getAdvancePaymentMonths() {
        return advancePaymentMonths;
    }

    public OfferAdvancePayment advancePaymentMonths(Integer advancePaymentMonths) {
        this.advancePaymentMonths = advancePaymentMonths;
        return this;
    }

    public void setAdvancePaymentMonths(Integer advancePaymentMonths) {
        this.advancePaymentMonths = advancePaymentMonths;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public OfferAdvancePayment offerPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
        return this;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public BigDecimal getOfferDiscount() {
        return offerDiscount;
    }

    public OfferAdvancePayment offerDiscount(BigDecimal offerDiscount) {
        this.offerDiscount = offerDiscount;
        return this;
    }

    public void setOfferDiscount(BigDecimal offerDiscount) {
        this.offerDiscount = offerDiscount;
    }

    public RecordStatus getStatus() {
        return status;
    }

    public OfferAdvancePayment status(RecordStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public OfferAdvancePayment createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public OfferAdvancePayment createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public OfferAdvancePayment lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public OfferAdvancePayment lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public OfferAdvancePayment tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Offer getOffer() {
        return offer;
    }

    public OfferAdvancePayment offer(Offer offer) {
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
        if (!(o instanceof OfferAdvancePayment)) {
            return false;
        }
        return id != null && id.equals(((OfferAdvancePayment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OfferAdvancePayment{" +
            "id=" + getId() +
            ", advancePaymentId=" + getAdvancePaymentId() +
            ", offerId='" + getOfferId() + "'" +
            ", advancePaymentMonths=" + getAdvancePaymentMonths() +
            ", offerPrice=" + getOfferPrice() +
            ", offerDiscount=" + getOfferDiscount() +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
