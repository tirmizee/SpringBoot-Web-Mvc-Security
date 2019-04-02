package com.tirmizee.backend.api.permission;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.backend.dao.PermissionDao;

@RestController
@RequestMapping("/api/permission")
public class PermissionApiController {
	
	@Autowired
	private PermissionDao permissionDao;
	
	@GetMapping(path = "/{roleId}")
	public Map<String, Object> allLogs(@PathVariable Integer roleId){
		Map<String, Object> response = new HashMap<>();
		response.put("data", permissionDao.findAllByUsername(roleId));
		return response;
	}
	
}
