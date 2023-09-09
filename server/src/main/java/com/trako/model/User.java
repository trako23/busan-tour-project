package com.trako.model;

import lombok.Data;
//@NoArgsConstructor
@Data
public class User {
	private RoleType user_code;
	private String id;
	private String password;
	private String name;
	private String country;
	private String email;
	private String phone;
//	
//	@Builder
//	public User(RoleType user_code, String id, String password, String name, String country, String email,
//			String phone) {
//		super();
//		this.user_code = user_code;
//		this.id = id;
//		this.password = password;
//		this.name = name;
//		this.country = country;
//		this.email = email;
//		this.phone = phone;
//	}
	
	
	
}
