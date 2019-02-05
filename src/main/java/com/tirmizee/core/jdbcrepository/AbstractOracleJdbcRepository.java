package com.tirmizee.core.jdbcrepository;

import java.io.Serializable;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Persistable;
import org.springframework.jdbc.core.RowMapper;

import com.tirmizee.core.jdbcrepository.sql.SqlGenerator;

/**
 * @author Pratya Yeekhaday
 *
 */
public abstract class AbstractOracleJdbcRepository<T extends Persistable<ID>, ID extends Serializable> extends AbstractJdbcRepository<T,ID> {

	public static final Logger LOG = Logger.getLogger(AbstractOracleJdbcRepository.class);
	
	public AbstractOracleJdbcRepository(RowMapper<T> rowMapper, RowUnmapper<T> rowUnmapper, SqlGenerator sqlGenerator,
			TableDescription table) {
		super(rowMapper, rowUnmapper, sqlGenerator, table);
	}

	public AbstractOracleJdbcRepository(RowMapper<T> rowMapper, RowUnmapper<T> rowUnmapper, String tableName,
			String idColumn) {
		super(rowMapper, rowUnmapper, tableName, idColumn);
	}

	public AbstractOracleJdbcRepository(RowMapper<T> rowMapper, RowUnmapper<T> rowUnmapper, String tableName) {
		super(rowMapper, rowUnmapper, tableName);
	}

	public AbstractOracleJdbcRepository(RowMapper<T> rowMapper, RowUnmapper<T> rowUnmapper, TableDescription table) {
		super(rowMapper, rowUnmapper, table);
	}

	public AbstractOracleJdbcRepository(RowMapper<T> rowMapper, String tableName, String idColumn) {
		super(rowMapper, tableName, idColumn);
	}

	public AbstractOracleJdbcRepository(RowMapper<T> rowMapper, String tableName) {
		super(rowMapper, tableName);
	}

	public AbstractOracleJdbcRepository(RowMapper<T> rowMapper, TableDescription table) {
		super(rowMapper, table);
	}
	
	@Override
	@Autowired
	protected void initialDataSource(DataSource dataSource) {
		super.initialDataSource(dataSource);
	}
	
}
