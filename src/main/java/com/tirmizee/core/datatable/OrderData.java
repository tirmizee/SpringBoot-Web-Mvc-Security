package com.tirmizee.core.datatable;

/**
 * wrapper for datatables.js  
 * 
 * @author pratya yeekhaday
 * @see https://datatables.net/manual/server-side
 */
public class OrderData {

	private Long column;
	private String dir;
	
	public Long getColumn() {
		return column;
	}
	public void setColumn(Long column) {
		this.column = column;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	
}
