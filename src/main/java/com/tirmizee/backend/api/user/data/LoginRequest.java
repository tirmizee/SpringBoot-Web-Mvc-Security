package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest implements Serializable {
	
	private static final long serialVersionUID = 5926468583005150707L;
	
	private String username;
	private String password;
	
}
