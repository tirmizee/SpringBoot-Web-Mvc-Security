package com.tirmizee.core.jdbcrepository.sql;

public class TempQuery {

	public static final String FIND_PERMISSION_ROLE = 
			"SELECT \r\n" + 
			"    P.PER_ID,\r\n" + 
			"    P.PER_CODE,\r\n" + 
			"    P.PER_NAME,\r\n" + 
			"    P.CREATE_BY,\r\n" + 
			"    P.CREATE_DATE,\r\n" + 
			"    P.UPDATE_BY,\r\n" + 
			"    P.UPDATE_DATE,\r\n" + 
			"    CASE WHEN U.PER_CODE IS NULL THEN 0 ELSE 1 END AS HAS_PERCODE\r\n" + 
			"FROM PERMISSION P\r\n" + 
			"LEFT JOIN (\r\n" + 
			"    SELECT\r\n" + 
			"        P.PER_CODE\r\n" + 
			"    FROM  ROLE R                 \r\n" + 
			"    INNER JOIN ROLE_MAP_PERMISSION RMP  ON R.ROLE_ID = RMP.ROLE_ID\r\n" + 
			"    INNER JOIN PERMISSION P             ON RMP.PER_ID = P.PER_ID\r\n" + 
			"    WHERE R.ROLE_ID = ? \r\n" + 
			") U ON P.PER_CODE = U.PER_CODE";
	
}
