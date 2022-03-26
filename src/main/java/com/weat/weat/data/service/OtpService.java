package com.weat.weat.data.service;

import java.util.Optional;

import com.weat.weat.data.dto.PhoneNumberOtpDto;

public interface OtpService {
	
	void generateAndSendOtp(Optional<String> phoneNumber);

	void validatePhoneVerificationOtp(PhoneNumberOtpDto phoneNumberOtpDto, Long userId);
	
	void validateOtp(String phoneNumber,int otp);

}
