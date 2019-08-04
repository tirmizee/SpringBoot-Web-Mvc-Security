package com.tirmizee.core.jdbcrepository;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

/**
 * @author Pratya Yeekhaday
 *
 */
@Component
public class NameQueryJdbcTemplate extends NamedParameterJdbcTemplate implements NameQueryJdbcOperations {

	private Map<String, String> queries;

	@Override
	public String getQuery(String queryName) {
		return queries.get(queryName);
	}
	
	@Autowired
	public NameQueryJdbcTemplate(DataSource dataSource, Map<String, String> queries) {
		super(dataSource);
		this.queries = queries;
	}

	@Override
	public <T> T nameQueryForObject(String queryName, Map<String, ?> paramMap, Class<T> mappedClass)
			throws DataAccessException {
		return queryForObject(queries.get(queryName), paramMap, mappedClass);
	}
	
	@Override
	public <T> T nameQueryForObject(String queryName, SqlParameterSource paramSource, Class<T> mappedClass)
			throws DataAccessException {
		return queryForObject(queries.get(queryName), paramSource, BeanPropertyRowMapper.newInstance(mappedClass));
	}

	@Override
	public <T> T nameQueryForObject(String queryName, SqlParameterSource paramSource, RowMapper<T> rowMapper)
			throws DataAccessException {
		return queryForObject(queries.get(queryName), paramSource,rowMapper);
	}

	@Override
	public <T> List<T> namQuery(String queryName, SqlParameterSource paramSource, RowMapper<T> rowMapper)
			throws DataAccessException {
		return query(queries.get(queryName), paramSource, rowMapper);
	}

	@Override
	public <T> List<T> namQuery(String queryName, RowMapper<T> rowMapper) throws DataAccessException {
		return query(queries.get(queryName), rowMapper);
	}

	@Override
	public <T> T namQuery(String queryName, SqlParameterSource paramSource, ResultSetExtractor<T> rse)
			throws DataAccessException {
		return query(queries.get(queryName),paramSource,rse);
	}

}
