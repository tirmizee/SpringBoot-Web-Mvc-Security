package com.tirmizee.core.repository;

import com.tirmizee.core.domain.SubDistrict;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface SubDistrictRepository extends JdbcRepository<SubDistrict, Integer> {

	public static final String TB_SUBDISTRICTS = "SUBDISTRICTS";
	public static final String COL_SUBDISTRICT_NAME_EN = "SUBDISTRICT_NAME_EN";
	public static final String COL_SUBDISTRICT_NAME_TH = "SUBDISTRICT_NAME_TH";
	public static final String COL_SUBDISTRICT_CODE = "SUBDISTRICT_CODE";
	public static final String COL_SUBDISTRICT_ID = "SUBDISTRICT_ID";
	public static final String COL_DISTRICT_CODE = "DISTRICT_CODE";
	public static final String SUBDISTRICT_NAME_EN = "SUBDISTRICTS.SUBDISTRICT_NAME_EN";
	public static final String SUBDISTRICT_NAME_TH = "SUBDISTRICTS.SUBDISTRICT_NAME_TH";
	public static final String SUBDISTRICT_CODE = "SUBDISTRICTS.SUBDISTRICT_CODE";
	public static final String SUBDISTRICT_ID = "SUBDISTRICTS.SUBDISTRICT_ID";
	public static final String DISTRICT_CODE = "SUBDISTRICTS.DISTRICT_CODE";
	
}
