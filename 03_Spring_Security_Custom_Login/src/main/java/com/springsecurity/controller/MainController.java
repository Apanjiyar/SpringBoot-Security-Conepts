package com.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@GetMapping("/")
	@ResponseBody
    public String index() {
        return "This is Index Page";
    }
	
	@GetMapping("/customLogin")
	public ModelAndView customLogin(@RequestParam(value = "error", required = false) boolean error,
			@RequestParam(value = "logout", required = false) boolean logout) {
	    ModelAndView modelAndView = new ModelAndView("customLogin");
	    if (error) {
	        modelAndView.addObject("errorMessage", "Invalid username or password");
	    }
	    if(logout) {
	    	modelAndView.addObject("logoutMessage", "You have been logged out successfully");
	    }
	    return modelAndView;
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
