package com.tirmizee.core.repository;

import com.tirmizee.core.domain.Role;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface RoleRepository extends JdbcRepository<Role, Integer> {

	public static final String TB_ROLE = "ROLE";
	public static final String COL_UPDATE_BY = "UPDATE_BY";
	public static final String COL_UPDATE_DATE = "UPDATE_DATE";
	public static final String COL_ROLE_DESC = "ROLE_DESC";
	public static final String COL_ROLE_NAME = "ROLE_NAME";
	public static final String COL_ROLE_CODE = "ROLE_CODE";
	public static final String COL_ROLE_ID = "ROLE_ID";
	public static final String COL_CREATE_DATE = "CREATE_DATE";
	public static final String UPDATE_BY = "ROLE.UPDATE_BY";
	public static final String UPDATE_DATE = "ROLE.UPDATE_DATE";
	public static final String ROLE_DESC = "ROLE.ROLE_DESC";
	public static final String ROLE_NAME = "ROLE.ROLE_NAME";
	public static final String ROLE_CODE = "ROLE.ROLE_CODE";
	public static final String ROLE_ID = "ROLE.ROLE_ID";
	public static final String CREATE_DATE = "ROLE.CREATE_DATE";
	
}
