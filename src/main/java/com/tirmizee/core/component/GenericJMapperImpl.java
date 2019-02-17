package com.tirmizee.core.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.Global;
import com.googlecode.jmapper.api.JMapperAPI;
import com.googlecode.jmapper.api.MappedClass;

/**
 * @author Pratya Yeekhaday
 *
 */
public class GenericJMapperImpl {

	private static final Global GLOBAL = new Global();
	
	public <D,S> D map(S source, final Class<D> destination){
		JMapperAPI jMapperAPI = globalJmapperApi(destination);
		JMapper<D,S> jMapper = getjmapper(destination, claszz(source), jMapperAPI);
		return jMapper.getDestination(source);
	}
	
	private <D,S> JMapper<D,S> getjmapper(Class<D> destination, Class<S> claszz, JMapperAPI jMapperAPI) {
		return new JMapper<>(destination, claszz, jMapperAPI);
	}

	public <D,S> List<D> map(List<S> source, final Class<D> destination){
		List<D> target = new ArrayList<>();
		source.forEach(o -> target.add(map(o, destination)));
		return target; 
	}
	
	public <D,S> Page<D> map(Page<S> source, Class<D> destination){
		final List<D> content = new ArrayList<>();
		final int page = source.getNumber(),
			      size = source.getSize();
		Sort sort = source.getSort();
		PageRequest pageRequest = new PageRequest(page,size,sort);
		for (S s : source.getContent()) {
			content.add(map(s, destination));
		}
		return new PageImpl<D>(content, pageRequest, source.getTotalElements());	
	}
	
	@SuppressWarnings("unchecked")
	private <S> Class<S> claszz(S source) {
		return (Class<S>) source.getClass();
	}
	
	private <D> JMapperAPI globalJmapperApi(final Class<D> destination) {
		return new JMapperAPI().add(new MappedClass(destination).add(GLOBAL));
	}
	
}
