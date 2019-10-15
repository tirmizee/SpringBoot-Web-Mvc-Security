package com.tirmizee.core.jdbcrepository;

import java.util.Map;

public class MissingRowUnmapper<T> extends RowUnmapper<T> {
	
	@Override
	public Map<String, Object> mapColumns(Object o) {
		throw new UnsupportedOperationException("This repository is read-only, it can't store or update entities");
	}
}
