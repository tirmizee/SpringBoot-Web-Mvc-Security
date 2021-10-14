package com.tirmizee.core.config;

import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jndi.JndiObjectFactoryBean;

import com.tirmizee.core.constant.Constant;
import com.tirmizee.core.constant.DatabaseResource;
import com.tirmizee.core.jdbcrepository.NamedQueryJdbcOperations;
import com.tirmizee.core.jdbcrepository.NamedQueryJdbcTemplate;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Pratya Yeekhaday
 *
 */
@Configuration
//@EnableTransactionManagement
public class DatabaseConfig {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);
	
	public static final String JAVA_ENV = "java:/comp/env/";
	
	@Bean("dataSource")
	@Profile(Constant.Profiles.DEVELOP)
	public DataSource dataSourceOracleDev() throws IllegalArgumentException, NamingException {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(DatabaseResource.ORACLE_DATASOURCE_DEV_DRIVER);
		config.setJdbcUrl(DatabaseResource.ORACLE_DATASOURCE_DEV_URL);
		config.setUsername(DatabaseResource.ORACLE_DATASOURCE_DEV_USER);
		config.setPassword(DatabaseResource.ORACLE_DATASOURCE_DEV_PASS);
		config.setMaximumPoolSize(30);
		config.setMinimumIdle(5);
		return new HikariDataSource(config);
	}
	
	@Bean("dataSource")
	@Profile(Constant.Profiles.UAT)
	public DataSource dataSourceOracleUat() throws IllegalArgumentException, NamingException{
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(DatabaseResource.ORACLE_DATASOURCE_UAT_DRIVER);
		config.setJdbcUrl(DatabaseResource.ORACLE_DATASOURCE_UAT_URL);
		config.setUsername(DatabaseResource.ORACLE_DATASOURCE_UAT_USER);
		config.setPassword(DatabaseResource.ORACLE_DATASOURCE_UAT_PASS);
		config.setMaximumPoolSize(30);
		config.setMinimumIdle(5);
		return new HikariDataSource(config);
	}
	
	@Bean("dataSource")
	@Profile(Constant.Profiles.PRODUCTION)
	public DataSource dataSourceOracleProduction() throws IllegalArgumentException, NamingException{
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(DatabaseResource.ORACLE_DATASOURCE_PRO_DRIVER);
		config.setJdbcUrl(DatabaseResource.ORACLE_DATASOURCE_PRO_URL);
		config.setUsername(DatabaseResource.ORACLE_DATASOURCE_PRO_USER);
		config.setPassword(DatabaseResource.ORACLE_DATASOURCE_PRO_PASS);
		config.setMaximumPoolSize(30);
		config.setMinimumIdle(5);
		return new HikariDataSource(config);
	}
	
	@Bean
	@SuppressWarnings("unchecked")
	public Map<String, String> queries(){
		Map<String, String> mapQueries = null;
		try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:jdbc-query.xml")) {
			mapQueries = (Map<String, String>) context.getBean("mapQueries");
		}
		return mapQueries;
	}
	
	@Bean
	public NamedQueryJdbcOperations namedQueryJdbcOperations(Map<String, String> queries, DataSource dataSource) {
		return new NamedQueryJdbcTemplate(dataSource, queries);
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
