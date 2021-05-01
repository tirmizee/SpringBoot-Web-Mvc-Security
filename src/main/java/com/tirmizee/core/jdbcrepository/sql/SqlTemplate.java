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
	
	private String prefix; 
	private String suffix ;
	private StringBuilder statement = new StringBuilder();
	private Map<String, String> valueMap = new HashMap<String, String>();
	
	public SqlTemplate() {
		prefix = DEFAULT_PREFIX;
		suffix = DEFAULT_SUFFIX;
	}
	
	public SqlTemplate(String prefix, String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;
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
		StrSubstitutor substitutor = new StrSubstitutor(valueMap, prefix, suffix);
		return substitutor.replace(statement).toString();
	}
	
}
