package com.trako.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trako.model.JwtToken;
import com.trako.model.RefreshTokenDTO;
import com.trako.model.User;
import com.trako.service.JwtTokenService;
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
	//허용하려는 클라이언트URL
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JwtToken> login(@RequestBody Map<String,String> loginForm){
		//JWT 토큰 생성
		JwtToken jwtToken = userService.login(loginForm.get("id"),loginForm.get("password"));
		
		
		return ResponseEntity.ok(jwtToken) ;
			
				
	}
	
	
	
}
