package com.tirmizee.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import com.tirmizee.backend.api.session.data.UserLoggedDTO;
import com.tirmizee.core.component.PageMapper;
import com.tirmizee.core.security.UserProfile;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired 
	private PageMapper mapper;
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Override
	public int countUserLogged() {
		return sessionRegistry.getAllPrincipals().size();
	}

	@Override
	public List<UserLoggedDTO> allUserLogged() {
		
		List<UserLoggedDTO> allUserLogged = new ArrayList<>();
		List<Object> principals = sessionRegistry.getAllPrincipals();
		
		for (Object principal : principals) {
			if (principal instanceof UserProfile) {
				UserProfile profile = (UserProfile) principal;
				List<SessionInformation> sessionInformations = sessionRegistry.getAllSessions(principal, false);
				UserLoggedDTO userLogged = mapper.map(profile, UserLoggedDTO.class);
				userLogged.setSessionId(sessionInformations.get(0).getSessionId());
				allUserLogged.add(userLogged);
			}
		}
		return allUserLogged;
	}

	@Override
	public Object removeSession(String sessionId) {
		sessionRegistry.removeSessionInformation(sessionId);
		return null;
	}

}
