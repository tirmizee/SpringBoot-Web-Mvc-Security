package com.tirmizee.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;

public class CustomConcurrentSessionControlAuthenticationStrategy extends ConcurrentSessionControlAuthenticationStrategy {

	public CustomConcurrentSessionControlAuthenticationStrategy(SessionRegistry sessionRegistry) {
		super(sessionRegistry);
	}

	@Override
	protected int getMaximumSessionsForThisUser(Authentication authentication) {
		System.out.println("saaaaaaaaaaaaaaaaaaaaaaaaaaa");
		return 1;
	}
	
}
