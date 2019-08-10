package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class AppSetting implements Persistable<String> {

	private static final long serialVersionUID = 520853585315329157L;
	private transient boolean persisted;
	
	private String key;
	private String value;
	
	@Override
	public String getId() {
		return key;
	}

	@Override
	public boolean isNew() {
		return !persisted;
	}

	public AppSetting withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}

}
