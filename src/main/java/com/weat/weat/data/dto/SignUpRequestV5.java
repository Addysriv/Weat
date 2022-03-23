package com.weat.weat.data.dto;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import com.weat.weat.common.utils.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestV5 {

	private Boolean isReferrer = false;

	private String referrerCode = Constants.EMPTY_STRING;

	private Map<String, Object> extraFields = new HashMap<>();

	private Boolean isEnglish = true;

	@NotBlank
	private String registrationId;

	private Boolean isDetectLocation = true;
}