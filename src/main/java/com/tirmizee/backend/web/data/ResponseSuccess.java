package com.tirmizee.backend.web.data;

import java.io.Serializable;
import java.util.Date;

public class ResponseSuccess implements Serializable {

	private static final long serialVersionUID = 49814922208079178L;

	private String messageCode;
	private String messageName;
	private Date date;

	public ResponseSuccess() {
		date = new Date();
	}
	
	public ResponseSuccess(String messageCode, String messageName) {
		this.messageCode = messageCode;
		this.messageName = messageName;
		this.date = new Date();
	}
	
	public ResponseSuccess(String messageCode, String messageName, Date date) {
		this.messageCode = messageCode;
		this.messageName = messageName;
		this.date = date;
	}

	public String getMessageCode() {
		return messageCode;
	}
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	
	public String getMessageName() {
		return messageName;
	}
	
	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ResponseSuccess messageCode(String messageCode) {
		this.messageCode = messageCode;
		return this;
	}
	
	public ResponseSuccess messageName(String messageName) {
		this.messageName = messageName;
		return this;
	}
	
	public ResponseSuccess date(Date date) {
		this.date = date;
		return this;
	}
	
}