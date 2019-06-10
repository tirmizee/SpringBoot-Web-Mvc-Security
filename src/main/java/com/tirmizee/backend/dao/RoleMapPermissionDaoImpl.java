package com.tirmizee.backend.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.RoleMapPermission;
import com.tirmizee.core.repository.RoleMapPermissionRepositoryImpl;

@Repository
public class RoleMapPermissionDaoImpl extends RoleMapPermissionRepositoryImpl implements RoleMapPermissionDao {

	@Override
	public List<RoleMapPermission> findByRoleId(Integer roleId) {
		StringBuilder statement = new StringBuilder()
			.append(" SELECT * FROM ").append(TB_ROLE_MAP_PERMISSION)
			.append(" WHERE ").append(COL_ROLE_ID).append(" = ? ");
		return getJdbcOps().query(statement.toString(), params(roleId), ROW_MAPPER);
	}

}
