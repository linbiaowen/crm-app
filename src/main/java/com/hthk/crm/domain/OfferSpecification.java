package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A OfferSpecification.
 */
@Document(collection = "offer_specification")
public class OfferSpecification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("offer_spec_id")
    private Long offerSpecId;

    @NotNull
    @Field("offer_id")
    private String offerId;

    @Field("start_date")
    private Instant startDate;

    @Field("end_date")
    private Instant endDate;

    @Field("limited_activation_period")
    private Boolean limitedActivationPeriod;

    @Field("allowed_activation_start_date")
    private Instant allowedActivationStartDate;

    @Field("allowed_activation_end_date")
    private Instant allowedActivationEndDate;

    @Field("is_group_sharing_offer")
    private Boolean isGroupSharingOffer;

    @Field("is_mnp_offer")
    private Boolean isMnpOffer;

    @Field("auto_renewal")
    private Boolean autoRenewal;

    @Field("transfer_allowed")
    private Boolean transferAllowed;

    @Field("info_sharing_allowed")
    private Boolean infoSharingAllowed;

    @Field("info_sharing_options")
    private String infoSharingOptions;

    @Field("offer_period")
    private Integer offerPeriod;

    @Field("offer_period_term")
    private String offerPeriodTerm;

    @Field("payment_type")
    private String paymentType;

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
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Offer offer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getOfferSpecId() {
        return offerSpecId;
    }

    public OfferSpecification offerSpecId(Long offerSpecId) {
        this.offerSpecId = offerSpecId;
        return this;
    }

    public void setOfferSpecId(Long offerSpecId) {
        this.offerSpecId = offerSpecId;
    }

    public String getOfferId() {
        return offerId;
    }

    public OfferSpecification offerId(String offerId) {
        this.offerId = offerId;
        return this;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public OfferSpecification startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public OfferSpecification endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Boolean isLimitedActivationPeriod() {
        return limitedActivationPeriod;
    }

    public OfferSpecification limitedActivationPeriod(Boolean limitedActivationPeriod) {
        this.limitedActivationPeriod = limitedActivationPeriod;
        return this;
    }

    public void setLimitedActivationPeriod(Boolean limitedActivationPeriod) {
        this.limitedActivationPeriod = limitedActivationPeriod;
    }

    public Instant getAllowedActivationStartDate() {
        return allowedActivationStartDate;
    }

    public OfferSpecification allowedActivationStartDate(Instant allowedActivationStartDate) {
        this.allowedActivationStartDate = allowedActivationStartDate;
        return this;
    }

    public void setAllowedActivationStartDate(Instant allowedActivationStartDate) {
        this.allowedActivationStartDate = allowedActivationStartDate;
    }

    public Instant getAllowedActivationEndDate() {
        return allowedActivationEndDate;
    }

    public OfferSpecification allowedActivationEndDate(Instant allowedActivationEndDate) {
        this.allowedActivationEndDate = allowedActivationEndDate;
        return this;
    }

    public void setAllowedActivationEndDate(Instant allowedActivationEndDate) {
        this.allowedActivationEndDate = allowedActivationEndDate;
    }

    public Boolean isIsGroupSharingOffer() {
        return isGroupSharingOffer;
    }

    public OfferSpecification isGroupSharingOffer(Boolean isGroupSharingOffer) {
        this.isGroupSharingOffer = isGroupSharingOffer;
        return this;
    }

    public void setIsGroupSharingOffer(Boolean isGroupSharingOffer) {
        this.isGroupSharingOffer = isGroupSharingOffer;
    }

    public Boolean isIsMnpOffer() {
        return isMnpOffer;
    }

    public OfferSpecification isMnpOffer(Boolean isMnpOffer) {
        this.isMnpOffer = isMnpOffer;
        return this;
    }

    public void setIsMnpOffer(Boolean isMnpOffer) {
        this.isMnpOffer = isMnpOffer;
    }

    public Boolean isAutoRenewal() {
        return autoRenewal;
    }

    public OfferSpecification autoRenewal(Boolean autoRenewal) {
        this.autoRenewal = autoRenewal;
        return this;
    }

    public void setAutoRenewal(Boolean autoRenewal) {
        this.autoRenewal = autoRenewal;
    }

    public Boolean isTransferAllowed() {
        return transferAllowed;
    }

    public OfferSpecification transferAllowed(Boolean transferAllowed) {
        this.transferAllowed = transferAllowed;
        return this;
    }

    public void setTransferAllowed(Boolean transferAllowed) {
        this.transferAllowed = transferAllowed;
    }

    public Boolean isInfoSharingAllowed() {
        return infoSharingAllowed;
    }

    public OfferSpecification infoSharingAllowed(Boolean infoSharingAllowed) {
        this.infoSharingAllowed = infoSharingAllowed;
        return this;
    }

    public void setInfoSharingAllowed(Boolean infoSharingAllowed) {
        this.infoSharingAllowed = infoSharingAllowed;
    }

    public String getInfoSharingOptions() {
        return infoSharingOptions;
    }

    public OfferSpecification infoSharingOptions(String infoSharingOptions) {
        this.infoSharingOptions = infoSharingOptions;
        return this;
    }

    public void setInfoSharingOptions(String infoSharingOptions) {
        this.infoSharingOptions = infoSharingOptions;
    }

    public Integer getOfferPeriod() {
        return offerPeriod;
    }

    public OfferSpecification offerPeriod(Integer offerPeriod) {
        this.offerPeriod = offerPeriod;
        return this;
    }

    public void setOfferPeriod(Integer offerPeriod) {
        this.offerPeriod = offerPeriod;
    }

    public String getOfferPeriodTerm() {
        return offerPeriodTerm;
    }

    public OfferSpecification offerPeriodTerm(String offerPeriodTerm) {
        this.offerPeriodTerm = offerPeriodTerm;
        return this;
    }

    public void setOfferPeriodTerm(String offerPeriodTerm) {
        this.offerPeriodTerm = offerPeriodTerm;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public OfferSpecification paymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public OfferSpecification createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public OfferSpecification createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public OfferSpecification lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public OfferSpecification lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public OfferSpecification tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Offer getOffer() {
        return offer;
    }

    public OfferSpecification offer(Offer offer) {
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
        if (!(o instanceof OfferSpecification)) {
            return false;
        }
        return id != null && id.equals(((OfferSpecification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OfferSpecification{" +
            "id=" + getId() +
            ", offerSpecId=" + getOfferSpecId() +
            ", offerId='" + getOfferId() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", limitedActivationPeriod='" + isLimitedActivationPeriod() + "'" +
            ", allowedActivationStartDate='" + getAllowedActivationStartDate() + "'" +
            ", allowedActivationEndDate='" + getAllowedActivationEndDate() + "'" +
            ", isGroupSharingOffer='" + isIsGroupSharingOffer() + "'" +
            ", isMnpOffer='" + isIsMnpOffer() + "'" +
            ", autoRenewal='" + isAutoRenewal() + "'" +
            ", transferAllowed='" + isTransferAllowed() + "'" +
            ", infoSharingAllowed='" + isInfoSharingAllowed() + "'" +
            ", infoSharingOptions='" + getInfoSharingOptions() + "'" +
            ", offerPeriod=" + getOfferPeriod() +
            ", offerPeriodTerm='" + getOfferPeriodTerm() + "'" +
            ", paymentType='" + getPaymentType() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
