package com.tirmizee.backend.service;

public interface MessagingService {
	
	void sendAsyncMessage(String destination, Object payload);

}
