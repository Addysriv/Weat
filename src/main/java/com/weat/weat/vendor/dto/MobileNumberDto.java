package com.weat.weat.vendor.dto;

public class MobileNumberDto {
    private Integer id;
    private Double mobileNumber;
    private Integer countryCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Double mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }
}
