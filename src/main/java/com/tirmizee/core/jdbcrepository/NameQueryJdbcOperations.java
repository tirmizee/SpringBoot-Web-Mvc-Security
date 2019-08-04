package com.tirmizee.core.jdbcrepository;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;


/**
 * @author Pratya Yeekhaday
 *
 */
public interface NameQueryJdbcOperations extends NamedParameterJdbcOperations {

	String getQuery(String queryName);
	
	<T> T nameQueryForObject(String queryName,  Map<String, ?> paramMap,  Class<T> mappedClass) throws DataAccessException;
	
	<T> T nameQueryForObject(String queryName, SqlParameterSource paramSource,  Class<T> mappedClass) throws DataAccessException;
	
	<T> T nameQueryForObject(String queryName, SqlParameterSource paramSource, RowMapper<T> rowMapper) throws DataAccessException;
	
	<T> T namQuery(String queryName, SqlParameterSource paramSource, ResultSetExtractor<T> rse) throws DataAccessException;

	<T> List<T> namQuery(String queryName, RowMapper<T> rowMapper) throws DataAccessException;
	
	<T> List<T> namQuery(String queryName, SqlParameterSource paramSource, RowMapper<T> rowMapper) throws DataAccessException;
	
	
	
}
