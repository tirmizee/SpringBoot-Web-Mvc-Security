package com.tirmizee.core.exception;

import java.sql.Timestamp;

import org.springframework.security.authentication.AccountStatusException;

public class LockTimePasswordInvalidException extends AccountStatusException {

	private static final long serialVersionUID = 6823612577538656928L;
	
	private String username;
	private Timestamp lockedTime;

	public LockTimePasswordInvalidException(String msg, String username) {
		super(msg);
		this.username = username;
	}
	
	public LockTimePasswordInvalidException(String msg, String username, Timestamp lockedTime) {
		super(msg);
		this.username = username;
		this.lockedTime = lockedTime;
	}

	public LockTimePasswordInvalidException(String msg, Throwable t) {
		super(msg, t);
	}

	public String getUsername() {
		return username;
	}

	public Timestamp getLockedTime() {
		return lockedTime;
	}

}
