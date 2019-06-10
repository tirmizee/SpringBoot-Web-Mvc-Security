package com.tirmizee.backend.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.ForgotPassword;
import com.tirmizee.core.repository.ForgotPasswordRepositoryImpl;

@Repository
public class ForgotPasswordDaoImpl extends ForgotPasswordRepositoryImpl implements ForgotPasswordDao {

	@Override
	public ForgotPassword findByUserIdAndToken(Long uid, String token) {
		try {
			String statement = new StringBuilder()
				.append(" SELECT * FROM ").append(TB_FORGOT_PASSWORD)
				.append(" WHERE ").append(COL_TOKEN).append(" = ? ")
				.append(" AND ").append(COL_USERID).append(" = ? ")
				.append(" AND ").append(COL_IS_RESET).append(" = 0 ")
				.toString();
			return getJdbcOps().queryForObject(statement, params(token, uid), ROW_MAPPER);
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

}
