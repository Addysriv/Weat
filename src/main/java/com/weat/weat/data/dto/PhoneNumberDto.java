package com.weat.weat.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PhoneNumberDto {

	@NotBlank
	private String phoneNumber;
	
	@NotBlank
	private String password;

	public PhoneNumberDto(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
