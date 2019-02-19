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

import com.tirmizee.core.exception.LimitBadCredentialsException;

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
		
		// DETERMINE URL
		String url = determineRedirectUrl(exception);
		
		// REDIRECT TO TARGET
		STRATEGY.sendRedirect(request, response, url);
	}
	
	private String determineRedirectUrl( AuthenticationException exception){
		
		// DETERMINE URL FOR USERNAME IS INVALID
		if (exception instanceof UsernameNotFoundException) {
			return "/login?error=Username or Password invalid";
		} 
		
		//DETERMINE URL FOR PASSWORD IS INVALID
		else if(exception instanceof LimitBadCredentialsException) {
			LimitBadCredentialsException badCredentials = (LimitBadCredentialsException) exception;
			String error = badCredentials.isLocked() ? "Username is Locked" : "Username or Password invalid";
			return String.format("/login?error=%s", error);
		} 
		
		//DETERMINE URL FOR USERNAME IS LOCKED
		else if(exception instanceof LockedException) {
			return "/login?error=Username is Locked";
		}
		
		return "/login";
	}
	
}
