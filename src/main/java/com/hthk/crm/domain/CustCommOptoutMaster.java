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
 * A CustCommOptoutMaster.
 */
@Document(collection = "cust_comm_optout_master")
public class CustCommOptoutMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("optout_id")
    private String optoutId;

    @Field("cust_acct_id")
    private String custAcctId;

    @Field("subscription_id")
    private String subscriptionId;

    @Field("primary_mob_nbr")
    private String primaryMobNbr;

    @NotNull
    @Field("optout_type_id")
    private String optoutTypeId;

    @NotNull
    @Field("optout_media_id")
    private String optoutMediaId;

    @NotNull
    @Field("optout_status")
    private String optoutStatus;

    @Field("optout_start_date")
    private Instant optoutStartDate;

    @Field("optout_end_date")
    private Instant optoutEndDate;

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
    @Field("customer")
    @JsonIgnoreProperties("custCommOptouts")
    private Customer customer;

    @DBRef
    @Field("custSubscription")
    @JsonIgnoreProperties("custCommOptouts")
    private CustSubscription custSubscription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOptoutId() {
        return optoutId;
    }

    public CustCommOptoutMaster optoutId(String optoutId) {
        this.optoutId = optoutId;
        return this;
    }

    public void setOptoutId(String optoutId) {
        this.optoutId = optoutId;
    }

    public String getCustAcctId() {
        return custAcctId;
    }

    public CustCommOptoutMaster custAcctId(String custAcctId) {
        this.custAcctId = custAcctId;
        return this;
    }

    public void setCustAcctId(String custAcctId) {
        this.custAcctId = custAcctId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public CustCommOptoutMaster subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getPrimaryMobNbr() {
        return primaryMobNbr;
    }

    public CustCommOptoutMaster primaryMobNbr(String primaryMobNbr) {
        this.primaryMobNbr = primaryMobNbr;
        return this;
    }

    public void setPrimaryMobNbr(String primaryMobNbr) {
        this.primaryMobNbr = primaryMobNbr;
    }

    public String getOptoutTypeId() {
        return optoutTypeId;
    }

    public CustCommOptoutMaster optoutTypeId(String optoutTypeId) {
        this.optoutTypeId = optoutTypeId;
        return this;
    }

    public void setOptoutTypeId(String optoutTypeId) {
        this.optoutTypeId = optoutTypeId;
    }

    public String getOptoutMediaId() {
        return optoutMediaId;
    }

    public CustCommOptoutMaster optoutMediaId(String optoutMediaId) {
        this.optoutMediaId = optoutMediaId;
        return this;
    }

    public void setOptoutMediaId(String optoutMediaId) {
        this.optoutMediaId = optoutMediaId;
    }

    public String getOptoutStatus() {
        return optoutStatus;
    }

    public CustCommOptoutMaster optoutStatus(String optoutStatus) {
        this.optoutStatus = optoutStatus;
        return this;
    }

    public void setOptoutStatus(String optoutStatus) {
        this.optoutStatus = optoutStatus;
    }

    public Instant getOptoutStartDate() {
        return optoutStartDate;
    }

    public CustCommOptoutMaster optoutStartDate(Instant optoutStartDate) {
        this.optoutStartDate = optoutStartDate;
        return this;
    }

    public void setOptoutStartDate(Instant optoutStartDate) {
        this.optoutStartDate = optoutStartDate;
    }

    public Instant getOptoutEndDate() {
        return optoutEndDate;
    }

    public CustCommOptoutMaster optoutEndDate(Instant optoutEndDate) {
        this.optoutEndDate = optoutEndDate;
        return this;
    }

    public void setOptoutEndDate(Instant optoutEndDate) {
        this.optoutEndDate = optoutEndDate;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public CustCommOptoutMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CustCommOptoutMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public CustCommOptoutMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public CustCommOptoutMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public CustCommOptoutMaster tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustCommOptoutMaster customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustSubscription getCustSubscription() {
        return custSubscription;
    }

    public CustCommOptoutMaster custSubscription(CustSubscription custSubscription) {
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
        if (!(o instanceof CustCommOptoutMaster)) {
            return false;
        }
        return id != null && id.equals(((CustCommOptoutMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CustCommOptoutMaster{" +
            "id=" + getId() +
            ", optoutId='" + getOptoutId() + "'" +
            ", custAcctId='" + getCustAcctId() + "'" +
            ", subscriptionId='" + getSubscriptionId() + "'" +
            ", primaryMobNbr='" + getPrimaryMobNbr() + "'" +
            ", optoutTypeId='" + getOptoutTypeId() + "'" +
            ", optoutMediaId='" + getOptoutMediaId() + "'" +
            ", optoutStatus='" + getOptoutStatus() + "'" +
            ", optoutStartDate='" + getOptoutStartDate() + "'" +
            ", optoutEndDate='" + getOptoutEndDate() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
