package com.tirmizee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class ApplicationInitializer extends SpringBootServletInitializer {

	public static void main(String...args) {
		SpringApplication.run(ApplicationInitializer.class, args);
	}

	@Bean("taskExecutor")
	@ConfigurationProperties("spring.task.execution")
	public TaskExecutor taskExecutor() {
		return  new ThreadPoolTaskExecutor();
	}
	
} 

