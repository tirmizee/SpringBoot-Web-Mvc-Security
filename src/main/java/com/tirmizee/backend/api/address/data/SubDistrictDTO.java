package com.tirmizee.backend.api.address.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class SubDistrictDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer subdistrictId;
	private String subdistrictCode;
	private Integer fkDistrictId;
	private String subdistrictNameEn;
	private String subdistrictNameTh;
	private String zipcode;
	
}
