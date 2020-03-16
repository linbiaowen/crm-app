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

import com.hthk.crm.domain.enumeration.Language;

/**
 * A SubscriptionDetails.
 */
@Document(collection = "subscription_details")
public class SubscriptionDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("subs_detail_id")
    private Long subsDetailId;

    @NotNull
    @Field("subscription_id")
    private String subscriptionId;

    @Field("start_date")
    private Instant startDate;

    @Field("end_date")
    private Instant endDate;

    @Field("order_id")
    private String orderId;

    @Field("ssa_nbr")
    private String ssaNbr;

    @Field("primary_msisdn")
    private String primaryMsisdn;

    @Field("iccid")
    private String iccid;

    @Field("imsi")
    private String imsi;

    @Field("mnp_requested_date")
    private Instant mnpRequestedDate;

    @NotNull
    @Field("lang")
    private Language lang;

    @Field("base_offer_id")
    private String baseOfferId;

    @Field("base_offer_name")
    private String baseOfferName;

    @Field("matrixx_catalog_id")
    private String matrixxCatalogId;

    @Field("matrixx_resource_id")
    private String matrixxResourceId;

    @Field("matrixx_object_id")
    private String matrixxObjectId;

    @Field("sales_channel")
    private String salesChannel;

    @Field("advance_payment_months")
    private Integer advancePaymentMonths;

    @Field("offer_price")
    private BigDecimal offerPrice;

    @Field("network_type")
    private String networkType;

    @Field("service_type")
    private String serviceType;

    @Field("offer_plan_code")
    private String offerPlanCode;

    @Field("service_in_person")
    private String serviceInPerson;

    @Field("fcm_token")
    private String fcmToken;

    @Field("remarks")
    private String remarks;

    @Field("cd_version")
    private String cdVersion;

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
    @Field("custSubscription")
    @JsonIgnoreProperties("subscriptionDetails")
    private CustSubscription custSubscription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSubsDetailId() {
        return subsDetailId;
    }

    public SubscriptionDetails subsDetailId(Long subsDetailId) {
        this.subsDetailId = subsDetailId;
        return this;
    }

    public void setSubsDetailId(Long subsDetailId) {
        this.subsDetailId = subsDetailId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public SubscriptionDetails subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public SubscriptionDetails startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public SubscriptionDetails endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public SubscriptionDetails orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSsaNbr() {
        return ssaNbr;
    }

    public SubscriptionDetails ssaNbr(String ssaNbr) {
        this.ssaNbr = ssaNbr;
        return this;
    }

    public void setSsaNbr(String ssaNbr) {
        this.ssaNbr = ssaNbr;
    }

    public String getPrimaryMsisdn() {
        return primaryMsisdn;
    }

    public SubscriptionDetails primaryMsisdn(String primaryMsisdn) {
        this.primaryMsisdn = primaryMsisdn;
        return this;
    }

    public void setPrimaryMsisdn(String primaryMsisdn) {
        this.primaryMsisdn = primaryMsisdn;
    }

    public String getIccid() {
        return iccid;
    }

    public SubscriptionDetails iccid(String iccid) {
        this.iccid = iccid;
        return this;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getImsi() {
        return imsi;
    }

    public SubscriptionDetails imsi(String imsi) {
        this.imsi = imsi;
        return this;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public Instant getMnpRequestedDate() {
        return mnpRequestedDate;
    }

    public SubscriptionDetails mnpRequestedDate(Instant mnpRequestedDate) {
        this.mnpRequestedDate = mnpRequestedDate;
        return this;
    }

    public void setMnpRequestedDate(Instant mnpRequestedDate) {
        this.mnpRequestedDate = mnpRequestedDate;
    }

    public Language getLang() {
        return lang;
    }

    public SubscriptionDetails lang(Language lang) {
        this.lang = lang;
        return this;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    public String getBaseOfferId() {
        return baseOfferId;
    }

    public SubscriptionDetails baseOfferId(String baseOfferId) {
        this.baseOfferId = baseOfferId;
        return this;
    }

    public void setBaseOfferId(String baseOfferId) {
        this.baseOfferId = baseOfferId;
    }

    public String getBaseOfferName() {
        return baseOfferName;
    }

    public SubscriptionDetails baseOfferName(String baseOfferName) {
        this.baseOfferName = baseOfferName;
        return this;
    }

    public void setBaseOfferName(String baseOfferName) {
        this.baseOfferName = baseOfferName;
    }

    public String getMatrixxCatalogId() {
        return matrixxCatalogId;
    }

    public SubscriptionDetails matrixxCatalogId(String matrixxCatalogId) {
        this.matrixxCatalogId = matrixxCatalogId;
        return this;
    }

    public void setMatrixxCatalogId(String matrixxCatalogId) {
        this.matrixxCatalogId = matrixxCatalogId;
    }

    public String getMatrixxResourceId() {
        return matrixxResourceId;
    }

    public SubscriptionDetails matrixxResourceId(String matrixxResourceId) {
        this.matrixxResourceId = matrixxResourceId;
        return this;
    }

    public void setMatrixxResourceId(String matrixxResourceId) {
        this.matrixxResourceId = matrixxResourceId;
    }

    public String getMatrixxObjectId() {
        return matrixxObjectId;
    }

    public SubscriptionDetails matrixxObjectId(String matrixxObjectId) {
        this.matrixxObjectId = matrixxObjectId;
        return this;
    }

    public void setMatrixxObjectId(String matrixxObjectId) {
        this.matrixxObjectId = matrixxObjectId;
    }

    public String getSalesChannel() {
        return salesChannel;
    }

    public SubscriptionDetails salesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
        return this;
    }

    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
    }

    public Integer getAdvancePaymentMonths() {
        return advancePaymentMonths;
    }

    public SubscriptionDetails advancePaymentMonths(Integer advancePaymentMonths) {
        this.advancePaymentMonths = advancePaymentMonths;
        return this;
    }

    public void setAdvancePaymentMonths(Integer advancePaymentMonths) {
        this.advancePaymentMonths = advancePaymentMonths;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public SubscriptionDetails offerPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
        return this;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getNetworkType() {
        return networkType;
    }

    public SubscriptionDetails networkType(String networkType) {
        this.networkType = networkType;
        return this;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public SubscriptionDetails serviceType(String serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getOfferPlanCode() {
        return offerPlanCode;
    }

    public SubscriptionDetails offerPlanCode(String offerPlanCode) {
        this.offerPlanCode = offerPlanCode;
        return this;
    }

    public void setOfferPlanCode(String offerPlanCode) {
        this.offerPlanCode = offerPlanCode;
    }

    public String getServiceInPerson() {
        return serviceInPerson;
    }

    public SubscriptionDetails serviceInPerson(String serviceInPerson) {
        this.serviceInPerson = serviceInPerson;
        return this;
    }

    public void setServiceInPerson(String serviceInPerson) {
        this.serviceInPerson = serviceInPerson;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public SubscriptionDetails fcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
        return this;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getRemarks() {
        return remarks;
    }

    public SubscriptionDetails remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCdVersion() {
        return cdVersion;
    }

    public SubscriptionDetails cdVersion(String cdVersion) {
        this.cdVersion = cdVersion;
        return this;
    }

    public void setCdVersion(String cdVersion) {
        this.cdVersion = cdVersion;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public SubscriptionDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public SubscriptionDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public SubscriptionDetails lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public SubscriptionDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public SubscriptionDetails tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public CustSubscription getCustSubscription() {
        return custSubscription;
    }

    public SubscriptionDetails custSubscription(CustSubscription custSubscription) {
        this.custSubscription = custSubscription;
        return this;
    }

    public void setCustSubscription(CustSubscription custSubscription) {
        this.custSubscription = custSubscription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubscriptionDetails)) {
            return false;
        }
        return id != null && id.equals(((SubscriptionDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SubscriptionDetails{" +
            "id=" + getId() +
            ", subsDetailId=" + getSubsDetailId() +
            ", subscriptionId='" + getSubscriptionId() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", orderId='" + getOrderId() + "'" +
            ", ssaNbr='" + getSsaNbr() + "'" +
            ", primaryMsisdn='" + getPrimaryMsisdn() + "'" +
            ", iccid='" + getIccid() + "'" +
            ", imsi='" + getImsi() + "'" +
            ", mnpRequestedDate='" + getMnpRequestedDate() + "'" +
            ", lang='" + getLang() + "'" +
            ", baseOfferId='" + getBaseOfferId() + "'" +
            ", baseOfferName='" + getBaseOfferName() + "'" +
            ", matrixxCatalogId='" + getMatrixxCatalogId() + "'" +
            ", matrixxResourceId='" + getMatrixxResourceId() + "'" +
            ", matrixxObjectId='" + getMatrixxObjectId() + "'" +
            ", salesChannel='" + getSalesChannel() + "'" +
            ", advancePaymentMonths=" + getAdvancePaymentMonths() +
            ", offerPrice=" + getOfferPrice() +
            ", networkType='" + getNetworkType() + "'" +
            ", serviceType='" + getServiceType() + "'" +
            ", offerPlanCode='" + getOfferPlanCode() + "'" +
            ", serviceInPerson='" + getServiceInPerson() + "'" +
            ", fcmToken='" + getFcmToken() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", cdVersion='" + getCdVersion() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
