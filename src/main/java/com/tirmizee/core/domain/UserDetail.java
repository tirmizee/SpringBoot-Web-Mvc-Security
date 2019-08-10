package com.tirmizee.core.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDetail implements Serializable{

	private static final long serialVersionUID = -4363276001483060389L;
	
	private String username;
	private String password;
	private String lastName;
	private String firstName;
	private String branchCode;
	private String profileImage;
	private Integer roleId;
	private String roleCode;
	private String roleName;
	private Date credentialsexpiredDate;
	private Date accountExpiredDate;
	private Timestamp accountLockedDate;
	private boolean credentialsnonexpired;
	private boolean accountnonexpired;
	private boolean accountnonlocked;	
	private boolean enabled;
	private boolean firstLogin;
	private Integer maxSession;
	
}
