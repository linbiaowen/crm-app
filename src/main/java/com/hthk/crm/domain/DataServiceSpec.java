package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DataServiceSpec.
 */
@Document(collection = "data_service_spec")
public class DataServiceSpec implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("data_spec_id")
    private Long dataSpecId;

    @NotNull
    @Field("service_spec_id")
    private String serviceSpecId;

    @Field("max_entitlement")
    private String maxEntitlement;

    @Field("max_access_speed")
    private String maxAccessSpeed;

    @Field("throttled_speed")
    private String throttledSpeed;

    @Field("upstream_speed")
    private String upstreamSpeed;

    @Field("downstream_speed")
    private String downstreamSpeed;

    @Field("social_sites")
    private String socialSites;

    @Field("entertainment_pack_options")
    private String entertainmentPackOptions;

    @Field("roaming_data_speed")
    private String roamingDataSpeed;

    @Field("roaming_data_volume")
    private String roamingDataVolume;

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
    @Field("cfsServices")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private CfsServiceSpec cfsServices;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDataSpecId() {
        return dataSpecId;
    }

    public DataServiceSpec dataSpecId(Long dataSpecId) {
        this.dataSpecId = dataSpecId;
        return this;
    }

    public void setDataSpecId(Long dataSpecId) {
        this.dataSpecId = dataSpecId;
    }

    public String getServiceSpecId() {
        return serviceSpecId;
    }

    public DataServiceSpec serviceSpecId(String serviceSpecId) {
        this.serviceSpecId = serviceSpecId;
        return this;
    }

    public void setServiceSpecId(String serviceSpecId) {
        this.serviceSpecId = serviceSpecId;
    }

    public String getMaxEntitlement() {
        return maxEntitlement;
    }

    public DataServiceSpec maxEntitlement(String maxEntitlement) {
        this.maxEntitlement = maxEntitlement;
        return this;
    }

    public void setMaxEntitlement(String maxEntitlement) {
        this.maxEntitlement = maxEntitlement;
    }

    public String getMaxAccessSpeed() {
        return maxAccessSpeed;
    }

    public DataServiceSpec maxAccessSpeed(String maxAccessSpeed) {
        this.maxAccessSpeed = maxAccessSpeed;
        return this;
    }

    public void setMaxAccessSpeed(String maxAccessSpeed) {
        this.maxAccessSpeed = maxAccessSpeed;
    }

    public String getThrottledSpeed() {
        return throttledSpeed;
    }

    public DataServiceSpec throttledSpeed(String throttledSpeed) {
        this.throttledSpeed = throttledSpeed;
        return this;
    }

    public void setThrottledSpeed(String throttledSpeed) {
        this.throttledSpeed = throttledSpeed;
    }

    public String getUpstreamSpeed() {
        return upstreamSpeed;
    }

    public DataServiceSpec upstreamSpeed(String upstreamSpeed) {
        this.upstreamSpeed = upstreamSpeed;
        return this;
    }

    public void setUpstreamSpeed(String upstreamSpeed) {
        this.upstreamSpeed = upstreamSpeed;
    }

    public String getDownstreamSpeed() {
        return downstreamSpeed;
    }

    public DataServiceSpec downstreamSpeed(String downstreamSpeed) {
        this.downstreamSpeed = downstreamSpeed;
        return this;
    }

    public void setDownstreamSpeed(String downstreamSpeed) {
        this.downstreamSpeed = downstreamSpeed;
    }

    public String getSocialSites() {
        return socialSites;
    }

    public DataServiceSpec socialSites(String socialSites) {
        this.socialSites = socialSites;
        return this;
    }

    public void setSocialSites(String socialSites) {
        this.socialSites = socialSites;
    }

    public String getEntertainmentPackOptions() {
        return entertainmentPackOptions;
    }

    public DataServiceSpec entertainmentPackOptions(String entertainmentPackOptions) {
        this.entertainmentPackOptions = entertainmentPackOptions;
        return this;
    }

    public void setEntertainmentPackOptions(String entertainmentPackOptions) {
        this.entertainmentPackOptions = entertainmentPackOptions;
    }

    public String getRoamingDataSpeed() {
        return roamingDataSpeed;
    }

    public DataServiceSpec roamingDataSpeed(String roamingDataSpeed) {
        this.roamingDataSpeed = roamingDataSpeed;
        return this;
    }

    public void setRoamingDataSpeed(String roamingDataSpeed) {
        this.roamingDataSpeed = roamingDataSpeed;
    }

    public String getRoamingDataVolume() {
        return roamingDataVolume;
    }

    public DataServiceSpec roamingDataVolume(String roamingDataVolume) {
        this.roamingDataVolume = roamingDataVolume;
        return this;
    }

    public void setRoamingDataVolume(String roamingDataVolume) {
        this.roamingDataVolume = roamingDataVolume;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public DataServiceSpec createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public DataServiceSpec createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public DataServiceSpec lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public DataServiceSpec lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public DataServiceSpec tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public CfsServiceSpec getCfsServices() {
        return cfsServices;
    }

    public DataServiceSpec cfsServices(CfsServiceSpec cfsServiceSpec) {
        this.cfsServices = cfsServiceSpec;
        return this;
    }

    public void setCfsServices(CfsServiceSpec cfsServiceSpec) {
        this.cfsServices = cfsServiceSpec;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataServiceSpec)) {
            return false;
        }
        return id != null && id.equals(((DataServiceSpec) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DataServiceSpec{" +
            "id=" + getId() +
            ", dataSpecId=" + getDataSpecId() +
            ", serviceSpecId='" + getServiceSpecId() + "'" +
            ", maxEntitlement='" + getMaxEntitlement() + "'" +
            ", maxAccessSpeed='" + getMaxAccessSpeed() + "'" +
            ", throttledSpeed='" + getThrottledSpeed() + "'" +
            ", upstreamSpeed='" + getUpstreamSpeed() + "'" +
            ", downstreamSpeed='" + getDownstreamSpeed() + "'" +
            ", socialSites='" + getSocialSites() + "'" +
            ", entertainmentPackOptions='" + getEntertainmentPackOptions() + "'" +
            ", roamingDataSpeed='" + getRoamingDataSpeed() + "'" +
            ", roamingDataVolume='" + getRoamingDataVolume() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
