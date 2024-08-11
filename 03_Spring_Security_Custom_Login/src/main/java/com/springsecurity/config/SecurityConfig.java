package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.springsecurity.service.EOUserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private EOUserService eoUserService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(eoUserService);
		provider.setPasswordEncoder(this.passwordEncoder());
		return provider;
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return eoUserService;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {	
		return httpSecurity
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authorize -> authorize.requestMatchers("/").permitAll()
				.requestMatchers("/home", "/about").permitAll()
				.requestMatchers("/admin/**", "/register").hasRole("ADMIN")
				.requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")
				.anyRequest().authenticated())										
			.formLogin(form -> form
					.loginPage("/customLogin")
					.loginProcessingUrl("/customLogin")
					.defaultSuccessUrl("/", true)
					.failureUrl("/customLogin?error=true")
					.permitAll())
			.logout(logout -> logout
					.logoutUrl("/customLogout")
					.logoutSuccessUrl("/customLogin?logout=true")
		            .permitAll())
			.build();
	}
}
