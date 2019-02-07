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
		
		final User member  = userDao.findByUsername(username);
	
		if (member == null ) {
			throw new UsernameNotFoundException(username);
		}

		return new UserProfile.Builder()
				.username(username)
				.password(member.getPassword())
				.enabled(true)
				.accountNonExpired(true)
				.accountNonLocked(true)
				.credentialsNonExpired(true)
				.build();
	}
	
}
