package com.tirmizee.backend.service;

import org.springframework.data.domain.Page;

import com.tirmizee.backend.api.role.data.RoleDTO;
import com.tirmizee.backend.api.role.data.SearchTermDTO;

public interface RoleService {
	
	Page<RoleDTO> generatePageByTerm(SearchTermDTO searchTerm);

}
