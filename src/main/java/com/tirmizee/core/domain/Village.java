package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

public class Village implements Persistable<String> {

	private static final long serialVersionUID = 1L;

	private transient boolean persisted;
	
	private String subDistrictCode;
	private String villageName;
	private String villageNo;
	private String villageCode;
	
	@Override
	public String getId() {
		return villageCode;
	}

	@Override
	public boolean isNew() {
		return !persisted;
	}

	public boolean isPersisted() {
		return persisted;
	}

	public void setPersisted(boolean persisted) {
		this.persisted = persisted;
	}

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
	
	public Village withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}
}
