package com.tirmizee.core.domain;

import java.sql.Timestamp;

import org.springframework.data.domain.Persistable;

public class ForgotPassword implements Persistable<Integer> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String username;
	private String token;
	private Timestamp updateDate;
	private Timestamp createDate;
	private String email;
	private String accessIp;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public boolean isNew() {
		return id == null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccessIp() {
		return accessIp;
	}

	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
