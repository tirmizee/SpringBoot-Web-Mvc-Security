package com.tirmizee.core.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.tirmizee.backend.service.UserAttempService;
import com.tirmizee.backend.service.UserService;
import com.tirmizee.core.exception.UserAccountExpiredException;
import com.tirmizee.core.exception.FirstloginException;
import com.tirmizee.core.exception.LimitBadCredentialsException;
import com.tirmizee.core.exception.PasswordExpriedException;
import com.tirmizee.core.utilities.DateUtils;


/**
 * @author Pratya Yeekhaday
 *
 */
public class AuthenticationProviderImpl extends DaoAuthenticationProvider {
	
	public static final Logger LOG = Logger.getLogger(AuthenticationProviderImpl.class);

	@Autowired
	@Qualifier("taskExecutor")
	private TaskExecutor task;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAttempService userAttempService;
	
	@Autowired
	private HttpServletRequest servletRequest;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		final String accessIp = servletRequest.getRemoteAddr();
		final String username = authentication.getName();
		
		try {
			
			Authentication authen = super.authenticate(authentication);
			UserProfile userProfile =  (UserProfile) authen.getPrincipal();
			task.execute(() -> userAttempService.resetLoginAttempt(username, accessIp));
			
			// FIRST LOGIN 
			if (userProfile.isFirstLogin()) {
				LOG.info(username + " : " + "first login");
				throw new FirstloginException(username, "Force password change first login");
			}
			
			// PASSWORD EXPRIED 
			if(DateUtils.nowAfter(userProfile.getCredentialsExpiredDate())) {
				LOG.info(username + " : " + "password expried");
				userService.fourcePasswordExpired(username);
				throw new PasswordExpriedException(username, "Force password expried change");
			}
			
			// ACCOUNT EXPRIED 
			if(DateUtils.nowAfter(userProfile.getAccountExpiredDate())) {
				LOG.info(username + " : " + "account expired");
				userService.fourceAccountExpired(username);
				throw new UserAccountExpiredException(username, "User account is expired");
			}
			
			return authen;
			
		} catch (BadCredentialsException ex) {
			
			boolean isLocked = userAttempService.updateLoginAttemptIsLocked(username, accessIp);
			throw new LimitBadCredentialsException(ex.getMessage(), username, isLocked);
	
		} catch (AccountExpiredException ex) {
			throw new UserAccountExpiredException(username, "User account is expired");	
		} catch (CredentialsExpiredException ex) {
			throw new PasswordExpriedException(username, "Force password expried change");
		} catch (Exception exception) {
			throw exception;
		}
	}
	
}
        