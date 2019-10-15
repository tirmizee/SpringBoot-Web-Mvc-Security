package com.tirmizee.core.jdbcrepository;

import java.util.Map;

public abstract class RowUnmapper<T> {
	public abstract Map<String, Object> mapColumns(T param);
}
