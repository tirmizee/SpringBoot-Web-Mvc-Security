package com.tirmizee.core.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.LogPassword;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("LogPasswordRepository")
public class LogPasswordRepositoryImpl extends AbstractOracleJdbcRepository<LogPassword, Integer> implements LogPasswordRepository {

	public static final RowMapper<LogPassword> ROW_MAPPER = new BeanPropertyRowMapper<>(LogPassword.class);
	
	public static final RowUnmapper<LogPassword> ROW_UNMAPPER = new RowUnmapper<LogPassword>() {
		@Override
		public Map<String, Object> mapColumns(LogPassword param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_CREATE_DATE, param.getCreateDate());
			map.put(COL_PASSWORD, param.getPassword());
			map.put(COL_USERNAME, param.getUsername());
			map.put(COL_LOG_ID, param.getLogId());
			return map;
		}
	};

	public LogPasswordRepositoryImpl() {
		this(TB_LOG_PASSWORD);
	}
	
	public LogPasswordRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_LOG_ID);
	}

	@Override
	protected <S extends LogPassword> S postCreate(S entity, Number generatedId) {
		entity.setLogId(generatedId.intValue());
		return entity;
	}

}
