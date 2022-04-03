package com.weat.weat.vendor.model;

import com.weat.weat.vendor.dto.StoreAddressDto;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Table(name = "store_address")
@NoArgsConstructor
public class StoreAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Store_id")
    private Integer id;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "area")
    private String area;

    @Column(name = "landmark")
    private String landmark;

    @Column(name = "pincode")
    private Integer pincode;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    public StoreAddress(StoreAddressDto storeAddressDto){
        this.setStreetname(storeAddressDto.getStreetName());
        this.setArea(storeAddressDto.getArea());
        this.setLandmark(storeAddressDto.getLandmark());
        this.setPincode(storeAddressDto.getPincode());
        this.setCity(storeAddressDto.getCity());
        this.setState(storeAddressDto.getState());
        this.setCountry(storeAddressDto.getCountry());
        this.setLongitude(storeAddressDto.getLongitude());
        this.setLatitude(storeAddressDto.getLatitude());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStreetname() {
        return streetName;
    }

    public void setStreetname(String streetname) {
        this.streetName = streetname;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public Integer getZipcode() {
        return pincode;
    }

    public void setZipcode(Integer zipcode) {
        this.pincode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
