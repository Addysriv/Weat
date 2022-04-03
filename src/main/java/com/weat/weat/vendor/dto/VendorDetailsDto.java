package com.weat.weat.vendor.dto;

import java.util.List;

public class VendorDetailsDto {

    private Integer id;

    private String vendorName;

    private String storeName;

    private Integer pan;

    private Double longitude;

    private double latitude;

    private List<MobileNumberDto> mobileNumberListdto;

    private StoreAddressDto storeAddressdto;

    private List<AccountNumberDetailsDto> accountNumberDetailsDto;

    private InventoryDto inventoryDto;

    public InventoryDto getInventoryDto() {
        return inventoryDto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getPan() {
        return pan;
    }

    public void setPan(Integer pan) {
        this.pan = pan;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<MobileNumberDto> getMobileNumberListdto() {
        return mobileNumberListdto;
    }

    public void setMobileNumberListdto(List<MobileNumberDto> mobileNumberListdto) {
        this.mobileNumberListdto = mobileNumberListdto;
    }

    public StoreAddressDto getStoreAddressdto() {
        return storeAddressdto;
    }

    public void setStoreAddressdto(StoreAddressDto storeAddressdto) {
        this.storeAddressdto = storeAddressdto;
    }

    public List<AccountNumberDetailsDto> getAccountNumberDetailsDto() {
        return accountNumberDetailsDto;
    }

    public void setAccountNumberDetailsDto(List<AccountNumberDetailsDto> accountNumberDetailsDto) {
        this.accountNumberDetailsDto = accountNumberDetailsDto;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


}
