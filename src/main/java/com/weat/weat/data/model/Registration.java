package com.weat.weat.data.model;

import java.io.Serializable;

import javax.persistence.Column;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weat.weat.common.utils.RegistrationStatus;

import lombok.Getter;
import lombok.Setter;

//TODO Later On This needs to be Worked On And Use Redis With TTL 
@Getter
@Setter
@RedisHash(value = "registration", timeToLive = 2 * 60 * 60)
public class Registration extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8440327147009532166L;

	@Indexed
	private String registrationId;

	@Column(name = "phone_number", length = 15)
	@Indexed
	private String phoneNumber;

	private Boolean phoneNoVerified;

	@JsonIgnore
	private boolean otpSentToPhone = false;

	@JsonIgnore
	private boolean otpVerified = false;
	
	
	private RegistrationStatus registrationStatus;
	
	private String firstName;
	
	private String lastName;
	
	private Long userId;
	
	private String password;
	

}
