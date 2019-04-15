package com.tirmizee.core.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.Geography;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("GeographyRepository")
public class GeographyRepositoryImpl extends AbstractOracleJdbcRepository<Geography, Integer> implements GeographyRepository {

	public static final RowMapper<Geography> ROW_MAPPER = new RowMapper<Geography>() {
		@Override
		public Geography mapRow(ResultSet rs, int rowNum) throws SQLException {
			Geography geography = new Geography();
			geography.setGeoNameEn(rs.getString(COL_GEO_NAME_EN));
			geography.setGeoNameTh(rs.getString(COL_GEO_NAME_TH));
			geography.setGeoId(rs.getInt(COL_GEO_ID));
			return geography.withPersisted(true);
		}
	};
	
	public static final RowUnmapper<Geography> ROW_UNMAPPER = new RowUnmapper<Geography>() {
		@Override
		public Map<String, Object> mapColumns(Geography param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_GEO_NAME_EN, param.getGeoNameEn());
			map.put(COL_GEO_NAME_TH, param.getGeoNameTh());
			map.put(COL_GEO_ID, param.getGeoId());
			return map;
		}
	};

	public GeographyRepositoryImpl() {
		this(TB_GEOGRAPHY);
	}
	
	public GeographyRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_GEO_ID);
	}

}
