package com.tirmizee.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tirmizee.core.filter.RequestLoggingFilter;

@Configuration
public class LoggingConfig {
	
	@Bean
	public RequestLoggingFilter logFilter() {
		RequestLoggingFilter loggingFilter = new RequestLoggingFilter();
		loggingFilter.setIncludePayload(true);
		loggingFilter.setIncludeQueryString(true);
		loggingFilter.setMaxPayloadLength(100);
		return loggingFilter;
	}

}
