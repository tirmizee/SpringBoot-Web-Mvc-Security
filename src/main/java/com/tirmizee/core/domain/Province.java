package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

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

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceNameTh() {
		return provinceNameTh;
	}

	public void setProvinceNameTh(String provinceNameTh) {
		this.provinceNameTh = provinceNameTh;
	}

	public String getProvinceNameEn() {
		return provinceNameEn;
	}

	public void setProvinceNameEn(String provinceNameEn) {
		this.provinceNameEn = provinceNameEn;
	}

	public Integer getGeoId() {
		return geoId;
	}

	public void setGeoId(Integer geoId) {
		this.geoId = geoId;
	}
	
}
