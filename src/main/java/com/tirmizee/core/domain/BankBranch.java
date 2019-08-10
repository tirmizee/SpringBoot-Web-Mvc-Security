package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class BankBranch implements Persistable<Integer>{

	private static final long serialVersionUID = -1745126589219391011L;

	private Integer branchId;
	private String bankCode;
	private String branchName;
	private String branchCode;
	private String isBankBranch;
	private java.sql.Timestamp updatedDate;
	private java.sql.Timestamp createdDate;
	private String branchNameEn;
	
	@Override
	public Integer getId() {
		return branchId;
	}

	@Override
	public boolean isNew() {
		return branchId == null;
	}

}
