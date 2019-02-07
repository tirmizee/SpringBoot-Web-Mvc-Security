package com.tirmizee.core.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.User;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("MemberRepository")
public class UserRepositoryImpl extends AbstractOracleJdbcRepository<User, String> implements UserRepository {

	public static final RowMapper<User> ROW_MAPPER = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setPassword(rs.getString(COL_PASSWORD));
			user.setUsername(rs.getString(COL_USERNAME));
			user.setCredentialsnonexpired(rs.getInt(COL_CREDENTIALSNONEXPIRED));
			user.setAccountnonexpired(rs.getInt(COL_ACCOUNTNONEXPIRED));
			user.setAccountnonlocked(rs.getInt(COL_ACCOUNTNONLOCKED));
			user.setEnabled(rs.getInt(COL_ENABLED));
			return user.withPersisted(true);
		}
	};
	
	public static final RowUnmapper<User> ROW_UNMAPPER = new RowUnmapper<User>() {
		@Override
		public Map<String, Object> mapColumns(User param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_PASSWORD, param.getPassword());
			map.put(COL_USERNAME, param.getUsername());
			map.put(COL_ENABLED, param.getEnabled());
			map.put(COL_ACCOUNTNONLOCKED, param.getAccountnonlocked());
			map.put(COL_ACCOUNTNONEXPIRED, param.getAccountnonexpired());
			map.put(COL_CREDENTIALSNONEXPIRED, param.getCredentialsnonexpired());
			return map;
		}
	};

	public UserRepositoryImpl() {
		this(TB_USERS);
	}
	
	public UserRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_USERNAME);
	}
	
	@Override
	protected <S extends User> S postUpdate(S entity) {
		entity.withPersisted(true);
		return entity;
	}

	@Override
	protected <S extends User> S postCreate(S entity, Number generatedId) {
		entity.withPersisted(true);
		return entity;
	}
	
}
