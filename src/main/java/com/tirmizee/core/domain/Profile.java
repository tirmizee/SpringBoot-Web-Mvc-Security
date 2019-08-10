package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class Profile implements Persistable<Long>{

	private static final long serialVersionUID = 8346198873383675994L;

	private transient boolean persisted;
	
	private Long profileId;
	private String citizenId;
	private String lastName;
	private String firstName;
	private String email;
	private String tel;
	private String profileImage;
	private String branchCode;
	private String subDistrictCode;
	private java.sql.Date updateDate;
	private java.sql.Date createDate;
	private byte[] profileByte;
	
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
