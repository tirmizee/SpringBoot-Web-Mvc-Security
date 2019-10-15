package com.tirmizee.backend.api.geography.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class CountProvincesDTO implements Serializable {

	private static final long serialVersionUID = 4637252972734141513L;
	private Integer geoId;
	private String geoNameEn;
	private String geoNameTh;
	private Integer countProvince;
	
}
