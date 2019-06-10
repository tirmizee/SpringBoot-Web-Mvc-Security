package com.tirmizee.backend.dao;

import com.tirmizee.core.domain.Profile;
import com.tirmizee.core.repository.ProfileRepository;

public interface ProfileDao extends ProfileRepository {
	
	Profile findByUserId(Long uid);
	
}
