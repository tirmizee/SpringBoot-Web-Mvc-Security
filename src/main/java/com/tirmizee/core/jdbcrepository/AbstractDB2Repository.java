package com.tirmizee.core.jdbcrepository;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.data.domain.Persistable;
import org.springframework.jdbc.core.RowMapper;

import com.tirmizee.core.jdbcrepository.sql.SqlGenerator;

public abstract class AbstractDB2Repository<T extends Persistable<ID>, ID extends Serializable> extends AbstractJdbcRepository<T,ID> {
	
	public AbstractDB2Repository(RowMapper<T> rowMapper, RowUnmapper<T> rowUnmapper, SqlGenerator sqlGenerator,
			TableDescription table) {
		super(rowMapper, rowUnmapper, sqlGenerator, table);
	}

	public AbstractDB2Repository(RowMapper<T> rowMapper, RowUnmapper<T> rowUnmapper, String tableName,
			String idColumn) {
		super(rowMapper, rowUnmapper, tableName, idColumn);
	}

	public AbstractDB2Repository(RowMapper<T> rowMapper, RowUnmapper<T> rowUnmapper, String tableName) {
		super(rowMapper, rowUnmapper, tableName);
	}

	public AbstractDB2Repository(RowMapper<T> rowMapper, RowUnmapper<T> rowUnmapper, TableDescription table) {
		super(rowMapper, rowUnmapper, table);
	}

	public AbstractDB2Repository(RowMapper<T> rowMapper, String tableName, String idColumn) {
		super(rowMapper, tableName, idColumn);
	}

	public AbstractDB2Repository(RowMapper<T> rowMapper, String tableName) {
		super(rowMapper, tableName);
	}

	public AbstractDB2Repository(RowMapper<T> rowMapper, TableDescription table) {
		super(rowMapper, table);
	}
	
	@Override
	protected void initialDataSource(DataSource dataSource) {
		super.initialDataSource(dataSource);
	}
	
}
