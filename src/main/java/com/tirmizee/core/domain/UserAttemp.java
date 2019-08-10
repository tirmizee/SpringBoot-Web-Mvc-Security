package com.tirmizee.core.domain;

import java.sql.Timestamp;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class UserAttemp implements Persistable<String> {

	private static final long serialVersionUID = -326668936067814297L;

	private transient boolean persisted;
	
	private String username;
	private Integer attemp;
	private String accessIp;
	private Timestamp lastModified;
	
	@Override
	public String getId() {
		return username;
	}

	@Override
	public boolean isNew() {
		return !persisted;
	}

	public UserAttemp withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}
	
}
