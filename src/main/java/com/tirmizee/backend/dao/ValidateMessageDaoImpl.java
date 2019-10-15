package com.tirmizee.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.ValidateMessage;
import com.tirmizee.core.jdbcrepository.NamedQueryJdbcOperations;
import com.tirmizee.core.repository.ValidateMessageRepositoryImpl;

@Repository
public class ValidateMessageDaoImpl extends ValidateMessageRepositoryImpl implements ValidateMessageDao {

	@Autowired
	private NamedQueryJdbcOperations queryJdbcOparation;
	
	@Override
	public ValidateMessage getByCode(String code) {
		try {
			MapSqlParameterSource params = new MapSqlParameterSource("MSG_CODE", code);
			return queryJdbcOparation.namedQueryForObject("GET.MESSAGE.BY.CODE",  params, ValidateMessage.class);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		} 
	}

}
