package com.tirmizee.core.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.ValidateMessage;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("ValidateMessageRepository")
public class ValidateMessageRepositoryImpl extends AbstractOracleJdbcRepository<ValidateMessage, Integer> implements ValidateMessageRepository {

	public static final RowMapper<ValidateMessage> ROW_MAPPER = new RowMapper<ValidateMessage>() {
		@Override
		public ValidateMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
			ValidateMessage validateMessage = new ValidateMessage();
			validateMessage.setMsgId(rs.getInt(COL_MSG_ID));
			validateMessage.setMsgCode(rs.getString(COL_MSG_CODE));
			validateMessage.setMsgDesc(rs.getString(COL_MSG_DESC));
			validateMessage.setCreateDate(rs.getTimestamp(COL_CREATE_DATE));
			validateMessage.setUpdateDate(rs.getTimestamp(COL_UPDATE_DATE));
			return validateMessage;
		}
	};
	
	public static final RowUnmapper<ValidateMessage> ROW_UNMAPPER = new RowUnmapper<ValidateMessage>() {
		@Override
		public Map<String, Object> mapColumns(ValidateMessage param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_UPDATE_DATE, param.getUpdateDate());
			map.put(COL_CREATE_DATE, param.getCreateDate());
			map.put(COL_MSG_DESC, param.getMsgDesc());
			map.put(COL_MSG_CODE, param.getMsgCode());
			map.put(COL_MSG_ID, param.getMsgId());
			return map;
		}
	};

	public ValidateMessageRepositoryImpl() {
		this(TB_VALIDATE_MESSAGE);
	}
	
	public ValidateMessageRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_MSG_ID);
	}

	@Override
	protected <S extends ValidateMessage> S postUpdate(S entity) {
		entity.setPersisted(true);
		return entity;
	}

	@Override
	protected <S extends ValidateMessage> S postCreate(S entity, Number generatedId) {
		entity.setPersisted(true);
		return entity;
	}

}
