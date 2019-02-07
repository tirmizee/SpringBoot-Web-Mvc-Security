package com.tirmizee.backend.dao;

import com.tirmizee.core.domain.User;
import com.tirmizee.core.repository.UserRepository;

public interface UserDao extends UserRepository {

	User findByUsername(String username);
	
}
