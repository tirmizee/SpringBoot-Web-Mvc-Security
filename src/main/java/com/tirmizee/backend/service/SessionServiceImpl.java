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
	public List<UserLoggedDTO> allUserLogged() {
		
		List<UserLoggedDTO> allUserLogged = new ArrayList<>();
		List<Object> principals = sessionRegistry.getAllPrincipals();
		
		for (Object principal : principals) {
			if (principal instanceof UserProfile) {
				UserProfile profile = (UserProfile) principal;
				List<SessionInformation> sessionInfos = getAllSessionsByUsername(profile.getUsername(), true);
				for (SessionInformation sessionInfo : sessionInfos) {
					UserLoggedDTO userLogged = mapper.map(profile, UserLoggedDTO.class);
					userLogged.setExpired(sessionInfo.isExpired());
					userLogged.setSessionId(sessionInfo.getSessionId());
					userLogged.setCreateDate(sessionInfo.getLastRequest());
					allUserLogged.add(userLogged);
				}
			}
		}
		return allUserLogged;
	}

	@Override
	public void removeSession(String username, String sessionId) {
		List<SessionInformation> sessionInformations = getAllSessionsByUsername(username, false);
		for (SessionInformation sessionInformation : sessionInformations) {
			if (StringUtils.equals(sessionId, sessionInformation.getSessionId())) {
				sessionInformation.expireNow();
				break;
			}
		}
	}

	@Override
	public List<SessionInformation> getAllSessionsByUsername(String username, boolean includeExpiredSessions) {
		
		List<SessionInformation> foundSession = null;
		List<Object> principals = sessionRegistry.getAllPrincipals();
		
		for (Object principal : principals) {
			if (principal instanceof UserProfile) {
				UserProfile profile = (UserProfile) principal;
				if (StringUtils.equals(username, profile.getUsername())) {
					foundSession = sessionRegistry.getAllSessions(profile, includeExpiredSessions);
					break;
				}
			}
		}
		return foundSession;
	}
	
	@Override
	public int countSessions() {
		return sessionRegistry.getAllPrincipals().size();
	}

	@Override
	public int countSessionsExpired() {
		int count = 0;
		List<Object> principals = sessionRegistry.getAllPrincipals();
		for (Object principal : principals) {
			if (principal instanceof UserProfile) {
				UserProfile profile = (UserProfile) principal;
				List<SessionInformation> sessionInfos = sessionRegistry.getAllSessions(profile, true);
				for (SessionInformation sessionInfo : sessionInfos) {
					if (sessionInfo.isExpired()) {
						count++;
					}
				}
			}
		}
		return count;
	}

	@Override
	public int countSessionsActive() {
		int count = 0;
		List<Object> principals = sessionRegistry.getAllPrincipals();
		for (Object principal : principals) {
			if (principal instanceof UserProfile) {
				UserProfile profile = (UserProfile) principal;
				List<SessionInformation> sessionInfos = sessionRegistry.getAllSessions(profile, false);
				for (SessionInformation sessionInfo : sessionInfos) {
					if (!sessionInfo.isExpired()) {
						count++;
					}
				}
			}
		}
		return count;
	}

}
