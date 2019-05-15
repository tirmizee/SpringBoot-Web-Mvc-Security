package com.tirmizee.backend.api.address.data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class SearchSubDistrictDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String districtCode;
	
	private String term;
	
	@NotNull
	private Integer page;
	
	@NotNull
	private Integer size;

	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
}
