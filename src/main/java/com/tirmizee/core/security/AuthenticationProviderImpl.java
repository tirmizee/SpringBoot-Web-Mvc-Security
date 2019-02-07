package com.tirmizee.core.security;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


public class AuthenticationProviderImpl extends DaoAuthenticationProvider {
	
	public static final Logger LOG = Logger.getLogger(AuthenticationProviderImpl.class);

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String username = authentication.getName();
		try{
			Authentication authen = super.authenticate(authentication);
			return authen;
		}catch (Exception e) {
			throw e;
		}
	}
	
}
        