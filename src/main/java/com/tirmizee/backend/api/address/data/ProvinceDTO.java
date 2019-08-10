package com.tirmizee.backend.api.address.data;

import java.io.Serializable;

import lombok.Data;
@Data
public class ProvinceDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer provinceId;
	private String provinceCode;
	private String provinceNameTh;
	private String provinceNameEn;

}
