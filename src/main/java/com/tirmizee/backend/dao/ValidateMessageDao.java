package com.tirmizee.backend.dao;

import com.tirmizee.core.domain.ValidateMessage;
import com.tirmizee.core.repository.ValidateMessageRepository;

public interface ValidateMessageDao extends ValidateMessageRepository {
	
	 ValidateMessage getByCode(String code);

}
