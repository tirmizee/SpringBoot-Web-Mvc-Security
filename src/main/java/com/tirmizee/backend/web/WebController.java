package com.tirmizee.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
	
	@GetMapping(path = {"/","/login"})
	public String login(@RequestParam(required = false) String error, ModelMap model) {
		if(error != null) { 
			model.addAttribute("error", error);
		}
		return "pages/login/login";
	}
	
	@GetMapping(path = "/main")
	public String main(ModelMap model) {
		return "pages/main/main";
	}
	
}
