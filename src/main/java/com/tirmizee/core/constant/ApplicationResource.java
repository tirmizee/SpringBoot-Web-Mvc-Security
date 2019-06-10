package com.tirmizee.core.constant;

import java.util.ResourceBundle;

public final class ApplicationResource {

	public static final ResourceBundle APPLICATION = ResourceBundle.getBundle("application");
	public static final String SERVER_CONTEXT_PATH = APPLICATION.getString("server.contextPath");
	public static final String SERVER_PORT = APPLICATION.getString("server.port");
	public static final String SECURITY_CSRF_HEADER = APPLICATION.getString("security.csrf.header");
	public static final String SPRING_PROFILE_ACTIVE = APPLICATION.getString("spring.profiles.active");
	
}
