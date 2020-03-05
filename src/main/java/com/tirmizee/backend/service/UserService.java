package com.tirmizee.backend.service;

import com.tirmizee.backend.api.user.data.ReqPasswordDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordExpriedDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordResetTokenDTO;
import com.tirmizee.backend.api.user.data.ReqUpdateStatusDTO;
import com.tirmizee.backend.api.user.data.UserDetailCriteriaDTO;
import com.tirmizee.backend.api.user.data.UserDetailPageDTO;
import com.tirmizee.backend.api.user.data.UserDetailUpdateDTO;
import com.tirmizee.core.config.security.UserProfile;
import com.tirmizee.core.datatable.RequestTable;
import com.tirmizee.core.datatable.ResponseTable;

public interface UserService {
	
	long countUses();

	boolean isPasswordExpired(String username);
	
	boolean hasBranch(Long userId, String branchCode);
	
	boolean hasBranch(String username, String branchCode);
	
	void forgotPassword(String email);
	
	void fourceAccountExpired(String username);
	
	void fourcePasswordExpired(String username);
	
	void resetPassword(ReqPasswordResetTokenDTO passwordResetTokenDTO);
	
	void changePasswordFirstLogin(String username, ReqPasswordDTO passwordDTO);
	
	void changePasswordExpired(String username, ReqPasswordExpriedDTO passwordExpriedDTO);
	
	void updateUser(UserDetailUpdateDTO updateUser);
	
	void validateUserByAuthority(Long userId, UserProfile userProfile);
	
	void validateUserByAuthority(String username, UserProfile userProfile);
	
	void updateStatusEnable(ReqUpdateStatusDTO updateEnable);
	
	void updateStatusFirstLogin(ReqUpdateStatusDTO updateFirstLogin);
	
	void updateStatusLocked(ReqUpdateStatusDTO updateAccountNonLocked);
	
	void updateStatusAccountExpired(ReqUpdateStatusDTO updateAccountExpired);
	
	void updateStatusPasswordExpired(ReqUpdateStatusDTO updatePasswordExpired);
	
	ResponseTable<UserDetailPageDTO> dataTableByAuthority(RequestTable<UserDetailCriteriaDTO> requestTable, UserProfile profile); 
	
}
