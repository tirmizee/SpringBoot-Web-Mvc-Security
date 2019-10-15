package com.tirmizee.core.utilities;

/**
 * @author Pratya Yeekhaday
 *
 */
public class ObjectUtils {

	public static Object[] toArray(Object...obj) {
		return obj;
	}
	
	public static boolean isAnyNull(Object...obj) {
		if (obj == null) { return true; }
		for (Object object : obj) {
			if (object == null) {
				return true;
			}
		}
		return false;
	}
	
}
