package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.hthk.crm.domain.enumeration.RecordStatus;

/**
 * A SubscriptionGroup.
 */
@Document(collection = "subscription_group")
public class SubscriptionGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("group_id")
    private Long groupId;

    @NotNull
    @Field("cust_acct_id")
    private String custAcctId;

    @NotNull
    @Field("group_type")
    private String groupType;

    @Field("group_name")
    private String groupName;

    @NotNull
    @Field("status")
    private RecordStatus status;

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
    @Field("groupMembers")
    private Set<GroupMembers> groupMembers = new HashSet<>();

    @DBRef
    @Field("customer")
    @JsonIgnoreProperties("subscriptionGroups")
    private Customer customer;

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

    public SubscriptionGroup groupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getCustAcctId() {
        return custAcctId;
    }

    public SubscriptionGroup custAcctId(String custAcctId) {
        this.custAcctId = custAcctId;
        return this;
    }

    public void setCustAcctId(String custAcctId) {
        this.custAcctId = custAcctId;
    }

    public String getGroupType() {
        return groupType;
    }

    public SubscriptionGroup groupType(String groupType) {
        this.groupType = groupType;
        return this;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupName() {
        return groupName;
    }

    public SubscriptionGroup groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public RecordStatus getStatus() {
        return status;
    }

    public SubscriptionGroup status(RecordStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public SubscriptionGroup startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public SubscriptionGroup endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public SubscriptionGroup createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public SubscriptionGroup createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public SubscriptionGroup lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public SubscriptionGroup lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public SubscriptionGroup tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Set<GroupMembers> getGroupMembers() {
        return groupMembers;
    }

    public SubscriptionGroup groupMembers(Set<GroupMembers> groupMembers) {
        this.groupMembers = groupMembers;
        return this;
    }

    public SubscriptionGroup addGroupMembers(GroupMembers groupMembers) {
        this.groupMembers.add(groupMembers);
        groupMembers.setSubscriptionGroup(this);
        return this;
    }

    public SubscriptionGroup removeGroupMembers(GroupMembers groupMembers) {
        this.groupMembers.remove(groupMembers);
        groupMembers.setSubscriptionGroup(null);
        return this;
    }

    public void setGroupMembers(Set<GroupMembers> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public Customer getCustomer() {
        return customer;
    }

    public SubscriptionGroup customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubscriptionGroup)) {
            return false;
        }
        return id != null && id.equals(((SubscriptionGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SubscriptionGroup{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", custAcctId='" + getCustAcctId() + "'" +
            ", groupType='" + getGroupType() + "'" +
            ", groupName='" + getGroupName() + "'" +
            ", status='" + getStatus() + "'" +
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
