package com.tirmizee.core.jdbcrepository.sql;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

/**
 * @author Pratya Yeekhaday
 *
 */
public class SqlTemplate {

	public static final String DEFAULT_PREFIX = "{"; 
	public static final String DEFAULT_SUFFIX = "}";
	
	private String PREFIX; 
	private String SUFFIX ;
	private StrSubstitutor substitutor;
	private StringBuilder statement = new StringBuilder();
	private Map<String, String> valueMap = new HashMap<String, String>();
	
	public SqlTemplate() {
		PREFIX = DEFAULT_PREFIX;
		SUFFIX = DEFAULT_SUFFIX;
	}
	
	public SqlTemplate(String prefix, String suffix) {
		PREFIX = prefix;
		SUFFIX = suffix;
	}

	public SqlTemplate append(String str) {
		statement.append(str);
		return this;
	}
	
	public SqlTemplate attribute(final String key, final String value) {
		valueMap.put(key, value);
		return this;
	}
	
	public String toString() {
		substitutor = new StrSubstitutor(valueMap, PREFIX, SUFFIX);
		return substitutor.replace(statement).toString();
	}
	
}
