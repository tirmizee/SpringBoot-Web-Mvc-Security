package com.tirmizee.backend.api.user.data;

import lombok.Data;

@Data
public class UserDetailCriteriaDTO {

	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String tel;
	private Integer roleId;
	
}
