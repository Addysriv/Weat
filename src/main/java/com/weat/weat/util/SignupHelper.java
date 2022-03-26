package com.weat.weat.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.i18n.phonenumbers.Phonenumber;
import com.weat.weat.common.utils.Constants;
import com.weat.weat.common.utils.PhoneUtil;
import com.weat.weat.common.utils.RegistrationStatus;
import com.weat.weat.data.dto.RegistrationDto;
import com.weat.weat.data.model.PhoneAccount;
import com.weat.weat.data.model.Registration;
import com.weat.weat.data.model.User;

public final class SignupHelper {

	private static final String UNKNOWN = "unknown";

	private static final String MASK_CHAR = "X";
	private static final String EMPTY_STRING = "";
	private static final String[] IP_HEADER_CANDIDATES = {
			"X-Forwarded-For",
			"Proxy-Client-IP",
			"WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR",
			"HTTP_X_FORWARDED",
			"HTTP_X_CLUSTER_CLIENT_IP",
			"HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR",
			"HTTP_FORWARDED",
			"HTTP_VIA"
	};
	private static final int NUM_MASK_CHARS = 6;

	private SignupHelper() {
	}



	public static String getClientIpAddress(HttpServletRequest request) {

		for (String header : IP_HEADER_CANDIDATES) {
			String ip = request.getHeader(header);
			if (StringUtils.isNotEmpty(ip) && ip.length() != 0 && !UNKNOWN.equalsIgnoreCase(ip)) {
				return ip;
			}
		}

		return request.getRemoteAddr();
	}



	private static String getMaskedValueDefault(String originalStr) {
		if (originalStr.length() <= 4) {
			// Show first few chars for small strings
			return originalStr.charAt(0) + StringUtils.repeat(MASK_CHAR, NUM_MASK_CHARS);
		}
		return originalStr.substring(0, 2) + StringUtils.repeat(MASK_CHAR, NUM_MASK_CHARS) + originalStr.substring(originalStr.length() - 2);
	}
	
	public static String getMaskedValue(String originalStr) {
		if (originalStr == null || originalStr.length() < 1) {
			return EMPTY_STRING;
		}
		if (originalStr.startsWith("+")) {
			// Mask like international phone number
			try {
				Phonenumber.PhoneNumber phoneNumber = PhoneUtil.parse(originalStr);
				return "+" + phoneNumber.getCountryCode() + getMaskedValueDefault(String.valueOf(phoneNumber.getNationalNumber()));
			} catch (Exception e) {
				return getMaskedValueDefault(originalStr);
			}
		}
		Pattern pattern = Pattern.compile("(?<email>.+)@(?<domain>.+\\..+)");
		Matcher matcher = pattern.matcher(originalStr);
		if (matcher.matches()) {
			// Mask like email
			String email = matcher.group("email");
			String domain = matcher.group("domain");
			return getMaskedValueDefault(email) + "@" + getMaskedValueDefault(domain);
		}
		return getMaskedValueDefault(originalStr);
	}

	public static RegistrationDto toDtoMasked(Registration registration) {
		RegistrationDto dto = new RegistrationDto();
		dto.setCompletePhoneNumber(SignupHelper.getMaskedValue(registration.getPhoneNumber()));
		dto.setRegistrationId(registration.getRegistrationId());
		dto.setRegistrationStatus(registration.getRegistrationStatus());
		return dto;
	}


	public static RegistrationDto toDto(Registration registration) {
		RegistrationDto dto = new RegistrationDto();
		dto.setCompletePhoneNumber(registration.getPhoneNumber());
		dto.setRegistrationId(registration.getRegistrationId());
		dto.setRegistrationStatus(registration.getRegistrationStatus());
		return dto;
	}




	public static RegistrationDto getRegistrationDtoForUser(User user, String regId) {
		RegistrationDto dto = new RegistrationDto();
		String name = (ObjectUtils.defaultIfNull(user.getFirstName(), Constants.EMPTY_STRING) + " " + ObjectUtils
				.defaultIfNull(user.getLastName(), Constants.EMPTY_STRING)).trim();
		dto.setName(name);
		dto.setRegistrationId(regId);
		dto.setCompletePhoneNumber(user.getPhoneAccount().map(PhoneAccount::getPhoneWithCountryCode).orElse(null));
		dto.setRegistrationStatus(RegistrationStatus.EXISTING_USER);
		dto.setUserId(user.getId());
		return dto;
	}

}
