package com.tirmizee.core.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.tirmizee.backend.service.MessagingService;
import com.tirmizee.backend.service.SessionService;
import com.tirmizee.core.component.ApplicationSetting;

/**
 * @author Pratya Yeekhaday
 *
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler{

	private final RedirectStrategy STRATEGY = new DefaultRedirectStrategy();
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private MessagingService messagingService;
	
	@Autowired
	private ApplicationSetting applicationSetting;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		// SET ACCESS IP 
		((UserProfile) authentication.getPrincipal()).setAccessIp(request.getRemoteAddr());
		
		// SET SESSION TIMEOUT
		int sessionTimeout = applicationSetting.getSessionTimeOut();
		request.getSession().setMaxInactiveInterval(sessionTimeout * 60);
		
		// DETERMINE DEFAULT URL FOR PERMISSION
		String targetUrl = determineTargetUrl(authentication);
		
		messagingService.sendAsyncMessage("/topic/viewusers", sessionService.allUserLoggedDetail());
		
		// REDIRECT URL TO TARGET 
		STRATEGY.sendRedirect(request, response, targetUrl);
		
	}
	
	protected String determineTargetUrl(Authentication authentication) {
		
//		Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		
		Object principal = authentication.getPrincipal();
		
		if (principal instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) principal;
			switch (StringUtils.upperCase(userProfile.getRoleCode())) {
				case "R01": return "/main";
				case "R02": return "/main";
				case "R03": return "/main";
				default   : return "/main";
			}
		}
		return "/main";
	}
	
}
