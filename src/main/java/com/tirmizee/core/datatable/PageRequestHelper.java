package com.tirmizee.core.datatable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.NullHandling;
import org.springframework.data.domain.Sort.Order;

/**
 * build annotation {@link SortColumn}
 * 
 * @author Pratya Yeekhaday
 *
 */
public class PageRequestHelper {
	
	public static  PageRequest of(int page, int size){
		return new PageRequest(page, size);
	}
	
	public static  PageRequest build(RequestTable<?> requestTable, Class<?> clazz){
		int page = (int) (requestTable.getStart()/requestTable.getLength()),
			size = (int) requestTable.getLength();
		return new PageRequest(page, size, buildSort(requestTable, clazz));
	}
	
	private static Sort buildSort(RequestTable<?> requestData,Class<?> clazz) {
		List<Sort.Order> listOrderBy = new ArrayList<Sort.Order>();
		Field[] fields = clazz.getDeclaredFields();
		for (OrderData orderData : requestData.getOrders()) {
			Sort.Direction direction = Sort.Direction.fromStringOrNull(orderData.getDir());
			String requestColumn = requestData.getColumns().get(orderData.getColumn().intValue()).getData();
			addOrderBy(direction, requestColumn, listOrderBy, fields);
		}
		return listOrderBy.isEmpty() ? null : new Sort(listOrderBy);
	}

	private static void addOrderBy(Direction direction, String requestColumn, List<Order> resultOrders, Field[] fields) {
		for (Field field : fields) {
			if (field.isAnnotationPresent(SortColumn.class) && field.getName().equalsIgnoreCase(requestColumn)) {
				SortColumn sortColumns = field.getAnnotation(SortColumn.class);
				for (String column : sortColumns.value()) {
					resultOrders.add(new Order(direction, column, NullHandling.NATIVE));
				}
				break;
			}
		}
	}
	
}
