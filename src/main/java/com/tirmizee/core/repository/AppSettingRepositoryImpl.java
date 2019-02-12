package com.tirmizee.core.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.AppSetting;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("AppSettingRepository")
public class AppSettingRepositoryImpl extends AbstractOracleJdbcRepository<AppSetting, String> implements AppSettingRepository {

	public static final RowMapper<AppSetting> ROW_MAPPER = new RowMapper<AppSetting>() {
		@Override
		public AppSetting mapRow(ResultSet rs, int rowNum) throws SQLException {
			AppSetting appSetting = new AppSetting();
			appSetting.setValue(rs.getString(COL_VALUE));
			appSetting.setKey(rs.getString(COL_KEY));
			return appSetting.withPersisted(true);
		}
	};
	
	public static final RowUnmapper<AppSetting> ROW_UNMAPPER = new RowUnmapper<AppSetting>() {
		@Override
		public Map<String, Object> mapColumns(AppSetting param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_VALUE, param.getValue());
			map.put(COL_KEY, param.getKey());
			return map;
		}
	};

	public AppSettingRepositoryImpl() {
		this(TB_APP_SETTING);
	}
	
	public AppSettingRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_KEY);
	}

	@Override
	protected <S extends AppSetting> S postUpdate(S entity) {
		entity.withPersisted(true);
		return entity;
	}

	@Override
	protected <S extends AppSetting> S postCreate(S entity, Number generatedId) {
		entity.withPersisted(true);
		return entity;
	}
	
}
