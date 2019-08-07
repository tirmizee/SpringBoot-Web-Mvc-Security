package com.tirmizee.backend.dao;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.tirmizee.backend.api.role.data.RoleDTO;
import com.tirmizee.backend.api.role.data.SearchRoleDTO;
import com.tirmizee.backend.api.role.data.SearchTermDTO;
import com.tirmizee.core.jdbcrepository.NamedQueryJdbcOperations;
import com.tirmizee.core.repository.RoleRepositoryImpl;

@Repository
public class RoleDaoImpl extends RoleRepositoryImpl implements RoleDao {

	@Autowired
	private NamedQueryJdbcOperations queryNamedJdbc;
	
	@Override
	public Page<RoleDTO> findPage(SearchTermDTO searchTerm, Pageable pageable) {
		
		List<Object> params = new LinkedList<>();
		
		StringBuilder statement = new StringBuilder()
			.append(" SELECT ")
				.append(COL_ROLE_ID).append(",")
				.append(COL_ROLE_CODE).append(",")
				.append(COL_ROLE_NAME).append(",")
				.append(COL_ROLE_DESC)
			.append(" FROM ").append(TB_ROLE)
			.append(" WHERE ").append(COL_ROLE_ID)
			.append(" IS NOT NULL");
		
		if (!StringUtils.isBlank(searchTerm.getTerm())) {
			statement
				.append(" AND ").append(COL_ROLE_CODE).append(" LIKE ? ")
				.append(" OR ").append(COL_ROLE_NAME).append(" LIKE ? ");
			params.add("%" + StringUtils.trimToEmpty(searchTerm.getTerm()) + "%");
			params.add("%" + StringUtils.trimToEmpty(searchTerm.getTerm()) + "%");
		}
		
		long total = count(statement.toString(), params.toArray());
		
		List<RoleDTO> content = getJdbcOps().query(
			getSqlGenerator().selectAll(statement, pageable), // build paging statement
			params.toArray(), 
			new BeanPropertyRowMapper<>(RoleDTO.class)
		);
		
		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public Page<RoleDTO> findPageTable(SearchRoleDTO search, Pageable pageable) {
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		StringBuilder statement = new StringBuilder(queryNamedJdbc.getQuery("FIND.ROLE"));
		
		if (!StringUtils.isBlank(search.getRoleCode())) {
			statement.append(" AND ROLE_CODE LIKE :ROLE_CODE ");
			paramSource.addValue("ROLE_CODE", "%" + StringUtils.trimToEmpty(search.getRoleCode()) + "%");
		}
		
		if (!StringUtils.isBlank(search.getRoleName())) {
			statement.append(" AND ROLE_NAME LIKE :ROLE_NAME ");
			paramSource.addValue("ROLE_NAME", "%" + StringUtils.trimToEmpty(search.getRoleName()) + "%");
		}
		
		return queryNamedJdbc.queryForPage(statement.toString(), pageable, paramSource, RoleDTO.class);
	}

}
