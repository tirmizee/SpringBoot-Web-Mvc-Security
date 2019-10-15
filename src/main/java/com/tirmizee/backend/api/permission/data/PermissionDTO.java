package com.tirmizee.backend.api.permission.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class PermissionDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer perId;
	private String perName;
	private String perCode;
	private String updateBy;
	private String createBy;
	private java.sql.Date updateDate;
	private java.sql.Date createDate;
	private boolean hasPercode;
	
}
