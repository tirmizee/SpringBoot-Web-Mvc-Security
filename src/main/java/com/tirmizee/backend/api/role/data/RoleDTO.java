package com.tirmizee.backend.api.role.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tirmizee.core.datatable.SortColumn;
import com.tirmizee.core.repository.RoleRepository;

import lombok.Data;

@Data
public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer roleId;
	
	@SortColumn(RoleRepository.COL_ROLE_NAME)
	private String roleName;
	
	@SortColumn(RoleRepository.COL_ROLE_CODE)
	private String roleCode;
	
	@SortColumn(RoleRepository.COL_ROLE_DESC)
	private String roleDesc;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@SortColumn(RoleRepository.COL_CREATE_DATE)
	private java.sql.Date CreateDate;
	
}
