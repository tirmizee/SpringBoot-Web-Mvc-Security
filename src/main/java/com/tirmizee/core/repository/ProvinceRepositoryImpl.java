package com.tirmizee.core.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.Province;
import com.tirmizee.core.jdbcrepository.AbstractOracleJdbcRepository;
import com.tirmizee.core.jdbcrepository.RowUnmapper;

@Repository("ProvinceRepository")
public class ProvinceRepositoryImpl extends AbstractOracleJdbcRepository<Province, Integer> implements ProvinceRepository {

	public static final RowMapper<Province> ROW_MAPPER = new BeanPropertyRowMapper<>(Province.class);
	
	public static final RowUnmapper<Province> ROW_UNMAPPER = new RowUnmapper<Province>() {
		@Override
		public Map<String, Object> mapColumns(Province param) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put(COL_PROVINCE_ID, param.getProvinceId());
			map.put(COL_PROVINCEC_CODE, param.getProvincecCode());
			map.put(COL_PROVINCE_NAME_TH, param.getProvinceNameTh());
			map.put(COL_PROVINCE_NAME_EN, param.getProvinceNameEn());
			map.put(COL_GEO_ID, param.getGeoId());
			return map;
		}
	};

	public ProvinceRepositoryImpl() {
		this(TB_PROVINCES);
	}
	
	public ProvinceRepositoryImpl(String tableName) {
		super(ROW_MAPPER, ROW_UNMAPPER, tableName, COL_PROVINCE_ID);
	}

	@Override
	protected <S extends Province> S postCreate(S entity, Number generatedId) {
		entity.setProvinceId(generatedId.intValue());
		return entity;
	}
	
}
