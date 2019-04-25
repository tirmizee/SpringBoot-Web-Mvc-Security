package com.tirmizee.core.utilities;

import java.util.Collections;
import java.util.List;

/**
 * @author Pratya Yeekhaday
 *
 */
public class CollectionUtils {
	
	public static <T> List<T> emptyIfNull(List<T> list) {
	    return list == null ? Collections.emptyList() : list;
	}

}
