package com.springsecurity.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.entity.EOUser;

public interface EOUserRepo extends JpaRepository<EOUser, Long>{
	
	public Optional<EOUser> findByUserName(String userName);
}
