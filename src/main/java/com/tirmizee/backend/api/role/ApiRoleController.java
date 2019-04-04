package com.tirmizee.backend.api.role;

import java.util.concurrent.ForkJoinPool;

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
import org.springframework.web.context.request.async.DeferredResult;

import com.tirmizee.backend.api.role.data.ReqUpdateRoleDTO;
import com.tirmizee.backend.api.role.data.RoleDTO;
import com.tirmizee.backend.api.role.data.SearchRoleDTO;
import com.tirmizee.backend.api.role.data.SearchTermDTO;
import com.tirmizee.backend.dao.RoleDao;
import com.tirmizee.backend.service.RoleService;
import com.tirmizee.backend.web.data.MessageSuccess;
import com.tirmizee.core.component.PageMapper;
import com.tirmizee.core.datatable.PageRequestHelper;
import com.tirmizee.core.datatable.RequestTable;
import com.tirmizee.core.datatable.ResponseTable;
import com.tirmizee.core.domain.Role;

@RestController
@RequestMapping(path = "api/role")
public class ApiRoleController {

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PageMapper mapper;
	
	@GetMapping(path = "/get/{roleId}")
	public DeferredResult<RoleDTO> get(@PathVariable Integer roleId) {
		DeferredResult<RoleDTO> deferredResult = new DeferredResult<>(60000L);
		ForkJoinPool.commonPool().submit(()->{
			try {
				Role role = roleDao.findOne(roleId);
				RoleDTO roleDTO = mapper.map(role, RoleDTO.class);
				deferredResult.setResult(roleDTO);
			} catch (Exception ex) {
				deferredResult.setErrorResult(ex);
			}
		});
		return deferredResult;
	}
	
	@PostMapping(path = "/update")
	public MessageSuccess updateRole(@RequestBody @Valid ReqUpdateRoleDTO updateRoleDTO) {
		return null;
	}
	
	@PostMapping(path = "/page")
	public DeferredResult<Page<RoleDTO>> page(@RequestBody SearchTermDTO searchTerm) {
		DeferredResult<Page<RoleDTO>> deferredResult = new DeferredResult<>(60000L);
		ForkJoinPool.commonPool().submit(()->{
			try {
				Page<RoleDTO> result = roleService.buildPageByTerm(searchTerm);
				deferredResult.setResult(result);
			} catch (Exception ex) {
				deferredResult.setErrorResult(ex);
			}
		});
		return deferredResult;
	}
	
	@PostMapping(path = "/find/page")
	public DeferredResult<ResponseTable<RoleDTO>> findPage(@RequestBody @Valid RequestTable<SearchRoleDTO> requestTable) {
		DeferredResult<ResponseTable<RoleDTO>> deferredResult = new DeferredResult<>(60000L);
		ForkJoinPool.commonPool().submit(()->{
			try {
				Pageable pageable = PageRequestHelper.build(requestTable, RoleDTO.class);
				Page<RoleDTO> page = roleDao.findPageTable(requestTable.getSerch(), pageable);
				ResponseTable<RoleDTO> responseTable = new ResponseTable<>(page);
				deferredResult.setResult(responseTable);
			} catch (Exception ex) {
				deferredResult.setErrorResult(ex);
			}
		});
		return deferredResult;
	}
	
}
