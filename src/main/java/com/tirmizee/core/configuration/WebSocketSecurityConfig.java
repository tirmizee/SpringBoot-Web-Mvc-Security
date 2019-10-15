package com.tirmizee.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

import com.tirmizee.core.constant.PermissionCode;

/**
 * @author Pratya Yeekhaday
 *
 */
@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

	@Override
	protected void configureInbound(
	MessageSecurityMetadataSourceRegistry messages) { 
	    messages
	    	.simpTypeMatchers(SimpMessageType.CONNECT, SimpMessageType.UNSUBSCRIBE, SimpMessageType.DISCONNECT).permitAll()
	    	.simpSubscribeDestMatchers("/topic/greetings/*").hasAuthority(PermissionCode.P000)
	    	.simpSubscribeDestMatchers("/topic/viewusers/*").hasAuthority(PermissionCode.P003);
	}

	@Override
	protected boolean sameOriginDisabled() {
		return true;
	}
	
}
