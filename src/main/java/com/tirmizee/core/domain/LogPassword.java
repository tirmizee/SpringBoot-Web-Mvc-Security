package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

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

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public java.sql.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.sql.Date createDate) {
		this.createDate = createDate;
	}

}
