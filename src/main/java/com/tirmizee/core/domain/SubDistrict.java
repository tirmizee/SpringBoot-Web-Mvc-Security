package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

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

	public Integer getSubdistrictId() {
		return subdistrictId;
	}

	public void setSubdistrictId(Integer subdistrictId) {
		this.subdistrictId = subdistrictId;
	}

	public String getSubdistrictCode() {
		return subdistrictCode;
	}

	public void setSubdistrictCode(String subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getSubdistrictNameEn() {
		return subdistrictNameEn;
	}

	public void setSubdistrictNameEn(String subdistrictNameEn) {
		this.subdistrictNameEn = subdistrictNameEn;
	}

	public String getSubdistrictNameTh() {
		return subdistrictNameTh;
	}

	public void setSubdistrictNameTh(String subdistrictNameTh) {
		this.subdistrictNameTh = subdistrictNameTh;
	}

}
