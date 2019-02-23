package com.tirmizee.backend.api.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.backend.api.user.data.RequestPasswordDTO;
import com.tirmizee.backend.service.UserService;
import com.tirmizee.backend.web.data.ResponseSuccess;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

	@Autowired 
	private UserService userService;
	
	@PostMapping(path = "/password/firstlogin")
	public ResponseSuccess changePasswordFirstLogin(@RequestBody @Valid RequestPasswordDTO changePasswordDTO) {
		final String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userService.changePasswordFirstLogin(username, changePasswordDTO);
	}
	
}
