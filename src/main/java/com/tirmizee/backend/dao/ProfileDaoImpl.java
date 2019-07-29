package com.tirmizee.backend.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.Profile;
import com.tirmizee.core.repository.ProfileRepositoryImpl;

@Repository
public class ProfileDaoImpl extends ProfileRepositoryImpl implements ProfileDao {

	@Autowired
	private Map<String, String> queries;
	
	@Override
	public Profile findByUserId(Long uid) {
		try {
			String statement = queries.get("GET.PROFILE.BY.USERID");
			return getJdbcOps().queryForObject(statement.toString(), params(uid),ROW_MAPPER);
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
		
	}

}
