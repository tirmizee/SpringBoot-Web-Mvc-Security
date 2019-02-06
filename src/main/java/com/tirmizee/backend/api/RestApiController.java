package com.tirmizee.backend.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.tirmizee.backend.dto.NHSO01Detail;
import com.tirmizee.backend.dto.Notification;

@RestController
@RequestMapping(path = "/api/rest")
public class RestApiController {

	 private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();
	
	@GetMapping(path = "/hello")
    public NHSO01Detail index() {
        return new NHSO01Detail();
    }
	
	@GetMapping("/new_notification")
    public SseEmitter getNewNotification() {
            SseEmitter emitter = new SseEmitter();
            this.emitters.add(emitter);

            emitter.onCompletion(() -> {
            	this.emitters.remove(emitter);
            });
            emitter.onTimeout(() -> {
                    emitter.complete(); 
                    this.emitters.remove(emitter);
            });

            return emitter;
    }
	
	@EventListener
    public void onNotification(Notification notification) {
            List<SseEmitter> deadEmitters = new ArrayList<>();
            this.emitters.forEach(emitter -> {
                    try {
                           emitter.send(notification);
                    } catch (Exception e) {
                           deadEmitters.add(emitter);
                    }
            });
            this.emitters.remove(deadEmitters);
    }
	
}
