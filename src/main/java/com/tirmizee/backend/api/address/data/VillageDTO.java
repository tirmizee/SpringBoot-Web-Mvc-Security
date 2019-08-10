package com.tirmizee.backend.api.address.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class VillageDTO implements Serializable {

	private static final long serialVersionUID = -6158985607680431312L;

	private String subDistrictCode;
	private String villageName;
	private String villageNo;
	private String villageCode;
	
}
