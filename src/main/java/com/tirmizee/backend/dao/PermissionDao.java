package com.tirmizee.backend.dao;

import java.util.List;

import com.tirmizee.core.domain.Permission;
import com.tirmizee.core.repository.PermissionRepository;

public interface PermissionDao extends PermissionRepository {

	List<Permission> findByUsername(String username);
	
}
