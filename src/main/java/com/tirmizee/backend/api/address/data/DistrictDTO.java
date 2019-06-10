package com.tirmizee.backend.api.address.data;

import java.io.Serializable;

public class DistrictDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String districtNameEn;
	private String districtNameTh;
	private String districtCode;
	private Integer fkProvinceId;
	private Integer districtId;
	
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
