package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

public class District implements Persistable<Integer> {

	private static final long serialVersionUID = 1L;

	private String districtNameEn;
	private String districtNameTh;
	private String districtCode;
	private Integer fkProvinceId;
	private Integer districtId;
	
	@Override
	public Integer getId() {
		return districtId;
	}

	@Override
	public boolean isNew() {
		return districtId == null;
	}

	public String getDistrictNameEn() {
		return districtNameEn;
	}

	public void setDistrictNameEn(String districtNameEn) {
		this.districtNameEn = districtNameEn;
	}

	public String getDistrictNameTh() {
		return districtNameTh;
	}

	public void setDistrictNameTh(String districtNameTh) {
		this.districtNameTh = districtNameTh;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public Integer getFkProvinceId() {
		return fkProvinceId;
	}

	public void setFkProvinceId(Integer fkProvinceId) {
		this.fkProvinceId = fkProvinceId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	
}
