package com.tirmizee.backend.service;

public interface EmailService {
	
	void sendMailForgotPassword(String email, String url);

}
