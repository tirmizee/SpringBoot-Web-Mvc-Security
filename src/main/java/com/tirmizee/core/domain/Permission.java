package com.tirmizee.core.domain;

import org.springframework.data.domain.Persistable;

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
	public Integer getPerId() {
		return perId;
	}
	public void setPerId(Integer perId) {
		this.perId = perId;
	}
	public String getPerName() {
		return perName;
	}
	public void setPerName(String perName) {
		this.perName = perName;
	}
	public String getPerCode() {
		return perCode;
	}
	public void setPerCode(String perCode) {
		this.perCode = perCode;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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
	
}
