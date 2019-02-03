package com.tirmizee.core.repository;

import com.tirmizee.core.domain.Member;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface MemberRepository extends JdbcRepository<Member, String>{
	
	public static final String TB_MEMBER = "member";
	
	public static final String COL_USERNAME = "username";
	public static final String COL_PASSWORD = "password";
	
	public static final String USERNAME = "member.username";
	public static final String PASSWORD = "member.password";
	
}
