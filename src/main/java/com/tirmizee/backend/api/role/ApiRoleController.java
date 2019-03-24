package com.tirmizee.backend.api.role;

import java.util.concurrent.ForkJoinPool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.tirmizee.backend.api.role.data.RoleDTO;
import com.tirmizee.backend.api.role.data.SearchTermDTO;
import com.tirmizee.backend.service.RoleService;

@RestController
@RequestMapping(path = "api/role")
public class ApiRoleController {

	@Autowired
	private RoleService roleService;
	
	@PostMapping(path = "/page")
	public DeferredResult<Page<RoleDTO>> page(@RequestBody SearchTermDTO searchTerm) {
		DeferredResult<Page<RoleDTO>> deferredResult = new DeferredResult<>(60000L);
		ForkJoinPool.commonPool().submit(()->{
			try {
				Page<RoleDTO> result = roleService.generatePageByTerm(searchTerm);
				deferredResult.setResult(result);
			} catch (Exception ex) {
				deferredResult.setErrorResult(ex);
			}
		});
		return deferredResult;
	}
	
}
