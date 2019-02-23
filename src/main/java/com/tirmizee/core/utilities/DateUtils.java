package com.tirmizee.core.utilities;

import java.sql.Timestamp;
import java.util.Date;

public final class DateUtils {

	public static Timestamp nowTimestamp(){
		return new Timestamp(new Date().getTime());
	}
	
	public static java.sql.Date now(){
		return new java.sql.Date(new Date().getTime());
	}
	
}
