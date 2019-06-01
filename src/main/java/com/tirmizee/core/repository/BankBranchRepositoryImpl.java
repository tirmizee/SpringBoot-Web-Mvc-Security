package com.tirmizee.core.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.BankBranch;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("BankBranchRepository")
public class BankBranchRepositoryImpl extends AbstractOracleJdbcRepository<BankBranch, Long> implements BankBranchRepository {

	public static final RowMapper<BankBranch> ROW_MAPPER = new BeanPropertyRowMapper<>(BankBranch.class);

	public static final RowUnmapper<BankBranch> ROW_UNMAPPER = new RowUnmapper<BankBranch>() {
		@Override
		public Map<String, Object> mapColumns(BankBranch param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_UPDATE_DATE, param.getUpdateDate());
			map.put(COL_CREATE_DATE, param.getCreateDate());
			map.put(COL_BANK_CODE, param.getBankCode());
			map.put(COL_BANK_BRANCH_DESC, param.getBankBranchDesc());
			map.put(COL_BANK_BRANCH_NAME, param.getBankBranchName());
			map.put(COL_BANK_BRANCH_CODE, param.getBankBranchCode());
			map.put(COL_BANK_BRANCH_ID, param.getBankBranchId());
			return map;
		}
	};

	public BankBranchRepositoryImpl() {
		this(TB_BANK_BRANCH);
	}
	
	public BankBranchRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_BANK_BRANCH_ID);
	}

	@Override
	protected <S extends BankBranch> S postCreate(S entity, Number generatedId) {
		entity.setBankBranchId(generatedId.longValue());
		return entity;
	}
	
}
