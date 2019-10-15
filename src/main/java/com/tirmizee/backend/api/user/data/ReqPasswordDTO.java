package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.tirmizee.core.hibernate.validator.FieldMatch;

import lombok.Data;

@Data
@FieldMatch(
	field = "password", 
	fieldMatch = "confirmPassword",
	message = "The password fields must match" )
public class ReqPasswordDTO implements Serializable {

	private static final long serialVersionUID = 5103538270501002341L;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String confirmPassword;
	
}
