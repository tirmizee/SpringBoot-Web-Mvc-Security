package com.tirmizee.core.component;

import java.util.List;
import java.util.Set;

import org.dozer.Mapper;
import org.springframework.data.domain.Page;

/**
 * @author pratya yeekhaday
 *
 */
public interface PageMapper extends Mapper {
	
	<T> Set<T> map(Set<?> source, Class<T> destinationClass);

	<T> List<T> map(List<?> source, Class<T> destinationClass);
	
	<T> Page<T> map(Page<?> source, Class<T> destinationClass);

}
