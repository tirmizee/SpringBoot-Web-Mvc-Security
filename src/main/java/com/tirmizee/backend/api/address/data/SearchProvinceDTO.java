package com.tirmizee.backend.api.address.data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SearchProvinceDTO implements Serializable {

	private static final long serialVersionUID = -5015659612972362369L;
	
	private String term;
	
	@NotNull
	private Integer page;
	
	@NotNull
	private Integer size;
	
}
