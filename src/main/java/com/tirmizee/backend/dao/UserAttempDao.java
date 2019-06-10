package com.tirmizee.backend.dao;

import com.tirmizee.core.domain.UserAttemp;
import com.tirmizee.core.repository.UserAttempRepository;

public interface UserAttempDao extends UserAttempRepository {

	UserAttemp findByUsername(String username);
	
}
