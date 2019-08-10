package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class District implements Persistable<Integer> {

	private static final long serialVersionUID = 1L;

	private String districtNameEn;
	private String districtNameTh;
	private String districtCode;
	private Integer districtId;
	private String provincecCode;
	
	@Override
	public Integer getId() {
		return districtId;
	}

	@Override
	public boolean isNew() {
		return districtId == null;
	}

}
