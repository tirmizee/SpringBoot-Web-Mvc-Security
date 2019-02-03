package com.tirmizee.core.jdbcrepository.sql;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.WeakHashMap;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessResourceFailureException;

public class SqlGeneratorFactory {
	
	public static final Logger LOG = Logger.getLogger(SqlGeneratorFactory.class);
	
	private static final SqlGeneratorFactory INSTANCE = new SqlGeneratorFactory(true);
	
	private final Deque<SqlGenerator> generators = new ArrayDeque<>();
	
	private final Map<DataSource, SqlGenerator> cache = new WeakHashMap<>(2, 1.0f);
	 
	public SqlGeneratorFactory(boolean registerDefault) {
	    if (registerDefault) {
	    	registerGenerator(new Oracle9SqlGenerator());
	        registerGenerator(new MYSQLGenerator());
	        registerGenerator(new MSSQLGenerator());
	    }
	}
	
	public static SqlGeneratorFactory getInstance() {
        return INSTANCE;
    }
	
	public SqlGenerator getGenerator(DataSource dataSource) {

        if (cache.containsKey(dataSource)) {
            return cache.get(dataSource);
        }

        DatabaseMetaData metaData;
        
        try {
            metaData = dataSource.getConnection().getMetaData();
        } catch (SQLException ex) {
            throw new DataAccessResourceFailureException(
                "Failed to retrieve database metadata", ex);
        }

        for (SqlGenerator generator : generators) {
            try {
                if (generator.isCompatible(metaData)) {
                    cache.put(dataSource, generator);
                    return generator;
                }
            } catch (SQLException ex) {
                LOG.debug("Exception occurred when invoking isCompatible() on {}", ex);
            }
        }
        // This should not happen, because registry should always contain one
        // "default" generator that returns true for every DatabaseMetaData.
        throw new IllegalStateException("No compatible SQL Generator found.");
    }

	public void registerGenerator(SqlGenerator sqlGenerator) {
        generators.push(sqlGenerator);
    }
	
	public void clear() {
		cache.clear();
        generators.clear();
    }
	
}
