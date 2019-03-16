package com.tirmizee.backend.dao;

import com.tirmizee.core.domain.ForgotPassword;
import com.tirmizee.core.repository.ForgotPasswordRepository;

public interface ForgotPasswordDao extends ForgotPasswordRepository {

	ForgotPassword findByToken(String token);
	
}
