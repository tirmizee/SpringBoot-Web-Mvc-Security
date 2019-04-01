package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

public class ReqUpdateFirstLoginDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private boolean firstLogin;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

}
