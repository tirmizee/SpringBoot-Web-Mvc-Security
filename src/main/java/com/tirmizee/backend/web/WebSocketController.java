package com.tirmizee.backend.web;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.tirmizee.backend.web.data.Notify;
import com.tirmizee.backend.web.data.Payload;

@Controller
public class WebSocketController {
	
//	@PreAuthorize("hasAnyAuthority('P002')")
	@MessageMapping("/notify")
    @SendTo("/topic/notify")
    public Payload greeting(Notify notify) throws Exception {
        return new Payload("notify " + notify.getMessage());
    }

	@MessageExceptionHandler
    @SendToUser("/topic/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
	
}
