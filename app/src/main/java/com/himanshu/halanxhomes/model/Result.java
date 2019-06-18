
package com.himanshu.halanxhomes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("furnish_type")
    @Expose
    private String furnishType;
    @SerializedName("house_size")
    @Expose
    private String houseSize;
    @SerializedName("cover_pic_url")
    @Expose
    private String coverPicUrl;
    @SerializedName("rent_from")
    @Expose
    private Integer rentFrom;
    @SerializedName("security_deposit_from")
    @Expose
    private Integer securityDepositFrom;
    @SerializedName("available_flat_count")
    @Expose
    private Integer availableFlatCount;
    @SerializedName("available_room_count")
    @Expose
    private Integer availableRoomCount;
    @SerializedName("available_bed_count")
    @Expose
    private Integer availableBedCount;
    @SerializedName("accomodation_allowed_str")
    @Expose
    private String accomodationAllowedStr;
    @SerializedName("house_type")
    @Expose
    private String houseType;
    @SerializedName("available_from")
    @Expose
    private Object availableFrom;
    @SerializedName("favorited")
    @Expose
    private Boolean favorited;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFurnishType() {
        return furnishType;
    }

    public void setFurnishType(String furnishType) {
        this.furnishType = furnishType;
    }

    public String getHouseSize() {
        return houseSize;
    }

    public void setHouseSize(String houseSize) {
        this.houseSize = houseSize;
    }

    public String getCoverPicUrl() {
        return coverPicUrl;
    }

    public void setCoverPicUrl(String coverPicUrl) {
        this.coverPicUrl = coverPicUrl;
    }

    public Integer getRentFrom() {
        return rentFrom;
    }

    public void setRentFrom(Integer rentFrom) {
        this.rentFrom = rentFrom;
    }

    public Integer getSecurityDepositFrom() {
        return securityDepositFrom;
    }

    public void setSecurityDepositFrom(Integer securityDepositFrom) {
        this.securityDepositFrom = securityDepositFrom;
    }

    public Integer getAvailableFlatCount() {
        return availableFlatCount;
    }

    public void setAvailableFlatCount(Integer availableFlatCount) {
        this.availableFlatCount = availableFlatCount;
    }

    public Integer getAvailableRoomCount() {
        return availableRoomCount;
    }

    public void setAvailableRoomCount(Integer availableRoomCount) {
        this.availableRoomCount = availableRoomCount;
    }

    public Integer getAvailableBedCount() {
        return availableBedCount;
    }

    public void setAvailableBedCount(Integer availableBedCount) {
        this.availableBedCount = availableBedCount;
    }

    public String getAccomodationAllowedStr() {
        return accomodationAllowedStr;
    }

    public void setAccomodationAllowedStr(String accomodationAllowedStr) {
        this.accomodationAllowedStr = accomodationAllowedStr;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Object getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Object availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

}
