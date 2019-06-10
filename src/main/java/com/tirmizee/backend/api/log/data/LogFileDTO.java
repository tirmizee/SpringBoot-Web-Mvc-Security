package com.tirmizee.backend.api.log.data;

import java.io.Serializable;
import java.util.Date;

public class LogFileDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private Date createDate;
	private String createDateText;
	private String extention;
	private long size;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getExtention() {
		return extention;
	}
	public void setExtention(String extention) {
		this.extention = extention;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getCreateDateText() {
		return createDateText;
	}
	public void setCreateDateText(String createDateText) {
		this.createDateText = createDateText;
	}

}
