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
public class BankBranchRepositoryImpl extends AbstractOracleJdbcRepository<BankBranch, Integer> implements BankBranchRepository {

	public static final RowMapper<BankBranch> ROW_MAPPER = new BeanPropertyRowMapper<>(BankBranch.class);

	public static final RowUnmapper<BankBranch> ROW_UNMAPPER = new RowUnmapper<BankBranch>() {
		@Override
		public Map<String, Object> mapColumns(BankBranch param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_IS_BANK_BRANCH, param.getIsBankBranch());
			map.put(COL_UPDATED_DATE, param.getUpdatedDate());
			map.put(COL_CREATED_DATE, param.getCreatedDate());
			map.put(COL_BRANCH_NAME_EN, param.getBranchNameEn());
			map.put(COL_BRANCH_NAME, param.getBranchName());
			map.put(COL_BRANCH_CODE, param.getBranchCode());
			map.put(COL_BANK_CODE, param.getBankCode());
			map.put(COL_BRANCH_ID, param.getBranchId());
			return map;
		}
	};

	public BankBranchRepositoryImpl() {
		this(TB_BANK_BRANCH);
	}
	
	public BankBranchRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_BRANCH_ID);
	}

	@Override
	protected <S extends BankBranch> S postCreate(S entity, Number generatedId) {
		entity.setBranchId(generatedId.intValue());
		return entity;
	}
	
}
