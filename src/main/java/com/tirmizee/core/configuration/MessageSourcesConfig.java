package com.tirmizee.core.configuration;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.tirmizee.core.component.VarargMessageSource;
import com.tirmizee.core.component.VarargMessageSourceImpl;

@Configuration
public class MessageSourcesConfig {
	
	public final Logger LOG = Logger.getLogger(MessageSourcesConfig.class);

	@Bean(name = "messageSource")
	public VarargMessageSource varargMessageSource() {
		VarargMessageSourceImpl varargMessageSource = new VarargMessageSourceImpl();
		varargMessageSource.setBasenames(
			"classpath:i18n/error/errors",
			"classpath:i18n/messages/messages");
		varargMessageSource.setUseCodeAsDefaultMessage(true);
		varargMessageSource.setDefaultEncoding("UTF-8");
		return varargMessageSource;
	}
	
	@Bean
	public LocalValidatorFactoryBean messageValidator() {
	    LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
	    ReloadableResourceBundleMessageSource messageValiadte = new ReloadableResourceBundleMessageSource();
	    messageValiadte.setBasename("classpath:i18n/validates/validates");
	    messageValiadte.setUseCodeAsDefaultMessage(true);
	    messageValiadte.setDefaultEncoding("UTF-8");
	    validator.setValidationMessageSource(messageValiadte);
	    return validator;
	}
	
}
