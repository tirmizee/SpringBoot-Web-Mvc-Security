package com.tirmizee.backend.api.session.data;

import java.io.Serializable;
import java.util.List;

public class ResponseUserLoggedDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<UserLoggedDTO> usersLogged;
	private int countSessionActive;
	private int countSessionExpired;
	
	public List<UserLoggedDTO> getUsersLogged() {
		return usersLogged;
	}
	public void setUsersLogged(List<UserLoggedDTO> usersLogged) {
		this.usersLogged = usersLogged;
	}
	public int getCountSessionActive() {
		return countSessionActive;
	}
	public void setCountSessionActive(int countSessionActive) {
		this.countSessionActive = countSessionActive;
	}
	public int getCountSessionExpired() {
		return countSessionExpired;
	}
	public void setCountSessionExpired(int countSessionExpired) {
		this.countSessionExpired = countSessionExpired;
	}
	
}
