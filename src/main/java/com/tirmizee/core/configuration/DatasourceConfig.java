package com.tirmizee.core.configuration;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class DatasourceConfig {
	
	public static final Logger LOG = Logger.getLogger(DatasourceConfig.class);
	
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSourceOracleDev(){
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(env.getProperty("spring.datasource.dev.url"));
		config.setUsername(env.getProperty("spring.datasource.dev.username"));
		config.setPassword(env.getProperty("spring.datasource.dev.password"));
		config.setDriverClassName(env.getProperty("spring.datasource.dev.driver"));
		return new HikariDataSource(config);
	}
	
}
