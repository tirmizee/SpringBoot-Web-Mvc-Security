package com.tirmizee.core.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.tirmizee.backend.service.UserAttempService;
import com.tirmizee.backend.service.UserService;
import com.tirmizee.core.exception.FirstloginException;
import com.tirmizee.core.exception.LimitBadCredentialsException;
import com.tirmizee.core.exception.PasswordExpriedException;

public class AuthenticationProviderImpl extends DaoAuthenticationProvider {
	
	public static final Logger LOG = Logger.getLogger(AuthenticationProviderImpl.class);

	@Autowired
	private HttpServletRequest servletRequest;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAttempService userAttempService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		final String accessIp = servletRequest.getRemoteAddr();
		final String username = authentication.getName();
		
		try {
			
			Authentication authen = super.authenticate(authentication);
			UserProfile userProfile =  (UserProfile) authen.getPrincipal();
			userAttempService.resetLoginAttempt(username, accessIp);
			
			// USER FIRST LOGIN 
			if (userProfile.isFirstLogin()) {
				LOG.info(username + " : " + "first login");
				throw new FirstloginException(username, "Force password change first login");
			}
			
			// USER PASSWORD EXPRIED 
			if(userService.isPasswordExpried(userProfile.getCredentialsExpiredDate())) {
				LOG.info(username + " : " + "password expried");
				userService.updatePasswordExpired(username);
				throw new PasswordExpriedException(username, "Force password expried change");
			}
			
			return authen;
			
		} catch (BadCredentialsException ex) {
			boolean isLocked = userAttempService.updateLoginAttemptIsLocked(username, accessIp);
			throw new LimitBadCredentialsException(ex.getMessage(), username, isLocked);
		} catch (CredentialsExpiredException ex) {
			throw new PasswordExpriedException(username, "Force password expried change");
		} catch (Exception ex) {
			throw ex;
		}
	}
	
}
        