package com.tirmizee.core.repository;

import com.tirmizee.core.domain.User;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface UserRepository extends JdbcRepository<User, Long>{
	
	public static final String TB_USERS = "USERS";
	public static final String COL_ACCOUNT_LOCKED_DATE = "ACCOUNT_LOCKED_DATE";
	public static final String COL_ACCOUNT_EXPIRED_DATE = "ACCOUNT_EXPIRED_DATE";
	public static final String COL_MAX_SESSION = "MAX_SESSION";
	public static final String COL_FK_ROLE_ID = "FK_ROLE_ID";
	public static final String COL_CREDENTIALSEXPIRED_DATE = "CREDENTIALSEXPIRED_DATE";
	public static final String COL_FIRST_LOGIN = "FIRST_LOGIN";
	public static final String COL_UPDATE_DATE = "UPDATE_DATE";
	public static final String COL_CREATE_DATE = "CREATE_DATE";
	public static final String COL_CREDENTIALSNONEXPIRED = "CREDENTIALSNONEXPIRED";
	public static final String COL_ACCOUNTNONEXPIRED = "ACCOUNTNONEXPIRED";
	public static final String COL_ACCOUNTNONLOCKED = "ACCOUNTNONLOCKED";
	public static final String COL_ENABLED = "ENABLED";
	public static final String COL_PASSWORD = "PASSWORD";
	public static final String COL_USERNAME = "USERNAME";
	public static final String COL_PROFILE_ID = "PROFILE_ID";
	public static final String COL_USER_ID = "USER_ID";
	public static final String ACCOUNT_LOCKED_DATE = "USERS.ACCOUNT_LOCKED_DATE";
	public static final String ACCOUNT_EXPIRED_DATE = "USERS.ACCOUNT_EXPIRED_DATE";
	public static final String MAX_SESSION = "USERS.MAX_SESSION";
	public static final String FK_ROLE_ID = "USERS.FK_ROLE_ID";
	public static final String CREDENTIALSEXPIRED_DATE = "USERS.CREDENTIALSEXPIRED_DATE";
	public static final String FIRST_LOGIN = "USERS.FIRST_LOGIN";
	public static final String UPDATE_DATE = "USERS.UPDATE_DATE";
	public static final String CREATE_DATE = "USERS.CREATE_DATE";
	public static final String CREDENTIALSNONEXPIRED = "USERS.CREDENTIALSNONEXPIRED";
	public static final String ACCOUNTNONEXPIRED = "USERS.ACCOUNTNONEXPIRED";
	public static final String ACCOUNTNONLOCKED = "USERS.ACCOUNTNONLOCKED";
	public static final String ENABLED = "USERS.ENABLED";
	public static final String PASSWORD = "USERS.PASSWORD";
	public static final String USERNAME = "USERS.USERNAME";
	public static final String PROFILE_ID = "USERS.PROFILE_ID";
	public static final String USER_ID = "USERS.USER_ID";
	
}
