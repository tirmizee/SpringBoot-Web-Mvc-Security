package com.tirmizee.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;

public class CustomConcurrentSessionControlAuthenStrategy extends ConcurrentSessionControlAuthenticationStrategy {

	public CustomConcurrentSessionControlAuthenStrategy(SessionRegistry sessionRegistry) {
		super(sessionRegistry);
	}

	@Override
	protected int getMaximumSessionsForThisUser(Authentication authentication) {
		return 1;
	}
	
}
