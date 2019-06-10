package com.tirmizee.core.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.Bank;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("BankRepository")
public class BankRepositoryImpl extends AbstractOracleJdbcRepository<Bank, Long> implements BankRepository {

	public static final RowMapper<Bank> ROW_MAPPER = new BeanPropertyRowMapper<>(Bank.class);
	
	public static final RowUnmapper<Bank> ROW_UNMAPPER = new RowUnmapper<Bank>() {
		@Override
		public Map<String, Object> mapColumns(Bank param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_UPDATE_DATE, param.getUpdateDate());
			map.put(COL_CREATE_DATE, param.getCreateDate());
			map.put(COL_BANK_DESC, param.getBankDesc());
			map.put(COL_BANK_NAME, param.getBankName());
			map.put(COL_BANK_CODE, param.getBankCode());
			map.put(COL_BANK_ID, param.getBankId());
			return map;
		}
	};

	public BankRepositoryImpl() {
		this(TB_BANK);
	}
	
	public BankRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_BANK_ID);
	}

	@Override
	protected <S extends Bank> S postCreate(S entity, Number generatedId) {
		entity.setBankId(generatedId.longValue());
		return entity;
	}
	
}
