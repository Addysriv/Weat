package com.weat.weat.data.model;

import java.util.Arrays;

public enum AuthProvider {
	LOCAL, SSO, PHONE, EMAIL;

	public static AuthProvider fromString(String idp) {
		String idpName = idp.toUpperCase().replace("-", "_");
		return Arrays.stream(values()).filter(val -> val.name().equals(idpName)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("No such auth provider registered"));
	}

}