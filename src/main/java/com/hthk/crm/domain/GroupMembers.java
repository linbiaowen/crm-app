package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.hthk.crm.domain.enumeration.GroupRole;

/**
 * A GroupMembers.
 */
@Document(collection = "group_members")
public class GroupMembers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("group_id")
    private Long groupId;

    @NotNull
    @Field("subscription_id")
    private String subscriptionId;

    @NotNull
    @Field("msisdn")
    private String msisdn;

    @NotNull
    @Field("group_role")
    private GroupRole groupRole;

    @Field("end_reason_code")
    private String endReasonCode;

    @Field("remarks")
    private String remarks;

    @Field("start_date")
    private Instant startDate;

    @Field("end_date")
    private Instant endDate;

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
    @Field("subscriptionGroup")
    @JsonIgnoreProperties("groupMembers")
    private SubscriptionGroup subscriptionGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public GroupMembers groupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public GroupMembers subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public GroupMembers msisdn(String msisdn) {
        this.msisdn = msisdn;
        return this;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public GroupRole getGroupRole() {
        return groupRole;
    }

    public GroupMembers groupRole(GroupRole groupRole) {
        this.groupRole = groupRole;
        return this;
    }

    public void setGroupRole(GroupRole groupRole) {
        this.groupRole = groupRole;
    }

    public String getEndReasonCode() {
        return endReasonCode;
    }

    public GroupMembers endReasonCode(String endReasonCode) {
        this.endReasonCode = endReasonCode;
        return this;
    }

    public void setEndReasonCode(String endReasonCode) {
        this.endReasonCode = endReasonCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public GroupMembers remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public GroupMembers startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public GroupMembers endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public GroupMembers createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public GroupMembers createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public GroupMembers lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public GroupMembers lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public GroupMembers tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public SubscriptionGroup getSubscriptionGroup() {
        return subscriptionGroup;
    }

    public GroupMembers subscriptionGroup(SubscriptionGroup subscriptionGroup) {
        this.subscriptionGroup = subscriptionGroup;
        return this;
    }

    public void setSubscriptionGroup(SubscriptionGroup subscriptionGroup) {
        this.subscriptionGroup = subscriptionGroup;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GroupMembers)) {
            return false;
        }
        return id != null && id.equals(((GroupMembers) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GroupMembers{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", subscriptionId='" + getSubscriptionId() + "'" +
            ", msisdn='" + getMsisdn() + "'" +
            ", groupRole='" + getGroupRole() + "'" +
            ", endReasonCode='" + getEndReasonCode() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
