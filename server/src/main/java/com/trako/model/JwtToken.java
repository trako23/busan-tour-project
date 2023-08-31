package com.trako.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {
	//generateToken메소드로 만들어진 
	//토큰은 빌더패턴으로 JwtToken에 담아줌
	private String grantType;
	private String accessToken;
	private String refreshToken;
	
}
