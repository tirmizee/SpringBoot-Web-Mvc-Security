package com.tirmizee.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.stereotype.Component;

/**
 * @author Pratya Yeekhaday
 *
 */

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
	
	public static final Logger LOG = Logger.getLogger(AccessDeniedHandlerImpl.class);

	private final RedirectStrategy STRATEGY = new DefaultRedirectStrategy();
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		LOG.debug(accessDeniedException.getMessage());
		
		// HANDLER CSRF TIME OUT
		if (accessDeniedException instanceof MissingCsrfTokenException) {
			
			// WHEN LOGIN
			if (request.getRequestURI().contains("login")) {
				STRATEGY.sendRedirect(request, response, "/login?error=Token timeout please try again");
			} 
			
			// WHEN LOGOUT
			else if (request.getRequestURI().contains("logout")) {
				STRATEGY.sendRedirect(request, response, "/login");
			} 
			
			else {
				STRATEGY.sendRedirect(request, response, "/accessdenied");
			}

		} else {
			STRATEGY.sendRedirect(request, response, "/accessdenied");
		}
	}

}
