package com.tirmizee.backend.api.permission;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.backend.api.permission.data.PermissionCriteriaDTO;
import com.tirmizee.backend.api.permission.data.PermissionPageDTO;
import com.tirmizee.backend.dao.PermissionDao;
import com.tirmizee.core.component.PageMapper;
import com.tirmizee.core.datatable.PageRequestHelper;
import com.tirmizee.core.datatable.RequestTable;
import com.tirmizee.core.datatable.ResponseTable;
import com.tirmizee.core.domain.Permission;

@RestController
@RequestMapping("/api/permission")
public class PermissionApiController {
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Autowired
	private PageMapper mapper;
	
	@GetMapping(path = "/{roleId}")
	public Map<String, Object> allLogs(@PathVariable Integer roleId){
		Map<String, Object> response = new HashMap<>();
		response.put("data", permissionDao.findAllByUsername(roleId));
		return response;
	}
	
	@PostMapping(path = "/page")
	public ResponseTable<PermissionPageDTO> page(@RequestBody @Valid RequestTable<PermissionCriteriaDTO> request){
		Pageable pageable = PageRequestHelper.build(request, PermissionPageDTO.class);
		Page<Permission> page = permissionDao.findAllByCriteria(request.getSerch(), pageable);
		return new ResponseTable<>(mapper.map(page, PermissionPageDTO.class));
	}
	
}
