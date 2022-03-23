package com.weat.weat.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public final class SignupHelper {

	private static final String UNKNOWN = "unknown";

	private static final String MASK_CHAR = "X";

	private static final String[] IP_HEADER_CANDIDATES = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA" };

	public static String getClientIpAddress(HttpServletRequest request) {

		for (String header : IP_HEADER_CANDIDATES) {
			String ip = request.getHeader(header);
			if (StringUtils.isNotEmpty(ip) && ip.length() != 0 && !UNKNOWN.equalsIgnoreCase(ip)) {
				return ip;
			}
		}

		return request.getRemoteAddr();
	}
}