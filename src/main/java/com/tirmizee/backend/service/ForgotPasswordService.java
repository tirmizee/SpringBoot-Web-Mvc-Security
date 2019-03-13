package com.tirmizee.backend.service;

public interface ForgotPasswordService {

	boolean isTokenExpired(String token);
	
	String generateToken();
	
	String createUrlResetPassword(String token);
	
}
