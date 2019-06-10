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
		return isMatches(logPasswords, password);
	}

	@Override
	public boolean isPasswordExists(String username, String password, int limit) {
		List<LogPassword> logPasswords = logPasswordDao.findDescByUsername(username, limit);
		return isMatches(logPasswords, password);
	}
	
	private boolean isMatches(List<LogPassword> listPassword, String checkPassword) {
		for (LogPassword logPassword : listPassword) {
			if (passwordEncoder.matches(checkPassword, logPassword.getPassword())) {
				return true;
			}
		}
		return false;
	}

}
