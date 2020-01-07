package com.tirmizee.backend.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tirmizee.backend.dao.AppSettingDao;
import com.tirmizee.core.component.ApplicationSetting;
import com.tirmizee.core.constant.Constant;

@Service
public class AppSettingServiceImpl implements AppSettingService {

	@Autowired
	private AppSettingDao appSettingDao;
	
	@Override
	public String getValue(String key) {
		return appSettingDao.findOne(key).getValue();
	}
	
	@Override
	public Map<String, String> getMapSetting(){
		Map<String, String> map = new HashMap<>();
		appSettingDao.findAll().forEach(o -> map.put(o.getKey(), o.getValue()));
		return map;
	}

	@Override
	public ApplicationSetting getApplicationSetting() {
		Map<String, String> mapSetting = getMapSetting();
		ApplicationSetting applicationSetting = new ApplicationSetting();
		applicationSetting.setSessionTimeOut(Integer.valueOf(mapSetting.get(Constant.AppSetting.SESSION_TIME_OUT)));
		applicationSetting.setMaxLoginFail(Integer.valueOf(mapSetting.get(Constant.AppSetting.MAX_LOGIN_FAIL)));
		applicationSetting.setPasswordChangeDay(Integer.valueOf(mapSetting.get(Constant.AppSetting.PASSWORD_CHANGE_DAY)));
		return applicationSetting;
	}
	

}
