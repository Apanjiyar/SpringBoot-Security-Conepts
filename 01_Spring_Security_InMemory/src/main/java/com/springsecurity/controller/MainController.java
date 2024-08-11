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
		return "This is index page";
	}
	
	@GetMapping("/home")
	@ResponseBody
	public String home() {
		return "This is home page";
	}
	
	@GetMapping("/about")
	@ResponseBody
	public String about() {
		return "This is home page";
	}
	
	@GetMapping("/admin/home")
	@ResponseBody
	public String adminHome() {
		return "This is Admin Home page";
	}
	
	@GetMapping("/user/home")
	@ResponseBody
	public String userHome() {
		return "This is User Home page";
	}
	
	@GetMapping("/contact")
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String contact() {
		return "This is Contact page";
	}
}
