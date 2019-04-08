package com.tirmizee.backend.api.subdistrict.data;

import java.io.Serializable;

public class SubDistrictDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer subdistrictId;
	private String subdistrictCode;
	private Integer fkDistrictId;
	private String subdistrictNameEn;
	private String subdistrictNameTh;
	private String zipcode;
	
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
	public Integer getFkDistrictId() {
		return fkDistrictId;
	}
	public void setFkDistrictId(Integer fkDistrictId) {
		this.fkDistrictId = fkDistrictId;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
}
