package com.tirmizee.core.domain;

import java.sql.Timestamp;

import org.springframework.data.domain.Persistable;

public class BankBranch implements Persistable<Long>{

	private static final long serialVersionUID = -1745126589219391011L;

	private Long bankBranchId;
	private String bankBranchCode;
	private String bankBranchName;
	private String bankBranchDesc;
	private String bankCode;
	private Timestamp updateDate;
	private Timestamp createDate;
	
	@Override
	public Long getId() {
		return bankBranchId;
	}

	@Override
	public boolean isNew() {
		return bankBranchId == null;
	}

	public Long getBankBranchId() {
		return bankBranchId;
	}

	public void setBankBranchId(Long bankBranchId) {
		this.bankBranchId = bankBranchId;
	}

	public String getBankBranchCode() {
		return bankBranchCode;
	}

	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}

	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

	public String getBankBranchDesc() {
		return bankBranchDesc;
	}

	public void setBankBranchDesc(String bankBranchDesc) {
		this.bankBranchDesc = bankBranchDesc;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
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

}
