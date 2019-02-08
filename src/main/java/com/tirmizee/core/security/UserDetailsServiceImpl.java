package com.tirmizee.core.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tirmizee.backend.dao.UserDao;
import com.tirmizee.core.domain.User;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UserDao userDao;
	
	public UserDetailsServiceImpl(UserDao memberDao) {
		this.userDao = memberDao;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		
		final User user  = userDao.findByUsername(username);
	
		if (user == null ) {
			throw new UsernameNotFoundException(username);
		}

		return new UserProfile.Builder()
				.username(username)
				.password(user.getPassword())
				.enabled(user.getEnabled())
				.accountNonExpired(user.getAccountnonexpired())
				.accountNonLocked(user.getAccountnonlocked())
				.credentialsNonExpired(user.getCredentialsnonexpired())
				.build();
	}
	
}
