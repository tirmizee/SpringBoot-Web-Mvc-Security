package com.tirmizee.core.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.RoleMapPermission;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;
import com.tirmizee.core.jdbcrepository.TableDescription;

@Repository("RoleMapPermissionRepository")
public class RoleMapPermissionRepositoryImpl extends AbstractOracleJdbcRepository<RoleMapPermission, Object[]> implements RoleMapPermissionRepository {

	public static final RowMapper<RoleMapPermission> ROW_MAPPER = new RowMapper<RoleMapPermission>() {
		@Override
		public RoleMapPermission mapRow(ResultSet rs, int rowNum) throws SQLException {
			RoleMapPermission roleMapPermission = new RoleMapPermission();
			roleMapPermission.setRoleId(rs.getInt(COL_ROLE_ID));
			roleMapPermission.setCreateDate(rs.getDate(COL_CREATE_DATE));
			roleMapPermission.setPerId(rs.getInt(COL_PER_ID));
			return roleMapPermission.withPersisted(true);
		}
	};

	public static final RowUnmapper<RoleMapPermission> ROW_UNMAPPER = new RowUnmapper<RoleMapPermission>() {
		@Override
		public Map<String, Object> mapColumns(RoleMapPermission param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_ROLE_ID, param.getRoleId());
			map.put(COL_CREATE_DATE, param.getCreateDate());
			map.put(COL_PER_ID, param.getPerId());
			return map;
		}
	};

	public RoleMapPermissionRepositoryImpl() {
		this(TB_ROLE_MAP_PERMISSION);
	}
	
	public RoleMapPermissionRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, new TableDescription(tableName, null, COL_ROLE_ID, COL_PER_ID));
	}

	@Override
	protected <S extends RoleMapPermission> S postUpdate(S entity) {
		entity.withPersisted(true);
		return entity;
	}

	@Override
	protected <S extends RoleMapPermission> S postCreate(S entity, Number generatedId) {
		entity.withPersisted(true);
		return entity;
	}
	
}
