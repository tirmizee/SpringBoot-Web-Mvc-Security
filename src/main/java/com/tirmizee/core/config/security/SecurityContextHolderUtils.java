package com.tirmizee.core.config.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Pratya Yeekhaday
 *
 */
public class SecurityContextHolderUtils {
	
	public static void grantAuthority(String username, String...authorities){
		UsernamePasswordAuthenticationToken authRequest = authenticationToken(
			username, null, AuthorityUtils.createAuthorityList(authorities));
		updateAuthentication(authRequest);
	}
	
	public static void grantAuthority(String username, GrantedAuthority...authorities){
		UsernamePasswordAuthenticationToken authRequest = authenticationToken(username, null, Arrays.asList(authorities));
		updateAuthentication(authRequest);
	}
	
	public static void addAuthority(String username, String authority){
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(authority));
		authorities.addAll(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		UsernamePasswordAuthenticationToken authRequest = authenticationToken(username , null ,authorities);
		updateAuthentication(authRequest);
	}
	
	public static void addAuthority(String username, String...authority){
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.addAll(AuthorityUtils.createAuthorityList(authority));
		authorities.addAll(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		UsernamePasswordAuthenticationToken authRequest = authenticationToken(username , null ,authorities);
		updateAuthentication(authRequest);
	}
	
	public static void grantAuthority(String username, String authority){
		List<GrantedAuthority> authorities = Arrays.asList( new SimpleGrantedAuthority(authority));
		UsernamePasswordAuthenticationToken authRequest = authenticationToken(username , null ,authorities);
		updateAuthentication(authRequest);
	}
	
	private static UsernamePasswordAuthenticationToken authenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities){
		return new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
	}
	
	private static void updateAuthentication(Authentication authentication){
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
