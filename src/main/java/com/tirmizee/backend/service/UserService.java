package com.tirmizee.backend.service;

import java.sql.Date;

import com.tirmizee.backend.api.user.data.ReqPasswordDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordExpriedDTO;
import com.tirmizee.backend.api.user.data.UserDetailCriteriaDTO;
import com.tirmizee.backend.api.user.data.UserDetailDTO;
import com.tirmizee.core.datatable.RequestTable;
import com.tirmizee.core.datatable.ResponseTable;

public interface UserService {
	
	boolean isPasswordExpried(Date expriedDate);
	
	void updatePasswordExpired(String username);
	
	void changePasswordFirstLogin(String username, ReqPasswordDTO passwordDTO);
	
	void changePasswordExpired(String username, ReqPasswordExpriedDTO passwordExpriedDTO);
	
	ResponseTable<UserDetailDTO> pagingTable(RequestTable<UserDetailCriteriaDTO> requestTable); 
	
}
