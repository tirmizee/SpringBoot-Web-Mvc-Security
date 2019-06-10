package com.tirmizee.core.exception;

import org.springframework.security.authentication.AccountStatusException;

public class UserAccountDisabledException extends AccountStatusException {

	private static final long serialVersionUID = 7404318284744897150L;
	
	private String username;
	
	public UserAccountDisabledException(String username, String msg) {
		super(msg);
		this.username = username;
	}

	public UserAccountDisabledException(String msg, Throwable t) {
		super(msg, t);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
