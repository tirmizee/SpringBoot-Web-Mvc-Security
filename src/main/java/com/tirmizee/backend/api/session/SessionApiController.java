package com.tirmizee.backend.api.session;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.tirmizee.backend.api.session.data.UserLoggedDTO;
import com.tirmizee.backend.service.SessionService;
import com.tirmizee.backend.web.data.MessageSuccess;

@RestController
@RequestMapping(path = "/api/session")
public class SessionApiController {
	
	@Autowired
	private SessionService sessionService;
	
	@PreAuthorize("hasAnyAuthority('P003')")
	@GetMapping(path = "/countuserlogged")
	public Integer countUserLogged() {
		return sessionService.countUserLogged();
	}
	
	@PreAuthorize("hasAnyAuthority('P003')")
	@PostMapping(path = "/alluserlogged")
	public DeferredResult<List<UserLoggedDTO>> allUserLogged() {
		DeferredResult<List<UserLoggedDTO>> deferredResult = new DeferredResult<>(60000L);
		ForkJoinPool.commonPool().submit(() -> {
			try {
				List<UserLoggedDTO> result = sessionService.allUserLogged();
				deferredResult.setResult(result);
			}catch (Exception ex) {
				deferredResult.setErrorResult(ex);
			}
		});
		return deferredResult;
	}
	
	@PreAuthorize("hasAnyAuthority('P003')")
	@GetMapping(path = "/removesession/{username}")
	public MessageSuccess removeSession(@PathVariable String username) {
		sessionService.removeSession(username);
		return new MessageSuccess();
	}

}
