package com.tirmizee.backend.api.address.data;

import java.io.Serializable;

public class VillageDTO implements Serializable {

	private static final long serialVersionUID = -6158985607680431312L;

	private String subDistrictCode;
	private String villageName;
	private String villageNo;
	private String villageCode;
	
	public String getSubDistrictCode() {
		return subDistrictCode;
	}
	public void setSubDistrictCode(String subDistrictCode) {
		this.subDistrictCode = subDistrictCode;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getVillageNo() {
		return villageNo;
	}
	public void setVillageNo(String villageNo) {
		this.villageNo = villageNo;
	}
	public String getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}
	
}
