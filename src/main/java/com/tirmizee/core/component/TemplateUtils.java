package com.tirmizee.core.component;

import java.util.Map;

public interface TemplateUtils {
	
	String load(String templateName , Map<String, Object> model);

}
