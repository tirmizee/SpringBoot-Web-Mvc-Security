package com.tirmizee.core.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.Permission;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("PermissionRepository")
public class PermissionRepositoryImpl extends AbstractOracleJdbcRepository<Permission, Integer> implements PermissionRepository {

	public static final RowMapper<Permission> ROW_MAPPER = new BeanPropertyRowMapper<>(Permission.class);
	
	public static final RowUnmapper<Permission> ROW_UNMAPPER = new RowUnmapper<Permission>() {
		@Override
		public Map<String, Object> mapColumns(Permission param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_UPDATE_BY, param.getUpdateBy());
			map.put(COL_UPDATE_DATE, param.getUpdateDate());
			map.put(COL_CREATE_DATE, param.getCreateDate());
			map.put(COL_PER_NAME, param.getPerName());
			map.put(COL_PER_CODE, param.getPerCode());
			map.put(COL_PER_ID, param.getPerId());
			map.put(COL_CREATE_BY, param.getCreateBy());
			return map;
		}
	};

	public PermissionRepositoryImpl() {
		this(TB_PERMISSION);
	}
	
	public PermissionRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_PER_ID);
	}

	@Override
	protected <S extends Permission> S postCreate(S entity, Number generatedId) {
		entity.setPerId(generatedId.intValue());
		return entity;
	}

}
