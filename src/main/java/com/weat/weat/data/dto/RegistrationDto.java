package com.weat.weat.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weat.weat.common.utils.RegistrationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {
	private String registrationId;

	private String name;

	private String completePhoneNumber;

	private RegistrationStatus registrationStatus;

	@JsonIgnore
	private Long userId;
	
	private String jwttoken;


}
