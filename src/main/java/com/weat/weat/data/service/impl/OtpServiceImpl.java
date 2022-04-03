package com.weat.weat.data.service.impl;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.i18n.phonenumbers.Phonenumber;
import com.weat.weat.common.exception.CoreException;
import com.weat.weat.common.utils.PhoneUtil;
import com.weat.weat.config.ApplicationConfiguration;
import com.weat.weat.data.dto.PhoneNumberDto;
import com.weat.weat.data.dto.PhoneNumberOtpDto;
import com.weat.weat.data.service.OtpService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OtpServiceImpl implements OtpService {
	
	public static final String OTP_POSTFIX = "_otp";
	public static final String VERIFICATION_OTP_PREFIX = "verify_";
	private static final String OTP_GENERATION_MAX_ATTEMPTS_POSTFIX = OTP_POSTFIX + "_generation_attempts";
	private static final String OTP_VALIDATION_MAX_ATTEMPTS_POSTFIX = OTP_POSTFIX + "_validation_attempts";
	public static final int MASTER_OTP = 1234;
	private final ApplicationConfiguration applicationConfiguration;
	private final RedisTemplate<String, Object> redisTemplate;
	private final Environment environment;


	@Override
	public void generateAndSendOtp(Optional<String> phoneNumber) {
		Preconditions.checkArgument(phoneNumber.isPresent());
		String key =  phoneNumber.get();
		Integer otp =  saveAndGetOtp(key);
		log.info("Generated OTP: {} for {}", otp, phoneNumber);
		phoneNumber.ifPresent(phone -> generatePhoneOtp(new PhoneNumberDto(phone), otp));
	}
	
	@Override public void validatePhoneVerificationOtp(PhoneNumberOtpDto phoneNumberOtpDto, Long userId) {

		String verificationKey = VERIFICATION_OTP_PREFIX + userId + "_" + phoneNumberOtpDto.getPhoneNumber();
		if (!validateOtpAndResetStats(verificationKey, phoneNumberOtpDto.getOtp())) {
			throw new IllegalArgumentException("The OTP you entered is incorrect. Please try again");
		}
	}
	
	private boolean validateOtpAndResetStats(String key, int otpToBeValidated) {

		key = buildOtpKey(key);
		//&& PhoneUtil.allowMasterOtp(environment.getActiveProfiles())
		if (otpToBeValidated == MASTER_OTP && true) {
			resetOtpStats(key);
			return true;
		}
		if (hasOtpValidationMaxAttemptsReached(key)) {
			throw new CoreException(HttpStatus.CONFLICT.value(),"You have exhausted your " + applicationConfiguration.getOtp().getMaxOtpValidationAttempts()
					+ " attempts. Please contact techsupport@upgrad.com for help");
		}
		increaseOtpValidationAttemptsByOne(key);
		Optional<Integer> otpFromCache = getOtpFromCache(key);
		log.info("otpFromCache: {}", otpFromCache);
		// otp doesn't exist in cache, return false because otp didn't match
		if (!otpFromCache.isPresent()) {
			return false;
		}
		if (otpFromCache.get() == otpToBeValidated) {
			// clear everything related to key when otp matches
			resetOtpStats(key);
			return true;
		}
		// Default assumption that otp doesn't match
		return false;
	}
	
	private Integer saveAndGetOtp(String key) {
		key = buildOtpKey(key);
		if (hasOtpGenerationMaxAttemptsReached(key)) {
			throw new CoreException(HttpStatus.BAD_REQUEST.value() , "You have exhausted your " + applicationConfiguration.getOtp().getMaxOtpGenerationAttempts()
					+ " attempts. Please contact admin for help");
		}
		increaseOtpGenerationAttemptsByOne(key);
		Optional<Integer> otpFromCache = getOtpFromCache(key);
		// resend same otp without updating expiry
		if (otpFromCache.isPresent()) {
			return otpFromCache.get();
		}
		int otp = PhoneUtil.generateFourDigitOTP();
		redisTemplate.opsForValue().set(key, otp);
		redisTemplate.expire(key, applicationConfiguration.getOtp().getExpiryInSeconds(), TimeUnit.SECONDS);
		return otp;
	}
	
	private String buildOtpKey(String key) {
		return key + OTP_POSTFIX;
	}
	
	private boolean hasOtpGenerationMaxAttemptsReached(String key) {
		Optional<Integer> numberOfAttempts = getNumberOfOtpGenerationAttempts(key);
		return numberOfAttempts.isPresent() && numberOfAttempts.get() >
		applicationConfiguration.getOtp().getMaxOtpGenerationAttempts();
	}
	
	private Optional<Integer> getNumberOfOtpGenerationAttempts(String key) {
		String val = (String) redisTemplate.opsForValue().get(buildOtpGenerationMaxAttemptsKey(key));
		if (val == null) {
			return Optional.empty();
		}
		return Optional.of(Integer.parseInt(val));
	}
	
	private void increaseOtpValidationAttemptsByOne(String key) {
		redisTemplate.opsForValue().increment(buildOtpValidationMaxAttemptsKey(key));
	}
	
	private boolean hasOtpValidationMaxAttemptsReached(String key) {
		Optional<Integer> numberOfAttempts = getNumberOfOtpValidationAttempts(key);
		return numberOfAttempts.isPresent() && numberOfAttempts.get() >
				applicationConfiguration.getOtp().getMaxOtpValidationAttempts();
	}
	
	private Optional<Integer> getNumberOfOtpValidationAttempts(String key) {
		String val = (String) redisTemplate.opsForValue().get(buildOtpValidationMaxAttemptsKey(key));
		if (val == null) {
			return Optional.empty();
		}
		return Optional.of(Integer.parseInt(val));
	}
	
	private void resetOtpStats(String key) {
		// reset otp generation attempts count
		redisTemplate.delete(buildOtpGenerationMaxAttemptsKey(key));
		// reset otp validation attempts count
		redisTemplate.delete(buildOtpValidationMaxAttemptsKey(key));
		// delete otp as well
		redisTemplate.delete(key);
	}
	
	private String buildOtpGenerationMaxAttemptsKey(String originalKey) {
		return originalKey + OTP_GENERATION_MAX_ATTEMPTS_POSTFIX;
	}
	
	private String buildOtpValidationMaxAttemptsKey(String originalKey) {
		return originalKey + OTP_VALIDATION_MAX_ATTEMPTS_POSTFIX;
	}
	
	private void increaseOtpGenerationAttemptsByOne(String key) {
		redisTemplate.opsForValue().increment(buildOtpGenerationMaxAttemptsKey(key));
	}
	
	private Optional<Integer> getOtpFromCache(String key) {
		String val = (String) redisTemplate.opsForValue().get(key);
		if (val == null) {
			return Optional.empty();
		}
		return Optional.of(Integer.parseInt(val));
	}
	
	public void generatePhoneOtp(PhoneNumberDto phoneNumberDto, Integer otp) {
		Phonenumber.PhoneNumber phoneNumber = PhoneUtil.parse(phoneNumberDto.getPhoneNumber());
		log.info("Currently SMS Sending to Phone Number is Prevented For Dev Environment");
		//Need to Configure SMS Service When we have Kaylera or any other Provider
		//smsService.sendOtp(new SendOtpDto(phoneNumber.getCountryCode(), phoneNumber.getNationalNumber(), otp));
	}

	@Override
	public void validateOtp(String key, int otp) {
		if (validateOtpAndResetStats(key, otp)) {
			log.debug("Valid OTP entered for key: {}", key);
		} else {
			throw new IllegalArgumentException("Invalid OTP");
		}

	}

}
