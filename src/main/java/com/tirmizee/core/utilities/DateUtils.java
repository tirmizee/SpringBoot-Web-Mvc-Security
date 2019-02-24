package com.tirmizee.core.utilities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public final class DateUtils {

	public static Timestamp nowTimestamp(){
		return new Timestamp(new Date().getTime());
	}
	
	public static java.sql.Date now(){
		return new java.sql.Date(new Date().getTime());
	}
	
	public static java.sql.Date plusDays(int day){
		LocalDate localDateNow = LocalDate.now().plusDays(day);
		return java.sql.Date.valueOf(localDateNow);
	}
	
}
