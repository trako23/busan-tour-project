package com.trako.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.trako.security.JwtAuthenticationFilter;
import com.trako.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@EnableWebSecurity //스프링 security지원을 가능하게 함
public class SecurityConfig {
	//JwtTokenProvider dependency injection
	private final JwtTokenProvider jwtTokenProvider;
	
	
	//private final CustomAuthFailureHandler customAuthFailureHandler;
	
	@Bean
    AuthenticationManager authenticationManager(
    AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			//HTTP Basic Authentication과 Form Based Authentication을 사용하지 않는다
			.formLogin().disable()
			.httpBasic().disable()
			.authorizeRequests()
			.antMatchers("/user/**","/api/**").permitAll()
			.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
	}

}
