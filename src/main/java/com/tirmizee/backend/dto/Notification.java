package com.tirmizee.backend.dto;

import java.util.Date;

public class Notification {

	private String text; 
	private Date time; 
	private static Integer jobId = 0;
	
    public Notification(String text, Date time) {
		super();
		this.text = text;
		this.time = time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public static Integer getNextJobId() {
        return ++jobId;
  } 

}
