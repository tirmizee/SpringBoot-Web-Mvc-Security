package com.tirmizee.core.repository;

import com.tirmizee.core.domain.ValidateMessage;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface ValidateMessageRepository extends JdbcRepository<ValidateMessage, Integer> {
	
	public static final String TB_VALIDATE_MESSAGE = "VALIDATE_MESSAGE";
	public static final String COL_UPDATE_DATE = "UPDATE_DATE";
	public static final String COL_CREATE_DATE = "CREATE_DATE";
	public static final String COL_MSG_DESC = "MSG_DESC";
	public static final String COL_MSG_CODE = "MSG_CODE";
	public static final String COL_MSG_ID = "MSG_ID";
	public static final String SQL_UPDATE_DATE = "VALIDATE_MESSAGE.UPDATE_DATE";
	public static final String SQL_CREATE_DATE = "VALIDATE_MESSAGE.CREATE_DATE";
	public static final String SQL_MSG_DESC = "VALIDATE_MESSAGE.MSG_DESC";
	public static final String SQL_MSG_CODE = "VALIDATE_MESSAGE.MSG_CODE";
	public static final String SQL_MSG_ID = "VALIDATE_MESSAGE.MSG_ID";
	
}
