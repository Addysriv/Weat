package com.weat.weat.common.utils;

import java.util.Arrays;
import java.util.List;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PhoneUtil {

	private static final int NINETY_THOUSAND = 900000;
	private static final int TEN_THOUSAND = 100000;

	private static final int NINE_THOUSAND = 9000;
	private static final int ONE_THOUSAND = 1000;

	public static Phonenumber.PhoneNumber parse(String phone) {
		try {
			return PhoneNumberUtil.getInstance().parse(phone, "IN");
		} catch (NumberParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static String generateSixDigitOTP() {
		int otp = TEN_THOUSAND + RandomDataGenerator.getRandomNumber(NINETY_THOUSAND);
		return Integer.toString(otp);
	}

	public static int generateFourDigitOTP() {
		return ONE_THOUSAND + RandomDataGenerator.getRandomNumber(NINE_THOUSAND);
	}

	public static boolean allowMasterOtp(String[] activeProfiles) {
		List<String> allowedProfiles = Arrays.asList(Constants.Profile.DEV, Constants.Profile.TEST,
				Constants.Profile.STAGING, Constants.Profile.LOADTEST, Constants.Profile.LOCAL);
		return Arrays.stream(activeProfiles).anyMatch(allowedProfiles::contains);
	}
}
