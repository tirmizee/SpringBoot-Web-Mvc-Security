package com.tirmizee.core.component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component("TemplateOparations")
public class TemplateOparationsImpl implements TemplateOparations {
	
	@Autowired
	private Configuration configuration;

	@Override
	public String load(String templateName, Map<String, Object> model) {
		try (final StringWriter writer = new StringWriter()){
		
			Template template = configuration.getTemplate(templateName);
			template.process(model, writer);
			return writer.toString();
		
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
