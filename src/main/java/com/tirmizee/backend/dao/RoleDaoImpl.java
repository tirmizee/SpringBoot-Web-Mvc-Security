package com.tirmizee.backend.dao;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.backend.api.role.data.RoleDTO;
import com.tirmizee.backend.api.role.data.SearchRoleDTO;
import com.tirmizee.backend.api.role.data.SearchTermDTO;
import com.tirmizee.core.repository.RoleRepositoryImpl;

@Repository
public class RoleDaoImpl extends RoleRepositoryImpl implements RoleDao {

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
		
		List<Object> params = new LinkedList<>();
		
		StringBuilder statement = new StringBuilder()
			.append(" SELECT ")
				.append(COL_ROLE_ID).append(",")
				.append(COL_ROLE_CODE).append(",")
				.append(COL_ROLE_NAME).append(",")
				.append(COL_ROLE_DESC).append(",")
				.append(COL_CREATE_DATE)
			.append(" FROM ").append(TB_ROLE)
			.append(" WHERE ").append(COL_ROLE_ID)
			.append(" IS NOT NULL");
		
		if (!StringUtils.isBlank(search.getRoleCode())) {
			statement.append(" AND ").append(COL_ROLE_CODE).append(" LIKE ? ");
			params.add("%" + StringUtils.trimToEmpty(search.getRoleCode()) + "%");
		}
		
		if (!StringUtils.isBlank(search.getRoleName())) {
			statement.append(" AND ").append(COL_ROLE_NAME).append(" LIKE ? ");
			params.add("%" + StringUtils.trimToEmpty(search.getRoleName()) + "%");
		}
		
		long total = count(statement.toString(), params.toArray());
		
		List<RoleDTO> content = getJdbcOps().query(
			getSqlGenerator().selectAll(statement, pageable), // build paging statement
			params.toArray(), 
			new BeanPropertyRowMapper<>(RoleDTO.class)
		);
		
		return new PageImpl<>(content, pageable, total);
	}

}
