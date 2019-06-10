package com.tirmizee.core.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.Village;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("VillageRepository")
public class VillageRepositoryImpl extends AbstractOracleJdbcRepository<Village,String> implements VillageRepository {

	public final static RowMapper<Village> ROW_MAPPER = new RowMapper<Village>() {
		@Override
		public Village mapRow(ResultSet rs, int rowNum) throws SQLException {
			Village village = new Village();
			village.setSubDistrictCode(rs.getString(COL_SUB_DISTRICT_CODE));
			village.setVillageName(rs.getString(COL_VILLAGE_NAME));
			village.setVillageNo(rs.getString(COL_VILLAGE_NO));
			village.setVillageCode(rs.getString(COL_VILLAGE_CODE));
			return village.withPersisted(true);
		}
	};
	
	public final static RowUnmapper<Village> ROW_UNMAPPER = new RowUnmapper<Village>() {
		@Override
		public Map<String, Object> mapColumns(Village param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_SUB_DISTRICT_CODE, param.getSubDistrictCode());
			map.put(COL_VILLAGE_NAME, param.getVillageName());
			map.put(COL_VILLAGE_NO, param.getVillageNo());
			map.put(COL_VILLAGE_CODE, param.getVillageCode());
			return map;
		}
	};

	public VillageRepositoryImpl() {
		this(TB_VILLAGES);
	}
	
	public VillageRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_VILLAGE_CODE);
	}

	@Override
	protected <S extends Village> S postUpdate(S entity) {
		entity.withPersisted(true);
		return entity;
	}

	@Override
	protected <S extends Village> S postCreate(S entity, Number generatedId) {
		entity.withPersisted(true);
		return entity;
	}

}
