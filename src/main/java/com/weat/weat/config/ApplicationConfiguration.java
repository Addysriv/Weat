package com.weat.weat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "weat")
@Data
@Getter
@Setter
public class ApplicationConfiguration {
	private Sms sms = new Sms();
	private final Otp otp = new Otp();

	
	
	@Getter
	@Setter
	public static class Sms{
		private final Kaleyra kaleyra = new Kaleyra();
		private final AutoRead autoRead =  new AutoRead();
		private String provider;
		private String otpSms;
		
		@Getter
		@Setter
		public static class Kaleyra{
			private String apiKey;
			private String senderId;
			private String url;
			private String body;
			private String method;
			private boolean enabled = false;
		}
		
		@Getter
		@Setter
		public static class AutoRead{
			private String appHash;
		}
	}
	
	@Getter
	@Setter
	public static class Otp{
		private long expiryInSeconds;
		private int maxOtpGenerationAttempts;
		private int maxOtpValidationAttempts;
		private int maxAttemptsCoolOffInSeconds;
	}
}