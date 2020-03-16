package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.hthk.crm.domain.enumeration.DocType;

/**
 * A CustDocument.
 */
@Document(collection = "cust_document")
public class CustDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("cust_doc_id")
    private String custDocId;

    @Field("cust_acct_id")
    private String custAcctId;

    @Field("subscription_id")
    private String subscriptionId;

    @NotNull
    @Field("doc_type")
    private DocType docType;

    @Field("doc_id_number")
    private String docIdNumber;

    @Field("doc_data_store_id")
    private Long docDataStoreId;

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
    @Field("docDataStore")
    private CustDocDataStore docDataStore;

    @DBRef
    @Field("customer")
    @JsonIgnoreProperties("custDocuments")
    private Customer customer;

    @DBRef
    @Field("custSubscription")
    @JsonIgnoreProperties("custDocuments")
    private CustSubscription custSubscription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustDocId() {
        return custDocId;
    }

    public CustDocument custDocId(String custDocId) {
        this.custDocId = custDocId;
        return this;
    }

    public void setCustDocId(String custDocId) {
        this.custDocId = custDocId;
    }

    public String getCustAcctId() {
        return custAcctId;
    }

    public CustDocument custAcctId(String custAcctId) {
        this.custAcctId = custAcctId;
        return this;
    }

    public void setCustAcctId(String custAcctId) {
        this.custAcctId = custAcctId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public CustDocument subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public DocType getDocType() {
        return docType;
    }

    public CustDocument docType(DocType docType) {
        this.docType = docType;
        return this;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public String getDocIdNumber() {
        return docIdNumber;
    }

    public CustDocument docIdNumber(String docIdNumber) {
        this.docIdNumber = docIdNumber;
        return this;
    }

    public void setDocIdNumber(String docIdNumber) {
        this.docIdNumber = docIdNumber;
    }

    public Long getDocDataStoreId() {
        return docDataStoreId;
    }

    public CustDocument docDataStoreId(Long docDataStoreId) {
        this.docDataStoreId = docDataStoreId;
        return this;
    }

    public void setDocDataStoreId(Long docDataStoreId) {
        this.docDataStoreId = docDataStoreId;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public CustDocument createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CustDocument createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public CustDocument lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public CustDocument lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public CustDocument tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public CustDocDataStore getDocDataStore() {
        return docDataStore;
    }

    public CustDocument docDataStore(CustDocDataStore custDocDataStore) {
        this.docDataStore = custDocDataStore;
        return this;
    }

    public void setDocDataStore(CustDocDataStore custDocDataStore) {
        this.docDataStore = custDocDataStore;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustDocument customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustSubscription getCustSubscription() {
        return custSubscription;
    }

    public CustDocument custSubscription(CustSubscription custSubscription) {
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
        if (!(o instanceof CustDocument)) {
            return false;
        }
        return id != null && id.equals(((CustDocument) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CustDocument{" +
            "id=" + getId() +
            ", custDocId='" + getCustDocId() + "'" +
            ", custAcctId='" + getCustAcctId() + "'" +
            ", subscriptionId='" + getSubscriptionId() + "'" +
            ", docType='" + getDocType() + "'" +
            ", docIdNumber='" + getDocIdNumber() + "'" +
            ", docDataStoreId=" + getDocDataStoreId() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
