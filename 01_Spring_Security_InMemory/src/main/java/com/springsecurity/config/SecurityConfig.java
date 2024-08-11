package com.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * In Memory User Creation
	 * 
	 * @return
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1 = User.builder()
				.username("user1")
				.password(passwordEncoder().encode("12345"))
				.roles("ADMIN")
				.build();
		
		UserDetails user2 = User.builder()
				.username("user2")
				.password(passwordEncoder().encode("12345"))
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(user1, user2);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/home", "/about").permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/user/**").hasRole("USER")
				.anyRequest().authenticated())
			.formLogin(form -> form
					.permitAll()
		).build();
	}
}
