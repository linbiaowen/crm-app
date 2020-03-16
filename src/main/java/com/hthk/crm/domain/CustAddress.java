package com.hthk.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.hthk.crm.domain.enumeration.AccountType;

import com.hthk.crm.domain.enumeration.AddressType;

import com.hthk.crm.domain.enumeration.Language;

/**
 * A CustAddress.
 */
@Document(collection = "cust_address")
public class CustAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("address_id")
    private Long addressId;

    @NotNull
    @Field("account_id")
    private String accountId;

    @NotNull
    @Field("account_type")
    private AccountType accountType;

    @NotNull
    @Field("address_type")
    private AddressType addressType;

    @NotNull
    @Field("address_lang")
    private Language addressLang;

    @NotNull
    @Field("formatted_address")
    private Boolean formattedAddress;

    @Field("room")
    private String room;

    @Field("floor")
    private String floor;

    @Field("block")
    private String block;

    @Field("building")
    private String building;

    @Field("street_estate")
    private String streetEstate;

    @Field("district")
    private String district;

    @Field("region")
    private String region;

    @Field("address_1")
    private String address1;

    @Field("address_2")
    private String address2;

    @Field("address_3")
    private String address3;

    @Field("address_4")
    private String address4;

    @Field("address_5")
    private String address5;

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
    @Field("customer")
    @JsonIgnoreProperties("custAddresses")
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAddressId() {
        return addressId;
    }

    public CustAddress addressId(Long addressId) {
        this.addressId = addressId;
        return this;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAccountId() {
        return accountId;
    }

    public CustAddress accountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public CustAddress accountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public CustAddress addressType(AddressType addressType) {
        this.addressType = addressType;
        return this;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public Language getAddressLang() {
        return addressLang;
    }

    public CustAddress addressLang(Language addressLang) {
        this.addressLang = addressLang;
        return this;
    }

    public void setAddressLang(Language addressLang) {
        this.addressLang = addressLang;
    }

    public Boolean isFormattedAddress() {
        return formattedAddress;
    }

    public CustAddress formattedAddress(Boolean formattedAddress) {
        this.formattedAddress = formattedAddress;
        return this;
    }

    public void setFormattedAddress(Boolean formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getRoom() {
        return room;
    }

    public CustAddress room(String room) {
        this.room = room;
        return this;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getFloor() {
        return floor;
    }

    public CustAddress floor(String floor) {
        this.floor = floor;
        return this;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBlock() {
        return block;
    }

    public CustAddress block(String block) {
        this.block = block;
        return this;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getBuilding() {
        return building;
    }

    public CustAddress building(String building) {
        this.building = building;
        return this;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStreetEstate() {
        return streetEstate;
    }

    public CustAddress streetEstate(String streetEstate) {
        this.streetEstate = streetEstate;
        return this;
    }

    public void setStreetEstate(String streetEstate) {
        this.streetEstate = streetEstate;
    }

    public String getDistrict() {
        return district;
    }

    public CustAddress district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRegion() {
        return region;
    }

    public CustAddress region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress1() {
        return address1;
    }

    public CustAddress address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public CustAddress address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public CustAddress address3(String address3) {
        this.address3 = address3;
        return this;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public CustAddress address4(String address4) {
        this.address4 = address4;
        return this;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getAddress5() {
        return address5;
    }

    public CustAddress address5(String address5) {
        this.address5 = address5;
        return this;
    }

    public void setAddress5(String address5) {
        this.address5 = address5;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public CustAddress createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CustAddress createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public CustAddress lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public CustAddress lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public CustAddress tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustAddress customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustAddress)) {
            return false;
        }
        return id != null && id.equals(((CustAddress) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CustAddress{" +
            "id=" + getId() +
            ", addressId=" + getAddressId() +
            ", accountId='" + getAccountId() + "'" +
            ", accountType='" + getAccountType() + "'" +
            ", addressType='" + getAddressType() + "'" +
            ", addressLang='" + getAddressLang() + "'" +
            ", formattedAddress='" + isFormattedAddress() + "'" +
            ", room='" + getRoom() + "'" +
            ", floor='" + getFloor() + "'" +
            ", block='" + getBlock() + "'" +
            ", building='" + getBuilding() + "'" +
            ", streetEstate='" + getStreetEstate() + "'" +
            ", district='" + getDistrict() + "'" +
            ", region='" + getRegion() + "'" +
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", address3='" + getAddress3() + "'" +
            ", address4='" + getAddress4() + "'" +
            ", address5='" + getAddress5() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
