package com.tirmizee.backend.api.setting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.backend.service.AppSettingService;
import com.tirmizee.backend.web.data.MessageSuccess;
import com.tirmizee.core.component.ApplicationSetting;

@RestController
@RequestMapping(value = "/api/setting")
public class ApiSettingController {

	@Autowired
	private AppSettingService appSettingService;
	
	@Autowired
	private ApplicationSetting applicationSetting;
	
	@GetMapping(path = "/refresh")
	public MessageSuccess refresh() {
		ApplicationSetting refreshApplicationSetting = appSettingService.getApplicationSetting();
		applicationSetting.setMaxLoginFail(refreshApplicationSetting.getMaxLoginFail());
		applicationSetting.setPasswordChangeDay(refreshApplicationSetting.getPasswordChangeDay());
		applicationSetting.setSessionTimeOut(refreshApplicationSetting.getSessionTimeOut());
		return new MessageSuccess();
	}
	
}
