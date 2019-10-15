package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.tirmizee.core.hibernate.validator.FieldMatch;

import lombok.Data;

@Data
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

}
