package com.weat.weat.util;


public enum SysParameterEnum {
	JWT_SECRET("JWT_SECRET", "weat"), JWT_TOKEN_VALIDITY("JWT_TOKEN_VALIDITY", "18000");

	private final String paramName;
	private final String paramValue;

	SysParameterEnum(String paramName, String paramValue) {
		this.paramName = paramName;
		this.paramValue = paramValue;
	}

	public String getParamName() {
		return paramName;
	}

	public String getParamValue() {
		return paramValue;
	}
}
