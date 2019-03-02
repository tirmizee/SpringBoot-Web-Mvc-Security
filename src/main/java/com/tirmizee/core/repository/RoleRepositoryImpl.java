package com.tirmizee.core.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.Role;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("RoleRepository")
public class RoleRepositoryImpl extends AbstractOracleJdbcRepository<Role, Integer> implements RoleRepository {

	public static final RowMapper<Role> ROW_MAPPER = new RowMapper<Role>() {
		@Override
		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			Role role = new Role();
			role.setUpdateBy(rs.getString(COL_UPDATE_BY));
			role.setUpdateDate(rs.getDate(COL_UPDATE_DATE));
			role.setRoleDesc(rs.getString(COL_ROLE_DESC));
			role.setRoleName(rs.getString(COL_ROLE_NAME));
			role.setRoleCode(rs.getString(COL_ROLE_CODE));
			role.setRoleId(rs.getInt(COL_ROLE_ID));
			return role;
		}
	};
	
	public static final RowUnmapper<Role> ROW_UNMAPPER = new RowUnmapper<Role>() {
		@Override
		public Map<String, Object> mapColumns(Role param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_UPDATE_BY, param.getUpdateBy());
			map.put(COL_UPDATE_DATE, param.getUpdateDate());
			map.put(COL_ROLE_DESC, param.getRoleDesc());
			map.put(COL_ROLE_NAME, param.getRoleName());
			map.put(COL_ROLE_CODE, param.getRoleCode());
			map.put(COL_ROLE_ID, param.getRoleId());
			return map;
		}
	};
	
	public RoleRepositoryImpl() {
		this(TB_ROLE);
	}
	
	public RoleRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_ROLE_ID);
	}

	@Override
	protected <S extends Role> S postCreate(S entity, Number generatedId) {
		entity.setRoleId(generatedId.intValue());
		return entity;
	}
	
}
