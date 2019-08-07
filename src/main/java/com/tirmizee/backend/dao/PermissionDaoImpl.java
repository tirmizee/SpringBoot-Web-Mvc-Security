package com.tirmizee.backend.dao;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.tirmizee.backend.api.permission.data.PermissionCriteriaDTO;
import com.tirmizee.backend.api.permission.data.PermissionDTO;
import com.tirmizee.core.domain.Permission;
import com.tirmizee.core.jdbcrepository.NamedQueryJdbcOperations;
import com.tirmizee.core.repository.PermissionRepositoryImpl;

@Repository
public class PermissionDaoImpl extends PermissionRepositoryImpl implements PermissionDao {

	@Autowired
	private NamedQueryJdbcOperations queryNamedJdbc;
	
	@Override
	public List<Permission> findByUsername(String username) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource("USERNAME",username);
		return queryNamedJdbc.namedQuery("FIND.PERMISSION.BY.USERNAME", paramSource, ROW_MAPPER);
	}

	@Override
	public List<PermissionDTO> findAllByUsername(Integer roleId) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource("ROLE_ID",roleId);
		return queryNamedJdbc.namedQuery("FIND.PERMISSION.BY.ROLEID", paramSource, PermissionDTO.class);
	}

	@Override
	public Page<Permission> findAllByCriteria(PermissionCriteriaDTO search, Pageable pageable) {
		
		List<Object> params = new LinkedList<>();
		
		StringBuilder statement = new StringBuilder()
			.append(" SELECT * FROM ").append(TB_PERMISSION)
			.append(" WHERE ").append(COL_PER_ID).append(" IS NOT NULL ");
		
		if (search.getPerCode() != null) {
			statement.append(" AND ").append(COL_PER_CODE).append(" LIKE ? ");
			params.add("%" + StringUtils.trimToEmpty(search.getPerCode()) + "%");
		}
		
		if (search.getPerName() != null) {
			statement.append(" AND ").append(COL_PER_NAME).append(" LIKE ? ");
			params.add("%" + StringUtils.trimToEmpty(search.getPerName()) + "%");
		}
		
		long total = count(statement.toString(), params.toArray());
		String statementPage = getSqlGenerator().selectAll(statement, pageable);
		List<Permission> content = getJdbcOps().query(statementPage, params.toArray(), ROW_MAPPER);
		return new PageImpl<>(content, pageable, total);
	}

}
