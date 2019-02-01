package com.tirmizee.core.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(
	scanBasePackages = { "com.tirmizee" },
	exclude = { SecurityAutoConfiguration.class }
)
public class Application extends SpringBootServletInitializer {

	public static void main(String...args) {
		SpringApplication.run(Application.class, args);
	}
	
}

