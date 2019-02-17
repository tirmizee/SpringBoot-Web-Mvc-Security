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
	
	private final RedirectStrategy strategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		//CHECK USERNAME IS INVALID
		if (exception instanceof UsernameNotFoundException) {
			determineRedirectUrl(request, response, "/login?error=Username or Password invalid");
		} 
		
		//CHECK PASSWORD IS INVALID
		else if(exception instanceof LimitBadCredentialsException) {
			LimitBadCredentialsException badCredentials = (LimitBadCredentialsException) exception;
			String error = badCredentials.isLocked() ? "Username is Locked" : "Username or Password invalid";
			determineRedirectUrl(request, response, String.format("/login?error=%s", error));
		} 
		
		//CHECK USERNAME IS LOCKED
		else if(exception instanceof LockedException) {
			determineRedirectUrl(request, response, "/login?error=Username is Locked");
		}
		
		else determineRedirectUrl(request, response, "/login");
		
	}
	
	private void determineRedirectUrl( HttpServletRequest request, HttpServletResponse response, String url) 
			throws ServletException, IOException{
		strategy.sendRedirect(request, response, url);
	}
	
}
