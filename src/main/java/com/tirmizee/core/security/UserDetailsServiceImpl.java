package com.tirmizee.core.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	public final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
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
		LOGGER.info("username : {}, authorities : {}", username, permissions.stream().map(e -> e.getPerCode()).collect(Collectors.toList()).toString());

		return new UserProfile.Builder()
				.username(username)
				.password(userDetail.getPassword())
				.authorities(grantAuthorities(permissions))
				.enabled(userDetail.isEnabled())
				.accountNonExpired(userDetail.isAccountNonExpired())
				.accountNonLocked(userDetail.isAccountNonLocked())
				.credentialsNonExpired(userDetail.isCredentialsNonExpired())
				.credentialsExpiredDate(userDetail.getCredentialsExpiredDate())
				.accountExpiredDate(userDetail.getAccountExpiredDate())
				.accountLockedDate(userDetail.getAccountLockedDate())
				.firstName(userDetail.getFirstName())
				.lastName(userDetail.getLastName())
				.roleCode(userDetail.getRoleCode())
				.roleName(userDetail.getRoleName())
				.branchCode(userDetail.getBranchCode())
				.profileImage(userDetail.getProfileImage())
				.isFirstLogin(userDetail.isFirstLogin())
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
