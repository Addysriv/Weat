package com.weat.weat.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties( ignoreUnknown = true)
public class OtpDto {

	private int otp;
}
