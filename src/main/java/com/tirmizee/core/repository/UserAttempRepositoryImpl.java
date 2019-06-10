package com.tirmizee.core.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.UserAttemp;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("UserAttempRepository")
public class UserAttempRepositoryImpl extends AbstractOracleJdbcRepository<UserAttemp, String> implements UserAttempRepository {

	public static final RowMapper<UserAttemp> ROW_MAPPER = new RowMapper<UserAttemp>() {
		@Override
		public UserAttemp mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserAttemp userAttemp = new UserAttemp();
			userAttemp.setLastModified(rs.getTimestamp(COL_LAST_MODIFIED));
			userAttemp.setAccessIp(rs.getString(COL_ACCESS_IP));
			userAttemp.setAttemp(rs.getInt(COL_ATTEMP));
			userAttemp.setUsername(rs.getString(COL_USERNAME));
			return userAttemp.withPersisted(true);
		}
	};
	
	public static final RowUnmapper<UserAttemp> ROW_UNMAPPER = new RowUnmapper<UserAttemp>() {
		@Override
		public Map<String, Object> mapColumns(UserAttemp param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_LAST_MODIFIED, param.getLastModified());
			map.put(COL_ACCESS_IP, param.getAccessIp());
			map.put(COL_ATTEMP, param.getAttemp());
			map.put(COL_USERNAME, param.getUsername());
			return map;
		}
	};

	public UserAttempRepositoryImpl() {
		this(TB_USER_ATTEMP);
	}
	
	public UserAttempRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_USERNAME);
	}

	@Override
	protected <S extends UserAttemp> S postUpdate(S entity) {
		entity.withPersisted(true);
		return entity;
	}

	@Override
	protected <S extends UserAttemp> S postCreate(S entity, Number generatedId) {
		entity.withPersisted(true);
		return entity;
	}

}
