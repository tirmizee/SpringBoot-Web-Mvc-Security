package com.tirmizee.backend.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.LogPassword;
import com.tirmizee.core.repository.LogPasswordRepositoryImpl;

@Repository
public class LogPasswordDaoImpl extends LogPasswordRepositoryImpl implements LogPasswordDao {

	@Override
	public List<LogPassword> findByUsername(String username) {
		StringBuilder statement = new StringBuilder()
			.append(" SELECT * FROM ").append(TB_LOG_PASSWORD)
			.append(" WHERE ").append(COL_USERNAME).append(" = ? ");
		return getJdbcOps().query(statement.toString(), params(username), ROW_MAPPER);
	}

	@Override
	public List<LogPassword> findDescByUsername(String username, int limit) {
		
		StringBuilder subStatement = new StringBuilder()
			.append(" SELECT * FROM ").append(TB_LOG_PASSWORD)
			.append(" WHERE ").append(COL_USERNAME).append(" = ? ")
			.append(" ORDER BY ").append(COL_CREATE_DATE).append(" DESC ");
		String statement = String.format("SELECT * FROM (%s) WHERE ROWNUM <= ?", subStatement.toString());
		return getJdbcOps().query(statement, params(username, limit), ROW_MAPPER);
	}

}
