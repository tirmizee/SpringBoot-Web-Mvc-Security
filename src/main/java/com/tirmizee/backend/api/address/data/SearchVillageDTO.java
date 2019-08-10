package com.tirmizee.backend.api.address.data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SearchVillageDTO implements Serializable {

	private static final long serialVersionUID = -9063286266840969529L;
	
	@NotNull
	private String subDistrictCode;
	
	private String term;
	
	@NotNull
	private Integer page;
	
	@NotNull
	private Integer size;

}
