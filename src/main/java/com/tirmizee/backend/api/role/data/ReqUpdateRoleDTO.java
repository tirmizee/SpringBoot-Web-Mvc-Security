package com.tirmizee.backend.api.role.data;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ReqUpdateRoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Integer roleId;
	
	@NotNull
	private String RoleCode;
	
	private String roleName;
	
	private List<Integer> perIds;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleCode() {
		return RoleCode;
	}
	public void setRoleCode(String roleCode) {
		RoleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<Integer> getPerIds() {
		return perIds;
	}
	public void setPerIds(List<Integer> perIds) {
		this.perIds = perIds;
	}
	
}
