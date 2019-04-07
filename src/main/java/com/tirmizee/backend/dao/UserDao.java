package com.tirmizee.backend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tirmizee.backend.api.user.data.UserDetailCriteriaDTO;
import com.tirmizee.backend.api.user.data.UserDetailPageDTO;
import com.tirmizee.backend.api.user.data.UserDetailUpdateDTO;
import com.tirmizee.core.domain.User;
import com.tirmizee.core.domain.UserDetail;
import com.tirmizee.core.repository.UserRepository;

public interface UserDao extends UserRepository {

	User findByUsername(String username);
	
	User findByEmail(String email);

	UserDetail findDetailByUsername(String username);
	
	UserDetailUpdateDTO findDetailByUserId(Long userId);
	
	Page<UserDetailPageDTO> findPageByCriteria(Pageable pageable, UserDetailCriteriaDTO search);
	
}
