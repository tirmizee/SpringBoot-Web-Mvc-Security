package com.tirmizee.backend.service;

import org.springframework.core.io.Resource;

import com.tirmizee.backend.service.data.ForgotPasswordModel;

public interface EmailService {
	
	void sendMailForgotPassword(ForgotPasswordModel forgotPasswordModel, Resource...file);

}
