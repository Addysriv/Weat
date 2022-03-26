package com.weat.weat.data.service.impl;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.weat.weat.common.exception.CoreException;
import com.weat.weat.common.utils.RegistrationStatus;
import com.weat.weat.data.dto.PhoneNumberDto;
import com.weat.weat.data.dto.RegistrationDto;
import com.weat.weat.data.dto.SignUpRequestV5;
import com.weat.weat.data.model.AuthProvider;
import com.weat.weat.data.model.PhoneAccount;
import com.weat.weat.data.model.Registration;
import com.weat.weat.data.model.User;
import com.weat.weat.data.service.PhoneAccountService;
import com.weat.weat.data.service.RegistrationService;
import com.weat.weat.repository.RegistrationRedisRepository;
import com.weat.weat.user.service.UserService;
import com.weat.weat.util.SignupHelper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

	private final RegistrationRedisRepository registrationRedisRepository;
	private final UserService userService;
	private final PhoneAccountService phoneAccountService;

	@Override
	public User signup(SignUpRequestV5 signupRequest, Map<String, String> headers, String clientIp) {
		String regId = signupRequest.getRegistrationId();
		Registration registration = fetchRegistration(regId);
		validateRegistrationForSignup(registration);
		User user = saveNewUser(registration);
		return user;
	}

	@Override
	public Registration fetchRegistration(String regId) {
		return registrationRedisRepository.findByRegistrationId(regId)
				.orElseThrow(() -> new CoreException(HttpStatus.NOT_FOUND.value(), regId));
	}

	private void validateRegistrationForSignup(Registration registration) {
		if (registration.getRegistrationStatus() != RegistrationStatus.NEW) {
			throw new CoreException(HttpStatus.CONFLICT.value(),
					"Existing User, Please login using Phone and Password");
		}

		if (!registration.isOtpVerified()) {
			throw new CoreException(HttpStatus.CONFLICT.value(), "OTP Not Verified");
		}

		if (StringUtils.isBlank(registration.getPhoneNumber())) {
			throw new CoreException(HttpStatus.BAD_REQUEST.value(), "Phone Number is Required");
		}
	}

	private User saveNewUser(Registration registration) {
		User user = new User();
		user.setActivated(true);
		user.setFirstName(registration.getFirstName());
		user.setLastName(registration.getLastName());
		user.setProvider(AuthProvider.LOCAL);
		user.setPhoneAccount(registration.getPhoneNumber());
		user = userService.saveUser(user);
		return user;
	}

	@Override
	public Registration initiate(@Valid PhoneNumberDto phoneNumberDto) {
		Registration registration = new Registration();
		registration.setPhoneNumber(phoneNumberDto.getPhoneNumber());
		registration.setOtpSentToPhone(true);
		Optional<PhoneAccount> existingPhoneAccount =  phoneAccountService.findByPhoneString(registration.getPhoneNumber());
		if(existingPhoneAccount.isEmpty()) {
			registration.setRegistrationStatus(RegistrationStatus.NEW);
		}else {
			handleSinglePhoneUser(registration,existingPhoneAccount.get());
		}
		registration.setRegistrationId(UUID.randomUUID().toString());
		return registrationRedisRepository.save(registration);
	}
	
	private void handleSinglePhoneUser(Registration registration, PhoneAccount thePhoneAccount) {
		User user = thePhoneAccount.getUser();
		registration.setFirstName(user.getFirstName());
		registration.setLastName(user.getLastName());
		registration.setUserId(user.getId());
		registration.setRegistrationStatus(RegistrationStatus.EXISTING_USER);
	}

	@Override
	public RegistrationDto processValidOtp(Registration registration) {
		RegistrationStatus registrationStatus = registration.getRegistrationStatus();

		if (registrationStatus == RegistrationStatus.EXISTING_USER) {
			registration.setOtpVerified(true);
			registration = registrationRedisRepository.save(registration);
			return SignupHelper.getRegistrationDtoForUser(userService.getUser(registration.getUserId()), registration.getRegistrationId());
		}

		
		if (registration.isOtpSentToPhone()) {
			registration.setPhoneNoVerified(true);
		}
		registration.setOtpVerified(true);

		registration = registrationRedisRepository.save(registration);

		return SignupHelper.toDto(registration);
	}

}
