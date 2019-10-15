package com.tirmizee.core.configuration;

import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http2.Http2Protocol;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.tirmizee.core.constant.Constant;
import com.tirmizee.core.constant.DatabaseResource;


/**
 * @author Pratya Yeekhaday
 *
 */
@Configuration
@PropertySource("classpath:db.properties")
public class TomcatEmbeddedConfig {

	public static final String JNDI_ORACLE_DEV = "jndi/dev";
	public static final String JNDI_ORACLE_UAT = "jndi/uat";
	public static final String JNDI_ORACLE_PRO = "jndi/pro";

	/*
	 * https://github.com/brettwooldridge/HikariCP/wiki/JNDI-DataSource-Factory-(Tomcat,-etc.)
	 */	
	private ContextResource resourceDatasourceDev() {
		ContextResource resource = new ContextResource();
		resource.setName(JNDI_ORACLE_DEV);
		resource.setType(DataSource.class.getName());
		resource.setProperty("poolName", "TirmizeePool");
		resource.setProperty("auth", "Container");
		resource.setProperty("maxLifetime", "1800000");
		resource.setProperty("connectionTimeout", "15000");
		resource.setProperty("maximumPoolSize", "20");
		resource.setProperty("maxIdle", "10");
		resource.setProperty("minimumIdle", "5");
		resource.setProperty("factory", "com.zaxxer.hikari.HikariJNDIFactory");
		resource.setProperty("driverClassName", DatabaseResource.ORACLE_DATASOURCE_DEV_DRIVER);
		resource.setProperty("jdbcUrl", DatabaseResource.ORACLE_DATASOURCE_DEV_URL);
		resource.setProperty("username", DatabaseResource.ORACLE_DATASOURCE_DEV_USER);
		resource.setProperty("password", DatabaseResource.ORACLE_DATASOURCE_DEV_PASS);
		return resource;
	}
	
	private ContextResource resourceDatasourceUat() {
		ContextResource resource = new ContextResource();
		resource.setName(JNDI_ORACLE_UAT);
		resource.setType(DataSource.class.getName());
		resource.setProperty("auth", "Container");
		resource.setProperty("factory", "com.zaxxer.hikari.HikariJNDIFactory");
		resource.setProperty("driverClassName", DatabaseResource.ORACLE_DATASOURCE_UAT_DRIVER);
		resource.setProperty("jdbcUrl", DatabaseResource.ORACLE_DATASOURCE_UAT_URL);
		resource.setProperty("username", DatabaseResource.ORACLE_DATASOURCE_UAT_USER);
		resource.setProperty("password", DatabaseResource.ORACLE_DATASOURCE_UAT_PASS);
		return resource;
	}
	
	private ContextResource resourceDatasourcePro() {
		ContextResource resource = new ContextResource();
		resource.setName(JNDI_ORACLE_PRO);
		resource.setType(DataSource.class.getName());
		resource.setProperty("auth", "Container");
		resource.setProperty("factory", "com.zaxxer.hikari.HikariJNDIFactory");
		resource.setProperty("driverClassName", DatabaseResource.ORACLE_DATASOURCE_PRO_DRIVER);
		resource.setProperty("jdbcUrl", DatabaseResource.ORACLE_DATASOURCE_PRO_URL);
		resource.setProperty("username", DatabaseResource.ORACLE_DATASOURCE_PRO_USER);
		resource.setProperty("password", DatabaseResource.ORACLE_DATASOURCE_PRO_PASS);
		return resource;
	}
	
	@Bean
	@Profile(Constant.Profiles.DEVELOP)
	public TomcatEmbeddedServletContainerFactory TomcatEmbeddedContainerDev() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
				 tomcat.enableNaming();
		         return super.getTomcatEmbeddedServletContainer(tomcat);
			}
			@Override
			protected void postProcessContext(Context context) {
		        context.getNamingResources().addResource(resourceDatasourceDev());
			}
		};
		
		tomcat.addConnectorCustomizers(connector -> connector.addUpgradeProtocol(new Http2Protocol()));
		return tomcat;
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
				/*		SecurityConstraint securityConstraint = new SecurityConstraint();
		        securityConstraint.setUserConstraint("CONFIDENTIAL");
		        
		        SecurityCollection collection = new SecurityCollection();
		        collection.addPattern("/*");
		        securityConstraint.addCollection(collection);
		         
		        context.addConstraint(securityConstraint);*/
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
	
	private Connector initiateHttpConnector() {
		
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
	    connector.setPort(8090);
	    connector.setSecure(false);
	    connector.setRedirectPort(8433);
	    connector.addUpgradeProtocol(new Http2Protocol());
	    return connector;
	}
	
}
