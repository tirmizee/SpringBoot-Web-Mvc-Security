package com.tirmizee.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tirmizee.backend.dao.AppSettingDao;

@Service
public class AppSettingServiceImpl implements AppSettingService{

	@Autowired
	private AppSettingDao appSettingDao;
	
	@Override
	public String getValue(String key) {
		return appSettingDao.findOne(key).getValue();
	}

}
