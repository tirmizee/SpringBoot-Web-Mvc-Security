package com.tirmizee.core.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.tirmizee.backend.service.UserAttempService;
import com.tirmizee.backend.service.UserService;
import com.tirmizee.backend.service.data.LockUserTime;
import com.tirmizee.core.exception.FirstloginException;
import com.tirmizee.core.exception.LockTimePasswordInvalidException;
import com.tirmizee.core.exception.PasswordExpriedException;
import com.tirmizee.core.exception.UserAccountDisabledException;
import com.tirmizee.core.exception.UserAccountExpiredException;
import com.tirmizee.core.utilities.DateUtils;

/**
 * @author Pratya Yeekhaday
 *
 */
public class AuthenticationProviderImpl extends DaoAuthenticationProvider {
	
	public final Logger LOGGER = LoggerFactory.getLogger(AuthenticationProviderImpl.class);
	
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
			
			/*FIRST LOGIN */
			if (userProfile.isFirstLogin()) {
				LOGGER.info("{} : first login", username);
				throw new FirstloginException(username, "Force password change first login");
			}
			
			/*PASSWORD EXPIRED*/ 
			if(DateUtils.nowAfter(userProfile.getCredentialsExpiredDate())) {
				LOGGER.info("{} : password expried", username);
				userService.fourcePasswordExpired(username);
				throw new PasswordExpriedException(username, "Force password expried change");
			}
			
			/*ACCOUNT EXPIRED*/ 
			if(DateUtils.nowAfter(userProfile.getAccountExpiredDate())) {
				LOGGER.info("{} : account expired", username);
				userService.fourceAccountExpired(username);
				throw new UserAccountExpiredException(username, "User account is expired");
			}
			
			/*ACCOUNT LOCKED TIME*/
			if(DateUtils.nowBefore(userProfile.getAccountLockedDate())) {
				LOGGER.info("{} : account locked time", username);
				throw new LockTimePasswordInvalidException(username, "User account is expired", userProfile.getAccountLockedDate());
			}
			
			task.execute(() -> userAttempService.resetLoginAttempt(username, accessIp));
			
			return authen;
			
		} catch (BadCredentialsException ex) {
			LOGGER.info("{} : password invalid", username);
//			LockUser lockUser = userAttempService.updateLoginAttemptFail(username, accessIp);
			LockUserTime lockUserTime = userAttempService.updateLockUserTime(username, accessIp);
			throw new LockTimePasswordInvalidException(ex.getMessage(), username, lockUserTime.getLockTime());
		} catch (AccountExpiredException ex) {
			LOGGER.info("{} : user account is expired", username);
			throw new UserAccountExpiredException(username, "User account is expired");	
		} catch (DisabledException ex) {
			LOGGER.info("{} : user account is disabled", username);
			throw new UserAccountDisabledException(username, "User account is disabled");	
		} catch (CredentialsExpiredException ex) {
			LOGGER.info("{} : force password expried change", username);
			throw new PasswordExpriedException(username, "Force password expried change");
		} catch (Exception exception) {
			LOGGER.info("{} : {}", username, exception.getMessage());
			throw exception;
		}
	}
	
}
        