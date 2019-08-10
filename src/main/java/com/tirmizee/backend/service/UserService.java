package com.tirmizee.backend.service;

import java.util.Set;

import com.tirmizee.backend.api.user.data.ReqPasswordDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordExpriedDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordResetTokenDTO;
import com.tirmizee.backend.api.user.data.ReqUpdateStatusDTO;
import com.tirmizee.backend.api.user.data.UserDetailCriteriaDTO;
import com.tirmizee.backend.api.user.data.UserDetailPageDTO;
import com.tirmizee.backend.api.user.data.UserDetailUpdateDTO;
import com.tirmizee.core.datatable.RequestTable;
import com.tirmizee.core.datatable.ResponseTable;
import com.tirmizee.core.security.UserProfile;

public interface UserService {
	
	long countUses();

	boolean isPasswordExpired(String username);
	
	void forgotPassword(String email);
	
	void fourceAccountExpired(String username);
	
	void fourcePasswordExpired(String username);
	
	void resetPassword(ReqPasswordResetTokenDTO passwordResetTokenDTO);
	
	void changePasswordFirstLogin(String username, ReqPasswordDTO passwordDTO);
	
	void changePasswordExpired(String username, ReqPasswordExpriedDTO passwordExpriedDTO);
	
	void updateUser(UserDetailUpdateDTO updateUser);
	
	void updateStatusEnable(ReqUpdateStatusDTO updateEnable);
	
	void updateStatusFirstLogin(ReqUpdateStatusDTO updateFirstLogin);
	
	void updateStatusLocked(ReqUpdateStatusDTO updateAccountNonLocked);
	
	void updateStatusAccountExpired(ReqUpdateStatusDTO updateAccountExpired);
	
	void updateStatusPasswordExpired(ReqUpdateStatusDTO updatePasswordExpired);
	
	ResponseTable<UserDetailPageDTO> pagingTable(RequestTable<UserDetailCriteriaDTO> requestTable, UserProfile profile); 
	
}
