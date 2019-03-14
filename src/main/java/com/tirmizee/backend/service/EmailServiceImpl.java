package com.tirmizee.backend.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.tirmizee.core.component.TemplateUtils;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private TemplateUtils template;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendMailForgotPassword(String email, String url) {
		
		MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        
    	Map<String, Object> model = new HashMap<>();
		model.put("url", url);
		
        try {
			helper = new MimeMessageHelper(message, true ,"UTF-8");
	        helper.setTo(email);
	        helper.setSubject("Forgot Password");
	        helper.setText(template.load("ForgotPassword.ftl", model) , true);
	        mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
