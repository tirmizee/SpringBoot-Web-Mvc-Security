package com.tirmizee.backend.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.tirmizee.backend.service.data.ForgotPasswordModel;
import com.tirmizee.core.component.TemplateUtils;

@Service
public class EmailServiceImpl implements EmailService {
	
	public static final String TEMPLATE_FORGOTPASSWORD = "ForgotPassword.ftl";
	
	@Autowired
	private TemplateUtils template;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendMailForgotPassword(ForgotPasswordModel forgotPasswordModel, Resource...file) {
		
		MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        
    	Map<String, Object> model = new HashMap<>();
		model.put("object", forgotPasswordModel);
				
        try {
			helper = new MimeMessageHelper(message, true ,"UTF-8");
	        helper.setTo(forgotPasswordModel.getEmail());
	        helper.setSubject(forgotPasswordModel.getTitle());
	        helper.setText(template.load(TEMPLATE_FORGOTPASSWORD, model) , true);
	        for (Resource attachment : file) {
	        	helper.addAttachment(attachment.getFilename(), attachment);
			}
	        mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
