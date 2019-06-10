package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

public class ReqUpdateStatusDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private boolean status;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
