package com.tirmizee.core.security;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;

/**
 * @author Pratya Yeekhaday
 *
 */
public class CustomConcurrentSessionControlAuthenStrategy extends ConcurrentSessionControlAuthenticationStrategy {

	public CustomConcurrentSessionControlAuthenStrategy(SessionRegistry sessionRegistry) {
		super(sessionRegistry);
	}

	@Override
	protected int getMaximumSessionsForThisUser(Authentication authentication) {
		
		Object principal = authentication.getPrincipal();
		
		if (principal instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) principal;
			return ObjectUtils.defaultIfNull(userProfile.getMaxSession(), 1);
		}
		
		return 1;
	}
	
}
