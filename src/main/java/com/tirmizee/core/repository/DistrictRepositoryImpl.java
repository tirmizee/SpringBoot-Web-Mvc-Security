package com.tirmizee.core.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.District;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("DistrictsRepository")
public class DistrictRepositoryImpl extends AbstractOracleJdbcRepository<District, Integer> implements DistrictRepository {

	public static final RowMapper<District> ROW_MAPPER = BeanPropertyRowMapper.newInstance(District.class);
	
	public static final RowUnmapper<District> ROW_UNMAPPER = new RowUnmapper<District>() {
		@Override
		public Map<String, Object> mapColumns(District param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_DISTRICT_NAME_EN, param.getDistrictNameEn());
			map.put(COL_DISTRICT_NAME_TH, param.getDistrictNameTh());
			map.put(COL_DISTRICT_CODE, param.getDistrictCode());
			map.put(COL_PROVINCE_CODE, param.getProvincecCode());
			map.put(COL_DISTRICT_ID, param.getDistrictId());
			return map;
		}
	};

	public DistrictRepositoryImpl() {
		this(TB_DISTRICTS);
	}
	
	public DistrictRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_DISTRICT_ID);
	}

	@Override
	protected <S extends District> S postCreate(S entity, Number generatedId) {
		entity.setDistrictId(generatedId.intValue());
		return entity;
	}

}
