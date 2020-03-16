package com.hthk.crm.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.hthk.crm.domain.enumeration.RecordStatus;

/**
 * A SimInventory.
 */
@Document(collection = "sim_inventory")
public class SimInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("iccid")
    private String iccid;

    @Field("imsi")
    private String imsi;

    @Field("ki")
    private String ki;

    @Field("k_4_sno")
    private String k4sno;

    @Field("opsno")
    private String opsno;

    @Field("offer_id")
    private String offerId;

    @Field("offer_name")
    private String offerName;

    @Field("status")
    private RecordStatus status;

    @Field("offer_valid_from_date")
    private String offerValidFromDate;

    @Field("offer_valid_to_date")
    private String offerValidToDate;

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

    public String getIccid() {
        return iccid;
    }

    public SimInventory iccid(String iccid) {
        this.iccid = iccid;
        return this;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getImsi() {
        return imsi;
    }

    public SimInventory imsi(String imsi) {
        this.imsi = imsi;
        return this;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getKi() {
        return ki;
    }

    public SimInventory ki(String ki) {
        this.ki = ki;
        return this;
    }

    public void setKi(String ki) {
        this.ki = ki;
    }

    public String getk4sno() {
        return k4sno;
    }

    public SimInventory k4sno(String k4sno) {
        this.k4sno = k4sno;
        return this;
    }

    public void setk4sno(String k4sno) {
        this.k4sno = k4sno;
    }

    public String getOpsno() {
        return opsno;
    }

    public SimInventory opsno(String opsno) {
        this.opsno = opsno;
        return this;
    }

    public void setOpsno(String opsno) {
        this.opsno = opsno;
    }

    public String getOfferId() {
        return offerId;
    }

    public SimInventory offerId(String offerId) {
        this.offerId = offerId;
        return this;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public SimInventory offerName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public RecordStatus getStatus() {
        return status;
    }

    public SimInventory status(RecordStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }

    public String getOfferValidFromDate() {
        return offerValidFromDate;
    }

    public SimInventory offerValidFromDate(String offerValidFromDate) {
        this.offerValidFromDate = offerValidFromDate;
        return this;
    }

    public void setOfferValidFromDate(String offerValidFromDate) {
        this.offerValidFromDate = offerValidFromDate;
    }

    public String getOfferValidToDate() {
        return offerValidToDate;
    }

    public SimInventory offerValidToDate(String offerValidToDate) {
        this.offerValidToDate = offerValidToDate;
        return this;
    }

    public void setOfferValidToDate(String offerValidToDate) {
        this.offerValidToDate = offerValidToDate;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public SimInventory createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public SimInventory createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public SimInventory lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public SimInventory lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public SimInventory tenantId(String tenantId) {
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
        if (!(o instanceof SimInventory)) {
            return false;
        }
        return id != null && id.equals(((SimInventory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SimInventory{" +
            "id=" + getId() +
            ", iccid='" + getIccid() + "'" +
            ", imsi='" + getImsi() + "'" +
            ", ki='" + getKi() + "'" +
            ", k4sno='" + getk4sno() + "'" +
            ", opsno='" + getOpsno() + "'" +
            ", offerId='" + getOfferId() + "'" +
            ", offerName='" + getOfferName() + "'" +
            ", status='" + getStatus() + "'" +
            ", offerValidFromDate='" + getOfferValidFromDate() + "'" +
            ", offerValidToDate='" + getOfferValidToDate() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
