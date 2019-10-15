package com.tirmizee.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class AsyncConfig {
	
	@Bean("taskExecutor")
	@ConfigurationProperties("spring.task.execution")
	public TaskExecutor taskExecutor() {
		return  new ThreadPoolTaskExecutor();
	}

}
