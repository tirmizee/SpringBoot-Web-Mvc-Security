package com.tirmizee.core.repository;

import com.tirmizee.core.domain.Permission;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface PermissionRepository extends JdbcRepository<Permission, Integer>{

	public static final String TB_PERMISSION = "PERMISSION";
	public static final String COL_UPDATE_BY = "UPDATE_BY";
	public static final String COL_UPDATE_DATE = "UPDATE_DATE";
	public static final String COL_CREATE_DATE = "CREATE_DATE";
	public static final String COL_PER_NAME = "PER_NAME";
	public static final String COL_PER_CODE = "PER_CODE";
	public static final String COL_PER_ID = "PER_ID";
	public static final String COL_CREATE_BY = "CREATE_BY";
	public static final String UPDATE_BY = "PERMISSION.UPDATE_BY";
	public static final String UPDATE_DATE = "PERMISSION.UPDATE_DATE";
	public static final String CREATE_DATE = "PERMISSION.CREATE_DATE";
	public static final String PER_NAME = "PERMISSION.PER_NAME";
	public static final String PER_CODE = "PERMISSION.PER_CODE";
	public static final String PER_ID = "PERMISSION.PER_ID";
	public static final String CREATE_BY = "PERMISSION.CREATE_BY";
	
}
