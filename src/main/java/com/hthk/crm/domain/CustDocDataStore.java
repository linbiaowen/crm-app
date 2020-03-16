package com.hthk.crm.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A CustDocDataStore.
 */
@Document(collection = "cust_doc_data_store")
public class CustDocDataStore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("doc_data_store_id")
    private Long docDataStoreId;

    @Field("document_data")
    private byte[] documentData;

    @Field("document_data_content_type")
    private String documentDataContentType;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDocDataStoreId() {
        return docDataStoreId;
    }

    public CustDocDataStore docDataStoreId(Long docDataStoreId) {
        this.docDataStoreId = docDataStoreId;
        return this;
    }

    public void setDocDataStoreId(Long docDataStoreId) {
        this.docDataStoreId = docDataStoreId;
    }

    public byte[] getDocumentData() {
        return documentData;
    }

    public CustDocDataStore documentData(byte[] documentData) {
        this.documentData = documentData;
        return this;
    }

    public void setDocumentData(byte[] documentData) {
        this.documentData = documentData;
    }

    public String getDocumentDataContentType() {
        return documentDataContentType;
    }

    public CustDocDataStore documentDataContentType(String documentDataContentType) {
        this.documentDataContentType = documentDataContentType;
        return this;
    }

    public void setDocumentDataContentType(String documentDataContentType) {
        this.documentDataContentType = documentDataContentType;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public CustDocDataStore createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CustDocDataStore createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public CustDocDataStore lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public CustDocDataStore lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public CustDocDataStore tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustDocDataStore)) {
            return false;
        }
        return id != null && id.equals(((CustDocDataStore) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CustDocDataStore{" +
            "id=" + getId() +
            ", docDataStoreId=" + getDocDataStoreId() +
            ", documentData='" + getDocumentData() + "'" +
            ", documentDataContentType='" + getDocumentDataContentType() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
