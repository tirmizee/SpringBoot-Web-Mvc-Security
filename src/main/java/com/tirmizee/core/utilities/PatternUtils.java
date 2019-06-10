package com.tirmizee.core.utilities;

import java.util.regex.Pattern;

public final class PatternUtils {

	public static boolean isUUID(String uuid) {
		String format = "^[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}$";
		return Pattern.matches(format, uuid);
	}
	
	public static boolean isTokenForgotPassword(String uuid) {
		String format = "^[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}-[0-9a-zA-Z]{20}$";
		return Pattern.matches(format, uuid);
	}
	
}
