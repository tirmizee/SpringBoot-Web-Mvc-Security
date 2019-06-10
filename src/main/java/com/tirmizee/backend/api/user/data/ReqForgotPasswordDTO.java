package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

public class ReqForgotPasswordDTO implements Serializable {

	private static final long serialVersionUID = -7875052620442575797L;

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
