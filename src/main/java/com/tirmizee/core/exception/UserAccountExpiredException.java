package com.tirmizee.core.exception;

import org.springframework.security.authentication.AccountStatusException;


/**
 * @author Pratya Yeekhaday
 *
 */
public class UserAccountExpiredException extends AccountStatusException {

	private static final long serialVersionUID = -7166583663144503591L;

	private String username;
	
	public UserAccountExpiredException(String username, String msg) {
		super(msg);
		this.username = username;
	}
	
	public UserAccountExpiredException(String msg, Throwable t) {
		super(msg, t);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
