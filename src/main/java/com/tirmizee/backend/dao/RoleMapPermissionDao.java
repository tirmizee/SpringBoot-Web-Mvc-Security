package com.tirmizee.backend.dao;

import java.util.List;

import com.tirmizee.core.domain.RoleMapPermission;
import com.tirmizee.core.repository.RoleMapPermissionRepository;

public interface RoleMapPermissionDao extends RoleMapPermissionRepository {
	
	List<RoleMapPermission> findByRoleId(Integer roleId);

}
