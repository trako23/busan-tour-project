package com.trako.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trako.model.JwtToken;
import com.trako.model.User;
import com.trako.security.UserInfo;
import com.trako.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("user")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService; 
	//가입하기
	@PostMapping("join")
	public void join(@ModelAttribute User user) {
		userService.saveUser(user);
	}
	
	//로그인
	@PostMapping("login")
	public ResponseEntity<JwtToken> login(@RequestBody Map<String,String> loginForm){
		//userService.
		
		JwtToken jwtToken = userService.login(loginForm.get("id"),loginForm.get("password"));
		log.info("test:{}",jwtToken);
		return ResponseEntity.ok(jwtToken);
	}
	
	
	
}
