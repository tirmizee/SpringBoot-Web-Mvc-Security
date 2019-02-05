package com.tirmizee.core.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.Member;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("MemberRepository")
public class MemberRepositoryImpl extends AbstractOracleJdbcRepository<Member, String> implements MemberRepository {

	public static final RowMapper<Member> ROW_MAPPER = new RowMapper<Member>() {
		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member member = new Member();
			member.setUsername(rs.getString(COL_USERNAME));
			member.setPassword(rs.getString(COL_PASSWORD));
			return member.withPersisted(true);
		}
	};
	
	public static final RowUnmapper<Member> ROW_UNMAPPER = new RowUnmapper<Member>() {
		@Override
		public Map<String, Object> mapColumns(Member param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_USERNAME, param.getUsername());
			map.put(COL_PASSWORD, param.getPassword());
			return map;
		}
	};

	public MemberRepositoryImpl() {
		this(TB_MEMBER);
	}
	
	public MemberRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_USERNAME);
	}
	
	@Override
	protected <S extends Member> S postUpdate(S entity) {
		entity.withPersisted(true);
		return entity;
	}

	@Override
	protected <S extends Member> S postCreate(S entity, Number generatedId) {
		entity.withPersisted(true);
		return entity;
	}
	
}
