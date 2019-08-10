package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class LogPassword implements Persistable<Integer>{

	private static final long serialVersionUID = -707449354409672249L;

	private Integer logId;
	private String password;
	private String username;
	private java.sql.Date createDate;
	
	@Override
	public Integer getId() {
		return logId;
	}

	@Override
	public boolean isNew() {
		return logId == null;
	}

}
