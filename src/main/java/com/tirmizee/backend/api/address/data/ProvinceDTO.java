package com.tirmizee.backend.api.address.data;

import java.io.Serializable;

public class ProvinceDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer provinceId;
	private String provinceCode;
	private String provinceNameTh;
	private String provinceNameEn;
	
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

}
