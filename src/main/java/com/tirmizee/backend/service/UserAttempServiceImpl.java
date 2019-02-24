package com.tirmizee.backend.service;
import static com.tirmizee.core.constant.Constant.AppSetting.MAX_LOGIN_FAIL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tirmizee.backend.dao.UserAttempDao;
import com.tirmizee.backend.dao.UserDao;
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
	public boolean updateLoginAttemptIsLocked(String username, String accessIp) {
		
		boolean isLocked = false;
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
		userAttempDao.save(userAttempt);
		
		int maxLoginFail = Integer.parseInt(appSettingService.getValue(MAX_LOGIN_FAIL));
		if (attemp >= maxLoginFail) {
			isLocked = true;
			User user = userDao.findByUsername(username);
			user.setAccountnonlocked(false);
			userDao.save(user);
		}
		return isLocked;
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
	
	private int plusOne(int attempts){
		return attempts + 1;
	}
	
}
