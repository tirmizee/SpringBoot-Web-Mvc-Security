package com.tirmizee.backend.service;

import com.tirmizee.backend.service.data.ForgotPasswordModel;

public interface EmailService {
	
	void sendMailForgotPassword(ForgotPasswordModel forgotPasswordModel);

}
