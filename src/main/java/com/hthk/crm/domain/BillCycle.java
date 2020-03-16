package com.hthk.crm.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A BillCycle.
 */
@Document(collection = "bill_cycle")
public class BillCycle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("bill_cycle_id")
    private Integer billCycleId;

    @Field("bill_cycle_desc")
    private String billCycleDesc;

    @NotNull
    @Field("bill_cycle")
    private Integer billCycle;

    @Field("bill_frequency")
    private String billFrequency;

    @Field("bill_cycle_start_date")
    private Instant billCycleStartDate;

    @Field("bill_cycle_end_date")
    private Instant billCycleEndDate;

    @Field("due_date_offset")
    private Integer dueDateOffset;

    @Field("direct_debit_process_day")
    private Integer directDebitProcessDay;

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

    public Integer getBillCycleId() {
        return billCycleId;
    }

    public BillCycle billCycleId(Integer billCycleId) {
        this.billCycleId = billCycleId;
        return this;
    }

    public void setBillCycleId(Integer billCycleId) {
        this.billCycleId = billCycleId;
    }

    public String getBillCycleDesc() {
        return billCycleDesc;
    }

    public BillCycle billCycleDesc(String billCycleDesc) {
        this.billCycleDesc = billCycleDesc;
        return this;
    }

    public void setBillCycleDesc(String billCycleDesc) {
        this.billCycleDesc = billCycleDesc;
    }

    public Integer getBillCycle() {
        return billCycle;
    }

    public BillCycle billCycle(Integer billCycle) {
        this.billCycle = billCycle;
        return this;
    }

    public void setBillCycle(Integer billCycle) {
        this.billCycle = billCycle;
    }

    public String getBillFrequency() {
        return billFrequency;
    }

    public BillCycle billFrequency(String billFrequency) {
        this.billFrequency = billFrequency;
        return this;
    }

    public void setBillFrequency(String billFrequency) {
        this.billFrequency = billFrequency;
    }

    public Instant getBillCycleStartDate() {
        return billCycleStartDate;
    }

    public BillCycle billCycleStartDate(Instant billCycleStartDate) {
        this.billCycleStartDate = billCycleStartDate;
        return this;
    }

    public void setBillCycleStartDate(Instant billCycleStartDate) {
        this.billCycleStartDate = billCycleStartDate;
    }

    public Instant getBillCycleEndDate() {
        return billCycleEndDate;
    }

    public BillCycle billCycleEndDate(Instant billCycleEndDate) {
        this.billCycleEndDate = billCycleEndDate;
        return this;
    }

    public void setBillCycleEndDate(Instant billCycleEndDate) {
        this.billCycleEndDate = billCycleEndDate;
    }

    public Integer getDueDateOffset() {
        return dueDateOffset;
    }

    public BillCycle dueDateOffset(Integer dueDateOffset) {
        this.dueDateOffset = dueDateOffset;
        return this;
    }

    public void setDueDateOffset(Integer dueDateOffset) {
        this.dueDateOffset = dueDateOffset;
    }

    public Integer getDirectDebitProcessDay() {
        return directDebitProcessDay;
    }

    public BillCycle directDebitProcessDay(Integer directDebitProcessDay) {
        this.directDebitProcessDay = directDebitProcessDay;
        return this;
    }

    public void setDirectDebitProcessDay(Integer directDebitProcessDay) {
        this.directDebitProcessDay = directDebitProcessDay;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public BillCycle createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public BillCycle createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public BillCycle lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public BillCycle lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public BillCycle tenantId(String tenantId) {
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
        if (!(o instanceof BillCycle)) {
            return false;
        }
        return id != null && id.equals(((BillCycle) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BillCycle{" +
            "id=" + getId() +
            ", billCycleId=" + getBillCycleId() +
            ", billCycleDesc='" + getBillCycleDesc() + "'" +
            ", billCycle=" + getBillCycle() +
            ", billFrequency='" + getBillFrequency() + "'" +
            ", billCycleStartDate='" + getBillCycleStartDate() + "'" +
            ", billCycleEndDate='" + getBillCycleEndDate() + "'" +
            ", dueDateOffset=" + getDueDateOffset() +
            ", directDebitProcessDay=" + getDirectDebitProcessDay() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
