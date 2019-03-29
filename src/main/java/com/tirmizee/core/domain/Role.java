package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

public class Role implements Persistable<Integer> {

	private static final long serialVersionUID = 1522087496128759375L;

	private Integer roleId;
	private String roleName;
	private String roleCode;
	private String roleDesc;
	private String updateBy;
	private java.sql.Date updateDate;
	private java.sql.Date CreateDate;
	
	@Override
	public Integer getId() {
		return roleId;
	}

	@Override
	public boolean isNew() {
		return roleId == null;
	}

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

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public java.sql.Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.sql.Date updateDate) {
		this.updateDate = updateDate;
	}

	public java.sql.Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(java.sql.Date createDate) {
		CreateDate = createDate;
	}

}
