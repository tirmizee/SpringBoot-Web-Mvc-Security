package com.tirmizee.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
	
	@GetMapping(path = "/")
	public String index(ModelMap model) {
		model.addAttribute("h", "hello world 555");
		return "index";
	}
	
}
