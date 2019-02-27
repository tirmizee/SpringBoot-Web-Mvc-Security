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
		return "pages/P001_main/P001_main";
	}
	
	@GetMapping(path = "/firstlogin")
	public String firstLogin(ModelMap model) {
		return "pages/PG00_password_firstlogin/PG00_password_firstlogin";
	}
	
	@GetMapping(path = "/passwordexpried")
	public String passwordExpried(ModelMap model) {
		return "pages/PG01_password_expried/PG01_password_expried";
	}
	
	@GetMapping(path = "/report")
	public String report(ModelMap model) {
		return "pages/P002_report/P002_report";
	}
	
}
