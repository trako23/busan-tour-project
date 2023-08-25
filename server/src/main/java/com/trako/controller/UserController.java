package com.trako.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trako.model.User;
import com.trako.repository.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("user")
@Controller
public class UserController {
	
	@Autowired
	private UserMapper userMapper; 
	
	@PostMapping("member")
	public void join(@ModelAttribute User user) {
		userMapper.saveUser(user);
	}
	
}
