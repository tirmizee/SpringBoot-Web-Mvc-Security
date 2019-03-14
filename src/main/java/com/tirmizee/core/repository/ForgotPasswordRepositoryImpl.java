package com.tirmizee.core.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.ForgotPassword;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("AbstractOracleJdbcRepository")
public class ForgotPasswordRepositoryImpl extends AbstractOracleJdbcRepository<ForgotPassword, Integer> implements ForgotPasswordRepository {

	public static final RowMapper<ForgotPassword> ROW_MAPPER = new BeanPropertyRowMapper<>(ForgotPassword.class);
	
	public static final RowUnmapper<ForgotPassword> ROW_UNMAPPER = new RowUnmapper<ForgotPassword>() {
		@Override
		public Map<String, Object> mapColumns(ForgotPassword param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_ID, param.getId());
			map.put(COL_UPDATE_DATE, param.getUpdateDate());
			map.put(COL_CREATE_DATE, param.getCreateDate());
			map.put(COL_EMAIL, param.getEmail());
			map.put(COL_ACCESS_IP, param.getAccessIp());
			map.put(COL_TOKEN, param.getToken());
			map.put(COL_USERNAME, param.getUsername());
			map.put(COL_EXPIRED_DATE, param.getExpiredDate());
			return map;
		}
	};

	public ForgotPasswordRepositoryImpl() {
		this(TB_FORGOT_PASSWORD);
	}
	
	public ForgotPasswordRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_ID);
	}

	@Override
	protected <S extends ForgotPassword> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}

}
