package com.tirmizee.backend.dao;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.tirmizee.backend.api.user.data.UserDetailCriteriaDTO;
import com.tirmizee.backend.api.user.data.UserDetailDTO;
import com.tirmizee.core.domain.User;
import com.tirmizee.core.domain.UserDetail;
import com.tirmizee.core.repository.ProfileRepository;
import com.tirmizee.core.repository.RoleRepository;
import com.tirmizee.core.repository.UserRepositoryImpl;

@Repository 
public class UserDaoImpl extends UserRepositoryImpl implements UserDao {
	
	public final Logger LOG = Logger.getLogger(UserDaoImpl.class);
	
	@Override
	public User findByUsername(String username) {
		try {
			StringBuilder statemet = new StringBuilder()
				.append("SELECT * FROM ").append(TB_USERS)
				.append(" WHERE ").append(COL_USERNAME).append(" = ? ");
			return getJdbcOps().queryForObject(statemet.toString(), params(username), ROW_MAPPER);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	@Override
	public UserDetail findDetailByUsername(String username) {
		try {
			final MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("username", username);
			final StringBuilder statemet = new StringBuilder()
				.append("SELECT ")
				.append(USERNAME).append(" , ")
				.append(PASSWORD).append(" , ")
				.append(RoleRepository.ROLE_ID).append(" , ")
				.append(RoleRepository.ROLE_NAME).append(" , ")
				.append(ENABLED).append(" , ")
				.append(ACCOUNTNONLOCKED).append(" , ")
				.append(ACCOUNTNONEXPIRED).append(" , ")
				.append(COL_CREDENTIALSEXPIRED_DATE).append(" , ")
				.append(CREDENTIALSNONEXPIRED).append(" , ")
				.append(FIRST_LOGIN).append(" , ")
				.append(ProfileRepository.FIRST_NAME).append(" , ")
				.append(ProfileRepository.LAST_NAME)
				.append(" FROM ").append(TB_USERS)
				.append(" INNER JOIN ").append(ProfileRepository.TB_PROFILE)
				.append(" ON ").append(PROFILE_ID).append(" = ").append(ProfileRepository.PROFILE_ID)
				.append(" INNER JOIN ").append(RoleRepository.TB_ROLE)
				.append(" ON ").append(FK_ROLE_ID).append(" = ").append(RoleRepository.ROLE_ID)
				.append(" WHERE ").append(USERNAME).append(" = :username ");
			return getNamedJdbcOps().queryForObject(statemet.toString(), params, new BeanPropertyRowMapper<>(UserDetail.class));
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public Page<UserDetailDTO> findPageByCriteria(Pageable pageable, UserDetailCriteriaDTO search) {
		List<Object> params = new LinkedList<>();
		StringBuilder statement = new StringBuilder()
			.append(" SELECT ")
				.append(USER_ID).append(",")
				.append(USERNAME).append(",")
				.append(FIRST_LOGIN).append(",")
				.append(ENABLED).append(",")
				.append(ACCOUNTNONLOCKED).append(",")
				.append(ACCOUNTNONEXPIRED).append(",")
				.append(CREDENTIALSNONEXPIRED).append(",")
				.append(CREDENTIALSEXPIRED_DATE).append(",")
				.append(ProfileRepository.FIRST_NAME).append(",")
				.append(ProfileRepository.LAST_NAME).append(",")
				.append(ProfileRepository.EMAIL).append(",")
				.append(RoleRepository.ROLE_CODE).append(",")
				.append(RoleRepository.ROLE_ID).append(",")
				.append(RoleRepository.ROLE_NAME).append(",")
				.append(RoleRepository.ROLE_DESC)
			.append(" FROM ").append(TB_USERS)
			.append(" INNER JOIN ").append(ProfileRepository.TB_PROFILE)
				.append(" ON ").append(PROFILE_ID).append(" = ").append(ProfileRepository.PROFILE_ID)
			.append(" INNER JOIN ").append(RoleRepository.TB_ROLE)
				.append(" ON ").append(FK_ROLE_ID).append(" = ").append(RoleRepository.ROLE_ID)
			.append(" WHERE ").append(USER_ID).append(" IS NOT NULL");
		
		if (!StringUtils.isBlank(search.getUsername())) {
			statement.append(" AND ").append(USERNAME).append(" LIKE ? ");
			params.add("%" + StringUtils.trimToEmpty(search.getUsername()) + "%");
		}
		
		if (!StringUtils.isBlank(search.getFirstName())) {
			statement.append(" AND ").append(ProfileRepository.FIRST_NAME).append(" LIKE ? ");
			params.add("%" + StringUtils.trimToEmpty(search.getFirstName()) + "%");
		}
		
		if (!StringUtils.isBlank(search.getLastName())) {
			statement.append(" AND ").append(ProfileRepository.LAST_NAME).append(" LIKE ? ");
			params.add("%" + StringUtils.trimToEmpty(search.getLastName()) + "%");
		}
		
		if (!StringUtils.isBlank(search.getEmail())) {
			statement.append(" AND ").append(ProfileRepository.EMAIL).append(" LIKE ? ");
			params.add("%" + StringUtils.trimToEmpty(search.getEmail()) + "%");
		}
		
		String statementPage = getSqlGenerator().selectAll(statement, pageable);
		List<UserDetailDTO> content = getJdbcOps().query(
			statementPage.toString(), 
			params.toArray(), 
			new BeanPropertyRowMapper<>(UserDetailDTO.class)
		);
		long total = count(statement.toString(), params.toArray());
		return new PageImpl<>(content, pageable, total);
	}

}
