package com.tirmizee.backend.api.session;

import java.util.concurrent.ForkJoinPool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.tirmizee.backend.api.session.data.UserLoggedDetailDTO;
import com.tirmizee.backend.service.SessionService;
import com.tirmizee.backend.web.data.MessageSuccess;

@RestController
@RequestMapping(path = "/api/session")
public class ApiSessionController {
	
	@Autowired
	private SessionService sessionService;
	
	@PreAuthorize("hasAnyAuthority('P003')")
	@GetMapping(path = "/countuserlogged")
	public Integer countUserLogged() {
		return sessionService.countSessions();
	}
	
	@PreAuthorize("hasAnyAuthority('P003')")
	@GetMapping(path = "/countsessionactive")
	public Integer countSessionActive() {
		return sessionService.countSessionsActive();
	}
	
	@PreAuthorize("hasAnyAuthority('P003')")
	@GetMapping(path = "/countsessionexpired")
	public Integer countSessionExpired() {
		return sessionService.countSessionsExpired();
	}
	
	@PreAuthorize("hasAnyAuthority('P003')")
	@GetMapping(path = "/removesession/{username}/{sessionId}")
	public MessageSuccess removeSession(@PathVariable String username, @PathVariable String sessionId) {
		sessionService.removeSession(username, sessionId);
		return new MessageSuccess();
	}
	
	@PreAuthorize("hasAnyAuthority('P003')")
	@PostMapping(path = "/alluserlogged")
	public DeferredResult<UserLoggedDetailDTO> allUserLogged() {
		DeferredResult<UserLoggedDetailDTO> deferredResult = new DeferredResult<>(60000L);
		ForkJoinPool.commonPool().submit(() -> {
			try {
				UserLoggedDetailDTO userLoggedDetailDTO = sessionService.allUserLoggedDetail();
				deferredResult.setResult(userLoggedDetailDTO);
			}catch (Exception ex) {
				deferredResult.setErrorResult(ex);
			}
		});
		return deferredResult;
	}

}
