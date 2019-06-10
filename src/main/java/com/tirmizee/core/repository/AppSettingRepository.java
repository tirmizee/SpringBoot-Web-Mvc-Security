package com.tirmizee.core.repository;

import com.tirmizee.core.domain.AppSetting;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface AppSettingRepository extends JdbcRepository<AppSetting, String> {
	
	public static final String TB_APP_SETTING = "APP_SETTING";
	public static final String COL_VALUE = "VALUE";
	public static final String COL_KEY = "KEY";
	public static final String VALUE = "APP_SETTING.VALUE";
	public static final String KEY = "APP_SETTING.KEY";

}
