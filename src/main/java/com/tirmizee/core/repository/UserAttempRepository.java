package com.tirmizee.core.repository;

import com.tirmizee.core.domain.UserAttemp;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface UserAttempRepository extends JdbcRepository<UserAttemp, String>{

	public static final String TB_USER_ATTEMP = "USER_ATTEMP";
	
	public static final String COL_LAST_MODIFIED = "LAST_MODIFIED";
	public static final String COL_ACCESS_IP = "ACCESS_IP";
	public static final String COL_ATTEMP = "ATTEMP";
	public static final String COL_USERNAME = "USERNAME";
	
	public static final String LAST_MODIFIED = "USER_ATTEMP.LAST_MODIFIED";
	public static final String ACCESS_IP = "USER_ATTEMP.ACCESS_IP";
	public static final String ATTEMP = "USER_ATTEMP.ATTEMP";
	public static final String USERNAME = "USER_ATTEMP.USERNAME";
	
}
