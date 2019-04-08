package com.tirmizee.backend.api.district.data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class SearchDistrictDTO implements Serializable {

	private static final long serialVersionUID = 7095397900208385329L;
	
	@NotNull
	private Integer provinceId;
	
	private String term;
	
	@NotNull
	private Integer page;
	
	@NotNull
	private Integer size;
	
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
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
