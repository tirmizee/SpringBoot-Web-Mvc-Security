package com.tirmizee.core.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.SubDistrict;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("SubDistrictRepository")
public class SubDistrictRepositoryImpl extends AbstractOracleJdbcRepository<SubDistrict, Integer> implements SubDistrictRepository {

	public static final RowMapper<SubDistrict> ROW_MAPPER = new BeanPropertyRowMapper<>(SubDistrict.class);
	
	public static final RowUnmapper<SubDistrict> ROW_UNMAPPER = new RowUnmapper<SubDistrict>() {
		@Override
		public Map<String, Object> mapColumns(SubDistrict param) {
			Map<String, Object> map =  new LinkedHashMap<>();
			map.put(COL_SUBDISTRICT_NAME_EN, param.getSubdistrictNameEn());
			map.put(COL_SUBDISTRICT_NAME_TH, param.getSubdistrictNameTh());
			map.put(COL_SUBDISTRICT_CODE, param.getSubdistrictCode());
			map.put(COL_FK_DISTRICT_ID, param.getFkDistrictId());
			map.put(COL_SUBDISTRICT_ID, param.getSubdistrictId());
			return map;
		}
	};

	public SubDistrictRepositoryImpl() {
		this(TB_SUBDISTRICTS);
	}
	
	public SubDistrictRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_SUBDISTRICT_ID);
	}

	@Override
	protected <S extends SubDistrict> S postCreate(S entity, Number generatedId) {
		entity.setSubdistrictId(generatedId.intValue());
		return entity;
	}

}
