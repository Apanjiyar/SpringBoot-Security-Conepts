package com.springsecurity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurity.entity.EOUser;
import com.springsecurity.repo.EOUserRepo;

@Service
public class EOUserService implements UserDetailsService {
	
	@Autowired
	private EOUserRepo eoUserRepo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<EOUser> eoUser = eoUserRepo.findByUserName(userName);
		UserDetails user = null;
		if(eoUser.isPresent()) {
			user = User.builder()
					.username(eoUser.get().getUserName())
					.password(eoUser.get().getPassword())
					.roles(eoUser.get().getRole())
					.build();
			return user;
		}else {
		    throw new UsernameNotFoundException("User not found");
		}
	}
}
