package com.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.entity.EOUser;
import com.springsecurity.repo.EOUserRepo;

@RestController
public class AppRestController {
	
	@Autowired
	private EOUserRepo eoUserRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public EOUser createEOUser(@RequestBody EOUser eoUser) {
		eoUser.setPassword(passwordEncoder.encode(eoUser.getPassword()));
		return eoUserRepo.save(eoUser);
	}
}
