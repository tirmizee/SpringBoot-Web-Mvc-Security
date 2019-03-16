package com.tirmizee.backend.web;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class WebErrorController {

	private static final Logger LOGGER = Logger.getLogger(WebErrorController.class);
	
	@GetMapping(value = "/accessdenied")
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public String accessDenied(ModelMap model) {
		LOGGER.debug("/AccessDenied");
		return "error/403";
	}
	
	@GetMapping(value = "/NotFound")
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String notFound(ModelMap model, @RequestParam(required = false) String RequestURL) {
		LOGGER.debug("/NotFound");
		model.addAttribute("RequestURL", RequestURL);
		return "error/404";
	}
	
	@GetMapping(value = "/ServerError")
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public String serverError(ModelMap model) {
		LOGGER.debug("/ServerError");
		return "error/500";
	}
	
}
