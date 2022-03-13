package com.weat.weat.data.model;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

//TODO Later On This needs to be Worked On And Use Redis With TTL 
@Getter
@Setter
//@RedisHash(value = "registration", timeToLive = 2 * 60 * 60)
public class Registration extends BaseEntity {

	// @Indexed
	private String registrationId;

	@Column(name = "phone_number", length = 15)
	// @Indexed
	private String phoneNumber;

	private Boolean phoneNoVerified;

	@JsonIgnore
	private boolean otpSentToPhone = false;

	@JsonIgnore
	private boolean otpVerified = false;

}
