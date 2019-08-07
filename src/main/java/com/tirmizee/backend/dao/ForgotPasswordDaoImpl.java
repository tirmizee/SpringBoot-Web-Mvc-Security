package com.tirmizee.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.ForgotPassword;
import com.tirmizee.core.jdbcrepository.NamedQueryJdbcOperations;
import com.tirmizee.core.repository.ForgotPasswordRepositoryImpl;

@Repository
public class ForgotPasswordDaoImpl extends ForgotPasswordRepositoryImpl implements ForgotPasswordDao {

	@Autowired
	private NamedQueryJdbcOperations queryNamedJdbc;
	
	@Override
	public ForgotPassword findByUserIdAndToken(Long uid, String token) {
		try {
			MapSqlParameterSource paramSource = new MapSqlParameterSource()
				.addValue("TOKEN", token)
				.addValue("USERID", uid);
			return queryNamedJdbc.namedQueryForObject("GET.PASSWORD.BY.UID.AND.TOKEN", paramSource, ROW_MAPPER);
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

}
