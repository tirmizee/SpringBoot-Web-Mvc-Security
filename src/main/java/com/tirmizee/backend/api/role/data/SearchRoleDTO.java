package com.tirmizee.backend.api.role.data;

import java.io.Serializable;

public class SearchRoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String roleName;
	private String roleCode;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
}
