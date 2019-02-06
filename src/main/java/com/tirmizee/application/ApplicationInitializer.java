package com.tirmizee.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(
	scanBasePackages = { "com.tirmizee" }
)
public class ApplicationInitializer extends SpringBootServletInitializer {

	public static void main(String...args) {
		SpringApplication.run(ApplicationInitializer.class, args);
	}
	
}

