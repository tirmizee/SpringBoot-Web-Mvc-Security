package com.tirmizee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ApplicationInitializer extends SpringBootServletInitializer {

	public static void main(String...args) {
		SpringApplication.run(ApplicationInitializer.class, args);
	}
	
} 

