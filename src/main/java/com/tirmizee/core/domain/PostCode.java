package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class PostCode implements Persistable<Integer>{

	private static final long serialVersionUID = 1L;
	
	private Integer postCodeId;
	private String subDistrictCode;
	private String zipcode;
	
	@Override
	public Integer getId() {
		return postCodeId;
	}

	@Override
	public boolean isNew() {
		return postCodeId == null;
	}

}
