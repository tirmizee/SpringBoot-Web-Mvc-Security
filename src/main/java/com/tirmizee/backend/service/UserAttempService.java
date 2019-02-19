package com.tirmizee.backend.service;

public interface UserAttempService {

	boolean updateLoginAttemptIsLocked(String username, String accessIp);
	
	void resetLoginAttempt(String username, String accessIp);
	
}
