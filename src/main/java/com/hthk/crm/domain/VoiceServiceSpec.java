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
 * A VoiceServiceSpec.
 */
@Document(collection = "voice_service_spec")
public class VoiceServiceSpec implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("voice_spec_id")
    private Long voiceSpecId;

    @NotNull
    @Field("service_spec_id")
    private String serviceSpecId;

    @Field("roaming_incoming_enabled")
    private Boolean roamingIncomingEnabled;

    @Field("roaming_outgoing_enabled")
    private Boolean roamingOutgoingEnabled;

    @Field("idd_enabled")
    private Boolean iddEnabled;

    @Field("idd_options")
    private String iddOptions;

    @Field("call_forward_enabled")
    private Boolean callForwardEnabled;

    @Field("call_waiting_enabled")
    private Boolean callWaitingEnabled;

    @Field("clip_enabled")
    private Boolean clipEnabled;

    @Field("call_barring_enabled")
    private Boolean callBarringEnabled;

    @Field("mvrs_enabled")
    private Boolean mvrsEnabled;

    @Field("special_call_services")
    private String specialCallServices;

    @Field("call_routing_supported")
    private Boolean callRoutingSupported;

    @Field("prbt_supported")
    private Boolean prbtSupported;

    @Field("hrbt_supported")
    private Boolean hrbtSupported;

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

    public Long getVoiceSpecId() {
        return voiceSpecId;
    }

    public VoiceServiceSpec voiceSpecId(Long voiceSpecId) {
        this.voiceSpecId = voiceSpecId;
        return this;
    }

    public void setVoiceSpecId(Long voiceSpecId) {
        this.voiceSpecId = voiceSpecId;
    }

    public String getServiceSpecId() {
        return serviceSpecId;
    }

    public VoiceServiceSpec serviceSpecId(String serviceSpecId) {
        this.serviceSpecId = serviceSpecId;
        return this;
    }

    public void setServiceSpecId(String serviceSpecId) {
        this.serviceSpecId = serviceSpecId;
    }

    public Boolean isRoamingIncomingEnabled() {
        return roamingIncomingEnabled;
    }

    public VoiceServiceSpec roamingIncomingEnabled(Boolean roamingIncomingEnabled) {
        this.roamingIncomingEnabled = roamingIncomingEnabled;
        return this;
    }

    public void setRoamingIncomingEnabled(Boolean roamingIncomingEnabled) {
        this.roamingIncomingEnabled = roamingIncomingEnabled;
    }

    public Boolean isRoamingOutgoingEnabled() {
        return roamingOutgoingEnabled;
    }

    public VoiceServiceSpec roamingOutgoingEnabled(Boolean roamingOutgoingEnabled) {
        this.roamingOutgoingEnabled = roamingOutgoingEnabled;
        return this;
    }

    public void setRoamingOutgoingEnabled(Boolean roamingOutgoingEnabled) {
        this.roamingOutgoingEnabled = roamingOutgoingEnabled;
    }

    public Boolean isIddEnabled() {
        return iddEnabled;
    }

    public VoiceServiceSpec iddEnabled(Boolean iddEnabled) {
        this.iddEnabled = iddEnabled;
        return this;
    }

    public void setIddEnabled(Boolean iddEnabled) {
        this.iddEnabled = iddEnabled;
    }

    public String getIddOptions() {
        return iddOptions;
    }

    public VoiceServiceSpec iddOptions(String iddOptions) {
        this.iddOptions = iddOptions;
        return this;
    }

    public void setIddOptions(String iddOptions) {
        this.iddOptions = iddOptions;
    }

    public Boolean isCallForwardEnabled() {
        return callForwardEnabled;
    }

    public VoiceServiceSpec callForwardEnabled(Boolean callForwardEnabled) {
        this.callForwardEnabled = callForwardEnabled;
        return this;
    }

    public void setCallForwardEnabled(Boolean callForwardEnabled) {
        this.callForwardEnabled = callForwardEnabled;
    }

    public Boolean isCallWaitingEnabled() {
        return callWaitingEnabled;
    }

    public VoiceServiceSpec callWaitingEnabled(Boolean callWaitingEnabled) {
        this.callWaitingEnabled = callWaitingEnabled;
        return this;
    }

    public void setCallWaitingEnabled(Boolean callWaitingEnabled) {
        this.callWaitingEnabled = callWaitingEnabled;
    }

    public Boolean isClipEnabled() {
        return clipEnabled;
    }

    public VoiceServiceSpec clipEnabled(Boolean clipEnabled) {
        this.clipEnabled = clipEnabled;
        return this;
    }

    public void setClipEnabled(Boolean clipEnabled) {
        this.clipEnabled = clipEnabled;
    }

    public Boolean isCallBarringEnabled() {
        return callBarringEnabled;
    }

    public VoiceServiceSpec callBarringEnabled(Boolean callBarringEnabled) {
        this.callBarringEnabled = callBarringEnabled;
        return this;
    }

    public void setCallBarringEnabled(Boolean callBarringEnabled) {
        this.callBarringEnabled = callBarringEnabled;
    }

    public Boolean isMvrsEnabled() {
        return mvrsEnabled;
    }

    public VoiceServiceSpec mvrsEnabled(Boolean mvrsEnabled) {
        this.mvrsEnabled = mvrsEnabled;
        return this;
    }

    public void setMvrsEnabled(Boolean mvrsEnabled) {
        this.mvrsEnabled = mvrsEnabled;
    }

    public String getSpecialCallServices() {
        return specialCallServices;
    }

    public VoiceServiceSpec specialCallServices(String specialCallServices) {
        this.specialCallServices = specialCallServices;
        return this;
    }

    public void setSpecialCallServices(String specialCallServices) {
        this.specialCallServices = specialCallServices;
    }

    public Boolean isCallRoutingSupported() {
        return callRoutingSupported;
    }

    public VoiceServiceSpec callRoutingSupported(Boolean callRoutingSupported) {
        this.callRoutingSupported = callRoutingSupported;
        return this;
    }

    public void setCallRoutingSupported(Boolean callRoutingSupported) {
        this.callRoutingSupported = callRoutingSupported;
    }

    public Boolean isPrbtSupported() {
        return prbtSupported;
    }

    public VoiceServiceSpec prbtSupported(Boolean prbtSupported) {
        this.prbtSupported = prbtSupported;
        return this;
    }

    public void setPrbtSupported(Boolean prbtSupported) {
        this.prbtSupported = prbtSupported;
    }

    public Boolean isHrbtSupported() {
        return hrbtSupported;
    }

    public VoiceServiceSpec hrbtSupported(Boolean hrbtSupported) {
        this.hrbtSupported = hrbtSupported;
        return this;
    }

    public void setHrbtSupported(Boolean hrbtSupported) {
        this.hrbtSupported = hrbtSupported;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VoiceServiceSpec createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VoiceServiceSpec createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VoiceServiceSpec lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VoiceServiceSpec lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public VoiceServiceSpec tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public CfsServiceSpec getCfsServices() {
        return cfsServices;
    }

    public VoiceServiceSpec cfsServices(CfsServiceSpec cfsServiceSpec) {
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
        if (!(o instanceof VoiceServiceSpec)) {
            return false;
        }
        return id != null && id.equals(((VoiceServiceSpec) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "VoiceServiceSpec{" +
            "id=" + getId() +
            ", voiceSpecId=" + getVoiceSpecId() +
            ", serviceSpecId='" + getServiceSpecId() + "'" +
            ", roamingIncomingEnabled='" + isRoamingIncomingEnabled() + "'" +
            ", roamingOutgoingEnabled='" + isRoamingOutgoingEnabled() + "'" +
            ", iddEnabled='" + isIddEnabled() + "'" +
            ", iddOptions='" + getIddOptions() + "'" +
            ", callForwardEnabled='" + isCallForwardEnabled() + "'" +
            ", callWaitingEnabled='" + isCallWaitingEnabled() + "'" +
            ", clipEnabled='" + isClipEnabled() + "'" +
            ", callBarringEnabled='" + isCallBarringEnabled() + "'" +
            ", mvrsEnabled='" + isMvrsEnabled() + "'" +
            ", specialCallServices='" + getSpecialCallServices() + "'" +
            ", callRoutingSupported='" + isCallRoutingSupported() + "'" +
            ", prbtSupported='" + isPrbtSupported() + "'" +
            ", hrbtSupported='" + isHrbtSupported() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
