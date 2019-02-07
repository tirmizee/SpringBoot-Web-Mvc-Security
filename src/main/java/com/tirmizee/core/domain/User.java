package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

public class User implements Persistable<String>{

	private static final long serialVersionUID = 5399011411340065307L;

	private transient boolean persisted;
	
	private String password;
	private String username; 
	private Integer enabled;
	private Integer credentialsnonexpired;
	private Integer accountnonexpired;
	private Integer accountnonlocked;
	
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

	public boolean isPersisted() {
		return persisted;
	}

	public void setPersisted(boolean persisted) {
		this.persisted = persisted;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getCredentialsnonexpired() {
		return credentialsnonexpired;
	}

	public void setCredentialsnonexpired(Integer credentialsnonexpired) {
		this.credentialsnonexpired = credentialsnonexpired;
	}

	public Integer getAccountnonexpired() {
		return accountnonexpired;
	}

	public void setAccountnonexpired(Integer accountnonexpired) {
		this.accountnonexpired = accountnonexpired;
	}

	public Integer getAccountnonlocked() {
		return accountnonlocked;
	}

	public void setAccountnonlocked(Integer accountnonlocked) {
		this.accountnonlocked = accountnonlocked;
	}

	@Override
	public String getId() {
		return username;
	}

	@Override
	public boolean isNew() {
		return !persisted;
	}

	public User withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}
	
}
