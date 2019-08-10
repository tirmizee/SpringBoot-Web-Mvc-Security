package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
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

}
