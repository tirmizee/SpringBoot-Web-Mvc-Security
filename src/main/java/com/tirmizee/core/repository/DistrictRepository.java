package com.tirmizee.core.repository;

import com.tirmizee.core.domain.District;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface DistrictRepository extends JdbcRepository<District, Integer> {

	public static final String TB_DISTRICTS = "DISTRICTS";
	public static final String COL_PROVINCE_CODE = "PROVINCE_CODE";
	public static final String COL_DISTRICT_NAME_EN = "DISTRICT_NAME_EN";
	public static final String COL_DISTRICT_NAME_TH = "DISTRICT_NAME_TH";
	public static final String COL_DISTRICT_CODE = "DISTRICT_CODE";
	public static final String COL_DISTRICT_ID = "DISTRICT_ID";
	public static final String PROVINCE_CODE = "DISTRICTS.PROVINCE_CODE";
	public static final String DISTRICT_NAME_EN = "DISTRICTS.DISTRICT_NAME_EN";
	public static final String DISTRICT_NAME_TH = "DISTRICTS.DISTRICT_NAME_TH";
	public static final String DISTRICT_CODE = "DISTRICTS.DISTRICT_CODE";
	public static final String DISTRICT_ID = "DISTRICTS.DISTRICT_ID";
	
}
