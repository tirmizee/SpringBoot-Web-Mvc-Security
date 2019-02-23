package com.tirmizee.backend.service;

import com.tirmizee.backend.api.user.data.RequestPasswordDTO;
import com.tirmizee.backend.web.data.ResponseSuccess;

public interface UserService {
	
	ResponseSuccess changePasswordFirstLogin(String username, RequestPasswordDTO passwordDTO);
	
}
