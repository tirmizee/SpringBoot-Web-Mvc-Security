package com.tirmizee.core.repository;

import com.tirmizee.core.domain.Village;
import com.tirmizee.core.jdbcrepository.JdbcRepository;

public interface VillageRepository extends JdbcRepository<Village, String> {

	public static final String TB_VILLAGES = "VILLAGES";
	public static final String COL_SUB_DISTRICT_CODE = "SUB_DISTRICT_CODE";
	public static final String COL_VILLAGE_NAME = "VILLAGE_NAME";
	public static final String COL_VILLAGE_NO = "VILLAGE_NO";
	public static final String COL_VILLAGE_CODE = "VILLAGE_CODE";
	public static final String SUB_DISTRICT_CODE = "VILLAGES.SUB_DISTRICT_CODE";
	public static final String VILLAGE_NAME = "VILLAGES.VILLAGE_NAME";
	public static final String VILLAGE_NO = "VILLAGES.VILLAGE_NO";
	public static final String VILLAGE_CODE = "VILLAGES.VILLAGE_CODE";
	
}
