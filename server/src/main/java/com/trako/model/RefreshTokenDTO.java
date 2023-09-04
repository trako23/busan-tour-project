package com.trako.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class RefreshTokenDTO implements Serializable{
	private String refreshToken;
	private String userId;
	//private List<String> roles;
	private Date expirationDate;
}
