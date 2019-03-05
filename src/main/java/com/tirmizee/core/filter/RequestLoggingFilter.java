package com.tirmizee.core.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Component
public class RequestLoggingFilter extends AbstractRequestLoggingFilter {
	
	private static final Logger LOG = Logger.getLogger(RequestLoggingFilter.class);

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		String accessIp = request.getRemoteAddr();
		LOG.info(name + " : " + accessIp + " : " + message);
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
	protected int getMaxPayloadLength() {
		return 250;
	}

	@Override
	protected boolean shouldLog(HttpServletRequest request) {
		final String requestUrl = request.getRequestURL().toString();
		return StringUtils.indexOfAny(requestUrl, "resource","webjars","ws") < 0;
	}
	
}
