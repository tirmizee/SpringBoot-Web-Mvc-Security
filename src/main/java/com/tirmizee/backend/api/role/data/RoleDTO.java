package com.tirmizee.backend.api.role.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tirmizee.core.datatable.SortColumn;
import com.tirmizee.core.repository.RoleRepository;

public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer roleId;
	
	@SortColumn(RoleRepository.COL_ROLE_NAME)
	private String roleName;
	
	@SortColumn(RoleRepository.COL_ROLE_CODE)
	private String roleCode;
	
	@SortColumn(RoleRepository.COL_ROLE_DESC)
	private String roleDesc;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@SortColumn(RoleRepository.COL_CREATE_DATE)
	private java.sql.Date CreateDate;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
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
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public java.sql.Date getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(java.sql.Date createDate) {
		CreateDate = createDate;
	}
	
}
