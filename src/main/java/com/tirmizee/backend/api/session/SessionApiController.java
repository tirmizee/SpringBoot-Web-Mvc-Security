package com.tirmizee.backend.api.session;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.tirmizee.backend.api.session.data.UserLoggedDTO;
import com.tirmizee.backend.service.SessionService;

@RestController
@RequestMapping(path = "/api/session")
public class SessionApiController {
	
	@Autowired
	private SessionService sessionService;
	
	@GetMapping(path = "/countuserlogged")
	public Integer countUserLogged() {
		return sessionService.countUserLogged();
	}
	
	@PostMapping(path = "/alluserlogged")
	public DeferredResult<List<UserLoggedDTO>> allUserLogged() {
		DeferredResult<List<UserLoggedDTO>> deferredResult = new DeferredResult<>(60000L);
		ForkJoinPool.commonPool().submit(()->{
			try {
				List<UserLoggedDTO> result = sessionService.allUserLogged();
				deferredResult.setResult(result);
			}catch (Exception ex) {
				deferredResult.setErrorResult(ex);
			}
		});
		return deferredResult;
	}
	
	@GetMapping(path = "/removesession/{sessionId}")
	public Object removeSession(@PathVariable String sessionId) {
		return sessionService.removeSession(sessionId);
	}

}
