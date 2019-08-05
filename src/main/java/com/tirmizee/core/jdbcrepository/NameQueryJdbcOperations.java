package com.tirmizee.core.jdbcrepository;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	<T> T queryNameForObject(String queryName,  Map<String, ?> paramMap,  Class<T> mappedClass) throws DataAccessException;
	
	<T> T queryNameForObject(String queryName, SqlParameterSource paramSource,  Class<T> mappedClass) throws DataAccessException;
	
	<T> T queryNameForObject(String queryName, SqlParameterSource paramSource, RowMapper<T> rowMapper) throws DataAccessException;
	
	<T> T queryName(String queryName, SqlParameterSource paramSource, ResultSetExtractor<T> rse) throws DataAccessException;

	<T> List<T> queryName(String queryName, RowMapper<T> rowMapper) throws DataAccessException;
	
	<T> List<T> queryName(String queryName, Map<String, ?> paramMap, Class<T> mappedClass) throws DataAccessException;
	
	<T> List<T> queryName(String queryName, Map<String, ?> paramMap, RowMapper<T> rowMapper) throws DataAccessException;
	
	<T> List<T> queryName(String queryName, SqlParameterSource paramSource, Class<T> mappedClass) throws DataAccessException;
	
	<T> List<T> queryName(String queryName, SqlParameterSource paramSource, RowMapper<T> rowMapper) throws DataAccessException;
	
	<T> Page<T> queryForPage(String query, Pageable pageable, Map<String, ?> paramMap, Class<T> mappedClass) throws DataAccessException;
	
	<T> Page<T> queryForPage(String query, Pageable pageable, SqlParameterSource paramSource, Class<T> mappedClass) throws DataAccessException;
	
	<T> Page<T> queryForPage(String query, Pageable pageable, SqlParameterSource paramSource, RowMapper<T> rowMapper) throws DataAccessException;
	
	<T> Page<T> queryNameForPage(String queryName, Pageable pageable, Map<String, ?> paramMap, Class<T> mappedClass) throws DataAccessException;
	
	<T> Page<T> queryNameForPage(String queryName, Pageable pageable, SqlParameterSource paramSource, Class<T> mappedClass) throws DataAccessException;
	
	<T> Page<T> queryNameForPage(String queryName, Pageable pageable, SqlParameterSource paramSource, RowMapper<T> rowMapper) throws DataAccessException;
	
}
