package com.tirmizee.backend.service;

import java.util.List;

import org.springframework.security.core.session.SessionInformation;

import com.tirmizee.backend.api.session.data.UserLoggedDTO;
import com.tirmizee.backend.api.session.data.UserLoggedDetailDTO;

public interface SessionService {
	
	int countSessions();
	
	int countSessionsActive();
	
	int countSessionsExpired();
	
	void removeSession(String username, String sessionId);
	
	List<UserLoggedDTO> allUserLogged();
	
	List<SessionInformation> findAllSessionsByUsername(String username, boolean includeExpiredSessions);
	
	UserLoggedDetailDTO allUserLoggedDetail();
	
}
