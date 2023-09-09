package com.trako.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trako.model.JwtToken;
import com.trako.model.RefreshTokenDTO;
import com.trako.model.User;
import com.trako.model.UserLoginDto;
import com.trako.repository.UserMapper;
import com.trako.security.JwtTokenProvider;
import com.trako.security.UserInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
	
	private final UserMapper userMapper;
	
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenService jwtTokenService;
	
	@Transactional
	public JwtToken login(String id , String password) {
		 // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id,password);
        
        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        
        // 3. 인증 정보를 기반으로 JWT 토큰 생성 지금 이게 생성 안됨
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
        //log.info("jwtToken : {}",jwtToken);  
        Date refreshTokenExpiresIn = jwtTokenProvider.getRefreshTokenExpiresIn();
        long ExpiresIn = refreshTokenExpiresIn.getTime();
        //Refresh Token 생성 및 저장
  		String refreshTokenValue = jwtToken.getRefreshToken();
  		RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO();
  		refreshTokenDTO.setRefreshToken(refreshTokenValue);
  		refreshTokenDTO.setUserId(id);
  		refreshTokenDTO.setExpirationDate(refreshTokenExpiresIn);
  		
  		
  		jwtTokenService.saveToken(id, refreshTokenDTO, ExpiresIn);
      		
        
        return jwtToken;
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
