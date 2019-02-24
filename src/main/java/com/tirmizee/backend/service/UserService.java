package com.tirmizee.backend.service;

import java.sql.Date;

import com.tirmizee.backend.api.user.data.ReqPasswordDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordExpriedDTO;

public interface UserService {
	
	boolean isPasswordExpried(Date expriedDate);
	
	void updatePasswordExpired(String username);
	
	void changePasswordFirstLogin(String username, ReqPasswordDTO passwordDTO);
	
	void changePasswordExpired(String username, ReqPasswordExpriedDTO passwordExpriedDTO);
	
}
