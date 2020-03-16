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

import com.hthk.crm.domain.enumeration.OrderType;

import com.hthk.crm.domain.enumeration.SubOrderType;

import com.hthk.crm.domain.enumeration.OrderNature;

import com.hthk.crm.domain.enumeration.OrderStatus;

/**
 * A OrderMaster.
 */
@Document(collection = "order_master")
public class OrderMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("order_id")
    private String orderId;

    @NotNull
    @Field("cust_acct_id")
    private String custAcctId;

    @NotNull
    @Field("subscription_id")
    private String subscriptionId;

    @NotNull
    @Field("order_type")
    private OrderType orderType;

    @NotNull
    @Field("sub_order_type")
    private SubOrderType subOrderType;

    @Field("order_nature")
    private OrderNature orderNature;

    @Field("is_change_plan")
    private Boolean isChangePlan;

    @NotNull
    @Field("order_status")
    private OrderStatus orderStatus;

    @Field("remarks")
    private String remarks;

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
    @Field("subsOrderDetails")
    private Set<SubsOrderDetails> subsOrderDetails = new HashSet<>();

    @DBRef
    @Field("subsItemDeliverys")
    private Set<SubsItemDelivery> subsItemDeliverys = new HashSet<>();

    @DBRef
    @Field("subscriptionProvisions")
    private Set<SubscriptionProvision> subscriptionProvisions = new HashSet<>();

    @DBRef
    @Field("orderProcessStatuses")
    private Set<OrderProcessStatus> orderProcessStatuses = new HashSet<>();

    @DBRef
    @Field("customer")
    @JsonIgnoreProperties("orderMasters")
    private Customer customer;

    @DBRef
    @Field("custSubscription")
    @JsonIgnoreProperties("orderMasters")
    private CustSubscription custSubscription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderMaster orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustAcctId() {
        return custAcctId;
    }

    public OrderMaster custAcctId(String custAcctId) {
        this.custAcctId = custAcctId;
        return this;
    }

    public void setCustAcctId(String custAcctId) {
        this.custAcctId = custAcctId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public OrderMaster subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public OrderMaster orderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public SubOrderType getSubOrderType() {
        return subOrderType;
    }

    public OrderMaster subOrderType(SubOrderType subOrderType) {
        this.subOrderType = subOrderType;
        return this;
    }

    public void setSubOrderType(SubOrderType subOrderType) {
        this.subOrderType = subOrderType;
    }

    public OrderNature getOrderNature() {
        return orderNature;
    }

    public OrderMaster orderNature(OrderNature orderNature) {
        this.orderNature = orderNature;
        return this;
    }

    public void setOrderNature(OrderNature orderNature) {
        this.orderNature = orderNature;
    }

    public Boolean isIsChangePlan() {
        return isChangePlan;
    }

    public OrderMaster isChangePlan(Boolean isChangePlan) {
        this.isChangePlan = isChangePlan;
        return this;
    }

    public void setIsChangePlan(Boolean isChangePlan) {
        this.isChangePlan = isChangePlan;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public OrderMaster orderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public OrderMaster remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public OrderMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public OrderMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public OrderMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public OrderMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public OrderMaster tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Set<SubsOrderDetails> getSubsOrderDetails() {
        return subsOrderDetails;
    }

    public OrderMaster subsOrderDetails(Set<SubsOrderDetails> subsOrderDetails) {
        this.subsOrderDetails = subsOrderDetails;
        return this;
    }

    public OrderMaster addSubsOrderDetails(SubsOrderDetails subsOrderDetails) {
        this.subsOrderDetails.add(subsOrderDetails);
        subsOrderDetails.setOrderMaster(this);
        return this;
    }

    public OrderMaster removeSubsOrderDetails(SubsOrderDetails subsOrderDetails) {
        this.subsOrderDetails.remove(subsOrderDetails);
        subsOrderDetails.setOrderMaster(null);
        return this;
    }

    public void setSubsOrderDetails(Set<SubsOrderDetails> subsOrderDetails) {
        this.subsOrderDetails = subsOrderDetails;
    }

    public Set<SubsItemDelivery> getSubsItemDeliverys() {
        return subsItemDeliverys;
    }

    public OrderMaster subsItemDeliverys(Set<SubsItemDelivery> subsItemDeliveries) {
        this.subsItemDeliverys = subsItemDeliveries;
        return this;
    }

    public OrderMaster addSubsItemDeliverys(SubsItemDelivery subsItemDelivery) {
        this.subsItemDeliverys.add(subsItemDelivery);
        subsItemDelivery.setOrderMaster(this);
        return this;
    }

    public OrderMaster removeSubsItemDeliverys(SubsItemDelivery subsItemDelivery) {
        this.subsItemDeliverys.remove(subsItemDelivery);
        subsItemDelivery.setOrderMaster(null);
        return this;
    }

    public void setSubsItemDeliverys(Set<SubsItemDelivery> subsItemDeliveries) {
        this.subsItemDeliverys = subsItemDeliveries;
    }

    public Set<SubscriptionProvision> getSubscriptionProvisions() {
        return subscriptionProvisions;
    }

    public OrderMaster subscriptionProvisions(Set<SubscriptionProvision> subscriptionProvisions) {
        this.subscriptionProvisions = subscriptionProvisions;
        return this;
    }

    public OrderMaster addSubscriptionProvisions(SubscriptionProvision subscriptionProvision) {
        this.subscriptionProvisions.add(subscriptionProvision);
        subscriptionProvision.setOrderMaster(this);
        return this;
    }

    public OrderMaster removeSubscriptionProvisions(SubscriptionProvision subscriptionProvision) {
        this.subscriptionProvisions.remove(subscriptionProvision);
        subscriptionProvision.setOrderMaster(null);
        return this;
    }

    public void setSubscriptionProvisions(Set<SubscriptionProvision> subscriptionProvisions) {
        this.subscriptionProvisions = subscriptionProvisions;
    }

    public Set<OrderProcessStatus> getOrderProcessStatuses() {
        return orderProcessStatuses;
    }

    public OrderMaster orderProcessStatuses(Set<OrderProcessStatus> orderProcessStatuses) {
        this.orderProcessStatuses = orderProcessStatuses;
        return this;
    }

    public OrderMaster addOrderProcessStatuses(OrderProcessStatus orderProcessStatus) {
        this.orderProcessStatuses.add(orderProcessStatus);
        orderProcessStatus.setOrderMaster(this);
        return this;
    }

    public OrderMaster removeOrderProcessStatuses(OrderProcessStatus orderProcessStatus) {
        this.orderProcessStatuses.remove(orderProcessStatus);
        orderProcessStatus.setOrderMaster(null);
        return this;
    }

    public void setOrderProcessStatuses(Set<OrderProcessStatus> orderProcessStatuses) {
        this.orderProcessStatuses = orderProcessStatuses;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderMaster customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustSubscription getCustSubscription() {
        return custSubscription;
    }

    public OrderMaster custSubscription(CustSubscription custSubscription) {
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
        if (!(o instanceof OrderMaster)) {
            return false;
        }
        return id != null && id.equals(((OrderMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrderMaster{" +
            "id=" + getId() +
            ", orderId='" + getOrderId() + "'" +
            ", custAcctId='" + getCustAcctId() + "'" +
            ", subscriptionId='" + getSubscriptionId() + "'" +
            ", orderType='" + getOrderType() + "'" +
            ", subOrderType='" + getSubOrderType() + "'" +
            ", orderNature='" + getOrderNature() + "'" +
            ", isChangePlan='" + isIsChangePlan() + "'" +
            ", orderStatus='" + getOrderStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
