package com.hthk.crm.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.hthk.crm.domain.enumeration.AcctStatus;

import com.hthk.crm.domain.enumeration.Language;

import com.hthk.crm.domain.enumeration.CustomerSegment;

/**
 * A Customer.
 */
@Document(collection = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("cust_acct_id")
    private String custAcctId;

    @Field("parent_cust_acct_id")
    private String parentCustAcctId;

    @NotNull
    @Field("acct_status")
    private AcctStatus acctStatus;

    @NotNull
    @Field("acct_start_date")
    private Instant acctStartDate;

    @Field("acct_end_date")
    private Instant acctEndDate;

    @Field("cabs_acct_id")
    private String cabsAcctId;

    @Field("title")
    private String title;

    @Field("given_name")
    private String givenName;

    @Field("family_name")
    private String familyName;

    @Field("given_name_chi")
    private String givenNameChi;

    @Field("family_name_chi")
    private String familyNameChi;

    @Field("alias_name")
    private String aliasName;

    @Field("gender")
    private String gender;

    @Field("birth_date")
    private LocalDate birthDate;

    @Field("id_type")
    private String idType;

    @Field("id_number")
    private String idNumber;

    @Field("company_name_eng")
    private String companyNameEng;

    @Field("company_name_chi")
    private String companyNameChi;

    @Field("unlimited_company")
    private Boolean unlimitedCompany;

    @NotNull
    @Field("lang")
    private Language lang;

    @Field("self_care_user_id")
    private String selfCareUserId;

    @Field("self_care_password")
    private String selfCarePassword;

    @Field("ivr_pin")
    private String ivrPin;

    @Field("maritial_status")
    private String maritialStatus;

    @Field("customer_segment")
    private CustomerSegment customerSegment;

    @Field("customer_class")
    private String customerClass;

    @Field("billing_acct_id")
    private String billingAcctId;

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
    @Field("parentCustomer")
    private Customer parentCustomer;

    @DBRef
    @Field("custDocuments")
    private Set<CustDocument> custDocuments = new HashSet<>();

    @DBRef
    @Field("custCommOptouts")
    private Set<CustCommOptoutMaster> custCommOptouts = new HashSet<>();

    @DBRef
    @Field("custContacts")
    private Set<CustContact> custContacts = new HashSet<>();

    @DBRef
    @Field("custAddresses")
    private Set<CustAddress> custAddresses = new HashSet<>();

    @DBRef
    @Field("subscriptionGroups")
    private Set<SubscriptionGroup> subscriptionGroups = new HashSet<>();

    @DBRef
    @Field("subscriptions")
    private Set<CustSubscription> subscriptions = new HashSet<>();

    @DBRef
    @Field("orderMasters")
    private Set<OrderMaster> orderMasters = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustAcctId() {
        return custAcctId;
    }

    public Customer custAcctId(String custAcctId) {
        this.custAcctId = custAcctId;
        return this;
    }

    public void setCustAcctId(String custAcctId) {
        this.custAcctId = custAcctId;
    }

    public String getParentCustAcctId() {
        return parentCustAcctId;
    }

    public Customer parentCustAcctId(String parentCustAcctId) {
        this.parentCustAcctId = parentCustAcctId;
        return this;
    }

    public void setParentCustAcctId(String parentCustAcctId) {
        this.parentCustAcctId = parentCustAcctId;
    }

    public AcctStatus getAcctStatus() {
        return acctStatus;
    }

    public Customer acctStatus(AcctStatus acctStatus) {
        this.acctStatus = acctStatus;
        return this;
    }

    public void setAcctStatus(AcctStatus acctStatus) {
        this.acctStatus = acctStatus;
    }

    public Instant getAcctStartDate() {
        return acctStartDate;
    }

    public Customer acctStartDate(Instant acctStartDate) {
        this.acctStartDate = acctStartDate;
        return this;
    }

    public void setAcctStartDate(Instant acctStartDate) {
        this.acctStartDate = acctStartDate;
    }

    public Instant getAcctEndDate() {
        return acctEndDate;
    }

    public Customer acctEndDate(Instant acctEndDate) {
        this.acctEndDate = acctEndDate;
        return this;
    }

    public void setAcctEndDate(Instant acctEndDate) {
        this.acctEndDate = acctEndDate;
    }

    public String getCabsAcctId() {
        return cabsAcctId;
    }

    public Customer cabsAcctId(String cabsAcctId) {
        this.cabsAcctId = cabsAcctId;
        return this;
    }

    public void setCabsAcctId(String cabsAcctId) {
        this.cabsAcctId = cabsAcctId;
    }

    public String getTitle() {
        return title;
    }

    public Customer title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGivenName() {
        return givenName;
    }

    public Customer givenName(String givenName) {
        this.givenName = givenName;
        return this;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public Customer familyName(String familyName) {
        this.familyName = familyName;
        return this;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenNameChi() {
        return givenNameChi;
    }

    public Customer givenNameChi(String givenNameChi) {
        this.givenNameChi = givenNameChi;
        return this;
    }

    public void setGivenNameChi(String givenNameChi) {
        this.givenNameChi = givenNameChi;
    }

    public String getFamilyNameChi() {
        return familyNameChi;
    }

    public Customer familyNameChi(String familyNameChi) {
        this.familyNameChi = familyNameChi;
        return this;
    }

    public void setFamilyNameChi(String familyNameChi) {
        this.familyNameChi = familyNameChi;
    }

    public String getAliasName() {
        return aliasName;
    }

    public Customer aliasName(String aliasName) {
        this.aliasName = aliasName;
        return this;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getGender() {
        return gender;
    }

    public Customer gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Customer birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdType() {
        return idType;
    }

    public Customer idType(String idType) {
        this.idType = idType;
        return this;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public Customer idNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCompanyNameEng() {
        return companyNameEng;
    }

    public Customer companyNameEng(String companyNameEng) {
        this.companyNameEng = companyNameEng;
        return this;
    }

    public void setCompanyNameEng(String companyNameEng) {
        this.companyNameEng = companyNameEng;
    }

    public String getCompanyNameChi() {
        return companyNameChi;
    }

    public Customer companyNameChi(String companyNameChi) {
        this.companyNameChi = companyNameChi;
        return this;
    }

    public void setCompanyNameChi(String companyNameChi) {
        this.companyNameChi = companyNameChi;
    }

    public Boolean isUnlimitedCompany() {
        return unlimitedCompany;
    }

    public Customer unlimitedCompany(Boolean unlimitedCompany) {
        this.unlimitedCompany = unlimitedCompany;
        return this;
    }

    public void setUnlimitedCompany(Boolean unlimitedCompany) {
        this.unlimitedCompany = unlimitedCompany;
    }

    public Language getLang() {
        return lang;
    }

    public Customer lang(Language lang) {
        this.lang = lang;
        return this;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    public String getSelfCareUserId() {
        return selfCareUserId;
    }

    public Customer selfCareUserId(String selfCareUserId) {
        this.selfCareUserId = selfCareUserId;
        return this;
    }

    public void setSelfCareUserId(String selfCareUserId) {
        this.selfCareUserId = selfCareUserId;
    }

    public String getSelfCarePassword() {
        return selfCarePassword;
    }

    public Customer selfCarePassword(String selfCarePassword) {
        this.selfCarePassword = selfCarePassword;
        return this;
    }

    public void setSelfCarePassword(String selfCarePassword) {
        this.selfCarePassword = selfCarePassword;
    }

    public String getIvrPin() {
        return ivrPin;
    }

    public Customer ivrPin(String ivrPin) {
        this.ivrPin = ivrPin;
        return this;
    }

    public void setIvrPin(String ivrPin) {
        this.ivrPin = ivrPin;
    }

    public String getMaritialStatus() {
        return maritialStatus;
    }

    public Customer maritialStatus(String maritialStatus) {
        this.maritialStatus = maritialStatus;
        return this;
    }

    public void setMaritialStatus(String maritialStatus) {
        this.maritialStatus = maritialStatus;
    }

    public CustomerSegment getCustomerSegment() {
        return customerSegment;
    }

    public Customer customerSegment(CustomerSegment customerSegment) {
        this.customerSegment = customerSegment;
        return this;
    }

    public void setCustomerSegment(CustomerSegment customerSegment) {
        this.customerSegment = customerSegment;
    }

    public String getCustomerClass() {
        return customerClass;
    }

    public Customer customerClass(String customerClass) {
        this.customerClass = customerClass;
        return this;
    }

    public void setCustomerClass(String customerClass) {
        this.customerClass = customerClass;
    }

    public String getBillingAcctId() {
        return billingAcctId;
    }

    public Customer billingAcctId(String billingAcctId) {
        this.billingAcctId = billingAcctId;
        return this;
    }

    public void setBillingAcctId(String billingAcctId) {
        this.billingAcctId = billingAcctId;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Customer createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Customer createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public Customer lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Customer lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public Customer tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Customer getParentCustomer() {
        return parentCustomer;
    }

    public Customer parentCustomer(Customer customer) {
        this.parentCustomer = customer;
        return this;
    }

    public void setParentCustomer(Customer customer) {
        this.parentCustomer = customer;
    }

    public Set<CustDocument> getCustDocuments() {
        return custDocuments;
    }

    public Customer custDocuments(Set<CustDocument> custDocuments) {
        this.custDocuments = custDocuments;
        return this;
    }

    public Customer addCustDocuments(CustDocument custDocument) {
        this.custDocuments.add(custDocument);
        custDocument.setCustomer(this);
        return this;
    }

    public Customer removeCustDocuments(CustDocument custDocument) {
        this.custDocuments.remove(custDocument);
        custDocument.setCustomer(null);
        return this;
    }

    public void setCustDocuments(Set<CustDocument> custDocuments) {
        this.custDocuments = custDocuments;
    }

    public Set<CustCommOptoutMaster> getCustCommOptouts() {
        return custCommOptouts;
    }

    public Customer custCommOptouts(Set<CustCommOptoutMaster> custCommOptoutMasters) {
        this.custCommOptouts = custCommOptoutMasters;
        return this;
    }

    public Customer addCustCommOptouts(CustCommOptoutMaster custCommOptoutMaster) {
        this.custCommOptouts.add(custCommOptoutMaster);
        custCommOptoutMaster.setCustomer(this);
        return this;
    }

    public Customer removeCustCommOptouts(CustCommOptoutMaster custCommOptoutMaster) {
        this.custCommOptouts.remove(custCommOptoutMaster);
        custCommOptoutMaster.setCustomer(null);
        return this;
    }

    public void setCustCommOptouts(Set<CustCommOptoutMaster> custCommOptoutMasters) {
        this.custCommOptouts = custCommOptoutMasters;
    }

    public Set<CustContact> getCustContacts() {
        return custContacts;
    }

    public Customer custContacts(Set<CustContact> custContacts) {
        this.custContacts = custContacts;
        return this;
    }

    public Customer addCustContacts(CustContact custContact) {
        this.custContacts.add(custContact);
        custContact.setCustomer(this);
        return this;
    }

    public Customer removeCustContacts(CustContact custContact) {
        this.custContacts.remove(custContact);
        custContact.setCustomer(null);
        return this;
    }

    public void setCustContacts(Set<CustContact> custContacts) {
        this.custContacts = custContacts;
    }

    public Set<CustAddress> getCustAddresses() {
        return custAddresses;
    }

    public Customer custAddresses(Set<CustAddress> custAddresses) {
        this.custAddresses = custAddresses;
        return this;
    }

    public Customer addCustAddresses(CustAddress custAddress) {
        this.custAddresses.add(custAddress);
        custAddress.setCustomer(this);
        return this;
    }

    public Customer removeCustAddresses(CustAddress custAddress) {
        this.custAddresses.remove(custAddress);
        custAddress.setCustomer(null);
        return this;
    }

    public void setCustAddresses(Set<CustAddress> custAddresses) {
        this.custAddresses = custAddresses;
    }

    public Set<SubscriptionGroup> getSubscriptionGroups() {
        return subscriptionGroups;
    }

    public Customer subscriptionGroups(Set<SubscriptionGroup> subscriptionGroups) {
        this.subscriptionGroups = subscriptionGroups;
        return this;
    }

    public Customer addSubscriptionGroups(SubscriptionGroup subscriptionGroup) {
        this.subscriptionGroups.add(subscriptionGroup);
        subscriptionGroup.setCustomer(this);
        return this;
    }

    public Customer removeSubscriptionGroups(SubscriptionGroup subscriptionGroup) {
        this.subscriptionGroups.remove(subscriptionGroup);
        subscriptionGroup.setCustomer(null);
        return this;
    }

    public void setSubscriptionGroups(Set<SubscriptionGroup> subscriptionGroups) {
        this.subscriptionGroups = subscriptionGroups;
    }

    public Set<CustSubscription> getSubscriptions() {
        return subscriptions;
    }

    public Customer subscriptions(Set<CustSubscription> custSubscriptions) {
        this.subscriptions = custSubscriptions;
        return this;
    }

    public Customer addSubscriptions(CustSubscription custSubscription) {
        this.subscriptions.add(custSubscription);
        custSubscription.setCustomer(this);
        return this;
    }

    public Customer removeSubscriptions(CustSubscription custSubscription) {
        this.subscriptions.remove(custSubscription);
        custSubscription.setCustomer(null);
        return this;
    }

    public void setSubscriptions(Set<CustSubscription> custSubscriptions) {
        this.subscriptions = custSubscriptions;
    }

    public Set<OrderMaster> getOrderMasters() {
        return orderMasters;
    }

    public Customer orderMasters(Set<OrderMaster> orderMasters) {
        this.orderMasters = orderMasters;
        return this;
    }

    public Customer addOrderMasters(OrderMaster orderMaster) {
        this.orderMasters.add(orderMaster);
        orderMaster.setCustomer(this);
        return this;
    }

    public Customer removeOrderMasters(OrderMaster orderMaster) {
        this.orderMasters.remove(orderMaster);
        orderMaster.setCustomer(null);
        return this;
    }

    public void setOrderMasters(Set<OrderMaster> orderMasters) {
        this.orderMasters = orderMasters;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", custAcctId='" + getCustAcctId() + "'" +
            ", parentCustAcctId='" + getParentCustAcctId() + "'" +
            ", acctStatus='" + getAcctStatus() + "'" +
            ", acctStartDate='" + getAcctStartDate() + "'" +
            ", acctEndDate='" + getAcctEndDate() + "'" +
            ", cabsAcctId='" + getCabsAcctId() + "'" +
            ", title='" + getTitle() + "'" +
            ", givenName='" + getGivenName() + "'" +
            ", familyName='" + getFamilyName() + "'" +
            ", givenNameChi='" + getGivenNameChi() + "'" +
            ", familyNameChi='" + getFamilyNameChi() + "'" +
            ", aliasName='" + getAliasName() + "'" +
            ", gender='" + getGender() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", idType='" + getIdType() + "'" +
            ", idNumber='" + getIdNumber() + "'" +
            ", companyNameEng='" + getCompanyNameEng() + "'" +
            ", companyNameChi='" + getCompanyNameChi() + "'" +
            ", unlimitedCompany='" + isUnlimitedCompany() + "'" +
            ", lang='" + getLang() + "'" +
            ", selfCareUserId='" + getSelfCareUserId() + "'" +
            ", selfCarePassword='" + getSelfCarePassword() + "'" +
            ", ivrPin='" + getIvrPin() + "'" +
            ", maritialStatus='" + getMaritialStatus() + "'" +
            ", customerSegment='" + getCustomerSegment() + "'" +
            ", customerClass='" + getCustomerClass() + "'" +
            ", billingAcctId='" + getBillingAcctId() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
