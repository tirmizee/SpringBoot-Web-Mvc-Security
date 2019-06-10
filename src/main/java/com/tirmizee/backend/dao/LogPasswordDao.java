package com.tirmizee.backend.dao;

import java.util.List;

import com.tirmizee.core.domain.LogPassword;
import com.tirmizee.core.repository.LogPasswordRepository;

public interface LogPasswordDao extends LogPasswordRepository {
	
	List<LogPassword> findByUsername(String username);
	
	List<LogPassword> findDescByUsername(String username, int limit);
	
}
