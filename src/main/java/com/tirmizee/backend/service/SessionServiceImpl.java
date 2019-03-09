package com.tirmizee.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import com.tirmizee.backend.api.session.data.UserLoggedDTO;
import com.tirmizee.core.component.PageMapper;
import com.tirmizee.core.security.UserProfile;

@Service
public class SessionServiceImpl implements SessionService {
	
	public static final Logger LOG = Logger.getLogger(SessionServiceImpl.class);

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
				List<SessionInformation> sessionInfos = getAllSessionsByUsername(profile.getUsername());
				UserLoggedDTO userLogged = mapper.map(profile, UserLoggedDTO.class);
				userLogged.setExpired(sessionInfos.isEmpty());
				allUserLogged.add(userLogged);
			}
		}
		return allUserLogged;
	}

	@Override
	public void removeSession(String username) {
		List<SessionInformation> sessionInformations = getAllSessionsByUsername(username);
		SessionInformation sessionInformation = sessionInformations.get(0);
		sessionInformation.expireNow();
	}

	@Override
	public List<SessionInformation> getAllSessionsByUsername(String username) {
		
		List<SessionInformation> foundSession = null;
		List<Object> principals = sessionRegistry.getAllPrincipals();
		
		for (Object principal : principals) {
			if (principal instanceof UserProfile) {
				UserProfile profile = (UserProfile) principal;
				if (StringUtils.equals(username, profile.getUsername())) {
					foundSession = sessionRegistry.getAllSessions(profile, false);
					break;
				}
			}
		}
		return foundSession;
	}

}
