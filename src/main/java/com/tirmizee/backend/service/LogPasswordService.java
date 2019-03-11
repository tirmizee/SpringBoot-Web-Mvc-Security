package com.tirmizee.backend.service;

public interface LogPasswordService {
	
	boolean isPasswordExists(String username, String password);
	
}
