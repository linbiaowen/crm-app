package com.hthk.crm.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A ModelCategory.
 */
@Document(collection = "model_category")
public class ModelCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("model_cate")
    private String modelCate;

    @Field("parent_model_cate")
    private String parentModelCate;

    @Field("model_cate_desc")
    private String modelCateDesc;

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

    public String getModelCate() {
        return modelCate;
    }

    public ModelCategory modelCate(String modelCate) {
        this.modelCate = modelCate;
        return this;
    }

    public void setModelCate(String modelCate) {
        this.modelCate = modelCate;
    }

    public String getParentModelCate() {
        return parentModelCate;
    }

    public ModelCategory parentModelCate(String parentModelCate) {
        this.parentModelCate = parentModelCate;
        return this;
    }

    public void setParentModelCate(String parentModelCate) {
        this.parentModelCate = parentModelCate;
    }

    public String getModelCateDesc() {
        return modelCateDesc;
    }

    public ModelCategory modelCateDesc(String modelCateDesc) {
        this.modelCateDesc = modelCateDesc;
        return this;
    }

    public void setModelCateDesc(String modelCateDesc) {
        this.modelCateDesc = modelCateDesc;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ModelCategory createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ModelCategory createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public ModelCategory lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public ModelCategory lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public ModelCategory tenantId(String tenantId) {
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
        if (!(o instanceof ModelCategory)) {
            return false;
        }
        return id != null && id.equals(((ModelCategory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ModelCategory{" +
            "id=" + getId() +
            ", modelCate='" + getModelCate() + "'" +
            ", parentModelCate='" + getParentModelCate() + "'" +
            ", modelCateDesc='" + getModelCateDesc() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
