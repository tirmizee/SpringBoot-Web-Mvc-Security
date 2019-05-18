package com.tirmizee.backend.service.data;

import java.sql.Timestamp;

public class LockUserTime {

	private String username;
	private Timestamp lockTime;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Timestamp getLockTime() {
		return lockTime;
	}
	public void setLockTime(Timestamp lockTime) {
		this.lockTime = lockTime;
	}
	
}
