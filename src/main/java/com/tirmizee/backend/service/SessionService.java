package com.tirmizee.backend.service;

import java.util.List;

import org.springframework.security.core.session.SessionInformation;

import com.tirmizee.backend.api.session.data.UserLoggedDTO;

public interface SessionService {
	
	int countSessions();
	
	int countSessionsActive();
	
	int countSessionsExpired();
	
	List<UserLoggedDTO> allUserLogged();
	
	void removeSession(String username);
	
	List<SessionInformation> getAllSessionsByUsername(String username);
	
}
