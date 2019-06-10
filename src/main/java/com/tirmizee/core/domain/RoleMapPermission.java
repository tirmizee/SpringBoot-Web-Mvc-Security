package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

public class RoleMapPermission implements Persistable<Object[]>{

	private static final long serialVersionUID = 1L;

	private transient boolean persisted;
	
	private Integer roleId;
	private Integer perId;
	private java.sql.Date createDate;
	
	@Override
	public Object[] getId() {
		return new Object[] {roleId, perId};
	}

	@Override
	public boolean isNew() {
		return !persisted;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPerId() {
		return perId;
	}

	public void setPerId(Integer perId) {
		this.perId = perId;
	}

	public java.sql.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.sql.Date createDate) {
		this.createDate = createDate;
	}
	public RoleMapPermission withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}
	
}
