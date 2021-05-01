package com.tirmizee.core.component;

import java.util.Locale;

import org.springframework.context.HierarchicalMessageSource;


/**
 * @author Pratya Yeekhday
 *
 */
public interface VarargMessageSource extends HierarchicalMessageSource {
	
	String getVargMessage(String code, Object...args);

	String getVargMessage(Locale locale, String code, Object...args);
	
	String getVargMessageDefault(String defaultMessage, String code, Object...args);
	
	String getVarargsMessageDefault(String defaultMessage, Locale locale, String code, Object...args);
	
}
