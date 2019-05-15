package com.tirmizee.core.repository;

import com.tirmizee.core.domain.Province;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface ProvinceRepository extends JdbcRepository<Province, Integer> {
	
	public static final String TB_PROVINCES = "PROVINCES";
	public static final String COL_PROVINCE_ID = "PROVINCE_ID";
	public static final String COL_PROVINCE_CODE = "PROVINCE_CODE";
	public static final String COL_PROVINCE_NAME_TH = "PROVINCE_NAME_TH";
	public static final String COL_PROVINCE_NAME_EN = "PROVINCE_NAME_EN";
	public static final String COL_GEO_ID = "GEO_ID";
	public static final String PROVINCE_ID = "PROVINCES.PROVINCE_ID";
	public static final String PROVINCE_CODE = "PROVINCES.PROVINCE_CODE";
	public static final String PROVINCE_NAME_TH = "PROVINCES.PROVINCE_NAME_TH";
	public static final String PROVINCE_NAME_EN = "PROVINCES.PROVINCE_NAME_EN";
	public static final String GEO_ID = "PROVINCES.GEO_ID";

}
