package com.weat.weat.data.model;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = @Index(name = "ACCOUNT_PHONE_NUMBER_INDEX", columnList = "countryCode,phoneNumber"))
public class PhoneAccount extends Account {
	private int countryCode;
	private long phoneNumber;

	public PhoneAccount() {
		setAuthProvider(AuthProvider.PHONE);
	}

	public String getPhoneWithCountryCode() {
		return "+" + countryCode + phoneNumber;
	}
	
	public PhoneAccount(boolean verified,User user, int countryCode,long phoneNumber) {
		super(verified,AuthProvider.PHONE,user);
		this.countryCode =  countryCode;
		this.phoneNumber = phoneNumber;
	}
	
}
