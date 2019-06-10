package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.tirmizee.core.hibernate.validator.FieldMatch;

@FieldMatch(
	field = "password", 
	fieldMatch = "confirmPassword",
	message = "The password fields must match" )
public class ReqPasswordResetTokenDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Long uid;
	
	@NotEmpty
	private String token;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String confirmPassword;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
