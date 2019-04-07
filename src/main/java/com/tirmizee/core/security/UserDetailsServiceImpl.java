package com.tirmizee.core.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tirmizee.backend.dao.PermissionDao;
import com.tirmizee.backend.dao.UserDao;
import com.tirmizee.core.domain.Permission;
import com.tirmizee.core.domain.UserDetail;
import com.tirmizee.core.utilities.DateUtils;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	public final Logger LOG = Logger.getLogger(UserDetailsServiceImpl.class);
	
	private UserDao userDao;
	private PermissionDao permissionDao;
	
	public UserDetailsServiceImpl(UserDao memberDao, PermissionDao permissionDao) {
		this.userDao = memberDao;
		this.permissionDao = permissionDao;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		
		UserDetail userDetail  = userDao.findDetailByUsername(username);
		
		if (userDetail == null ) {
			throw new UsernameNotFoundException(username);
		}
		
		List<Permission> permissions = permissionDao.findByUsername(username);
		LOG.info(username + " : " + permissions.stream().map(e -> e.getPerCode()).collect(Collectors.toList()).toString());

		return new UserProfile.Builder()
				.username(username)
				.password(userDetail.getPassword())
				.authorities(grantAuthorities(permissions))
				.enabled(userDetail.isEnabled())
				.accountNonExpired(userDetail.isAccountnonexpired())
				.accountNonLocked(userDetail.isAccountnonlocked())
				.credentialsNonExpired(userDetail.isCredentialsnonexpired())
				.credentialsExpiredDate(userDetail.getCredentialsexpiredDate())
				.accountExpiredDate(userDetail.getAccountExpiredDate())
				.firstName(userDetail.getFirstName())
				.lastName(userDetail.getLastName())
				.roleCode(userDetail.getRoleCode())
				.roleName(userDetail.getRoleName())
				.isFirstLogin(userDetail.getFirstLogin())
				.maxSession(userDetail.getMaxSession())
				.createDate(DateUtils.now())
				.build();
	}
	
	private Set<GrantedAuthority> grantAuthorities(Collection<? extends Permission> permissions){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        permissions.forEach(o -> authorities.add(new SimpleGrantedAuthority(o.getPerCode())));
	    return authorities;
	}
	
}
