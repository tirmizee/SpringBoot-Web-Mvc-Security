package com.tirmizee.core.security;

import static com.tirmizee.core.constant.Constant.AppSetting.SESSION_TIME_OUT;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.tirmizee.backend.service.AppSettingService;
import com.tirmizee.core.constant.PermissionCode;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler{

	private final RedirectStrategy STRATEGY = new DefaultRedirectStrategy();
	
	@Autowired
	private AppSettingService appSettingService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		// SET ACCESS IP 
		((UserProfile) authentication.getPrincipal()).setAccessIp(request.getRemoteAddr());
		
		// SET SESSION TIMEOUT
		int sessionTimeout = Integer.parseInt(appSettingService.getValue(SESSION_TIME_OUT));
		request.getSession().setMaxInactiveInterval(sessionTimeout * 60);
		
		// DETERMINE DEFAULT URL FOR PERMISSION
		String targetUrl = determineTargetUrl(authentication);
		
		// REDIRECT URL TO TARGET 
		STRATEGY.sendRedirect(request, response, targetUrl);
		
	}
	
	protected String determineTargetUrl(Authentication authentication) {
		Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if (authorities.contains(PermissionCode.P000)) {
			return "/main";
		}else {
			return "/main";
		}
	}
	
}
