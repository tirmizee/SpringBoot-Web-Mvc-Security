<<<<<<< HEAD
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

=======
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

>>>>>>> branch 'master' of https://github.com/tirmizee/SpringBoot.git
