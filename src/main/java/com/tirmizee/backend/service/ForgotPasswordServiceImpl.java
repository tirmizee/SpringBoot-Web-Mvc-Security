package com.tirmizee.backend.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tirmizee.core.component.PasswordGenerator;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	@Autowired
	private HttpServletRequest request;
	
	@Override
	public boolean isTokenExpired(String token) {
		return false;
	}

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

}
