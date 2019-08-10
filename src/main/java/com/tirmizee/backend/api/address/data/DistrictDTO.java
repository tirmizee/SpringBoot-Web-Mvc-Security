package com.tirmizee.backend.api.address.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class DistrictDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String districtNameEn;
	private String districtNameTh;
	private String districtCode;
	private Integer fkProvinceId;
	private Integer districtId;
	
}
