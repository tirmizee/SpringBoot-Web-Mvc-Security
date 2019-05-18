package com.tirmizee.core.constant;

import java.util.ResourceBundle;

public final class DatabaseResource {
	
	private static final ResourceBundle RESOURCE = ResourceBundle.getBundle("db");
	
	public static final String ORACLE_DATASOURCE_DEV_URL = RESOURCE.getString("oracle.datasource.dev.url");
	public static final String ORACLE_DATASOURCE_DEV_USER = RESOURCE.getString("oracle.datasource.dev.username");
	public static final String ORACLE_DATASOURCE_DEV_PASS = RESOURCE.getString("oracle.datasource.dev.password");
	public static final String ORACLE_DATASOURCE_DEV_DRIVER = RESOURCE.getString("oracle.datasource.dev.driver");
	public static final String ORACLE_DATASOURCE_UAT_URL = RESOURCE.getString("oracle.datasource.uat.url");
	public static final String ORACLE_DATASOURCE_UAT_USER = RESOURCE.getString("oracle.datasource.uat.username");
	public static final String ORACLE_DATASOURCE_UAT_PASS = RESOURCE.getString("oracle.datasource.uat.password");
	public static final String ORACLE_DATASOURCE_UAT_DRIVER = RESOURCE.getString("oracle.datasource.uat.driver");
	public static final String ORACLE_DATASOURCE_PRO_URL = RESOURCE.getString("oracle.datasource.pro.url");
	public static final String ORACLE_DATASOURCE_PRO_USER = RESOURCE.getString("oracle.datasource.pro.username");
	public static final String ORACLE_DATASOURCE_PRO_PASS = RESOURCE.getString("oracle.datasource.pro.password");
	public static final String ORACLE_DATASOURCE_PRO_DRIVER = RESOURCE.getString("oracle.datasource.pro.driver");
	
}
