package com.tirmizee.core.repository;

import com.tirmizee.core.domain.Geography;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface GeographyRepository extends JdbcRepository<Geography, Integer> {

	public static final String TB_GEOGRAPHY = "GEOGRAPHY";
	public static final String COL_GEO_NAME_EN = "GEO_NAME_EN";
	public static final String COL_GEO_NAME_TH = "GEO_NAME_TH";
	public static final String COL_GEO_ID = "GEO_ID";
	public static final String GEO_NAME_EN = "GEOGRAPHY.GEO_NAME_EN";
	public static final String GEO_NAME_TH = "GEOGRAPHY.GEO_NAME_TH";
	public static final String GEO_ID = "GEOGRAPHY.GEO_ID";
	
}
