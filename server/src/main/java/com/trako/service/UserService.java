package com.trako.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trako.config.UserInfo;
import com.trako.model.User;
import com.trako.repository.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
	
	private final UserMapper userMapper;
	
	private final PasswordEncoder passwordEncoder;
	
	
	public User findUser(String id) {
		return userMapper.findUser(id);
	}
	
	public void saveUser(User user) {
		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		userMapper.saveUser(user);
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername: {}", username);	//Username = member_id
		User user = userMapper.findUser(username);
		if(user != null) {
			return new UserInfo(user);
		}
		throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
	}
	
	

}
