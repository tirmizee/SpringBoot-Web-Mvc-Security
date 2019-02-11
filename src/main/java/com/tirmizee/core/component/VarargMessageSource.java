package com.tirmizee.core.component;

import java.util.Locale;

import org.springframework.context.HierarchicalMessageSource;


/**
 * @author Pratya Yeekhday
 *
 */
public interface VarargMessageSource extends HierarchicalMessageSource {
	
	String getVarargsMessage(String code, Object...args);

	String getVarargsMessage(Locale locale, String code, Object...args);
	
	String getVarargsMessageDefault(String defaultMessage, String code, Object...args);
	
	String getVarargsMessageDefault(String defaultMessage,Locale locale, String code, Object...args);
	
}
