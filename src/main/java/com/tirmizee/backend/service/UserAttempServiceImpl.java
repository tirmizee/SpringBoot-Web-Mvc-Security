package com.tirmizee.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tirmizee.backend.dao.UserAttempDao;
import com.tirmizee.backend.dao.UserDao;
import com.tirmizee.core.domain.User;
import com.tirmizee.core.domain.UserAttemp;
import com.tirmizee.core.utilities.DateUtils;


@Service
public class UserAttempServiceImpl implements UserAttempService {

	public final int MAX_LOGIN_FAIL = 3;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserAttempDao userAttempDao;
	
	@Override
	public boolean updateLoginAttemptIsLocked(String username) {
		
		boolean isLocked = false;
		UserAttemp userAttempt = userAttempDao.findByUsername(username);
		
		if (userAttempt == null) {
			userAttempt = new UserAttemp();
			userAttempt.setUsername(username);
			userAttempt.setAttemp(0);
		}
		
		final int attemp = plusOne(userAttempt.getAttemp());
		userAttempt.setAttemp(attemp);
		userAttempt.setLastModified(DateUtils.nowTimestamp());
		userAttempDao.save(userAttempt);
		
		if (attemp >= MAX_LOGIN_FAIL) {
			isLocked = true;
			User user = userDao.findByUsername(username);
			user.setAccountnonlocked(false);
			userDao.save(user);
		}
		return isLocked;
	}

	@Override
	public void resetLoginAttempt(String username) {
		UserAttemp userAttempt = userAttempDao.findByUsername(username);
		if (userAttempt == null) {
			userAttempt = new UserAttemp();
			userAttempt.setUsername(username);
		}
		userAttempt.setAttemp(0);
		userAttempDao.save(userAttempt);
	}
	
	private int plusOne(int attempts){
		return attempts + 1;
	}
	
}
