package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
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

	public RoleMapPermission withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}
	
}
