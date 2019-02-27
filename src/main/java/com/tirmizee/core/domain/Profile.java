package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

public class Profile implements Persistable<Long>{

	private static final long serialVersionUID = 8346198873383675994L;

	private transient boolean persisted;
	
	private Long profileId;
	private String lastName;
	private String firstName;
	private String email;
	private java.sql.Date updateDate;
	private java.sql.Date createDate;
	
	public boolean isPersisted() {
		return persisted;
	}

	public void setPersisted(boolean persisted) {
		this.persisted = persisted;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public java.sql.Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.sql.Date updateDate) {
		this.updateDate = updateDate;
	}

	public java.sql.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.sql.Date createDate) {
		this.createDate = createDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Long getId() {
		return profileId;
	}

	@Override
	public boolean isNew() {
		return !persisted;
	}

	public Profile withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}
	
}
