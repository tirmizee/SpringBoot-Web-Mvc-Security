package com.tirmizee.core.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Component
public class RequestLoggingFilter extends AbstractRequestLoggingFilter {
	
	private static final Logger LOG = Logger.getLogger(RequestLoggingFilter.class);

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {
		LOG.info(message);
	}

	@Override
	protected boolean isIncludeClientInfo() {
		return false;
	}

	@Override
	public boolean isIncludeHeaders() {
		return false;
	}

	@Override
	protected boolean isIncludePayload() {
		return true;
	}

	@Override
	protected boolean isIncludeQueryString() {
		return true;
	}

	@Override
	protected boolean shouldLog(HttpServletRequest request) {
		return request.getRequestURL().toString().indexOf("resource") < 0;
	}
	
}
