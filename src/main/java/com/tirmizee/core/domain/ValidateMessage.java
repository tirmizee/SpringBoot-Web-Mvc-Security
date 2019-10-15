package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class ValidateMessage implements Persistable<Integer> {

	private static final long serialVersionUID = -1529342827747405886L;

	private transient boolean persisted;
	
	private Integer msgId;
	private String msgCode;
	private String msgDesc;
	private java.sql.Timestamp updateDate;
	private java.sql.Timestamp createDate;
	
	@Override
	public Integer getId() {
		return msgId;
	}

	@Override
	public boolean isNew() {
		return !persisted;
	}
	
	public ValidateMessage withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}

}
