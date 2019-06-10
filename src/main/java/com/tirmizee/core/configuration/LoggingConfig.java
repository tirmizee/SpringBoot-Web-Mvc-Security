package com.tirmizee.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tirmizee.core.filter.RequestLoggingFilter;

@Configuration
public class LoggingConfig {
	
	@Bean
	@ConfigurationProperties("logging")
	public RequestLoggingFilter logFilter() {
		return new RequestLoggingFilter();
	}

}
