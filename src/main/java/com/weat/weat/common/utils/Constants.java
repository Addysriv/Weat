package com.weat.weat.common.utils;

public final class Constants {

	public static final String EMPTY_STRING = "";
	public static final String SINGLE_WHITESPACE = " ";
	public static final String UTF_8 = "UTF-8";
	public static final Double _100_PERCENT = Double.valueOf(100);
	public static final String DATE_FORMAT_DDMMYYYY = "dd/MM/yyyy";
	public static final String DATE_FORMAT_DDMMYYYY_HHMM_DASHED = "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	public static final String DATE_FORMAT_TIMEZONE = "dd-M-yyyy hh:mm:ss a";
	public static final String DATE_FORMAT_YYYYMMDDT = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String HTTP_AUTHORIZATION_HEADER = "Authorization";
	public static final String AUTH_TOKEN = "AUTH-TOKEN";
	public static final String UI_TIME_FORMAT = "HH:mm";
	public static final String UI_DATE_FORMAT = "dd-MM-yyyy";
	public static final String UI_DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";
	public static final String INCOMING_UI_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String INCOMING_UI_DATE_FORMAT = "yyyy-MM-dd";
	public static final String UI_TEXT_FORMAT = "@";
	public static final String KALEYRA_SMS_PROVIDER= "kaleyra";
	public static final String DEFAULT_SMS_PROVIDER= "kaleyra";
	public static final String KALEYRA_EMAIL_PROVIDER= "kaleyra";
	public static final String DEFAULT_EMAIL_PROVIDER= "KALEYRA_EMAIL_PROVIDER";
	public static final String REGISTRATION_ID= "X_REGISTRATION_ID";
	public static final String JWT_SECRET = "JWT_SECRET";
	public static final String JWT_TOKEN_VALIDITY = "JWT_TOKEN_VALIDITY";
	public static final String CLIENT_HEADER = "client";

	
	public static final class Profile {
		public static final String DEV = "dev";
		public static final String STAGING = "staging";
		public static final String LOCAL = "local";
		public static final String LOADTEST = "loadtest";
		public static final String TEST = "test";
	}
	
	public static final class Client {
		public static final String WEB = "web";
	}
}