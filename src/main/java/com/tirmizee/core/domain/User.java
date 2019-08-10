package com.tirmizee.core.domain;

import java.sql.Timestamp;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class User implements Persistable<Long>{

	private static final long serialVersionUID = 5399011411340065307L;

	private transient boolean persisted;
	
	private Long userId;
	private Long profileId;
	private String username;
	private String password;
	private java.sql.Date updateDate;
	private java.sql.Date createDate;
	private java.sql.Date credentialsexpiredDate;
	private java.sql.Date accountExpiredDate;
	private Timestamp accountLockedDate;
	private boolean credentialsnonexpired;
	private boolean accountnonexpired;
	private boolean accountnonlocked;	
	private boolean enabled;
	private boolean firstLogin;
	private Integer fkRoleId;
	private Integer maxSession;
	
	public boolean isPersisted() {
		return persisted;
	}

	@Override
	public Long getId() {
		return userId;
	}

	@Override
	public boolean isNew() {
		return !persisted;
	}

	public User withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}
	
}
