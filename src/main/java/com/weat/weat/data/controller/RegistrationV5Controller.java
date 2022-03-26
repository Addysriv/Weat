package com.weat.weat.data.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weat.weat.common.utils.Constants;
import com.weat.weat.data.dto.OtpDto;
import com.weat.weat.data.dto.PhoneNumberDto;
import com.weat.weat.data.dto.SignUpRequestV5;
import com.weat.weat.response.ApiResponse;

@RequestMapping("/auth")
public interface RegistrationV5Controller {

	@PostMapping("/signin-phone") ResponseEntity<ApiResponse> initiatePhoneLogin(@RequestBody @Valid PhoneNumberDto phoneNumberDto,HttpServletRequest request);

	@PostMapping("/signup") ResponseEntity<ApiResponse> completeSignup(@RequestBody @Valid SignUpRequestV5 signUpRequestV4,
			@RequestHeader(required = false) Map<String, String> headers, HttpServletRequest request);
	
	@PostMapping("/otp/validate") ResponseEntity<ApiResponse> validateOtp(@RequestHeader(value= Constants.REGISTRATION_ID)String regId,@RequestBody OtpDto otp,HttpServletRequest request);
}
