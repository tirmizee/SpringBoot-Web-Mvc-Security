package com.tirmizee.backend.service;

public interface UserAttempService {

	boolean updateLoginAttemptIsLocked(String username);
	
	void resetLoginAttempt(String username);
	
}
