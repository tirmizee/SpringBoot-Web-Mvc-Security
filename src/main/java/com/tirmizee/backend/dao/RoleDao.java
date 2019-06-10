package com.tirmizee.backend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tirmizee.backend.api.role.data.RoleDTO;
import com.tirmizee.backend.api.role.data.SearchRoleDTO;
import com.tirmizee.backend.api.role.data.SearchTermDTO;
import com.tirmizee.core.repository.RoleRepository;

public interface RoleDao extends RoleRepository {
	
	Page<RoleDTO> findPage(SearchTermDTO searchTerm, Pageable pageable);
	
	Page<RoleDTO> findPageTable(SearchRoleDTO searchTerm, Pageable pageable);
	
}
