package com.tirmizee.core.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
	    return restTemplateBuilder
    		.setConnectTimeout(5000)
            .setReadTimeout(5000).defaultMessageConverters()
            .build();
	}
	
}
