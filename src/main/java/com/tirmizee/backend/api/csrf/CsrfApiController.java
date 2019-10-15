package com.tirmizee.backend.api.csrf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.backend.web.WebController;

@RestController
public class CsrfApiController {
	
	public static final Logger LOG = LoggerFactory.getLogger(WebController.class);
	
	@RequestMapping("/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }

}
