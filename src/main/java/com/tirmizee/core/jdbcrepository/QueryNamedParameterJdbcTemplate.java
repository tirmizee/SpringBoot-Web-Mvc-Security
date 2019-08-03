package com.tirmizee.core.jdbcrepository;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

/**
 * @author Pratya Yeekhaday
 *
 */
@Component
public class QueryNamedParameterJdbcTemplate extends NamedParameterJdbcTemplate implements QueryNamedParameterJdbcOperations {

	private Map<String, String> queries;
	
	@Autowired
	public QueryNamedParameterJdbcTemplate(DataSource dataSource, Map<String, String> queries) {
		super(dataSource);
		this.queries = queries;
	}

	@Override
	public <T> T queryNamedForObject(String queryName, SqlParameterSource paramSource, Class<T> mappedClass)
			throws DataAccessException {
		return queryForObject(queries.get(queryName), paramSource, BeanPropertyRowMapper.newInstance(mappedClass));
	}

	@Override
	public <T> T queryNamedForObject(String queryName, SqlParameterSource paramSource, RowMapper<T> rowMapper)
			throws DataAccessException {
		return queryForObject(queries.get(queryName), paramSource,rowMapper);
	}

	
}
