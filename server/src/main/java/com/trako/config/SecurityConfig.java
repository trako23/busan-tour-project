package com.trako.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
	
	
	private final CustomAuthFailureHandler customAuthFailureHandler;
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.headers().frameOptions().disable()
			.and()
			.authorizeRequests()
			.antMatchers("/", "/user/join", "/user/login", "/user/login-failed", "/user/logout",
					"/user/idCheck").permitAll()
			.antMatchers("/api/**").permitAll()
			.antMatchers("/css/*", "/js/*", "/favioon.ioo", "/error").permitAll()
			.antMatchers("/admin/**").hasAnyRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.usernameParameter("id") //로그인 파라미터 지정
			.loginPage("/user/login")	//GET 방식
			.loginProcessingUrl("/user/login")  //POST방식
			.defaultSuccessUrl("/user/login-success")
			//.failureUrl("/user/login-failed")
			.failureHandler(customAuthFailureHandler)
			.and()
			.logout()
			.logoutUrl("/user/logout")
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");
			
			
		return http.build();
		
	}

}
