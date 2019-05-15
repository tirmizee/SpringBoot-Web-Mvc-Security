package com.tirmizee.core.configuration;

import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.tirmizee.core.constant.Constant;
import com.tirmizee.core.constant.Properties;


/**
 * @author User
 *
 */
@Configuration
@PropertySource("classpath:db.properties")
public class TomcatEmbeddedConfig {

	public static final String JNDI_ORACLE_DEV = "jndi/dev";
	public static final String JNDI_ORACLE_UAT = "jndi/uat";
	public static final String JNDI_ORACLE_PRO = "jndi/pro";

	@Autowired
	private Environment env;
	
	@Bean
	@Profile(Constant.Profiles.DEVELOP)
	public TomcatEmbeddedServletContainerFactory TomcatEmbeddedContainerDev() {
		return new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
				 tomcat.enableNaming();
		         return super.getTomcatEmbeddedServletContainer(tomcat);
			}
			@Override
			protected void postProcessContext(Context context) {
				
				SecurityConstraint securityConstraint = new SecurityConstraint();
		         securityConstraint.setUserConstraint("CONFIDENTIAL");
		        
		         SecurityCollection collection = new SecurityCollection();
		         collection.addPattern("/*");
		         securityConstraint.addCollection(collection);
		         
		         context.addConstraint(securityConstraint);
		         context.getNamingResources().addResource(resourceDatasourceDev());
			}
		};
	}
	
	@Bean
	@Profile(Constant.Profiles.UAT)
	public TomcatEmbeddedServletContainerFactory TomcatEmbeddedContainerUat() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
				 tomcat.enableNaming();
		         return super.getTomcatEmbeddedServletContainer(tomcat);
			}
			@Override
			protected void postProcessContext(Context context) {
				context.getNamingResources().addResource(resourceDatasourceUat());
			}
		};
		
		tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
		return tomcat;
	}
	
	@Bean
	@Profile(Constant.Profiles.PRODUCTION)
	public TomcatEmbeddedServletContainerFactory TomcatEmbeddedContainerPro() {
		return new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
				 tomcat.enableNaming();
		         return super.getTomcatEmbeddedServletContainer(tomcat);
			}
			@Override
			protected void postProcessContext(Context context) {
				context.getNamingResources().addResource(resourceDatasourcePro());
			}
		};
	}
	
	/*
	 * https://github.com/brettwooldridge/HikariCP/wiki/JNDI-DataSource-Factory-(Tomcat,-etc.)
	 */	
	private ContextResource resourceDatasourceDev() {
		ContextResource resource = new ContextResource();
		resource.setName(JNDI_ORACLE_DEV);
		resource.setType(DataSource.class.getName());
		resource.setProperty("auth", "Container");
		resource.setProperty("maxActive", "10");
		resource.setProperty("maxIdle", "5");
		resource.setProperty("factory", "com.zaxxer.hikari.HikariJNDIFactory");
		resource.setProperty("driverClassName", env.getProperty(Properties.DB.ORACLE_DEV_DRIVER));
		resource.setProperty("jdbcUrl", env.getProperty(Properties.DB.ORACLE_DEV_URL));
		resource.setProperty("username", env.getProperty(Properties.DB.ORACLE_DEV_USER));
		resource.setProperty("password", env.getProperty(Properties.DB.ORACLE_DEV_PASS));
		return resource;
	}
	
	private ContextResource resourceDatasourceUat() {
		ContextResource resource = new ContextResource();
		resource.setName(JNDI_ORACLE_UAT);
		resource.setType(DataSource.class.getName());
		resource.setProperty("auth", "Container");
		resource.setProperty("factory", "com.zaxxer.hikari.HikariJNDIFactory");
		resource.setProperty("driverClassName", env.getProperty(Properties.DB.ORACLE_UAT_DRIVER));
		resource.setProperty("jdbcUrl", env.getProperty(Properties.DB.ORACLE_UAT_URL));
		resource.setProperty("username", env.getProperty(Properties.DB.ORACLE_UAT_USER));
		resource.setProperty("password", env.getProperty(Properties.DB.ORACLE_UAT_PASS));
		return resource;
	}
	
	private ContextResource resourceDatasourcePro() {
		ContextResource resource = new ContextResource();
		resource.setName(JNDI_ORACLE_PRO);
		resource.setType(DataSource.class.getName());
		resource.setProperty("auth", "Container");
		resource.setProperty("factory", "com.zaxxer.hikari.HikariJNDIFactory");
		resource.setProperty("driverClassName", env.getProperty(Properties.DB.ORACLE_PRO_DRIVER));
		resource.setProperty("jdbcUrl", env.getProperty(Properties.DB.ORACLE_PRO_URL));
		resource.setProperty("username", env.getProperty(Properties.DB.ORACLE_PRO_USER));
		resource.setProperty("password", env.getProperty(Properties.DB.ORACLE_PRO_PASS));
		return resource;
	}
	
	private Connector initiateHttpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
	    connector.setPort(8090);
	    connector.setSecure(false);
	    connector.setRedirectPort(8443);
	    return connector;
	}
	
}
