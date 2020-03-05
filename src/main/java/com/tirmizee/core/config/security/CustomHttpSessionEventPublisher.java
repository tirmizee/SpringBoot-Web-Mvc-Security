package com.tirmizee.core.config.security;

import javax.servlet.http.HttpSessionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.session.HttpSessionEventPublisher;

public class CustomHttpSessionEventPublisher extends HttpSessionEventPublisher {

	public final Logger LOGGER = LoggerFactory.getLogger(CustomHttpSessionEventPublisher.class); 
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		super.sessionCreated(event);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		super.sessionDestroyed(event);
	}

}
