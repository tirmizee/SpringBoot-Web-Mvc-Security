package com.tirmizee.backend.api.user;

import java.util.concurrent.ForkJoinPool;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.tirmizee.backend.api.user.data.ReqForgotPasswordDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordExpriedDTO;
import com.tirmizee.backend.api.user.data.UserDetailCriteriaDTO;
import com.tirmizee.backend.api.user.data.UserDetailDTO;
import com.tirmizee.backend.service.UserService;
import com.tirmizee.backend.web.data.MessageSuccess;
import com.tirmizee.core.datatable.RequestTable;
import com.tirmizee.core.datatable.ResponseTable;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {
	
	public final Logger LOG = Logger.getLogger(ApiUserController.class);
	
	@Autowired 
	private UserService userService;
	
	@PostMapping(path = "/password/firstlogin")
	public MessageSuccess changePasswordFirstLogin(@RequestBody @Valid ReqPasswordDTO passwordDTO) {
		final String username = SecurityContextHolder.getContext().getAuthentication().getName();
		userService.changePasswordFirstLogin(username, passwordDTO);
		return new MessageSuccess();
	}
	
	@PostMapping(path = "/password/expried")
	public MessageSuccess changePasswordExpried(@RequestBody @Valid ReqPasswordExpriedDTO passwordExpriedDTO) {
		final String username = SecurityContextHolder.getContext().getAuthentication().getName();
		userService.changePasswordExpired(username, passwordExpriedDTO);
		return new MessageSuccess();
	}
	
	@PostMapping(path = "/forgotpassword")
	public MessageSuccess forgotPassword(@RequestBody @Valid ReqForgotPasswordDTO forgotPasswordDTO) {
		userService.forgotPassword(forgotPasswordDTO.getEmail());
		return new MessageSuccess();
	}
	
	@PostMapping(path = "/page")
	public DeferredResult<ResponseTable<UserDetailDTO>> pageDataTable(@RequestBody @Valid RequestTable<UserDetailCriteriaDTO> requestTable){
		DeferredResult<ResponseTable<UserDetailDTO>> deferredResult = new DeferredResult<>(60000L);
		ForkJoinPool.commonPool().submit(()->{
			try {
				ResponseTable<UserDetailDTO> result = userService.pagingTable(requestTable);
				deferredResult.setResult(result);
			}catch (Exception ex) {
				deferredResult.setErrorResult(ex);
			}
		});
		return deferredResult;
	}

	@GetMapping(path = "/count")
	public DeferredResult<Long> count() {
		DeferredResult<Long> deferredResult = new DeferredResult<>(60000L);
		ForkJoinPool.commonPool().submit(()->{
			try {
				deferredResult.setResult(userService.countUses());
			}catch (Exception ex) {
				deferredResult.setErrorResult(ex);
			}
		});
		return deferredResult;
	}
	
}
