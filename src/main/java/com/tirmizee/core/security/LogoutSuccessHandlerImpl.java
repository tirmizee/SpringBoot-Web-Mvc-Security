package com.tirmizee.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.tirmizee.backend.service.MessagingService;
import com.tirmizee.backend.service.SessionService;

@Component
public class LogoutSuccessHandlerImpl extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

	private SessionService sessionService;
	
	private MessagingService messagingService;
	
	@Autowired
	public LogoutSuccessHandlerImpl(SessionService sessionService, MessagingService messagingService) {
		this.sessionService = sessionService;
		this.messagingService = messagingService;
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		messagingService.sendAsyncMessage("/topic/viewusers", sessionService.allUserLoggedDetail());
		super.handle(request, response, authentication);
	}

}
