package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

public class PostCode implements Persistable<Integer>{

	private static final long serialVersionUID = 1L;
	
	private Integer postCodeId;
	private String subDistrictCode;
	private String zipcode;
	
	public Integer getPostCodeId() {
		return postCodeId;
	}

	public void setPostCodeId(Integer postCodeId) {
		this.postCodeId = postCodeId;
	}

	public String getSubDistrictCode() {
		return subDistrictCode;
	}

	public void setSubDistrictCode(String subDistrictCode) {
		this.subDistrictCode = subDistrictCode;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public Integer getId() {
		return postCodeId;
	}

	@Override
	public boolean isNew() {
		return postCodeId == null;
	}

}
