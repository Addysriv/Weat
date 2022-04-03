package com.weat.weat.data.service;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetails;

import com.weat.weat.data.dto.PhoneNumberDto;
import com.weat.weat.data.dto.RegistrationDto;
import com.weat.weat.data.dto.SignUpRequestV5;
import com.weat.weat.data.model.Registration;
import com.weat.weat.data.model.User;

public interface RegistrationService {
	
	UserDetails signup(SignUpRequestV5 signupRequest, Map<String,String> headers, String clientIp);

	Registration fetchRegistration(String regId);

	Registration initiate(@Valid PhoneNumberDto phoneNumberDto);

	RegistrationDto processValidOtp(Registration registration);

}
