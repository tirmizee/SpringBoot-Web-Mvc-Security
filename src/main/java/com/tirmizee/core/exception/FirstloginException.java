package com.tirmizee.core.exception;

import org.springframework.security.core.AuthenticationException;

public class FirstloginException extends AuthenticationException {

	private static final long serialVersionUID = -6537804745024332721L;

	private String username;
	
	public FirstloginException(String msg) {
		super(msg);
	}

	public FirstloginException(String username ,String msg) {
		super(msg);
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
