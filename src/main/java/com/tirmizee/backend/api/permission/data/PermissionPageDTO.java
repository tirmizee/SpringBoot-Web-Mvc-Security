package com.tirmizee.backend.api.permission.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tirmizee.core.datatable.SortColumn;
import com.tirmizee.core.repository.PermissionRepository;

import lombok.Data;

@Data
public class PermissionPageDTO {

	private Integer perId;
	
	@SortColumn(PermissionRepository.COL_PER_NAME)
	private String perName;
	
	@SortColumn(PermissionRepository.COL_PER_CODE)
	private String perCode;
	
	private String updateBy;
	
	@SortColumn(PermissionRepository.COL_CREATE_DATE)
	private String createBy;
	
	private java.sql.Date updateDate;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private java.sql.Date createDate;
	
}
