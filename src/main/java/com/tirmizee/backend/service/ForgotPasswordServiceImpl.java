package com.tirmizee.backend.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tirmizee.backend.dao.ForgotPasswordDao;
import com.tirmizee.core.component.PasswordGenerator;
import com.tirmizee.core.domain.ForgotPassword;
import com.tirmizee.core.exception.UrlNotFoundException;
import com.tirmizee.core.utilities.DateUtils;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ForgotPasswordDao forgotPasswordDao;
	
	@Override
	public String generateToken() {
		return UUID.randomUUID().toString() + "-" + PasswordGenerator.generate(20);
	}

	@Override
	public String createUrlResetPassword(String token) {
		String urlFormat = "%s://%s:%d%s";
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		String path = request.getContextPath();
		int port = request.getServerPort();
		String url = String.format(urlFormat, scheme, serverName, port, path);
		return url + "/resetpassword/" + token;
	}

	@Override
	public boolean isTokenExists(String token) {
		return forgotPasswordDao.findByToken(token) != null;
	}
	
	@Override
	public boolean isTokenExpired(String token) {
		ForgotPassword forgotPassword = forgotPasswordDao.findByToken(token);
		if (forgotPassword != null) {
			return DateUtils.nowAfter(forgotPassword.getExpiredDate());
		}
		return false;
	}

	@Override
	public void validateToken(String token) {
		
		ForgotPassword forgotPassword = forgotPasswordDao.findByToken(token);
		
		// VALIDATE TOKEN IS EXISTS
		if (forgotPassword == null) {
			throw new UrlNotFoundException(); 
		}
		
		// VALIDATE TOKEN IS EXPIRED
		if (DateUtils.nowAfter(forgotPassword.getExpiredDate())) {
			throw new UrlNotFoundException(); 
		}
	}

}
