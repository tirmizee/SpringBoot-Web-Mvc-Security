package com.tirmizee.core.configuration;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	public static final Logger LOG = Logger.getLogger(WebMvcConfig.class);
	
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
	    cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
	    return cookieLocaleResolver;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    return new LocaleChangeInterceptor() {
	    	@Override
			public String getParamName() {
				return "language";
			}
	    	@Override
	    	protected Locale parseLocaleValue(String locale) {
	    		String repairedLocale = locale.replaceAll("'", "");
	    		if ("th".equalsIgnoreCase(repairedLocale)) {
	    			return new Locale("th");
	    		}
	    		return Locale.ENGLISH;
	    	}
	    };
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	
}
