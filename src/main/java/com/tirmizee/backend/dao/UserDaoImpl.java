package com.tirmizee.backend.dao;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.User;
import com.tirmizee.core.repository.UserRepositoryImpl;

@Repository
public class UserDaoImpl extends UserRepositoryImpl implements UserDao {
	
	public final Logger LOG = Logger.getLogger(UserDaoImpl.class);
	
	@Override
	public User findByUsername(String username) {
		try {
			StringBuilder statemet = new StringBuilder()
				.append("SELECT * FROM ").append(TB_MEMBER)
				.append(" WHERE ").append(COL_USERNAME).append(" = ? ");
			return getJdbcOps().queryForObject(statemet.toString(), params(username), ROW_MAPPER);
		} catch(EmptyResultDataAccessException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
