package com.tirmizee.core.jdbcrepository.sql;

import static java.lang.String.format;
import static org.springframework.util.StringUtils.collectionToDelimitedString;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.Assert;

import com.tirmizee.core.jdbcrepository.TableDescription;

public abstract class AbstractSqlGenerator implements SqlGenerator {

	public static final String WHERE = " WHERE ";
	public static final String AND = " AND ";
	public static final String OR = " OR ";
	public static final String SELECT = "SELECT ";
	public static final String FROM = "FROM ";
	public static final String DELETE = "DELETE ";
	public static final String COMMA = ", ";
	public static final String PARAM = " = ?";
	public static final String ALLCLAUSE = "*";
	
	@Override
	public String count(String table) {
		return SELECT + "COUNT(*) " + FROM + " (" + table + ") t";
	}

	@Override
	public String count(TableDescription table) {
		return format("SELECT count(*) FROM %s", table.getFromClause());
	}

	@Override
	public String deleteAll(TableDescription table) {
		return format("DELETE FROM %s", table.getName());
	}

	@Override
	public String deleteById(TableDescription table) {
		return deleteByIds(table, 1);
	}

	@Override
	public String deleteByIds(TableDescription table, int idsCount) {
		 return deleteAll(table) + " WHERE " + idsPredicate(table, idsCount);
	}

	@Override
	public String existsById(TableDescription table) {
		return format("SELECT 1 FROM %s WHERE %s", table.getName(), idPredicate(table));
	}

	@Override
	public String insert(TableDescription table, Map<String, Object> columns) {
		String TABLE = table.getName();
		String VALUES = repeat("?", COMMA, columns.size());
		String COLUMNS = collectionToDelimitedString(columns.keySet(), COMMA);
		return format("INSERT INTO %s (%s) VALUES (%s)",TABLE, COLUMNS, VALUES);
	}

	@Override
	public String selectById(TableDescription table) {
		return selectByIds(table, 1);
	}

	@Override
	public String selectByIds(TableDescription table, int idsCount) {
		return idsCount > 0
	           ? selectAll(table) + " WHERE " + idsPredicate(table, idsCount)
	           : selectAll(table);
	}
	
	@Override
	public String selectAll(TableDescription table) {
		return format("SELECT %s FROM %s", ALLCLAUSE, table.getName());
	}
	
	@Override
	public String selectAll(StringBuilder statement, Sort sort) {
		return statement.append(sort != null ? orderByClause(sort) : "").toString();
	}

	@Override
	public String selectAll(TableDescription table, Sort sort) {
		return selectAll(table) + (sort != null ? orderByClause(sort) : "");
	}

	@Override
	public String update(TableDescription table, Map<String, Object> columns) {
		return format("UPDATE %s SET %s WHERE %s",
	           table.getName(),
	           formatParameters(columns.keySet(), COMMA),
	           idPredicate(table));
	}
	
	protected String orderByClause(Sort sort) {
        return " ORDER BY " + orderByExpression(sort);
    }
	
	protected String orderByExpression(Sort sort) {
        
		StringBuilder sb = new StringBuilder();

        for (Iterator<Order> it = sort.iterator(); it.hasNext();) {
            Order order = it.next();
            sb.append(order.getProperty()).append(' ').append(order.getDirection());
            if (it.hasNext()) { 
            	sb.append(COMMA); 
            }
        }
        return sb.toString();
    }
	
	private String idPredicate(TableDescription table) {
        return formatParameters(table.getIdColumns(), AND);
    }
	
	private String idsPredicate(TableDescription table, int idsCount) {
        
		Assert.isTrue(idsCount > 0, "idsCount must be greater than zero");

        List<String> idColumnNames = table.getIdColumns();

        if (idsCount == 1) {
            return idPredicate(table);
        } else if (idColumnNames.size() > 1) {
            return repeat("(" + formatParameters(idColumnNames, AND) + ")", OR, idsCount);
        } else {
            return idColumnNames.get(0) + " IN (" + repeat("?", COMMA, idsCount) + ")";
        }
    }
	
	protected Sort sortById(TableDescription table) {
        return new Sort(Direction.ASC, table.getIdColumns());
    }
	
	private String formatParameters(Collection<String> columns, String delimiter) {
        return collectionToDelimitedString(columns, delimiter, "", PARAM);
    }
	
	public static String repeat(String str, String separator, int count) {
        StringBuilder sb = new StringBuilder((str.length() + separator.length()) * Math.max(count, 0));

        for (int n = 0; n < count; n++) {
            if (n > 0) sb.append(separator);
            sb.append(str);
        }
        return sb.toString();
    }
	
}
