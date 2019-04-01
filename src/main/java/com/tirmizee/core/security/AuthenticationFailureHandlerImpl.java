package com.tirmizee.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.tirmizee.core.constant.PermissionCode;
import com.tirmizee.core.exception.FirstloginException;
import com.tirmizee.core.exception.LimitBadCredentialsException;
import com.tirmizee.core.exception.PasswordExpriedException;

/**
 * @author Pratya Yeekhaday
 *
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
	
	public static final Logger LOG = Logger.getLogger(AuthenticationFailureHandlerImpl.class);
	
	private final RedirectStrategy STRATEGY = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		// DETERMINE URL FOR USERNAME INVALID
		if (exception instanceof UsernameNotFoundException) {
			STRATEGY.sendRedirect(request, response, "/login?error=Username or Password invalid");
		} 
		
		// DETERMINE URL FOR PASSWORD INVALID
		else if(exception instanceof LimitBadCredentialsException) {
			LimitBadCredentialsException badCredentials = (LimitBadCredentialsException) exception;
			String error = badCredentials.isLocked() ? "Username is Locked" : "Username or Password invalid";
			STRATEGY.sendRedirect(request, response, String.format("/login?error=%s", error));
		} 
		
		// DETERMINE URL FOR USERNAME LOCKED
		else if(exception instanceof LockedException) {
			STRATEGY.sendRedirect(request, response, "/login?error=Username is Locked");
		}

		// DETERMINE URL FOR USER FIRST LOGIN
		else if(exception instanceof FirstloginException) {
			final FirstloginException firstloginException = (FirstloginException) exception;
			final String username = firstloginException.getUsername();
			SecurityContextHolderUtils.grantAuthority(username, PermissionCode.PG00);
			STRATEGY.sendRedirect(request, response, "/firstlogin");
		}
		
		// DETERMINE URL FOR USER PASSWORD EXPRIED
		else if(exception instanceof PasswordExpriedException) {
			final PasswordExpriedException passwordExpriedException = (PasswordExpriedException) exception;
			final String username = passwordExpriedException.getUsername();
			SecurityContextHolderUtils.grantAuthority(username, PermissionCode.PG01);
			STRATEGY.sendRedirect(request, response, "/passwordexpried");
		}
		
		else STRATEGY.sendRedirect(request, response, "/login");
		
	}
	
}
