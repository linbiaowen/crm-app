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

import com.hthk.crm.domain.enumeration.ServiceType;

/**
 * A SubsOrderDetails.
 */
@Document(collection = "subs_order_details")
public class SubsOrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("subs_order_seq_id")
    private Long subsOrderSeqId;

    @NotNull
    @Field("subscription_id")
    private String subscriptionId;

    @Field("start_date")
    private Instant startDate;

    @Field("end_date")
    private Instant endDate;

    @NotNull
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

    @Field("sim_verified")
    private Boolean simVerified;

    @Field("sim_verified_date")
    private Instant simVerifiedDate;

    @Field("billing_acct_id")
    private String billingAcctId;

    @Field("bill_cycle_id")
    private Integer billCycleId;

    @Field("mnp_requested_date")
    private Instant mnpRequestedDate;

    @Field("mnp_ticket")
    private String mnpTicket;

    @Field("mnp_port_in_session")
    private String mnpPortInSession;

    @Field("mnp_original_id")
    private String mnpOriginalId;

    @Field("mnp_cust_name")
    private String mnpCustName;

    @Field("mnp_id_nbr")
    private String mnpIdNbr;

    @Field("mnp_id_type")
    private String mnpIdType;

    @Field("hthk_msisdn")
    private Boolean hthkMsisdn;

    @NotNull
    @Field("lang")
    private Language lang;

    @Field("offer_id")
    private String offerId;

    @Field("offer_name")
    private String offerName;

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

    @Field("servicetype")
    private ServiceType servicetype;

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
    @JsonIgnoreProperties("subsOrderDetails")
    private CustSubscription custSubscription;

    @DBRef
    @Field("orderMaster")
    @JsonIgnoreProperties("subsOrderDetails")
    private OrderMaster orderMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSubsOrderSeqId() {
        return subsOrderSeqId;
    }

    public SubsOrderDetails subsOrderSeqId(Long subsOrderSeqId) {
        this.subsOrderSeqId = subsOrderSeqId;
        return this;
    }

    public void setSubsOrderSeqId(Long subsOrderSeqId) {
        this.subsOrderSeqId = subsOrderSeqId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public SubsOrderDetails subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public SubsOrderDetails startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public SubsOrderDetails endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public SubsOrderDetails orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSsaNbr() {
        return ssaNbr;
    }

    public SubsOrderDetails ssaNbr(String ssaNbr) {
        this.ssaNbr = ssaNbr;
        return this;
    }

    public void setSsaNbr(String ssaNbr) {
        this.ssaNbr = ssaNbr;
    }

    public String getPrimaryMsisdn() {
        return primaryMsisdn;
    }

    public SubsOrderDetails primaryMsisdn(String primaryMsisdn) {
        this.primaryMsisdn = primaryMsisdn;
        return this;
    }

    public void setPrimaryMsisdn(String primaryMsisdn) {
        this.primaryMsisdn = primaryMsisdn;
    }

    public String getIccid() {
        return iccid;
    }

    public SubsOrderDetails iccid(String iccid) {
        this.iccid = iccid;
        return this;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getImsi() {
        return imsi;
    }

    public SubsOrderDetails imsi(String imsi) {
        this.imsi = imsi;
        return this;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public Boolean isSimVerified() {
        return simVerified;
    }

    public SubsOrderDetails simVerified(Boolean simVerified) {
        this.simVerified = simVerified;
        return this;
    }

    public void setSimVerified(Boolean simVerified) {
        this.simVerified = simVerified;
    }

    public Instant getSimVerifiedDate() {
        return simVerifiedDate;
    }

    public SubsOrderDetails simVerifiedDate(Instant simVerifiedDate) {
        this.simVerifiedDate = simVerifiedDate;
        return this;
    }

    public void setSimVerifiedDate(Instant simVerifiedDate) {
        this.simVerifiedDate = simVerifiedDate;
    }

    public String getBillingAcctId() {
        return billingAcctId;
    }

    public SubsOrderDetails billingAcctId(String billingAcctId) {
        this.billingAcctId = billingAcctId;
        return this;
    }

    public void setBillingAcctId(String billingAcctId) {
        this.billingAcctId = billingAcctId;
    }

    public Integer getBillCycleId() {
        return billCycleId;
    }

    public SubsOrderDetails billCycleId(Integer billCycleId) {
        this.billCycleId = billCycleId;
        return this;
    }

    public void setBillCycleId(Integer billCycleId) {
        this.billCycleId = billCycleId;
    }

    public Instant getMnpRequestedDate() {
        return mnpRequestedDate;
    }

    public SubsOrderDetails mnpRequestedDate(Instant mnpRequestedDate) {
        this.mnpRequestedDate = mnpRequestedDate;
        return this;
    }

    public void setMnpRequestedDate(Instant mnpRequestedDate) {
        this.mnpRequestedDate = mnpRequestedDate;
    }

    public String getMnpTicket() {
        return mnpTicket;
    }

    public SubsOrderDetails mnpTicket(String mnpTicket) {
        this.mnpTicket = mnpTicket;
        return this;
    }

    public void setMnpTicket(String mnpTicket) {
        this.mnpTicket = mnpTicket;
    }

    public String getMnpPortInSession() {
        return mnpPortInSession;
    }

    public SubsOrderDetails mnpPortInSession(String mnpPortInSession) {
        this.mnpPortInSession = mnpPortInSession;
        return this;
    }

    public void setMnpPortInSession(String mnpPortInSession) {
        this.mnpPortInSession = mnpPortInSession;
    }

    public String getMnpOriginalId() {
        return mnpOriginalId;
    }

    public SubsOrderDetails mnpOriginalId(String mnpOriginalId) {
        this.mnpOriginalId = mnpOriginalId;
        return this;
    }

    public void setMnpOriginalId(String mnpOriginalId) {
        this.mnpOriginalId = mnpOriginalId;
    }

    public String getMnpCustName() {
        return mnpCustName;
    }

    public SubsOrderDetails mnpCustName(String mnpCustName) {
        this.mnpCustName = mnpCustName;
        return this;
    }

    public void setMnpCustName(String mnpCustName) {
        this.mnpCustName = mnpCustName;
    }

    public String getMnpIdNbr() {
        return mnpIdNbr;
    }

    public SubsOrderDetails mnpIdNbr(String mnpIdNbr) {
        this.mnpIdNbr = mnpIdNbr;
        return this;
    }

    public void setMnpIdNbr(String mnpIdNbr) {
        this.mnpIdNbr = mnpIdNbr;
    }

    public String getMnpIdType() {
        return mnpIdType;
    }

    public SubsOrderDetails mnpIdType(String mnpIdType) {
        this.mnpIdType = mnpIdType;
        return this;
    }

    public void setMnpIdType(String mnpIdType) {
        this.mnpIdType = mnpIdType;
    }

    public Boolean isHthkMsisdn() {
        return hthkMsisdn;
    }

    public SubsOrderDetails hthkMsisdn(Boolean hthkMsisdn) {
        this.hthkMsisdn = hthkMsisdn;
        return this;
    }

    public void setHthkMsisdn(Boolean hthkMsisdn) {
        this.hthkMsisdn = hthkMsisdn;
    }

    public Language getLang() {
        return lang;
    }

    public SubsOrderDetails lang(Language lang) {
        this.lang = lang;
        return this;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    public String getOfferId() {
        return offerId;
    }

    public SubsOrderDetails offerId(String offerId) {
        this.offerId = offerId;
        return this;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public SubsOrderDetails offerName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getMatrixxCatalogId() {
        return matrixxCatalogId;
    }

    public SubsOrderDetails matrixxCatalogId(String matrixxCatalogId) {
        this.matrixxCatalogId = matrixxCatalogId;
        return this;
    }

    public void setMatrixxCatalogId(String matrixxCatalogId) {
        this.matrixxCatalogId = matrixxCatalogId;
    }

    public String getMatrixxResourceId() {
        return matrixxResourceId;
    }

    public SubsOrderDetails matrixxResourceId(String matrixxResourceId) {
        this.matrixxResourceId = matrixxResourceId;
        return this;
    }

    public void setMatrixxResourceId(String matrixxResourceId) {
        this.matrixxResourceId = matrixxResourceId;
    }

    public String getMatrixxObjectId() {
        return matrixxObjectId;
    }

    public SubsOrderDetails matrixxObjectId(String matrixxObjectId) {
        this.matrixxObjectId = matrixxObjectId;
        return this;
    }

    public void setMatrixxObjectId(String matrixxObjectId) {
        this.matrixxObjectId = matrixxObjectId;
    }

    public String getSalesChannel() {
        return salesChannel;
    }

    public SubsOrderDetails salesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
        return this;
    }

    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
    }

    public Integer getAdvancePaymentMonths() {
        return advancePaymentMonths;
    }

    public SubsOrderDetails advancePaymentMonths(Integer advancePaymentMonths) {
        this.advancePaymentMonths = advancePaymentMonths;
        return this;
    }

    public void setAdvancePaymentMonths(Integer advancePaymentMonths) {
        this.advancePaymentMonths = advancePaymentMonths;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public SubsOrderDetails offerPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
        return this;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getNetworkType() {
        return networkType;
    }

    public SubsOrderDetails networkType(String networkType) {
        this.networkType = networkType;
        return this;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public ServiceType getServicetype() {
        return servicetype;
    }

    public SubsOrderDetails servicetype(ServiceType servicetype) {
        this.servicetype = servicetype;
        return this;
    }

    public void setServicetype(ServiceType servicetype) {
        this.servicetype = servicetype;
    }

    public String getOfferPlanCode() {
        return offerPlanCode;
    }

    public SubsOrderDetails offerPlanCode(String offerPlanCode) {
        this.offerPlanCode = offerPlanCode;
        return this;
    }

    public void setOfferPlanCode(String offerPlanCode) {
        this.offerPlanCode = offerPlanCode;
    }

    public String getServiceInPerson() {
        return serviceInPerson;
    }

    public SubsOrderDetails serviceInPerson(String serviceInPerson) {
        this.serviceInPerson = serviceInPerson;
        return this;
    }

    public void setServiceInPerson(String serviceInPerson) {
        this.serviceInPerson = serviceInPerson;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public SubsOrderDetails fcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
        return this;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getRemarks() {
        return remarks;
    }

    public SubsOrderDetails remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCdVersion() {
        return cdVersion;
    }

    public SubsOrderDetails cdVersion(String cdVersion) {
        this.cdVersion = cdVersion;
        return this;
    }

    public void setCdVersion(String cdVersion) {
        this.cdVersion = cdVersion;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public SubsOrderDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public SubsOrderDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public SubsOrderDetails lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public SubsOrderDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public SubsOrderDetails tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public CustSubscription getCustSubscription() {
        return custSubscription;
    }

    public SubsOrderDetails custSubscription(CustSubscription custSubscription) {
        this.custSubscription = custSubscription;
        return this;
    }

    public void setCustSubscription(CustSubscription custSubscription) {
        this.custSubscription = custSubscription;
    }

    public OrderMaster getOrderMaster() {
        return orderMaster;
    }

    public SubsOrderDetails orderMaster(OrderMaster orderMaster) {
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
        if (!(o instanceof SubsOrderDetails)) {
            return false;
        }
        return id != null && id.equals(((SubsOrderDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SubsOrderDetails{" +
            "id=" + getId() +
            ", subsOrderSeqId=" + getSubsOrderSeqId() +
            ", subscriptionId='" + getSubscriptionId() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", orderId='" + getOrderId() + "'" +
            ", ssaNbr='" + getSsaNbr() + "'" +
            ", primaryMsisdn='" + getPrimaryMsisdn() + "'" +
            ", iccid='" + getIccid() + "'" +
            ", imsi='" + getImsi() + "'" +
            ", simVerified='" + isSimVerified() + "'" +
            ", simVerifiedDate='" + getSimVerifiedDate() + "'" +
            ", billingAcctId='" + getBillingAcctId() + "'" +
            ", billCycleId=" + getBillCycleId() +
            ", mnpRequestedDate='" + getMnpRequestedDate() + "'" +
            ", mnpTicket='" + getMnpTicket() + "'" +
            ", mnpPortInSession='" + getMnpPortInSession() + "'" +
            ", mnpOriginalId='" + getMnpOriginalId() + "'" +
            ", mnpCustName='" + getMnpCustName() + "'" +
            ", mnpIdNbr='" + getMnpIdNbr() + "'" +
            ", mnpIdType='" + getMnpIdType() + "'" +
            ", hthkMsisdn='" + isHthkMsisdn() + "'" +
            ", lang='" + getLang() + "'" +
            ", offerId='" + getOfferId() + "'" +
            ", offerName='" + getOfferName() + "'" +
            ", matrixxCatalogId='" + getMatrixxCatalogId() + "'" +
            ", matrixxResourceId='" + getMatrixxResourceId() + "'" +
            ", matrixxObjectId='" + getMatrixxObjectId() + "'" +
            ", salesChannel='" + getSalesChannel() + "'" +
            ", advancePaymentMonths=" + getAdvancePaymentMonths() +
            ", offerPrice=" + getOfferPrice() +
            ", networkType='" + getNetworkType() + "'" +
            ", servicetype='" + getServicetype() + "'" +
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
