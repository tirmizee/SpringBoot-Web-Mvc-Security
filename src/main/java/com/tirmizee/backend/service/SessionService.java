package com.tirmizee.backend.service;

import java.util.List;

import com.tirmizee.backend.api.session.data.UserLoggedDTO;

public interface SessionService {
	
	int countUserLogged();

	List<UserLoggedDTO> allUserLogged();
	
	Object removeSession(String sessionId);
	
}
