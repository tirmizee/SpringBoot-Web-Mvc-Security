package com.tirmizee.core.jdbcrepository;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.tirmizee.core.jdbcrepository.sql.SqlGenerator;
import com.tirmizee.core.jdbcrepository.sql.SqlGeneratorFactory;

/**
 * @author Pratya Yeekhaday
 * 
 *  
 */
@Component
public class NamedQueryJdbcTemplate extends NamedParameterJdbcTemplate implements NamedQueryJdbcOperations {

	private SqlGenerator sqlGenerator;
	
	private Map<String, String> queries;
	
	@Autowired
	public NamedQueryJdbcTemplate(DataSource dataSource, Map<String, String> queries) {
		super(dataSource);
		this.queries = queries;
		sqlGenerator = SqlGeneratorFactory.getInstance().getGenerator(dataSource);
	}
	
	@Override 
	public String getQuery(String queryName) {
		return queries.get(queryName);
	}
	
	@Override
	public <T> T namedQueryForObject(String queryName, Map<String, ?> paramMap, Class<T> mappedClass)
			throws DataAccessException {
		return queryForObject(queries.get(queryName), paramMap, mappedClass);
	}

	@Override
	public <T> T namedQueryForObject(String queryName, Map<String, ?> paramMap, RowMapper<T> rowMapper)
			throws DataAccessException {
		return queryForObject(queries.get(queryName), paramMap, rowMapper);
	}
	
	@Override
	public <T> T namedQueryForObject(String queryName, SqlParameterSource paramSource, Class<T> mappedClass)
			throws DataAccessException {
		return queryForObject(queries.get(queryName), paramSource, BeanPropertyRowMapper.newInstance(mappedClass));
	}

	@Override
	public <T> T namedQueryForObject(String queryName, SqlParameterSource paramSource, RowMapper<T> rowMapper)
			throws DataAccessException {
		return queryForObject(queries.get(queryName), paramSource,rowMapper);
	}

	@Override
	public <T> List<T> namedQuery(String queryName, SqlParameterSource paramSource, RowMapper<T> rowMapper)
			throws DataAccessException {
		return query(queries.get(queryName), paramSource, rowMapper);
	}

	@Override
	public <T> List<T> namedQuery(String queryName, RowMapper<T> rowMapper) throws DataAccessException {
		return query(queries.get(queryName), rowMapper);
	}

	@Override
	public <T> T namedQuery(String queryName, SqlParameterSource paramSource, ResultSetExtractor<T> rse)
			throws DataAccessException {
		return query(queries.get(queryName),paramSource,rse);
	}

	@Override
	public <T> List<T> namedQuery(String queryName, SqlParameterSource paramSource, Class<T> mappedClass)
			throws DataAccessException {
		return query(queries.get(queryName), paramSource, BeanPropertyRowMapper.newInstance(mappedClass));
	}

	@Override
	public <T> List<T> namedQuery(String queryName, Map<String, ?> paramMap, RowMapper<T> rowMapper)
			throws DataAccessException {
		return namedQuery(queries.get(queryName), paramMap, rowMapper);
	}

	@Override
	public <T> List<T> namedQuery(String queryName, Map<String, ?> paramMap, Class<T> mappedClass)
			throws DataAccessException {
		return query(queries.get(queryName), paramMap, BeanPropertyRowMapper.newInstance(mappedClass));
	}
	
	@Override
	public <T> Page<T> namedQueryForPage(String queryName, Pageable pageable, Map<String, ?> paramMap, Class<T> mappedClass)
			throws DataAccessException {
		StringBuilder statement = new StringBuilder(queries.get(queryName));
		List<T> content = query(sqlGenerator.selectAll(statement, pageable), paramMap, BeanPropertyRowMapper.newInstance(mappedClass));
		Long total = count(statement.toString(), paramMap);
		return new PageImpl<>(content, pageable, total);
	}
	
	@Override
	public <T> Page<T> namedQueryForPage(String queryName, Pageable pageable, SqlParameterSource paramSource, 
			Class<T> mappedClass) throws DataAccessException {
		StringBuilder statement = new StringBuilder(queries.get(queryName));
		List<T> content = query(sqlGenerator.selectAll(statement, pageable), paramSource, BeanPropertyRowMapper.newInstance(mappedClass));
		Long total = count(statement.toString(), paramSource);
		return new PageImpl<>(content, pageable, total);
	}
	
	@Override
	public <T> Page<T> namedQueryForPage(String queryName, Pageable pageable, SqlParameterSource paramSource,
			RowMapper<T> rowMapper) throws DataAccessException {
		StringBuilder statement = new StringBuilder(queries.get(queryName));
		List<T> content = query(sqlGenerator.selectAll(statement, pageable), paramSource, rowMapper);
		Long total = count(statement.toString(), paramSource);
		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public <T> Page<T> queryForPage(String query, Pageable pageable, Object[] args, Class<T> mappedClass)
			throws DataAccessException {
		StringBuilder statement = new StringBuilder(query);
		List<T> content = getJdbcOperations().query(sqlGenerator.selectAll(statement, pageable), args, BeanPropertyRowMapper.newInstance(mappedClass));
		Long total = count(statement.toString(), args);
		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public <T> Page<T> queryForPage(String query, Pageable pageable, Map<String, ?> paramMap, Class<T> mappedClass)
			throws DataAccessException {
		StringBuilder statement = new StringBuilder(query);
		List<T> content = query(sqlGenerator.selectAll(statement, pageable), paramMap, BeanPropertyRowMapper.newInstance(mappedClass));
		Long total = count(statement.toString(), paramMap);
		return new PageImpl<>(content, pageable, total);
	}
	
	@Override
	public <T> Page<T> queryForPage(String query, Pageable pageable, Map<String, ?> paramMap, RowMapper<T> rowMapper)
			throws DataAccessException {
		StringBuilder statement = new StringBuilder(query);
		List<T> content = query(sqlGenerator.selectAll(statement, pageable), paramMap, rowMapper);
		Long total = count(statement.toString(), paramMap);
		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public <T> Page<T> queryForPage(String query, Pageable pageable, SqlParameterSource paramSource,
			Class<T> mappedClass) throws DataAccessException {
		StringBuilder statement = new StringBuilder(query);
		List<T> content = query(sqlGenerator.selectAll(statement, pageable), paramSource, BeanPropertyRowMapper.newInstance(mappedClass));
		Long total = count(statement.toString(), paramSource);
		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public <T> Page<T> queryForPage(String query, Pageable pageable, SqlParameterSource paramSource,
			RowMapper<T> rowMapper) throws DataAccessException {
		StringBuilder statement = new StringBuilder(query);
		List<T> content = query(sqlGenerator.selectAll(statement, pageable), paramSource,rowMapper);
		Long total = count(statement.toString(), paramSource);
		return new PageImpl<>(content, pageable, total);
	}
	
	private Long count(String statement, Map<String, ?> paramMap) {
		return queryForObject(sqlGenerator.count(statement), paramMap, Long.class);
	}

	private Long count(String statement, SqlParameterSource paramSource) {
		return queryForObject(sqlGenerator.count(statement), paramSource, Long.class);
	}
	
	private Long count(String statement, Object[] args) {
		return getJdbcOperations().queryForObject(sqlGenerator.count(statement), Long.class, args);
	}
	
	public void addQuery(Map<String, String> mapQuery) {
		queries.putAll(mapQuery);
	}

}
