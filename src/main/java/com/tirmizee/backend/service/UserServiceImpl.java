package com.tirmizee.backend.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tirmizee.backend.api.user.data.ReqPasswordDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordExpriedDTO;
import com.tirmizee.backend.dao.UserDao;
import com.tirmizee.core.constant.MessageCode;
import com.tirmizee.core.domain.User;
import com.tirmizee.core.exception.BusinessException;
import com.tirmizee.core.utilities.DateUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserDao userDao;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void changePasswordFirstLogin(String username, ReqPasswordDTO passwordDTO) {
		
		User user = userDao.findByUsername(username);
		user.setPassword(passwordEncoder.encode(passwordDTO.getConfirmPassword()));
		user.setUpdateDate(DateUtils.now());
		user.setFirstLogin(false);
		userDao.save(user);
		
	}
	
	@Override
	public boolean isPasswordExpried(Date expriedDate) {
		return expriedDate == null ? false : DateUtils.now().after(expriedDate);
	}

	@Override
	public void updatePasswordExpired(String username) {
		User user = userDao.findByUsername(username);
		user.setCredentialsnonexpired(false);
		user.setUpdateDate(DateUtils.now());
		userDao.save(user);
	}
	
	@Override
	public void changePasswordExpired(String username, ReqPasswordExpriedDTO passwordExpriedDTO) {
		
		User user = userDao.findByUsername(username);
		
		if (!passwordEncoder.matches(passwordExpriedDTO.getOldPassword(), user.getPassword())) {
			throw new BusinessException(MessageCode.MSG001);
		}
		
		user.setPassword(passwordEncoder.encode(passwordExpriedDTO.getNewPasswordConfirm()));
		user.setCredentialsexpiredDate(DateUtils.plusDays(90));
		user.setCredentialsnonexpired(true);
		user.setUpdateDate(DateUtils.now());
		userDao.save(user);
	} 
	
}
