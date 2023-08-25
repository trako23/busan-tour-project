package com.trako.model;

import lombok.Data;

@Data
public class User {
	private String user_code;
	private String id;
	private String password;
	private String name;
	private String country;
	private String email;
	private String phone;
	
}
