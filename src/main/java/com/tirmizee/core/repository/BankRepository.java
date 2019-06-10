package com.tirmizee.core.repository;

import com.tirmizee.core.domain.Bank;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface BankRepository extends JdbcRepository<Bank, Long> {

	public static final String TB_BANK = "BANK";
	public static final String COL_UPDATE_DATE = "UPDATE_DATE";
	public static final String COL_CREATE_DATE = "CREATE_DATE";
	public static final String COL_BANK_DESC = "BANK_DESC";
	public static final String COL_BANK_NAME = "BANK_NAME";
	public static final String COL_BANK_CODE = "BANK_CODE";
	public static final String COL_BANK_ID = "BANK_ID";
	public static final String UPDATE_DATE = "BANK.UPDATE_DATE";
	public static final String CREATE_DATE = "BANK.CREATE_DATE";
	public static final String BANK_DESC = "BANK.BANK_DESC";
	public static final String BANK_NAME = "BANK.BANK_NAME";
	public static final String BANK_CODE = "BANK.BANK_CODE";
	public static final String BANK_ID = "BANK.BANK_ID";
	
}
