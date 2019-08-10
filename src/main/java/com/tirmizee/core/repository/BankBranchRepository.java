package com.tirmizee.core.repository;

import com.tirmizee.core.domain.BankBranch;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface BankBranchRepository extends JdbcRepository<BankBranch, Integer> {
	
	public static final String TB_BANK_BRANCH = "BANK_BRANCH";
	public static final String COL_IS_BANK_BRANCH = "IS_BANK_BRANCH";
	public static final String COL_UPDATED_DATE = "UPDATED_DATE";
	public static final String COL_CREATED_DATE = "CREATED_DATE";
	public static final String COL_BRANCH_NAME_EN = "BRANCH_NAME_EN";
	public static final String COL_BRANCH_NAME = "BRANCH_NAME";
	public static final String COL_BRANCH_CODE = "BRANCH_CODE";
	public static final String COL_BANK_CODE = "BANK_CODE";
	public static final String COL_BRANCH_ID = "BRANCH_ID";
	public static final String SQL_IS_BANK_BRANCH = "BANK_BRANCH.IS_BANK_BRANCH";
	public static final String SQL_UPDATED_DATE = "BANK_BRANCH.UPDATED_DATE";
	public static final String SQL_CREATED_DATE = "BANK_BRANCH.CREATED_DATE";
	public static final String SQL_BRANCH_NAME_EN = "BANK_BRANCH.BRANCH_NAME_EN";
	public static final String SQL_BRANCH_NAME = "BANK_BRANCH.BRANCH_NAME";
	public static final String SQL_BRANCH_CODE = "BANK_BRANCH.BRANCH_CODE";
	public static final String SQL_BANK_CODE = "BANK_BRANCH.BANK_CODE";
	public static final String SQL_BRANCH_ID = "BANK_BRANCH.BRANCH_ID";

}
