package com.tirmizee.core.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tirmizee.backend.web.data.Payload;

@Component
public class SchedulerTaskImpl implements SchedulerTask {

	public final Logger LOG = Logger.getLogger(SchedulerTaskImpl.class); 
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@Override
	@Scheduled(fixedRate = 1000)
	public void serverDatetime() {
		 template.convertAndSend("/topic/greetings", new Payload(dateFormat.format(new Date())));
	}

}
