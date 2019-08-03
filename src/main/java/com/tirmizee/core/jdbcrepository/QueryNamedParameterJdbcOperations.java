package com.tirmizee.core.jdbcrepository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;


/**
 * @author Pratya Yeekhaday
 *
 */
public interface QueryNamedParameterJdbcOperations extends NamedParameterJdbcOperations {

	<T> T queryNamedForObject(String queryName, SqlParameterSource paramSource,  Class<T> mappedClass) throws DataAccessException;
	
	<T> T queryNamedForObject(String queryName, SqlParameterSource paramSource, RowMapper<T> rowMapper) throws DataAccessException;
	
}
