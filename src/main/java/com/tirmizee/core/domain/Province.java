package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

public class Province implements Persistable<Integer> {

	private static final long serialVersionUID = 1L;
	
	private Integer provinceId;
	private String provincecCode;
	private String provinceNameTh;
	private String provinceNameEn;

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

	public String getProvincecCode() {
		return provincecCode;
	}

	public void setProvincecCode(String provincecCode) {
		this.provincecCode = provincecCode;
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

}
