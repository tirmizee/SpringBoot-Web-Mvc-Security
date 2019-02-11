package com.tirmizee.core.component;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author Pratya Yeekhday
 * 
 */
public class VarargMessageSourceImpl extends ReloadableResourceBundleMessageSource implements VarargMessageSource {
	
	@Override
	public String getVarargsMessage(String code, Object...args) {
		return getMessage(code, args, LocaleContextHolder.getLocale());
	}
	
	@Override
	public String getVarargsMessage(Locale locale, String code, Object...args) {
		return getMessage(code, args, locale);
	}

	@Override
	public String getVarargsMessageDefault(String defaultMessage, String code, Object...args) {
		return getMessage(code, args, defaultMessage, LocaleContextHolder.getLocale());
	}
	
	@Override
	public String getVarargsMessageDefault(String defaultMessage, Locale locale, String code, Object...args) {
		return getMessage(code, args, defaultMessage, locale);
	}

}
