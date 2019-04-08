package com.tirmizee.core.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.PostCode;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("PostCodeRepository")
public class PostCodeRepositoryImpl extends AbstractOracleJdbcRepository<PostCode, Integer> implements PostCodeRepository {
	
	private static final RowMapper<PostCode> ROW_MAPPER = new RowMapper<PostCode>() {
		@Override
		public PostCode mapRow(ResultSet rs, int rowNum) throws SQLException {
			PostCode postcode = new PostCode();
			postcode.setZipcode(rs.getString(COL_ZIPCODE));
			postcode.setSubDistrictCode(rs.getString(COL_SUB_DISTRICT_CODE));
			postcode.setPostCodeId(rs.getInt(COL_POST_CODE_ID));
			return postcode;
		}
	};
	
	private static final RowUnmapper<PostCode> ROW_UNMAPPER = new RowUnmapper<PostCode>() {
		@Override
		public Map<String, Object> mapColumns(PostCode param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_ZIPCODE, param.getZipcode());
			map.put(COL_SUB_DISTRICT_CODE, param.getSubDistrictCode());
			map.put(COL_POST_CODE_ID, param.getPostCodeId());
			return map;
		}
	};

	public PostCodeRepositoryImpl() {
		this(TB_POSTCODE);
	}
	
	public PostCodeRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_POST_CODE_ID);
	}

	@Override
	protected <S extends PostCode> S postCreate(S entity, Number generatedId) {
		entity.setPostCodeId(generatedId.intValue());
		return entity;
	}

}
