package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

public class Member implements Persistable<String>{

	private static final long serialVersionUID = 5399011411340065307L;

	private transient boolean persisted;
	private String username; 
	private String password; 
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getId() {
		return username;
	}

	@Override
	public boolean isNew() {
		return !persisted;
	}

	public Member withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}
	
}
