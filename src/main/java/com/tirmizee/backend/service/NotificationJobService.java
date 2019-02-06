package com.tirmizee.backend.service;

import java.util.Date;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tirmizee.backend.dto.Notification;

@Service
public class NotificationJobService {
	
	  public final ApplicationEventPublisher eventPublisher;
      
      public NotificationJobService(ApplicationEventPublisher eventPublisher) {
          this.eventPublisher = eventPublisher;
      }

      @Scheduled(fixedRate = 1000)
      public void publishJobNotifications() throws InterruptedException {
          Integer jobId = Notification.getNextJobId();
          Notification nStarted = new Notification("Job No. " + jobId + " started.", new Date());
          this.eventPublisher.publishEvent(nStarted);
      }

}
