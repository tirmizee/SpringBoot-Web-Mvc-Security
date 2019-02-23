package com.tirmizee.core.configuration;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.tirmizee.core.constant.Constant;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
	
	public static final Logger LOG = Logger.getLogger(DatabaseConfig.class);
	
	@Bean
	@Profile(Constant.Profiles.DEVELOP)
	public DataSource dataSourceOracleDev() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();           
		bean.setJndiName("java:/comp/env/" + TomcatEmbeddedConfig.JNDI_ORACLE_DEV);  
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
	}
	
	@Bean
	@Profile(Constant.Profiles.UAT)
	public DataSource dataSourceOracleUat() throws IllegalArgumentException, NamingException{
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();           
		bean.setJndiName("java:/comp/env/" + TomcatEmbeddedConfig.JNDI_ORACLE_UAT);  
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
	}
	
	@Bean
	@Profile(Constant.Profiles.PRODUCTION)
	public DataSource dataSourceOracleProduction() throws IllegalArgumentException, NamingException{
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();           
		bean.setJndiName("java:/comp/env/" + TomcatEmbeddedConfig.JNDI_ORACLE_PRO);  
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
	}

	@Bean 
	@Profile(Constant.Profiles.DEVELOP)
	public PlatformTransactionManager transactionManagerOracleDev() throws IllegalArgumentException, NamingException  {
	    return new DataSourceTransactionManager(dataSourceOracleDev());
	}
	
	@Bean
	@Profile(Constant.Profiles.UAT)
	public PlatformTransactionManager transactionManagerOracleUat() throws IllegalArgumentException, NamingException {
	    return new DataSourceTransactionManager(dataSourceOracleUat());
	}
	
	@Bean
	@Profile(Constant.Profiles.PRODUCTION)
	public PlatformTransactionManager transactionManagerOracleProduction() throws IllegalArgumentException, NamingException {
	    return new DataSourceTransactionManager(dataSourceOracleProduction());
	}
	
}
