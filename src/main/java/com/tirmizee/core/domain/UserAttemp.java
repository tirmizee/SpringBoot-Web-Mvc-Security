package com.tirmizee.core.domain;

import java.sql.Timestamp;

import org.springframework.data.domain.Persistable;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAttemp() {
		return attemp;
	}

	public void setAttemp(Integer attemp) {
		this.attemp = attemp;
	}

	public String getAccessIp() {
		return accessIp;
	}

	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}

	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	public UserAttemp withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}
	
}
