package com.tirmizee.backend.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.UserAttemp;
import com.tirmizee.core.repository.UserAttempRepositoryImpl;

@Repository
public class UserAttempDaoImpl extends UserAttempRepositoryImpl implements UserAttempDao {

	@Override
	public UserAttemp findByUsername(String username) {
		try {
			StringBuilder select = new StringBuilder()
				.append(" SELECT * FROM ").append(TB_USER_ATTEMP)
				.append(" WHERE ").append(COL_USERNAME).append(" = ? ");
			return getJdbcOps().queryForObject(select.toString(),params(username), ROW_MAPPER);
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
