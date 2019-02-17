package com.tirmizee.core.utilities;

import java.sql.Timestamp;
import java.util.Date;

public final class DateUtils {

	public static Timestamp nowTimestamp(){
		return new Timestamp(new Date().getTime());
	}
	
}
