package com.tirmizee.backend.api.user;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.backend.api.user.data.ReqPasswordDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordExpriedDTO;
import com.tirmizee.backend.service.UserService;
import com.tirmizee.backend.web.data.MessageSuccess;

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
	
}
