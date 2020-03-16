package com.hthk.crm.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A ModelGroup.
 */
@Document(collection = "model_group")
public class ModelGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("model_group")
    private String modelGroup;

    @Field("group_desc")
    private String groupDesc;

    @Field("list_price")
    private BigDecimal listPrice;

    @Field("brand")
    private String brand;

    @Field("model")
    private String model;

    @Field("orig_country")
    private String origCountry;

    @Field("network")
    private String network;

    @Field("camera")
    private String camera;

    @Field("mem_card_slot")
    private String memCardSlot;

    @Field("data_transfer")
    private String dataTransfer;

    @Field("warranty")
    private String warranty;

    @Field("warranty_provider")
    private String warrantyProvider;

    @Field("model_cate")
    private String modelCate;

    @Field("remarks")
    private String remarks;

    @Field("remarks_end_date")
    private String remarksEndDate;

    @Field("brand_chi")
    private String brandChi;

    @Field("model_chi")
    private String modelChi;

    @Field("orig_country_chi")
    private String origCountryChi;

    @Field("network_chi")
    private String networkChi;

    @Field("camera_chi")
    private String cameraChi;

    @Field("mem_card_slot_chi")
    private String memCardSlotChi;

    @Field("data_transfer_chi")
    private String dataTransferChi;

    @Field("warranty_chi")
    private String warrantyChi;

    @Field("warranty_provider_chi")
    private String warrantyProviderChi;

    @Field("model_cate_chi")
    private String modelCateChi;

    @Field("remarks_chi")
    private String remarksChi;

    @Field("remarks_chi_end_date")
    private String remarksChiEndDate;

    @Field("coupon_flag")
    private String couponFlag;

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

    public String getModelGroup() {
        return modelGroup;
    }

    public ModelGroup modelGroup(String modelGroup) {
        this.modelGroup = modelGroup;
        return this;
    }

    public void setModelGroup(String modelGroup) {
        this.modelGroup = modelGroup;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public ModelGroup groupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
        return this;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public ModelGroup listPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
        return this;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public String getBrand() {
        return brand;
    }

    public ModelGroup brand(String brand) {
        this.brand = brand;
        return this;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public ModelGroup model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOrigCountry() {
        return origCountry;
    }

    public ModelGroup origCountry(String origCountry) {
        this.origCountry = origCountry;
        return this;
    }

    public void setOrigCountry(String origCountry) {
        this.origCountry = origCountry;
    }

    public String getNetwork() {
        return network;
    }

    public ModelGroup network(String network) {
        this.network = network;
        return this;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getCamera() {
        return camera;
    }

    public ModelGroup camera(String camera) {
        this.camera = camera;
        return this;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getMemCardSlot() {
        return memCardSlot;
    }

    public ModelGroup memCardSlot(String memCardSlot) {
        this.memCardSlot = memCardSlot;
        return this;
    }

    public void setMemCardSlot(String memCardSlot) {
        this.memCardSlot = memCardSlot;
    }

    public String getDataTransfer() {
        return dataTransfer;
    }

    public ModelGroup dataTransfer(String dataTransfer) {
        this.dataTransfer = dataTransfer;
        return this;
    }

    public void setDataTransfer(String dataTransfer) {
        this.dataTransfer = dataTransfer;
    }

    public String getWarranty() {
        return warranty;
    }

    public ModelGroup warranty(String warranty) {
        this.warranty = warranty;
        return this;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getWarrantyProvider() {
        return warrantyProvider;
    }

    public ModelGroup warrantyProvider(String warrantyProvider) {
        this.warrantyProvider = warrantyProvider;
        return this;
    }

    public void setWarrantyProvider(String warrantyProvider) {
        this.warrantyProvider = warrantyProvider;
    }

    public String getModelCate() {
        return modelCate;
    }

    public ModelGroup modelCate(String modelCate) {
        this.modelCate = modelCate;
        return this;
    }

    public void setModelCate(String modelCate) {
        this.modelCate = modelCate;
    }

    public String getRemarks() {
        return remarks;
    }

    public ModelGroup remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarksEndDate() {
        return remarksEndDate;
    }

    public ModelGroup remarksEndDate(String remarksEndDate) {
        this.remarksEndDate = remarksEndDate;
        return this;
    }

    public void setRemarksEndDate(String remarksEndDate) {
        this.remarksEndDate = remarksEndDate;
    }

    public String getBrandChi() {
        return brandChi;
    }

    public ModelGroup brandChi(String brandChi) {
        this.brandChi = brandChi;
        return this;
    }

    public void setBrandChi(String brandChi) {
        this.brandChi = brandChi;
    }

    public String getModelChi() {
        return modelChi;
    }

    public ModelGroup modelChi(String modelChi) {
        this.modelChi = modelChi;
        return this;
    }

    public void setModelChi(String modelChi) {
        this.modelChi = modelChi;
    }

    public String getOrigCountryChi() {
        return origCountryChi;
    }

    public ModelGroup origCountryChi(String origCountryChi) {
        this.origCountryChi = origCountryChi;
        return this;
    }

    public void setOrigCountryChi(String origCountryChi) {
        this.origCountryChi = origCountryChi;
    }

    public String getNetworkChi() {
        return networkChi;
    }

    public ModelGroup networkChi(String networkChi) {
        this.networkChi = networkChi;
        return this;
    }

    public void setNetworkChi(String networkChi) {
        this.networkChi = networkChi;
    }

    public String getCameraChi() {
        return cameraChi;
    }

    public ModelGroup cameraChi(String cameraChi) {
        this.cameraChi = cameraChi;
        return this;
    }

    public void setCameraChi(String cameraChi) {
        this.cameraChi = cameraChi;
    }

    public String getMemCardSlotChi() {
        return memCardSlotChi;
    }

    public ModelGroup memCardSlotChi(String memCardSlotChi) {
        this.memCardSlotChi = memCardSlotChi;
        return this;
    }

    public void setMemCardSlotChi(String memCardSlotChi) {
        this.memCardSlotChi = memCardSlotChi;
    }

    public String getDataTransferChi() {
        return dataTransferChi;
    }

    public ModelGroup dataTransferChi(String dataTransferChi) {
        this.dataTransferChi = dataTransferChi;
        return this;
    }

    public void setDataTransferChi(String dataTransferChi) {
        this.dataTransferChi = dataTransferChi;
    }

    public String getWarrantyChi() {
        return warrantyChi;
    }

    public ModelGroup warrantyChi(String warrantyChi) {
        this.warrantyChi = warrantyChi;
        return this;
    }

    public void setWarrantyChi(String warrantyChi) {
        this.warrantyChi = warrantyChi;
    }

    public String getWarrantyProviderChi() {
        return warrantyProviderChi;
    }

    public ModelGroup warrantyProviderChi(String warrantyProviderChi) {
        this.warrantyProviderChi = warrantyProviderChi;
        return this;
    }

    public void setWarrantyProviderChi(String warrantyProviderChi) {
        this.warrantyProviderChi = warrantyProviderChi;
    }

    public String getModelCateChi() {
        return modelCateChi;
    }

    public ModelGroup modelCateChi(String modelCateChi) {
        this.modelCateChi = modelCateChi;
        return this;
    }

    public void setModelCateChi(String modelCateChi) {
        this.modelCateChi = modelCateChi;
    }

    public String getRemarksChi() {
        return remarksChi;
    }

    public ModelGroup remarksChi(String remarksChi) {
        this.remarksChi = remarksChi;
        return this;
    }

    public void setRemarksChi(String remarksChi) {
        this.remarksChi = remarksChi;
    }

    public String getRemarksChiEndDate() {
        return remarksChiEndDate;
    }

    public ModelGroup remarksChiEndDate(String remarksChiEndDate) {
        this.remarksChiEndDate = remarksChiEndDate;
        return this;
    }

    public void setRemarksChiEndDate(String remarksChiEndDate) {
        this.remarksChiEndDate = remarksChiEndDate;
    }

    public String getCouponFlag() {
        return couponFlag;
    }

    public ModelGroup couponFlag(String couponFlag) {
        this.couponFlag = couponFlag;
        return this;
    }

    public void setCouponFlag(String couponFlag) {
        this.couponFlag = couponFlag;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ModelGroup createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ModelGroup createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public ModelGroup lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public ModelGroup lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public ModelGroup tenantId(String tenantId) {
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
        if (!(o instanceof ModelGroup)) {
            return false;
        }
        return id != null && id.equals(((ModelGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ModelGroup{" +
            "id=" + getId() +
            ", modelGroup='" + getModelGroup() + "'" +
            ", groupDesc='" + getGroupDesc() + "'" +
            ", listPrice=" + getListPrice() +
            ", brand='" + getBrand() + "'" +
            ", model='" + getModel() + "'" +
            ", origCountry='" + getOrigCountry() + "'" +
            ", network='" + getNetwork() + "'" +
            ", camera='" + getCamera() + "'" +
            ", memCardSlot='" + getMemCardSlot() + "'" +
            ", dataTransfer='" + getDataTransfer() + "'" +
            ", warranty='" + getWarranty() + "'" +
            ", warrantyProvider='" + getWarrantyProvider() + "'" +
            ", modelCate='" + getModelCate() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", remarksEndDate='" + getRemarksEndDate() + "'" +
            ", brandChi='" + getBrandChi() + "'" +
            ", modelChi='" + getModelChi() + "'" +
            ", origCountryChi='" + getOrigCountryChi() + "'" +
            ", networkChi='" + getNetworkChi() + "'" +
            ", cameraChi='" + getCameraChi() + "'" +
            ", memCardSlotChi='" + getMemCardSlotChi() + "'" +
            ", dataTransferChi='" + getDataTransferChi() + "'" +
            ", warrantyChi='" + getWarrantyChi() + "'" +
            ", warrantyProviderChi='" + getWarrantyProviderChi() + "'" +
            ", modelCateChi='" + getModelCateChi() + "'" +
            ", remarksChi='" + getRemarksChi() + "'" +
            ", remarksChiEndDate='" + getRemarksChiEndDate() + "'" +
            ", couponFlag='" + getCouponFlag() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
