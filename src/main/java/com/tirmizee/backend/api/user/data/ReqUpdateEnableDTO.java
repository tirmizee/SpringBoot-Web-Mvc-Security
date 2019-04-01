package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

public class ReqUpdateEnableDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private boolean enabled;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
