package com.tirmizee.backend.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.Profile;
import com.tirmizee.core.repository.ProfileRepositoryImpl;
import com.tirmizee.core.repository.UserRepository;

@Repository
public class ProfileDaoImpl extends ProfileRepositoryImpl implements ProfileDao {

	@Override
	public Profile findByUserId(Long uid) {
		try {
			StringBuilder statement = new StringBuilder()
				.append(" SELECT ").append(TB_PROFILE).append(".* ")
				.append(" FROM ").append(TB_PROFILE)
				.append(" INNER JOIN ").append(UserRepository.TB_USERS)
				.append(" ON ").append(PROFILE_ID)
				.append(" = ").append(UserRepository.PROFILE_ID)
				.append(" WHERE ").append(UserRepository.USER_ID)
				.append(" = ? ");
			return getJdbcOps().queryForObject(statement.toString(), params(uid),ROW_MAPPER);
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
		
	}

}
