package com.tirmizee.backend.service;

import com.tirmizee.backend.service.data.LockUser;
import com.tirmizee.backend.service.data.LockUserTime;

public interface UserAttempService {

	LockUser updateLockUser(String username, String accessIp) ;
	
	LockUserTime updateLockUserTime(String username, String accessIp);
	
	void resetLoginAttempt(String username, String accessIp);
	
}
