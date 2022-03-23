package com.weat.weat.data.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weat.weat.data.dto.PhoneNumberDto;
import com.weat.weat.data.dto.RegistrationDto;
import com.weat.weat.data.dto.SignUpRequestV5;

@RequestMapping("/auth")
public interface RegistrationV5Controller {

	@PostMapping("/signin-phone") ResponseEntity<RegistrationDto> initiatePhoneLogin(@RequestBody @Valid PhoneNumberDto phoneNumberDto);

	@PostMapping("/signup") ResponseEntity<RegistrationDto> completeSignup(@RequestBody @Valid SignUpRequestV5 signUpRequestV4,
			@RequestHeader(required = false) Map<String, String> headers, HttpServletRequest request);
}
