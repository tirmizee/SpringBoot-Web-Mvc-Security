package com.tirmizee.core.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.tirmizee.backend.service.UserAttempService;
import com.tirmizee.core.exception.LimitBadCredentialsException;


public class AuthenticationProviderImpl extends DaoAuthenticationProvider {
	
	public static final Logger LOG = Logger.getLogger(AuthenticationProviderImpl.class);

	@Autowired
	private HttpServletRequest servletRequest;
	
	@Autowired
	private UserAttempService userAttempService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String accessIp = servletRequest.getRemoteAddr();
		final String username = authentication.getName();
		try{
			Authentication authen = super.authenticate(authentication);
			userAttempService.resetLoginAttempt(username, accessIp);
			return authen;
		}catch (BadCredentialsException ex) {
			boolean isLocked = userAttempService.updateLoginAttemptIsLocked(username, accessIp);
			throw new LimitBadCredentialsException(ex.getMessage(), username, isLocked);
		}catch (Exception ex) {
			throw ex;
		}
	}
	
}
        