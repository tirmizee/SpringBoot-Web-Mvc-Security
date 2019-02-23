package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.tirmizee.core.annotaion.FieldMatch;

@FieldMatch(
		firstField = "password", 
		secondField = "confirmPassword",
		message = "The password fields must match")
public class RequestPasswordDTO implements Serializable {

	private static final long serialVersionUID = 5103538270501002341L;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String confirmPassword;
	
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
