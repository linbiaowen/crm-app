package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.hthk.crm.domain.enumeration.MemberShipServiceType;

/**
 * A SupremeMaster.
 */
@Document(collection = "supreme_master")
public class SupremeMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("supreme_seq_id")
    private Long supremeSeqId;

    @Field("subscription_id")
    private String subscriptionId;

    @Field("msisdn")
    private String msisdn;

    @Field("start_date")
    private Instant startDate;

    @Field("end_date")
    private Instant endDate;

    @Field("membership_service_type")
    private MemberShipServiceType membershipServiceType;

    @Field("pe_code")
    private String peCode;

    @Field("personal_exec_direct_line")
    private String personalExecDirectLine;

    @Field("personal_exec_name")
    private String personalExecName;

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
    @JsonIgnoreProperties("supremeMasters")
    private CustSubscription custSubscription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSupremeSeqId() {
        return supremeSeqId;
    }

    public SupremeMaster supremeSeqId(Long supremeSeqId) {
        this.supremeSeqId = supremeSeqId;
        return this;
    }

    public void setSupremeSeqId(Long supremeSeqId) {
        this.supremeSeqId = supremeSeqId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public SupremeMaster subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public SupremeMaster msisdn(String msisdn) {
        this.msisdn = msisdn;
        return this;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public SupremeMaster startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public SupremeMaster endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public MemberShipServiceType getMembershipServiceType() {
        return membershipServiceType;
    }

    public SupremeMaster membershipServiceType(MemberShipServiceType membershipServiceType) {
        this.membershipServiceType = membershipServiceType;
        return this;
    }

    public void setMembershipServiceType(MemberShipServiceType membershipServiceType) {
        this.membershipServiceType = membershipServiceType;
    }

    public String getPeCode() {
        return peCode;
    }

    public SupremeMaster peCode(String peCode) {
        this.peCode = peCode;
        return this;
    }

    public void setPeCode(String peCode) {
        this.peCode = peCode;
    }

    public String getPersonalExecDirectLine() {
        return personalExecDirectLine;
    }

    public SupremeMaster personalExecDirectLine(String personalExecDirectLine) {
        this.personalExecDirectLine = personalExecDirectLine;
        return this;
    }

    public void setPersonalExecDirectLine(String personalExecDirectLine) {
        this.personalExecDirectLine = personalExecDirectLine;
    }

    public String getPersonalExecName() {
        return personalExecName;
    }

    public SupremeMaster personalExecName(String personalExecName) {
        this.personalExecName = personalExecName;
        return this;
    }

    public void setPersonalExecName(String personalExecName) {
        this.personalExecName = personalExecName;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public SupremeMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public SupremeMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public SupremeMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public SupremeMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public SupremeMaster tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public CustSubscription getCustSubscription() {
        return custSubscription;
    }

    public SupremeMaster custSubscription(CustSubscription custSubscription) {
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
        if (!(o instanceof SupremeMaster)) {
            return false;
        }
        return id != null && id.equals(((SupremeMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SupremeMaster{" +
            "id=" + getId() +
            ", supremeSeqId=" + getSupremeSeqId() +
            ", subscriptionId='" + getSubscriptionId() + "'" +
            ", msisdn='" + getMsisdn() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", membershipServiceType='" + getMembershipServiceType() + "'" +
            ", peCode='" + getPeCode() + "'" +
            ", personalExecDirectLine='" + getPersonalExecDirectLine() + "'" +
            ", personalExecName='" + getPersonalExecName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
