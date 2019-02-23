package com.tirmizee.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tirmizee.backend.api.user.data.RequestPasswordDTO;
import com.tirmizee.backend.dao.UserDao;
import com.tirmizee.backend.web.data.ResponseSuccess;
import com.tirmizee.core.component.VarargMessageSource;
import com.tirmizee.core.domain.User;
import com.tirmizee.core.utilities.DateUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserDao userDao;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private VarargMessageSource messageSource;

	@Override
	@Transactional
	public ResponseSuccess changePasswordFirstLogin(String username, RequestPasswordDTO passwordDTO) {
		
		User user = userDao.findByUsername(username);
		user.setPassword(passwordEncoder.encode(passwordDTO.getConfirmPassword()));
		user.setUpdateDate(DateUtils.now());
		user.setFirstLogin(false);
		userDao.save(user);
		return new ResponseSuccess("1","ssssssssss");
	}

	
}
