package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class SubDistrict implements Persistable<Integer> {

	private static final long serialVersionUID = 1L;
	
	private Integer subdistrictId;
	private String subdistrictCode;
	private String subdistrictNameEn;
	private String subdistrictNameTh;
	private String districtCode;
	
	@Override
	public Integer getId() {
		return subdistrictId;
	}

	@Override
	public boolean isNew() {
		return subdistrictId == null;
	}

}
