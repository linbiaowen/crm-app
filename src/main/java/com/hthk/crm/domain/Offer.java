package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.hthk.crm.domain.enumeration.OfferType;

/**
 * A Offer.
 */
@Document(collection = "offer")
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("offer_id")
    private String offerId;

    @Field("offer_external_id")
    private String offerExternalId;

    @NotNull
    @Field("offer_name")
    private String offerName;

    @Field("offer_name_chi")
    private String offerNameChi;

    @Field("offer_type")
    private OfferType offerType;

    @Field("offer_price")
    private BigDecimal offerPrice;

    @Field("customer_segments")
    private String customerSegments;

    @Field("customer_classes")
    private String customerClasses;

    @Field("sales_channels")
    private String salesChannels;

    @Field("start_date")
    private Instant startDate;

    @Field("end_date")
    private Instant endDate;

    @Field("child_offer_ids")
    private String childOfferIds;

    @Field("product_spec_ids")
    private String productSpecIds;

    @Field("advance_payment_ids")
    private String advancePaymentIds;

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
    @Field("offerSpec")
    private OfferSpecification offerSpec;

    @DBRef
    @Field("offerProducts")
    private Set<OfferProduct> offerProducts = new HashSet<>();

    @DBRef
    @Field("productSpecifications")
    private Set<ProductSpecification> productSpecifications = new HashSet<>();

    @DBRef
    @Field("offerAdvancePayment")
    private Set<OfferAdvancePayment> offerAdvancePayments = new HashSet<>();

    @DBRef
    @Field("offerPromotions")
    private Set<OfferPromotion> offerPromotions = new HashSet<>();

    @DBRef
    @Field("offerDiscounts")
    private Set<OfferDiscount> offerDiscounts = new HashSet<>();

    @DBRef
    @Field("parentOffers")
    private Set<Offer> parentOffers = new HashSet<>();

    @DBRef
    @Field("childOffers")
    @JsonIgnore
    private Set<Offer> childOffers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferId() {
        return offerId;
    }

    public Offer offerId(String offerId) {
        this.offerId = offerId;
        return this;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferExternalId() {
        return offerExternalId;
    }

    public Offer offerExternalId(String offerExternalId) {
        this.offerExternalId = offerExternalId;
        return this;
    }

    public void setOfferExternalId(String offerExternalId) {
        this.offerExternalId = offerExternalId;
    }

    public String getOfferName() {
        return offerName;
    }

    public Offer offerName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferNameChi() {
        return offerNameChi;
    }

    public Offer offerNameChi(String offerNameChi) {
        this.offerNameChi = offerNameChi;
        return this;
    }

    public void setOfferNameChi(String offerNameChi) {
        this.offerNameChi = offerNameChi;
    }

    public OfferType getOfferType() {
        return offerType;
    }

    public Offer offerType(OfferType offerType) {
        this.offerType = offerType;
        return this;
    }

    public void setOfferType(OfferType offerType) {
        this.offerType = offerType;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public Offer offerPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
        return this;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getCustomerSegments() {
        return customerSegments;
    }

    public Offer customerSegments(String customerSegments) {
        this.customerSegments = customerSegments;
        return this;
    }

    public void setCustomerSegments(String customerSegments) {
        this.customerSegments = customerSegments;
    }

    public String getCustomerClasses() {
        return customerClasses;
    }

    public Offer customerClasses(String customerClasses) {
        this.customerClasses = customerClasses;
        return this;
    }

    public void setCustomerClasses(String customerClasses) {
        this.customerClasses = customerClasses;
    }

    public String getSalesChannels() {
        return salesChannels;
    }

    public Offer salesChannels(String salesChannels) {
        this.salesChannels = salesChannels;
        return this;
    }

    public void setSalesChannels(String salesChannels) {
        this.salesChannels = salesChannels;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Offer startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Offer endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getChildOfferIds() {
        return childOfferIds;
    }

    public Offer childOfferIds(String childOfferIds) {
        this.childOfferIds = childOfferIds;
        return this;
    }

    public void setChildOfferIds(String childOfferIds) {
        this.childOfferIds = childOfferIds;
    }

    public String getProductSpecIds() {
        return productSpecIds;
    }

    public Offer productSpecIds(String productSpecIds) {
        this.productSpecIds = productSpecIds;
        return this;
    }

    public void setProductSpecIds(String productSpecIds) {
        this.productSpecIds = productSpecIds;
    }

    public String getAdvancePaymentIds() {
        return advancePaymentIds;
    }

    public Offer advancePaymentIds(String advancePaymentIds) {
        this.advancePaymentIds = advancePaymentIds;
        return this;
    }

    public void setAdvancePaymentIds(String advancePaymentIds) {
        this.advancePaymentIds = advancePaymentIds;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Offer createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Offer createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public Offer lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Offer lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public Offer tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public OfferSpecification getOfferSpec() {
        return offerSpec;
    }

    public Offer offerSpec(OfferSpecification offerSpecification) {
        this.offerSpec = offerSpecification;
        return this;
    }

    public void setOfferSpec(OfferSpecification offerSpecification) {
        this.offerSpec = offerSpecification;
    }

    public Set<OfferProduct> getOfferProducts() {
        return offerProducts;
    }

    public Offer offerProducts(Set<OfferProduct> offerProducts) {
        this.offerProducts = offerProducts;
        return this;
    }

    public Offer addOfferProducts(OfferProduct offerProduct) {
        this.offerProducts.add(offerProduct);
        offerProduct.setOffer(this);
        return this;
    }

    public Offer removeOfferProducts(OfferProduct offerProduct) {
        this.offerProducts.remove(offerProduct);
        offerProduct.setOffer(null);
        return this;
    }

    public void setOfferProducts(Set<OfferProduct> offerProducts) {
        this.offerProducts = offerProducts;
    }

    public Set<ProductSpecification> getProductSpecifications() {
        return productSpecifications;
    }

    public Offer productSpecifications(Set<ProductSpecification> productSpecifications) {
        this.productSpecifications = productSpecifications;
        return this;
    }

    public Offer addProductSpecifications(ProductSpecification productSpecification) {
        this.productSpecifications.add(productSpecification);
        productSpecification.setOffer(this);
        return this;
    }

    public Offer removeProductSpecifications(ProductSpecification productSpecification) {
        this.productSpecifications.remove(productSpecification);
        productSpecification.setOffer(null);
        return this;
    }

    public void setProductSpecifications(Set<ProductSpecification> productSpecifications) {
        this.productSpecifications = productSpecifications;
    }

    public Set<OfferAdvancePayment> getOfferAdvancePayments() {
        return offerAdvancePayments;
    }

    public Offer offerAdvancePayments(Set<OfferAdvancePayment> offerAdvancePayments) {
        this.offerAdvancePayments = offerAdvancePayments;
        return this;
    }

    public Offer addOfferAdvancePayment(OfferAdvancePayment offerAdvancePayment) {
        this.offerAdvancePayments.add(offerAdvancePayment);
        offerAdvancePayment.setOffer(this);
        return this;
    }

    public Offer removeOfferAdvancePayment(OfferAdvancePayment offerAdvancePayment) {
        this.offerAdvancePayments.remove(offerAdvancePayment);
        offerAdvancePayment.setOffer(null);
        return this;
    }

    public void setOfferAdvancePayments(Set<OfferAdvancePayment> offerAdvancePayments) {
        this.offerAdvancePayments = offerAdvancePayments;
    }

    public Set<OfferPromotion> getOfferPromotions() {
        return offerPromotions;
    }

    public Offer offerPromotions(Set<OfferPromotion> offerPromotions) {
        this.offerPromotions = offerPromotions;
        return this;
    }

    public Offer addOfferPromotions(OfferPromotion offerPromotion) {
        this.offerPromotions.add(offerPromotion);
        offerPromotion.setOffer(this);
        return this;
    }

    public Offer removeOfferPromotions(OfferPromotion offerPromotion) {
        this.offerPromotions.remove(offerPromotion);
        offerPromotion.setOffer(null);
        return this;
    }

    public void setOfferPromotions(Set<OfferPromotion> offerPromotions) {
        this.offerPromotions = offerPromotions;
    }

    public Set<OfferDiscount> getOfferDiscounts() {
        return offerDiscounts;
    }

    public Offer offerDiscounts(Set<OfferDiscount> offerDiscounts) {
        this.offerDiscounts = offerDiscounts;
        return this;
    }

    public Offer addOfferDiscounts(OfferDiscount offerDiscount) {
        this.offerDiscounts.add(offerDiscount);
        offerDiscount.setOffer(this);
        return this;
    }

    public Offer removeOfferDiscounts(OfferDiscount offerDiscount) {
        this.offerDiscounts.remove(offerDiscount);
        offerDiscount.setOffer(null);
        return this;
    }

    public void setOfferDiscounts(Set<OfferDiscount> offerDiscounts) {
        this.offerDiscounts = offerDiscounts;
    }

    public Set<Offer> getParentOffers() {
        return parentOffers;
    }

    public Offer parentOffers(Set<Offer> offers) {
        this.parentOffers = offers;
        return this;
    }

    public Offer addParentOffer(Offer offer) {
        this.parentOffers.add(offer);
        offer.getChildOffers().add(this);
        return this;
    }

    public Offer removeParentOffer(Offer offer) {
        this.parentOffers.remove(offer);
        offer.getChildOffers().remove(this);
        return this;
    }

    public void setParentOffers(Set<Offer> offers) {
        this.parentOffers = offers;
    }

    public Set<Offer> getChildOffers() {
        return childOffers;
    }

    public Offer childOffers(Set<Offer> offers) {
        this.childOffers = offers;
        return this;
    }

    public Offer addChildOffer(Offer offer) {
        this.childOffers.add(offer);
        offer.getParentOffers().add(this);
        return this;
    }

    public Offer removeChildOffer(Offer offer) {
        this.childOffers.remove(offer);
        offer.getParentOffers().remove(this);
        return this;
    }

    public void setChildOffers(Set<Offer> offers) {
        this.childOffers = offers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Offer)) {
            return false;
        }
        return id != null && id.equals(((Offer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Offer{" +
            "id=" + getId() +
            ", offerId='" + getOfferId() + "'" +
            ", offerExternalId='" + getOfferExternalId() + "'" +
            ", offerName='" + getOfferName() + "'" +
            ", offerNameChi='" + getOfferNameChi() + "'" +
            ", offerType='" + getOfferType() + "'" +
            ", offerPrice=" + getOfferPrice() +
            ", customerSegments='" + getCustomerSegments() + "'" +
            ", customerClasses='" + getCustomerClasses() + "'" +
            ", salesChannels='" + getSalesChannels() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", childOfferIds='" + getChildOfferIds() + "'" +
            ", productSpecIds='" + getProductSpecIds() + "'" +
            ", advancePaymentIds='" + getAdvancePaymentIds() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
