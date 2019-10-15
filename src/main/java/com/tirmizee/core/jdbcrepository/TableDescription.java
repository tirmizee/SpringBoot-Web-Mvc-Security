package com.tirmizee.core.jdbcrepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class TableDescription {

	private final String name;
	private final List<String> idColumns;
	private final String fromClause;

	public TableDescription(String name, String fromClause, String...idColumns) {
		Assert.notNull(name , "tablename must be provided");		
		Assert.notNull(idColumns, "primary key column must be provided");
		Assert.isTrue(idColumns.length > 0, "At least one primary key column must be provided");

		this.name = name;
		this.idColumns = Collections.unmodifiableList(Arrays.asList(idColumns));
		this.fromClause = ((StringUtils.hasText(fromClause)) ? fromClause : name);
	}

	public TableDescription(String name, String idColumn) {
		this(name, null, new String[] { idColumn });
	}

	public String getName() {
		return this.name;
	}

	public List<String> getIdColumns() {
		return this.idColumns;
	}

	public String getFromClause() {
		return this.fromClause;
	}
}
