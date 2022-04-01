package com.weat.weat.data.controller.impl;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import com.weat.weat.common.exception.CoreException;
import com.weat.weat.common.utils.CoreUtil;
import com.weat.weat.common.utils.RegistrationStatus;
import com.weat.weat.config.Messages;
import com.weat.weat.data.controller.RegistrationV5Controller;
import com.weat.weat.data.dto.OtpDto;
import com.weat.weat.data.dto.PhoneNumberDto;
import com.weat.weat.data.dto.RegistrationDto;
import com.weat.weat.data.dto.SignUpRequestV5;
import com.weat.weat.data.model.Registration;
import com.weat.weat.data.model.User;
import com.weat.weat.data.service.OtpService;
import com.weat.weat.data.service.RegistrationService;
import com.weat.weat.response.ApiResponse;
import com.weat.weat.service.impl.CustomUserDetailsService;
import com.weat.weat.util.JwtTokenUtil;
import com.weat.weat.util.SignupHelper;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RegistrationV5ControllerImpl implements RegistrationV5Controller {

	private final RegistrationService registrationService;
	private final OtpService otpService;
	private final Messages messages;
	private final JwtTokenUtil jwtTokenUtil;
	private final CustomUserDetailsService userDetailsService;


	@Override
	public ResponseEntity<ApiResponse> initiatePhoneLogin(@Valid PhoneNumberDto phoneNumberDto,
			HttpServletRequest request) {
		Registration registration = registrationService.initiate(phoneNumberDto);
		otpService.generateAndSendOtp(Optional.ofNullable(registration.getPhoneNumber()));
		return CoreUtil.buildApiResponse(SignupHelper.toDtoMasked(registration), request,
				messages.get("user.notcreated"), HttpStatus.CREATED.value(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.CREATED, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<ApiResponse> completeSignup(@Valid SignUpRequestV5 signUpRequestV4,
			Map<String, String> headers, HttpServletRequest request) {
		String clientIp = SignupHelper.getClientIpAddress(request);
		final UserDetails userDetails = registrationService.signup(signUpRequestV4, headers, clientIp);
		final String token = jwtTokenUtil.generateToken(userDetails);
		return CoreUtil.buildApiResponse(token, request, messages.get("user.notcreated"),
				HttpStatus.OK.value(), HttpStatus.NOT_FOUND.value(), HttpStatus.OK, HttpStatus.NOT_FOUND);
		
	}

	@Override
	public ResponseEntity<ApiResponse> validateOtp(String regId, OtpDto otp, HttpServletRequest request) {
		Registration registration = registrationService.fetchRegistration(regId);
		if (registration.isOtpVerified()) {
			throw new CoreException(HttpStatus.CONFLICT.value(), "OTP Already Verified");
		}
		if (registration.isOtpSentToPhone()) {
			otpService.validateOtp(registration.getPhoneNumber(), otp.getOtp());
		}
		RegistrationDto registrationDto = registrationService.processValidOtp(registration);
		if (registration.getRegistrationStatus() == RegistrationStatus.EXISTING_USER) {
			String clientIp = SignupHelper.getClientIpAddress(request);
			final UserDetails userDetails = userDetailsService.loadUserByUsername(registration.getPhoneNumber());
			final String token = jwtTokenUtil.generateToken(userDetails);
			return CoreUtil.buildApiResponse(token, request, messages.get("user.error"),
					HttpStatus.OK.value(), HttpStatus.NOT_FOUND.value(), HttpStatus.OK, HttpStatus.NOT_FOUND);
		}
		return CoreUtil.buildApiResponse(registrationDto, request, messages.get("user.error"), HttpStatus.OK.value(),
				HttpStatus.NOT_FOUND.value(), HttpStatus.OK, HttpStatus.NOT_FOUND);
	}

}
