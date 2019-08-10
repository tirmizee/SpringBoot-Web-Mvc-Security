package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class Province implements Persistable<Integer> {

	private static final long serialVersionUID = 1L;
	
	private Integer provinceId;
	private String provinceCode;
	private String provinceNameTh;
	private String provinceNameEn;
	private Integer geoId;

	@Override
	public Integer getId() {
		return provinceId;
	}

	@Override
	public boolean isNew() {
		return provinceId == null;
	}
	
}
