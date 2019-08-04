package com.tirmizee.backend.dao;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.tirmizee.backend.api.user.data.UserDetailCriteriaDTO;
import com.tirmizee.backend.api.user.data.UserDetailPageDTO;
import com.tirmizee.backend.api.user.data.UserDetailUpdateDTO;
import com.tirmizee.core.domain.User;
import com.tirmizee.core.domain.UserDetail;
import com.tirmizee.core.jdbcrepository.NameQueryJdbcOperations;
import com.tirmizee.core.repository.ProfileRepository;
import com.tirmizee.core.repository.RoleRepository;
import com.tirmizee.core.repository.UserRepositoryImpl;

@Repository 
public class UserDaoImpl extends UserRepositoryImpl implements UserDao {
	
	public final Logger LOG = Logger.getLogger(UserDaoImpl.class);
	
	@Autowired
	private NameQueryJdbcOperations queryNamedJdbc;
	
	@Override
	public User findByUsername(String username) {
		try {
			String statemet = "SELECT * FROM USER_ATTEMP WHERE USERNAME = ?";
			return getJdbcOps().queryForObject(statemet, params(username), ROW_MAPPER);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	@Override
	public UserDetailUpdateDTO findDetailByUserId(Long userId) {
		try {
			final MapSqlParameterSource params = new MapSqlParameterSource("userId", userId);
			return queryNamedJdbc.nameQueryForObject("FIND.DETAIL.BY.USERID", params, UserDetailUpdateDTO.class);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	@Override
	public UserDetail findDetailByUsername(String username) {
		try {
			MapSqlParameterSource params = new MapSqlParameterSource("username", username);
			return queryNamedJdbc.nameQueryForObject("GET.DETAIL.BY.USERNAME", params, UserDetail.class);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public Page<UserDetailPageDTO> findPageByCriteria(Pageable pageable, UserDetailCriteriaDTO search) {
		List<Object> params = new LinkedList<>();
		
		StringBuilder statement = new StringBuilder(queryNamedJdbc.getQuery("FIND.DETAIL.BY.CRITERIA"));
		
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
		
		if (!StringUtils.isBlank(search.getTel())) {
			statement.append(" AND ").append(ProfileRepository.TEL).append(" LIKE ? ");
			params.add("%" + StringUtils.trimToEmpty(search.getTel()) + "%");
		}
		
		if (search.getRoleId() != null) {
			statement.append(" AND ").append(RoleRepository.ROLE_ID).append(" = ? ");
			params.add(search.getRoleId());
		}
		
		String statementPage = getSqlGenerator().selectAll(statement, pageable);
		List<UserDetailPageDTO> content = getJdbcOps().query(
			statementPage, 
			params.toArray(), 
			BeanPropertyRowMapper.newInstance(UserDetailPageDTO.class)
		);
		long total = count(statement.toString(), params.toArray());
		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public User findByEmail(String email) {
		try {
			MapSqlParameterSource params = new MapSqlParameterSource("EMAIL", email);
			return queryNamedJdbc.nameQueryForObject("FIND.USER.BY.EMAIL",  params, User.class);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public User findByUsername(String username, Long excludeUserId) {
		try {
			String statemet = "SELECT * FROM USERS WHERE USERNAME = ? AND USER_ID <> ?";
			return getJdbcOps().queryForObject(statemet, params(username, excludeUserId), ROW_MAPPER);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}

}
