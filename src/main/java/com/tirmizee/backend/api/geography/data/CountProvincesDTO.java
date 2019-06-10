package com.tirmizee.backend.api.geography.data;

import java.io.Serializable;

public class CountProvincesDTO implements Serializable {

	private static final long serialVersionUID = 4637252972734141513L;
	private Integer geoId;
	private String geoNameEn;
	private String geoNameTh;
	private Integer countProvince;
	
	public Integer getGeoId() {
		return geoId;
	}
	public void setGeoId(Integer geoId) {
		this.geoId = geoId;
	}
	public String getGeoNameEn() {
		return geoNameEn;
	}
	public void setGeoNameEn(String geoNameEn) {
		this.geoNameEn = geoNameEn;
	}
	public String getGeoNameTh() {
		return geoNameTh;
	}
	public void setGeoNameTh(String geoNameTh) {
		this.geoNameTh = geoNameTh;
	}
	public Integer getCountProvince() {
		return countProvince;
	}
	public void setCountProvince(Integer countProvince) {
		this.countProvince = countProvince;
	}
	
}
