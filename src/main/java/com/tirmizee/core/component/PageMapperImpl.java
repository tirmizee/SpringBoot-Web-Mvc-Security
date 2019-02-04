package com.tirmizee.core.component;
	
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author pratya yeekhaday
 *
 */
public class PageMapperImpl extends DozerBeanMapper implements PageMapper {
	
	public PageMapperImpl(List<String> mappingFiles) {
		super(mappingFiles);
	}

	@Override
	public <T> List<T> map(List<?> source, Class<T> destinationClass) {
		return  (List<T>) map(source, destinationClass, new ArrayList<>());
	}
	
	@Override
	public <T> Set<T> map(Set<?> source, Class<T> destinationClass) {
		return (Set<T>) map(source, destinationClass, new HashSet<>());
	}
	
	@Override
	public <T> Page<T> map(Page<?> source, Class<T> destinationClass){
		return internalMapPage(source, destinationClass);	
	}
	
	private <T> Collection<T> map(Collection<?> source , Class<T> destinationClass ,Collection<T> target){
		for (Object t : source) { 
			target.add(map(t, destinationClass));
		}
		return target;
	}
	
	private <O,T> Page<T> internalMapPage(final Page<O> object, Class<T> destinationClass){
		final List<T> content = new ArrayList<>();
		int page = object.getNumber(),
			size = object.getSize();
		Sort sort = object.getSort();
		PageRequest pageRequest = new PageRequest(page,size,sort);
		for (O o : object.getContent()) {
			content.add(map(o, destinationClass));
		}
		return new PageImpl<T>(content, pageRequest, object.getTotalElements());	
	}
	
}
