package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

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

	public Integer getGeoId() {
		return geoId;
	}

	public void setGeoId(Integer geoId) {
		this.geoId = geoId;
	}

	public String getGeoNameEn() {
		return geoNameEn;
	}

	public void setGeoNameEn(String geoNameEn) {
		this.geoNameEn = geoNameEn;
	}

	public String getGeoNameTh() {
		return geoNameTh;
	}

	public void setGeoNameTh(String geoNameTh) {
		this.geoNameTh = geoNameTh;
	}
	
	public Geography withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}
}
