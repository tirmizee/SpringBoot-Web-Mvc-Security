package com.tirmizee.backend.dao;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.tirmizee.backend.api.user.data.UserDetailCriteriaDTO;
import com.tirmizee.backend.api.user.data.UserDetailPageDTO;
import com.tirmizee.backend.api.user.data.UserDetailUpdateDTO;
import com.tirmizee.core.domain.User;
import com.tirmizee.core.domain.UserDetail;
import com.tirmizee.core.repository.DistrictRepository;
import com.tirmizee.core.repository.PostCodeRepository;
import com.tirmizee.core.repository.ProfileRepository;
import com.tirmizee.core.repository.ProvinceRepository;
import com.tirmizee.core.repository.RoleRepository;
import com.tirmizee.core.repository.SubDistrictRepository;
import com.tirmizee.core.repository.UserRepositoryImpl;

@Repository 
public class UserDaoImpl extends UserRepositoryImpl implements UserDao {
	
	public final Logger LOG = Logger.getLogger(UserDaoImpl.class);
	
	@Override
	public User findByUsername(String username) {
		try {
			StringBuilder statemet = new StringBuilder()
				.append("SELECT * FROM ").append(TB_USERS)
				.append(" WHERE ").append(COL_USERNAME).append(" = ? ");
			return getJdbcOps().queryForObject(statemet.toString(), params(username), ROW_MAPPER);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	@Override
	public UserDetailUpdateDTO findDetailByUserId(Long userId) {
		try {
			final MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", userId);
			final StringBuilder statemet = new StringBuilder()
				.append("SELECT ")
				.append(USER_ID).append(" , ")
				.append(USERNAME).append(" , ")
				.append(PASSWORD).append(" , ")
				.append(RoleRepository.ROLE_ID).append(" , ")
				.append(RoleRepository.ROLE_CODE).append(" , ")
				.append(RoleRepository.ROLE_NAME).append(" , ")
				.append(ENABLED).append(" , ")
				.append(ACCOUNTNONLOCKED).append(" , ")
				.append(ACCOUNTNONEXPIRED).append(" , ")
				.append(ACCOUNT_EXPIRED_DATE).append(" , ")
				.append(COL_CREDENTIALSEXPIRED_DATE).append(" , ")
				.append(CREDENTIALSNONEXPIRED).append(" , ")
				.append(FIRST_LOGIN).append(" , ")
				.append(MAX_SESSION).append(" , ")
				.append(ProfileRepository.CITIZEN_ID).append(" , ")
				.append(ProfileRepository.TEL).append(" , ")
				.append(ProfileRepository.FIRST_NAME).append(" , ")
				.append(ProfileRepository.EMAIL).append(" , ")
				.append(ProfileRepository.SUB_DISTRICT_CODE).append(" , ")
				.append(ProfileRepository.LAST_NAME).append(" , ")
				.append(ProvinceRepository.PROVINCE_ID).append(" , ")
				.append(ProvinceRepository.PROVINCE_NAME_TH).append(" , ")
				.append(DistrictRepository.DISTRICT_ID).append(" , ")
				.append(DistrictRepository.DISTRICT_NAME_TH).append(" , ")
				.append(SubDistrictRepository.SUBDISTRICT_ID).append(" , ")
				.append(SubDistrictRepository.SUBDISTRICT_NAME_TH).append(" , ")
				.append(PostCodeRepository.ZIPCODE)
				.append(" FROM ").append(TB_USERS)
				.append(" INNER JOIN ").append(ProfileRepository.TB_PROFILE)
				.append(" ON ").append(PROFILE_ID).append(" = ").append(ProfileRepository.PROFILE_ID)
				.append(" INNER JOIN ").append(RoleRepository.TB_ROLE)
				.append(" ON ").append(FK_ROLE_ID).append(" = ").append(RoleRepository.ROLE_ID)
				.append(" LEFT JOIN ").append(SubDistrictRepository.TB_SUBDISTRICTS)
				.append(" ON ").append(ProfileRepository.SUB_DISTRICT_CODE).append(" = ").append(SubDistrictRepository.SUBDISTRICT_CODE)
				.append(" LEFT JOIN ").append(DistrictRepository.TB_DISTRICTS)
				.append(" ON ").append(SubDistrictRepository.FK_DISTRICT_ID).append(" = ").append(DistrictRepository.DISTRICT_ID)
				.append(" LEFT JOIN ").append(ProvinceRepository.TB_PROVINCES)
				.append(" ON ").append(DistrictRepository.FK_PROVINCE_ID).append(" = ").append(ProvinceRepository.PROVINCE_ID)
				.append(" LEFT JOIN ").append(PostCodeRepository.TB_POSTCODE)
				.append(" ON ").append(SubDistrictRepository.SUBDISTRICT_CODE).append(" = ").append(PostCodeRepository.SUB_DISTRICT_CODE)
				.append(" WHERE ").append(USER_ID).append(" = :userId ");
			return getNamedJdbcOps().queryForObject(statemet.toString(), params, new BeanPropertyRowMapper<>(UserDetailUpdateDTO.class));
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	@Override
	public UserDetail findDetailByUsername(String username) {
		try {
			final MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("username", username);
			final StringBuilder statemet = new StringBuilder()
				.append("SELECT ")
				.append(USERNAME).append(" , ")
				.append(PASSWORD).append(" , ")
				.append(RoleRepository.ROLE_ID).append(" , ")
				.append(RoleRepository.ROLE_CODE).append(" , ")
				.append(RoleRepository.ROLE_NAME).append(" , ")
				.append(ENABLED).append(" , ")
				.append(ACCOUNTNONLOCKED).append(" , ")
				.append(ACCOUNTNONEXPIRED).append(" , ")
				.append(ACCOUNT_EXPIRED_DATE).append(" , ")
				.append(COL_CREDENTIALSEXPIRED_DATE).append(" , ")
				.append(CREDENTIALSNONEXPIRED).append(" , ")
				.append(FIRST_LOGIN).append(" , ")
				.append(MAX_SESSION).append(" , ")
				.append(ProfileRepository.FIRST_NAME).append(" , ")
				.append(ProfileRepository.LAST_NAME)
				.append(" FROM ").append(TB_USERS)
				.append(" INNER JOIN ").append(ProfileRepository.TB_PROFILE)
				.append(" ON ").append(PROFILE_ID).append(" = ").append(ProfileRepository.PROFILE_ID)
				.append(" INNER JOIN ").append(RoleRepository.TB_ROLE)
				.append(" ON ").append(FK_ROLE_ID).append(" = ").append(RoleRepository.ROLE_ID)
				.append(" WHERE ").append(USERNAME).append(" = :username ");
			return getNamedJdbcOps().queryForObject(statemet.toString(), params, new BeanPropertyRowMapper<>(UserDetail.class));
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public Page<UserDetailPageDTO> findPageByCriteria(Pageable pageable, UserDetailCriteriaDTO search) {
		List<Object> params = new LinkedList<>();
		StringBuilder statement = new StringBuilder()
			.append(" SELECT ")
				.append(USER_ID).append(",")
				.append(USERNAME).append(",")
				.append(FIRST_LOGIN).append(",")
				.append(ENABLED).append(",")
				.append(ACCOUNTNONLOCKED).append(",")
				.append(ACCOUNTNONEXPIRED).append(",")
				.append(CREDENTIALSNONEXPIRED).append(",")
				.append(CREDENTIALSEXPIRED_DATE).append(",")
				.append(ProfileRepository.FIRST_NAME).append(",")
				.append(ProfileRepository.LAST_NAME).append(",")
				.append(ProfileRepository.EMAIL).append(",")
				.append(RoleRepository.ROLE_CODE).append(",")
				.append(RoleRepository.ROLE_ID).append(",")
				.append(RoleRepository.ROLE_NAME).append(",")
				.append(RoleRepository.ROLE_DESC)
			.append(" FROM ").append(TB_USERS)
			.append(" INNER JOIN ").append(ProfileRepository.TB_PROFILE)
				.append(" ON ").append(PROFILE_ID).append(" = ").append(ProfileRepository.PROFILE_ID)
			.append(" INNER JOIN ").append(RoleRepository.TB_ROLE)
				.append(" ON ").append(FK_ROLE_ID).append(" = ").append(RoleRepository.ROLE_ID)
			.append(" WHERE ").append(USER_ID).append(" IS NOT NULL");
		
		if (!StringUtils.isBlank(search.getUsername())) {
			statement.append(" AND ").append(USERNAME).append(" LIKE ? ");
			params.add("%" + StringUtils.trimToEmpty(search.getUsername()) + "%");
		}
		
		if (!StringUtils.isBlank(search.getFirstName())) {
			statement.append(" AND ").append(ProfileRepository.FIRST_NAME).append(" LIKE ? ");
			params.add("%" + StringUtils.trimToEmpty(search.getFirstName()) + "%");
		}
		
		if (!StringUtils.isBlank(search.getLastName())) {
			statement.append(" AND ").append(ProfileRepository.LAST_NAME).append(" LIKE ? ");
			params.add("%" + StringUtils.trimToEmpty(search.getLastName()) + "%");
		}
		
		if (!StringUtils.isBlank(search.getEmail())) {
			statement.append(" AND ").append(ProfileRepository.EMAIL).append(" LIKE ? ");
			params.add("%" + StringUtils.trimToEmpty(search.getEmail()) + "%");
		}
		
		if (search.getRoleId() != null) {
			statement.append(" AND ").append(RoleRepository.ROLE_ID).append(" = ? ");
			params.add(search.getRoleId());
		}
		
		String statementPage = getSqlGenerator().selectAll(statement, pageable);
		List<UserDetailPageDTO> content = getJdbcOps().query(
			statementPage.toString(), 
			params.toArray(), 
			new BeanPropertyRowMapper<>(UserDetailPageDTO.class)
		);
		long total = count(statement.toString(), params.toArray());
		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public User findByEmail(String email) {
		try {
			StringBuilder statemet = new StringBuilder()
				.append(" SELECT ").append(TB_USERS).append(".*")
				.append(" FROM ").append(TB_USERS)
				.append(" INNER JOIN ").append(ProfileRepository.TB_PROFILE)
					.append(" ON ").append(PROFILE_ID).append(" = ").append(ProfileRepository.PROFILE_ID)
				.append(" WHERE ").append(ProfileRepository.EMAIL).append(" = ? ");
			return getJdbcOps().queryForObject(statemet.toString(), params(email), ROW_MAPPER);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}

}
