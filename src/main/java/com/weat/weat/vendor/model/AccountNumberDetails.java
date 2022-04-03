package com.weat.weat.vendor.model;

import com.weat.weat.vendor.dto.AccountNumberDetailsDto;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Table(name = "account_number_details")
public class AccountNumberDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "account_number")
    private Long accountnumber;

    @Column(name = "ifsc_code")
    private String ifscCode;

    @Column(name = "account_holder_name")
    private String accountHolderName;


    public AccountNumberDetails(AccountNumberDetailsDto accountNumberDetailsDtoList){
        this.setBankName(accountNumberDetailsDtoList.getBankName());
        this.setAccountnumber(accountNumberDetailsDtoList.getAccountNumber());
        this.setIfscCode(accountNumberDetailsDtoList.getIfscCode());
        this.setAccountHolderName(accountNumberDetailsDtoList.getAccountHolderName());
    }

    public AccountNumberDetails() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Long getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(Long accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
   
   
   
   
}
