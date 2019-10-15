package com.tirmizee.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingServiceImpl implements MessagingService {

	@Autowired
	@Qualifier("taskExecutor")
	private TaskExecutor task;
	
	@Autowired
	private SimpMessagingTemplate template;

	@Override
	public void sendAsyncMessage(String destination, Object payload) {
		task.execute(() -> {
			template.convertAndSend(destination, payload);
		});
	}
	
}
