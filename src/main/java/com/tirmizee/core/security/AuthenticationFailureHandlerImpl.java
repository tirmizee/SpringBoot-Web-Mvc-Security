package com.tirmizee.core.security;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.tirmizee.core.constant.PermissionCode;
import com.tirmizee.core.exception.FirstloginException;
import com.tirmizee.core.exception.LockPasswordInvalidException;
import com.tirmizee.core.exception.LockTimePasswordInvalidException;
import com.tirmizee.core.exception.PasswordExpriedException;
import com.tirmizee.core.exception.UserAccountDisabledException;
import com.tirmizee.core.exception.UserAccountExpiredException;
import com.tirmizee.core.utilities.DateUtils;

/**
 * @author Pratya Yeekhaday
 *
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFailureHandlerImpl.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		LOGGER.info("AuthenticationException : {}", exception.getMessage());
		
		/*DETERMINE URL FOR USERNAME INVALID*/
		if (exception instanceof UsernameNotFoundException) {
			redirectStrategy.sendRedirect(request, response, "/login?error=Username or Password invalid");
		} 
		
		/*DETERMINE URL FOR USERNAME IS LOCKED*/
		else if(exception instanceof LockedException) {
			redirectStrategy.sendRedirect(request, response, "/login?error=Username is Locked");
		}
		
		/*DETERMINE URL FOR PASSWORD INVALID*/
		else if (exception instanceof LockTimePasswordInvalidException) {
			
			LockTimePasswordInvalidException lockTimeException = (LockTimePasswordInvalidException) exception;
			String errorName = "Username or Password invalid";
			Timestamp lockTime = lockTimeException.getLockedTime();

			if (lockTime != null) {
				String dateFormat = DateUtils.DD_MM_YYYY.format(lockTime);
				errorName = DateUtils.nowBefore(lockTime) ? "Username is locked <br> Time : " + dateFormat : errorName;
			} 
			
			redirectStrategy.sendRedirect(request, response, "/login?error=" + errorName);
		}
		
		// DETERMINE URL FOR PASSWORD INVALID
		else if(exception instanceof LockPasswordInvalidException) {
			LockPasswordInvalidException passwordInvalidException = (LockPasswordInvalidException) exception;
			String error = passwordInvalidException.isLocked() ? "Username is Locked" : "Username or Password invalid";
			redirectStrategy.sendRedirect(request, response, String.format("/login?error=%s", error));
		} 
		
		// DETERMINE URL FOR ACCOUNT DISABLED
		else if(exception instanceof UserAccountDisabledException) {
			redirectStrategy.sendRedirect(request, response, "/login?error=User Account is Disabled");
		}
		
		// DETERMINE URL FOR ACCOUNT EXPRIED
		else if(exception instanceof UserAccountExpiredException) {
			redirectStrategy.sendRedirect(request, response, "/login?error=User Account is Expired");
		}

		// DETERMINE URL FOR USER FIRST LOGIN
		else if(exception instanceof FirstloginException) {
			final FirstloginException firstloginException = (FirstloginException) exception;
			final String username = firstloginException.getUsername();
			SecurityContextHolderUtils.grantAuthority(username, PermissionCode.PG00);
			redirectStrategy.sendRedirect(request, response, "/firstlogin");
		}
		
		// DETERMINE URL FOR USER PASSWORD EXPRIED
		else if(exception instanceof PasswordExpriedException) {
			final PasswordExpriedException passwordExpriedException = (PasswordExpriedException) exception;
			final String username = passwordExpriedException.getUsername();
			SecurityContextHolderUtils.grantAuthority(username, PermissionCode.PG01);
			redirectStrategy.sendRedirect(request, response, "/passwordexpried");
		}
		
		else redirectStrategy.sendRedirect(request, response, "/login");
		
	}
	
}
