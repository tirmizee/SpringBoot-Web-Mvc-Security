package com.tirmizee.core.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tirmizee.core.component.PageMapper;
import com.tirmizee.core.component.PageMapperImpl;


@Configuration
public class DozerMapperConfig {
	
	@Bean	
	public PageMapper getCustomMapper(){
		return new PageMapperImpl(Arrays.asList("dozer-config.xml"));
	}

=======
package com.tirmizee.core.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tirmizee.core.component.PageMapper;
import com.tirmizee.core.component.PageMapperImpl;

@Configuration
public class DozerMapperConfig {
	
	@Bean	
	public PageMapper getCustomMapper(){
		return new PageMapperImpl(Arrays.asList("dozer-config.xml"));
	}

}