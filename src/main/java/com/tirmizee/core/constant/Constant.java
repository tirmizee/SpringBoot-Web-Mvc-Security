package com.tirmizee.core.constant;

import java.util.ResourceBundle;

public final class Constant {
	
	public static final class Profiles {
		public static final String DEVELOP = "dev";
		public static final String UAT = "uat";
		public static final String PRODUCTION = "production";
	}
	
	public static final class AppSetting {
		public static final String SESSION_TIME_OUT = "SESSION_TIME_OUT";
		public static final String MAX_LOGIN_FAIL = "MAX_LOGIN_FAIL";
		public static final String PASSWORD_CHANGE_DAY = "PASSWORD_CHANGE_DAY";
	}
	
	public static final class Resource {
		public static final ResourceBundle APPLICATION = ResourceBundle.getBundle("application");
		public static final ResourceBundle DB = ResourceBundle.getBundle("db");
	}
	
}
