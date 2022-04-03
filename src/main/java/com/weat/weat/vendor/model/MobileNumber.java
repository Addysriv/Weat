package com.weat.weat.vendor.model;

import com.weat.weat.vendor.dto.MobileNumberDto;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Table(name = "mobile_number")
public class MobileNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "mobile_number")
    private Double mobileNumber;

    @Column(name = "country_code")
    private Integer countryCode;




    public MobileNumber(MobileNumberDto mobileNumberDtoList){
        this.setMobileNumber(mobileNumberDtoList.getMobileNumber());
        this.setCountryCode(mobileNumberDtoList.getCountryCode());
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public MobileNumber(Double mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public MobileNumber() {
        super();

    }

    public Double getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Double mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
