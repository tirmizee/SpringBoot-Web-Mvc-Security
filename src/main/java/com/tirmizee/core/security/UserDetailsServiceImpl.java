package com.tirmizee.core.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tirmizee.backend.dao.UserDao;
import com.tirmizee.core.domain.UserDetail;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UserDao userDao;
	
	public UserDetailsServiceImpl(UserDao memberDao) {
		this.userDao = memberDao;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		
		final UserDetail userDetail  = userDao.findDetailByUsername(username);
	
		if (userDetail == null ) {
			throw new UsernameNotFoundException(username);
		}

		return new UserProfile.Builder()
				.username(username)
				.password(userDetail.getPassword())
				.enabled(userDetail.isEnabled())
				.accountNonExpired(userDetail.isAccountnonexpired())
				.accountNonLocked(userDetail.isAccountnonlocked())
				.credentialsNonExpired(userDetail.isCredentialsnonexpired())
				.fistName(userDetail.getFirstName())
				.lastName(userDetail.getLastName())
				.isFirstLogin(userDetail.getFirstLogin())
				.build();
	}
	
	
	
}
