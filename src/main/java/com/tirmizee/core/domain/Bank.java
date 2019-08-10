package com.tirmizee.core.domain;

import java.sql.Timestamp;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class Bank implements Persistable<Long> {
	
	private static final long serialVersionUID = -3042051200660206901L;

	private Long bankId;
	private String bankCode;
	private String bankName;
	private String bankDesc;
	private Timestamp updateDate;
	private Timestamp createDate;
	
	@Override
	public Long getId() {
		return bankId;
	}

	@Override
	public boolean isNew() {
		return bankId == null;
	}

}
