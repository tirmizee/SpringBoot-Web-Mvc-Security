package com.tirmizee.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tirmizee.backend.dao.LogPasswordDao;
import com.tirmizee.core.domain.LogPassword;

@Service
public class LogPasswordServiceImpl implements LogPasswordService {

	@Autowired
	private LogPasswordDao logPasswordDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public boolean isPasswordExists(String username, String password) {
		List<LogPassword> logPasswords = logPasswordDao.findByUsername(username);
		for (LogPassword logPassword : logPasswords) {
			if (passwordEncoder.matches(password, logPassword.getPassword())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isPasswordExists(String username, String password, int limit) {
		List<LogPassword> logPasswords = logPasswordDao.findDescByUsername(username, limit);
		for (LogPassword logPassword : logPasswords) {
			if (passwordEncoder.matches(password, logPassword.getPassword())) {
				return true;
			}
		}
		return false;
	}

}
