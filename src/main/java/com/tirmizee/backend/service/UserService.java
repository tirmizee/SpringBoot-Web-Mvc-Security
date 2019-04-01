package com.tirmizee.backend.service;

import com.tirmizee.backend.api.user.data.ReqPasswordDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordExpriedDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordResetTokenDTO;
import com.tirmizee.backend.api.user.data.ReqUpdateAccountNonLockedDTO;
import com.tirmizee.backend.api.user.data.ReqUpdateEnableDTO;
import com.tirmizee.backend.api.user.data.ReqUpdateFirstLoginDTO;
import com.tirmizee.backend.api.user.data.ReqUpdatePasswordExpiredDTO;
import com.tirmizee.backend.api.user.data.UserDetailCriteriaDTO;
import com.tirmizee.backend.api.user.data.UserDetailDTO;
import com.tirmizee.core.datatable.RequestTable;
import com.tirmizee.core.datatable.ResponseTable;

public interface UserService {
	
	long countUses();

	boolean isPasswordExpired(String username);
	
	void forgotPassword(String email);
	
	void fourcePasswordExpired(String username);
	
	void resetPassword(ReqPasswordResetTokenDTO passwordResetTokenDTO);
	
	void changePasswordFirstLogin(String username, ReqPasswordDTO passwordDTO);
	
	void changePasswordExpired(String username, ReqPasswordExpriedDTO passwordExpriedDTO);
	
	void updateStatusEnable(ReqUpdateEnableDTO updateEnable);
	
	void updateStatusFirstLogin(ReqUpdateFirstLoginDTO updateFirstLogin);
	
	void updateStatusLocked(ReqUpdateAccountNonLockedDTO updateAccountNonLocked);
	
	void updateStatusPasswordExpired(ReqUpdatePasswordExpiredDTO updatePasswordExpired);
	
	ResponseTable<UserDetailDTO> pagingTable(RequestTable<UserDetailCriteriaDTO> requestTable); 
	
}
