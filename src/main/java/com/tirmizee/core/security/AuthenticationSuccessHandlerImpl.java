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

import com.tirmizee.backend.dao.AppSettingDao;
import com.tirmizee.core.constant.PermissionCode;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler{

	private final RedirectStrategy STRATEGY = new DefaultRedirectStrategy();
	
	@Autowired
	private AppSettingDao appSetting;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		// DETERMINE URL FOR PERMISSION
		String targetUrl = determineTargetUrl(authentication);
		
		// SET SESSION TIMEOUT
		int sessionTimeout = Integer.parseInt(appSetting.findOne(SESSION_TIME_OUT).getValue());
		request.getSession().setMaxInactiveInterval(sessionTimeout * 60);
		
		// REDIRECT TO URL 
		STRATEGY.sendRedirect(request, response, targetUrl);
		
	}
	
	protected String determineTargetUrl(Authentication authentication) {
		Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if (authorities.contains(PermissionCode.TR001)) {
			return "/main";
		}else {
			return "/main";
		}
	}
	
}
