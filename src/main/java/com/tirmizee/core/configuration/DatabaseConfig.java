package com.tirmizee.core.configuration;

import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jndi.JndiObjectFactoryBean;

import com.tirmizee.core.constant.Constant;

/**
 * @author Pratya Yeekhaday
 *
 */
@Configuration
//@EnableTransactionManagement
public class DatabaseConfig {
	
	public static final Logger LOG = Logger.getLogger(DatabaseConfig.class);
	
	public static final String JAVA_ENV = "java:/comp/env/";
	
	@Bean
	@Profile(Constant.Profiles.DEVELOP)
	public DataSource dataSourceOracleDev() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();           
		bean.setJndiName(JAVA_ENV + TomcatEmbeddedConfig.JNDI_ORACLE_DEV);  
		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(false);
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();
	}
	
	@Bean
	@Profile(Constant.Profiles.UAT)
	public DataSource dataSourceOracleUat() throws IllegalArgumentException, NamingException{
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();           
		bean.setJndiName(JAVA_ENV + TomcatEmbeddedConfig.JNDI_ORACLE_UAT);  
		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(false);
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();
	}
	
	@Bean
	@Profile(Constant.Profiles.PRODUCTION)
	public DataSource dataSourceOracleProduction() throws IllegalArgumentException, NamingException{
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();           
		bean.setJndiName(JAVA_ENV + TomcatEmbeddedConfig.JNDI_ORACLE_PRO);  
		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(false);
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();
	}
	
	
	@Bean
	@SuppressWarnings("unchecked")
	public Map<String, String> queries(){
		Map<String, String> mapQueries = null;
		try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:query.xml")) {
			mapQueries = (Map<String, String>) context.getBean("mapQueries");
		}
		return mapQueries;
	}

	/*@Bean 
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
	}*/
	
}
