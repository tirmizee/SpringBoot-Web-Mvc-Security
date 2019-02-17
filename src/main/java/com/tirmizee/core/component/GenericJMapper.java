package com.tirmizee.core.component;

import java.util.List;

import org.springframework.data.domain.Page;

public interface GenericJMapper {
	
	<D,S> D map(S source, final Class<D> destination);
	
	<D,S> List<D> map(List<S> source, final Class<D> destination);
	
	<D,S> Page<D> map(Page<S> source, Class<D> destination);

}
