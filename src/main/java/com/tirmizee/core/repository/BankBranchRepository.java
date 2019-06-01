package com.tirmizee.core.repository;

import com.tirmizee.core.domain.BankBranch;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface BankBranchRepository extends JdbcRepository<BankBranch, Long> {

	public static final String TB_BANK_BRANCH = "BANK_BRANCH";
	public static final String COL_UPDATE_DATE = "UPDATE_DATE";
	public static final String COL_CREATE_DATE = "CREATE_DATE";
	public static final String COL_BANK_CODE = "BANK_CODE";
	public static final String COL_BANK_BRANCH_DESC = "BANK_BRANCH_DESC";
	public static final String COL_BANK_BRANCH_NAME = "BANK_BRANCH_NAME";
	public static final String COL_BANK_BRANCH_CODE = "BANK_BRANCH_CODE";
	public static final String COL_BANK_BRANCH_ID = "BANK_BRANCH_ID";
	public static final String UPDATE_DATE = "BANK_BRANCH.UPDATE_DATE";
	public static final String CREATE_DATE = "BANK_BRANCH.CREATE_DATE";
	public static final String BANK_CODE = "BANK_BRANCH.BANK_CODE";
	public static final String BANK_BRANCH_DESC = "BANK_BRANCH.BANK_BRANCH_DESC";
	public static final String BANK_BRANCH_NAME = "BANK_BRANCH.BANK_BRANCH_NAME";
	public static final String BANK_BRANCH_CODE = "BANK_BRANCH.BANK_BRANCH_CODE";
	public static final String BANK_BRANCH_ID = "BANK_BRANCH.BANK_BRANCH_ID";

}
