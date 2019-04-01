package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

public class ReqUpdateAccountNonLockedDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private boolean accountnonlocked;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isAccountnonlocked() {
		return accountnonlocked;
	}
	public void setAccountnonlocked(boolean accountnonlocked) {
		this.accountnonlocked = accountnonlocked;
	}
	
}
