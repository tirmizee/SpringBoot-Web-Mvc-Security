package com.tirmizee.core.jdbcrepository.sql;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.springframework.data.domain.Pageable;

import com.tirmizee.core.exception.UnsupportedSQLException;
import com.tirmizee.core.jdbcrepository.TableDescription;

public class MSSQLGenerator extends AbstractSqlGenerator {

	public static final String PRODUCT_NAME = "Microsoft SQL Server";
	
	private static String ROW_NUM_WRAPPER = "SELECT a__.* FROM (SELECT row_number() OVER (ORDER BY %s) AS ROW_NUM,  t__.*  FROM   (%s) t__) a__ WHERE  a__.row_num BETWEEN %s AND %s";
	
	@Override
	public boolean isCompatible(DatabaseMetaData metadata) throws SQLException {
		return PRODUCT_NAME.equalsIgnoreCase(metadata.getDatabaseProductName());
	}

	@Override
	public String selectAll(TableDescription table, Pageable page) {
		return generateSelectAllWithPagination(table, page);
	}
	
	@Override
	@Deprecated
	public String selectAll(StringBuilder statement, Pageable page) {
		throw new UnsupportedSQLException("Unsupported for MSSQL");
	}
	
	@Override
	public String selectAll(StringBuilder statement, TableDescription table, Pageable page) {
		return generateSelectWithPagination(table, statement, page);
	}

	public String generateSelectAllWithPagination(TableDescription table, Pageable page) {
		
		final int beginOffset = beginOffset(page);
		final int endOffset = beginOffset + page.getPageSize() - 1;
		
		String orderByPart = orderByPart(page, table);
		String selectAllPart = selectAll(table);
		
		return String.format(ROW_NUM_WRAPPER, orderByPart, selectAllPart, beginOffset, endOffset);
	}
	
	public String generateSelectWithPagination(TableDescription table, StringBuilder statement ,Pageable page) {
		
		final int beginOffset = beginOffset(page);
		final int endOffset = beginOffset + page.getPageSize() - 1;
		
		String orderByPart = orderByPart(page, table);
		return String.format(ROW_NUM_WRAPPER, orderByPart, statement, beginOffset, endOffset);
	}

	private static int beginOffset(Pageable page){
		return page.getPageNumber() * page.getPageSize() + 1;
	}
	
	private static String orderByPart(Pageable page ,TableDescription table){
		String orderBy =  table.getIdColumns().get(0);
		if (page.getSort() != null) {
			orderBy = page.getSort().toString().replace(":", "");
		}
		return orderBy;
	}
	
}
