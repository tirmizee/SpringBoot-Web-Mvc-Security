package com.tirmizee.backend.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.Permission;
import com.tirmizee.core.repository.PermissionRepositoryImpl;
import com.tirmizee.core.repository.RoleMapPermissionRepository;
import com.tirmizee.core.repository.RoleRepository;
import com.tirmizee.core.repository.UserRepository;

@Repository
public class PermissionDaoImpl extends PermissionRepositoryImpl implements PermissionDao {

	@Override
	public List<Permission> findByUsername(String username) {
		StringBuilder statement = new StringBuilder()
			.append(" SELECT ")
				.append(TB_PERMISSION + ".*")
			.append(" FROM ").append(UserRepository.TB_USERS)
			.append(" INNER JOIN ").append(RoleRepository.TB_ROLE)
				.append(" ON ").append(UserRepository.FK_ROLE_ID)
				.append(" = ").append(RoleRepository.ROLE_ID)
			.append(" INNER JOIN ").append(RoleMapPermissionRepository.TB_ROLE_MAP_PERMISSION)
				.append(" ON ").append(RoleRepository.ROLE_ID)
				.append(" = ").append(RoleMapPermissionRepository.ROLE_ID)
			.append(" INNER JOIN ").append(TB_PERMISSION)
				.append(" ON ").append(RoleMapPermissionRepository.PER_ID)
				.append(" = ").append(PER_ID)
			.append(" WHERE ").append(UserRepository.USERNAME)
			.append(" = ? ");
		return getJdbcOps().query(statement.toString(), params(username), ROW_MAPPER);
	}

}
