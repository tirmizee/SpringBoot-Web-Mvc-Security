package com.tirmizee.backend.dao;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.User;
import com.tirmizee.core.domain.UserDetail;
import com.tirmizee.core.repository.ProfileRepository;
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
				.append(ENABLED).append(" , ")
				.append(ACCOUNTNONLOCKED).append(" , ")
				.append(ACCOUNTNONEXPIRED).append(" , ")
				.append(CREDENTIALSNONEXPIRED).append(" , ")
				.append(FIRST_LOGIN).append(" , ")
				.append(ProfileRepository.FIRST_NAME).append(" , ")
				.append(ProfileRepository.LAST_NAME)
				.append(" FROM ").append(TB_USERS)
				.append(" INNER JOIN ").append(ProfileRepository.TB_PROFILE)
				.append(" ON ").append(PROFILE_ID).append(" = ").append(ProfileRepository.PROFILE_ID)
				.append(" WHERE ").append(USERNAME).append(" = :username ");
			return getNamedJdbcOps().queryForObject(statemet.toString(), params, new BeanPropertyRowMapper<>(UserDetail.class));
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}

}
