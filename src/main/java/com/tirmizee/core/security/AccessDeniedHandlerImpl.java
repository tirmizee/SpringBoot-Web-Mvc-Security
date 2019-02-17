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
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.stereotype.Component;


@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler{
	
	public static final Logger LOG = Logger.getLogger(AccessDeniedHandlerImpl.class);

	private final RedirectStrategy STRATEGY = new DefaultRedirectStrategy();
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		if (accessDeniedException instanceof InvalidCsrfTokenException) {
			STRATEGY.sendRedirect(request, response, "/");
		}else if (accessDeniedException instanceof MissingCsrfTokenException) {
			STRATEGY.sendRedirect(request, response, "/");
		}
		STRATEGY.sendRedirect(request, response, "/");
	}

}
