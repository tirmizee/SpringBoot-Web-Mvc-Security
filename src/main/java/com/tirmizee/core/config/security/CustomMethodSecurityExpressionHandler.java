package com.tirmizee.core.config.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

	private AuthenticationTrustResolver trustResolver =  new AuthenticationTrustResolverImpl();

	@Override
	protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication,
			MethodInvocation invocation) {
	    CustomMethodSecurityExpressionRoot root = new CustomMethodSecurityExpressionRoot(authentication);
	    root.setPermissionEvaluator(getPermissionEvaluator());
	    root.setTrustResolver(this.trustResolver);
	    root.setRoleHierarchy(getRoleHierarchy());
	    return root;
	}
	
	class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

		private Object filterObject;
		private Object returnObject;
		private Object target;
		
		public CustomMethodSecurityExpressionRoot(Authentication authentication) {
			super(authentication);
		}
		
		public boolean isMember(Long OrganizationId) {
			UserProfile userProfile = ((UserProfile) this.getPrincipal());
	        return userProfile.isAccountNonExpired();
	    }
		
		@Override
		public void setFilterObject(Object filterObject) {
			this.filterObject = filterObject;
		}
		
		@Override
		public Object getFilterObject() {
			return filterObject;
		}
		
		@Override
		public void setReturnObject(Object returnObject) {
			this.returnObject = returnObject;
		}
		
		@Override
		public Object getReturnObject() {
			return returnObject;
		}
		
		@Override
		public Object getThis() {
			return target;
		}

		void setThis(Object target) {
			this.target = target;
		}
		
	}
	
}
