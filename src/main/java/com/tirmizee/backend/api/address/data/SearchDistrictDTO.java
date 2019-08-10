package com.tirmizee.backend.api.address.data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SearchDistrictDTO implements Serializable {

	private static final long serialVersionUID = 7095397900208385329L;
	
	@NotNull
	private String provinceCode;
	
	private String term;
	
	@NotNull
	private Integer page;
	
	@NotNull
	private Integer size;
	
}
