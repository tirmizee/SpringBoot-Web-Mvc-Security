package com.tirmizee.core.repository;

import com.tirmizee.core.domain.Profile;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface ProfileRepository extends JdbcRepository<Profile, Long>{

	public static final String TB_PROFILE = "PROFILE";
	
	public static final String COL_UPDATE_DATE = "UPDATE_DATE";
	public static final String COL_CREATE_DATE = "CREATE_DATE";
	public static final String COL_LAST_NAME = "LAST_NAME";
	public static final String COL_FIRST_NAME = "FIRST_NAME";
	public static final String COL_EMAIL = "EMAIL";
	public static final String COL_PROFILE_ID = "PROFILE_ID";
	
	public static final String UPDATE_DATE = "PROFILE.UPDATE_DATE";
	public static final String CREATE_DATE = "PROFILE.CREATE_DATE";
	public static final String LAST_NAME = "PROFILE.LAST_NAME";
	public static final String FIRST_NAME = "PROFILE.FIRST_NAME";
	public static final String EMAIL = "PROFILE.EMAIL";
	public static final String PROFILE_ID = "PROFILE.PROFILE_ID";
	
}
