package com.tirmizee.core.jdbcrepository.sql;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.tirmizee.core.jdbcrepository.TableDescription;

public class Oracle9SqlGenerator extends AbstractSqlGenerator {

	private static final String PAGE_WRAPPER = "SELECT t2__.* FROM ( SELECT t1__.*, ROWNUM as rn__ FROM ( %s ) t1__ ) t2__ WHERE t2__.rn__ > %d AND ROWNUM <= %d";
	
	@Override
	public boolean isCompatible(DatabaseMetaData metadata) throws SQLException {
		 return "Oracle".equals(metadata.getDatabaseProductName());
	}

	@Override
	public String selectAll(StringBuilder statement, Pageable page) {
		 return String.format(PAGE_WRAPPER, 
				 selectAll(statement, page.getSort()), 
				 page.getOffset(),
				 page.getPageSize());
	}

	@Override
	public String selectAll(TableDescription table, Pageable page) {
		Sort sort = page.getSort() != null ? page.getSort() : sortById(table);
        return String.format(PAGE_WRAPPER, 
        		selectAll(table, sort),
        		page.getOffset(),
        		page.getPageSize());
	}

	@Override
	public String selectAll(StringBuilder statement, TableDescription table, Pageable page) {
		Sort sort = page.getSort() != null ? page.getSort() : sortById(table);
		return String.format(PAGE_WRAPPER, 
				selectAll(statement, sort),
				page.getOffset(), 
				page.getPageSize());
	}

}
