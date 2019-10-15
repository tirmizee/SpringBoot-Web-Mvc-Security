package com.tirmizee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ApplicationInitializer extends SpringBootServletInitializer implements CommandLineRunner {

	public final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	public static void main(String...args) {
		SpringApplication.run(ApplicationInitializer.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
	
	/*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationInitializer.class);
    }*/
	
} 

