package com.tirmizee.backend.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tirmizee.backend.api.permission.data.PermissionCriteriaDTO;
import com.tirmizee.backend.api.permission.data.PermissionDTO;
import com.tirmizee.core.domain.Permission;
import com.tirmizee.core.repository.PermissionRepository;

public interface PermissionDao extends PermissionRepository {

	List<Permission> findByUsername(String username);
	
	List<PermissionDTO> findAllByUsername(Integer roleId);
	
	Page<Permission> findAllByCriteria(PermissionCriteriaDTO search, Pageable pageable);
	
}
