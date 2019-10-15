package com.tirmizee.core.security;

import java.io.IOException;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import com.tirmizee.backend.service.MessagingService;
import com.tirmizee.backend.service.SessionService;
import com.tirmizee.core.utilities.ObjectUtils;

/**
 * @author Pratya Yeekhaday
 *
 */
public class SessionInformationExpiredStrategyImpl implements SessionInformationExpiredStrategy {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	private final String destinationUrl;
	private final RedirectStrategy redirectStrategy;
	private SessionService sessionService;
	private MessagingService messagingService;

	public SessionInformationExpiredStrategyImpl(String invalidSessionUrl) {
		this(invalidSessionUrl, new DefaultRedirectStrategy());
	}

	public SessionInformationExpiredStrategyImpl(String invalidSessionUrl, RedirectStrategy redirectStrategy) {
		Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
		this.destinationUrl=invalidSessionUrl;
		this.redirectStrategy=redirectStrategy;
	}

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		LOGGER.debug("Redirecting to '" + destinationUrl + "'");
		
		if (!ObjectUtils.isAnyNull(sessionService, messagingService)) {
			messagingService.sendAsyncMessage("/topic/viewusers", sessionService.allUserLoggedDetail());
		}
		redirectStrategy.sendRedirect(event.getRequest(), event.getResponse(), destinationUrl);
	}

	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	public void setMessagingService(MessagingService messagingService) {
		this.messagingService = messagingService;
	}
	
}
