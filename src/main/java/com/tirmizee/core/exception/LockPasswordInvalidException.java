package com.tirmizee.core.exception;

import org.springframework.security.core.AuthenticationException;

public class LockPasswordInvalidException extends AuthenticationException {

	private static final long serialVersionUID = 4901051389431476698L;

	private String username;
	private boolean isLocked;
	
	public LockPasswordInvalidException(String msg) {
		super(msg);
	}
	
	public LockPasswordInvalidException(String msg, String username, boolean isLocked) {
		super(msg);
		this.username = username;
		this.isLocked = isLocked;
	}

	public LockPasswordInvalidException(String msg, Throwable t) {
		super(msg, t);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

}
