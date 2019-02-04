package com.tirmizee.core.configuration;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.tirmizee.core.constant.Properties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class DatabaseConfig {
	
	public static final Logger LOG = Logger.getLogger(DatabaseConfig.class);
	
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSourceOracleDev(){
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(env.getProperty(Properties.DB.ORACLE_DEV_URL));
		config.setUsername(env.getProperty(Properties.DB.ORACLE_DEV_USER));
		config.setPassword(env.getProperty(Properties.DB.ORACLE_DEV_PASS));
		config.setDriverClassName(env.getProperty(Properties.DB.ORACLE_DEV_DRIVER));
		return new HikariDataSource(config);
	}
	
	@Bean
	public PlatformTransactionManager transactionManagerOracleDev() {
	    return new DataSourceTransactionManager(dataSourceOracleDev());
	}
	
}
