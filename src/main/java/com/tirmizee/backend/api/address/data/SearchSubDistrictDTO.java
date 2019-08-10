package com.tirmizee.backend.api.address.data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SearchSubDistrictDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String districtCode;
	
	private String term;
	
	@NotNull
	private Integer page;
	
	@NotNull
	private Integer size;

}
