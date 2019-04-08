package com.tirmizee.backend.api.province.data;

import java.io.Serializable;

public class ProvinceDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer provinceId;
	private String provincecCode;
	private String provinceNameTh;
	private String provinceNameEn;
	
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
