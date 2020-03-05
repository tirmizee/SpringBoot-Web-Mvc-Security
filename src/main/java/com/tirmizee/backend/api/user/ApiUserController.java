package com.tirmizee.backend.api.user;

import java.util.concurrent.ForkJoinPool;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.tirmizee.backend.api.user.data.ReqForgotPasswordDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordExpriedDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordResetTokenDTO;
import com.tirmizee.backend.api.user.data.ReqUpdateStatusDTO;
import com.tirmizee.backend.api.user.data.UserDetailCriteriaDTO;
import com.tirmizee.backend.api.user.data.UserDetailPageDTO;
import com.tirmizee.backend.api.user.data.UserDetailUpdateDTO;
import com.tirmizee.backend.dao.UserDao;
import com.tirmizee.backend.service.UserService;
import com.tirmizee.backend.web.data.MessageSuccess;
import com.tirmizee.core.annotaion.CurrentUser;
import com.tirmizee.core.config.security.UserProfile;
import com.tirmizee.core.datatable.RequestTable;
import com.tirmizee.core.datatable.ResponseTable;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {
	
	public final Logger LOG = Logger.getLogger(ApiUserController.class);

	@Autowired 
	private UserDao userDao;
	
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
	
	@PostMapping(path = "/password/forgot")
	public MessageSuccess forgotPassword(@RequestBody @Valid ReqForgotPasswordDTO forgotPasswordDTO) {
		userService.forgotPassword(forgotPasswordDTO.getEmail());
		return new MessageSuccess();
	}
	
	@PostMapping(path = "/password/reset")
	public MessageSuccess resetPassword(@RequestBody @Valid ReqPasswordResetTokenDTO passwordResetTokenDTO) {
		userService.resetPassword(passwordResetTokenDTO);
		return new MessageSuccess(null, "Reset your password complete.");
	}
	
	@PreAuthorize("hasAnyAuthority('P002,P006')")
	@PostMapping(path = "/update/enabled")
	public MessageSuccess updateEnabled(@RequestBody @Valid ReqUpdateStatusDTO reqStatusDTO, @CurrentUser UserProfile userProfile) {
		userService.validateUserByAuthority(reqStatusDTO.getUsername(), userProfile);
		userService.updateStatusEnable(reqStatusDTO);
		return new MessageSuccess(null, "Update Status Enble Complete.");
	}
	
	@PreAuthorize("hasAnyAuthority('P002,P006')")
	@PostMapping(path = "/update/passwordexpired")
	public MessageSuccess updatePasswordExpired(@RequestBody @Valid ReqUpdateStatusDTO reqStatusDTO, @CurrentUser UserProfile userProfile) {
		userService.validateUserByAuthority(reqStatusDTO.getUsername(), userProfile);
		userService.updateStatusPasswordExpired(reqStatusDTO);
		return new MessageSuccess(null, "Update Status Password Expired Complete.");
	}
	
	@PreAuthorize("hasAnyAuthority('P002,P006')")
	@PostMapping(path = "/update/accountnonexpired")
	public MessageSuccess updateAccountExpired(@RequestBody @Valid ReqUpdateStatusDTO reqStatusDTO, @CurrentUser UserProfile userProfile) {
		userService.validateUserByAuthority(reqStatusDTO.getUsername(), userProfile);
		userService.updateStatusAccountExpired(reqStatusDTO);
		return new MessageSuccess(null, "Update Status Password Expired Complete.");
	}
	
	@PreAuthorize("hasAnyAuthority('P002,P006')")
	@PostMapping(path = "/update/accountnonlocked")
	public MessageSuccess updateAccountNonLocked(@RequestBody @Valid ReqUpdateStatusDTO reqStatusDTO, @CurrentUser UserProfile userProfile) {
		userService.validateUserByAuthority(reqStatusDTO.getUsername(), userProfile);
		userService.updateStatusLocked(reqStatusDTO);
		return new MessageSuccess(null, "Update Status Account Non Locked Complete.");
	}
	
	@PreAuthorize("hasAnyAuthority('P002,P006')")
	@PostMapping(path = "/update/firstlogin")
	public MessageSuccess updateFirstLogin(@RequestBody @Valid ReqUpdateStatusDTO reqStatusDTO, @CurrentUser UserProfile userProfile) {
		userService.validateUserByAuthority(reqStatusDTO.getUsername(), userProfile);
		userService.updateStatusFirstLogin(reqStatusDTO);
		return new MessageSuccess(null, "Update Status First Login Complete.");
	}
	
	@PreAuthorize("hasAnyAuthority('P002,P006')")
	@GetMapping(path = "/get/{userId}")
	public UserDetailUpdateDTO getUser(@PathVariable Long userId, @CurrentUser UserProfile userProfile) {
		userService.validateUserByAuthority(userId, userProfile);
		return userDao.findDetailByUserId(userId);
	}
	
	@PreAuthorize("hasAnyAuthority('P002,P006')")
	@PostMapping(path = "/update")
	public MessageSuccess updateUser(@RequestBody @Valid UserDetailUpdateDTO updateUser, @CurrentUser UserProfile userProfile) {
		userService.validateUserByAuthority(updateUser.getUserId(), userProfile);
		userService.updateUser(updateUser);
		return new MessageSuccess(null, "Update User Complete.");
	}
	
	@PreAuthorize("hasAnyAuthority('P002,P006')")
	@PostMapping(path = "/page")
	public DeferredResult<ResponseTable<UserDetailPageDTO>> pageDataTable(@RequestBody @Valid RequestTable<UserDetailCriteriaDTO> requestTable, @CurrentUser UserProfile userProfile){
		DeferredResult<ResponseTable<UserDetailPageDTO>> deferredResult = new DeferredResult<>(60000L);
		ForkJoinPool.commonPool().submit(()->{
			try {
				ResponseTable<UserDetailPageDTO> result = userService.dataTableByAuthority(requestTable, userProfile);
				deferredResult.setResult(result);
			}catch (Exception exception) {
				deferredResult.setErrorResult(exception);
			}
		});
		return deferredResult;
	}

	@PreAuthorize("hasAnyAuthority('P002,P006')")
	@GetMapping(path = "/count")
	public DeferredResult<Long> count() {
		DeferredResult<Long> deferredResult = new DeferredResult<>(60000L);
		ForkJoinPool.commonPool().submit(()->{
			try {
				deferredResult.setResult(userService.countUses());
			}catch (Exception exception) {
				deferredResult.setErrorResult(exception);
			}
		});
		return deferredResult;
	}
	
}
