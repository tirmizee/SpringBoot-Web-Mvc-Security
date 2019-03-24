package com.tirmizee.backend.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tirmizee.backend.api.role.data.RoleDTO;
import com.tirmizee.backend.api.role.data.SearchTermDTO;
import com.tirmizee.backend.dao.RoleDao;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Page<RoleDTO> generatePageByTerm(SearchTermDTO searchTerm) {
		Integer page = ObjectUtils.defaultIfNull(searchTerm.getPage(), 0),
				size = ObjectUtils.defaultIfNull(searchTerm.getSize(), 20);
		Pageable pageable = new PageRequest(page, size);
		return roleDao.findPage(searchTerm, pageable);
	}

}
