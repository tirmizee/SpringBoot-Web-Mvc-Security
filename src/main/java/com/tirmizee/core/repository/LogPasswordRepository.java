package com.tirmizee.core.repository;

import com.tirmizee.core.domain.LogPassword;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface LogPasswordRepository extends JdbcRepository<LogPassword, Integer> {
	
	public static final String TB_LOG_PASSWORD = "LOG_PASSWORD";
	public static final String COL_CREATE_DATE = "CREATE_DATE";
	public static final String COL_PASSWORD = "PASSWORD";
	public static final String COL_USERNAME = "USERNAME";
	public static final String COL_LOG_ID = "LOG_ID";
	public static final String CREATE_DATE = "LOG_PASSWORD.CREATE_DATE";
	public static final String PASSWORD = "LOG_PASSWORD.PASSWORD";
	public static final String USERNAME = "LOG_PASSWORD.USERNAME";
	public static final String LOG_ID = "LOG_PASSWORD.LOG_ID";

}
