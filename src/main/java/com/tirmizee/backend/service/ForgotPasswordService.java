package com.tirmizee.backend.service;

public interface ForgotPasswordService {

	String generateToken();
	
	String createURLResetPassword(Long uid, String token);
	
	void validatePasswordResetToken(Long uid, String token);
	
}
