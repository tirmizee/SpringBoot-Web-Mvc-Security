package com.tirmizee.core.jdbcrepository.sql;

import static java.lang.String.format;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.springframework.data.domain.Pageable;

import com.tirmizee.core.exception.UnsupportedSQLException;
import com.tirmizee.core.jdbcrepository.TableDescription;


/**
 * @author pratya yeekhaday
 *
 */
public class MYSQLGenerator extends AbstractSqlGenerator {

	public static final String PRODUCT_NAME = "MySQL";
	
	@Override
	public boolean isCompatible(DatabaseMetaData metadata) throws SQLException {
		return PRODUCT_NAME.equalsIgnoreCase(metadata.getDatabaseProductName());
	}
	
	@Override
	public String selectAll(TableDescription table, Pageable page) {
		String statement = selectAll(table, page.getSort());
		return format("%s %s", statement, limit(page));
	}
	
	@Override
	public String selectAll(StringBuilder statement, Pageable page) {
		return selectAll(statement, page.getSort()) + 
			   format(" LIMIT %d OFFSET %d",page.getPageSize(), page.getOffset());
	}
	
	@Override
	@Deprecated
	public String selectAll(StringBuilder statement, TableDescription table, Pageable page) {
		throw new UnsupportedSQLException("Unsupported for MYSQL");
	}
	
	private String limit(Pageable page) {
		return format(" LIMIT %d OFFSET %d ", page.getPageSize(), page.getOffset());
	}

}
