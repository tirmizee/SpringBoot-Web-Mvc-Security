package com.tirmizee.backend.service;

public interface ForgotPasswordService {

	String generateToken();
	
	String createUrlResetPassword(String token);
	
	boolean isTokenExpired(String token, boolean includeTokenNull);
	
}
