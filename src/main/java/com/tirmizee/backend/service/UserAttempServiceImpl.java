package com.tirmizee.backend.service;
import static com.tirmizee.core.constant.Constant.AppSetting.MAX_LOGIN_FAIL;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tirmizee.backend.dao.UserAttempDao;
import com.tirmizee.backend.dao.UserDao;
import com.tirmizee.backend.service.data.LockUser;
import com.tirmizee.backend.service.data.LockUserTime;
import com.tirmizee.core.domain.User;
import com.tirmizee.core.domain.UserAttemp;
import com.tirmizee.core.utilities.DateUtils;

@Service
public class UserAttempServiceImpl implements UserAttempService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserAttempDao userAttempDao;
	
	@Autowired
	private AppSettingService appSettingService;
	
	@Override
	public LockUser updateLockUser(String username, String accessIp) {
		
		boolean isLocked = false;
		UserAttemp userAttempt = updateOrInsertLoginAttempt(username, accessIp);
		
		int maxLoginFail = Integer.parseInt(appSettingService.getValue(MAX_LOGIN_FAIL));
		if (userAttempt.getAttemp() >= maxLoginFail) {
			isLocked = true;
			User user = userDao.findByUsername(username);
			user.setAccountnonlocked(false);
			userDao.save(user);
		}
		
		LockUser lockUser = new LockUser();
		lockUser.setUsername(username);
		lockUser.setLocked(isLocked);
		
		return lockUser;
	}
	
	@Override
	public LockUserTime updateLockUserTime(String username, String accessIp) {
		
		UserAttemp userAttempt = updateOrInsertLoginAttempt(username, accessIp);
		User user = userDao.findByUsername(username);
		
		Timestamp lockedTime = user.getAccountLockedDate();
		int maxLoginFail = Integer.parseInt(appSettingService.getValue(MAX_LOGIN_FAIL));
		if (userAttempt.getAttemp() >= maxLoginFail && DateUtils.nowAfter(lockedTime)) {
			
			lockedTime = DateUtils.nowTimestampPlusMinutes(15);
			user.setAccountLockedDate(lockedTime);
			userDao.save(user);
		}
		
		LockUserTime softLockUser = new LockUserTime();
		softLockUser.setUsername(username);
		softLockUser.setLockTime(lockedTime);
		return softLockUser;
	}
	
	@Override
	public void resetLoginAttempt(String username, String accessIp) {
		UserAttemp userAttempt = userAttempDao.findByUsername(username);
		if (userAttempt == null) {
			userAttempt = new UserAttemp();
			userAttempt.setUsername(username);
		}
		userAttempt.setAttemp(0);
		userAttempt.setAccessIp(accessIp);
		userAttempDao.save(userAttempt);
	}
	
	private UserAttemp updateOrInsertLoginAttempt(String username, String accessIp) {
		
		UserAttemp userAttempt = userAttempDao.findByUsername(username);
		
		if (userAttempt == null) {
			userAttempt = new UserAttemp();
			userAttempt.setUsername(username);
			userAttempt.setAttemp(0);
		}
		
		final int attemp = plusOne(userAttempt.getAttemp());
		userAttempt.setAccessIp(accessIp);
		userAttempt.setAttemp(attemp);
		userAttempt.setLastModified(DateUtils.nowTimestamp());
		return userAttempDao.save(userAttempt);
	}

	private int plusOne(int attempts){
		return attempts + 1;
	}
	
}
