package com.tirmizee.backend.service;

import java.util.Map;

import com.tirmizee.core.component.ApplicationSetting;

public interface AppSettingService {

	String getValue(String key);
	
	Map<String, String> getMapSetting();
	
	ApplicationSetting getApplicationSetting();
	
}
