package com.tirmizee.backend.api.address.data;

public class SubDistrictCountVillageDTO {

	private String subdistrictCode;
	private String subdistrictNameTh;
	private Integer countVillage;
	
	public String getSubdistrictCode() {
		return subdistrictCode;
	}
	public void setSubdistrictCode(String subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}
	public String getSubdistrictNameTh() {
		return subdistrictNameTh;
	}
	public void setSubdistrictNameTh(String subdistrictNameTh) {
		this.subdistrictNameTh = subdistrictNameTh;
	}
	public Integer getCountVillage() {
		return countVillage;
	}
	public void setCountVillage(Integer countVillage) {
		this.countVillage = countVillage;
	}
	
}
