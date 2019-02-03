package com.tirmizee.core.jdbcrepository.sql;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.tirmizee.core.jdbcrepository.TableDescription;

public interface SqlGenerator {
	
    public boolean isCompatible(DatabaseMetaData metadata) throws SQLException;
	
	String count(String table);
	
	String count(TableDescription table);

    String deleteAll(TableDescription table);

    String deleteById(TableDescription table);

    String deleteByIds(TableDescription table, int idsCount);

    String existsById(TableDescription table);

    String insert(TableDescription table, Map<String, Object> columns);

    String selectAll(TableDescription table);
    
    String selectAll(StringBuilder statement, Sort sort);
    
    String selectAll(StringBuilder statement, Pageable page);
    
    String selectAll(StringBuilder statement, TableDescription table, Pageable page);
    
    String selectAll(TableDescription table, Pageable page);

    String selectAll(TableDescription table, Sort sort);

    String selectById(TableDescription table);

    String selectByIds(TableDescription table, int idsCount);

    String update(TableDescription table, Map<String, Object> columns);

}
