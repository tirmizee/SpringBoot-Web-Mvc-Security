package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

public class ReqUpdatePasswordExpiredDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private boolean passwordExpired;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isPasswordExpired() {
		return passwordExpired;
	}
	public void setPasswordExpired(boolean passwordExpired) {
		this.passwordExpired = passwordExpired;
	}
	
}
