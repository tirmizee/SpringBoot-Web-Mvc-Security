package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class Geography implements Persistable<Integer> {

	private static final long serialVersionUID = 1L;

	private transient boolean persisted;
	
	private Integer geoId;
	private String geoNameEn;
	private String geoNameTh;
	
	@Override
	public Integer getId() {
		return geoId;
	}

	@Override
	public boolean isNew() {
		return !persisted;
	}

	public Geography withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}
}
