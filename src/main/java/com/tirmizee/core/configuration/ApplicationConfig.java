package com.tirmizee.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tirmizee.backend.service.AppSettingService;
import com.tirmizee.core.component.ApplicationSetting;

@Configuration
public class ApplicationConfig {

	@Bean
	public ApplicationSetting applicationSetting(AppSettingService appSettingService) {
		return appSettingService.getApplicationSetting();
	}
	
}
