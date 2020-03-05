package com.tirmizee.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.tirmizee.core.config.security.CustomMethodSecurityExpressionHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

	public CustomMethodSecurityExpressionHandler methodSecurityExpressionHandler() {
		CustomMethodSecurityExpressionHandler methodSecurityExpressionHandler = 
			new CustomMethodSecurityExpressionHandler();
		return methodSecurityExpressionHandler;
	}
	
	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		return methodSecurityExpressionHandler();
	}
	
}
