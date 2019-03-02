package com.tirmizee.core.repository;

import com.tirmizee.core.domain.RoleMapPermission;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface RoleMapPermissionRepository extends JdbcRepository<RoleMapPermission, Object[]>{

	public static final String TB_ROLE_MAP_PERMISSION = "ROLE_MAP_PERMISSION";
	public static final String COL_ROLE_ID = "ROLE_ID";
	public static final String COL_CREATE_DATE = "CREATE_DATE";
	public static final String COL_PER_ID = "PER_ID";
	public static final String ROLE_ID = "ROLE_MAP_PERMISSION.ROLE_ID";
	public static final String CREATE_DATE = "ROLE_MAP_PERMISSION.CREATE_DATE";
	public static final String PER_ID = "ROLE_MAP_PERMISSION.PER_ID";
	
}
