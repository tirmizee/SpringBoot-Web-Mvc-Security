package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDetailUpdateDTO implements Serializable {

	private static final long serialVersionUID = -7358757628634792977L;

	private Long userId;
	private String username;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private java.sql.Date credentialsexpiredDate;
	
	private boolean credentialsnonexpired;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private java.sql.Date accountExpiredDate;
	
	private Integer maxSession;
	private boolean accountnonexpired;
	private boolean accountnonlocked;	
	private boolean enabled;
	private String citizenId;
	private boolean firstLogin;
	private String lastName;
	private String firstName;
	private String email;
	private String tel;
	private Integer roleId;
	private String roleName;
	private String roleCode;
	private Integer provinceId;
	private String provinceCode;
	private Integer districtId;
	private String districtCode;
	private Integer subdistrictId;
	private String provinceNameTh;
	private String districtNameTh;
	private String subdistrictNameTh;
	private String subDistrictCode;
	private String zipcode;
	
}
