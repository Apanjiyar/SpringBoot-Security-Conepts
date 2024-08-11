package com.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@GetMapping("/")
	@ResponseBody
    public String index() {
        return "This is Index Page";
    }

	@GetMapping("/home")
	@ResponseBody
	public String home() {
		return "This is Home Page";
	}
	
	@GetMapping("/about")
	@ResponseBody
	public String about() {
		return "This is About Page";
	}
	
	@GetMapping("contact")
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String contact() {
		return "This is Contact page";
	}
}
