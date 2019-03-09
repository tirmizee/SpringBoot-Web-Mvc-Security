package com.tirmizee.backend.web;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

	public static final Logger LOG = Logger.getLogger(WebController.class);
	
	@GetMapping(path = {"/","/login"})
	public String login(@RequestParam(required = false) String error, ModelMap model) {
		if(error != null) { 
			model.addAttribute("error", error);
		}
		return "pages/login/login";
	}
	
	@GetMapping(path = "/main")
	public String main(ModelMap model) {
		return "pages/P000_main/P000_main";
	}
	
	@GetMapping(path = "/firstlogin")
	public String firstLogin(ModelMap model) {
		return "pages/PG00_password_firstlogin/PG00_password_firstlogin";
	}
	
	@GetMapping(path = "/passwordexpried")
	public String passwordExpried(ModelMap model) {
		return "pages/PG01_password_expried/PG01_password_expried";
	}
	
	@PreAuthorize("hasAnyAuthority('P001')")
	@GetMapping(path = "/report")
	public String report(ModelMap model) {
		return "pages/P001_report/P001_report";
	}
	
	@PreAuthorize("hasAnyAuthority('P002')")
	@GetMapping(path = "/manageuser")
	public String manageUser(ModelMap model) {
		return "pages/P002_manage_user/P002_manage_user";
	}
	
	@PreAuthorize("hasAnyAuthority('P003')")
	@GetMapping(path = "/managesession")
	public String manageSession(ModelMap model) {
		return "pages/P003_manage_session/P003_manage_session";
	}
	
}
