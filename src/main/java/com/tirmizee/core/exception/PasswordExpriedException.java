package com.tirmizee.core.exception;

import org.springframework.security.authentication.AccountStatusException;

public class PasswordExpriedException extends AccountStatusException {
	
	private static final long serialVersionUID = -4274987226818951183L;

	private String username;
	
	public PasswordExpriedException(String msg) {
		super(msg);
	}

	public PasswordExpriedException(String username, String msg) {
		super(msg);
		this.username = username;
	}
	
	public PasswordExpriedException(String msg, Throwable t) {
		super(msg, t);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
