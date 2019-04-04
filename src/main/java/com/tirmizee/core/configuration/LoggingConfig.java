package com.tirmizee.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tirmizee.core.filter.RequestLoggingFilter;

@Configuration
public class LoggingConfig {
	
	public static final String MESSAGE_PREFIX = "request [";
	
	@Bean
	@ConfigurationProperties("logging")
	public RequestLoggingFilter logFilter() {
		RequestLoggingFilter loggingFilter = new RequestLoggingFilter();
		loggingFilter.setAfterMessagePrefix(MESSAGE_PREFIX);
		return loggingFilter;
	}

}
