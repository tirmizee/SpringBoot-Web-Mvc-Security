package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
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

	public Village withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}
	
}
