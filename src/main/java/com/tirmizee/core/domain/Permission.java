package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class Permission implements Persistable<Integer>{

	private static final long serialVersionUID = 1000135212787831884L;
	
	private Integer perId;
	private String perName;
	private String perCode;
	private String updateBy;
	private String createBy;
	private java.sql.Date updateDate;
	private java.sql.Date createDate;
	
	@Override
	public Integer getId() {
		return perId;
	}
	
	@Override
	public boolean isNew() {
		return perId == null;
	}
	
}
