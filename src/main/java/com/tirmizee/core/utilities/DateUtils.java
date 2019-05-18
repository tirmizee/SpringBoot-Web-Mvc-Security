package com.tirmizee.core.utilities;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public final class DateUtils {

	public static final SimpleDateFormat DD_MM_YYYY = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public static Timestamp nowTimestamp(){
		return new Timestamp(new Date().getTime());
	}
	
	public static Timestamp nowTimestampPlusMinutes(int minutes){
		LocalDateTime localDateNow = LocalDateTime.now().plusMinutes(minutes);
		return Timestamp.valueOf(localDateNow);
	}
	
	public static java.sql.Date now(){
		return new java.sql.Date(new Date().getTime());
	}
	
	public static java.sql.Date plusDays(int day){
		LocalDate localDateNow = LocalDate.now().plusDays(day);
		return java.sql.Date.valueOf(localDateNow);
	}
	
	public static Date toDateFormat(String toDate, String format) throws ParseException{
		return new SimpleDateFormat(format).parse(toDate);
	}
	
	public static boolean nowAfter(Timestamp time){
		return time == null ? false : nowTimestamp().after(time);
	}
	
	public static boolean nowBefore(Timestamp time){
		return time == null ? false : nowTimestamp().before(time);
	}
	
	public static boolean nowAfter(java.sql.Date date){
		return date == null ? false : now().after(date);
	}
	
}
