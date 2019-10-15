package com.tirmizee.backend.api.session.data;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserLoggedDetailDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<UserLoggedDTO> usersLogged;
	private int countSessionActive;
	private int countSessionExpired;
	
}
