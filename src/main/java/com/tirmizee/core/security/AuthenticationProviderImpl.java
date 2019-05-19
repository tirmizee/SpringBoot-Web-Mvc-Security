package com.tirmizee.core.security;

import javax.servlet.http.HttpServletRequest;

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
				logger.info(username + " : " + "first login");
				throw new FirstloginException(username, "Force password change first login");
			}
			
			/*PASSWORD EXPIRED*/ 
			if(DateUtils.nowAfter(userProfile.getCredentialsExpiredDate())) {
				logger.info(username + " : " + "password expried");
				userService.fourcePasswordExpired(username);
				throw new PasswordExpriedException(username, "Force password expried change");
			}
			
			/*ACCOUNT EXPIRED*/ 
			if(DateUtils.nowAfter(userProfile.getAccountExpiredDate())) {
				logger.info(username + " : " + "account expired");
				userService.fourceAccountExpired(username);
				throw new UserAccountExpiredException(username, "User account is expired");
			}
			
			/*ACCOUNT LOCKED TIME*/
			if(DateUtils.nowBefore(userProfile.getAccountLockedDate())) {
				logger.info(username + " : " + "account locked time");
				throw new LockTimePasswordInvalidException(username, "User account is expired", userProfile.getAccountLockedDate());
			}
			
			task.execute(() -> userAttempService.resetLoginAttempt(username, accessIp));
			
			return authen;
			
		} catch (BadCredentialsException ex) {
			logger.info(username + " : " + "account locked time");
//			LockUser lockUser = userAttempService.updateLoginAttemptFail(username, accessIp);
			LockUserTime lockUserTime = userAttempService.updateLockUserTime(username, accessIp);
			throw new LockTimePasswordInvalidException(ex.getMessage(), username, lockUserTime.getLockTime());
		} catch (AccountExpiredException ex) {
			throw new UserAccountExpiredException(username, "User account is expired");	
		} catch (DisabledException ex) {
			throw new UserAccountDisabledException(username, "User account is disabled");	
		} catch (CredentialsExpiredException ex) {
			throw new PasswordExpriedException(username, "Force password expried change");
		} catch (Exception exception) {
			throw exception;
		}
	}
	
}
        