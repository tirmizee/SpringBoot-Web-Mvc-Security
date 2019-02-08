package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

public class User implements Persistable<String>{

	private static final long serialVersionUID = 5399011411340065307L;

	private transient boolean persisted;
	
	private String password;
	private String username; 
	private Boolean enabled;
	private Boolean credentialsnonexpired;
	private Boolean accountnonexpired;
	private Boolean accountnonlocked;
	
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getCredentialsnonexpired() {
		return credentialsnonexpired;
	}

	public void setCredentialsnonexpired(Boolean credentialsnonexpired) {
		this.credentialsnonexpired = credentialsnonexpired;
	}

	public Boolean getAccountnonexpired() {
		return accountnonexpired;
	}

	public void setAccountnonexpired(Boolean accountnonexpired) {
		this.accountnonexpired = accountnonexpired;
	}

	public Boolean getAccountnonlocked() {
		return accountnonlocked;
	}

	public void setAccountnonlocked(Boolean accountnonlocked) {
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
